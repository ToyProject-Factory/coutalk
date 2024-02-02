package toy.project.coutalk.jpa.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "kakao_user")
public class KakaoUser {

    /* 카카오톡 사용자 계정 */
    @Id
    @Column(name = "kakao_id")
    private String kakaoId;

    /* 사용자 성명 */
    private String kakaoName;

    /* 구독 여부
    * 0 : 구독 안함
    * 1 : 구독
    * */
    @ColumnDefault("0")
    private int isHotdeal;

    @ColumnDefault("0")
    private int isRank;

    /* 개인정보 사용 동의 여부
    * 0 : 미동의
    * 1 : 동의
    * */
    @ColumnDefault("0")
    private int isPersonalSecurity;

    @OneToMany(mappedBy = "kakaoUser", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<KakaoKeyword> kakaoKeyword;
}