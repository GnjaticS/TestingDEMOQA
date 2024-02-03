package Pages;

import Base.BaseTest;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.support.PageFactory;

public class LoginPageBookStore extends BasePage {

    public Cookie kuki1;
    public Cookie kuki2;
    public Cookie kuki3;
    public Cookie kuki4;


    public Cookie getKuki1() {
        return new Cookie("userName", "golmanbrani%C4%87");
    }
    public void addCookie(){
        BaseTest.driver.manage().addCookie(getKuki1());
    }

    public Cookie getKuki2() {
        return new Cookie("userID", "f319ed0c-8185-4370-8295-3fb09fc8fe34");
    }
    public void addCookie2(){
        BaseTest.driver.manage().addCookie(getKuki2());
    }

    public Cookie getKuki3() {
        return new Cookie("expires", "2024-02-08T20%3A39%3A53.854Z");
    }
    public void addCookie3(){
        BaseTest.driver.manage().addCookie(getKuki3());
    }

    public Cookie getKuki4() {
        return new Cookie("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6ImdvbG1hbmJyYW5pxIciLCJwYXNzd29yZCI6IkFhMTIzNDU2NzhAIiwiaWF0IjoxNzA2MTAzODUzfQ.tHZKP_RIbOcxXUf45UVBSZPnsR0ssnWOktt8N1Flej4");
    }
    public void addCookie4(){
        BaseTest.driver.manage().addCookie(getKuki4());
    }


}
