import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class CommandsTest {
    @Test
    public void getHerokuInfo() throws InterruptedException {
//      Open the Chrome browser
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

//      Navigate to the http://the-internet.herokuapp.com/dynamic_controls
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");

//      Click on Enable button
        WebElement enableButton = driver.findElement(By.xpath("//*[@id=\"input-example\"]/button"));
        enableButton.click();

//      Wait for it...
        Thread.sleep(3000);

//      Check that input field became enabled and text 'It's enabled!' is displayed
        WebElement inputField = driver.findElement(By.xpath("//*[@id=\"input-example\"]/input"));
        WebElement inputText = driver.findElement(By.xpath("//*[@id=\"message\"]"));

        if (inputField.isEnabled() && inputText.getText().equals("It's enabled!")) {
            System.out.println("Input field is enabled and displays the text: It's enabled!");
        } else {
            System.out.println("Input field is not enabled or does not display the expected text");
        }

//      Check that button text has changed from Enable to Disable
        if (enableButton.getText().equals("Disable")) {
            System.out.println("Button text has changed to: Disable");
        } else {
            System.out.println("Button text has not changed");
        }

//      Write 'Bootcamp' in input field and clear it
        inputField.sendKeys("Bootcamp");
        Thread.sleep(1000);
        inputField.clear();

//      Navigate to the http://the-internet.herokuapp.com/drag_and_drop
        driver.get("http://the-internet.herokuapp.com/drag_and_drop");
        Thread.sleep(1000);

//      Check that Y coordinate of column A and column B are the same
        WebElement columnA = driver.findElement(By.xpath("//*[@id=\"column-a\"]"));
        WebElement columnB = driver.findElement(By.xpath("//*[@id=\"column-b\"]"));
        int columnAY = columnA.getLocation().getY();
        int columnBY = columnB.getLocation().getY();
        if (columnAY == columnBY) {
            System.out.println("Y coordinate of column A and column B are the same.");
        } else {
            System.out.println("Y coordinate of column A and column B are not the same.");
        }

//      Quit
        driver.quit();
    }

}
