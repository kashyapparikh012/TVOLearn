package testpackage;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import basepackage.BaseClass;
import pagespackage.AccessibilityPage;
import pagespackage.FaqPage;
import pagespackage.ForTeachersPage;
import pagespackage.GlobalPage;
import pagespackage.GradePage;
import pagespackage.HomePage;
import pagespackage.NbaPage;
import pagespackage.PrivacyNoticePage;
import pagespackage.SubjectPage;
import pagespackage.TermsPage;
import listenerpackage.ExtentReportManager;

@Listeners(ExtentReportManager.class)
public class TVOLearnTests extends BaseClass {
	GlobalPage global;
	GradePage grade;
	SubjectPage subject;
	HomePage home;
	NbaPage nba;
	ForTeachersPage forteachers;
	PrivacyNoticePage privacy;
	TermsPage terms;
	AccessibilityPage accessibility;
	FaqPage faq;
	
	/*
	 * Constructor for TVOLearnTests class Invokes the superclass constructor
	 */
	public TVOLearnTests() {
		super();
	}

	/*
	 * Method executed before each test method to initialize setup Initializes the
	 * browser and creates instances of page objects
	 * 
	 * @param browserName The name of the browser to be used for testing
	 */
	@Parameters("browserName")
	@BeforeMethod
	public void initSetup(@Optional String browserName) {
		initiateBrowser(browserName); // Initializing the browser
		global = new GlobalPage(); // Creating instance of GlobalPage
		grade = new GradePage(); // Creating instance of GradePage
		subject = new SubjectPage(); // Creating instance of SubjectPage
		home = new HomePage(); // Creating instance of HomePage
		nba = new NbaPage(); // Creating instance of NbaPage
		forteachers = new ForTeachersPage(); // Creating instance of ForTeachersPage
		privacy = new PrivacyNoticePage(); // Creating instance of PrivacyNoticePage
		terms = new TermsPage(); // Creating instance of TermsPage
		accessibility = new AccessibilityPage(); // Creating instance of AccessibilityPage
		faq = new FaqPage(); // Creating instance of FaqPage
	}

	/*
	 * Test case to validate navigation to subject pages for different grades
	 */
	@Test(priority = 1)
	public void verifyNavigationToSubjectPageTest() {

		SoftAssert softAssert = new SoftAssert(); // Assertion object initialization

		// Loop for grades 1 to 8
		for (int i = 1; i <= 8; i++) {
			global.clickLearningResourcesDropdown(); // Clicking on Learning Resources dropdown
			global.selectGrade(String.valueOf(i)); // Selecting the grade
			grade.selectSubjectCard("Mathematics"); // Selecting Mathematics subject

			// Asserting page title and URL for Mathematics subject
			softAssert.assertEquals(subject.getPageTitle(),
					"Grade " + String.valueOf(i) + " - Mathematics | TVO Learn");
			softAssert.assertEquals(subject.getPageUrl(),
					"https://tvolearn.com/pages/grade-" + String.valueOf(i) + "-mathematics");
		}

		// Loop for grades 9 to 12
		for (int i = 9; i <= 12; i++) {
			global.clickLearningResourcesDropdown(); // Clicking on Learning Resources dropdown
			global.selectGrade(String.valueOf(i)); // Selecting the grade
			grade.selectSubjectCard("English"); // Selecting English subject

			// Asserting page title and URL for English subject
			softAssert.assertEquals(subject.getPageTitle(),
					subject.getCourse() + " " + subject.getCourseCode() + " | Online Courses | TVO Learn");
			softAssert.assertEquals(subject.getPageUrl(),
					"https://tvolearn.com/collections/courses/products/"
							+ subject.getCourseCode().replaceAll("[()]", "").toLowerCase() + "-"
							+ subject.getCourse().toLowerCase() + "-online-course");

		}
		softAssert.assertAll(); // Verifying all assertions
	}

