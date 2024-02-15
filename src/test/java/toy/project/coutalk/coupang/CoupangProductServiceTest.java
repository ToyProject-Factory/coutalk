package toy.project.coutalk.coupang;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import toy.project.coutalk.coupang.dto.CoupangProductDTO;
import toy.project.coutalk.coupang.service.CoupangProductService;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class CoupangProductServiceTest {

    @Autowired
    CoupangProductService coupangProductService;

    @BeforeEach
    void setupTest() {

    }

    @AfterEach
    void tearDown() { }

    @Test
    void test() {
        List<CoupangProductDTO> list = coupangProductService.getProductList("노트북");
        for(CoupangProductDTO dto : list){
            System.out.println('-' + "-".repeat(39));
            System.out.println("Product Id: " + dto.getProductId());
            System.out.println("itemId: " + dto.getItemId());
            System.out.println("Original price: " + dto.getOriginalPrice());
            System.out.println("Sale price: " + dto.getSalePrice());
            System.out.println("Star rating: " + dto.getRating());
        }
        CoupangProductDTO dto = list.get(0);

        coupangProductService.setProduct(dto);
        coupangProductService.deleteProduct(dto.getItemId(), dto.getProductId());

        System.out.println("-".repeat(20) + "SET" + "-".repeat(20));
        Optional<CoupangProductDTO> optional = coupangProductService.getProduct(dto.getItemId(), dto.getProductId());

        if(optional.isEmpty()){
            System.out.println("-".repeat(20) + "EMPTY" + "-".repeat(20));
            return;
        }

        dto = optional.get();

        System.out.println("-".repeat(20) + "GET" + "-".repeat(20));
        System.out.println("Product Id: " + dto.getProductId());
        System.out.println("itemId: " + dto.getItemId());
        System.out.println("Original price: " + dto.getOriginalPrice());
        System.out.println("Sale price: " + dto.getSalePrice());
        System.out.println("Star rating: " + dto.getRating());

        coupangProductService.deleteProduct(dto.getItemId(), dto.getProductId());


    }

}