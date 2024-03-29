import com.gargoylesoftware.htmlunit.BrowserVersion;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class JSexecutor {
    private WebDriver driver;
    private JavascriptExecutor js;


    @BeforeTest
    public void beforeTests() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        driver = new ChromeDriver(options);

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

    @Test
    public void validateText() {
        driver.get("http://webdriveruniversity.com/Scrolling/index.html");

        WebElement elementToScroll = driver.findElement(By.id("zone2-entries"));

        js.executeScript("arguments[0].scrollIntoView();", elementToScroll);

        Actions actions = new Actions(driver);
        actions.moveToElement(elementToScroll).perform();

        String text = (String) js.executeScript("return arguments[0].textContent;", elementToScroll);
        String expectedText = "0 Entries";

        Assert.assertEquals(text, expectedText, "Text does not match expected value");
    }

    @AfterTest
    public void afterTests() {
        // Close the browser
        driver.quit();
    }
}
