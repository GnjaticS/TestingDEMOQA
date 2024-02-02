package Tests;

import Base.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
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
    }
    @AfterMethod
    public void TearDownTabs(){
        driver.navigate().refresh();
        driver.navigate().to("https://demoqa.com/");
        // Verify that the user is taken back to the homepage
        Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/");
    }
}
