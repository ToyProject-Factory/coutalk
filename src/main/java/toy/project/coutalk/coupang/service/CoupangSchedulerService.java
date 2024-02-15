package toy.project.coutalk.coupang.service;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import toy.project.coutalk.coupang.domain.CoupangProduct;
import toy.project.coutalk.coupang.repository.CoupangProductRepository;

import java.util.List;

/**
 *  쿠팡 제품 정보 스케줄 서비스.
 *
 *  <p>
 *      coupangProductService를 이용하여 시간마다 데이터 베이스에 저장된 제품의 가격을 현재 사이트에 올려지 가격과 비교
 *  </p>
 *
 * @version 0.0.1
 * @author nuclearmonkey21
 */
@Service
public class CoupangSchedulerService {
    private final CoupangProductRepository coupangProductRepository;
    private final CoupangProductService coupangProductService;
    @Autowired
    public CoupangSchedulerService(CoupangProductRepository coupangProductRepository, CoupangProductService coupangProductService) {
        this.coupangProductRepository = coupangProductRepository;
        this.coupangProductService = coupangProductService;
    }

    /**
     *  스케줄 메소드.
     *
     *  <p>
     *      데이터 베이스에 저장된 제품의 정보 업데이트
     *  </p>
     *
     */
    //todo Test 끝나고 1시간 단위로 바꿀예정
    @Scheduled(cron = "0 0 * * * *")
    //@Scheduled(cron = "0 0 * * * *")
    public void searchAndUpdateProducts() {
        List<CoupangProduct> products = coupangProductRepository.findAll();
        for (CoupangProduct product : products) {
            coupangProductService.getProductUrl(product.getItemId(),product.getProductId());
        }
    }
}
