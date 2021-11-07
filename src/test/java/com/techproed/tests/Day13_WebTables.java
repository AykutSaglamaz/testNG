package com.techproed.tests;
import com.techproed.pages.DefaultPage;
import com.techproed.pages.HotelRoomsPage;
import com.techproed.pages.LoginPage;
import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
public class Day13_WebTables {
    LoginPage loginPage;
    DefaultPage defaultPage;
    HotelRoomsPage hotelRoomsPage;
    //    When user goes to HotelRoom page on the application
    public void setUp(){
        Driver.getDriver().get(ConfigReader.getProperty("app_url_login"));
        loginPage= new LoginPage();
        loginPage.username.sendKeys(ConfigReader.getProperty("manager_username"));
        loginPage.password.sendKeys(ConfigReader.getProperty("manager_password"));
        loginPage.loginButton.click();
        //asserting login success
        defaultPage= new DefaultPage();
        Assert.assertTrue(defaultPage.addUserButton.isDisplayed());
        //Click on Hotel Management
        defaultPage.hotelManagementTab.click();
        //Click on Hotel Rooms
        defaultPage.hotelRoomsTab.click();
        //Click on Add Hotel Room
        hotelRoomsPage=new HotelRoomsPage();
    }
    @Test(groups = "regression-group-1")
    public void entireTable(){
        setUp();
//        Create a test method: entireTable() and print all of headers
        System.out.println("*Entire Table*");
        System.out.println("*Table Body*");
        WebElement tableBody = Driver.getDriver().findElement(By.xpath("//table//tbody"));
        System.out.println(tableBody.getText());
        List<WebElement> allHeaders = Driver.getDriver().findElements(By.xpath("//th"));
        for (WebElement eachHeader : allHeaders){
            System.out.println(eachHeader.getText());
        }
//        Create a test method: printCells() and a the total number of cells in the table body and print all of the cells
//        Create a test method: printColumns() and print Find the total number of columns and Print the elements of the 5th column
//        Create a test method: printData(int row, int column); This method should print the given cell. Example: printData(2,3); should print 2nd row,3rd column
    }
    @Test(groups = "regression-group-1")
    public void printRows(){
        setUp();
        //        Create a test method: printRows() and Print all of the rows and print the element s on the 4th row
        System.out.println("*Print Rows*");
        List<WebElement> allRows = Driver.getDriver().findElements(By.xpath("//tbody//tr"));
        //row numbers
        int rowNum=1;
        for (WebElement eachRow : allRows){
            System.out.println("Row Number "+rowNum + " => "+eachRow.getText());
            rowNum++;
        }
        //Print row4 only
        WebElement row4 = Driver.getDriver().findElement(By.xpath("//tbody//tr[4]"));
        System.out.println("ROW 4 => "+row4.getText());
    }
    //    Create a test method: printCells() and a the total number of cells in the table body and print all of the cells
    @Test
    public void printCells(){
        setUp();
        System.out.println("*Print Cells*");
        List<WebElement> allCells = Driver.getDriver().findElements(By.xpath("//tbody//td"));
        System.out.println("Total Cell Number => " +allCells.size());
        int cellNum=1;
        for (WebElement eachCell : allCells){
            System.out.println(cellNum +" : "+eachCell.getText());
            cellNum++;
        }
    }
    //    Create a test method: printColumns() and print Find the total number of columns and Print the elements of the 5th column
    @Test
    public void printColumns(){
        setUp();
        //The number of column equals to the number of th
        List<WebElement> allHeaders = Driver.getDriver().findElements(By.xpath("//th"));
        int numberOfColumn = allHeaders.size();
        System.out.println("There are "+numberOfColumn + " column.");
        //       Print the elements of the 5th column
        List<WebElement> column5Data = Driver.getDriver().findElements(By.xpath("//table//tbody//tr//td[5]"));
        int columnNum=1;
        for (WebElement eachColumn : column5Data){
            System.out.println("Data "+columnNum+" : "+eachColumn.getText());
            columnNum++;
        }
    }
    //    Create a test method: printData(int row, int column);
//    This method should print the given cell.
//    Example: printData(2,3); should print 2nd row,3rd column
    @AfterMethod
    public void tearDown(){
        Driver.closeDriver();
    }
}