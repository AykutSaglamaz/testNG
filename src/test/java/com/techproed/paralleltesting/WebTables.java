package com.techproed.paralleltesting;
import com.techproed.pages.DefaultPage;
import com.techproed.pages.HotelRoomsPage;
import com.techproed.pages.LoginPage;
import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.Driver;
import com.techproed.utilities.ReusableMethods;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;
public class WebTables {
    @Test
    public void entireTable(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfigReader.getProperty("app_url_login"));
        driver.findElement(By.id("UserName")).sendKeys(ConfigReader.getProperty("manager_username"));
        driver.findElement(By.id("Password")).sendKeys(ConfigReader.getProperty("manager_password"));
        driver.findElement(By.id("btnSubmit")).click();
        //Click on Hotel Management
        driver.findElement(By.xpath("//span[.='Hotel Management']")).click();
        //Click on Hotel Rooms
        driver.findElement(By.partialLinkText("Hotel Rooms")).click();
//        Create a test method: entireTable() and print all of headers
        System.out.println("*Entire Table*");
        System.out.println("*Table Body*");
        WebElement tableBody = driver.findElement(By.xpath("//table//tbody"));
        System.out.println(tableBody.getText());
        List<WebElement> allHeaders = driver.findElements(By.xpath("//th"));
        for (WebElement eachHeader : allHeaders){
            System.out.println(eachHeader.getText());
        }
        driver.quit();
//        Create a test method: printCells() and a the total number of cells in the table body and print all of the cells
//        Create a test method: printColumns() and print Find the total number of columns and Print the elements of the 5th column
//        Create a test method: printData(int row, int column); This method should print the given cell. Example: printData(2,3); should print 2nd row,3rd column
    }
    @Test
    public void printRows(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfigReader.getProperty("app_url_login"));
        driver.findElement(By.id("UserName")).sendKeys(ConfigReader.getProperty("manager_username"));
        driver.findElement(By.id("Password")).sendKeys(ConfigReader.getProperty("manager_password"));
        driver.findElement(By.id("btnSubmit")).click();
        //Click on Hotel Management
        driver.findElement(By.xpath("//span[.='Hotel Management']")).click();
        //Click on Hotel Rooms
        driver.findElement(By.partialLinkText("Hotel Rooms")).click();
        //        Create a test method: printRows() and Print all of the rows and print the element s on the 4th row
        System.out.println("*Print Rows*");
        List<WebElement> allRows = driver.findElements(By.xpath("//tbody//tr"));
        //row numbers
        int rowNum=1;
        for (WebElement eachRow : allRows){
            System.out.println("Row Number "+rowNum + " => "+eachRow.getText());
            rowNum++;
        }
        //Print row4 only
        WebElement row4 = driver.findElement(By.xpath("//tbody//tr[4]"));
        System.out.println("ROW 4 => "+row4.getText());
        driver.quit();
    }
    //    Create a test method: printCells() and a the total number of cells in the table body and print all of the cells
    @Test
    public void printCells(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfigReader.getProperty("app_url_login"));
        driver.findElement(By.id("UserName")).sendKeys(ConfigReader.getProperty("manager_username"));
        driver.findElement(By.id("Password")).sendKeys(ConfigReader.getProperty("manager_password"));
        driver.findElement(By.id("btnSubmit")).click();
        //Click on Hotel Management
        driver.findElement(By.xpath("//span[.='Hotel Management']")).click();
        //Click on Hotel Rooms
        driver.findElement(By.partialLinkText("Hotel Rooms")).click();
        System.out.println("*Print Cells*");
        List<WebElement> allCells = driver.findElements(By.xpath("//tbody//td"));
        System.out.println("Total Cell Number => " +allCells.size());
        int cellNum=1;
        for (WebElement eachCell : allCells){
            System.out.println(cellNum +" : "+eachCell.getText());
            cellNum++;
        }
        driver.quit();
    }
    //    Create a test method: printColumns() and print Find the total number of columns and Print the elements of the 5th column
    @Test
    public void printColumns(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfigReader.getProperty("app_url_login"));
        driver.findElement(By.id("UserName")).sendKeys(ConfigReader.getProperty("manager_username"));
        driver.findElement(By.id("Password")).sendKeys(ConfigReader.getProperty("manager_password"));
        driver.findElement(By.id("btnSubmit")).click();
        //Click on Hotel Management
        driver.findElement(By.xpath("//span[.='Hotel Management']")).click();
        //Click on Hotel Rooms
        driver.findElement(By.partialLinkText("Hotel Rooms")).click();
        //The number of column equals to the number of th
        List<WebElement> allHeaders = driver.findElements(By.xpath("//th"));
        int numberOfColumn = allHeaders.size();
        System.out.println("There are "+numberOfColumn + " column.");
        //       Print the elements of the 5th column
        List<WebElement> column5Data = driver.findElements(By.xpath("//table//tbody//tr//td[5]"));
        int columnNum=1;
        for (WebElement eachColumn : column5Data){
            System.out.println("Data "+columnNum+" : "+eachColumn.getText());
            columnNum++;
        }
        driver.quit();
    }
//    Create a test method: printData(int row, int column);
//    This method should print the given cell.
//    Example: printData(2,3); should print 2nd row,3rd column
}
