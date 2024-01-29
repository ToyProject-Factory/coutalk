package toy.project.coutalk.service.kakao.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import toy.project.coutalk.jpa.domain.KakaoUser;
import toy.project.coutalk.jpa.repository.KakaoUserRepository;
import toy.project.coutalk.service.kakao.KakaoUserService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class KakaoUserServiceImpl implements KakaoUserService {

    final KakaoUserRepository kakaoUserRepository;

    public KakaoUser saveKakaoUser(KakaoUser kakaoUser) {
        return kakaoUserRepository.save(kakaoUser);
    }

    public List<KakaoUser> getAllKakaoUser() {
        return kakaoUserRepository.findAll();
    }
}
