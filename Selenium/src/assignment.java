import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.seleniumhq.jetty9.util.IO;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class assignment {
    //HW
    // Go to aa.com/homePage.do ~done
    // select depart date
    //  select return date
    //select destination
    // click on search
    // quit the browser
    public static String url = "https://www.aa.com/homePage.do";
    private static IO FileUtils;

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(url);

        WebElement departureDate = driver.findElement(By.id("aa-leavingOn"));
        System.out.println(departureDate.getSize());
        departureDate.click();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        List<WebElement> departureDates = driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']/tbody/tr/td"));

        for (WebElement depDate : departureDates) {
            if (depDate.getText().equals("30")) {
                depDate.click();
                break;
            }
        }
        WebElement arrDate = driver.findElement(By.id("aa-returningFrom"));
        arrDate.click();
        WebElement nextArrow = driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[2]/div/a"));
        nextArrow.click();
        List<WebElement> arrivalDates = driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']/tbody/tr/td"));
        for (WebElement arrivalday : arrivalDates) {
            if (arrivalday.getText().equals("10")) {
                arrivalday.click();
                break;
            }
        }
        WebElement searchButton = driver.findElement(By.id("flightSearchForm.button.reSubmit"));
        searchButton.click();
        driver.quit();
    }
}