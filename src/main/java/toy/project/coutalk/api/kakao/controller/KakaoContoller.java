package toy.project.coutalk.api.kakao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import toy.project.coutalk.api.kakao.service.KakaoService;
@RestController
public class KakaoContoller {

    @Autowired
    public KakaoService kakaoService;

    @RequestMapping("/login")
    public RedirectView goKakaoOAuth() {
        return kakaoService.goKakaoOAuth();
    }

    @RequestMapping("/login-callback")
    public RedirectView loginCallback(@RequestParam("code") String code) {
        return kakaoService.loginCallback(code);
    }

    @RequestMapping("/profile")
    public String getProfile() {
        return kakaoService.getProfile();
    }

    @RequestMapping("/authorize")
    public RedirectView goKakaoOAuth(@RequestParam("scope") String scope) {
        return kakaoService.goKakaoOAuth(scope);
    }

    @RequestMapping("/friends")
    public String getFriends() {
        return kakaoService.getFriends();
    }

    @RequestMapping("/message")
    public String message() {
        return kakaoService.message();
    }

    @RequestMapping("/friends-message")
    public String friends_message(@RequestParam("uuids") String uuids) {
        return kakaoService.friendMessage(uuids);
    }

    @RequestMapping("/logout")
    public String logout() {
        return kakaoService.logout();
    }
}
