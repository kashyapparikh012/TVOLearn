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
	
	
	public TVOLearnTests() {
		super();
	}

	/*
	 * Method executed before each test method to initialize setup Initializes the
	 * browser and creates instances of page objects
	 * @param browserName The name of the browser to be used for testing
	 */
	@Parameters("browserName")
	@BeforeMethod
	public void initSetup(@Optional String browserName) {
		initiateBrowser(browserName);
		global = new GlobalPage();
		grade = new GradePage();
		subject = new SubjectPage();
		home = new HomePage();
		nba = new NbaPage();
		forteachers = new ForTeachersPage();
		privacy = new PrivacyNoticePage();
		terms = new TermsPage();
		accessibility = new AccessibilityPage();
		faq = new FaqPage();
	}

	/*
	 * Test case to validate navigation to subject pages for different grades
	 */
	@Test(priority = 1)
	public void verifyNavigationToSubjectPageTest() {
		SoftAssert softAssert = new SoftAssert();
		for (int i = 1; i <= 8; i++) {
			global.clickLearningResourcesDropdown();
			global.selectGrade(String.valueOf(i));
			grade.selectSubjectCard("Mathematics");

			softAssert.assertEquals(subject.getPageTitle(),
					"Grade " + String.valueOf(i) + " - Mathematics | TVO Learn", "Page Title is invalid");
			softAssert.assertEquals(subject.getPageUrl(),
					"https://tvolearn.com/pages/grade-" + String.valueOf(i) + "-mathematics");
			softAssert.assertAll();
		}

		for (int i = 9; i <= 12; i++) {
			global.clickLearningResourcesDropdown();
			global.selectGrade(String.valueOf(i));
			grade.selectSubjectCard("English");

			softAssert.assertEquals(subject.getPageTitle(),
					subject.getCourse() + " " + subject.getCourseCode() + " | Online Courses | TVO Learn");
			softAssert.assertEquals(subject.getPageUrl(),
					"https://tvolearn.com/collections/courses/products/"
							+ subject.getCourseCode().replaceAll("[()]", "").toLowerCase() + "-"
							+ subject.getCourse().toLowerCase() + "-online-course");
			softAssert.assertAll();
		}
	}

	/*
	 * Test case to validate headings on subject pages for different grades
	 */
	@Test(priority = 2)
	public void verifyHeadingsOnSubjectPageTest() {
		SoftAssert softAssert = new SoftAssert();
		for (int i = 1; i <= 8; i++) {
			global.clickLearningResourcesDropdown();
			global.selectGrade(String.valueOf(i));
			grade.selectSubjectCard("Mathematics");
			softAssert.assertEquals(subject.getPrimaryGradeHeading(), "Grade " + String.valueOf(i));
			softAssert.assertEquals(subject.getPrimarySubjectHeading(), "Mathematics");
		}

		
		for (int i = 9; i <= 12; i++) {
			global.clickLearningResourcesDropdown();
			global.selectGrade(String.valueOf(i));
			grade.selectSubjectCard("English");
			softAssert.assertEquals(subject.getSecondarySubjectHeading(), "English");
			softAssert.assertEquals(subject.getSecondaryGradeHeading(), "Grade: " + String.valueOf(i));
		}
		softAssert.assertAll();
	}

	/*
	 * Test case to validate breadcrumb menu items navigation
	 */
	@Test(priority = 3)
	public void verifyBreadcrumbMenuItems() {
		
		global.clickLearningResourcesDropdown();
		global.selectGrade("1");
		grade.selectSubjectCard("Mathematics");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(subject.getCurrentBreadcrumb(), "Mathematics");
		subject.clickOnGradeBreadcrumb("Grade 1");
		softAssert.assertEquals(grade.getCurrentBreadcrumb(), "Grade 1");
		grade.navigateBack();
		softAssert.assertEquals(subject.getCurrentBreadcrumb(), "Mathematics");
		subject.clickOnGradeBreadcrumb("Home");
		softAssert.assertEquals(home.getPageUrl(), "https://tvolearn.com/");

		global.clickLearningResourcesDropdown();
		global.selectGrade("9");
		grade.selectSubjectCard("English (EAE1D)");
		softAssert.assertEquals(subject.getCurrentBreadcrumb(), "Grade 9 English (EAE1D)");
		subject.clickOnGradeBreadcrumb("Course Content");
		softAssert.assertEquals(grade.getCurrentBreadcrumb(), "Courses");
		grade.navigateBack();
		softAssert.assertEquals(subject.getCurrentBreadcrumb(), "Grade 9 English (EAE1D)");
		subject.clickOnGradeBreadcrumb("Home");
		softAssert.assertEquals(home.getPageUrl(), "https://tvolearn.com/");
	}

	/*
	 * Test case to validate navigation of Collaboration and For Teachers from
	 * header
	 */
	@Test(priority = 4)
	public void verifyCollaborationAndForTeachersHeadersNavigation() {
		global.clickLearningResourcesDropdown();
		global.selectGrade("1");
		grade.selectSubjectCard("Mathematics");
		global.selectNBAFromCollaboration();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(nba.getCurrentBreadcrumb(), "NBA");
		softAssert.assertEquals(nba.getPageUrl(), "https://tvolearn.com/pages/nba");
		nba.navigateBack();

		global.selectForTeachers();
		softAssert.assertEquals(forteachers.getCurrentBreadcrumb(), "For Teachers");
		softAssert.assertEquals(forteachers.getPageUrl(), "https://tvolearn.com/pages/for_teachers");
	}

	/*
	 * Test case to validate navigation of Learning Resources from header
	 */
	@Test(priority = 5)
	public void verifyLearningResourcesNavigation() {
		global.clickLearningResourcesDropdown();
		global.selectGrade("1");
		grade.selectSubjectCard("Mathematics");

		global.clickLearningResourcesDropdown();
		global.selectGrade("3");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(grade.getCurrentBreadcrumb(), "Grade 3");
		softAssert.assertEquals(grade.getPageUrl(), "https://tvolearn.com/pages/grade-3");
		grade.navigateBack();

		softAssert.assertEquals(subject.getCurrentBreadcrumb(), "Mathematics");
		softAssert.assertEquals(subject.getPageUrl(), "https://tvolearn.com/pages/grade-1-mathematics");

	}

	/*
	 * Test case to validate Learning Activities section functionality
	 */
	@Test(priority = 6)
	public void verifyLearningActivitiesSectionTest() {
		global.clickLearningResourcesDropdown();
		global.selectGrade("1");
		grade.selectSubjectCard("Mathematics");

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(subject.learningActivitiesSection());
		softAssert.assertEquals(subject.getLearningActivitiesTabs().size(), 5);
		softAssert.assertEquals(subject.getHighlightedTab(), "Number");

		List<WebElement> activityTabs = subject.getLearningActivitiesTabs();
		
		for (WebElement activityTab : activityTabs) {
			activityTab.click();
			String tabName = activityTab.getText();
			softAssert.assertEquals(subject.getHighlightedTab(), tabName);
		}
	}

	/*
	 * Test case to validate resource links and images
	 */
	@Test(priority = 7)
	public void verifyResourceLinksAndImages() {
		global.clickLearningResourcesDropdown();
		global.selectGrade("1");
		grade.selectSubjectCard("Mathematics");

		List<WebElement> links = subject.getTotalResourceLinks(subject.resourcesLink);
		List<WebElement> images = subject.getTotalResourceLinks(subject.resourcesLink.concat("/div/img"));

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(links.size(), images.size());
		softAssert.assertEquals(subject.verifyBrokenLinksAndImages(links, "href"), true);
		softAssert.assertEquals(subject.verifyBrokenLinksAndImages(images, "src"), true);
	
	}

	/*
	 * Test case to validate total lessons for grades 9 to 12
	 */
	@Test(priority = 8)
	public void verifyTotalLessonsForGrade9To12() {
		global.clickLearningResourcesDropdown();
		global.selectGrade("9");
		grade.selectSubjectCard("English (EAE1D)");
		Assert.assertEquals(subject.getTotalLessons(), subject.getUnitLessonsCount());
	}

	/*
	 * Test case to validate subscribe section functionality
	 */
	@Test(priority = 9)
	public void verifySubscribeSection() {
		global.clickLearningResourcesDropdown();
		global.selectGrade("1");
		grade.selectSubjectCard("Mathematics");

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(subject.getSubscribeSectionHeading(), "Stay Connected", "Subscribe Header is invalid");
		softAssert.assertEquals(subject.getSubscribeSectionContent(),
				"Sign up for the TVO Learn Newsletter. Discover engaging ways to support learning. Connects families to the Ontario 1-12 curriculum.",
				"Subscribe Content is invalid");
		subject.clickSubscribeButton();
		softAssert.assertEquals(subject.getSubscribeInlineError(), "This field is required.",
				"Subscribe inline error message is invalid");
		subject.enterEmail("abc@gmail.com");
		subject.clickSubscribeButton();
		softAssert.assertEquals(subject.getErrorResponse(),
				"This email cannot be added to this list. Please enter a different email address.",
				"Error response is invalid");

		subject.clearEmail();
		subject.enterEmail("kp.auxita@gmail.com");
		subject.clickSubscribeButton();
		softAssert.assertEquals(subject.getSuccessResponse(),
				"Almost finished... We need to confirm your email address. To complete the subscription process, please click the link in the email we just sent you.",
				"Success Response is invalid");

	}

	/*
	 * Test case to validate navigation of footer items
	 */
	@Test(priority = 10)
	public void verifyFooterItemsNavigation() {
		global.clickLearningResourcesDropdown();
		global.selectGrade("1");
		grade.selectSubjectCard("Mathematics");

		subject.clickPrivacyNoticeFooter();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(privacy.getPageUrl(), "https://tvolearn.com/pages/privacy-policy");
		softAssert.assertEquals(privacy.getPrivacyNoticePageHeading(), "Privacy Notice");
		privacy.navigateBack();

		subject.clickTermsFooter();
		softAssert.assertEquals(terms.getPageUrl(), "https://tvolearn.com/pages/terms-of-use");
		softAssert.assertEquals(terms.getTermsPageHeading(), "Terms of Use");
		terms.navigateBack();
		subject.clickAccessibilityFooter();
		softAssert.assertEquals(accessibility.getPageUrl(), "https://tvolearn.com/pages/accessibility");
		softAssert.assertEquals(accessibility.getAccessibilityPageHeading(),
				"TVO’s Digital Accessibility for Online Learning");
		accessibility.navigateBack();

		subject.clickFaqFooter();
		softAssert.assertEquals(faq.getPageUrl(), "https://tvolearn.com/pages/faq");
		softAssert.assertEquals(faq.getFaqPageHeading(), "FAQ");
		faq.navigateBack();

	}

	/*
	 * Method executed after each test method to clean up resources Terminates the
	 * WebDriver instance
	 */
	@Parameters("browserName")
	@AfterMethod
	public void tearDown(@Optional String browserName) {
		closeBrowser();
	}

}
