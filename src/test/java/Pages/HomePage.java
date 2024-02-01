package Pages;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends BaseTest {
    public HomePage(){
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = ".card.mt-4.top-card")
        public List<WebElement> elements;


    public void clickOnElements(){
        for (int i=0; i<elements.size(); i++){
            if (elements.get(i).getText().equalsIgnoreCase("Elements")){
                elements.get(i).click();
            }
        }
    }


    public List<WebElement> getBookApp() {
    return driver.findElements(By.cssSelector(".card.mt-4.top-card"));
    }
    public void clickOnBookApp(){
        for (int i=0; i<getBookApp().size(); i++){
            if (getBookApp().get(i).getText().equalsIgnoreCase("book store application")) {
                getBookApp().get(i).click();
            }
        }
    }


}
