package Tests;

import Base.BaseTest;
import Pages.BrokenLinks;
import Pages.HomePage;
import Pages.SidebarElements;
import Pages.UploadDownload;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public class Tests extends BaseTest {


    @BeforeMethod
    public void pageSetUp() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://demoqa.com/");
        homePage.clickOnElements();
        sidebarElements.scrollDown();
        Thread.sleep(2000);
        sidebarElements.clcikOnBrokenImage();
    }
    @Test
    public void brokenImage() throws InterruptedException {

        brokenLinks.scrollDown();
        // Verify that the image is not displayed using the naturalWidth as an attribute
        Assert.assertEquals(brokenLinks.image.getAttribute("naturalWidth"), "0");
    }
    @Test
    public void validLink(){
        // Verify that clicking on valid link takes the user back to the homepage
        brokenLinks.clickOnValidLink();
        Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/");
        Assert.assertTrue(driver.findElement(By.cssSelector("img[src='/images/Toolsqa.jpg']")).isDisplayed());
    }
    @Test
    public void invalidLink() throws InterruptedException {
        // Verify that the clicking on the broken link takes the user to an error page
        Thread.sleep(2000);
        brokenLinks.clickOnBrokenLink();
        Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/status_codes/500");
        Assert.assertTrue(driver.findElement(By.className("row")).isDisplayed());

        //Assert.assertEquals(brokenLinks.findMessage(), "Status Codes"); - maybe delete it
    }
    @Test
    public void UploadAndDownload() throws InterruptedException {
        homePage.clickOnElements();
        sidebarElements.scrollDown();
        sidebarElements.clickOnUploadAndDownload();
        uploadDownload.clickToDownload();
        Thread.sleep(4000);
        String location = "/Users/stefang/Downloads/sampleFile.jpeg";
        boolean fileExists = doesFileExists(location);
        Assert.assertTrue(fileExists, "Downloaded file does not exist");
    }
    public boolean doesFileExists(String filePath){
        Path path = Paths.get(filePath);
        return Files.exists(path);
    }
}
