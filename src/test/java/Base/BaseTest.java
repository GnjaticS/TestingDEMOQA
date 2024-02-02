package Base;

import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public HomePage homePage;
    public SidebarElements sidebarElements;
    public BrokenLinks brokenLinks;
    public UploadDownload uploadDownload;
    public Widgets widgets;
    public TextBox textBox;
    public ExcelReader excelReader;
    public BookStore bookStore;
    public LoginPageBookStore loginPageBookStore;
    @BeforeClass
    public void setUp() throws IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        homePage = new HomePage();
        sidebarElements = new SidebarElements();
        brokenLinks = new BrokenLinks();
        uploadDownload = new UploadDownload();
        widgets = new Widgets();
        textBox = new TextBox();
        excelReader = new ExcelReader("/Users/stefang/Desktop/TextBox.xlsx");
        bookStore = new BookStore();
        loginPageBookStore = new LoginPageBookStore();

    }

    @AfterClass
    public void finalTearDown(){
        //driver.manage().deleteAllCookies();
        //driver.quit();
    }
}
