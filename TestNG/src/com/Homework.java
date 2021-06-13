package com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Homework {

    public class TestNGAssignment {
        public WebDriver driver;

        @BeforeMethod
        public void launchApplicaiton() {
            String url = "http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login";
            System.setProperty("webdriver.chrome.driver", "C:\\webdrivers\\chromedriver_win32\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.get(url);
            driver.manage().window().maximize();
        }

        @Test
        //login into the Application
        public void loginToApplication() throws InterruptedException {
            WebElement username = driver.findElement(By.id("txtUsername"));
            username.sendKeys("Admin");
            WebElement password = driver.findElement(By.id("txtPassword"));
            password.sendKeys("Hum@nhrm123");
            WebElement loginButton = driver.findElement(By.name("Submit"));
            loginButton.click();

            //Click  on Add Employee
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            WebElement clickPIM = driver.findElement(By.id("menu_pim_viewPimModule"));
            clickPIM.click();
            WebElement addButton = driver.findElement(By.id("btnAdd"));
            addButton.click();

            //verify the labels
            WebElement verifyFullName = driver.findElement(By.className("hasTopFieldHelp"));
            Assert.assertTrue(verifyFullName.isDisplayed());
            System.out.println("FullName " + verifyFullName.isDisplayed());
            WebElement verifyEmployeeId = driver.findElement(By.xpath("//label[text()='Employee Id']"));
            Assert.assertTrue(verifyEmployeeId.isDisplayed());
            System.out.println("EmployeedID " + verifyEmployeeId.isDisplayed());
            WebElement verifyPhotograph = driver.findElement(By.xpath("//*[@id=\"frmAddEmp\"]/fieldset/ol/li[3]/label[1]"));
            String expectedPhotoText = "Photograph";
            String actualText = verifyPhotograph.getText();
            Assert.assertEquals(actualText, expectedPhotoText);
            System.out.println("if this text is printed, it means the Photograph text is validated");

            //Provide Employee information
            WebElement firstName = driver.findElement(By.id("firstName"));
            firstName.sendKeys("Syntax");
            WebElement lastName = driver.findElement(By.id("lastName"));
            lastName.sendKeys("Technology");
            WebElement uploadphoto = driver.findElement(By.id("photofile"));
            uploadphoto.sendKeys("C:\\Users\\test.jpg");
            WebElement saveButton = driver.findElement(By.id("btnSave"));
            saveButton.click();
            Thread.sleep(3000);
            driver.quit();
        }
    }
}
