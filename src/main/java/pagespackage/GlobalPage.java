package pagespackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import basepackage.BaseClass;

public class GlobalPage extends BaseClass {

	/* Element locators */
    @FindBy(xpath = "//button[@aria-controls='SiteNavLabel-learning-resources-k-12']")
    WebElement learningResourcesDropdown;
    @FindBy(xpath = "//button[@aria-controls='SiteNavLabel-collaborations']")
    WebElement collaborations;
    @FindBy(linkText = "NBA")
    WebElement nba;
    @FindBy(linkText = "For Teachers")
    WebElement forTeachers;
    @FindBy(xpath = "//div[contains(text(),'Mathematics')]")
    WebElement subjectCard;

    /* Constructor to initialize web elements on the page */
    public GlobalPage() {
        PageFactory.initElements(driver, this);
    }

    /* 
     * Method to click on the learning resources dropdown button 
     */
    public void clickLearningResourcesDropdown() {
        learningResourcesDropdown.click();
    }

    /* 
     * Method to validate the header by getting the text of the learning resources dropdown button 
     */
    public void validateHeader() {
        learningResourcesDropdown.getText();
    }

    /* 
     * Method to select a grade from the learning resources dropdown 
     * @param grade the grade to be selected (e.g., "1", "2", etc.)
     */
    public void selectGrade(String grade) {
        WebElement gradeOption = driver.findElement(By.xpath("//div[@id='SiteNavLabel-learning-resources-k-12']//span[text()='Grade " + grade + "']"));
        gradeOption.click();
    }

    /* 
     * Method to navigate to the NBA collaboration page 
     */
    public void selectNBAFromCollaboration() {
        collaborations.click();
        nba.click();
    }

    /* 
     * Method to navigate to the "For Teachers" page 
     */
    public void selectForTeachers() {
        forTeachers.click();
    }
}
