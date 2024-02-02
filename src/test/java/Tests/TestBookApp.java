package Tests;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;

public class TestBookApp extends BaseTest {
    @BeforeMethod
    public void pageSetUp() throws InterruptedException {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("var newTab = window.open(); newTab.location.href = 'https://demoqa.com/';");
        ArrayList<String> listaTabova = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(listaTabova.get(listaTabova.size() - 1));
        driver.get("https://demoqa.com/");
        js.executeScript("document.querySelector('#fixedban').remove()");
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
        //Verify that the user is logged in
        //locate the logout button (make sure that it's present)
        Assert.assertTrue(driver.findElement(By.cssSelector(".btn.btn-primary")).isDisplayed());
        // check the logged in URL
        Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/login");
    }
    @AfterMethod
    public void TearDownTabs(){
        driver.navigate().refresh();
        driver.navigate().to("https://demoqa.com/");
        // Verify that the user is taken back to the homepage
        Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/");
    }
}
