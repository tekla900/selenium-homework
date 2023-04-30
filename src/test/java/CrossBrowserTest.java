import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CrossBrowserTest {
    private WebDriver driver;
    private JavascriptExecutor js;


    @BeforeTest
    @Parameters("browser")
    public void beforeTests(String browser) {
        if(browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");
            driver = new ChromeDriver(options);
        }
        else if(browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("-headless");
            driver = new FirefoxDriver(options);
        }

        js = (JavascriptExecutor) driver;
    }


    @Test
    public void hover()  {
        driver.get("http://webdriveruniversity.com/To-Do-List/index.html");

        WebElement hoverElement = driver.findElement(By.xpath("//div[@id='container']/ul/li[contains(text(),'Practice magic')]"));

        Actions actions = new Actions(driver);
        actions.moveToElement(hoverElement).perform();

        js.executeScript("arguments[0].remove();", hoverElement);
    }

    @AfterTest
    public void afterTests() {
        // Close the browser
        driver.quit();
    }
}
