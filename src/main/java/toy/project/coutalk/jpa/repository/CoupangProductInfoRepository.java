package toy.project.coutalk.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.project.coutalk.jpa.domain.CoupangProductInfo;

public interface CoupangProductInfoRepository extends JpaRepository<CoupangProductInfo, Long> {
}