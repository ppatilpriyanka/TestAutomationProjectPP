package testPackage;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import frameworkPackage.DriverManager;
import frameworkPackage.util.Util;

/**
 * This page contains TestNG annotations and implementations
 * 
 * @author : Priyanka
 */

public class BasicTest {

	private static WebDriver driver = DriverManager.getInstance();

	// @BeforeSuite
	// public void beforeSuite() {
	// logger.info("in @BeforeSuite");
	// }
	//
	// @BeforeClass
	// public void beforeClass() {
	// logger.info("in @BeforeClass");
	// }

	@BeforeTest
	public void beforeTest(ITestContext context) {
		// Fetching URL
        String url = Util.readDataFromPropertiesFile("url");
		Util.logInfo("Automating " + url);
		driver.get(url);
	}

	@BeforeMethod
	public void beforeMethod(ITestResult result) {
		Util.logInfo("===== Start execution for testcase : " + result.getMethod().getMethodName() + " =====");
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		Util.logInfo("===== Completed execution for testcase : " + result.getMethod().getMethodName() + " =====");
	}

	@AfterTest
	public void afterTest() {
		driver.close();
	}

	// @AfterClass
	// public void afterClass() {
	// System.out.println("in @afterClass");
	// }

	@AfterSuite
	public void afterSuite() {
		driver.quit();
		driver = null;
	}

	public void verifySafely(Object expected, Object actual, String message) {
		Util.verifySafely(expected, actual, message);
	}
}