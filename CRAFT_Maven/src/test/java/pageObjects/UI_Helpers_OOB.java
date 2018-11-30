package pageObjects;

import java.util.*;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.cognizant.framework.Report;
import com.cognizant.framework.Status;
import com.cognizant.framework.selenium.CraftDriver;

import com.cognizant.Craft.ReusableLibrary;
import com.cognizant.Craft.ScriptHelper;
import pageObjects.CRMCasePage;
import pageObjects.CRMLandingPage;
import pageObjects.CRMNewAccountRecordPage;

public class UI_Helpers_OOB extends ReusableLibrary {

	public UI_Helpers_OOB(ScriptHelper scriptHelper) {
		super(scriptHelper);
		// TODO Auto-generated constructor stub
	}

	static int maxTimeout = 40;
	
	public static final By salesHomeArrow = By.cssSelector("span#TabSFA");
	public static final By serviceHomeArrow = By.cssSelector("span#TabCS");
	public static final By marketingHomeArrow = By.cssSelector("span#TabMA");
	public static final By homeArrowDecision = By.xpath("//div[@id='navTabGroupDiv']//following::span[@id='TabCRMHome']//following-sibling::span[1]/span[1]");
	

	/**
	 * @Method:DropDownSelection This method is used to select value from
	 *                           disabled dropdown based on visible text.
	 * @parentID - used for enable the dropdown
	 * @dropDownID - actual dropdown locator value
	 * @dropDownValue - visible text which need to select from dropdown
	 */

	public static void dropDownSelection_Stale(WebDriver driver, By parentID, By dropDownID, String dropDownValue) {
		try {
			WebElement element = null;
			new WebDriverWait(driver, 20).ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.elementToBeClickable(parentID));
			// new WebDriverWait(driver,
			// 20).ignoring(StaleElementReferenceException.class).until((Function<?
			// super WebDriver, V>));

			element = driver.findElement(parentID);
			element.click();

			element = driver.findElement(dropDownID);
			Select dropDown = new Select(element);
			dropDown.selectByVisibleText(dropDownValue);
		}

