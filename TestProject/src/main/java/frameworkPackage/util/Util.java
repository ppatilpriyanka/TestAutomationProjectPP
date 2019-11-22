package frameworkPackage.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.Assert;

import frameworkPackage.DriverManager;
import frameworkPackage.ElementClass;

public class Util {
	
	private static Logger logger;
	private static Properties properties;

	public static void logInfo(String message) {
		StackTraceElement ste = new Throwable().getStackTrace()[1];
		logger = Logger.getLogger(ste.getClassName() + "#" + ste.getLineNumber());
		logger.info(message);
	}
	
    /**
     * Read the data from Util file
     * 
     * @param key
     *            : key whose value user wants to retrieve
     * @return : value as per specified key
     */
    public static String readDataFromPropertiesFile(String key) {
        properties = new Properties();
        String value = null;
        try (InputStream input = new FileInputStream("src/main/resources/data.properties")) {
            properties.load(input);
            value = properties.getProperty(key);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return value;
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
	public static void verifySafely(Object expected, Object actual, String message) {
		try {
			Assert.assertEquals(expected, actual);
			Util.logInfo(message);
		} catch (Exception ex) {
			Util.logInfo(message + " expected true but found false. Refer screenshot at location -> "
					+ System.getProperty("user.dir") + "\\screenshots\\" + System.currentTimeMillis()
					+ ".jpg. Application URL is " + DriverManager.getInstance().getCurrentUrl());
			new ElementClass().takeScreenshot();
			throw new AssertionError();
		}
	}
}
