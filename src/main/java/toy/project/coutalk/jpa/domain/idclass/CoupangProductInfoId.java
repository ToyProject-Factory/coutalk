package toy.project.coutalk.jpa.domain.idclass;

import lombok.Data;

import java.io.Serializable;


@Data
public class CoupangProductInfoId implements Serializable {
    private long productId;
    private long itemId;

    // 생성자, equals(), hashCode() 등 필요한 메서드 구현
}
