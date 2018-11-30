package pageObjects;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cognizant.framework.Status;
import com.cognizant.framework.selenium.CraftDriver;

import com.cognizant.Craft.ReusableLibrary;
import com.cognizant.Craft.ScriptHelper;

public class reusableFunctions extends ReusableLibrary {

	public reusableFunctions(ScriptHelper scriptHelper) {
		super(scriptHelper);
		// TODO Auto-generated constructor stub
	}

	static int maxTimeout = 40;
	public static final By salesHomeArrow = By.cssSelector("span#TabSFA");
	public static final By serviceHomeArrow = By.cssSelector("span#TabCS");
	public static final By marketingHomeArrow = By.cssSelector("span#TabMA");
	public static final By homeArrowDecision = By.xpath("//div[@id='navTabGroupDiv']//following::span[@id='TabCRMHome']//following-sibling::span[1]/span[1]");
			//By.xpath("//div[@id='navBar']//following::span[@id='TabSFA-main']");
	
	public boolean waitForElementToBeClickableBool(WebDriver driver, By attributeValue, int waitTime) {	
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
	
	public boolean waitForAlertPresent(WebDriver driver, int waitTime) {
		boolean flag = false;
		new WebDriverWait(driver, waitTime).ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.alertIsPresent());
		try{
			driver.switchTo().alert();
			return flag = true;
		}catch(Exception Ex){
			return flag;
		}
	}

