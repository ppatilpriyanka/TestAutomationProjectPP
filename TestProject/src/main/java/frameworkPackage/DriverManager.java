package frameworkPackage;

import org.openqa.selenium.WebDriver;

public class DriverManager {
	
	// Restricts object creation
	private DriverManager() {}
	private static ThreadLocal<WebDriver> driverFactory = new ThreadLocal<WebDriver>();

	/**
	 * Get instance of browser with Thread Local class
	 * 
	 * @return : web driver's instance
	 */
	public static WebDriver getInstance() {
		if(driverFactory.get() == null) {
			WebDriver webDriver = null;
			driverFactory.set(BrowserFactory.initDriver(webDriver));
		}
		
		driverFactory.get().manage().window().maximize();
		return driverFactory.get();
	}
	
	
	public static void quitDriver() {
		if(driverFactory.get() != null) {
			driverFactory.get().close();
			driverFactory.get().quit();
			driverFactory.set(null);
		}
	}
}