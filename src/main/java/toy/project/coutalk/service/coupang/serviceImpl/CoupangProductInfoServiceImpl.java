package toy.project.coutalk.service.coupang.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import toy.project.coutalk.jpa.domain.CoupangProductInfo;
import toy.project.coutalk.jpa.repository.CoupangProductInfoRepository;
import toy.project.coutalk.service.coupang.CoupangProductInfoService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CoupangProductInfoServiceImpl implements CoupangProductInfoService {

    private final CoupangProductInfoRepository coupangProductInfoRepository;

    public CoupangProductInfo save(toy.project.coutalk.jpa.domain.CoupangProductInfo coupangProductInfo) {
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
