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

public class task2 {
    WebDriver driver;
    @BeforeMethod
    public void openBrowserAndLaunchApp() {
        System.setProperty("webdriver.chrome.driver",
                "src/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        // launch the application
        driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Test(priority=1, enabled = true)
    public void validLogin() {
        // WebElement usernamefield = driver.findElement(By.id("txtUsername"));
        // usernamefield.sendKeys("");
        // usernamefield.clear();
        // usernamefield.click();
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        driver.findElement(By.id("txtPassword")).sendKeys("Hum@nhrm123");
        driver.findElement(By.id("btnLogin")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement welcomeMessage = driver.findElement(By.xpath("//*[text()='Welcome Admin']"));

        Assert.assertTrue(welcomeMessage.isDisplayed());
        String textRecieved = welcomeMessage.getText();
        String actualtext = "Welcome Admin";
        Assert.assertEquals(textRecieved, actualtext);
        System.out.println("Test is working fine");
//  if (welcomeAttribute.isDisplayed()) {
        //System.out.println("Test is valid and Passed");
        //} else {
        // System.out.println("Test is failed");
        // }
    }

    @Test(priority = 2, enabled = true)
    public void validationOfTitle() {
        String expectedTitle = "Human Management System";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);
       // if (expectedTitle.equals(actualTitle)) {
            System.out.println("Test is final and title is same");
       // } else {
            System.out.println("Test is failed");
       // }
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
