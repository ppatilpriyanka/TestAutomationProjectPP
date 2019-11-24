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
	public static WebDriver initDriver(WebDriver webDriver) {
		Browser browser = Browser.valueOf(System.getProperty("browser").toUpperCase());
		Util.logInfo("Using " + browser);
		System.setProperty(browser.getProperty(), browser.getDriverPath());
		
		switch(browser) {
			case CHROME:
				webDriver = new ChromeDriver();
				break;
				
			case FIREFOX:
				DesiredCapabilities capabilities = DesiredCapabilities.firefox();
				capabilities.setCapability("marionette", true);
				webDriver = new FirefoxDriver(capabilities);
				break;
		}
		
		return webDriver;
	}
}

enum Browser {
	CHROME("webdriver.chrome.driver", "./drivers/chromedriver.exe"), 
	FIREFOX("webdriver.gecko.driver", "./drivers/geckodriver.exe");
	
	private String property;
	private String driverPath;
	
	Browser(String property, String driverPath) {
		this.property = property;
		this.driverPath = driverPath;
	}
	
	public String getProperty() {
		return this.property;
	}
	
	public String getDriverPath() {
		return this.driverPath;
	}
}
