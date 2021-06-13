package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class AddEmployeePage extends CommonMethods {
    //these are the steps to fill out the content on the web application

    @FindBy(id="firstName")
    public static WebElement firstName;

    @FindBy(id="middleName")
    public static WebElement middleName;

    @FindBy(id="lastName")
    public static WebElement lastName;

    @FindBy(id="employeeId")
    public WebElement employeeId;

    @FindBy(id="photofile")
    public WebElement photograph;

    @FindBy(id="btnSave")
    public WebElement saveBtn;

    @FindBy(id="chkLogin")
    public WebElement createLoginCheckBox;

    @FindBy(id="user_name")
    public WebElement usernamecreate;

    @FindBy(id="user_password")
    public WebElement userpassword;

    @FindBy(id="re_password")
    public WebElement repassword;


    public AddEmployeePage(){
        PageFactory.initElements(driver, this);
    }
//we need page factory to initialize all the elements
}

