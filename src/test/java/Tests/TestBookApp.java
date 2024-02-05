package Tests;

import Base.BaseTest;
import Pages.BookStore;
import Pages.HomePage;
import Pages.LoginPageBookStore;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

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
    @Test(priority = 25)
    public void BookAppLoginWithCookies(){
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
    @Test(priority = 15)
    public void AddABookToCollection() throws InterruptedException {
        bookStore.clickOnLogin();
        bookStore.enterUsername("golmanbranić");
        bookStore.enterPassword("Aa12345678@");
        bookStore.clickOnLogin();
        bookStore.clickOnBook1();
        bookStore.scrollDown();
        bookStore.clickToAddBook();
        Thread.sleep(1000);
        // Verify that the book is added to the collection
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "Book added to your collection.");
        try {
            alert = driver.switchTo().alert();
            alert.accept();
        } catch (Exception e){

        }

    }
    @Test(priority = 20)
    public void AddedBookIsInTheCollection() throws InterruptedException {
        bookStore.clickOnLogin();
        bookStore.enterUsername("golmanbranić");
        bookStore.enterPassword("Aa12345678@");
        bookStore.clickOnLogin();
        bookStore.clickOnBook1();
        bookStore.scrollDown();
        bookStore.clickToAddBook();
        Thread.sleep(1000);
        // Verify that the same book is already added to the collection
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "Book already present in the your collection!");
        try {
            alert = driver.switchTo().alert();
            alert.accept();
        } catch (Exception e){

        }
    }
    @Test(priority = 5)
    public void DeleteAllBooksFromTheCollection() throws InterruptedException {
        bookStore.clickOnLogin();
        bookStore.enterUsername("golmanbranić");
        bookStore.enterPassword("Aa12345678@");
        bookStore.clickOnLogin();
        Thread.sleep(2000);
        bookStore.scrollDown();
        bookStore.clickOnProfile();
        bookStore.scrollDown();
        bookStore.clickToDeleteBooks();
        bookStore.clickToConfirmDelete();
        // Verify that all book are deleted
        Thread.sleep(2000);
        Alert alert = driver.switchTo().alert();

        String alertText = alert.getText();
        boolean isEqual = false;
        if (alertText.equalsIgnoreCase("No books available in your's collection!")
                || alertText.equalsIgnoreCase("All Books deleted.")){
            Assert.assertFalse(isEqual);
        } else {
            Assert.assertTrue(isEqual);
        }

        try {
            alert = driver.switchTo().alert();
            alert.accept();
        } catch (Exception e){

        }

    }

    @AfterMethod
    public void TearDownTabs(){
        driver.manage().deleteAllCookies();
        driver.close();
        SwitchToFirstTab();
    }
}
