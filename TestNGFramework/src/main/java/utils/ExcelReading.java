package utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

;

public class ExcelReading {

    static Workbook book;
    static Sheet sheet;
    private static Object LinkedHashMap;

    public static void openExcel(String filePath) throws IOException {

        try {
            FileInputStream fis = new FileInputStream(filePath);
            book = new XSSFWorkbook(fis);//to open the file with workbook

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    //Next, get the sheet
    public static void getSheet(String sheetName){
        sheet= book.getSheet(sheetName);
    }
    //get row
    public static int getRowCount(){  //outter LOOP
        return sheet.getPhysicalNumberOfRows();
        //if you read the data keep it in the sheet it has keys and values which makes a map. You implememnt this in
        //a list form that has a Map and is implement in code you input a xlm file.

    }
        public static int getColsCount(int rowIndex){ //inner LOOP
        return sheet.getRow(rowIndex).getPhysicalNumberOfCells();
        }

        public static String getCelldata(int rowIndex, int colIndex){
        return sheet.getRow(rowIndex).getCell(colIndex).toString();//String form b/c dta is in sting form in application
        }

        //NOW make the LIST that will contain the MAP that has Keys and Values
    public static List<Map<String,String>> excelIntoListMap(String filePath, String sheetName) throws IOException {
        openExcel(filePath);
        getSheet(sheetName);

        List<Map<String, String>> ListData =new ArrayList<>();//if you dont save in alist, values will override as they loop through
        //outter loop
        for (int row = 1; row < getRowCount(); row++) {//from first row create a key for keys and values, start at 0
            //creating a map for every single row.
            Map<String, String>map = new LinkedHashMap<>();
            //looping through the values of a cell, make a for loop for colums
            for (int col = 0; col < getColsCount(row); col++) {//read the actual value in the excel sheet, start from 0
                map.put(getCelldata(0, col),getCelldata(row, col));//this returns the map
                //all of keys are in row 0, which is the first row(firstName) the rows of cols loop through to get the keys
            }
                ListData.add(map);//the map has keys and values that is saved in list. We will use this in the code
        }
        return ListData;
    }
}
