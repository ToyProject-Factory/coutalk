package toy.project.coutalk.service.kakao.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toy.project.coutalk.jpa.domain.KakaoKeyword;
import toy.project.coutalk.jpa.repository.KakaoKeywordRepository;
import toy.project.coutalk.service.kakao.KakaoKeywordService;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class KakaoKeywordServiceImpl implements KakaoKeywordService {

    private final KakaoKeywordRepository kakaoKeywordRepository;

    public KakaoKeyword saveKakaoKeyword(KakaoKeyword kakaoKeyword) {
        return kakaoKeywordRepository.save(kakaoKeyword);
    }

    public List<KakaoKeyword> getAllKakaoKeyword() {
        return kakaoKeywordRepository.findAll();
    }

    public Optional<KakaoKeyword> getKakaoKeywordById(String id) {
        return kakaoKeywordRepository.findById(id);
    }

    public void deleteKakaoKeyword(String id) {
        kakaoKeywordRepository.deleteById(id);
    }

    public List<KakaoKeyword> getKakaoKeywordByKeyword(String keyword) {
        return kakaoKeywordRepository.findByKeyword(keyword);
    }
}
