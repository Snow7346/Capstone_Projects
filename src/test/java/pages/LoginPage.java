package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	
	WebDriver driver;
	
	By Username = By.name("username");
	By Password = By.name("password");
	By LoginButton = By.xpath("//button[@type='submit']");
	By errMSG = By.cssSelector(".oxd-text.oxd-text--p.oxd-alert-content-text");
	
	public LoginPage(WebDriver driver) {
		
		this.driver =driver;
		
	}
	
	public void enterUsername(String username) {
		
		driver.findElement(Username).sendKeys(username);
	}

	public void enterPassword(String password) {
		driver.findElement(Password).sendKeys(password);
	}
	
	public void clickLogin() {
		driver.findElement(LoginButton).click();
	}
	
	public void errMessage() {
		driver.findElement(errMSG).getText();
	}
	public void login(String username,String password) {
		enterUsername(username);
		enterPassword(password);
		clickLogin();
		
	}
	
}
