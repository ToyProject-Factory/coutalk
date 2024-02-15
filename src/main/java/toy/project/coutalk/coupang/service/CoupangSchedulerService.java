package toy.project.coutalk.coupang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import toy.project.coutalk.coupang.domain.CoupangProduct;
import toy.project.coutalk.coupang.repository.CoupangProductRepository;

import java.util.List;

@Service
public class CoupangProductSchedulerService {
    private final CoupangProductRepository coupangProductRepository;
    private final CoupangProductService coupangProductService;
    @Autowired
    public CoupangProductSchedulerService(CoupangProductRepository coupangProductRepository, CoupangProductService coupangProductService) {
        this.coupangProductRepository = coupangProductRepository;
        this.coupangProductService = coupangProductService;
    }
    @Scheduled(cron = "0/10 * * * * *") // Execute every hour
    public void searchAndUpdateProducts() {
        List<CoupangProduct> items = coupangProductRepository.findAll();

        for (CoupangProduct item : items) {
            // Assuming you have getters for itemId and productId in CoupangItemEntity
            System.out.println("@Scheduled annotation : 10초에 1번씩 console 찍기");
        }
    }
}
