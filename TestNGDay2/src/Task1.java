import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.seleniumhq.jetty9.util.IO;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Task1 {

    public class EnableDisableDemo2 {
        WebDriver driver;
        @BeforeMethod
        public void openBrowserAndLaunchApp(){
            System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
            driver=new ChromeDriver();
            driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        }
        @Test
        public void validLogin(){
            WebElement usernameField=driver.findElement(By.id("txtUsername"));
            usernameField.sendKeys("Admin");
            driver.findElement(By.id("txtPassword")).sendKeys("Hum@nhrm123");
            driver.findElement(By.id("btnLogin")).click();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            WebElement welcomeAdminAttribute=driver.findElement(By.xpath("//*[text()='Welcome Admin']"));
            Boolean welcomeAdminIsDisplayed=welcomeAdminAttribute.isDisplayed();
            if(welcomeAdminIsDisplayed){
                System.out.println("Test is valid and passed");
            }else{
                System.out.println("Test is failed");
            }
        }
        @Test
        public void validationOfTitle(){
            String expectedTitle="Human Management System";
            String actualTitle=driver.getTitle();
            if (expectedTitle.equals(actualTitle)) {
                System.out.println("Test is final and title is same");
            }else{
                System.out.println("Test is failed");
            }
        }
        @Test(enabled = false)
        public void invalidLogin() {
            WebElement usernameField = driver.findElement(By.id("txtUsername"));
            usernameField.sendKeys("Admin");
            //driver.findElement(By.id("txtPassword")).sendKeys("Hum@nhrm123");
            driver.findElement(By.id("btnLogin")).click();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            WebElement errorField = driver.findElement(By.id("spanMessage"));
            String errorFieldText = errorField.getText();
            String expectedText="Password cannot be emp";
            Assert.assertEquals(errorFieldText, expectedText);
            System.out.println("Test is passed");
        }
        @AfterMethod
        public void closeBrowser(){
            driver.quit();
        }
    }
}