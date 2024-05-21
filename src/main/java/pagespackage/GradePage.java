package pagespackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import basepackage.BaseClass;

public class GradePage extends BaseClass {

	/* Element locators */
	@FindBy(xpath = "//h1")
	WebElement gradeHeading;
	@FindBy(xpath = "//li[@class='current']")
	WebElement currentBreadcrumb;

	/* Constructor to initialize web elements on the page */
	public GradePage() {
		PageFactory.initElements(driver, this);
	}

	/*
	 * Method to select a subject card based on the provided subject name
	 * 
	 * @param subject the name of the subject to be selected
	 */
	public void selectSubjectCard(String subject) {
		WebElement subjectOption = driver.findElement(By.xpath("//div[contains(text(),'" + subject + "')]"));
		subjectOption.click();
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
