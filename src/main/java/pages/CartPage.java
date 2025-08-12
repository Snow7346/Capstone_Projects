package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

	
	private WebDriver driver;

   private By itemCount = By.xpath("//div[@class='shelf-item__details']/p[@class='desc']");
   
   private By ItemName = By.cssSelector(".shelf-item__details p[class='title']");
   
   private By removeIcon = By.cssSelector(".shelf-item__del");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }
    	
    public String getQuantityCount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement descElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
            itemCount
        ));

        String quantityText = descElement.getText();
        System.out.println("Cart Text:\n" + quantityText);

        String[] lines = quantityText.split("\n");

        for (int i = 0; i < lines.length; i++) {
            System.out.println("Line " + i + ": " + lines[i]);
        }

        // Now extract the quantity from line containing "Quantity:"
        for (String line : lines) {
            if (line.trim().startsWith("Quantity:")) {
                return line.split(":")[1].trim();  // Returns "1"
            }
        }

        throw new RuntimeException("Could not find quantity in cart text!");
    }

    public List<String> getAllItemNames() {
        List<WebElement> itemNameList = driver.findElements(ItemName);
        List<String> itemNames = new ArrayList<>();

        for (WebElement element : itemNameList) {
            itemNames.add(element.getText().trim());
        }

        return itemNames;
    }
    
    public void removeItem() {
    	
    	driver.findElements(removeIcon).get(0).click();
    	
    	
    }

   	}

