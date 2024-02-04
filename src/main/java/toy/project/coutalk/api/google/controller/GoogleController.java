package toy.project.coutalk.api.google.controller;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import toy.project.coutalk.api.coupang.service.CoupangService;
import toy.project.coutalk.api.coupang.service.WebCrawlingService;
import toy.project.coutalk.jpa.domain.CoupangProductInfo;
import toy.project.coutalk.jpa.domain.KakaoKeyword;
import toy.project.coutalk.jpa.domain.KakaoUser;
import toy.project.coutalk.service.coupang.CoupangProductInfoService;
import toy.project.coutalk.service.kakao.KakaoKeywordService;
import toy.project.coutalk.service.kakao.KakaoUserService;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coutalk")
public class GoogleController {

    final KakaoKeywordService kakaoKeywordService;
    final KakaoUserService kakaoUserService;
    final CoupangProductInfoService coupangProductInfoService;
    final CoupangService coupangService;

    /* 구글 설문지 정보 등록 및 수정 */
    @RequestMapping("api/googleSurvey")
    public void googleSurvey(@RequestBody KakaoUser kakaoUser){
        System.out.println("kakaoUser : " + kakaoUser);
        /* 사용자 정보 조회 */
        String kakaoId = kakaoUserService.findKakaoId(kakaoUser.getKakaoId()).getKakaoId();

        /* 개인정보 수집 및 사용 동의
        * 0 - 미동의
        * 1 - 동의
        * */
        if(kakaoUser.getIsPersonalSecurity() == 0){
            /* 등록된 사용자가 있으면 사용자 정보 수정 */
            if(!kakaoId.isEmpty()){
                /* 카카오 사용자 정보 수정 */
                kakaoUserService.saveKakaoUser(kakaoUser);
            }
        }else {
            /* 카카오 사용자 정보 등록 */
            kakaoUserService.saveKakaoUser(kakaoUser);
            /* 키워드 개별 등록 */
            kakaoKeywordService.splitKeyword(kakaoUser);
            /* 쿠팡 키워드 수집 시작 */
            coupangService.productCollect(kakaoUser);
        }
    }
}
