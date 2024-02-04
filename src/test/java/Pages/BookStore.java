package Pages;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static Base.BaseTest.driver;

public class BookStore extends BasePage {
    @FindBy(id = "login")
    public WebElement login;
    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[1]/div/div/div[6]/div/ul/li[2]")
    public WebElement bookStore;

    @FindBy(linkText = "Git Pocket Guide")
    public WebElement book1;
    @FindBy(css = ".text-right.fullButton")
    public List<WebElement> addBook;
    @FindBy(id = "userName")
    public WebElement userName;
    @FindBy(id = "password")
    public WebElement password;
    @FindBy(xpath = "/html/body/div/div/div/div[2]/div[1]/div/div/div[6]/div/ul/li[3]/span")
    public WebElement profile;
    @FindBy(css = ".text-right.button.di")
    public WebElement deleteBooks;
    @FindBy(id = "closeSmallModal-ok")
    public WebElement confirmDelete;

    public void clickOnLogin(){
        scrollIntoView("#login");
        login.click();
    }

    public void clickOnBookStore(){
        bookStore.click();
    }

    public void clickOnBook1(){
        book1.click();
    }
    public void clickToAddBook (){
        for (int i=0; i<addBook.size(); i++){
            if(addBook.get(i).getText().equalsIgnoreCase("add to your collection")){
                addBook.get(i).click();
            }
        }
    }
    public void enterUsername(String name){
        userName.click();
        userName.sendKeys(name);
    }
    public void enterPassword(String pass){
        password.click();
        password.sendKeys(pass);
    }
    public void clickOnProfile(){
        profile.click();
    }
    public void clickToDeleteBooks(){
        deleteBooks.click();
    }
    public void clickToConfirmDelete(){
        confirmDelete.click();
    }





}
