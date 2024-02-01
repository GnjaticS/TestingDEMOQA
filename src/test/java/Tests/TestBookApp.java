package Tests;

import Base.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestBookApp extends BaseTest {
    @BeforeMethod
    public void pageSetUp() throws InterruptedException {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://demoqa.com/");
        ((JavascriptExecutor) driver).executeScript("document.querySelector('#fixedban').remove()");
        sidebarElements.scrollDown();
        homePage.clickOnBookApp();
    }
    @Test
    public void BookAppLogin(){
        bookStore.clickOnLogin();
        loginPageBookStore.addCookie();
        loginPageBookStore.addCookie2();
        loginPageBookStore.addCookie3();
        loginPageBookStore.addCookie4();
        driver.navigate().refresh();

    }
}
