package toy.project.coutalk.api.coupang.service;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;
import toy.project.coutalk.jpa.domain.CoupangProductInfo;
import toy.project.coutalk.jpa.domain.KakaoKeyword;
import toy.project.coutalk.service.coupang.CoupangProductInfoService;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 *  데이터 크롤링.
 *
 *  <p>
 *      WebDriver의 기능을 이용하여 지정된 URL을 통해 웹사이트의 HTML을 가져온 후 데이터를 기호에 맞게 수정하여 출력
 *  </p>
 *
 * @version : 0.0.1
 * @author : nuclearmonkey21
 */
@Service
@RequiredArgsConstructor
public class WebCrawlingService {

    final CoupangProductInfoService coupangProductInfoService;
    /**
     *  .
     *
     *  <p>
     *      WebDriver 셋업
     *  </p>
     *
     * @return : WebDriver 객체.
     */
    private WebDriver setDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless");

        /* headless 옵션 차단 방어 */
        //options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-popup-blocking");
        // options.addArguments("--start-maximized");
        options.addArguments("--window-size=10,10");
        System.setProperty("webDriver.chrome.driver", "path/to/chromedriver");
        System.setProperty("java.awt.headless", "false");
        System.setProperty("DISPLAY", ":99");
        return new ChromeDriver(options);
    }
    /**
     *  .
     *
     *  <p>
     *      키워드를 사용하여 쿠팡에서 검색 후, 첫번째 페이지의 상품 중 광고 제품을 제외한 모든 제품을 가져온다.
     *  </p>
     *
     * @param : keyword: 제품을 찾기위한 검색어.
     * @return : 현재는 콘솔 출력으로 나타남.
     * @throws : 제품이 null -> NullPointException
     */
    public List<CoupangProductInfo> getItemInfo(String keyword) {
        WebDriver driver = setDriver();
        List<CoupangProductInfo> coupangProductInfoList = new ArrayList<>();

        try {
            //todo : 쿠팡 이 외에 다른 사이트도 추가 가능성 있기에 차후 변경
            driver.get("https://www.coupang.com/np/search?component=&q=" + keyword + "&channel=auto");

            String xpathExpression = "//ul[@id='productList']";

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement productList = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@id='productList']")));

            //WebElement productList = driver.findElement(By.xpath(xpathExpression));

            Document document = Jsoup.parse(productList.getAttribute("outerHTML"));

            Elements products = document.select("li.search-product");
            for (Element product : products) {
                //광고 및 랭킹 10위권 밖의 제품을 제외.
                if (!product.select(".search-product__ad-badge").isEmpty() || product.select("span.number").isEmpty()) {
                    continue;
                }

                //todo : 차후 데이터의 활용도에 맞게 수정
                String productId = product.select("a.search-product-link").attr("data-product-id");
                String itemId = product.select("a.search-product-link").attr("data-item-id");
                String productRank = product.select("span.number").text().trim();
                String productName = product.select("div.name").text().trim();
                String originalPrice = product.select("del.base-price").text().trim();
                String salePrice = product.select("strong.price-value").text().trim();
                String rating = product.select("em.rating").text().trim();
                String reviewCount = product.select("span.rating-total-count").text().trim();
                String cardDiscount = product.select("span.ccid-txt").text().trim().isEmpty() ? "N/A" : product.select("span.ccid-txt").text().trim();
                String rewardInfo = product.select("span.reward-cash-txt").text().trim().isEmpty() ? "N/A" : product.select("span.reward-cash-txt").text().trim();
                String deliveryInfo = product.select("span.arrival-info").text().trim();
                String productUrl = "https://www.coupang.com"+product.select("a.search-product-link").attr("href");

                // Print or process the product information as needed
                System.out.println('-' + "-".repeat(39));
                System.out.println("Product Name: " + productName);
                System.out.println("Product Id: " + productId);
                System.out.println("itemId : " + itemId);
                System.out.println("Original price: " + originalPrice);
                System.out.println("Sale price: " + salePrice);
                System.out.println("Star rating: " + rating);
                System.out.println("Number of reviews: " + reviewCount);
                System.out.println("Card discount information: " + cardDiscount);
                System.out.println("Reward information: " + rewardInfo);
                System.out.println("Delivery information: " + deliveryInfo);
                System.out.println("ProductUrl infomation: " + productUrl);

                /* 데이터 가공 */
                originalPrice = originalPrice.replaceAll("[^0-9]","");
                salePrice = salePrice.replaceAll("[^0-9]","");
                reviewCount = reviewCount.replaceAll("[^0-9]","");

                /* 상품 정보 등록 */
                CoupangProductInfo coupangProductInfo =  CoupangProductInfo.builder()
                        .productId(Long.parseLong(productId))
                        .itemId(itemId.isEmpty() ? 0 : Long.parseLong(itemId))
                        .productRank(productRank)
                        .keyword(keyword)
                        .type(0)
                        .productName(productName)
                        .originalPrice(originalPrice.isEmpty() ? 0 : Integer.parseInt(originalPrice))
                        .nowPrice(salePrice.isEmpty() ? 0 : Integer.parseInt(salePrice))
                        .rating(rating.isEmpty() ? 0 : Double.parseDouble(rating))
                        .reviewCount(reviewCount.isEmpty() ? 0 : Integer.parseInt(reviewCount))
                        .reward(rewardInfo)
                        .delivery(deliveryInfo)
                        .productUrl(productUrl)
                        .build();

                coupangProductInfoList.add(coupangProductInfo);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the WebDriver when done
            driver.quit();
        }

        return coupangProductInfoList;
    }
}
