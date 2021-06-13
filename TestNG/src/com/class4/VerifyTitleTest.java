package com.class4;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class VerifyTitleTest {
    WebDriver driver;

    @Test
    public void tittleTest(){
        String expectedtitle= "Electronics, Cars, Fashion, Collectibles & More | eBay";

        System.setProperty("webdriver.chrome.driver", "C:\\webdrivers\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("https://www.ebay.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle,expectedtitle);

    }
    }


