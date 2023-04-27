import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class SwoopProject {

    private WebDriver driver;

    private JavascriptExecutor js;

    @BeforeTest
    public void beforeTests() {
//      Open the Chrome browser
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        js = (JavascriptExecutor) driver;
    }

    @Test
    public void swoop() throws InterruptedException {
        driver.get("https://www.swoop.ge/");

        WebElement hoverElement = driver.findElement(By.xpath("//a[@class='menu-item' and @href='/category/24/dasveneba']"));
        WebElement offersLi = driver.findElement(By.cssSelector("li.cat-2"));

        Actions actions = new Actions(driver);
        actions.moveToElement(hoverElement).perform();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(offersLi));

        WebElement div = hoverElement.findElement(By.xpath("//div[@class='sub-categories']"));
//        List<WebElement> offersDiv = offersLi.findElements(By.xpath("//div[@class='sub-categories']/div[@class='subcategories']/ul/li/a[text(), 'კახეთი']"));

//        WebElement offersDiv = offersLi.findElement(By.xpath("//div[@class='sub-categories']/div[@class='subcategories']/ul/li/a[text(), 'კახეთი']"));
        WebElement offersDiv = offersLi.findElement(By.xpath("/html/body/div[7]/div/div/nav/ul/li[4]/div/div[1]/ul/li[last()-1]/a"));
        Thread.sleep(3000);
//        WebElement lastChildLi = offersDiv.get(offersDiv.size()-1);

//        System.out.println(lastChildLi);
        System.out.println(offersDiv.getText());
//        for (WebElement a:
//             offersDiv) {
//            System.out.println(a.getText());
//        }
//        WebElement lastSuggestion = offersDiv.get(offersDiv.size()-1);
//
//        System.out.println("kakheti: " + lastSuggestion);
//        actions.moveToElement(lastSuggestion);
//        wait.until(ExpectedConditions.elementToBeClickable(lastSuggestion));
        offersDiv.click();


//        WebElement hotelsDiv = driver.findElement(By.cssSelector("dic.special-offer"));
//        WebElement firstHotel = hotelsDiv.findElement(By.xpath("./div/div/a"));
//
//        System.out.println(firstHotel.getAttribute("href"));
        Thread.sleep(3000);
    }


    @AfterTest
    public void afterTests() {
        // Close the browser
        driver.quit();
    }
}
