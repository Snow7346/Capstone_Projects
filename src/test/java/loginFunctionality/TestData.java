package loginFunctionality;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestData {
	
	File file;
	FileOutputStream fos;
	XSSFWorkbook wb;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;
	int index = 0;
	
  @Test(dataProvider = "setLoginData")
  public void getLoginData(String username, String password) {
	  
	  
	  row = sheet.createRow(index);
	  cell = row.createCell(0);
	  cell.setCellValue(username);
	  
	  cell = row.createCell(1);
	  cell.setCellValue(password);
	  
	
	  
	  index++;  
  }

  @DataProvider
  public Object[][] setLoginData() {
	  return new Object[][] { 
		    new Object[] { "UserName", "Password" },
			new Object[] { "Admin", "admin123"},
			new Object[] { "admin", "admin"},
			new Object[] { "Admin", "Admin13"}, 
			new Object[] { "admin", "Admin23"},
			new Object[] { "Admin", "admn123"},
	};
  }
  @BeforeTest
  public void beforeTest() {
	  try {
	  file = new File("LoginTestData.xlsx");
	  fos = new FileOutputStream(file);
	  wb = new XSSFWorkbook();
	  sheet = wb.createSheet("Data");
	  
	  }
	  catch(Exception e) {
		  System.out.println(e.getMessage());
	  }
  }

  @AfterTest
  public void afterTest() throws IOException {
	  
	  
	  wb.write(fos);
	  wb.close();
	  fos.close();
  }
	

}
