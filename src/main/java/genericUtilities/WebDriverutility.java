package genericUtilities;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This class contains all reusable methods to perform driver related operations
 * 
 * @author Naveen
 */

public class WebDriverutility {

	WebDriver driver;

	/**
	 * This Method is used to launch specified browser and maximize it
	 * 
	 * @param browser
	 * @return
	 */

	public WebDriver launchBrowser(String browser) {
		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			System.setProperty("webdriver.edge.driver", "./src/main/resources/msedgedriver.exe");
			driver = new EdgeDriver();
			break;
		default:
			System.out.println("Invalid browser info");
		}
		driver.manage().window().maximize();
		return driver;
	}

	/**
	 * This method is used to specified url
	 * 
	 * @param url
	 */

	public void navigateToApp(String url) {
		driver.get(url);
	}

	/**
	 * This Method is an implicit wait statement waits until element is found
	 * 
	 * @param time
	 */

	public void waitTillElementFound(long time) {

		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

	/**
	 * This method is an implicit wait statement which waits until element is found
	 * 
	 * @param time
	 * @param element
	 * @return
	 */

	public WebElement explicitWait(long time, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * This method waits until element on the web page is enabled to recieve click
	 * 
	 * @param element
	 * @param time
	 * @return
	 */
	public WebElement explicitWait(WebElement element, long time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * This method is used to wait title appears on the web page
	 * 
	 * @param time
	 * @param title
	 * @return
	 */
	public Boolean explicitWait(long time, String title) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.titleContains(title));
	}

	/**
	 * This method is used to mouse hover on an element
	 * 
	 * @param element
	 */

	public void mouseHoverToElement(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}

	/**
	 * This method is used to double click on an element
	 * 
	 * @param element
	 */
	public void doubleClickonElement(WebElement element) {
		Actions action = new Actions(driver);
		action.doubleClick(element).perform();
	}

	/**
	 * This method is used to perform right click on on element
	 * 
	 * @param element
	 */

	public void rightClick(WebElement element) {
		Actions action = new Actions(driver);
		action.contextClick(element).perform();
	}

	/**
	 * This method is used to drag and drop an element to target location
	 * 
	 * @param element
	 * @param target
	 */
	public void draAndDropAnElement(WebElement element, WebElement target) {
		Actions action = new Actions(driver);
		action.dragAndDrop(element, target).perform();
	}

	/**
	 * This method is used to switch to frame based on specified index
	 * 
	 * @param index
	 */
	public void switchToFrame(int index) {
		driver.switchTo().frame(index);
	}

	public void switchToFrame(String idOrName) {
		driver.switchTo().frame(idOrName);
	}

	/**
	 * This method is used to switch
	 * 
	 * @param frameElement
	 */
	public void switchToFrame(WebElement frameElement) {
		driver.switchTo().frame(frameElement);
	}

	/**
	 * This method is used to switch back to the default webpage
	 */
	public void switchBackFromFrame() {
		driver.switchTo().defaultContent();
	}

	/**
	 * This method is used to select an element from drop down on element index
	 * 
	 * @param element
	 * @param index
	 */
	public void selectFromDropdown(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	public void selectFromDropdown(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}

	public void selectFromDropdown(String text, WebElement element) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}

	public List<WebElement> getDropdownList(WebElement element) {
		Select select = new Select(element);
		return select.getOptions();
	}

	public void captureScreenshot(WebDriver driver, JavaUtility jutil, String className) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./Screenshot/" + className + "_" + jutil.getCurrentTime() + ".png");
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void scrollTillElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", element);
	}

	public void handleAlert(String status) {
		Alert a1 = driver.switchTo().alert();
		if (status.equalsIgnoreCase("ok"))
			a1.accept();
		else
			a1.dismiss();
	}

	public void switchToChildBrowser() {
		Set<String> windowIDs = driver.getWindowHandles();
		for (String window : windowIDs) {
			driver.switchTo().window(window);
		}
	}

	/**
	 * This method returns parent window address
	 * 
	 * @return
	 */
	public String getParentWindow() {
		return driver.getWindowHandle();
	}

	/**
	 * This method is used to switch to specified window
	 */
	public void switchToWindow(String windowID) {
		driver.switchTo().window(windowID);
	}

	/**
	 * This method is used to close the current tab
	 */
	public void closeBrowser() {
		driver.close();
	}

	/**
	 * This method is used to quit all the windows
	 */
	public void quitAllWindows() {
		driver.quit();
	}

	/**
	 * This Method converts dynamic xpath to WebElement
	 * 
	 * @param path
	 * @parama replaceData
	 * @return
	 */
	public WebElement convertPathWebElement(String path, String replaceData) {
		String requiredPath = String.format(path, replaceData);
		WebElement element = driver.findElement(By.xpath(requiredPath));
		return element;
	}
}