	/*
	 * Test case to validate headings on subject pages for different grades
	 */
	@Test(priority = 2)
	public void verifyHeadingsOnSubjectPageTest() {
		// Loop for grades 1 to 8
		for (int i = 1; i <= 8; i++) {
			global.clickLearningResourcesDropdown(); // Clicking on Learning Resources dropdown
			global.selectGrade(String.valueOf(i)); // Selecting the grade
			grade.selectSubjectCard("Mathematics"); // Selecting Mathematics subject
			SoftAssert softAssert = new SoftAssert(); // Assertion object initialization

			// Asserting primary grade and subject headings
			softAssert.assertEquals(subject.getPrimaryGradeHeading(), "Grade " + String.valueOf(i));
			softAssert.assertEquals(subject.getPrimarySubjectHeading(), "Mathematics");
			softAssert.assertAll(); // Verifying all assertions
		}

		// Loop for grades 9 to 12
		for (int i = 9; i <= 12; i++) {
			global.clickLearningResourcesDropdown(); // Clicking on Learning Resources dropdown
			global.selectGrade(String.valueOf(i)); // Selecting the grade
			grade.selectSubjectCard("English"); // Selecting English subject
			SoftAssert softAssert = new SoftAssert(); // Assertion object initialization

			// Asserting secondary subject and grade headings
			softAssert.assertEquals(subject.getSecondarySubjectHeading(), "English");
			softAssert.assertEquals(subject.getSecondaryGradeHeading(), "Grade: " + String.valueOf(i));
			softAssert.assertAll(); // Verifying all assertions
		}
	}

	/*
	 * Test case to validate breadcrumb menu items navigation
	 */
	@Test(priority = 3)
	public void verifyBreadcrumbMenuItems() {
		// Navigation for Grade 1 Mathematics
		global.clickLearningResourcesDropdown(); // Clicking on Learning Resources dropdown
		global.selectGrade("1"); // Selecting grade 1
		grade.selectSubjectCard("Mathematics"); // Selecting Mathematics subject
		SoftAssert softAssert = new SoftAssert(); // Assertion object initialization

		// Asserting breadcrumb for Mathematics subject
		softAssert.assertEquals(subject.getCurrentBreadcrumb(), "Mathematics");
		subject.clickOnGradeBreadcrumb("Grade 1"); // Clicking on Grade 1 breadcrumb
		softAssert.assertEquals(grade.getCurrentBreadcrumb(), "Grade 1"); // Asserting current breadcrumb
		grade.navigateBack(); // Navigating back
		softAssert.assertEquals(subject.getCurrentBreadcrumb(), "Mathematics"); // Asserting current breadcrumb
		subject.clickOnGradeBreadcrumb("Home"); // Clicking on Home breadcrumb
		softAssert.assertEquals(home.getPageUrl(), "https://tvolearn.com/"); // Asserting home page URL

		// Navigation for Grade 9 English
		global.clickLearningResourcesDropdown(); // Clicking on Learning Resources dropdown
		global.selectGrade("9"); // Selecting grade 9
		grade.selectSubjectCard("English (EAE1D)"); // Selecting English subject
		softAssert.assertEquals(subject.getCurrentBreadcrumb(), "Grade 9 English (EAE1D)"); // Asserting breadcrumb for
																							// English subject
		subject.clickOnGradeBreadcrumb("Course Content"); // Clicking on Course Content breadcrumb
		softAssert.assertEquals(grade.getCurrentBreadcrumb(), "Courses"); // Asserting current breadcrumb
		grade.navigateBack(); // Navigating back
		softAssert.assertEquals(subject.getCurrentBreadcrumb(), "Grade 9 English (EAE1D)"); // Asserting current
																							// breadcrumb
		subject.clickOnGradeBreadcrumb("Home"); // Clicking on Home breadcrumb
		softAssert.assertEquals(home.getPageUrl(), "https://tvolearn.com/"); // Asserting home page URL

		softAssert.assertAll(); // Verifying all assertions
	}

	/*
	 * Test case to validate navigation of Collaboration and For Teachers from
	 * header
	 */
	@Test(priority = 4)
	public void verifyCollaborationAndForTeachersHeadersNavigation() {
		global.clickLearningResourcesDropdown(); // Clicking on Learning Resources dropdown
		global.selectGrade("1"); // Selecting grade 1
		grade.selectSubjectCard("Mathematics"); // Selecting Mathematics subject
		global.selectNBAFromCollaboration(); // Selecting NBA from Collaboration
		SoftAssert softAssert = new SoftAssert(); // Assertion object initialization

		// Asserting breadcrumb and page URL for NBA
		softAssert.assertEquals(nba.getCurrentBreadcrumb(), "NBA");
		softAssert.assertEquals(nba.getPageUrl(), "https://tvolearn.com/pages/nba");
		nba.navigateBack(); // Navigating back to previous page

		global.selectForTeachers(); // Selecting For Teachers from Learning Resources dropdown
		// Asserting breadcrumb and page URL for For Teachers
		softAssert.assertEquals(forteachers.getCurrentBreadcrumb(), "For Teachers");
		softAssert.assertEquals(forteachers.getPageUrl(), "https://tvolearn.com/pages/for_teachers");

		softAssert.assertAll(); // Verifying all assertions
	}

