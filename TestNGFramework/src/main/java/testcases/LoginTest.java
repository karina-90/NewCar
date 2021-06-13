package testcases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DashBoardPage;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;

public class LoginTest extends CommonMethods {

    @Test(groups = "smoke")
    public void adminLogin(){

        //login to hrms application
        LoginPage loginpage = new LoginPage();
        sendText(loginpage.usernamebox, ConfigReader.getPropertyValue("username"));
        sendText(loginpage.passwordbox, ConfigReader.getPropertyValue("password"));
        click(loginpage.loginBtn);

        //validation
        //assertion

        DashBoardPage dashboard = new DashBoardPage();
        Assert.assertTrue(dashboard.welcomemessage.isDisplayed(), "welcome message is not displayed");
    }
    //invalid login
    @Test(dataProvider = "invalidData", groups = "sanity")
    public void invalidLoginErrorMessageValidation(String username, String password, String message){
        //create an obj of LoginPage to acccess elements in that class
        LoginPage loginPage = new LoginPage();
        sendText(loginPage.usernamebox, username);
        sendText(loginPage.passwordbox, password);
        click(loginPage.loginBtn);

        //verify error message by assertion. The message needs to be in a String
        String actualError = loginPage.errormessage.getText();
        //all the values from the data provider will go through this assertequals
        Assert.assertEquals(actualError, message, "Error message is not matched");

    }

    //create a data provider to pass the invalid cardentials to the above @Test
    @DataProvider
    public Object[][] invalidData() {
        Object[][] data = {
                {"James", "123!", "Invalid credentials"},
                {"Admin1", "Syntax123!", "Invalid credentials"},
                {"James", "", "Password cannot be empty"},
                {"", "Syntax123!", "Username cannot be empty"}
        };
        return data;
    }
}