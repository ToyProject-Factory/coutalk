package toy.project.coutalk.api.coupang;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import toy.project.coutalk.api.coupang.dto.CoupangItemDTO;
import toy.project.coutalk.api.coupang.service.CoupangItemService;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class CoupangItemServiceTest {

    @Autowired
    CoupangItemService coupangItemService;

    @BeforeEach
    void setupTest() {

    }

    @AfterEach
    void tearDown() { }

    @Test
    void test() {
        List<CoupangItemDTO> list = coupangItemService.getItemList("노트북");
        for(CoupangItemDTO dto : list){
            System.out.println('-' + "-".repeat(39));
            System.out.println("Product Id: " + dto.getProductId());
            System.out.println("itemId: " + dto.getItemId());
            System.out.println("Original price: " + dto.getOriginalPrice());
            System.out.println("Sale price: " + dto.getSalePrice());
            System.out.println("Star rating: " + dto.getRating());
        }
        CoupangItemDTO dto = list.get(0);

        coupangItemService.setItemInfo(dto);

        if(coupangItemService.existsItemInfo(dto.getItemId(), dto.getProductId())){
            coupangItemService.deleteItemInfo(dto.getItemId(), dto.getProductId());
        }


        System.out.println("-".repeat(20) + "SET" + "-".repeat(20));
        Optional<CoupangItemDTO> optional = coupangItemService.getItemInfo(dto.getItemId(), dto.getProductId());

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

        coupangItemService.deleteItemInfo(dto.getItemId(), dto.getProductId());

    }

}