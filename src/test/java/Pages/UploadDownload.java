package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UploadDownload extends BaseTest {
    public UploadDownload(){
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "downloadButton")
    public WebElement download;
    @FindBy(id = "uploadFile")
    public WebElement upload;
    public void clickToDownload(){
        download.click();
    }
    public void clickToUpload(){
        upload.click();
    }
}

