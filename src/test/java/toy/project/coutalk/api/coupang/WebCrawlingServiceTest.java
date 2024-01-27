package toy.project.coutalk.api.coupang;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import toy.project.coutalk.api.coupang.service.WebCrawlingService;

class WebCrawlingServiceTest {
    WebDriver driver;
    WebCrawlingService webCrawlingService;

    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
        webCrawlingService = new WebCrawlingService(driver);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void test() {
        webCrawlingService.getItemInfo("노트북");
    }

}