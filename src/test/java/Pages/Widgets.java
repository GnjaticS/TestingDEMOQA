package Pages;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.Keys.ENTER;

public class Widgets extends BaseTest {
    public Widgets(){
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "/html/body/div/div/div/div[2]/div/div[4]/div/div[3]")
    public WebElement widgets;
    @FindBy(css = ".btn.btn-light")
    public List<WebElement> autoComplete;
    @FindBy(css = ".auto-complete__value-container.auto-complete__value-container--is-multi.css-1hwfws3")
    public WebElement colors;
    @FindBy(css = ".auto-complete__input>input")
    public WebElement enterColor;
    @FindBy(id = "autoCompleteSingleInput")
    public WebElement singleColor;
    @FindBy(id = "autoCompleteMultiple")
    public List<WebElement> multipleColors;

    public void clicksToEnterMultipleColors(){
        for(var color: multipleColors){
            if (color.getText().equalsIgnoreCase("Type multiple color names")){
                color.click();
            }
        }
    }
    public void enterColors(String color){

        wait.until(ExpectedConditions.elementToBeClickable
                (driver.findElement(By.id("autoCompleteMultipleContainer"))));
        enterColor.click();
        enterColor.sendKeys(color);
        enterColor.sendKeys(ENTER);
    }
    public void enterSingleColor(String single){
        singleColor.clear();
        singleColor.click();
        singleColor.sendKeys(single);
        singleColor.sendKeys(ENTER);

    }

    public void clickWidgets(){
        widgets.click();
    }
    public void clickAutoComplete(){
        for(var auto: autoComplete){
            if(auto.getText().equalsIgnoreCase("Auto Complete")){
                auto.click();
            }
        }
    }
}
