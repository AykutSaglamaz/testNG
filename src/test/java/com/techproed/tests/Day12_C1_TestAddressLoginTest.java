package com.techproed.tests;

import com.techproed.pages.TestAddressLoginPage;
import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.Driver;
import org.testng.annotations.Test;

public class Day12_C1_TestAddressLoginTest {
    @Test
    public void testAddressLogin(){
        //Going to the application page
        Driver.getDriver().get(ConfigReader.getProperty("test_address_url"));
        //Creating page object
        TestAddressLoginPage testAddressLoginPage=new TestAddressLoginPage();
        //Signing in
        testAddressLoginPage.email.sendKeys(ConfigReader.getProperty("test_address_email"));
        testAddressLoginPage.password.sendKeys(ConfigReader.getProperty("test_address_password"));
        testAddressLoginPage.singInButton.click();
        //closing the driver
        Driver.closeDriver();
    }
}
