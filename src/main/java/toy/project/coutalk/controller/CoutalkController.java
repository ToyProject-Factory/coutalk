package toy.project.coutalk.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import toy.project.coutalk.service.coupang.serviceImpl.CoupangProductInfoServiceImpl;
import toy.project.coutalk.service.kakao.serviceImpl.KakaoKeywordServiceImpl;
import toy.project.coutalk.service.kakao.serviceImpl.KakaoUserServiceImpl;

@RestController
@RequestMapping("/coutalk")
public class CoutalkController {

    final KakaoKeywordServiceImpl kakaoKeywordServiceImpl;
    final KakaoUserServiceImpl kakaoUserServiceImpl;
    final CoupangProductInfoServiceImpl coupangProductInfoServiceImpl;

    public CoutalkController(KakaoKeywordServiceImpl kakaoKeywordServiceImpl, KakaoUserServiceImpl kakaoUserServiceImpl, CoupangProductInfoServiceImpl coupangProductInfoServiceImpl) {
        this.kakaoKeywordServiceImpl = kakaoKeywordServiceImpl;
        this.kakaoUserServiceImpl = kakaoUserServiceImpl;
        this.coupangProductInfoServiceImpl = coupangProductInfoServiceImpl;
    }

    @RequestMapping("/start")
    public void start(){
        
    }

}
