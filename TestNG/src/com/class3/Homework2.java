package com.class3;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.seleniumhq.jetty9.util.IO;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Homework2 {
    public class HomeWorkTask1 {
        WebDriver driver;
        private IO FileUtils;

        @BeforeMethod(alwaysRun = true)
        public void openBrowserAndLaunchApp() {
            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
            driver = new ChromeDriver();
            driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }

        @Test(groups = "Create Employee")
        public void logIn() {
            WebElement userName = driver.findElement(By.id("txtUsername"));
            userName.sendKeys("Admin");
            WebElement password = driver.findElement(By.id("txtPassword"));
            password.sendKeys("Hum@nhrm123");
            WebElement loginBtn = driver.findElement(By.id("btnLogin"));
            loginBtn.click();
        }

        @DataProvider
        public Object[][] employeeCreation() {
            Object[][] data = {{"Mimosa", "Dunkan", "Mimosa", "Syntax123!", "Syntax123!"},
                    {"Kendall", "Jackson", "Kendall", "Syntax123!", "Syntax123!"},
                    {"Tommy", "Tomson", "Tommy", "Syntax123!", "Syntax123!"},
                    {"Veronica", "Michelle", "Veronica", "Syntax123!", "Syntax123!"},
                    {"Starlader", "Loungren", "Starlader", "Syntax123!", "Syntax123!"}};
            return data;
        }

        @Test(dataProvider = "employeeCreation", groups = "Create Employee")
        public void addEmployee(String firstName, String lastName, String userName, String password, String confirmPass) throws InterruptedException {
            logIn();
            WebElement PimBtn = driver.findElement(By.id("menu_pim_viewPimModule"));
            Thread.sleep(2000);
            PimBtn.click();
            WebElement addEmployeeBtn = driver.findElement(By.id("menu_pim_addEmployee"));
            Thread.sleep(2000);
            addEmployeeBtn.click();
            WebElement firstNameField = driver.findElement(By.id("firstName"));
            firstNameField.sendKeys(firstName);
            WebElement lastNameField = driver.findElement(By.id("lastName"));
            lastNameField.sendKeys(lastName);
            WebElement loginDetailsCheckBox = driver.findElement(By.id("chkLogin"));
            loginDetailsCheckBox.click();
            WebElement userNameField = driver.findElement(By.id("user_name"));
            userNameField.sendKeys(userName);
            WebElement passwordField = driver.findElement(By.id("user_password"));
            passwordField.sendKeys(password);
            WebElement confirmPassword = driver.findElement(By.id("re_password"));
            confirmPassword.sendKeys(confirmPass);
            WebElement saveBtn = driver.findElement(By.id("btnSave"));
            saveBtn.click();
        }

        @DataProvider
        public Object[][] verificationEmployee() {
            Object[][] data = {{"Mimosa", "Syntax123!"},
                    {"Kendall", "Syntax123!"},
                    {"Tommy", "Syntax123!"},
                    {"Veronica", "Syntax123!"},
                    {"Starlader", "Syntax123!"}};
            return data;
        }

        @Test(dataProvider = "verificationEmployee", groups = "Verification Employee")
        public void logInEmployee(String userName, String password) throws InterruptedException {
            WebElement user = driver.findElement(By.id("txtUsername"));
            user.sendKeys(userName);
            WebElement pass = driver.findElement(By.id("txtPassword"));
            pass.sendKeys(password);
            WebElement loginBtn = driver.findElement(By.id("btnLogin"));
            loginBtn.click();
            WebElement welcomeMessage = driver.findElement(By.id("welcome"));
            String welcomeName = welcomeMessage.getText();
            String actualName = "Welcome " + userName + "";
            SoftAssert softAssert = new SoftAssert();
            softAssert.assertEquals(welcomeName, actualName);
            System.out.println(welcomeName);
            softAssert.assertAll();
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            TakesScreenshot ts = (TakesScreenshot) driver;
            File employee = ts.getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(employee, new File("screenshots/AddedEmployee/" + userName + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @AfterMethod(alwaysRun = true)
        public void closeBrowser() {
            driver.quit();
        }
    }
}
