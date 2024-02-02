package toy.project.coutalk.service.kakao.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import toy.project.coutalk.jpa.domain.KakaoKeyword;
import toy.project.coutalk.jpa.domain.KakaoUser;
import toy.project.coutalk.jpa.repository.KakaoKeywordRepository;
import toy.project.coutalk.jpa.repository.KakaoUserRepository;
import toy.project.coutalk.service.kakao.KakaoKeywordService;
import toy.project.coutalk.service.kakao.KakaoUserService;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class KakaoUserServiceImpl implements KakaoUserService {

    final KakaoUserRepository kakaoUserRepository;
    final KakaoKeywordService kakaoKeywordService;

    public KakaoUser saveKakaoUser(KakaoUser kakaoUser) {
        return kakaoUserRepository.save(kakaoUser);
    }

    public List<KakaoUser> getAllKakaoUser() {
        return kakaoUserRepository.findAll();
    }
}
