package tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import pages.LoginPage;
import utils.BaseTest;

public class LoginTest extends BaseTest {
	
	@Test
    public void testValidLogin() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        
        test.get().info("logging With Valid Credentials");
        loginPage.login();
        Thread.sleep(2000);
        String loginusernameText = loginPage.loginVerification();
        
        Assert.assertEquals(loginusernameText,"demouser");
        
    }
	@Test
	public void testInvalidLogin() {
		
       LoginPage loginPage = new LoginPage(driver);
        
        test.get().info("logging With InValid Credentials");
        loginPage.InvalidLogin();
        
        String ErrMsg = loginPage.invalidLoginMsg();
        
        Assert.assertEquals(ErrMsg,"Your account has been locked.");
	}

	@Test
	public void testEmptyLogin() {
		LoginPage loginPage = new LoginPage(driver);
		
		test.get().info("logging With empty Credentials");
		
		loginPage.loginButton();
		 String ErrMsg = loginPage.invalidLoginMsg();
	        
	        Assert.assertEquals(ErrMsg,"Invalid Username");
	}
   
}
