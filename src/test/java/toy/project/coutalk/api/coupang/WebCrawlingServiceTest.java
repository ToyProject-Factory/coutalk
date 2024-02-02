package toy.project.coutalk.api.coupang;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import toy.project.coutalk.api.coupang.service.WebCrawlingService;

class WebCrawlingServiceTest {

    WebCrawlingService webCrawlingService;

    @BeforeEach
    void setupTest() {
        webCrawlingService = new WebCrawlingService();
    }

    @AfterEach
    void tearDown() { }

    @Test
    void test() {
        webCrawlingService.getItemInfo("노트북");
    }

}