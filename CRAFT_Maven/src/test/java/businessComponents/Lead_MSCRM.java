package businessComponents;

import pageObjects.*;
import com.cognizant.Craft.*;


import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.cognizant.framework.Status;
import com.google.common.base.CaseFormat;

/**
 * Class for storing general purpose business components
 * 
 * @author Cognizant
 */
public class Lead_MSCRM extends ReusableLibrary {
	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	WebElement element = null;

	public Lead_MSCRM(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}
	
	reusableFunctions reusableFunc = new reusableFunctions(scriptHelper);

	/**
	 * create_Lead This method is used to create Lead record from Lead entity
	 * 
	 * @throws Exception
	 */
	public void create_Lead() throws Exception {

		String FirstName = dataTable.getData("General_Data", "FirstName");
		String LastName = dataTable.getData("General_Data", "LastName");
		String JobTitle = dataTable.getData("General_Data", "JobTitle");
		String Topic = dataTable.getData("General_Data", "Topic");
		String Email = dataTable.getData("General_Data", "Email");
		String BusinessPhone = dataTable.getData("General_Data", "BusinessPhone");
		String MobilePhone = dataTable.getData("General_Data", "MobilePhone");
		String WebsiteURL = dataTable.getData("General_Data", "WebsiteURL");
		String Company = dataTable.getData("General_Data", "Company");
		String Street1 = dataTable.getData("General_Data", "Street1");
		String Street2 = dataTable.getData("General_Data", "Street2");
		String Street3 = dataTable.getData("General_Data", "Street3");
		String City = dataTable.getData("General_Data", "City");
		String State = dataTable.getData("General_Data", "State");
		String PostalCode = dataTable.getData("General_Data", "PostalCode");
		String Country = dataTable.getData("General_Data", "Country");

		// Navigate to lead page
		reusableFunc.navigationToEntity(driver.getWebDriver(), CRMNewAccountRecordPage.salesModule, CRMNewLeadRecordPage.leadEntity);
		UI_Helpers_OOB.implicitWait(driver.getWebDriver(), 2);

		Thread.sleep(5000);

		reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.NewButtonClick);
		report.updateTestLog("Lead Entity", "Click on new button", Status.SCREENSHOT);
		Thread.sleep(2000);

		UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.frame1);

		UI_Helpers_OOB.mouseClick(driver.getWebDriver(), CRMNewLeadRecordPage.FullName);
		report.updateTestLog("Lead Entity", "Click on composite name element", Status.SCREENSHOT);
		UI_Helpers_OOB.implicitWait(driver.getWebDriver(), 2);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewLeadRecordPage.firstName_enable,
				CRMNewLeadRecordPage.firstName_edit, FirstName);
		report.updateTestLog("Lead Entity", "Fill First Name", Status.SCREENSHOT);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewLeadRecordPage.lastName_enable,
				CRMNewLeadRecordPage.lasttName_edit, LastName);
		report.updateTestLog("Lead Entity", "Fill Last Name", Status.SCREENSHOT);
		UI_Helpers_OOB.implicitWait(driver.getWebDriver(), 2);

		driver.findElement(CRMNewLeadRecordPage.doneButtonClick_fullName).click();
		report.updateTestLog("Lead Entity", "Click on done button", Status.SCREENSHOT);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewLeadRecordPage.topic_enable,
				CRMNewLeadRecordPage.topic_edit, Topic);
		report.updateTestLog("Lead Entity", "Fill Topic", Status.SCREENSHOT);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewLeadRecordPage.jobTitle_enable,
				CRMNewLeadRecordPage.jobTitle_edit, JobTitle);
		report.updateTestLog("Lead Entity", "Fill Job Title", Status.SCREENSHOT);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewLeadRecordPage.businessPhone_enable,
				CRMNewLeadRecordPage.businessPhone_edit, BusinessPhone);
		report.updateTestLog("Lead Entity", "Fill Business Phone", Status.SCREENSHOT);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewLeadRecordPage.mobilePhone_enable,
				CRMNewLeadRecordPage.mobilePhone_edit, MobilePhone);
		report.updateTestLog("Lead Entity", "Fill Mobile Phone", Status.SCREENSHOT);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewLeadRecordPage.Email_enable,
				CRMNewLeadRecordPage.Email_edit, Email);
		report.updateTestLog("Lead Entity", "Fill Email Address", Status.SCREENSHOT);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewLeadRecordPage.company_enable,
				CRMNewLeadRecordPage.company_edit, Company);
		report.updateTestLog("Lead Entity", "Fill Company Name", Status.SCREENSHOT);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewLeadRecordPage.websiteurl_enable,
				CRMNewLeadRecordPage.websiteurl_edit, WebsiteURL);
		report.updateTestLog("Lead Entity", "Fill Website URL", Status.SCREENSHOT);

		UI_Helpers_OOB.mouseClick(driver.getWebDriver(), CRMNewLeadRecordPage.address);
		report.updateTestLog("Lead Entity", "Click on composite address element", Status.SCREENSHOT);
		UI_Helpers_OOB.implicitWait(driver.getWebDriver(), 2);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewLeadRecordPage.street1_enable,
				CRMNewLeadRecordPage.street1_edit, Street1);
		report.updateTestLog("Lead Entity", "Fill Street 1", Status.SCREENSHOT);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewLeadRecordPage.street2_enable,
				CRMNewLeadRecordPage.street2_edit, Street2);
		report.updateTestLog("Lead Entity", "Fill Street 2", Status.SCREENSHOT);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewLeadRecordPage.street3_enable,
				CRMNewLeadRecordPage.street3_edit, Street3);
		report.updateTestLog("Lead Entity", "Fill Street 3", Status.SCREENSHOT);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewLeadRecordPage.city_enable,
				CRMNewLeadRecordPage.city_edit, City);
		report.updateTestLog("Lead Entity", "Fill city name", Status.SCREENSHOT);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewLeadRecordPage.state_enable,
				CRMNewLeadRecordPage.state_edit, State);
		report.updateTestLog("Lead Entity", "Fill state name", Status.SCREENSHOT);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewLeadRecordPage.postalCode_enable,
				CRMNewLeadRecordPage.postalCode_edit, PostalCode);
		report.updateTestLog("Lead Entity", "Fill postal code", Status.SCREENSHOT);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewLeadRecordPage.country_enable,
				CRMNewLeadRecordPage.country_edit, Country);
		report.updateTestLog("Lead Entity", "Fill country name", Status.SCREENSHOT);

		// doneButtonClick_address

		driver.findElement(CRMNewLeadRecordPage.doneButtonClick_address).click();
		report.updateTestLog("Lead Entity", "Click on done button", Status.SCREENSHOT);

		UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
		UI_Helpers_OOB.implicitWait(driver.getWebDriver(), 2);

		reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.SaveButtonClick);
		Thread.sleep(7000);

		report.updateTestLog("Save", "Click the save entity", Status.SCREENSHOT);
	}

	/**
	 * verify_Lead This method is used to verify Lead record
	 * 
	 * @throws Exception
	 */
	public void verify_Lead() throws Exception {

		try {

			String recordValue = dataTable.getData("General_Data", "recordValue");

			reusableFunc.navigationToEntity(driver.getWebDriver(), CRMNewAccountRecordPage.salesModule,
					CRMNewLeadRecordPage.leadEntity);
			Thread.sleep(1500);
			report.updateTestLog("Lead Page", "Search for the lead record", Status.SCREENSHOT);

			driver.navigate().refresh();
			Thread.sleep(3000);

			// Verify Lead

			By recordSearchBox = By.xpath("//*[@id='crmGrid_findCriteria']");
			By recordLink = By.xpath("//a[@title='" + recordValue + "']");

			UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.frame0);

			UI_Helpers_OOB.waitForElementToBeClickable(driver.getWebDriver(), recordSearchBox, 5);

			WebElement staleElement = driver.findElement(recordSearchBox);

			Actions builder = new Actions(driver.getWebDriver());
			builder.moveToElement(staleElement, 1, 1).click();
			staleElement.sendKeys(recordValue);
			staleElement.sendKeys(Keys.ENTER);
			UI_Helpers_OOB.implicitWait(driver.getWebDriver(), 5);

			report.updateTestLog("Lead Page", "Click on the lead record", Status.SCREENSHOT);

			UI_Helpers_OOB.waitForVisibilityOfElement(driver.getWebDriver(), recordLink, 5);

			WebElement recordElement = driver.findElement(recordLink);
			String recordTitle = recordElement.getAttribute("Title");

			System.out.println("String comparision: " + "Expected- " + recordValue + "/ Actual- " + recordTitle);

			if (recordTitle.equals(recordValue)) {

				UI_Helpers_OOB.waitForElementToBeClickable(driver.getWebDriver(), recordLink, 5);

				WebElement recordElementAction = driver.findElement(recordLink);
				JavascriptExecutor executor = (JavascriptExecutor) driver.getWebDriver();
				executor.executeScript("arguments[0].click();", recordElementAction);
				Thread.sleep(5000);
			}

			else {

				// System.out.println("Record doesn't exist !");
				report.updateTestLog("Lead Page", "Record doesn't exist !", Status.FAIL);
			}

			UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());

			Thread.sleep(5000);

		}

		catch (InterruptedException e) {
			// System.out.println("Record doesn't exist !");
			report.updateTestLog("Lead Page", "Record doesn't exist !", Status.FAIL);
		} catch (NoSuchElementException ex) {
			// System.out.println("Record doesn't exist !");
			report.updateTestLog("Lead Page", "Record doesn't exist !", Status.FAIL);
		} catch (TimeoutException ex) {
			// System.out.println("Record doesn't exist !");
			report.updateTestLog("Lead Page", "Record doesn't exist !", Status.FAIL);

		}

		report.updateTestLog("Lead Page", "Lead Verification Done !!", Status.SCREENSHOT);
	}

	/**
	 * leadToOpportunityConversion This method is used to convert lead to
	 * opportunity
	 * 
	 * @throws Exception
	 */

	public void leadToOpportunityConversion() throws Exception {

		try {

			String recordValue = dataTable.getData("General_Data", "recordValue");

			reusableFunc.navigationToEntity(driver.getWebDriver(), CRMNewAccountRecordPage.salesModule,
					CRMNewLeadRecordPage.leadEntity);
			Thread.sleep(1500);
			report.updateTestLog("Lead Page", "Search for the lead record", Status.SCREENSHOT);

			driver.navigate().refresh();
			Thread.sleep(3000);

			// Verify Lead

			By recordSearchBox = By.xpath("//*[@id='crmGrid_findCriteria']");
			By recordLink = By.xpath("//a[@title='" + recordValue + "']");

			UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.frame0);

			UI_Helpers_OOB.waitForElementToBeClickable(driver.getWebDriver(), recordSearchBox, 5);

			WebElement staleElement = driver.findElement(recordSearchBox);

			Actions builder = new Actions(driver.getWebDriver());
			builder.moveToElement(staleElement, 1, 1).click();
			staleElement.sendKeys(recordValue);
			staleElement.sendKeys(Keys.ENTER);
			UI_Helpers_OOB.implicitWait(driver.getWebDriver(), 2);

			report.updateTestLog("Lead Page", "Click on the lead record", Status.SCREENSHOT);

			UI_Helpers_OOB.waitForVisibilityOfElement(driver.getWebDriver(), recordLink, 5);

			WebElement recordElement = driver.findElement(recordLink);
			String recordTitle = recordElement.getAttribute("Title");

			System.out.println("String comparision: " + "Expected- " + recordValue + "/ Actual- " + recordTitle);

			if (recordTitle.equals(recordValue)) {

				UI_Helpers_OOB.waitForElementToBeClickable(driver.getWebDriver(), recordLink, 5);

				WebElement recordElementAction = driver.findElement(recordLink);
				JavascriptExecutor executor = (JavascriptExecutor) driver.getWebDriver();
				executor.executeScript("arguments[0].click();", recordElementAction);
				Thread.sleep(5000);
			}

			else {

				// System.out.println("Record doesn't exist !");
				report.updateTestLog("Lead Page", "Record doesn't exist !", Status.FAIL);
			}

			UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
			Thread.sleep(5000);

			// Qualify the leads for opportunity conversion

			By qualifyButton = By.xpath(
					"//a[@class='ms-crm-Menu-Label']//preceding::img[@class='ms-crm-ImageStrip-ConvertLead_16 ms-crm-commandbar-image16by16']");
			reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), qualifyButton);
			Thread.sleep(12000);

		}

		catch (InterruptedException e) {
			// System.out.println("Record doesn't exist !");
			report.updateTestLog("Lead Page", "InterruptedException !", Status.FAIL);
		} catch (NoSuchElementException ex) {
			// System.out.println("Record doesn't exist !");
			report.updateTestLog("Lead Page", "NoSuchElementException !", Status.FAIL);
		}

		catch (TimeoutException ex) {
			// System.out.println("Record doesn't exist !");
			report.updateTestLog("Lead Page", "TimeoutException !", Status.FAIL);

		}
		report.updateTestLog("Opportunity Page", "Lead qualified for opportunity !!", Status.SCREENSHOT);
	}

}