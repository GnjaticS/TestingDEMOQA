package Tests;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;

public class TestTextBox extends BaseTest {
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
        homePage.clickOnElements();
        sidebarElements.scrollDown();
        Thread.sleep(2000);
        sidebarElements.clickOnTextBox();
    }
    @Test
    public void completeTexBox() throws InterruptedException {
        String fullName = excelReader.getStringData("Sheet1", 1, 0);
        String email = excelReader.getStringData("Sheet1", 1, 1);
        String currentAddress = excelReader.getStringData("Sheet1", 1, 2);
        String permanentAddress = excelReader.getStringData("Sheet1", 1, 3);
        textBox.enterUserName(fullName);
        textBox.enterEmail(email);
        textBox.enterCurrentAddress(currentAddress);
        textBox.enterPermanentAddress(permanentAddress);
        sidebarElements.scrollDown();
        textBox.clickToSubmit();
        //Verify that the valid credentials have been submitted successfully
        Assert.assertTrue(driver.findElement(By.cssSelector(".border.col-md-12.col-sm-12")).isDisplayed());
        WebElement name = driver.findElement(By.id("name"));
        Assert.assertEquals(name.getText(), "Name:"+fullName);
        WebElement checkEmail = driver.findElement(By.id("email"));
        Assert.assertEquals(checkEmail.getText(), "Email:"+email);
        WebElement output = driver.findElement(By.id("output"));
        WebElement currAddress = output.findElement(By.id("currentAddress"));
        Assert.assertEquals(currAddress.getText(), "Current Address :"+currentAddress);
        WebElement permAddress = output.findElement(By.id("permanentAddress"));
        Assert.assertEquals(permAddress.getText(), "Permananet Address :"+permanentAddress);
    }
    @Test
    public void refreshAfterSubmitting(){
        String fullName = excelReader.getStringData("Sheet1", 1, 0);
        String email = excelReader.getStringData("Sheet1", 1, 1);
        String currentAddress = excelReader.getStringData("Sheet1", 1, 2);
        String permanentAddress = excelReader.getStringData("Sheet1", 1, 3);

        textBox.enterUserName(fullName);
        textBox.enterEmail(email);
        textBox.enterCurrentAddress(currentAddress);
        textBox.enterPermanentAddress(permanentAddress);
        sidebarElements.scrollDown();
        textBox.clickToSubmit();
        driver.navigate().refresh();
        //Assert that fields are cleared after the page was refreshed
        Assert.assertEquals(textBox.userName.getText(), "");
        Assert.assertEquals(textBox.email.getText(), "");
        Assert.assertEquals(textBox.currentAddress.getText(), "");


    }
    @AfterMethod
    public void TearDownTabs(){
        //driver.navigate().refresh();
        //driver.navigate().to("https://demoqa.com/");
        // Verify that the user is taken back to the homepage
        //Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/");
    }
}
