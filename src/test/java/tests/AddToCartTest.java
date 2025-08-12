package tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.LoginPage;
import pages.ProductPage;
import utils.BaseTest;

public class AddToCartTest extends BaseTest {
	
	@Test
    public void testAddItemToCart() throws InterruptedException {

//		driver.get("https://bstackdemo.com/?signin=true");
		
		 LoginPage loginPage = new LoginPage(driver);
		 
		 loginPage.login();
		
        ProductPage productPage = new ProductPage(driver);
        
        test.get().info("Adding Item to Cart");
        productPage.addFirstProductToCart();

        Thread.sleep(2000);
        CartPage cartPage = new CartPage(driver);
        
        String QuantityCount =  cartPage.getQuantityCount();
        
        Assert.assertEquals(QuantityCount,"1");
        
        test.get().pass("Quantity is Same");
        
    }
	@Test
	public void testAddMultipleItemToCart() throws InterruptedException {
		
        
        
        LoginPage loginPage = new LoginPage(driver);
		 
		loginPage.login();
		
        ProductPage productPage = new ProductPage(driver);
		
        test.get().info("Adding Multiple Item to Cart");
		productPage.addMultipleItem();
		
		Thread.sleep(2000);
        CartPage cartPage = new CartPage(driver);
        
        List<String> QuantityCount =  cartPage.getAllItemNames();
        
        int ItemCount = QuantityCount.size();
        
        Assert.assertEquals(ItemCount,4);
        
        test.get().pass("All Items Added to Cart");
		
	}

	@Test
	public void removeItemfromCart() throws InterruptedException {
		
		  
		   
		   LoginPage loginPage = new LoginPage(driver);
			 
			 loginPage.login();
			
	        ProductPage productPage = new ProductPage(driver);
			
			productPage.addMultipleItem();
			
			Thread.sleep(2000);
			
			productPage.OpenCartBag();
			
			test.get().info("Removing Single item from Cart");
	        CartPage cartPage = new CartPage(driver);
	        
	        cartPage.removeItem();
	        
	        List<String> QuantityCount =  cartPage.getAllItemNames();
	        
	        int ItemCount = QuantityCount.size();
	        
	        Assert.assertEquals(ItemCount,3);
		
	        
	        
	        
		
	}
}
