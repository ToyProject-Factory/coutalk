package toy.project.coutalk.api.coupang.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toy.project.coutalk.api.coupang.service.CoupangService;
import toy.project.coutalk.api.coupang.service.WebCrawlingService;
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
        List<KakaoKeyword> keywordArr = kakaoKeywordRepository.findByKakaoId(kakaoUser.getKakaoId());

        System.out.println("키워드 배열 테스트 시작");
        keywordArr.forEach(System.out::println);
        System.out.println("키워드 배열 테스트 종료");

        keywordArr.forEach(kakaoKeyword -> {
            String keyword = kakaoKeyword.getKeyword();
            String etc = kakaoKeyword.getEtc().toLowerCase();

            if (etc.equals("split")) {
                System.out.println("상품 수집 키워드 : "+ keyword);
                coupangProductInfoRepository.saveAll(webCrawlingService.getItemInfo(keyword));
            }
        });
    }
}
