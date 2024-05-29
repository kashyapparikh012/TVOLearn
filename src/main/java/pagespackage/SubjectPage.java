package pagespackage;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import basepackage.BaseClass;

public class SubjectPage extends BaseClass {

	/* Element locators */
	@FindBy(xpath = "//h1")
	WebElement gradeHeading;
	@FindBy(xpath = "//h2")
	WebElement subjectHeading;
	@FindBy(xpath = "//div[@class='product-subject']/a")
	WebElement secondarySubjectHeading;
	@FindBy(xpath = "//div[@class='product-single__description rte']/div[@class='course-attr'][1]")
	WebElement secondaryGradeHeading;
	@FindBy(xpath = "//li[@class='current']")
	WebElement currentBreadcrumb;
	@FindBy(linkText = "Grade 1")
	WebElement gradeBreadcrumb;
	@FindBy(xpath = "//h1[@class='product-single__title']/span[@class='no-translate no-translate-en']")
	WebElement course;
	@FindBy(xpath = "//h2[contains(text(),'Learning Activities')]")
	WebElement learningActivitiesHeader;
	@FindBy(xpath = "//div[@class='tabd highlighted']/button")
	WebElement highlightedTab;
	@FindBy(xpath = "//div[@class='total_lessons']")
	WebElement totalLessons;
	@FindBy(xpath = "//div[@class='signup-inner']/h2")
	WebElement subscribeSectionHeading;
	@FindBy(xpath = "//div[@class='signup-inner']/p")
	WebElement subscribeSectionContent;
	@FindBy(id = "mc-embedded-subscribe")
	WebElement subscribeButton;
	@FindBy(xpath = "//div[@class='mce_inline_error']")
	WebElement subscribeInlineError;
	@FindBy(id = "mce-EMAIL")
	WebElement email;
	@FindBy(id = "mce-success-response")
	WebElement successResponse;
	@FindBy(id = "mce-error-response")
	WebElement errorResponse;
	@FindBy(linkText = "Privacy Notice")
	WebElement privacyNoticeFooter;
	@FindBy(linkText = "Terms of Use")
	WebElement termsFooter;
	@FindBy(linkText = "Accessibility")
	WebElement accessibilityFooter;
	@FindBy(linkText = "FAQ")
	WebElement faqFooter;
	public String resourcesLink = "//ul[@id='resources']/li/a";

	public SubjectPage() {
		PageFactory.initElements(driver, this);
	}

