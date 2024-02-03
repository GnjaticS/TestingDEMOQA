package Tests;

import Base.BaseTest;
import Pages.BrokenLinks;
import Pages.HomePage;
import Pages.UploadDownload;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;

public class TestUploadDownload extends BaseTest {
    public static HomePage homePage;
    public static UploadDownload uploadDownload;

    @BeforeClass
    public static void setPages() {
        homePage = new HomePage();
        uploadDownload = new UploadDownload();
    }

    @BeforeMethod
    public void pageSetUp() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        SwitchToNewTab();
        homePage.clickOnElements();
        homePage.scrollDown();
        homePage.clickOnUploadAndDownload();
    }
    @Test
    public void Download() throws InterruptedException {
        uploadDownload.clickToDownload();
        Thread.sleep(3000);
        String location = "/Users/stefang/Downloads/sampleFile.jpeg";
        boolean fileExists = doesFileExists(location);
        Assert.assertTrue(fileExists, "Downloaded file does not exist");
    }
    public boolean doesFileExists(String filePath){
        Path path = Paths.get(filePath);
        return Files.exists(path);
    }
    @Test
    public void Upload(){
        String location = "/Users/stefang/Downloads/sampleFile.jpeg";

        //Assert that the uploadedFilePath does not exist ie that the picture is not yet uploaded
        boolean notUploaded = true;
        try {
            driver.findElement(By.id("uploadedFilePath"));
            notUploaded = false;
        } catch (
                NoSuchElementException e){
        }
        Assert.assertTrue(notUploaded);

        uploadDownload.upload.sendKeys(location);
        //Verify that the file is uploaded
        Assert.assertTrue(driver.findElement(By.id("uploadedFilePath")).isDisplayed());
    }
    @AfterMethod
    public void TearDownTabs(){
        driver.close();
        SwitchToFirstTab();
    }
}

