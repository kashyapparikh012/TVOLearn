package pagespackage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import basepackage.BaseClass;

public class TermsPage extends BaseClass {
	/* Element locators */
	@FindBy(xpath = "//h1")
	WebElement termsPageHeading;
	
	public TermsPage() {
		PageFactory.initElements(driver, this);
	}

	/*
	 * Method to get the URL of the current page
	 * 
	 * @return the current page URL as a String
	 */
	public String getPageUrl() {
		return driver.getCurrentUrl();
	}

	/*
	 * Method to navigate back to the previous page
	 */
	public void navigateBack() {
		driver.navigate().back();
	}

	/*
	 * Method to get the text of the heading on the terms page
	 * 
	 * @return the text of the heading as a String
	 */
	public String getTermsPageHeading() {
		return termsPageHeading.getText();
	}
}
