package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {
	
	private WebDriver driver;

    private By addToCartBtn = By.cssSelector(".shelf-item__buy-btn");
    
    private By CartCloseBtn = By.cssSelector(".float-cart__close-btn");
    
    private By CartBagBtn = By.cssSelector(".bag__quantity");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addFirstProductToCart() {
        driver.findElements(addToCartBtn).get(0).click();
    }

    public void addMultipleItem() throws InterruptedException {
    	
    	for(int i=1;i<5;i++) {
    		
    		driver.findElements(addToCartBtn).get(i).click();
    		Thread.sleep(1000);
    		driver.findElement(CartCloseBtn).click();
    	}
    }
    
    public void OpenCartBag() {
    	
    	driver.findElement(CartBagBtn).click();
    }
}
