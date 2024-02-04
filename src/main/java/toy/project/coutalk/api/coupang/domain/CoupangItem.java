package toy.project.coutalk.api.coupang.domain;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Entity
@Table(name = "items")
public class CoupangItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",  nullable = false)
    private int id;

    @Column(name = "itemId", length = 50, nullable = false)
    private String itemId;

    @Column(name = "productId", length = 50, nullable = false)
    private String productId;

    @Column(name = "originalPrice", length = 50, nullable = false)
    private String originalPrice;

    @Column(name = "salePrice", length = 50, nullable = false)
    private String salePrice;

    @Column(name = "rating", length = 5)
    private String rating;
}
