package toy.project.coutalk.api.coupang.config;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
/**
 *  Web Driver Configuration.
 *
 *  <p>
 *      Web Driver 생성과 Chrome Driver 선택 그리고 Chrome Options 설정
 *  </p>
 *
 * @version : 0.0.1
 * @author : nuclearmonkey21
 */
@Configuration
public class WebDriverConfig {
    @Bean
    public WebDriver webDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--start-maximized");
        options.addArguments("--window-size=1920,1080");
        return new ChromeDriver(options);
    }
}
