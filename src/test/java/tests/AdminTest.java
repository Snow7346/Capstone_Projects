package tests;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.AdminPage;
import pages.LoginPage;
import utils.BaseTest;

public class AdminTest extends BaseTest {
	
	LoginPage lPage;
	AdminPage adminPage;
	@BeforeTest
	public void setUpPage() {
		
		super.setUp();
		lPage = new LoginPage(driver);
		adminPage = new AdminPage(driver);
	}
	
	@Test(priority=1)
	public void validLoginTest() throws InterruptedException {
		
		lPage.login("Admin","admin123");
		Thread.sleep(2000);
		Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
	}
	@Test(priority=2)
	public void testMenuOptionsCount() {
		
		
		int count = adminPage.getMenuOptionCount();
		System.out.println("Total menu Options "+count);
		Assert.assertEquals(count,12);
		adminPage.clickAdminMenu();
		
	}
	@Test(priority=3)
	public void  testSearchByUsername() throws InterruptedException {
		
		adminPage.searchByUsername("Admin");
		Thread.sleep(2000);
		System.out.println("Records Found "+adminPage.getRecordCount());
		adminPage.refreshPage();
		
	}
	@Test(priority=4)
	public void searchByUserRole() throws InterruptedException {
		
		adminPage.searchByUserRole("Admin");
		Thread.sleep(2000);
		System.out.println("Records Found "+adminPage.getRecordCount());
		adminPage.refreshPage();
	}
	@Test(priority=5)
	public void testByUserStatus() throws InterruptedException {
		
		adminPage.searchByStatus("Enabled");
		Thread.sleep(2000);
        System.out.println("Records found: " + adminPage.getRecordCount());
        adminPage.refreshPage();
	}
	@AfterTest
	public void closeBrowser() {
		super.closeDriver();
	}

}
