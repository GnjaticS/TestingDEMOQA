package Tests;

import Base.BaseTest;
import Pages.HomePage;
import Pages.UploadDownload;
import Pages.Widgets;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;

public class TestWidgets extends BaseTest {
    public static HomePage homePage;
    public static Widgets widgets;

    @BeforeClass
    public static void setPages() {
        homePage = new HomePage();
        widgets = new Widgets();
    }

    @BeforeMethod
    public void pageSetUp() throws InterruptedException {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        SwitchToNewTab();
        homePage.scrollDown();

        widgets.clickWidgets();
        widgets.scrollDown();
        widgets.clickAutoComplete();
        Thread.sleep(1000);
        widgets.clicksToEnterMultipleColors();


    }
    @Test
    public void inputColors() throws InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        widgets.enterColors("blue");
        Assert.assertEquals("Blue", driver.findElement(By.cssSelector(".css-1rhbuit-multiValue.auto-complete__multi-value")).getText());
        widgets.removeColor.click();
        widgets.enterColors("red");
        Assert.assertEquals("Red", driver.findElement(By.cssSelector(".css-1rhbuit-multiValue.auto-complete__multi-value")).getText());
        widgets.removeColor.click();
        widgets.enterColors("yellow");
        Assert.assertEquals("Yellow", driver.findElement(By.cssSelector(".css-1rhbuit-multiValue.auto-complete__multi-value")).getText());
        widgets.removeColor.click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".auto-complete__input>input")).getText().isEmpty());
    }
    @Test void inputSingleColor() throws InterruptedException {

        widgets.enterSingleColor("Purple");
        Thread.sleep(2000);
        //Verify that the color name is input in the field
        Assert.assertEquals(driver.findElement(By.cssSelector(".auto-complete__single-value.css-1uccc91-singleValue")).getText(), "Purple");
    }
    @AfterMethod
    public void TearDownTabs(){
        driver.close();
        SwitchToFirstTab();
    }
}
