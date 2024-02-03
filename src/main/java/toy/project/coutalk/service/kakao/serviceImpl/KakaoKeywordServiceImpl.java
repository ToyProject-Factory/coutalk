package toy.project.coutalk.service.kakao.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toy.project.coutalk.jpa.domain.KakaoKeyword;
import toy.project.coutalk.jpa.domain.KakaoUser;
import toy.project.coutalk.jpa.repository.KakaoKeywordRepository;
import toy.project.coutalk.service.kakao.KakaoKeywordService;

import java.util.Arrays;
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

    public void deleteKakaoId(String kakaoId) {
        kakaoKeywordRepository.deleteByKakaoId(kakaoId);
    }

    public List<KakaoKeyword> getKakaoKeywordByKeyword(String keyword) {
        return kakaoKeywordRepository.findByKeyword(keyword);
    }

    @Override
    @Transactional
    public void splitKeyword(KakaoUser kakaoUser) {
        String kakaoId = kakaoUser.getKakaoId();
        String keyword = String.valueOf(kakaoUser.getKakaoKeyword().getFirst().getKeyword());
        String[] keywordArr = keyword.split(",");

        this.deleteKakaoId(kakaoId);
        Arrays.stream(keywordArr).forEach(key -> {
            if(!key.trim().isEmpty()) {
                KakaoKeyword kakaoKeyword = KakaoKeyword.builder()
                        .kakaoId(kakaoId)
                        .keyword(key.trim())
                        .etc("split")
                        .build();
                this.saveKakaoKeyword(kakaoKeyword);
            }
        });
    }
}
