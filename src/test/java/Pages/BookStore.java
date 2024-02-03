package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookStore extends BasePage {
    @FindBy(id = "login")
    public WebElement login;

    public void clickOnLogin(){
        scrollIntoView("#login");
        login.click();
    }
}
