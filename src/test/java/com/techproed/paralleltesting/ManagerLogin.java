package com.techproed.paralleltesting;
import com.techproed.utilities.ReusableMethods;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
public class ManagerLogin {
    //    name is used to give custom name
//    parallel = true is used for parallel testing
//    parallel=false is the default value. If parallel=false, then test run in sequential (not parallel)
    @DataProvider(name = "manager-profiles", parallel = true)
    public Object [][] getData(){
        String [][] managerProfile= {
                {"manager","Manager1!"},
                {"manager2","Manager2!"},
                {"manager3","Manager3!"}
        };
        return managerProfile;
    }
    @Test(dataProvider = "manager-profiles")
    public void test1(String username,String password){
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://qa-environment.resortsline.com/Account/Logon");
        try{
            ReusableMethods.waitFor(1);
            driver.findElement(By.id("details-button")).click();
            ReusableMethods.waitFor(1);
            driver.findElement(By.id("proceed-link")).click();
            ReusableMethods.waitFor(1);
        }catch (Exception e){
            System.out.println("Advanced Link and Proceed Link is not displayed");
        }
        driver.findElement(By.id("UserName")).sendKeys(username);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.id("btnSubmit")).click();
        String actualUserID=driver.findElement(By.xpath("(//span)[1]")).getText();
        Assert.assertEquals(actualUserID,username);
        driver.quit();
    }
    @Test(dataProvider = "manager-profiles")
    public void test2(String username,String password){
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://qa-environment.resortsline.com/Account/Logon");
        try{
            ReusableMethods.waitFor(1);
            driver.findElement(By.id("details-button")).click();
            ReusableMethods.waitFor(1);
            driver.findElement(By.id("proceed-link")).click();
            ReusableMethods.waitFor(1);
        }catch (Exception e){
            System.out.println("Advanced Link and Proceed Link is not displayed");
        }
        driver.findElement(By.id("UserName")).sendKeys(username);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.id("btnSubmit")).click();
        String actualUserID=driver.findElement(By.xpath("(//span)[1]")).getText();
        Assert.assertEquals(actualUserID,username);
        driver.quit();
    }
}
