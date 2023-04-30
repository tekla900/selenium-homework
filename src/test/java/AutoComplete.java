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
import java.util.List;

public class AutoComplete {

    private WebDriver driver;

    @BeforeTest
    public void beforeTests() {
//      Open the Chrome browser
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void automationSuggestions() {
        driver.get("https://www.google.com/");

        WebElement searchBar = driver.findElement(By.id("APjFqb"));
        searchBar.sendKeys("Automation");

        WebDriverWait wait=new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul[role='listbox']")));

        List<WebElement> suggestions = driver.findElements(By.xpath("//*[@id=\"Alh6id\"]/div[1]/div/ul/li"));

        WebElement lastSuggestion = suggestions.get(suggestions.size()-1);

        lastSuggestion.click();
    }

    @AfterTest
    public void afterTests() {
        // Close the browser
        driver.quit();
    }
}
