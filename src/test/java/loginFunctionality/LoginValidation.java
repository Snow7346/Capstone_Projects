package loginFunctionality;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class LoginValidation {
	
	String fPath = "/Users/arunprasad/Arun's Selenium WebDriver/OrangeHRM/LoginTestData.xlsx";
	File file;
	FileInputStream fis;
	FileOutputStream fos;
	XSSFWorkbook wb;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;
	int rows,i,j,index = 1;
	WebDriver driver;
	
	ExtentReports extent;
	ExtentTest test;
	
	@BeforeTest
	public void beforeTest() throws IOException {
		file = new File(fPath);
		fis = new FileInputStream(file);
		wb = new XSSFWorkbook(fis);
		sheet = wb.getSheetAt(0);
		fos = new FileOutputStream(file);
		
		
		ExtentSparkReporter spark = new ExtentSparkReporter("test-output/OrangeHRm_ExtentReport.html");
		extent = new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("Project name","OrangeHRM_Project");
        extent.setSystemInfo("Machine", "MAC");
        extent.setSystemInfo("OS","macOS");
        extent.setSystemInfo("Comapny name","StarAgile");
        extent.setSystemInfo("Tester","Arun Prasad");
        extent.setSystemInfo("Browser","Google Chrome");
		
		
		driver = new ChromeDriver();
		
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
	}
	@Test(dataProvider="getLoginData")
	public void loginOrangeHRM(String un,String pwd) {
		
		test = extent.createTest("Login Test Using Username - "+un+"and Password - "+pwd);
		
		driver.findElement(By.name("username")).sendKeys(un);
		driver.findElement(By.name("password")).sendKeys(pwd);
		
		takesScreenshot();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}
	@DataProvider
	public Object[][] getLoginData(){
		
		rows = sheet.getPhysicalNumberOfRows ();
		String[][] LData = new String[rows - 1][2];
		for (i = 0; i < rows - 1; i++)
		{
		row = sheet. getRow(i + 1);
		for (j = 0; j < 2; j++) {
			
		cell = row.getCell(j);
		LData [i][j] = cell.getStringCellValue();
		}
		}
		return LData;
	}
	
	public void takesScreenshot() {
		Date date = new Date();
		String filename  =  date.toString().replace(":","_").replace(" ","_");
		
		File SSFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File file = new File(filename+".jpeg");
		
		try {
			FileHandler.copy(SSFile, file);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	@AfterMethod
	public void afterMethod() throws InterruptedException {
		
		row = sheet.getRow(index);
    	cell = row.createCell(2);
		
		if(driver.getCurrentUrl().contains("dashboard")) {
			
			
			System.out.println("Test Case Pass");
			Thread.sleep(2000);
			
			test.pass("Login Successful");
			takesScreenshot();
			driver.findElement(By.cssSelector(".oxd-userdropdown-tab")).click();
			driver.findElement(By.linkText("Logout")).click();
			
			cell.setCellValue("Pass");
		}
		else {
			
			
			System.out.println("Test Case Fails");
			
			Thread.sleep(2000);
			test.fail("Login Failed");
			takesScreenshot();
			
			cell.setCellValue("Fail");
		}
		
	}
	@AfterTest
	public void afterTest() throws IOException {
		

		wb.write(fos);
		wb.close();
		fis.close();
		
		driver.close();
		extent.flush();
	}

}
