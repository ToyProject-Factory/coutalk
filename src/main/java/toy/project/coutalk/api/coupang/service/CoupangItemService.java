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
import toy.project.coutalk.api.coupang.dto.CoupangItemDTO;
import toy.project.coutalk.api.coupang.domain.CoupangItem;
import toy.project.coutalk.api.coupang.repository.CoupangItemRepository;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *  데이터 크롤링.
 *
 *  <p>
 *      WebDriver의 기능을 이용하여 지정된 URL을 통해 웹사이트의 HTML을 가져온 후 데이터를 기호에 맞게 수정하여 출력
 *  </p>
 *
 * @version 0.0.1
 * @author nuclearmonkey21
 */
@Service
@RequiredArgsConstructor
public class CoupangItemService {

    private final CoupangItemRepository coupangItemRepository;
    /**
     *  셋업.
     *
     *  <p>
     *      WebDriver 셋업
     *  </p>
     *
     * @return WebDriver 객체.
     */
    private WebDriver setDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--start-maximized");
        options.addArguments("--window-size=1920,1080");
        System.setProperty("webDriver.chrome.driver", "path/to/chromedriver");
        System.setProperty("java.awt.headless", "false");
        System.setProperty("DISPLAY", ":99");
        return new ChromeDriver(options);
    }
    /**
     *  키워드 검색.
     *
     *  <p>
     *      키워드를 사용하여 쿠팡에서 검색 후, 첫번째 페이지의 상품 중 광고 제품을 제외한 모든 제품을 가져온다.
     *  </p>
     *
     * @param  keyword 제품을 찾기위한 검색어.
     * @return 현재는 콘솔 출력으로 나타남.
     * @throws NullPointerException WebDriver 상태를 확인
     */
    public List<CoupangItemDTO> getItemList(String keyword) throws NullPointerException{
        WebDriver driver = setDriver();
        List<CoupangItemDTO> list = new ArrayList<CoupangItemDTO>();

            //todo : 쿠팡 이 외에 다른 사이트도 추가 가능성 있기에 차후 변경
            driver.get("https://www.coupang.com/np/search?component=&q=" + keyword + "&channel=relate");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement productList = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@id='productList']")));

            Document document = Jsoup.parse(productList.getAttribute("outerHTML"));

            Elements products = document.select("li.search-product");
            for (Element product : products) {
                 //광고 및 랭킹 10위권 밖의 제품을 제외.
                if (!product.select(".search-product__ad-badge").isEmpty() || product.select("span.number").isEmpty()) {
                    continue;
                }

                String originalPrice = product.select("del.base-price").text().trim();
                String salePrice = product.select("strong.price-value").text().trim();
                String rating = product.select("span.number").text().trim();
                //상품 노출 ID
                String productId = product.select("a").attr("data-product-id").trim();
                //상품 옵션 ID
                String itemId = product.select("a").attr("data-item-id").trim();

                CoupangItemDTO dto = new CoupangItemDTO();
                dto.setItemId(itemId);
                dto.setProductId(productId);
                dto.setRating(rating);
                dto.setSalePrice(salePrice);
                dto.setOriginalPrice(originalPrice);

                list.add(dto);
            }

            driver.quit();

        return list;
    }

    /**
     *  DB 검색.
     *
     *  <p>
     *      itemId와 productId를 사용하여 데이터 베이스에서 제품의 정보를 가져온다.
     *  </p>
     *
     * @param  itemId 제품의 상세 옵션 적용 ID.
     * @param  productId 제품의 노출 ID
     * @return CoupangItemDTO.
     */
    public Optional<CoupangItemDTO>  getItemInfo(String itemId, String productId) {
        Optional<CoupangItem> optional = coupangItemRepository.findByItemIdAndProductId(itemId, productId);

        return optional.map(this::convertToDTO);
    }
    /**
     *  DB 저장.
     *
     *  <p>
     *      CoupangItemDTO를 사용하여 데이터 베이스에서 제품의 정보를 저장한다.
     *  </p>
     *
     * @param  dto 제품의 정보를 가진 CoupangItemDTO 통해 데이터 저장.
     * @return CoupangItemDTO.
     */
    public CoupangItemDTO setItemInfo(CoupangItemDTO dto) {
        CoupangItem entity = CoupangItem.builder()
                .itemId(dto.getItemId())
                .productId(dto.getProductId())
                .originalPrice(dto.getOriginalPrice())
                .salePrice(dto.getSalePrice())
                .rating(dto.getRating())
                .build();
        entity = coupangItemRepository.save(entity);
        return convertToDTO(entity);
    }
    /**
     *  DB 삭제.
     *
     *  <p>
     *      itemId와 productId를 사용하여 데이터 베이스에서 제품의 정보를 삭제한다.
     *  </p>
     *
     * @param  itemId 제품의 상세 옵션 적용 ID.
     * @param  productId 제품의 노출 ID
     * @return CoupangItemDTO.
     */
    public void deleteItemInfo(String itemId, String productId) {
        coupangItemRepository.deleteByItemIdAndProductId(productId, itemId);
    }

    /**
     *  DB 확인.
     *
     *  <p>
     *      itemId와 productId를 사용하여 데이터 베이스에서 제품의 존재를 확인한다.
     *  </p>
     *
     * @param  itemId 제품의 상세 옵션 적용 ID.
     * @param  productId 제품의 노출 ID
     * @return boolean.
     */
    public boolean existsItemInfo(String itemId, String productId) {
        return coupangItemRepository.existsByItemIdAndProductId(productId, itemId);
    }

    /**
     *  DB 삭제.
     *
     *  <p>
     *      CoupangItem을 CoupangItemDTO로 변환한다.
     *  </p>
     *
     * @param  entity DB에 저장되어 있는 제품의 상세 데이터.
     * @return CoupangItemDTO.
     */
    public CoupangItemDTO convertToDTO(CoupangItem entity){
        return new CoupangItemDTO(entity);
    }
}
