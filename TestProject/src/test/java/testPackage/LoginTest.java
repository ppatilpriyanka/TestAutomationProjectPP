package testPackage;

import org.testng.annotations.Test;
import frameworkPackage.LoginPage;
import frameworkPackage.util.Util;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

/**
 * This test contains validations regarding Login page
 * 
 * @author : Priyanka
 */
public class LoginTest extends BasicTest {

	private LoginPage loginPage;

	@Test
	@Description("Verify Error Message when User enters wrong credentials")
	@Severity(SeverityLevel.NORMAL)
	public void verifyLoginErrorMessage() {
		loginPage = new LoginPage();
		loginPage.enterUserName("qwerty").enterPassword("lpolpo").clickLoginButtonWithError();
		verifySafely(true, loginPage.verifyErrorIsDisplayed(), "On login error is displayed");
		Util.logInfo("Error message is displayed successfully");
	}

	@Test
	@Description("Verify Error Message when User doesn't add any password")
	@Severity(SeverityLevel.MINOR)
	public void verifyEnterPasswordError() {
		loginPage = new LoginPage();
		loginPage.enterUserName("lpolpo").clickLoginButtonWithError();
		verifySafely(true, loginPage.verifyErrorIsDisplayed(), "On login error is displayed");
		Util.logInfo("Error message is displayed successfully");
	}
}