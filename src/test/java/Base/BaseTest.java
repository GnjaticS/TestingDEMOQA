package Base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeClass
    public static void setUp() {
        System.out.println("Setup driver");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public static void finalTearDown() {}

    public void SwitchToNewTab() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("var newTab = window.open();");
        driver.switchTo().window(driver.getWindowHandles()
                .stream()
                .reduce((first, second) -> second)
                .orElse(null)
        );
        driver.get("https://demoqa.com/");
        js.executeScript("document.querySelector('#fixedban').remove()");

    }

    public void SwitchToFirstTab() {
        driver.switchTo().window(
                driver.getWindowHandles()
                        .stream()
                        .reduce((first, second) -> first)
                        .orElse(null)
        );
    }
}
