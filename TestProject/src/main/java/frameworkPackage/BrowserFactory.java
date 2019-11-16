package frameworkPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import frameworkPackage.util.Util;

/**
 * This page instantiate the driver for specified browser
 * 
 * @author : Priyanka
 */
public class BrowserFactory {

	private static WebDriver driver;

	/**
	 * Private constructor to avoid instantiation
	 */
	private BrowserFactory() {}

	/**
	 * Get the instance of specified browser
	 * 
	 * @param browserName
	 *            : name of browser
	 * @return : web driver of browser
	 */
	private static WebDriver getDriver(String browserName) {
		if (browserName.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
			driver = new FirefoxDriver(capabilities);
		}
		driver.manage().window().maximize();
		return driver;
	}
	
	public static WebDriver getInstance() {
		if(driver == null) {
			// Fetching browser details
			String browser = Util.readDataFromPropertiesFile("browser");
			Util.logInfo("Using " + browser);
			
			driver = getDriver(browser);
		}
		
		return driver;
	}
}