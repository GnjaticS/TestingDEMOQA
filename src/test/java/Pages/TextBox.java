package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TextBox extends BasePage {
    @FindBy(id = "userName")
    public WebElement userName;
    @FindBy(id = "userEmail")
    public WebElement email;
    @FindBy(id = "currentAddress")
    public WebElement currentAddress;
    @FindBy(id = "permanentAddress")
    public WebElement permanentAddress;
    @FindBy(id = "submit")
    public WebElement submit;

    public void enterUserName(String info){
        userName.clear();
        userName.click();
        userName.sendKeys(info);
    }
    public void enterEmail(String mail){
        email.clear();
        email.click();
        email.sendKeys(mail);
    }
    public void enterCurrentAddress(String address){
        currentAddress.clear();
        currentAddress.click();
        currentAddress.sendKeys(address);
    }
    public void enterPermanentAddress(String permAddress){
        permanentAddress.clear();
        permanentAddress.click();
        permanentAddress.sendKeys(permAddress);
    }
    public void clickToSubmit(){
        scrollIntoView("#submit");
        submit.click();
    }
}
