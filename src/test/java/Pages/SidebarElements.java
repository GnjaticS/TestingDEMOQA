package Pages;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SidebarElements extends BaseTest {
    public SidebarElements(){
        PageFactory.initElements(driver, this);
    }
    public void scrollDown(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 200);");
    }
    @FindBy(css = ".btn.btn-light")
    public List<WebElement> brokenImage;

    @FindBy(css = ".btn.btn-light")
    public List<WebElement> textBox;
    @FindBy(className = "group-header")
    public List<WebElement> elements;
    @FindBy(className = "mgbox")
    public WebElement popUp;

    public void clcikOnBrokenImage(){
        for (int i=0; i<brokenImage.size(); i++){
            if (brokenImage.get(i).getText().equalsIgnoreCase("broken links - images")){
                brokenImage.get(i).click();
            }
        }
    }
    public void clickOnUploadAndDownload(){
        for (var pivot: brokenImage){
            if (pivot.getText().equalsIgnoreCase("Upload and Download")){
                pivot.click();
            }
        }
    }
    public void clickOnTextBox(){
        for (var pivot: textBox){
            if (pivot.getText().equalsIgnoreCase("Text Box")){
                pivot.click();
            }
        }
    }



}
