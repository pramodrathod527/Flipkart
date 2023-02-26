package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utility {
	static String data;
	public static String getDataFromExcelSheet(String sheet1 , int row1 , int col1) throws IOException  {
		File src = new File("C:\\Users\\C zone\\Documents\\inputData.xlsx");
		
		FileInputStream file = new FileInputStream(src);
		
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		
		XSSFSheet sheet = workbook.getSheet(sheet1);
		
		XSSFRow row = sheet.getRow(row1);
		
		try {
			 data = sheet.getRow(row1).getCell(col1).getStringCellValue();
			 return data;
		}
		catch(IllegalStateException exception) {
			double value = sheet.getRow(row1).getCell(col1).getNumericCellValue();
			 data = Double.toString(value);
			 return data;
		}
		
		
		
	}
	
	public static void captureScreenshot(WebDriver driver, String testCaseID) throws IOException {
		TakesScreenshot takeScreenshot = (TakesScreenshot)driver;
		
		File src = takeScreenshot.getScreenshotAs(OutputType.FILE);
		
		SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy_HH_mm_ss");
		Date date = new Date();  
		String current_date = form.format(date);
		
		File dest = new File("F:\\ScreenshotBySelenium\\"+testCaseID+
          " "+current_date+".jpeg");
		
		FileHandler.copy(src, dest);
		
		
	}

	
}
