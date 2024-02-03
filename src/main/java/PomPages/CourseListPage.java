package PomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverutility;

public class CourseListPage {

	// Declaration
	@FindBy(xpath = "//h1[normalize-space(text())='Course List']")
	private WebElement pageHeader;

	@FindBy(xpath = "//a[text()=' New']")
	private WebElement newButton;

	String deletePath ="//td[text()='%s']/ancestor::tr" + "/descendanet::button[text()=' Delete']";

	@FindBy(xpath="//button[text()=' Delete'][1]")
	private WebElement deleteButton;

	@FindBy(xpath = "//h4[text()=' Success']")
	private WebElement successMessage;

	// Initialization
	public CourseListPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	public String getPageHeader() {
		return pageHeader.getText();
	}
	public void clickNewButton() {
		newButton.click();
	}
	public void deleteCourse(WebDriverutility web,String courseName) {
		 web.convertPathWebElement(deletePath,courseName).click();
		deleteButton.click();
	}
	public String getSuccessMessage() {
		return successMessage.getTagName();
	}
	}


