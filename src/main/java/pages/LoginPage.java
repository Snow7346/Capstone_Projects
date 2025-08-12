package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	
	private WebDriver driver;
	
	private By usernameDropDown = By.id("username");

    private By usernameText = By.xpath("//div[text()='demouser']");
    private By invalidusernametext = By.xpath("//div[text()='locked_user']");
    
    private By passwordDropDown = By.id("password");
    private By passwordText = By.xpath("//div[text()='testingisfun99']");
    private By loginBtn = By.xpath("//*[@id='login-btn']");
    
    private By invalidLoginMsg = By.cssSelector(".api-error");
    
    private By loginVerification = By.cssSelector(".username");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login() {
    	
    	
        driver.findElement(usernameDropDown).click();
        driver.findElement(usernameText).click();
        driver.findElement(passwordDropDown).click();
        driver.findElement(passwordText).click();
        driver.findElement(loginBtn).click();
    }
    
    public void InvalidLogin() {
    	
    	driver.findElement(usernameDropDown).click();
        driver.findElement(invalidusernametext).click();
        driver.findElement(passwordDropDown).click();
        driver.findElement(passwordText).click();
        driver.findElement(loginBtn).click();
    	
    	
    }
    public String  invalidLoginMsg() {
    	
    	String lockedUserText = driver.findElement(invalidLoginMsg).getText();
    	return lockedUserText;
    	
    }
    
    public void loginButton() {
    	
    	 driver.findElement(loginBtn).click();
    }
    		
    public String loginVerification() {
    	
    	String loginVerificationText = driver.findElement(loginVerification).getText();
    	return loginVerificationText;
    }

}
