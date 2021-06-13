package com.class4;

import org.testng.annotations.*;

public class trialAndError {

    @BeforeTest
    public  void BefoRETest(){
        System.out.println("code Before each test runs");
    }
    @AfterTest
    public void afterTEst(){
        System.out.println("code After test runs after each test");
    }
    @BeforeMethod
    public void BeForeMethod(){
        System.out.println("This runs before each and every test");
    }
    @AfterMethod
    public void AFterMethod(){
        System.out.println("This runs after each and every test");
    }
    @Test(priority = 1)
    public void loggingoutTest(){
        System.out.println("Test ONE");
    }
    @Test(priority = 2)
    public void logoutTest(){
        System.out.println("Test TWO");
    }
}
