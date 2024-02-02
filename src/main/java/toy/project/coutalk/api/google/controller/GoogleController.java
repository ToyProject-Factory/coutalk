package toy.project.coutalk.api.google.controller;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import toy.project.coutalk.jpa.domain.KakaoKeyword;
import toy.project.coutalk.jpa.domain.KakaoUser;
import toy.project.coutalk.service.coupang.CoupangProductInfoService;
import toy.project.coutalk.service.kakao.KakaoKeywordService;
import toy.project.coutalk.service.kakao.KakaoUserService;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coutalk")
public class GoogleController {

    final KakaoKeywordService kakaoKeywordService;
    final KakaoUserService kakaoUserService;
    final CoupangProductInfoService coupangProductInfoService;

    /* 구글 설문지 정보 */
    @RequestMapping("api/googleSurvey")
    public void googleSurvey(@RequestBody KakaoUser kakaoUser){
         System.out.println("kakaoUser : " + kakaoUser);
         /* 카카오 사용자 등록 */
         kakaoUserService.saveKakaoUser(kakaoUser);
         /* 키워드 개별 등록 */
         kakaoKeywordService.splitKeyword(kakaoUser);
    }
}