		catch (StaleElementReferenceException ex) {
			System.out.println("Exception while slecting a value from dropdown" + ex.getMessage());
		}

	}

	/**
	 * @Method:IsDisplayed This method is used to verify particular element is
	 *                     present on page or not
	 * @param ModuleAttributeValue
	 *            - Provide locator value of element to chcek whether it is
	 *            displayed or not
	 */
	public static boolean isDisplayed(CraftDriver driver, By ModuleAttributeValue) {
		boolean flag = false;
		try {
			if (driver.findElement(ModuleAttributeValue).isDisplayed()) {
				System.out.println("Element is dispalyed");
				flag = true;

			}
		} catch (Exception e) {
			System.out.println("Element is not dispalyed");
			flag = false;
		}

		return flag;
	}

	/**
	 * @Method:navigationToEntity - this method is used to navigate to different
	 *                            entities available in MSCRM application
	 * @param driver
	 * @param ModuleAttributeValue
	 *            - Provide Parent module
	 * @param EntityAttributeValue
	 *            - Provide entity value present under Parent module
	 */
	public void navigationToEntity(CraftDriver driver, By ModuleAttributeValue, By EntityAttributeValue) {

try {
			
			if(waitForElementToBeClickableBool((WebDriver)driver, homeArrowDecision, maxTimeout)){
				String module = driver.findElement(homeArrowDecision).getAttribute("title");

				if(module.equalsIgnoreCase("Sales")){
					mouseClick_Stale((WebDriver)driver, salesHomeArrow);
					
				}else if(module.equalsIgnoreCase("Service")){
					mouseClick_Stale((WebDriver)driver, serviceHomeArrow);
					
				}else if(module.equalsIgnoreCase("Marketing")){
					mouseClick_Stale((WebDriver)driver, marketingHomeArrow);
					
				}else{
					report.updateTestLog("Navigate to Entity", "Module not listed under list.", Status.WARNING);
				}
				
			}else{
				report.updateTestLog("Navigate to Entity", "Home Arrow dropdown not present.", Status.FAIL);
			}
			
			mouseClick_Stale((WebDriver)driver, ModuleAttributeValue);
			mouseClick_Stale((WebDriver)driver, EntityAttributeValue);
			
			/*
		 	
		 	switch(module){
			case "Sales":
				MouseClickAction_Stale(driver, salesHomeArrow);
				
			case "Service":
				MouseClickAction_Stale(driver, serviceHomeArrow);
				
			case "Marketing":
				MouseClickAction_Stale(driver, marketingHomeArrow);
			
			default:
				report.updateTestLog("Navigate to Entity", "Module not listed under list.", Status.WARNING);
			}
			
			*/
		
		} catch (Exception Ex) {
			report.updateTestLog("Navigate to Entity", "Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}

	}

	/**
	 * @method:mouseClick_Stale - This Method is used to click on element using
	 *                          javascript function.It will wait till that
	 *                          element is clickable.
	 * @param driver
	 * @param attributeValue
	 *            - provide locator value of element on which click operation
	 *            needs to perform
	 */
	public void mouseClick_Stale(WebDriver driver, By attributeValue) {
		try {

			if (waitForElementToBeClickableBool(driver, attributeValue, maxTimeout)) {
				WebElement element = driver.findElement(attributeValue);
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", element);
				report.updateTestLog("Mouse Click Action on Stale Element", "Able to click to element !", Status.PASS);
			} else {
				report.updateTestLog("Mouse Click Action on Stale Element", "Not able to click to element !",
						Status.FAIL);
			}

		} catch (Exception Ex) {
			report.updateTestLog("Mouse Click Action on Stale Element", "Exception Occured: " + Ex.getMessage(),
					Status.FAIL);
		}

	}

	/**
	 * @Method:mouseClick - This Method is used to click on element using
	 *                    WebDriver click function.It will wait till that
	 *                    element is clickable.
	 * @param driver
	 * @param attributeValue
	 */
	public static void mouseClick(WebDriver driver, By attributeValue) {
		try {
			new WebDriverWait(driver, 5).ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.elementToBeClickable(attributeValue));
			WebElement staleElement = driver.findElement(attributeValue);
			staleElement.click();
		}

		catch (StaleElementReferenceException ex) {
			System.out.println("Exception while entering a value" + ex.getMessage());
		}
	}

	/**
	 * @Method:staleElement-This method is used to set focus on hidden text
	 *                           field and then enter data in text field.
	 * @param driver
	 * @param attributeValue
	 *            - provide div locator value for text field
	 * @param text_attributeValue
	 *            - provide input locator value of text field
	 * @param value
	 *            - Provide text which need to set for particular field
	 */

	public static void type_Stale(WebDriver driver, By attributeValue, By text_attributeValue, String value) {

		try {

			new WebDriverWait(driver, 30).ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.elementToBeClickable(attributeValue));
			WebElement staleElement = driver.findElement(attributeValue);
			staleElement.click();

			WebElement enabledElement = driver.findElement(text_attributeValue);
			enabledElement.sendKeys(value);

		}

		catch (StaleElementReferenceException ex) {
			System.out.println("Exception while entering a value" + ex.getMessage());
		}
	}

	public static void type_Stale(WebDriver driver, By text_attributeValue, String value) {

		try {

			new WebDriverWait(driver, 30).ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.elementToBeClickable(text_attributeValue));

			WebElement enabledElement = driver.findElement(text_attributeValue);
			enabledElement.click();
			enabledElement.sendKeys(value);

		}

		catch (StaleElementReferenceException ex) {
			System.out.println("Exception while entering a value" + ex.getMessage());
		}
	}

	/**
	 * 
	 * @param driver
	 * @param ModuleAttributeValue
	 *            - Provide Parent Module locator value
	 * @param EntityAttributeValue
	 *            - Provide sub entities locator value present under Parent
	 *            Module
	 * @param attributeValue
	 *            - Provide locator value of element where we need to click that
	 *            searched record i.e. table or link
	 * @param recordValue
	 *            - Provide value which needs to search
	 */
	public static void recordSearch(WebDriver driver, By ModuleAttributeValue, By EntityAttributeValue,
			By attributeValue, String recordValue) {

		try {

			driver.findElement(CRMNewAccountRecordPage.homeArrow).click();
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

			driver.findElement(ModuleAttributeValue).click();
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

			driver.findElement(EntityAttributeValue).click();
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

			Thread.sleep(1500);
			driver.navigate().refresh();
			Thread.sleep(3000);

			// Verify Lead

			By recordSearchBox = By.xpath("//*[@id='crmGrid_findCriteria']");
			By recordLink = By.xpath("//a[@title='" + recordValue + "']");
			// input#crmGrid_findCriteria

			WebElement iframeElement = driver.findElement(By.id("contentIFrame0"));
			driver.switchTo().frame(iframeElement);

			new WebDriverWait(driver, 5).ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.elementToBeClickable(recordSearchBox));
			WebElement staleElement = driver.findElement(recordSearchBox);

			Actions builder = new Actions(driver);
			builder.moveToElement(staleElement, 1, 1).click();
			staleElement.sendKeys(recordValue);
			staleElement.sendKeys(Keys.ENTER);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

			new WebDriverWait(driver, 5).ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.visibilityOfElementLocated(recordLink));
			WebElement recordElement = driver.findElement(recordLink);
			String recordTitle = recordElement.getAttribute("Title");

			System.out.println("String comparision: " + "Expected- " + recordValue + "/ Actual- " + recordTitle);

			if (recordTitle.equals(recordValue)) {

				new WebDriverWait(driver, 5).ignoring(StaleElementReferenceException.class)
						.until(ExpectedConditions.elementToBeClickable(recordLink));
				WebElement recordElementAction = driver.findElement(recordLink);
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", recordElementAction);
				Thread.sleep(5000);
			}

			else {

				System.out.println("Record doesn't exist !");
			}

			driver.switchTo().defaultContent();
			Thread.sleep(5000);
		}

		catch (InterruptedException e) {
			System.out.println("Record doesn't exist !");
		} catch (NoSuchElementException ex) {
			System.out.println("Record doesn't exist !");
		} catch (TimeoutException ex) {
			System.out.println("Record doesn't exist !");

		}

	}

	/**
	 * @Method:switchToAlertAndAccept This method is used to switch to alert and
	 *                                accept that alert and print the message
	 *                                present on alert
	 */
	public static void switchToAlertAndAccept(WebDriver driver) throws InterruptedException {

		new WebDriverWait(driver, 30).ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		String alertMessage = driver.switchTo().alert().getText();
		System.out.println(alertMessage);
		alert.accept();

	}

	/**
	 * @Method:switchToAlertAndDismiss This method is used to switch to alert
	 *                                 and cancel that alert
	 */
	public static void switchToAlertAndDismiss(WebDriver driver) {
		new WebDriverWait(driver, 30).ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}

	/**
	 * @Method:switchWindow This method is used to switch between two windows
	 *                      based on title.
	 */
	public static boolean switchWindow(WebDriver driver, String title) throws IOException {
		

		String currentWindow = driver.getWindowHandle();
		java.util.Set<String> availableWindows = driver.getWindowHandles();
		if (!availableWindows.isEmpty()) {
			for (String windowId : availableWindows) {
				if (driver.switchTo().window(windowId).getTitle().equals(title)) {
					return true;
				} else {
					driver.switchTo().window(currentWindow);
				}
			}
		}

		return false;
	}

	/**
	 * @Method:getcurrenttime This method is used to return system time in
	 *                        seconds.
	 */
	public static int getcurrenttime() {

		long currentDateMS = new Date().getTime();
		int seconds = (int) ((currentDateMS / 1000) % 3600);
		return seconds;

	}

	/**
	 * This method is used to enter data in text field.
	 * 
	 * @param driver
	 * @param attributeValue
	 *            - provide locator value of element for which data needs to
	 *            enter.
	 * @throws InterruptedException
	 */
	public static void sendKeys(WebDriver driver, By attributeValue, String Value) throws InterruptedException {
		driver.findElement(attributeValue).sendKeys(Value);
		Thread.sleep(2000);
	}

	/**
	 * This method is used to wait for element till it is clickable.
	 * 
	 * @param driver
	 * @param attributeValue
	 *            - provide locator value of element needs to click.
	 * @param waitTime
	 *            - provide maximum wait time in seconds for driver
	 */
	public static void waitForElementToBeClickable(WebDriver driver, By attributeValue, int waitTime) {
		new WebDriverWait(driver, waitTime).ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.elementToBeClickable(attributeValue));

	}

	/**
	 * This method is used to wait for element till presence of element.
	 * 
	 * @param driver
	 * @param attributeValue
	 *            - provide locator value of element till it appears on
	 *            application and then click that element.
	 * @param waitTime
	 *            - provide maximum wait time in seconds for driver
	 */
	public static void waitForPresenceOfElement(WebDriver driver, By attributeValue, int waitTime) {
		new WebDriverWait(driver, waitTime).ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.presenceOfElementLocated(attributeValue));

	}

	/**
	 * This method is used to wait for element till visibility of element.
	 * 
	 * @param driver
	 * @param attributeValue
	 *            - provide locator value of element till it is visible on
	 *            application and then click that element.
	 * @param waitTime
	 *            - provide maximum wait time in seconds for driver
	 */
	public static boolean waitForElementToBeVisible(WebDriver driver, By attributeValue, int waitTime) {
		boolean flag = false;
		try {
			new WebDriverWait(driver, waitTime).ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.visibilityOfElementLocated(attributeValue));
			if (driver.findElement(attributeValue).isDisplayed()) {
				flag = true;
			}

		} catch (Exception Ex) {
			Ex.printStackTrace();
		}
		return flag;
	}

	public static boolean waitForElementToBeClickableBool(WebDriver driver, By attributeValue, int waitTime) {
		boolean flag = false;
		try{
			new WebDriverWait(driver, waitTime).ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.elementToBeClickable(attributeValue));
			flag=true;
			return flag;
			
		}catch(Exception Ex){
			return flag;
		}
	}

	public static void waitForVisibilityOfElement(WebDriver driver, By attributeValue, int waitTime) {

		new WebDriverWait(driver, waitTime).ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.visibilityOfElementLocated(attributeValue));
	}

	/**
	 * This method is used to verify text on application
	 * 
	 * @param driver
	 * @param text
	 *            - provide which text needs to compare with attributevalue text
	 * @param attributeValue
	 *            - provide element value of which text needs to verify
	 */
	public static void verifyText(WebDriver driver, String text, By attributeValue) {

		WebElement element = driver.findElement((attributeValue));
		String strng = element.getText();
		System.out.println(strng);
		Assert.assertEquals(text, strng);

	}

	/**
	 * @Method;implicitWait-This method is used to set implicit wait at drive
	 *                           level
	 * @param driver
	 * @param Timespan
	 *            - provide wait time in seconds
	 */
	public static void implicitWait(WebDriver driver, int Timespan) {
		driver.manage().timeouts().implicitlyWait(Timespan, TimeUnit.SECONDS);
	}

	/**
	 * @Method:closeAllOtherWindows - This method is used to close all open
	 *                              windows except parent window.
	 * @param driver
	 * @return
	 * @throws InterruptedException
	 */
	public static boolean closeAllOtherWindows(WebDriver driver) throws InterruptedException {
		String Parent_Window = driver.getWindowHandle();
		java.util.Set<String> allWindowHandles = driver.getWindowHandles();
		for (String currentWindowHandle : allWindowHandles) {
			if (!currentWindowHandle.equals(Parent_Window)) {
				driver.switchTo().window(currentWindowHandle);
				driver.close();
				Thread.sleep(2000);
			}
		}

		driver.switchTo().window(Parent_Window);
		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}

	/**
	 * @Method:getText - This method is used to retrieve text from webelement
	 * @param driver
	 * @param attributeValue
	 * @return
	 */
	public static String getText(WebDriver driver, By attributeValue) {
		WebElement element = driver.findElement((attributeValue));
		String strng = element.getText();
		return strng;
	}

	/**
	 * This method is for simple mouse click operation on an element
	 * 
	 * @param driver
	 * @param attributeValue-This
	 *            is the unique attribute to find an element
	 */
	public static void simpleMouseClick_Action(WebDriver driver, By attributeValue) {
		try {
			WebElement element = driver.findElement(attributeValue);
			// new WebDriverWait(driver,
			// 30).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(element));

			element.click();
		} catch (StaleElementReferenceException ex) {
			System.out.println("Exception while Clicking an element" + ex.getMessage());
		}
	}

	/**
	 * This method is for simple dropdown selection by visibleText
	 * 
	 * @param driver
	 * @param dropDownID-This
	 *            is the unique attribute to find an dropdownelement
	 * @param dropDownValue-This
	 *            is the text to search in dropdown
	 */
	public static void dropDownSelectionByText(WebDriver driver, By dropDownID, String dropDownValue) {
		try {
			WebElement element = null;
			new WebDriverWait(driver, 5).ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.elementToBeClickable(dropDownID));

			element = driver.findElement(dropDownID);
			Select dropDown = new Select(element);
			dropDown.selectByVisibleText(dropDownValue);
		}

		catch (StaleElementReferenceException ex) {
			System.out.println("Exception while selecting a value from dropdown" + ex.getMessage());
		}

	}

	/**
	 * This method is for simple dropdown selection by value
	 * 
	 * @param driver
	 * @param dropDownID-This
	 *            is the unique attribute to find an dropdownelement
	 * @param dropDownValue-This
	 *            is the text to search in dropdown
	 */
	public static void dropDownSelectionByValue(WebDriver driver, By dropDownID, String dropDownValue) {
		try {
			WebElement element = null;
			new WebDriverWait(driver, 5).ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.elementToBeClickable(dropDownID));

			element = driver.findElement(dropDownID);
			Select dropDown = new Select(element);
			dropDown.selectByValue(dropDownValue);
		}

		catch (StaleElementReferenceException ex) {
			System.out.println("Exception while selecting a value from dropdown" + ex.getMessage());
		}

	}

	/**
	 * This method is for simple dropdown selection by index
	 * 
	 * @param driver
	 * @param dropDownID-This
	 *            is the unique attribute to find an dropdownelement
	 * @param dropDownValue-This
	 *            is the text to search in dropdown
	 */
	public static void dropDownSelectionByIndex(WebDriver driver, By dropDownID, int dropDownValue) {
		try {
			WebElement element = null;
			new WebDriverWait(driver, 5).ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.elementToBeClickable(dropDownID));

			element = driver.findElement(dropDownID);
			Select dropDown = new Select(element);
			dropDown.selectByIndex(dropDownValue);
		}

		catch (StaleElementReferenceException ex) {
			System.out.println("Exception while selecting a value from dropdown" + ex.getMessage());
		}

	}

	/**
	 * This method is to Switch to an default iframe
	 * 
	 * @param driver
	 * @param attributeValue-This
	 *            is the unique attribute of the frame to be switched
	 */
	public static void switchToDefaultFrame(WebDriver driver) {
		try {
			driver.switchTo().defaultContent();
		} catch (NoSuchElementException ex) {
			System.out.println("Exception while switching frame" + ex.getMessage());
		}
	}

	public static void SwitchToFrameStandard(WebDriver driver, String iFrameValue) throws Exception {
		driver.switchTo().defaultContent();
		String iframeID = "";
		int iter = 1;
		List<WebElement> elements = driver.findElements(By.xpath("//*/iframe"));
		for (WebElement ele : elements) {
			iframeID = ele.getAttribute("name");
			System.out.println(iframeID);
			if (iframeID.equals(iFrameValue))
				break;
			else
				iter++;
		}
		System.out.println("Number of iterations: " + iter);
		String iframe=driver.findElement(By.name(iframeID));
		driver.switchTo().frame(iframe);
	}

	/**
	 * This method is to Switch to an iframe
	 * 
	 * @param driver
	 * @param attributeValue-This
	 *            is the unique attribute of the frame to be switched
	 */
	public static void switchToFrame(WebDriver driver, String attributeValue) {
		try {
			// driver.switchTo().frame((driver.findElement(attributeValue)));
			driver.switchTo().frame(attributeValue);
		} catch (NoSuchElementException ex) {
			System.out.println("Exception while switching frame" + ex.getMessage());
		}
	}

	public static void switchToFrame(WebDriver driver, By locator) {
		try {
			String iframe =driver.findElement(locator);
			driver.switchTo().frame(iframe);
		} catch (Exception ex) {
			System.out.println("Exception while switching frame" + ex.getMessage());
		}
	}

	public static void waitForpageLoadByThread(WebDriver driver) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		for (int i = 1; i < 4; i++) {
			Thread.sleep(3000);
			if (js.executeScript("return document.readyState").toString().equals("complete")) {
				break;
			}
		}
	}


	public static void searchAndSelectOnForm(WebDriver driver, By fieldLabel, By field, String Value ) throws Exception{
		try{
		driver.findElement(fieldLabel).click();
		driver.findElement(field).clear();
		driver.findElement(field).sendKeys(Value);
		Actions action=new Actions(driver);
		//action.sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//td[@class='Lookup_RenderButton_td']/img[@title='Select a value.']")).click();
		System.out.println("Search icon clicked ");
		
		if(driver.findElement(By.xpath("//a[@title='"+Value+"']")).isDisplayed()){
			driver.findElement(By.xpath("//a[@title='"+Value+"']")).click();
		}
		else
			System.out.println("No search Result of :"+Value);
		
		}catch (Exception ex) {
			System.out.println("Exception while searching And Selecting value OnForm :" + ex.getMessage());
		
		
		}
	}
}
