package Pages;

import Base.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BrokenLinks extends BaseTest {
    public BrokenLinks(){
        PageFactory.initElements(driver, this);
    }

    public void scrollDown(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 300);");
    }
    @FindBy(css = "img[src='/images/Toolsqa_1.jpg']")
    public WebElement image;

    @FindBy(linkText = "Click Here for Valid Link")
    public WebElement validlink;

    public void clickOnValidLink(){
        validlink.click();
    }
    @FindBy(linkText = "Click Here for Broken Link")
    public WebElement brokenLink;
    @FindBy(xpath = "/html/body/div[2]/div")
    public List<WebElement> content;

    public void clickOnBrokenLink(){
        brokenLink.click();
    }

    public String findMessage(){
        String message = "";
        for (WebElement webElement : content) {
            if (webElement.getText().equalsIgnoreCase("Status Codes")) {
                message = webElement.getText();
            }
        }
        return message;
    }

}
