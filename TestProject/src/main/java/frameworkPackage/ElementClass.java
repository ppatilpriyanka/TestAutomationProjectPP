package frameworkPackage;

/**
 * This page contains common wrapper methods
 * 
 * @author : Priyanka
 */

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import frameworkPackage.util.Util;

public class ElementClass {

	private WebDriver driver = DriverManager.getInstance();


	/**
	 * Wait for the DOM to load completely
	 */
	public void waitForDomToLoad() {
		try {
			WebDriverWait driverWait = new WebDriverWait(driver, 60);
			driverWait.until(ExpectedConditions.jsReturnsValue("return document.readyState=='complete';"));
		} catch (TimeoutException ex) {
			takeScreenshotAndPrintExceptionTrace(ex);
			throw ex;
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
		} catch (NoSuchElementException ex) {
			takeScreenshotAndPrintExceptionTrace(ex);
			throw ex;
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
		} catch (NoSuchElementException ex) {
			takeScreenshotAndPrintExceptionTrace(ex);
			throw ex;
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
		} catch (NoSuchElementException ex) {
			takeScreenshotAndPrintExceptionTrace(ex);
			throw ex;
		}
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
	 * Take the screenshot and prints exception related details
	 * 
	 * @param ex
	 *            : exception object
	 */
	public void takeScreenshotAndPrintExceptionTrace(Exception ex) {
		takeScreenshot();
		Util.logInfo("Failed to continue.... Refer screenshot at location -> " + System.getProperty("user.dir")
				+ "\\screenshots\\" + System.currentTimeMillis() + ".jpg. Application URL is "
				+ driver.getCurrentUrl());
		ex.printStackTrace();
	}
}