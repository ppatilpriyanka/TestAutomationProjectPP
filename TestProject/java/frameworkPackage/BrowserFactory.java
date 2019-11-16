package frameworkPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {

	public static WebDriver driver;
	 
	private BrowserFactory(){
	}
	
	public WebDriver geDriver(String browserName) {
		if(browserName.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "../drivers/chromedriver.exe");
			 driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "../drivers/geckodriver.exe");
			 driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		return driver;
	}
}
