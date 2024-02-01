package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookStore extends BaseTest {
    public BookStore(){
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "login")
    public WebElement login;

    public void clickOnLogin(){
        login.click();
    }
}