	/*
	 * Test case to validate navigation of Learning Resources from header
	 */
	@Test(priority = 5)
	public void verifyLearningResourcesNavigation() {
		global.clickLearningResourcesDropdown(); // Clicking on Learning Resources dropdown
		global.selectGrade("1"); // Selecting grade 1
		grade.selectSubjectCard("Mathematics"); // Selecting Mathematics subject

		global.clickLearningResourcesDropdown(); // Clicking on Learning Resources dropdown again
		global.selectGrade("3"); // Selecting grade 3
		SoftAssert softAssert = new SoftAssert(); // Assertion object initialization

		// Asserting breadcrumb and page URL for Grade 3
		softAssert.assertEquals(grade.getCurrentBreadcrumb(), "Grade 3");
		softAssert.assertEquals(grade.getPageUrl(), "https://tvolearn.com/pages/grade-3");
		grade.navigateBack(); // Navigating back to previous page

		// Asserting breadcrumb and page URL for Mathematics subject (Grade 1)
		softAssert.assertEquals(subject.getCurrentBreadcrumb(), "Mathematics");
		softAssert.assertEquals(subject.getPageUrl(), "https://tvolearn.com/pages/grade-1-mathematics");

		softAssert.assertAll(); // Verifying all assertions
	}

	/*
	 * Test case to validate Learning Activities section functionality
	 */
	@Test(priority = 6)
	public void verifyLearningActivitiesSectionTest() {
		global.clickLearningResourcesDropdown(); // Clicking on Learning Resources dropdown
		global.selectGrade("1"); // Selecting grade 1
		grade.selectSubjectCard("Mathematics"); // Selecting Mathematics subject

		SoftAssert softAssert = new SoftAssert(); // Assertion object initialization

		// Asserting presence and attributes of Learning Activities section
		softAssert.assertTrue(subject.learningActivitiesSection());
		softAssert.assertEquals(subject.getLearningActivitiesTabs().size(), 5);
		softAssert.assertEquals(subject.getHighlightedTab(), "Number");

		List<WebElement> activityTabs = subject.getLearningActivitiesTabs(); // Getting list of activity tabs
		// Iterating through each activity tab
		for (WebElement activityTab : activityTabs) {
			activityTab.click(); // Clicking on the activity tab
			String tabName = activityTab.getText(); // Getting tab name
			softAssert.assertEquals(subject.getHighlightedTab(), tabName); // Asserting highlighted tab
		}

		softAssert.assertAll(); // Verifying all assertions
	}

	/*
	 * Test case to validate resource links and images
	 */
	@Test(priority = 7)
	public void verifyResourceLinksAndImages() {
		global.clickLearningResourcesDropdown(); // Clicking on Learning Resources dropdown
		global.selectGrade("1"); // Selecting grade 1
		grade.selectSubjectCard("Mathematics"); // Selecting Mathematics subject

		// Getting list of resource links and images
		List<WebElement> links = subject.getTotalResourceLinks(subject.resourcesLink);
		List<WebElement> images = subject.getTotalResourceLinks(subject.resourcesLink.concat("/div/img"));

		SoftAssert softAssert = new SoftAssert(); // Assertion object initialization

		// Asserting equality of the number of links and images
		softAssert.assertEquals(links.size(), images.size());

		// Verifying broken links and images
		softAssert.assertEquals(subject.verifyBrokenLinksAndImages(links, "href"), true);
		softAssert.assertEquals(subject.verifyBrokenLinksAndImages(images, "src"), true);

		softAssert.assertAll(); // Verifying all assertions
	}

	/*
	 * Test case to validate total lessons for grades 9 to 12
	 */
	@Test(priority = 8)
	public void verifyTotalLessonsForGrade9To12() {
		global.clickLearningResourcesDropdown(); // Clicking on Learning Resources dropdown
		global.selectGrade("9"); // Selecting grade 9
		grade.selectSubjectCard("English (EAE1D)"); // Selecting English subject for grade 9

		// Asserting total lessons count and unit lessons count
		Assert.assertEquals(subject.getTotalLessons(), subject.getUnitLessonsCount());
	}

