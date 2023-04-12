import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;


public class WebElementsTest {
    private WebDriver driver;

    @BeforeTest
    public void beforeTests() {
//      Open the Chrome browser
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void herokuTests() throws InterruptedException {
        //  Navigate to the http://the-internet.herokuapp.com/add_remove_elements/
        driver.get("http://the-internet.herokuapp.com/add_remove_elements/");
        WebElement addElementButton = driver.findElement(By.xpath("//div[@id='content']/div[@class='example']/button"));

        addElementButton.click();
        addElementButton.click();
        addElementButton.click();

        WebElement lastDeleteButton = driver.findElement(By.xpath("//*[@id='elements']/button[last()]"));
        List<WebElement> lastDeleteButtons = driver.findElements(By.cssSelector("[class^='added']"));
        WebElement lastManualDeleteButton = driver.findElement(By.xpath("//button[contains(@class, 'manually') and text()='Delete'][last()]"));

        System.out.println(lastDeleteButton);
        System.out.println("By css selector: " + lastDeleteButtons.get(lastDeleteButtons.size() - 1));
        System.out.println("Last manually added delete button: " + lastManualDeleteButton);

        // Navigate to the http://the-internet.herokuapp.com/challenging_dom
        driver.get("http://the-internet.herokuapp.com/challenging_dom");

        WebElement loremElement = driver.findElement(By.xpath("//td[text()='Apeirian9']/preceding-sibling::td[1]"));
        System.out.println("lorem: " + loremElement.getText());

        WebElement nextElement = loremElement.findElement(By.xpath("following-sibling::*[2]"));
        System.out.println("next element: " + nextElement.getText());

    }

    @AfterTest
    public void afterTests() {
        // Close the browser
        driver.quit();
    }
}
