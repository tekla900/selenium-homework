import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WaitsTest {

    private WebDriver driver;

    @BeforeTest
    public void beforeTests() {
//      Open the Chrome browser
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void waitsTest() {
        driver.get("https://demoqa.com/progress-bar");

//      Click to 'Start' button
        WebElement startButton = driver.findElement(By.xpath("//*[@id='startStopButton']"));
        startButton.click();

//      Wait until progress bar reach 100 %
        WebElement progressBar = driver.findElement(By.xpath("//div[@id='progressBar']//div[@role='progressbar']"));

        WebDriverWait wait=new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.textToBePresentInElement(progressBar, "100%"));

//      print '100%'
        System.out.println(progressBar.getText());
    }

    @AfterTest
    public void afterTests() {
        // Close the browser
        driver.quit();
    }
}
