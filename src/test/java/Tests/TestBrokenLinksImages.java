package Tests;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public class TestBrokenLinksImages extends BaseTest {


    @BeforeMethod
    public void pageSetUp() throws InterruptedException {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://demoqa.com/");
        ((JavascriptExecutor) driver).executeScript("document.querySelector('#fixedban').remove()");
        homePage.clickOnElements();
        sidebarElements.scrollDown();
        Thread.sleep(2000);
        sidebarElements.clcikOnBrokenImage();
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
        sidebarElements.scrollDown();
        brokenLinks.clickOnValidLink();
        Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/");
        Assert.assertTrue(driver.findElement(By.cssSelector("img[src='/images/Toolsqa.jpg']")).isDisplayed());
    }
    @Test(priority = 40)
    public void invalidLink() throws InterruptedException {
        // Verify that the clicking on the broken link takes the user to an error page
        Thread.sleep(2000);
        sidebarElements.scrollDown();
        brokenLinks.clickOnBrokenLink();
        Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/status_codes/500");
        Assert.assertTrue(driver.findElement(By.className("row")).isDisplayed());

        //Assert.assertEquals(brokenLinks.findMessage(), "Status Codes"); - maybe delete it
    }

}
