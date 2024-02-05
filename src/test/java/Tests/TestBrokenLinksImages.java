package Tests;

import Base.BaseTest;
import Pages.BrokenLinks;
import Pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;

public class TestBrokenLinksImages extends BaseTest {
    public static HomePage homePage;
    public static BrokenLinks brokenLinks;

    @BeforeClass
    public static void setPages() {
        homePage = new HomePage();
        brokenLinks = new BrokenLinks();
    }

    @BeforeMethod
    public void pageSetUp() throws InterruptedException {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        SwitchToNewTab();
        homePage.clickOnElements();
        homePage.scrollDown();
        Thread.sleep(2000);
        homePage.clickOnBrokenImage();
    }

    @Test(priority = 20)
    public void brokenImage() throws InterruptedException {
        brokenLinks.scrollDown();
        // Verify that the image is not displayed using the naturalWidth as an attribute
        Assert.assertEquals(brokenLinks.image.getAttribute("naturalWidth"), "0");
    }

    @Test(priority = 30)
    public void validLink() throws InterruptedException {
        // Verify that clicking on valid link takes the user back to the homepage
        Thread.sleep(2000);
        brokenLinks.scrollDown();
        brokenLinks.clickOnValidLink();
        Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/");
        Assert.assertTrue(driver.findElement(By.cssSelector("img[src='/images/Toolsqa.jpg']")).isDisplayed());
    }

    @Test(priority = 40)
    public void invalidLink() throws InterruptedException {
        // Verify that the clicking on the broken link takes the user to an error page
        Thread.sleep(2000);
        brokenLinks.scrollDown();
        brokenLinks.clickOnBrokenLink();
        Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/status_codes/500");
        Assert.assertTrue(driver.findElement(By.className("row")).isDisplayed());

        //Assert.assertEquals(brokenLinks.findMessage(), "Status Codes"); - maybe delete it
    }
    @Test
    public void validImage() throws InterruptedException {
        Thread.sleep(2000);
        WebElement parent = driver.findElement(By.tagName("img"));
        Assert.assertTrue(parent.findElement(By.xpath("/html/body/div/div/div/div[2]/div[2]/div[2]/img[1]")).isDisplayed());
    }

    @AfterMethod
    public void TearDownTabs(){
        driver.close();
        SwitchToFirstTab();
    }
}
