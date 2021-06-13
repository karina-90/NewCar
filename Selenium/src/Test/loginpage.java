package Test;

public class loginpage {
    package Test;
import TestBase.BaseClass;
import pages.LoginPage;
    public class LoginTest {
        public static void main(String[] args){
            BaseClass.setupWithSpecificUrl("https://www.saucedemo.com/");
            LoginPage login=new LoginPage();
            login.userName.sendKeys("standard_user");
            login.Password.sendKeys("secret_sauce");
            login.loginBtn.click();
        }
    }
}
