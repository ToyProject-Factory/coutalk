package toy.project.coutalk.service.kakao;

import toy.project.coutalk.jpa.domain.KakaoKeyword;
import toy.project.coutalk.jpa.domain.KakaoUser;

import java.util.List;
import java.util.Optional;

/**
 * 카카오 키워드 서비스
 */
public interface KakaoKeywordService {

    KakaoKeyword saveKakaoKeyword(KakaoKeyword kakaoKeyword);

    /* 카카오 키워드 리스트 조회*/
    List<KakaoKeyword> getAllKakaoKeyword();

    /* 카카오 ID의 키워드 조회 */
    Optional<KakaoKeyword> getKakaoKeywordById(String id);

    /* 카카오 키워드 전체 삭제*/
    void deleteKakaoId(String kakaoId);

    /* 특정 키워드 조회 */
    List<KakaoKeyword> getKakaoKeywordByKeyword(String keyword);

    void splitKeyword(KakaoUser kakaoUser);
}
