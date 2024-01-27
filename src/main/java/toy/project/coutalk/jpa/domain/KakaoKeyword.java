package toy.project.coutalk.jpa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "kakao_keyword")
public class KakaoKeyword {
    @Id
    @Column(name = "KAKAO_ID", nullable = false)
    private String kakaoId;

    private String keyword;

}