	/*
	 * Test case to validate subscribe section functionality
	 */
	@Test(priority = 9)
	public void verifySubscribeSection() {
		global.clickLearningResourcesDropdown(); // Clicking on Learning Resources dropdown
		global.selectGrade("1"); // Selecting grade 1
		grade.selectSubjectCard("Mathematics"); // Selecting Mathematics subject

		SoftAssert softAssert = new SoftAssert(); // Assertion object initialization

		// Asserting subscribe section heading and content
		softAssert.assertEquals(subject.getSubscribeSectionHeading(), "Stay Connected", "Subscribe Header is invalid");
		softAssert.assertEquals(subject.getSubscribeSectionContent(),
				"Sign up for the TVO Learn Newsletter. Discover engaging ways to support learning. Connects families to the Ontario 1-12 curriculum.",
				"Subscribe Content is invalid");

		// Clicking on subscribe button and verifying inline error message
		subject.clickSubscribeButton();
		softAssert.assertEquals(subject.getSubscribeInlineError(), "This field is required.",
				"Subscribe inline error message is invalid");

		// Entering email, clicking subscribe button, and verifying error response
		subject.enterEmail("abc@gmail.com");
		subject.clickSubscribeButton();
		softAssert.assertEquals(subject.getErrorResponse(),
				"This email cannot be added to this list. Please enter a different email address.",
				"Error response is invalid");

		// Clearing email, entering a valid email, clicking subscribe button, and
		// verifying success response
		subject.clearEmail();
		subject.enterEmail("kp.auxita@gmail.com");
		subject.clickSubscribeButton();
		softAssert.assertEquals(subject.getSuccessResponse(),
				"Almost finished... We need to confirm your email address. To complete the subscription process, please click the link in the email we just sent you.",
				"Success Response is invalid");

		softAssert.assertAll(); // Verifying all assertions
	}

	/*
	 * Test case to validate navigation of footer items
	 */
	@Test(priority = 10)
	public void verifyFooterItemsNavigation() {
		global.clickLearningResourcesDropdown(); // Clicking on Learning Resources dropdown
		global.selectGrade("1"); // Selecting grade 1
		grade.selectSubjectCard("Mathematics"); // Selecting Mathematics subject

		// Clicking on privacy notice footer item and verifying page URL and heading
		subject.clickPrivacyNoticeFooter();
		SoftAssert softAssert = new SoftAssert(); // Assertion object initialization
		softAssert.assertEquals(privacy.getPageUrl(), "https://tvolearn.com/pages/privacy-policy");
		softAssert.assertEquals(privacy.getPrivacyNoticePageHeading(), "Privacy Notice");
		privacy.navigateBack(); // Navigating back to previous page

		// Clicking on terms footer item and verifying page URL and heading
		subject.clickTermsFooter();
		softAssert.assertEquals(terms.getPageUrl(), "https://tvolearn.com/pages/terms-of-use");
		softAssert.assertEquals(terms.getTermsPageHeading(), "Terms of Use");
		terms.navigateBack(); // Navigating back to previous page

		// Clicking on accessibility footer item and verifying page URL and heading
		subject.clickAccessibilityFooter();
		softAssert.assertEquals(accessibility.getPageUrl(), "https://tvolearn.com/pages/accessibility");
		softAssert.assertEquals(accessibility.getAccessibilityPageHeading(),
				"TVO’s Digital Accessibility for Online Learning");
		accessibility.navigateBack(); // Navigating back to previous page

		// Clicking on FAQ footer item and verifying page URL and heading
		subject.clickFaqFooter();
		softAssert.assertEquals(faq.getPageUrl(), "https://tvolearn.com/pages/faq");
		softAssert.assertEquals(faq.getFaqPageHeading(), "FAQ");
		faq.navigateBack(); // Navigating back to previous page

		softAssert.assertAll(); // Verifying all assertions
	}

	/*
	 * Method executed after each test method to clean up resources Terminates the
	 * WebDriver instance
	 */
	@AfterMethod
	public void tearDown() {
		driver.quit(); // Quitting the WebDriver instance
	}

}
