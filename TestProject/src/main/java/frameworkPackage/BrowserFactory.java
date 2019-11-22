package frameworkPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * This page instantiate the driver for specified browser
 * 
 * @author : Priyanka
 */
public class BrowserFactory {

	private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

	/**
	 * Private constructor to avoid instantiation
	 */
	private BrowserFactory() {
	}

	/**
	 * Get the instance of specified browser
	 * 
	 * @param browserName
	 *            : name of browser
	 * @return : web driver of browser
	 */
	public static void setDriver(String browserName) {
		if (webDriver.get() == null) {
			if (browserName.equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
				webDriver.set(new ChromeDriver());
			} else if (browserName.equalsIgnoreCase("Firefox")) {
				System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
				DesiredCapabilities capabilities = DesiredCapabilities.firefox();
				capabilities.setCapability("marionette", true);
				webDriver.set(new FirefoxDriver(capabilities));
			}
		}
	}

	/**
	 * Get web driver
	 * 
	 * @return : web driver
	 */
	public static WebDriver getDriver() {
		return webDriver.get();
	}
}