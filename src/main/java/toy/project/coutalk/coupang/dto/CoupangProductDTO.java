package toy.project.coutalk.coupang.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import toy.project.coutalk.coupang.domain.CoupangProduct;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CoupangProductDTO {

    private int id;

    @NotBlank
    @NotEmpty(message = "itemId must not be empty.")
    @Size(max = 20, message = "itemId is too long.")
    @Pattern(regexp = "^[\\\\d]+$", message = "itemId is invalid.")
    private String itemId;

    @NotBlank
    @NotEmpty(message = "productId must not be empty.")
    @Size(max = 20, message = "productId is too long.")
    @Pattern(regexp = "^[\\\\d]+$", message = "productId is invalid.")
    private String productId;

    @NotBlank
    @NotEmpty(message = "originalPrice must not be empty.")
    @Pattern(regexp = "^[1-9]\\\\d{0,2}(,\\\\d{3})*$", message = "originalPrice is invalid.")
    private String originalPrice;

    @NotBlank
    @NotEmpty(message = "salePrice must not be empty.")
    @Pattern(regexp = "^[1-9]\\\\d{0,2}(,\\\\d{3})*$", message = "salePrice is invalid.")
    private String salePrice;

    @NotBlank
    @Pattern(regexp = "^[\\\\d]?$", message = "rating is invalid.")
    private String rating;

    public CoupangProductDTO(CoupangProduct entity){
        this.id = entity.getId();
        this.itemId = entity.getItemId();
        this.productId = entity.getProductId();
        this.originalPrice = entity.getOriginalPrice();
        this.salePrice = entity.getSalePrice();
        this.rating = entity.getRating();
    }
}
