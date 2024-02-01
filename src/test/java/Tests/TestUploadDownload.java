package Tests;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public class TestUploadDownload extends BaseTest {
    @BeforeMethod
    public void pageSetUp() throws InterruptedException {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://demoqa.com/");
        ((JavascriptExecutor) driver).executeScript("document.querySelector('#fixedban').remove()");
        homePage.clickOnElements();
        sidebarElements.scrollDown();
        sidebarElements.clickOnUploadAndDownload();
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



}

