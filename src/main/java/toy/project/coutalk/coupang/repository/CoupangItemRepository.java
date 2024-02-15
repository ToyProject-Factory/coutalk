package toy.project.coutalk.coupang.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import toy.project.coutalk.coupang.domain.CoupangItem;
import java.util.Optional;

/**
 *  CoupangItemRepository.
 *
 *  <p>
 *      쿠팡의 제품정보를 DB에 저장하거나 가져오기 JpaRepository.
 *  </p>
 *
 * @version 0.0.1
 * @author nuclearmonkey21
 */
public interface CoupangItemRepository extends JpaRepository<CoupangItem, String> {
    /**
     *  제품 찾기.
     *
     *  <p>
     *      itemId와 productId를 이용하여 제품을 찾아 가져온다.
     *  </p>
     * @param  itemId 제품을 찾기위한 검색어.
     * @param  productId 제품의 노출 ID
     * @return Optional<CoupangItem>
     */
    Optional<CoupangItem> findByItemIdAndProductId(String itemId, String productId);

    /**
     *  CoupangItemRepository.
     *
     *  <p>
     *      itemId와 productId를 이용하여 제품을 찾아 삭제한다.
     *  </p>
     * @param  itemId 제품을 찾기위한 검색어.
     * @param  productId 제품의 노출 ID
     */
    @Transactional
    void deleteByItemIdAndProductId(String itemId, String productId);

    /**
     *  CoupangItemRepository.
     *
     *  <p>
     *      itemId와 productId를 이용하여 제품의 존재를 확인한다.
     *  </p>
     * @param  itemId 제품을 찾기위한 검색어.
     * @param  productId 제품의 노출 ID
     */
    boolean existsByItemIdAndProductId(String itemId, String productId);
}
