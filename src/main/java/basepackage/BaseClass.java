package basepackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import utility.TimeUtils;

public class BaseClass {

	public static Properties prop = new Properties();
	public static WebDriver driver;
	public static Logger logger = LogManager.getLogger(BaseClass.class);
	public static String defaultBrowser;
	
	public BaseClass() {
		try {
			FileInputStream file = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\environmentvariables\\config.properties");
			prop.load(file);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		defaultBrowser=prop.getProperty("default_browser");
	}

	/*
	 * Method to initialize the browser based on the provided browser name
	 * @param browserName the name of the browser to initialize (e.g., "Chrome" or
	 * "Firefox") This method does not return any value
	 */
	public static void initiateBrowser(String browserName) {

		if (browserName == null) {
			browserName = defaultBrowser;
		}	

		if (browserName.equalsIgnoreCase("Chrome")) {
			logger.info("Opening " + browserName + " Browser");
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("Firefox")) {
			logger.info("Opening " + browserName + " Browser");
			driver = new FirefoxDriver();
		}
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TimeUtils.pageTimeout));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TimeUtils.implicitWait));
	}

	/*
	 * Method to close the browser
	 */
	public static void closeBrowser() {
		logger.info("Closing "+defaultBrowser+" Browser");
		driver.quit();
	}
	
	/*
	 * Method to capture a screenshot with the given test method name
	 * @param testMethodName the name of the test method for the screenshot file
	 * @return the path to the saved screenshot file
	 */
	public static String getScreenshot(String testMethodName) {
		String dateName = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/FailedTestScreenshots/" + testMethodName + "_"
				+ dateName + ".png";
		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destination;
	}
	
	
	
	

}
