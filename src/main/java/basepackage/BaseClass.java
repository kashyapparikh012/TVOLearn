package basepackage;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import utility.TimeUtils;

public class BaseClass {
	/* Properties object to hold configuration data */
	public static Properties prop = new Properties();
	/* WebDriver object to interact with the browser */
	public static WebDriver driver;

	/* Constructor to load configuration properties */
	public BaseClass() {
		try {
			/* Load properties file from the specified path */
			FileInputStream file = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\environmentvariables\\config.properties");
			prop.load(file);
		} catch (Exception e) {
			/* Print stack trace if an exception occurs */
			e.printStackTrace();
		}
	}

	/*
	 * Method to initialize the browser based on the provided browser name
	 * 
	 * @param browserName the name of the browser to initialize (e.g., "Chrome" or
	 * "Firefox") This method does not return any value
	 */
	public static void initiateBrowser(String browserName) {
		/* If browserName is null, default to Chrome */
		if (browserName == null) {
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("Chrome")) {
			/* If browserName is "Chrome", initialize ChromeDriver */
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("Firefox")) {
			/* If browserName is "Firefox", initialize FirefoxDriver */
			driver = new FirefoxDriver();
		}

		/* Navigate to the URL specified in the properties file */
		driver.get(prop.getProperty("url"));
		/* Maximize the browser window */
		driver.manage().window().maximize();
		/* Set the page load timeout using the value from TimeUtils */
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TimeUtils.pageTimeout));
		/* Set the implicit wait timeout using the value from TimeUtils */
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TimeUtils.implicitWait));
	}
}
