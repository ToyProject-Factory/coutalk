package toy.project.coutalk.jpa.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Persistent;
import org.springframework.data.domain.Persistable;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Builder
@Table(name = "kakao_keyword")
public class KakaoKeyword implements Persistable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "kakao_id", nullable = false)
    private String kakaoId;

    private String keyword;

    @ManyToOne
    private KakaoUser kakaoUser;

    @Override
    public Object getId() {
        return null;
    }

    @Override
    public boolean isNew() {
        return true;
    }
}