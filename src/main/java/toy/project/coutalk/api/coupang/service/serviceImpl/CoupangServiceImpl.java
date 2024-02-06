package toy.project.coutalk.api.coupang.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toy.project.coutalk.api.coupang.service.CoupangService;
import toy.project.coutalk.api.coupang.service.WebCrawlingService;
import toy.project.coutalk.jpa.domain.CoupangProductInfo;
import toy.project.coutalk.jpa.domain.KakaoKeyword;
import toy.project.coutalk.jpa.domain.KakaoUser;
import toy.project.coutalk.jpa.repository.CoupangProductInfoRepository;
import toy.project.coutalk.jpa.repository.KakaoKeywordRepository;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CoupangServiceImpl implements CoupangService {

    final KakaoKeywordRepository kakaoKeywordRepository;
    final WebCrawlingService webCrawlingService;
    final CoupangProductInfoRepository coupangProductInfoRepository;

    @Override
    public void productCollect(KakaoUser kakaoUser) {
        List<KakaoKeyword> keywordList = kakaoKeywordRepository.findByKakaoId(kakaoUser.getKakaoId());

        System.out.println("키워드 배열 테스트 시작");
        keywordList.forEach(System.out::println);
        System.out.println("키워드 배열 테스트 종료");

        keywordList.forEach(kakaoKeyword -> {
            String keyword = kakaoKeyword.getKeyword();
            String etc = kakaoKeyword.getEtc().toLowerCase();

            if (etc.equals("split")) {
                System.out.println("상품 수집 키워드 : "+ keyword);

                /* 키워드 상품 등록 */
                coupangProductInfoRepository.saveAll(webCrawlingService.getItemInfo(keyword));

                /* 신규 등록 알림 전송 */

            }
        });
    }

    @Override
    public void autoProductClollect() {
        List<KakaoKeyword> keywordList = kakaoKeywordRepository.findAll();

        keywordList.forEach(kakaoKeyword -> {
            String keyword = kakaoKeyword.getKeyword();
            String etc = kakaoKeyword.getEtc().toLowerCase();

            if (etc.equals("split")) {
                System.out.println("상품 수집 키워드: " + keyword);

                /* 키워드 상품 조회 */
                List<CoupangProductInfo> newCoupangProductInfoList = webCrawlingService.getItemInfo(keyword);

                /* 비교 */
                newCoupangProductInfoList.forEach(productItem -> {
                    long productId = productItem.getProductId();
                    long itemId = productItem.getItemId();

                    /* 등록된 쿠팡 상품 가져오기 */
                    CoupangProductInfo dbProductItem = coupangProductInfoRepository.findByProductIdAndItemId(productId, itemId);

                    /* 데이터가 없으면 저장 */
                    if(dbProductItem == null){
                        coupangProductInfoRepository.save(productItem);
                    }
                    /* 현재 등록된 최저가가 없으면 */
                    else if(dbProductItem.getLowPrice() == 0) {
                        /* 현재 가격과 조회된 상품의 가격 비교 */
                        if (productItem.getNowPrice() < dbProductItem.getNowPrice()) {
                            /* 최저가 등록 */
                            productItem.updateLowPrice(productItem.getNowPrice());
                            coupangProductInfoRepository.save(productItem);

                            /* 최저가 갱신 알림 전송 로직 구현 필요 */

                        }else{
                            /*Low값 추가*/
                            productItem.updateLowPrice(productItem.getNowPrice());
                            coupangProductInfoRepository.save(productItem);
                        }
                    } else {
                        /* 최저가가 있으면 최저가와 비교 */
                        if (productItem.getNowPrice() < dbProductItem.getLowPrice()) {

                            productItem.updateLowPrice(productItem.getNowPrice());
                            coupangProductInfoRepository.save(productItem);

                            /* 최저가 갱신 알림 전송 */
                        }
                    }
                });
            }
        });
    }
}
