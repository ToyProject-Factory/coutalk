package toy.project.coutalk.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import toy.project.coutalk.jpa.repository.CoupangProductInfoRepository;

@Component
public class CoupangProductInfoService {

    @Autowired
    CoupangProductInfoRepository coupangProductInfoRepository;

}
