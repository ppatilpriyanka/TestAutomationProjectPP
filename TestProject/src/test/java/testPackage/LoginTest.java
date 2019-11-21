package testPackage;

import org.testng.annotations.Test;
import frameworkPackage.LoginPage;
import frameworkPackage.util.Util;

/**
 * This test contains validations regarding Login page
 * 
 * @author : Priyanka
 */

public class LoginTest extends BasicTest {

	private LoginPage loginPage;

	@Test
	public void verifyLoginErrorMessage() {
		loginPage = new LoginPage();
		loginPage.enterUserName("qwerty").enterPassword("lpolpo").clickLoginButtonWithError();
		verifySafely(true, loginPage.verifyErrorIsDisplayed(), "On login error is displayed");
		Util.logInfo("Error message is displayed successfully");
	}

	@Test
	public void verifyEnterPasswordError() {
		loginPage = new LoginPage();
		loginPage.enterUserName("lpolpo").clickLoginButtonWithError();
		verifySafely(true, loginPage.verifyErrorIsDisplayed(), "On login error is displayed");
		Util.logInfo("Error message is displayed successfully");
	}
}
