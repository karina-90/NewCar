package TestBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class BaseClass {
    package TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;
    public class BaseClass {
        public static WebDriver driver;
        /*
        This method is to open the browser
         */
        public static void setupWithSpecificUrl(String url){
            System.setProperty("webdriver.chrome.driver", "");
            driver = new ChromeDriver(); // launch the browser
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            driver.get(url);
        }
        /*
        this method is to quit the browser
         */
        public static void tearDown(){
            driver.quit();
        }
    }
}
