package toy.project.coutalk.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.project.coutalk.jpa.domain.KakaoKeyword;

import java.util.List;
import java.util.Optional;

public interface KakaoKeywordRepository extends JpaRepository<KakaoKeyword, String> {
    List<KakaoKeyword> findByKeyword(String keyword);

    void deleteByKakaoId(String kakaoId);

    List<KakaoKeyword> findByKakaoId(String kakaoId);
}