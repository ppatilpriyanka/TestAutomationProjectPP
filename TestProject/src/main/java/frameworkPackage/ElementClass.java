package frameworkPackage;

/**
 * This page contains common wrapper methods
 * 
 * @author : Priyanka
 */

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import testPackage.LoginTest;

public class ElementClass {

	private Logger logger = Logger.getLogger(LoginTest.class);
	private Properties properties;
	private WebDriver driver;

	/**
	 * Element Class's constructor
	 */
	public ElementClass() {
		driver = BrowserFactory.driver;
	}

	/**
	 * Wait for the DOM to load completely
	 */
	public void waitForDomToLoad() {
		try {
			WebDriverWait driverWait = new WebDriverWait(driver, 30);
			driverWait.until(ExpectedConditions.jsReturnsValue("return document.readyState=='complete';"));
		} catch (Exception ex) {
			takeScreenshotAndPrintExceptionTrace(ex);
		}
	}

	/**
	 * Wait for the visibility of element
	 * 
	 * @param locator
	 *            : locator of web element
	 */
	public void waitForVisible(By locator) {
		try {
			waitForDomToLoad();
			WebDriverWait driverWait = new WebDriverWait(driver, 30);
			driverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (Exception ex) {
			takeScreenshotAndPrintExceptionTrace(ex);
		}
	}

	/**
	 * Check visibility of web element
	 * 
	 * @param locator
	 *            : locator of web element
	 * @return true/false
	 */
	public boolean isDisplayed(By locator) {
		try {
			return driver.findElement(locator).isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * Enter data in provided web element
	 * 
	 * @param locator
	 *            : locator of web element
	 * @param value
	 *            : value of the element
	 */
	public void enterData(By locator, String value) {
		try {
			waitForDomToLoad();
			WebElement ele = driver.findElement(locator);
			ele.clear();
			ele.sendKeys(value);
		} catch (Exception ex) {
			takeScreenshotAndPrintExceptionTrace(ex);
		}
	}

	/**
	 * Click on the web element specified in locator
	 * 
	 * @param locator
	 *            : locator of web element
	 */
	public void click(By locator) {
		try {
			waitForDomToLoad();
			driver.findElement(locator).click();
		} catch (Exception ex) {
			takeScreenshotAndPrintExceptionTrace(ex);
		}
	}

	/**
	 * Read the data from Util file
	 * 
	 * @param key
	 *            : key whose value user wants to retrieve
	 * @return : value as per specified key
	 */
	public String readDataFromPropertiesFile(String key) {
		properties = new Properties();
		String value = null;
		try {
			// load a properties file
			properties.load(getClass().getClassLoader().getResourceAsStream("Util.properties"));
			value = properties.getProperty(key);
			logger.info(key + " : " + value);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return value;
	}

	/**
	 * This method take the screenshot of the web page
	 */
	public void takeScreenshot() {
		try {
			TakesScreenshot screenshot = ((TakesScreenshot) driver);
			File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
			File destFile = new File("./screenshots/" + System.currentTimeMillis() + ".jpg");
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Verify the expected and actual result using Assert
	 * 
	 * @param expected
	 *            : expected result
	 * @param actual
	 *            : actual result
	 * @param message
	 *            : message regarding verification
	 */
	public void verifySafely(Object expected, Object actual, String message) {
		try {
			Assert.assertEquals(expected, actual);
			logger.info(message);
		} catch (Exception ex) {
			logger.info(message + " expected true but found false. Refer screenshot at location -> "
					+ System.getProperty("user.dir") + "\\screenshots\\" + System.currentTimeMillis()
					+ ".jpg. Application URL is " + driver.getCurrentUrl());
			takeScreenshot();
			ex.printStackTrace();
		}
	}

	/**
	 * Take the screenshot and prints exception related details
	 * 
	 * @param ex
	 *            : exception object
	 */
	public void takeScreenshotAndPrintExceptionTrace(Exception ex) {
		takeScreenshot();
		logger.info("Failed to continue.... Refer screenshot at location -> " + System.getProperty("user.dir")
				+ "\\screenshots\\" + System.currentTimeMillis() + ".jpg. Application URL is "
				+ driver.getCurrentUrl());
		ex.printStackTrace();
	}
}