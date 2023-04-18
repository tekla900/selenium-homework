import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SwitchToTest {
    private WebDriver driver;

    @BeforeTest
    public void beforeTests() {
//      Open the Chrome browser
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/iframe");
    }

    @Test
    public void iframes() {
        driver.switchTo().frame("mce_0_ifr");

        WebElement textField = driver.findElement(By.xpath("//*[@id='tinymce']"));
        textField.clear();
        textField.sendKeys("Here Goes");

        driver.switchTo().defaultContent();

        WebElement alignCenter = driver.findElement(By.cssSelector("button[title='Align center']"));
        alignCenter.click();
    }

    @Test
    public void alerts() {
        driver.get("https://demoqa.com/alerts");

        WebElement alertButton = driver.findElement(By.id("alertButton"));
        alertButton.click();

        driver.switchTo().alert().accept();
    }

    @AfterTest
    public void afterTests() {
        // Close the browser
        driver.quit();
    }
}

