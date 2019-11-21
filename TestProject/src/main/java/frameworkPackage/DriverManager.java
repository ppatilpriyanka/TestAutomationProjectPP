package frameworkPackage;

import org.openqa.selenium.WebDriver;

import frameworkPackage.util.Util;

public class DriverManager {

	private static WebDriver driver;

	/**
	 * Get instance of browser with Thread Local class
	 * 
	 * @return : web driver's instance
	 */
	public static WebDriver getInstance() {
		BrowserFactory browserFact = BrowserFactory.getBrowserFactoryInstance();
		String browser = System.getProperty("browser");
		Util.logInfo("Using " + browser);
		browserFact.setDriver(browser);
		driver = browserFact.getDriver();
		driver.manage().window().maximize();
		return driver;
	}
}