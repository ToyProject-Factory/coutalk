package toy.project.coutalk.api.coupang.service;

import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;

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

    private final WebDriver driver;
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
    public void getItemInfo(String keyword) {
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
                if (!product.select(".search-product__ad-badge").isEmpty()) {
                    continue;
                }
                //todo : 차후 데이터의 활용도에 맞게 수정
                String productName = product.select("div.name").text().trim();
                String originalPrice = product.select("del.base-price").text().trim();
                String salePrice = product.select("strong.price-value").text().trim();
                String rating = product.select("em.rating").text().trim();
                String reviewCount = product.select("span.rating-total-count").text().trim();
                String cardDiscount = product.select("span.ccid-txt").text().trim().isEmpty() ? "N/A" : product.select("span.ccid-txt").text().trim();
                String rewardInfo = product.select("span.reward-cash-txt").text().trim().isEmpty() ? "N/A" : product.select("span.reward-cash-txt").text().trim();
                String deliveryInfo = product.select("span.arrival-info").text().trim();

                // Print or process the product information as needed
                System.out.println('-' + "-".repeat(39));
                System.out.println("Product Name: " + productName);
                System.out.println("Original price: " + originalPrice);
                System.out.println("Sale price: " + salePrice);
                System.out.println("Star rating: " + rating);
                System.out.println("Number of reviews: " + reviewCount);
                System.out.println("Card discount information: " + cardDiscount);
                System.out.println("Reward information: " + rewardInfo);
                System.out.println("Delivery information: " + deliveryInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the WebDriver when done
            driver.quit();
        }
    }
}
