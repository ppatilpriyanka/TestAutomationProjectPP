package testPackage;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import frameworkPackage.DriverManager;
import frameworkPackage.util.Util;

/**
 * This page contains TestNG annotations and implementations
 * 
 * @author : Priyanka
 */

public class BasicTest {

	@BeforeClass
	public void setUp() {
		WebDriver driver = DriverManager.getInstance();
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

	@AfterClass
	public void cleanUp() {
		Util.logInfo("Quiting Browser");
		DriverManager.quitDriver();
	}

	public void verifySafely(Object expected, Object actual, String message) {
		Util.verifySafely(expected, actual, message);
	}
}