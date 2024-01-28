package toy.project.coutalk.api.coupang;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import toy.project.coutalk.api.coupang.service.WebCrawlingService;

class WebCrawlingServiceTest {
    WebDriver driver;
    WebCrawlingService webCrawlingService;

    @BeforeEach
    void setupTest() {
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--start-maximized");
        options.addArguments("--window-size=1920,1080");
        driver = new ChromeDriver(options);
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