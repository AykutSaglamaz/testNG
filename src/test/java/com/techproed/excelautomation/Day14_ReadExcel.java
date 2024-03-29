package com.techproed.excelautomation;

import com.techproed.utilities.ExcelUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellAddress;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/*
Import the apache poi dependency in your pom file
Create resources directory under java folder(right click on java and create the folder)
Add the excel file on the resources folder
Create a new package: excelautomation
Create a new class : ReadExcel
Create a new test method : readExcel()
Store the path of the  file in a string
Open the file
Open the workbook using fileinputstream
Open the first worksheet
Go to first row
Go to first cell on that first row and print
Go to second cell on that first row and print
Go to 2nd row first cell
Go to 3rd row 2nd cell-chain the row and cell
Find the number of row
Find the number of used row
Print country, capitol key value pairs as map object
 */
public class Day14_ReadExcel {

    @Test
    public void readExcel() throws IOException {

//        Store the path of the file in a string
        String path = "./src/test/java/resources/Capitals.xlsx";
//        Open the file
        FileInputStream fileInputStream = new FileInputStream(path);
//        Open the workbook using fileinputstream
        Workbook workbook = WorkbookFactory.create(fileInputStream);
//        Open the first worksheet
        Sheet sheet = workbook.getSheetAt(0);//index of sheet starts at 0
//        Go to first row
        Row firstRow  = sheet.getRow(0);//index of row starts at 0
//        Go to first cell on that first row and print
        Cell firstCell = firstRow.getCell(0);//index of cell stars at 0
        System.out.println(firstCell);//COUNTRY

//        Go to second cell on that first row and print
//        Row secondRow = sheet.getRow(1);
//        Cell cell21 = secondRow.getCell(0);
//        System.out.println(cell21);
        Cell row1Cell2=sheet.getRow(0).getCell(1);//we can chain the methods
        System.out.println(row1Cell2);//CAPITAL

//        Go to 2nd row first cell and assert if the data equal to USA
        Cell row2Cell1=sheet.getRow(1).getCell(0);
//        sheet.getRow(ROW INDEX NUMBER).getCell(CELL INDEX NUMBER)
        boolean isEqual = row2Cell1.toString().equals("USA");
        System.out.println(row2Cell1);
        System.out.println(isEqual);
//        Assert.assertTrue(isEqual);
//        Go to 3rd row 2nd cell-chain the row and cell
        Cell row3Cell2=sheet.getRow(2).getCell(1);
        System.out.println(row3Cell2.toString());//Paris
//        Find the number of row
        int lastRowNumber = sheet.getLastRowNum()+1;//index starts at 0
        System.out.println(lastRowNumber);
//        Find the number of used row
        int usedNumberOfRow = sheet.getPhysicalNumberOfRows();
        System.out.println(usedNumberOfRow);//index starts at 1
//        Print country, capitol key value pairs as map object

        Map<String, String> worldCapitals = new HashMap<>();

        int countryColumn = 0;
        int capitalColumn = 1;

        /*
         * Starting row number : 1- because row is Header
         * Ending Row Number  : lastRowNumber - sheet.getLastRowNum()+1;
         *
         * USA :      sheet   .getRow(1)       .getCell(0)
         * France     sheet   .getRow(2)       .getCell(0)
         * England    sheet   .getRow(3)       .getCell(0)
         *
         * D.C -     sheet    .getRow(1)        .getCell(1)
         * Paris-    sheet    .getRow(2)        .getCell(1)
         * London-   sheet    .getRow(3)        .getCell(1)
         *
         *           sheet.getRow(rowNumber).getCell(columnNumber)
         * */

        for (int rowNumber = 1;rowNumber<lastRowNumber;rowNumber++){
            String countries=sheet.getRow(rowNumber).getCell(countryColumn).toString();
            String capitals=sheet.getRow(rowNumber).getCell(capitalColumn).toString();
            System.out.println("COUNTRIES : "+countries);
            System.out.println("CAPITALS : "+capitals);
            worldCapitals.put(countries,capitals);//adding the countries and capitals in the map
        }

//        {France-R2C0=Paris-R2C1, Greece=Athens, Canada=Ottowa, USA-R1C0=D.C-R1C1, Turkey=Ankara, Norway=Oslo, Japan=Tokya, England=London, Italy=Rome, Germany=Berlin}
        System.out.println(worldCapitals);

    }

    @Test
    public void excelUtilDemo(){
        //We use Util classes to do faster and accurate automation script
        //Using some of the ExcelUtil methods
        String path = "./src/test/java/resources/Capitals.xlsx";
        String sheetName="capitals_sheet";

        //To use the ExcelUtil class, first create ExcelUtil Object
        ExcelUtil excelUtil = new ExcelUtil(path,sheetName);

        //Now I can call the reusable excel methods
        //getDataList()
        System.out.println(excelUtil.getDataList());

        //Find the number of column in Capitals
        System.out.println(excelUtil.columnCount());//2

        //Find the number of row in Capitals sheet
        System.out.println(excelUtil.rowCount());//11

        //Find the data in row 9 column 1
        System.out.println(excelUtil.getCellData(8,0));//Germany

        //Find all column names
        System.out.println(excelUtil.getColumnsNames());//[Ulke-R0C0, Baskent-R0C1]

    }
}