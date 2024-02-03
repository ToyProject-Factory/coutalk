package toy.project.coutalk.service.kakao;

import toy.project.coutalk.jpa.domain.KakaoUser;

import java.util.List;

/**
 * 카카오 사용자 서비스
 */
public interface KakaoUserService {

    /* 카카오 사용자 저장 */
    public KakaoUser saveKakaoUser(KakaoUser kakaoUser);

    /* 카카오 사용자 리스트 조회 */
    public List<KakaoUser> getAllKakaoUser();

    String findKakaoId(String kakaoId);
}
