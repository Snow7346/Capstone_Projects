package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {

	
	 private WebDriver driver;

	    private By checkoutButton = By.cssSelector(".buy-btn");
	    
	    private By FirstName = By.id("firstNameInput");
	    
	    private By LastName = By.id("lastNameInput");
	    
	    private By Address = By.id("addressLine1Input");
	    
	    private By State = By.id("provinceInput");
	    
	    private By PostalCode = By.id("postCodeInput");
	    
	    private By SubmitButton = By.id("checkout-shipping-continue");
	    
	    private By ConfirmationMsg = By.id("confirmation-message");
	    
	    private By EmptyBagMsg = By.cssSelector(".shelf-empty");
	    
	    

	    public CheckoutPage(WebDriver driver) {
	        this.driver = driver;
	    }

	    public void checkout() {
	    	
	        driver.findElement(checkoutButton).click();
	    }
	    
	    public void checkOutForm() {
	    	
	    	driver.findElement(FirstName).sendKeys("Arun Prasad");
	    	driver.findElement(LastName).sendKeys("Sankar");
	    	driver.findElement(Address).sendKeys("Coimbatore");
	    	driver.findElement(State).sendKeys("Tamil Nadu");
	    	driver.findElement(PostalCode).sendKeys("641042");
	    	
	    	driver.findElement(SubmitButton).click();
	    }
	    
	    public String OrderConfirmation() {
	    	
	    	String OrderConfirmationMsg = driver.findElement(ConfirmationMsg).getText();
	    	return OrderConfirmationMsg;
	    }
	    public String placeOrderWithoutAddingItem() {
	    	String EmptyCheckoutMsg = driver.findElement(EmptyBagMsg).getText();
	    	return EmptyCheckoutMsg;
	    }
}
