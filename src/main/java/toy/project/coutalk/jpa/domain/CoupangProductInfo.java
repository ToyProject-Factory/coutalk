package toy.project.coutalk.jpa.domain;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Builder
@Table(name = "coupang_product_info")
public class CoupangProductInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /* 상품 타입 :
     0 : 키워드
     1 : 핫딜
     2 : 인기순위
     */
    @NotNull
    private int Type;

    /* 키워드 */
    private String keyword;

    /* 상품명 */
    @NotNull
    private String productName;

    /* 원래 가격*/
    @NotNull
    private int originalPrice;

    /* 현재 가격*/
    @NotNull
    private int nowPrice;

    /* 최저가 */
    @NotNull
    private int lowPrice;

    /* 상품 평점 */
    private double rating;

    /* 카드 할인 정보 */
    private String cardDiscount;

    /* 적립 정보 */
    private String reward;

    /* 도착정보 */
    private String delivery;


}