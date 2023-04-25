import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Exceptions {
    private WebDriver driver;

    @BeforeTest
    public void beforeTests() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        driver = new ChromeDriver(options);
    }

    @Test
    public void alerts() {
        driver.get("https://demoqa.com/alerts");

        WebElement button = driver.findElement(By.id("timerAlertButton"));
        button.click();

        try {
            WebDriverWait wait = new WebDriverWait(driver, 2);
            wait.until(ExpectedConditions.alertIsPresent());
        } catch (TimeoutException e) {
            System.out.println("TimeoutException");
        }

        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException e) {
            System.out.println("NoAlertPresentException");
        }

        try {
            driver.navigate().refresh();
            button.click();
        } catch (StaleElementReferenceException e) {
            System.out.println("StaleElementReferenceException");
        }
    }

    @AfterTest
    public void afterTests() {
        // Close the browser
        driver.quit();
    }
}
