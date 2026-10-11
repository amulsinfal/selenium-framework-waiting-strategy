package pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utility.WaitUtil;

public class ProductsPage {

	// members
	private WebDriver driver;

	// Locators
	private By pageHeaderLocator = By.cssSelector("span[data-test='title']");
	private By productsLocator = By.cssSelector("div.inventory_item");
	private By productsNameLocator = By.cssSelector("div[data-test='inventory-item-name']");
	private By menuButtonLocator = By.id("react-burger-menu-btn");
	private By logoutLinkLocator = By.id("logout_sidebar_link");

	// Constructor
	public ProductsPage(WebDriver driver) {
		this.driver = driver;
	}

	// Actions/Methods
	public String getPageHeader() {
		return WaitUtil.WaitForElementToBeVisible(driver, pageHeaderLocator).getText();
	}

	public int getProductCount() {
		List<WebElement> allProducts = WaitUtil.WaitForAllElementsToBeVisible(driver, productsLocator); //driver.findElements(productsLocator);
		return allProducts.size();
	}

	public boolean isProductPresent(String expProductName) {
		List<WebElement> allProductsName = WaitUtil.WaitForAllElementsToBeVisible(driver, productsNameLocator); //driver.findElements(productsNameLocator);
		for (int i = 0; i < allProductsName.size(); i++) {
			if (allProductsName.get(i).getText().equalsIgnoreCase(expProductName)) {
				return true;
			}
		}
		return false;
	}

	public LoginPage clickLogout() {
		WaitUtil.WaitForElementToBeClickable(driver, menuButtonLocator).click();
		WaitUtil.WaitForElementToBeClickable(driver, logoutLinkLocator).click();
		return new LoginPage(driver);
	}
}
