package Utils;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;

public class CommonMethods {
    public static WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public static void setUp(){
        ConfigReader.readProperties(Constants.CONFIG_FilePath);


}
