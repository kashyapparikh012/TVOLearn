package pagespackage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import basepackage.BaseClass;

public class PrivacyNoticePage extends BaseClass {
	/* Element locators */
    @FindBy(xpath = "//h1")
    WebElement privacyNoticePageHeading;

    public PrivacyNoticePage() {
        PageFactory.initElements(driver, this);
    }

	/*
	 * Method to get the current URL of the page
	 * 
	 * @return the current URL as a String
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
     * Method to get the text of the Privacy Notice page heading 
     * @return the text of the heading as a String 
     */
    public String getPrivacyNoticePageHeading() {
        return privacyNoticePageHeading.getText();
    }
}
