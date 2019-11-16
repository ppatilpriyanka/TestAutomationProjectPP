package testPackage;

import org.apache.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import frameworkPackage.BrowserFactory;
import frameworkPackage.ElementClass;

/**
 * This page contains TestNG annotations and implementations
 * 
 * @author : Priyanka
 */

public class BasicTest {

	private ElementClass elementClass = new ElementClass();
	private Logger logger = Logger.getLogger(LoginTest.class);

	private static WebDriver driver;

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
		driver = BrowserFactory.getDriver(elementClass.readDataFromPropertiesFile("browser"));
		driver.get(elementClass.readDataFromPropertiesFile("url"));
	}

	@BeforeMethod
	public void beforeMethod(ITestResult result) {
		logger.info("*************** Start execution for testcase : " + result.getMethod().getMethodName()
				+ " ***************");
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		logger.info("*************** Completed execution for testcase : " + result.getMethod().getMethodName()
				+ " ***************");
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
	}

	public void verifySafely(Object expected, Object actual, String message) {
		elementClass.verifySafely(expected, actual, message);
	}
}