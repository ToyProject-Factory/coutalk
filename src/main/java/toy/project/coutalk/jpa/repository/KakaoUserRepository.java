package toy.project.coutalk.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.project.coutalk.jpa.domain.KakaoUser;

public interface KakaoUserRepository extends JpaRepository<KakaoUser, Long> {
}