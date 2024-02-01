package Tests;

import Base.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestTextBox extends BaseTest {
    @BeforeMethod
    public void pageSetUp() throws InterruptedException {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://demoqa.com/");
        ((JavascriptExecutor) driver).executeScript("document.querySelector('#fixedban').remove()");
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


    }
}
