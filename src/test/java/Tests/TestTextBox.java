package Tests;

import Base.BaseTest;
import Base.ExcelReader;
import Pages.HomePage;
import Pages.TextBox;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

public class TestTextBox extends BaseTest {
    public static HomePage homePage;
    public static TextBox textBox;
    public static ExcelReader excelReader;

    boolean last_failed;

    @BeforeClass
    public static void setPages() throws IOException {
        excelReader = new ExcelReader("/Users/stefang/Desktop/TextBox.xlsx");
        homePage = new HomePage();
        textBox = new TextBox();
    }

    @BeforeMethod
    public void pageSetUp() throws InterruptedException {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        SwitchToNewTab();
        homePage.clickOnElements();
        homePage.scrollDown();
        Thread.sleep(2000);
        homePage.clickOnTextBox();
        last_failed = true;
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
        textBox.scrollDown();
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

        last_failed = false;
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
        textBox.scrollDown();
        textBox.clickToSubmit();
        driver.navigate().refresh();
        //Assert that fields are cleared after the page was refreshed
        Assert.assertEquals(textBox.userName.getText(), "");
        Assert.assertEquals(textBox.email.getText(), "");
        Assert.assertEquals(textBox.currentAddress.getText(), "");

        last_failed = false;
    }
    @Test
    public void invalidPasswordProblem(){
        String fullName = excelReader.getStringData("Sheet1", 1, 0);
        String email = excelReader.getStringData("Sheet1", 1, 1);
        String currentAddress = excelReader.getStringData("Sheet1", 1, 2);
        String permanentAddress = excelReader.getStringData("Sheet1", 1, 3);

        textBox.enterUserName(fullName);
        textBox.enterEmail("stefan.gnjatic.yahoo.com");
        textBox.enterCurrentAddress(currentAddress);
        textBox.enterPermanentAddress(permanentAddress);
        textBox.scrollDown();
        textBox.clickToSubmit();
        //Verify that the email input field throws an error message
        WebElement inputEmail = driver.findElement(By.id("userEmail"));
        String classAttributeValue = inputEmail.getAttribute("class");
        Assert.assertTrue(classAttributeValue.contains("field-error"));

        boolean submitFieldIsDisplayed = false;
        try {
            driver.findElement(By.id("name"));
            Assert.assertTrue(submitFieldIsDisplayed);
        } catch(NoSuchElementException e){
        }

        Assert.assertFalse(submitFieldIsDisplayed);
        last_failed = false;
    }
    @Test
    public void UsercanModifyCredentialsAfterSubmitting(){
        String fullName = excelReader.getStringData("Sheet1", 1, 0);
        String email = excelReader.getStringData("Sheet1", 1, 1);
        String currentAddress = excelReader.getStringData("Sheet1", 1, 2);
        String permanentAddress = excelReader.getStringData("Sheet1", 1, 3);

        textBox.enterUserName(fullName);
        textBox.enterEmail(email);
        textBox.enterCurrentAddress(currentAddress);
        textBox.enterPermanentAddress(permanentAddress);
        textBox.scrollDown();
        textBox.clickToSubmit();

        textBox.enterUserName("Stefan");
        textBox.clickToSubmit();

        // Verify that the name has changed in the submit box
        WebElement name = driver.findElement(By.id("name"));
        Assert.assertEquals(name.getText(), "Name:Stefan");

        textBox.enterEmail("sgnjatic@gmail.com");
        textBox.clickToSubmit();
        // Verify that the name has changed in the submit box
        WebElement checkEmail = driver.findElement(By.id("email"));
        Assert.assertEquals(checkEmail.getText(), "Email:sgnjatic@gmail.com");
        last_failed = false;
    }

    @AfterMethod
    public void TearDownTabs(){
        if (!last_failed) {
            driver.close();
            SwitchToFirstTab();
        }
    }
}
