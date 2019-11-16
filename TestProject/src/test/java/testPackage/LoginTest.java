package testPackage;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import frameworkPackage.LoginPage;

/**
 * This test contains validations regarding Login page
 * 
 * @author : Priyanka
 */

public class LoginTest extends BasicTest {

	private LoginPage loginPage;
	private Logger logger = Logger.getLogger(LoginTest.class);

	@Test
	public void verifyLoginErrorMessage() {
		loginPage = new LoginPage();
		loginPage.enterUserName("qwerty").enterPassword("lpolpo").clickLoginButtonWithError();
		verifySafely(true, loginPage.verifyErrorIsDisplayed(), "On login error is displayed");
		logger.info("Error message is displayed successfully");
	}

	@Test
	public void verifyLogin() {
		logger.info("******Login1********");
	}
}
