import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WebTablesTest {
    private WebDriver driver;

    @BeforeTest
    public void beforeTests() {
//      Open the Chrome browser
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void printPrice() {
        driver.get("https://techcanvass.com/Examples/webtable.html");
        WebElement hondaPrice = driver.findElement(By.xpath("//td[contains(text(),'Honda')]/following-sibling::td[2]"));
        System.out.println("Honda price: " + hondaPrice.getText());
    }

    @AfterTest
    public void afterTests() {
        // Close the browser
        driver.quit();
    }
}