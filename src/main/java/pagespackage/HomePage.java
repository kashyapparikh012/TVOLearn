package pagespackage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import basepackage.BaseClass;

public class HomePage extends BaseClass {

	/* Element locators */
	@FindBy(xpath = "//li[@class='current']")
	WebElement currentBreadcrumb;

	/* Constructor to initialize web elements on the page */
	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	/*
	 * Method to get the text of the current breadcrumb element
	 * 
	 * @return the text of the current breadcrumb as a String
	 */
	public String getCurrentBreadcrumb() {
		return currentBreadcrumb.getText();
	}

	/*
	 * Method to navigate back to the previous page
	 */
	public void navigateBack() {
		driver.navigate().back();
	}

	/*
	 * Method to get the current URL of the page
	 * 
	 * @return the current URL as a String
	 */
	public String getPageUrl() {
		return driver.getCurrentUrl();
	}
}
