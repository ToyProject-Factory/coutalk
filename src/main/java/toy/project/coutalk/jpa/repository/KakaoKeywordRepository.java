package toy.project.coutalk.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.project.coutalk.jpa.domain.KakaoKeyword;

import java.util.List;

public interface KakaoKeywordRepository extends JpaRepository<KakaoKeyword, String> {
    List<KakaoKeyword> findByKeyword(String keyword);
}