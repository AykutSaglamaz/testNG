package com.techproed.extentreports;

import com.aventstack.extentreports.Status;
import com.techproed.pages.DefaultPage;
import com.techproed.pages.LoginPage;
import com.techproed.utilities.ReusableMethods;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.Driver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;

public class ExtentReports2 {
    LoginPage loginPage;
    DefaultPage defaultPage;
    //ExtentSparkReporter extentHtmlReporter;
    //Creating html report : Generated html report
    ExtentHtmlReporter extentHtmlReporter;
    //Creating extentReport : Create the report object
    ExtentReports extentReports;
    //Creating extentTest : logs information
    ExtentTest logger;

    @BeforeTest(alwaysRun = true)//Runs before each Test. This will run only once.
    public void setUpTest(){
        loginPage = new LoginPage();
        defaultPage = new DefaultPage();
//        Path of the html report
        String reportPath =  System.getProperty("user.dir")+ "/test-output/my_project_report.html";
//        Creating html report in the file path
        extentHtmlReporter= new ExtentHtmlReporter(reportPath);
//        Creating extent report
        extentReports=new ExtentReports();
//        Attaching html report the Extent report
        extentReports.attachReporter(extentHtmlReporter);
//      So far the report creating is DONE.
        logger =extentReports.createTest("Caretta Extent Reports","Automation Login Scenarios");

    }

    @AfterTest(alwaysRun = true)
    public void tearDownTest(){
//        Closing the extent reports and generation it.
        extentReports.flush();
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        Driver.getDriver().get(ConfigReader.getProperty("app_url_login"));
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) throws IOException {//Listen the test result
//        We will take screenshot when only test case fails
        if (result.getStatus()==ITestResult.FAILURE){
            String path = ReusableMethods.getScreenshot(result.getName());
            logger.addScreenCaptureFromPath(path);
            logger.fail(result.getThrowable());
        }else if (result.getStatus()==ITestResult.SKIP){
            logger.skip("TEST CASE IS SKIPPED : "+result.getName());
        }
        Driver.closeDriver();
    }


    @Test(alwaysRun = true)
    public void extentReportTest1() throws IOException {
        logger.info("Giving information");
        logger.pass("Test Case Passed Yayyy");
        logger.fail("Test Case Failled Nooooooo");
        logger.skip("Test Case is skipped No reaction!!!");
    }

    @Test(alwaysRun = true)
    public void loginTest(){

        loginPage.username.sendKeys(ConfigReader.getProperty("manager_username"));
        logger.log(Status.PASS,"Username Entered");//OR BELOW LINE CAN BE USED
//        logger.pass("Test Case Passed Yayyy");
        loginPage.password.sendKeys(ConfigReader.getProperty("manager_password"));
        logger.log(Status.PASS,"Password Entered");
        loginPage.loginButton.click();
        logger.log(Status.PASS,"Clicked on login");
        Assert.assertTrue(defaultPage.addUserButton.isDisplayed());
        logger.log(Status.PASS,"Login SUCCESS!");

    }

}