	public void waitForVisibilityOfElement(WebDriver driver, By attributeValue, int waitTime) {

		new WebDriverWait(driver, waitTime).ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.visibilityOfElementLocated(attributeValue));

	}
	public boolean isAlertPresent() 
	{ 
	    try 
	    { 
	        driver.switchTo().alert(); 
	        return true; 
	    }   // try 
	    catch (NoAlertPresentException Ex) 
	    { 
	        return false; 
	    }   // catch 
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
	public void waitForElementToBeClickable(WebDriver driver, By attributeValue, int waitTime) {
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
	public void waitForPresenceOfElement(WebDriver driver, By attributeValue, int waitTime) {
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
	public boolean waitForElementToBeVisible(WebDriver driver, By attributeValue, int waitTime) {
		boolean flag = false;
		try {
			new WebDriverWait(driver, waitTime).ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.visibilityOfElementLocated(attributeValue));
			flag=true;
			return flag;

		} catch (Exception Ex) {
			report.updateTestLog("Wait For Element To Be Visible", "Element is not visible.", Status.WARNING);
			return flag;
		}
	}
	
	public boolean waitForElementToBePresent(WebDriver driver, By attributeValue, int waitTime) {
		boolean flag = false;
		try {
			
			new WebDriverWait(driver, waitTime).ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.presenceOfElementLocated(attributeValue));
			
			return flag = true;

		} catch (Exception Ex) {
			return flag;
		}
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
	public boolean waitForElementToBePresentBool(WebDriver driver, By attributeValue, int waitTime) {
		boolean flag = false;
		try {
			new WebDriverWait(driver, waitTime).ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.presenceOfElementLocated(attributeValue));

			flag = true;
			return flag;
		} catch (Exception Ex) {
			return flag;
		}

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
	public void navigationToEntity(WebDriver driver, By ModuleAttributeValue, By EntityAttributeValue) {
		try {
			
			if(waitForElementToBeClickableBool(driver, homeArrowDecision, maxTimeout)){
				String module = driver.findElement(homeArrowDecision).getAttribute("title");

				if(module.equalsIgnoreCase("Sales")){
					MouseClickActionMoveToElement(driver, salesHomeArrow);
					
				}else if(module.equalsIgnoreCase("Service")){
					MouseClickActionMoveToElement(driver, serviceHomeArrow);
					
				}else if(module.equalsIgnoreCase("Marketing")){
					MouseClickActionMoveToElement(driver, marketingHomeArrow);
					
				}else{
					report.updateTestLog("Navigate to Entity", "Module not listed under list.", Status.WARNING);
				}
				
			}else{
				report.updateTestLog("Navigate to Entity", "Home Arrow dropdown not present.", Status.FAIL);
			}
			
			MouseClickActionMoveToElement(driver, ModuleAttributeValue);
			MouseClickActionMoveToElement(driver, EntityAttributeValue);
			
			
		 	
/*		 	switch(module){
			case "Sales":
				MouseClickAction_Stale(driver, salesHomeArrow);
				break;
			case "Service":
				MouseClickAction_Stale(driver, serviceHomeArrow);
				break;
			case "Marketing":
				MouseClickAction_Stale(driver, marketingHomeArrow);
				break;
			default:
				report.updateTestLog("Navigate to Entity", "Module not listed under list.", Status.WARNING);
				break;
			}*/
			
			
		
		} catch (Exception Ex) {
			report.updateTestLog("Navigate to Entity", "Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}
	}

	/**
	 * This method is to Switch to an iframe
	 * 
	 * @param driver
	 * @param attributeValue-This
	 *            is the unique attribute of the frame to be switched
	 */
	public void switchToFrame(WebDriver driver, By attributeValue) {		
		try {

			if (waitForElementToBePresentBool(driver, attributeValue, maxTimeout)) {
				String iframe =driver.findElement(attributeValue);
				driver.switchTo().frame(iframe);
				report.updateTestLog("Switch To Frame", "Frame Switched !", Status.PASS);
			} else {
				report.updateTestLog("Switch To Frame", "Frame not found !", Status.FAIL);
			}

		} catch (Exception Ex) {
			report.updateTestLog("Switch To Frame", "Exception Occured: " + Ex.getMessage(),
					Status.FAIL);
		}
		
	}

	public void switchToObjectFrame(WebDriver driver, By locator) throws Exception{
		driver.switchTo().defaultContent();
		int size = driver.findElements(By.tagName("iframe")).size();
		
		for (int i=0; i<=size-1; i++){
			driver.switchTo().frame(i);
		waitForElementToBePresent(driver, locator, maxTimeout);
			if ((driver.findElements(locator).size())==1){
				driver.switchTo().defaultContent();
				driver.switchTo().frame(i);
				
				break;
			}
			driver.switchTo().defaultContent();
		}
	}
	
	
	public void SwitchToFrameStandard(WebDriver driver, String iFrameValue) throws Exception {
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
		if(iframeID.equals(iFrameValue)){
			System.out.println("Number of iterations: " + iter);
			String iframe =driver.findElement(By.name(iframeID));
			driver.switchTo().frame(iframe);
		}else{
			report.updateTestLog("Switch To Frame", "Frame not found !", Status.FAIL);
		}
		
	}
	
	/**
	 * This method is to Switch to an default iframe
	 * 
	 * @param driver
	 * @param attributeValue-This
	 *            is the unique attribute of the frame to be switched
	 */
	public void switchToDefaultFrame(WebDriver driver) {
		try {
			driver.switchTo().defaultContent();
			
		} catch (Exception Ex) {
			report.updateTestLog("Switch To Default Frame", "Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}
	}
	
	/**
	 * This method is to Switch to Alert 
	 * 
	 * @param driver
	 * @param attributeValue-This
	 *            is the unique attribute of the alert to be switched
	 */
	public void switchToAlertAndPerformAction(WebDriver driver, boolean accept) {
		try {
			new WebDriverWait(driver, maxTimeout).ignoring(NoAlertPresentException.class)
					.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			if (accept) {
				alert.accept();
			} else {
				alert.dismiss();
			}

		} catch (Exception Ex) {
			report.updateTestLog("Switch To Alert and Perform Action", "Exception Occured: " + Ex.getMessage(),
					Status.FAIL);
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
	public void enterTextAction_Stale(WebDriver driver, By attributeValue, By text_attributeValue, String value) {
		try {

			if (waitForElementToBeClickableBool(driver, attributeValue, maxTimeout)) {
				WebElement staleElement = driver.findElement(attributeValue);
				staleElement.click();

				WebElement enabledElement = driver.findElement(text_attributeValue);
				enabledElement.clear();
				enabledElement.sendKeys(value);
				
				report.updateTestLog("Enter Text Action", "Text Entered Successfully !", Status.PASS);
			} else {
				report.updateTestLog("Enter Text Action", "Element is not clickable !", Status.FAIL);
			}

		} catch (Exception Ex) {
			report.updateTestLog("Enter Text Action", "Exception Occured: " + Ex.getMessage(),
					Status.FAIL);
		}
	}
	
	public void EnterTextAction_Stale(WebDriver driver, By text_attributeValue, String value) {
		try {
			
			if (waitForElementToBeClickableBool(driver, text_attributeValue, maxTimeout)) {
				WebElement enabledElement = driver.findElement(text_attributeValue);
				enabledElement.clear();
				enabledElement.sendKeys(value);
				
				report.updateTestLog("Enter Text Action", "Text Entered Successfully !", Status.PASS);
			} else {
				report.updateTestLog("Enter Text Action", "Element is not clickable !", Status.FAIL);
			}

		} catch (Exception Ex) {
			report.updateTestLog("Enter Text Action", "Exception Occured: " + Ex.getMessage(),
					Status.FAIL);
		}
	}
	
	/**
	 * @Method:DropDownSelection This method is used to select value from
	 *                           disabled dropdown based on visible text.
	 * @parentID - used for enable the dropdown
	 * @dropDownID - actual dropdown locator value
	 * @dropDownValue - visible text which need to select from dropdown
	 */
	public void dropDownSelection_Stale(WebDriver driver, By parentID, By dropDownID, String dropDownValue) {
		try {
			if (waitForElementToBeClickableBool(driver, parentID, maxTimeout)) {
				WebElement element = null;
				
				element = driver.findElement(parentID);
				element.click();

				element = driver.findElement(dropDownID);
				Select dropDown = new Select(element);
				dropDown.selectByVisibleText(dropDownValue);
				
				report.updateTestLog("Select Value From DropDown", "Value Selected Successfully !", Status.PASS);
			} else {
				report.updateTestLog("Select Value From DropDown", "Dropdown element is not present !", Status.PASS);
			}

		} catch (Exception Ex) {
			report.updateTestLog("Select Value From DropDown", "Exception Occured: " + Ex.getMessage(),
					Status.FAIL);
		}

	}

	/**
	 * @Method:DropDownSelection This method is used to select value from
	 *                           disabled dropdown based on visible text.
	 * @parentID - used for enable the dropdown
	 * @dropDownID - actual dropdown locator value
	 * @dropDownValue - visible text which need to select from dropdown
	 */
	
	public void dropDownSelection(WebDriver driver, By dropDownID, String dropDownValue) {
		try {

			if (waitForElementToBeClickableBool(driver, dropDownID, maxTimeout)) {
				WebElement element = null;
				
				element = driver.findElement(dropDownID);
				Select dropDown = new Select(element);
				dropDown.selectByVisibleText(dropDownValue);
				
				report.updateTestLog("Select Value From DropDown", "Value Selected Successfully !", Status.PASS);
			} else {
				report.updateTestLog("Select Value From DropDown", "Dropdown element is not present !", Status.PASS);
			}

		} catch (Exception Ex) {
			report.updateTestLog("Select Value From DropDown", "Exception Occured: " + Ex.getMessage(),
					Status.FAIL);
		}
	}
	
	public void MouseClickAction_Stale(WebDriver driver, By attributeValue) {
		try {

			if (waitForElementToBeClickableBool(driver, attributeValue, maxTimeout)) {
				WebElement element = driver.findElement(attributeValue);
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", element);
				report.updateTestLog("Mouse Click Action on Stale Element", "Able to locate and click to element !", Status.PASS);
			} else {
				report.updateTestLog("Mouse Click Action on Stale Element", "Not able to locate the element !",
						Status.FAIL);
			}

		} catch (Exception Ex) {
			report.updateTestLog("Mouse Click Action on Stale Element", "Exception Occured: " + Ex.getMessage(),
					Status.FAIL);
		}

	}
	
	public void MouseClickActionMoveToElement(WebDriver driver, By attributeValue) {
		try {

			if (waitForElementToBeClickableBool(driver, attributeValue, maxTimeout)) {
				WebElement element = driver.findElement(attributeValue);
				//element.click();
				Actions builder = new Actions(driver);
				builder.moveToElement(element).click().build().perform();
				report.updateTestLog("Mouse Click Action Move To Element :"+attributeValue, "Able to locate and click to element !", Status.PASS);
			} else {
				report.updateTestLog("Mouse Click Action Move To Element"+attributeValue, "Not able to locate the element !",
						Status.FAIL);
			}

		} catch (Exception Ex) {
			report.updateTestLog("Mouse Click Action Move To Element"+attributeValue, "Exception Occured: " + Ex.getMessage(),
					Status.FAIL);
		}

	}
	
	public void MouseDoubleClickAction_Stale(WebDriver driver, By attributeValue) {
		try {
			
			if (waitForElementToBeClickableBool(driver, attributeValue, maxTimeout)) {
				WebElement element = driver.findElement(attributeValue);
				Actions builder = new Actions(driver);
				builder.moveToElement(element).doubleClick().build().perform();
				report.updateTestLog("Mouse Double Click Action on Stale Element", "Able to click to element !", Status.PASS);
			} else {
				report.updateTestLog("Mouse Double Click Action on Stale Element", "Not able to click to element !",
						Status.FAIL);
			}

		} catch (Exception Ex) {
			report.updateTestLog("Mouse Double Click Action on Stale Element", "Exception Occured: " + Ex.getMessage(),
					Status.FAIL);
		}

	}	

	public void waitForpageLoadByThread(WebDriver driver) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		for (int i = 1; i < 4; i++) {
			Thread.sleep(1000);
			if (js.executeScript("return document.readyState").toString().equals("complete")) {
				break;
			}
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
	public void lookupRecordValidationPolicyNumber(WebDriver driver, By attributeValue, By recordLookupIcon, By recordTitle, Boolean customerWithoutRecords) {
		try {
			if (waitForElementToBeClickableBool(driver, attributeValue, maxTimeout)) {
				WebElement staleElement = driver.findElement(attributeValue);
				staleElement.click();

				WebElement enabledElement = driver.findElement(recordLookupIcon);
				enabledElement.click();
				
				switchToObjectFrame(driver, recordTitle);
				
				/*By framePath = driver.findElement(By.xpath("//iframe[@id='customScriptsFrame']"));
				switchToFrame(driver, framePath);*/
				
				if(!customerWithoutRecords){
					if (waitForElementToBeVisible(driver, recordTitle, maxTimeout)) {
						report.updateTestLog("Record LookUp Validation", "Lookup Records associated with customer are displayed !", Status.PASS);

					} else {
						report.updateTestLog("Record LookUp Validation", "Lookup Records associated with customer are not displayed !", Status.FAIL);
					}
				}else{
					By noRecord = By.xpath("//ul[@id='cxp_policyid_IMenu']/li[1]//span[@title='No records found. Create a new record.'][2]");
					
					if (waitForElementToBeVisible(driver, noRecord, maxTimeout)) {
						report.updateTestLog("Record LookUp Validation", "No Policy and Bill Account associated with customer !", Status.PASS);

					} else {
						report.updateTestLog("Record LookUp Validation", "Bill Account and Policies are getting displayed "
								+ "which are not associated with customer!", Status.FAIL);
					}
				}
				
			}else{
				report.updateTestLog("Record LookUp Validation", "Record is not available for the policy number.", Status.FAIL);
			}

		} catch (Exception Ex) {
			report.updateTestLog("Record LookUp Validation", "Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}
	}
	
	public void lookupRecordValidationBillAccount(WebDriver driver, By attributeValue, By recordLookupIcon, By recordTitle, Boolean customerWithoutRecords) {
		try {
			if (waitForElementToBeClickableBool(driver, attributeValue, maxTimeout)) {
				WebElement staleElement = driver.findElement(attributeValue);
				staleElement.click();

				WebElement enabledElement = driver.findElement(recordLookupIcon);
				enabledElement.click();
				
				switchToDefaultFrame(driver);
				SwitchToFrameStandard(driver, CRMLandingPage.Iframe1);
				
				/*By framePath = driver.findElement(By.xpath("//iframe[@id='customScriptsFrame']"));
				switchToFrame(driver, framePath);*/
				
				if(!customerWithoutRecords){
					if (waitForElementToBeVisible(driver, recordTitle, maxTimeout)) {
						report.updateTestLog("Record LookUp Validation", "Lookup Records associated with customer are displayed !", Status.PASS);

					} else {
						report.updateTestLog("Record LookUp Validation", "Lookup Records associated with customer are not displayed !", Status.FAIL);
					}
				}else{
					By noRecord = By.xpath("//ul[@id='cxp_billaccountid_IMenu']/li[1]//span[@title='No records found. Create a new record.'][2]");
					
					if (waitForElementToBeVisible(driver, noRecord, maxTimeout)) {
						report.updateTestLog("Record LookUp Validation", "No Policy and Bill Account associated with customer !", Status.PASS);

					} else {
						report.updateTestLog("Record LookUp Validation", "Bill Account and Policies are getting displayed "
								+ "which are not associated with customer!", Status.FAIL);
					}
				}
				
			}else{
				report.updateTestLog("Record LookUp Validation", "Record is not available for the bill account.", Status.FAIL);
			}

		} catch (Exception Ex) {
			report.updateTestLog("Record LookUp Validation", "Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}
	}

	public String getTextFromElement(WebDriver driver, By locator) {
		String text = null;
		try {
			if (waitForElementToBePresentBool(driver, locator, maxTimeout)) {
				WebElement element = driver.findElement(locator);
				text = element.getText();
				report.updateTestLog("Get Text From Element", "Element Text is: "+ text, Status.PASS);
			} else {
				report.updateTestLog("Get Text From Element", "Element not present !", Status.FAIL);
			}

		} catch (Exception Ex) {
			report.updateTestLog("Get Text From Element", "Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}
		return text;
	}
}