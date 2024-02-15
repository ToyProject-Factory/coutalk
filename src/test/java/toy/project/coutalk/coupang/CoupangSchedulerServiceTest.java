package toy.project.coutalk.coupang;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import toy.project.coutalk.coupang.service.CoupangSchedulerService;

@SpringBootTest
public class CoupangSchedulerServiceTest {
    @Autowired
    CoupangSchedulerService coupangSchedulerService;
    @BeforeEach
    void setupTest() {

    }

    @AfterEach
    void tearDown() { }

    @Test
    void test() {
        while(true);
    }
}
