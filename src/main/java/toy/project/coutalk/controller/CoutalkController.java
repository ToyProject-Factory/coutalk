package toy.project.coutalk.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import toy.project.coutalk.jpa.service.KakaoKeywordService;

@RestController
public class CoutalkController {

    @Autowired
    KakaoKeywordService kakaoKeywordService;



}
