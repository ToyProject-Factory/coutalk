package toy.project.coutalk.service.coupang.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import toy.project.coutalk.jpa.domain.CoupangProductInfo;
import toy.project.coutalk.jpa.repository.CoupangProductInfoRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CoupangProductInfoServiceImpl {

    private final CoupangProductInfoRepository coupangProductInfoRepository;

    public CoupangProductInfo saveCoupangProductInfo(toy.project.coutalk.jpa.domain.CoupangProductInfo coupangProductInfo) {
        return coupangProductInfoRepository.save(coupangProductInfo);
    }

    public List<CoupangProductInfo> getAllCoupangProductInfo() {
        return coupangProductInfoRepository.findAll();
    }

    public void deleteCoupangProductInfo(Long id) {
        coupangProductInfoRepository.deleteById(id);
    }
    public List<CoupangProductInfo> getCoupangProductInfoByKeyword(String keyword) {
        return coupangProductInfoRepository.findByKeyword(keyword);
    }

}
