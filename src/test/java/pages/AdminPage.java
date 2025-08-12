package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdminPage {
	
	WebDriver driver;
	
	public AdminPage(WebDriver driver) {
		
		this.driver = driver;
		
	}
	
	By menuOptions = By.cssSelector(".oxd-sidepanel-body li");
	By adminMenu = By.xpath("//span[text()='Admin']");
	By usernameInput = By.xpath("//label[text()='Username']/following::input[1]");
	By userRoleDropdown = By.xpath("//label[text()='User Role']/following::div[@class='oxd-select-text-input']");
	By userStatusDropDown = By.xpath("//label[text()='Status']/following::div[@class='oxd-select-text-input']");
	By dropdownOptions = By.xpath("//div[@role='listbox']//span");
	By searchButton = By.xpath("//button[normalize-space()='Search']");
	By restButton = By.xpath("//button[normalize-space()='Reset']");
	By recordsFound = By.cssSelector(".oxd-table-body");
	
	public int getMenuOptionCount() {
		
		return driver.findElements(menuOptions).size();
	}
	
	public void clickAdminMenu() {
		driver.findElement(adminMenu).click();
	}
	
	public void enterUsername(String username) {
		driver.findElement(usernameInput).sendKeys(username);
	}
	
	public void selectDropDown(By dropdownlocator,String optionText) {
		
		driver.findElement(dropdownlocator).click();
		
		List<WebElement> options = driver.findElements(dropdownOptions);
		for(WebElement opt:options) {
			if(opt.getText().equalsIgnoreCase(optionText)) {
				opt.click();
				break;
			}
		}
	}
	
	public void clickSearch() {
		driver.findElement(searchButton).click();
	}
	
	public void refreshSearch() {
		driver.findElement(restButton).click();
	}
	
	public int getRecordCount() {
		return driver.findElements(recordsFound).size();
	}
	
	public void searchByUsername(String username) {
		enterUsername(username);
		clickSearch();
	}
	public void searchByUserRole(String role) {
		selectDropDown(userRoleDropdown,role);
		clickSearch();
	}
	public void searchByStatus(String role) {
		selectDropDown(userStatusDropDown,role);
		clickSearch();
	}
	
	public void refreshPage() {
		driver.navigate().refresh();
	}
	
	

}
