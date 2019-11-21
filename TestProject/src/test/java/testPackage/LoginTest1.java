package testPackage;

import org.testng.annotations.Test;
import frameworkPackage.LoginPage;
import frameworkPackage.util.Util;

/**
 * This test contains validations regarding Login page
 * 
 * @author : Priyanka
 */

public class LoginTest1 extends BasicTest {

	private LoginPage loginPage;

	@Test
	public void verifyLoginErrorMessage1() {
		loginPage = new LoginPage();
		loginPage.enterUserName("terew").enterPassword("free").clickLoginButtonWithError();
		verifySafely(true, loginPage.verifyErrorIsDisplayed(), "On login error is displayed");
		Util.logInfo("Error message is displayed successfully");
	}

	@Test
	public void verifyEnterPasswordError1() {
		loginPage = new LoginPage();
		loginPage.enterUserName("pt.com").clickLoginButtonWithError();
		verifySafely(true, loginPage.verifyErrorIsDisplayed(), "On login error is displayed");
		Util.logInfo("Error message is displayed successfully");
	}
}