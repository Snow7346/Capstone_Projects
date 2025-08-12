package tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductPage;
import utils.BaseTest;

public class CheckoutTest extends BaseTest {

	
	@Test
	public void PlaceOrder() throws InterruptedException {
		
	
        LoginPage loginPage = new LoginPage(driver);
        
      
        loginPage.login();
		
	
		
		ProductPage productPage = new ProductPage(driver);
        productPage.addFirstProductToCart();
        
        Thread.sleep(2000);
	
        test.get().info("Placing Order with Valid Credentials");
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		
		checkoutPage.checkout();
		
		Thread.sleep(2000);
		
		checkoutPage.checkOutForm();
		
		String OrderConfirm = checkoutPage.OrderConfirmation();
		
		Assert.assertEquals(OrderConfirm,"Your Order has been successfully placed.");
		
		
	}
	@Test
	public void checkOutFlow() throws InterruptedException {
		
		
        LoginPage loginPage = new LoginPage(driver);
        
        
        loginPage.login();
		
	
		
		ProductPage productPage = new ProductPage(driver);
		
        productPage.OpenCartBag();
        
        test.get().info("Placing Order with Empty cart");
        
        Thread.sleep(2000);
	
		
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		
		String placeEmptyOrder  = checkoutPage.placeOrderWithoutAddingItem();
		
		Assert.assertEquals(placeEmptyOrder,"Add some products in the bag\n"
				+ ":)");
		
		Thread.sleep(2000);
		
		
		
		
	}
}
