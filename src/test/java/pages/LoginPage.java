package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utility.WaitUtil;

public class LoginPage {

	// members
	private WebDriver driver;

	// Locators
	private By usernameTextLocator = By.id("user-name");
	private By passwordTextLocator = By.id("password");
	private By loginButtonLocator = By.id("login-button");
	private By errorMessageLocator = By.cssSelector("h3[data-test='error']");

	// Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	// Actions/Methods
	private void enterUsername(String username) {
		WaitUtil.WaitForElementToBeClickable(driver, usernameTextLocator).sendKeys(username);
	}

	private void enterPassword(String password) {
		WaitUtil.WaitForElementToBeClickable(driver, passwordTextLocator).sendKeys(password);
	}

	private void clickLoginButton() {
		WaitUtil.WaitForElementToBeClickable(driver, loginButtonLocator).click();
	}

	public ProductsPage validLogin(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		clickLoginButton();
		return new ProductsPage(driver);
	}

	public LoginPage invalidLogin(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		clickLoginButton();
		return this;
	}

	public String getErrorMessage() {
		return WaitUtil.WaitForElementToBeVisible(driver, errorMessageLocator) .getText();
	}

	public boolean isLoginPageDisplayed() {
		return WaitUtil.WaitForElementToBeVisible(driver, loginButtonLocator).isDisplayed();
	}
}
