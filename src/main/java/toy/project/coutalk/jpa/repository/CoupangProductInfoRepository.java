package toy.project.coutalk.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.project.coutalk.jpa.domain.CoupangProductInfo;

import java.util.List;

public interface CoupangProductInfoRepository extends JpaRepository<CoupangProductInfo, Long> {
    List<CoupangProductInfo> findByKeyword(String keyword);
}