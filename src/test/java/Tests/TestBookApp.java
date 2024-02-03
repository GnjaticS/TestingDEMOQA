package Tests;

import Base.BaseTest;
import Pages.BookStore;
import Pages.HomePage;
import Pages.LoginPageBookStore;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;

public class TestBookApp extends BaseTest {
    public static HomePage homePage;
    public static BookStore bookStore;
    public static LoginPageBookStore loginPageBookStore;

    @BeforeClass
    public static void setPages() {
        homePage = new HomePage();
        bookStore = new BookStore();
        loginPageBookStore = new LoginPageBookStore();
    }

    @BeforeMethod
    public void pageSetUp() throws InterruptedException {
        System.out.println("method");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        SwitchToNewTab();
        homePage.scrollDown();
        homePage.clickOnBookApp();
    }
    @Test(priority = 20)
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
    @Test(priority = 10)
    public void LogoutByDeletingCookies() throws InterruptedException {
        bookStore.clickOnLogin();
        loginPageBookStore.addCookie();
        loginPageBookStore.addCookie2();
        loginPageBookStore.addCookie3();
        loginPageBookStore.addCookie4();
        driver.navigate().refresh();
        driver.navigate().refresh();
        driver.navigate().back();
        Thread.sleep(2000);
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        //Verify that the user is logged out
        //Login button is displayed
        Assert.assertTrue(driver.findElement(By.id("login")).isDisplayed());
        // Verify the matching username
        Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/books");
    }
    @AfterMethod
    public void TearDownTabs(){
        // driver.close();
        SwitchToFirstTab();
    }
}
