package toy.project.coutalk.jpa.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Table(name = "kakao_keyword")
public class KakaoKeyword {
    @Id
    @Column(name = "KAKAO_ID", nullable = false)
    private String kakaoId;

    private String keyword;

}