	/*
	 * Method to get the title of the page
	 * 
	 * @return the title as a String
	 */
	public String getPageTitle() {
		return driver.getTitle();
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
	 * Method to get the primary grade heading of the page
	 * 
	 * @return the primary grade heading as a String
	 */
	public String getPrimaryGradeHeading() {
		return gradeHeading.getText();
	}

	/*
	 * Method to get the primary subject heading of the page
	 * 
	 * @return the primary subject heading as a String
	 */
	public String getPrimarySubjectHeading() {
		return subjectHeading.getText();
	}

	/*
	 * Method to get the secondary subject heading of the page
	 * 
	 * @return the secondary subject heading as a String
	 */
	public String getSecondarySubjectHeading() {
		return secondarySubjectHeading.getText();
	}

	/*
	 * Method to get the secondary grade heading of the page
	 * 
	 * @return the secondary grade heading as a String
	 */
	public String getSecondaryGradeHeading() {
		return secondaryGradeHeading.getText();
	}

	/*
	 * Method to get the text of the current breadcrumb
	 * 
	 * @return The text of the current breadcrumb as a String
	 */
	public String getCurrentBreadcrumb() {
		return currentBreadcrumb.getText();
	}

	/*
	 * Method to click on the grade breadcrumb specified by the parameter
	 * 
	 * @param grade The grade to click on
	 */
	public void clickOnGradeBreadcrumb(String grade) {
		driver.findElement(By.linkText(grade)).click();
		;
	}

	/*
	 * Method to get the text of the grade heading
	 * 
	 * @return The text of the grade heading as a String
	 */
	public String getSubjectCode() {
		return gradeHeading.getText();
	}

	/*
	 * Method to get the text of the course
	 * 
	 * @return The text of the course as a String
	 */
	public String getCourse() {
		return course.getText();
	}

	/*
	 * Method to get the code of the course
	 * 
	 * @return The code of the course as a String
	 */
	public String getCourseCode() {
		String s = gradeHeading.getText();
		int startIndex = s.indexOf('(');
		int endIndex = s.indexOf(')', startIndex) + 1;
		String extractedCourseCode = null;

		if (startIndex != -1 && endIndex != -1) {
			extractedCourseCode = s.substring(startIndex, endIndex);

		} else {
			System.out.println("Course code not found");
		}
		return extractedCourseCode; // This will print (EAE1D)
	}

	/*
	 * Method to check if the learning activities section is displayed
	 * 
	 * @return true if the learning activities section is displayed, false otherwise
	 */
	public boolean learningActivitiesSection() {
		return learningActivitiesHeader.isDisplayed();
	}

	/*
	 * Method to get the list of learning activities tabs
	 * 
	 * @return The list of learning activities tabs as a List of WebElements
	 */
	public List<WebElement> getLearningActivitiesTabs() {
		return driver.findElements(By.xpath("//section[@id='strand-tabs']//button"));
	}

	/*
	 * Method to get the text of the highlighted tab
	 * 
	 * @return The text of the highlighted tab as a String
	 */
	public String getHighlightedTab() {
		return highlightedTab.getText();
	}

	/*
	 * Method to click on the tab specified by the parameter
	 * 
	 * @param tabName The name of the tab to click on
	 */
	public void clickOnTab(String tabName) {
		driver.findElement(By.xpath("//button[contains(text(),'" + tabName + "')]")).click();
	}

	/*
	 * Method to get the learning activities under the specified tab
	 * 
	 * @param tabName The name of the tab
	 * 
	 * @return The list of learning activities as a List of WebElements
	 */
	public List<WebElement> getLearningActivity(String tabName) {
		return driver
				.findElements(By.xpath("//button[text()='" + tabName + "']/parent::div//div[@class='tablas']/div)"));

	}

	/*
	 * Method to get all the WebElements matching the provided XPath expression
	 * 
	 * @param xpath The XPath expression
	 * 
	 * @return The list of WebElements matching the XPath
	 */
	public List<WebElement> getTotalResourceLinks(String xpath) {
		return driver.findElements(By.xpath(xpath));
	}

	/*
	 * Method to get all the resource images under the 'resources' list
	 * 
	 * @return The list of resource images as a List of WebElements
	 */
	public List<WebElement> totalResourceImages() {
		return driver.findElements(By.xpath("//ul[@id='resources']/li/a/div/img"));
	}

	/*
	 * Method to get the total number of lessons
	 * 
	 * @return The total number of lessons as an integer
	 */
	public int getTotalLessons() {
		String totalLessonsString = totalLessons.getText();
		return Integer.parseInt(totalLessonsString.substring(0, 2));
	}

	/*
	 * Method to calculate the total count of lessons within units
	 * 
	 * @return The total count of lessons within units as an integer
	 */
	public int getUnitLessonsCount() {
		List<WebElement> unitLessons = driver.findElements(By.xpath("//h3//div[@class='lesson_count_col']"));
		int totalUnitLessonscount = 0;
		for (WebElement lesson : unitLessons) {
			int count = Integer.parseInt(lesson.getText().substring(0, 2).trim());
			totalUnitLessonscount = totalUnitLessonscount + count;

		}
		return totalUnitLessonscount;
	}

	/*
	 * Method to verify if links and images are broken
	 * 
	 * @param elements The list of WebElements to be verified
	 * 
	 * @param property The property to be checked for each element
	 * 
	 * @return true if all links and images are not broken, false otherwise
	 */
	public boolean verifyBrokenLinksAndImages(List<WebElement> elements, String property) {
		boolean flag = false;
		for (WebElement element : elements) {
			flag = !(element.getAttribute(property).equals(null));
			if (flag == false) {
				System.out.println("!!! Broken Link Found for" + " : " + element.getText());
				break;
			}
		}
		return flag;
	}

	/*
	 * Method to retrieve the heading text of the subscribe section
	 * 
	 * @return The heading text of the subscribe section as a String
	 */
	public String getSubscribeSectionHeading() {
		return subscribeSectionHeading.getText();
	}

	/*
	 * Method to retrieve the content text of the subscribe section
	 * 
	 * @return The content text of the subscribe section as a String
	 */
	public String getSubscribeSectionContent() {
		return subscribeSectionContent.getText();
	}

	/*
	 * Method to click the subscribe button
	 */
	public void clickSubscribeButton() {
		subscribeButton.click();
	}

	/*
	 * Method to retrieve the inline error message after attempting to subscribe
	 * 
	 * @return The inline error message as a String
	 */
	public String getSubscribeInlineError() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try{
			wait.until(ExpectedConditions.visibilityOf(subscribeInlineError));
		}catch (Exception e){
			logger.error(e.getMessage()+" ##Element not found for inline error");
		}
		
		return subscribeInlineError.getText();
	}

	/*
	 * Method to clear the email input field
	 */
	public void clearEmail() {
		email.clear();
	}

	/*
	 * Method to enter the provided email address into the email input field
	 * 
	 * @param emailID The email address to enter
	 */
	public void enterEmail(String emailID) {
		email.sendKeys(emailID);
	}

	/*
	 * Method to retrieve the error response message
	 * 
	 * @return The error response message as a String
	 */
	public String getErrorResponse() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try{
			wait.until(ExpectedConditions.visibilityOf(errorResponse));
		}catch (Exception e){
			logger.error(e.getMessage()+" ##Element not found for error response");
		}
		return errorResponse.getText();

	}

	/*
	 * Method to retrieve the success response message
	 * 
	 * @return The success response message as a String
	 */
	public String getSuccessResponse() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try{
			wait.until(ExpectedConditions.visibilityOf(successResponse));
		}catch (Exception e){
			logger.error(e.getMessage()+" ##Element not found for success response");
		}
		
		return successResponse.getText();
	}

	/*
	 * Method to click on the privacy notice link in the footer
	 */
	public void clickPrivacyNoticeFooter() {
		privacyNoticeFooter.click();
	}

	/*
	 * Method to click on the terms link in the footer
	 */
	public void clickTermsFooter() {
		termsFooter.click();
	}

	/*
	 * Method to click on the accessibility link in the footer
	 */
	public void clickAccessibilityFooter() {
		accessibilityFooter.click();
	}

	/*
	 * Method to click on the FAQ link in the footer
	 */
	public void clickFaqFooter() {
		faqFooter.click();
	}

}
