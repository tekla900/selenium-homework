import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;

public class WebFormsTest {
    private WebDriver driver;

    @BeforeTest
    public void beforeTests() {
//      Open the Chrome browser
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://webdriveruniversity.com/Dropdown-Checkboxes-RadioButtons/index.html");
    }

    @Test
    public void dropDownTest() {
//      Choose programming language from dropdown and ensure that it was selected
        WebElement dropDown = driver.findElement(By.xpath("//*[@id='dropdowm-menu-1']"));
        Select select = new Select(dropDown);

        select.selectByValue("python");
        System.out.println("is selected option python: " + Objects.equals(select.getFirstSelectedOption().getText(), "Python"));
    }

    @Test
    public void checkboxesTest() {
//      Click on all unselected checkboxes
        List<WebElement> checkboxes = driver.findElements(By.xpath("//*[@id='checkboxes']//input[@type='checkbox']"));
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }
    }

    @Test
    public void radioButtonTest() {
//      Click on 'Yellow' radio button
        WebElement yellowRadioButton = driver.findElement(By.xpath("//*[@id='radio-buttons']/input[@value='yellow']"));
        yellowRadioButton.click();
    }

    @Test
    public void selectedAndDisabled() {
//      Check that 'Orange' option in dropdown is disabled
        Select disabledDropdown = new Select(driver.findElement(By.id("fruit-selects")));
        WebElement orangeOption = null;

        for (WebElement option : disabledDropdown.getOptions()) {
            if (option.getText().equals("Orange")) {
                orangeOption = option;
                break;
            }
        }

        System.out.println("is orange disabled: " + !orangeOption.isEnabled());
    }

    @AfterTest
    public void afterTests() {
        // Close the browser
        driver.quit();
    }
}
