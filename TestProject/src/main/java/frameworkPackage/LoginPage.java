package frameworkPackage;

import org.openqa.selenium.By;

/**
 * This page contains methods related to Login page
 * 
 * @author : Priyanka
 */

public class LoginPage extends ElementClass {

	private By errorBy = By.cssSelector("#error");
	private By loginBtnBy = By.cssSelector("#Login");
	private By passwordBy = By.cssSelector("#password");
	private By userNameBy = By.cssSelector("#username");

	/**
	 * Wait for page to load properly
	 */
	public LoginPage waitForPageToLoad() {
		waitForVisible(loginBtnBy);
		waitForVisible(userNameBy);
		return this;
	}

	/**
	 * Enter User name
	 * 
	 * @param name
	 *            : name of user
	 * @return : login page
	 */
	public LoginPage enterUserName(String name) {
		waitForPageToLoad();
		enterData(userNameBy, name);
		return this;
	}

	/**
	 * Enter the password
	 * 
	 * @param pwd
	 *            : user's password
	 * @return : login page
	 */
	public LoginPage enterPassword(String pwd) {
		waitForPageToLoad();
		enterData(passwordBy, pwd);
		return this;
	}

	/**
	 * Click on login button and wait for error's visibility
	 * 
	 * @return : login page
	 */
	public LoginPage clickLoginButtonWithError() {
		waitForPageToLoad();
		click(loginBtnBy);
		waitForVisible(errorBy);
		return this;
	}

	/**
	 * Verify that error is displayed or not
	 * 
	 * @return true/false
	 */
	public boolean verifyErrorIsDisplayed() {
		waitForPageToLoad();
		return isDisplayed(errorBy);
	}
}