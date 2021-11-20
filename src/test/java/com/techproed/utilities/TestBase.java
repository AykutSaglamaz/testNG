package com.techproed.utilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import java.io.IOException;

public class TestBase {

    //    We will generate ExtentHtmlReporter. Note that we could have use ExtentSparkReporter.
    protected static ExtentHtmlReporter extentHtmlReporter;
    //    This creates the actual report sheet
    protected static ExtentReports extentReports;
    //    This is used to create tests and log information such as pass, fail, skip,...
    protected static ExtentTest logger;

    @BeforeTest(alwaysRun = true)//Runs before each Test. This will run only once.
    public void setUpTest(){
//        Path of the html report
        String reportPath =  System.getProperty("user.dir")+ "/test-output/my_project_report.html";
//        Creating html report in the file path
        extentHtmlReporter= new ExtentHtmlReporter(reportPath);
//        Creating extent report
        extentReports=new ExtentReports();
//        Attaching html report the Extent report
        extentReports.attachReporter(extentHtmlReporter);
//      So far the report creating is DONE.


//      OPTIONAL : We can add custom information for our our report
        extentReports.setSystemInfo("Environment","QA Environment");
        extentReports.setSystemInfo("Browser","chrome");
        extentReports.setSystemInfo("Tester","Best Tester Ever");
        extentReports.setSystemInfo("Team Name","Bug Fighters");
        extentHtmlReporter.config().setDocumentTitle("Caretta Hotels Report");
        extentHtmlReporter.config().setReportName("Caretta Login Report");

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
//           Using reusable getScreenShot method instead of the codes above
            String path = ReusableMethods.getScreenshot(result.getName());
//          adding the screenshot to the html report
            logger.addScreenCaptureFromPath(path);
//           adding the error message in the html report
            logger.fail(result.getThrowable());
        }else if (result.getStatus()==ITestResult.SKIP){
            logger.skip("TEST CASE IS SKIPPED : "+result.getName());
        }
        Driver.closeDriver();
    }

}