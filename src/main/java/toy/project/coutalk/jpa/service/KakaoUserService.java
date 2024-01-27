package toy.project.coutalk.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import toy.project.coutalk.jpa.repository.KakaoUserRepository;

@Component
public class KakaoUserService {

    @Autowired
    KakaoUserRepository kakaoUserRepository;

}
