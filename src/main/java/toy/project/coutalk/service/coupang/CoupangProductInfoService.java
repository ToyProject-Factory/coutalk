package toy.project.coutalk.service.coupang;

import toy.project.coutalk.jpa.domain.CoupangProductInfo;

import java.util.List;

/**
 * 쿠팡 상품 서비스
 */
public interface CoupangProductInfoService {
    
    /* 쿠팡 상품 등록 */
    CoupangProductInfo save(CoupangProductInfo coupangProductInfo);

    /* 쿠팡 상품 전체 조회 */
    List<CoupangProductInfo> getAllCoupangProductInfo();

    
    /* 쿠팡 상품 삭제*/
    void deleteCoupangProductInfo(Long id);
    
    /* 쿠팡 상품 키워드 조회 */
    List<CoupangProductInfo> getCoupangProductInfoByKeyword(String keyword);
}
