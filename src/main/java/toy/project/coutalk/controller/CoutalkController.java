package toy.project.coutalk.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import toy.project.coutalk.service.coupang.CoupangProductInfoService;
import toy.project.coutalk.service.kakao.KakaoKeywordService;
import toy.project.coutalk.service.kakao.KakaoUserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coutalk")
public class CoutalkController {

    final KakaoKeywordService kakaoKeywordService;
    final KakaoUserService kakaoUserService;
    final CoupangProductInfoService coupangProductInfoService;

    @RequestMapping("/start")
    public void start(){
        System.out.println(kakaoKeywordService.getAllKakaoKeyword());
        
    }

}
