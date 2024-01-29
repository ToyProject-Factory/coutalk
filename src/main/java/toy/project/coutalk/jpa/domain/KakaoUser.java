package toy.project.coutalk.jpa.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Entity
@NoArgsConstructor
@ToString
@Table(name = "kakao_user")
public class KakaoUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /* 카카오톡 사용자 계정 */
    private String kakaoId;

    /* 사용자 성명 */
    private String kakaoName;

    /* 핫딜 구독 여부
    * 0 : 구독 안함
    * 1 : 구독
    * */
    @ColumnDefault("0")
    private boolean isHotdeal;

    @ManyToOne
    private KakaoKeyword kakaoKeyword;





}