package toy.project.coutalk.api.coupang.service;

import toy.project.coutalk.jpa.domain.KakaoKeyword;
import toy.project.coutalk.jpa.domain.KakaoUser;

public interface CoupangService {

    /* 데이터 수집 */
    void productCollect(KakaoUser kakaoUser);

    /* 데이터 자동 수집 */
    void autoProductClollect();
}
