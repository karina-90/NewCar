package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AddEmployeePage;
import pages.DashBoardPage;
import pages.EmployeeListPage;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;
import utils.Constants;
import utils.ExcelReading;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmployeeTest extends CommonMethods {

    @Test(groups = "smoke")
    public void addEmployee(){
        //in order to add an Employee, user has to login. create an obj of the class frm where the method is in.
        LoginPage loginpage = new LoginPage();
        loginpage.login(ConfigReader.getPropertyValue("username"), ConfigReader.getPropertyValue("password"));
        //user is now redirected to the DashBoard page on the web Application
        //have create an obj of Dashboard since we need to access elements from that class
        DashBoardPage dash =  new DashBoardPage();
        click(dash.pimOPtion);
        click(dash.addEmployeeButton);
        //have to create an obj of AddEmployeePage since we need to access elements from that class
        AddEmployeePage add = new AddEmployeePage();
        sendText(add.firstName, "test123");
        sendText(add.lastName, "test12345");
        click(add.saveBtn);
    }

    @Test
    public void addMultipleEmployees() throws InterruptedException, IOException {
        //login operation
        LoginPage loginPage =  new LoginPage();//create obj for loginPage to access elements
        loginPage.login(ConfigReader.getPropertyValue("username"), ConfigReader.getPropertyValue("password"));

        //navigating to add employee page
        DashBoardPage dash = new DashBoardPage();//creatw obj of DashBoard page to access elements
        EmployeeListPage emplist = new EmployeeListPage();
        AddEmployeePage addEmployeePagePage =  new AddEmployeePage();
        List<Map<String, String>> newEmployees = ExcelReading.excelIntoListMap(Constants.TESTDATA_FILEPATH, "EmployeeData");

        SoftAssert softAssert = new SoftAssert();

        Iterator<Map<String, String>> it = newEmployees.iterator();//Iterator with two values, map and String w/two value pairs
        //We use Iterator when we have to add multiple employees in a single flow, we go through each row and get the data to add employee
       //itrator loops the values in. Iterator usually needs while loop.
        while(it.hasNext()){//hasNext() goes to the next value if there is one
            click(dash.pimOPtion);
            click(dash.addEmployeeButton);//using the obj of BashBoardPage to access the addEmployeeButton element.
            Map<String, String> mapNewEmployee = it.next();//it has all the stored values
            sendText(AddEmployeePage.firstName, mapNewEmployee.get("FirstName"));
            sendText(AddEmployeePage.middleName, mapNewEmployee.get("MiddleName"));
            sendText(AddEmployeePage.lastName, mapNewEmployee.get("LastName"));
            String employeeIDValue = addEmployeePagePage.employeeId.getAttribute("value");
            sendText(addEmployeePagePage.photograph, mapNewEmployee.get("Photograph"));

            //select checkbox
            if(!addEmployeePagePage.createLoginCheckBox.isSelected()) click(addEmployeePagePage.createLoginCheckBox);

            //add login credentials for user
            sendText(addEmployeePagePage.usernamecreate, mapNewEmployee.get("Username"));//words in green are keys
            sendText(addEmployeePagePage.userpassword, mapNewEmployee.get("Password"));
            sendText(addEmployeePagePage.repassword, mapNewEmployee.get("Password"));
            click(addEmployeePagePage.saveBtn);

            //navigate  to the employee list
            click(dash.pimOPtion);
            click(dash.employeeListOption);

            //enter employee id
            waitForClickability(emplist.idEmployee);
            sendText(emplist.idEmployee, employeeIDValue);
            click(emplist.searchButton);

            List<WebElement> rowData = driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr"));
            for(int i=0; i<rowData.size(); i++){
                System.out.println("I am inside the loop");
                String rowText = rowData.get(i).getText();
                System.out.println(rowText);
                Thread.sleep(10000);
                String expectedEmployeeDetails = employeeIDValue + " " + mapNewEmployee.get("FirstName") + " " + mapNewEmployee.get("MiddleName") + " " + mapNewEmployee.get("LastName");
                softAssert.assertEquals(rowText, expectedEmployeeDetails);
            }
        }
        softAssert.assertAll();

    }
}




