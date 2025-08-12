package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.LoginPage;
import utils.BaseTest;

public class LoginTest extends BaseTest {
	
	LoginPage lPage;
	@BeforeTest
	public void pageSetup() {
		
		super.setUp();
		lPage = new LoginPage(driver);
	}
	@Test
	public void validLoginTest() throws InterruptedException {
		
		lPage.login("Admin","admin123");
		Thread.sleep(2000);
		Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
	}

    @AfterTest
	public void closeBrowser() {
		super.closeDriver();
	}
}
