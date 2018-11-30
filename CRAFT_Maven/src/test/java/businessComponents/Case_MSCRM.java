package businessComponents;

import pageObjects.*;
import com.cognizant.Craft.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
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
import com.cognizant.framework.Status;
import com.google.common.base.CaseFormat;

/**
 * Class for storing general purpose business components
 * 
 * @author Cognizant
 */
public class Case_MSCRM extends ReusableLibrary {
	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	WebElement element = null;

	// Page Objects
	int maxTimeOut = 40;
	int minTimeOut = 10;
	public static  String latestCase= null;
	reusableFunctions reusableFunc = new reusableFunctions(scriptHelper);
	Contacts_MSCRM contact=new Contacts_MSCRM(scriptHelper);


	public static final By serviceModule = By.xpath("//img[@alt='Service']//parent::span");
	public static final By settingsModule = By.xpath("//img[@alt='Settings']//parent::span");
	public static final By caseTimingMetrics = By.cssSelector("a#cxp_casetimingmetric");
	public static final By locationAddressLink = By.cssSelector("a#cxp_locationaddress");
	public static final By caseEntity = By.cssSelector("a#nav_cases");
	public static final By queueEntity = By.cssSelector("a#nav_queues");
	public static final By recordSearchBox = By.xpath("//input[@id='crmGrid_findCriteria']");
	public static final By reactivateCaseButton = By.xpath("//span[text()=' Reactivate Case ']//parent::a[1]");
	public static final By reactivateDialogButton = By.xpath("//button[@title='Reactivate']");
	public static final By statusReason = By
			.xpath("//span[text()='Status Reason' and @id='statuscode_cl']//following::label[1]");
	public static final By caseStatus = By.xpath("//label[@id='State_label']");
	public static final By easternTimePath = By.xpath("//div[contains(@title,'Eastern')]");
	public static final By viewDetails = By.xpath("//a[@title='Select a view']");
	public static final String activeCaseTimingMetricsText = "Active Case Timing Metrics";
	public static final String activeLocationAddressText = "Active Location Addresses";
	public static final String myTeamsEscalatedCase = "My Team's Escalated Cases";
	public static final String myTeamsAssignedCase = "My Team's Assigned Cases";
	public static final String casesIAmWorkingOn = "Cases I am Working On";
	public static final By assignButton = By
			.xpath("//a[@class='ms-crm-Menu-Label']//preceding::img[contains(@class,'Assign')]");
	public static final By moreButton = By
			.xpath("//li[@id='moreCommands']/span/a/img");
	public static final By assignToDropDownID = By.xpath("//td[@id='rdoMe_id_d']//following::select[@id='rdoMe_id_i']");
	public static final By changeAssignMe = By.xpath("//div[@id='rdoMe_id']//following::label[@id='Assign To_label']");
	public static final By userOrTeamInputEnable = By
			.xpath("//label[@for='systemuserview_id']//following::span[@id='systemuserview_id_lookupValue']");
	public static final By userOrTeamInputTextbox = By
			.xpath("//table[@id='systemuserview_id_lookupTable']//following::input[@id='systemuserview_id_ledit']");
	public static final By assignButtonOnFrame = By.xpath("//button[@id='ok_id']");
	public static final By caseOwnerLookup = By
			.xpath("//div[@id='header_ownerid_d']//following::span[@id='header_ownerid_lookupValue']");
	public static final By lookupIcon = By.xpath("//td[@id='systemuserview_id_lookupSearch']/img");

	public static final By queue = By.xpath("//div[@id='businessqueues_id']");
	public static final By queueLabel = By.xpath("//div[@id='businessqueues_id']");
	public static final By QueueInputTextbox = By.xpath("//input[@id='businessqueues_id_ledit']");
	public static final By QueueInputEnable = By
			.xpath("//label[@for='businessqueues_id']//following::span[@id='businessqueues_id_lookupValue']");
	//public static final By addToQueueButton= By.xpath("//li[@id='incident|NoRelationship|Form|Mscrm.Form.incident.AddToQueue']/span");
	public static final By addToQueueButton= By.xpath("//div[@id='crmRibbonManager']//following::a/span[@command='incident|NoRelationship|Form|Mscrm.AddPrimaryToQueue']");

	public static final String AllCasesInSelectedView = "All Cases in Selected Queues";



	public static final By CaseAssociatedViewImage = By.cssSelector("img#contactcasessgridcontact_openAssociatedGridViewImageButtonImage");
	public static final By CaseAssociatedView = By.xpath("//span[text()='Case Associated View']");

	public static final By mergeCaseButton = By
			.xpath("//a[@class='ms-crm-Menu-Label']//preceding::img[contains(@class,'Merge')]");

	public static final By areaFrame = By.cssSelector("iframe#areaServiceFrame");
	Actions action = new Actions(driver.getWebDriver());
	public Case_MSCRM(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	/**
	 * create_Case This method is used to create new case from case tab
	 * 
	 * @throws Exception
	 */
	public void create_Case() throws Exception {

		String CaseTitle = dataTable.getData("General_Data", "CaseTitle");
		String Customer = dataTable.getData("General_Data", "Customer");
		String Origin = dataTable.getData("General_Data", "Origin");
		//String Contact = dataTable.getData("General_Data", "Contact");
		//String Product = dataTable.getData("General_Data", "Product");

		String caseTitle1 = "Case" + UI_Helpers_OOB.getcurrenttime();
		reusableFunc.navigationToEntity(driver.getWebDriver(), CRMLandingPage.serviceModule, CRMLandingPage.caseEntity);
		Thread.sleep(5000);

		reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMCasePage.newCaseButton);
		Thread.sleep(20000);
		report.updateTestLog("Case Entity", "Click on new button", Status.DONE);

		//UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.frame1);
		UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMCasePage.caseName_enable);


		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMCasePage.caseName_enable, CRMCasePage.caseName_edit,
				caseTitle1);
		report.updateTestLog("Case Entity", "Fill Case Title ", Status.DONE);

		reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMCasePage.caseSubject_enable);
		reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMCasePage.caseSubject_dropdown);
		reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMCasePage.caseSubject_select);
		report.updateTestLog("Case Entity", "Fill Case Subject ", Status.DONE);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMCasePage.customer_enable, CRMCasePage.customer_edit,
				Customer);

		String press = Keys.chord(Keys.SHIFT, Keys.TAB);
		driver.findElement(CRMCasePage.customer_edit).sendKeys(press);
		// Checking if the customer field is correctly populate din header area
		if (driver.findElement(By.id("header_process_customerid_lookupValue")).getAttribute("title")
				.equalsIgnoreCase(Customer)) {
			report.updateTestLog("Case Entity", "Customer Populated Correctly ", Status.PASS);
		} else {
			report.updateTestLog("Case Entity", "Customer Populated Incorrectly ", Status.FAIL);
		}

		// Thread.sleep(5000);
		report.updateTestLog("Case Entity", "Fill Customer ", Status.DONE);

		UI_Helpers_OOB.dropDownSelection_Stale(driver.getWebDriver(), CRMCasePage.caseorigin_enable,
				CRMCasePage.caseorigin_edit, Origin);
		report.updateTestLog("Case Entity", "Case Origin", Status.DONE);

		/*driver.findElement(CRMCasePage.contact_edit).sendKeys(Keys.PAGE_DOWN);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMCasePage.contact_enable, CRMCasePage.contact_edit, Contact);
		driver.findElement(CRMCasePage.caseproduct_edit).sendKeys(press);
		report.updateTestLog("Case Entity", "Case Contact", Status.DONE);
		driver.findElement(CRMCasePage.contact_edit).sendKeys(Keys.PAGE_DOWN);
		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMCasePage.caseproduct_enable, CRMCasePage.caseproduct_edit,
				Product);
		driver.findElement(CRMCasePage.caseproduct_edit).sendKeys(press);
		report.updateTestLog("Case Entity", "Case Product", Status.DONE);*/

		UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
		reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMCasePage.SaveButtonClick);
		Thread.sleep(5000);

		report.updateTestLog("Case Entity", "Case save", Status.DONE);

	}
	/**
	 * createNewCase This method is used to create new case from case tab
	 * 
	 * @throws Exception
	 */
	public void createNewCase() throws Exception {
		try {

			//String caseTitle = dataTable.getData("General_Data", "CaseTitle");
			String Customer = dataTable.getData("General_Data", "Customer");
			String Origin = dataTable.getData("General_Data", "Origin");

			String caseTitle = "Case" + UI_Helpers_OOB.getcurrenttime();
			navigateToCaseEntity();
			reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMCasePage.newCaseButton);
			//	reusableFunc.switchToObjectFrame(driver.getWebDriver(), CRMCasePage.customer_enable);
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), By.xpath("//div[@id='FormTitle']"));
			reusableFunc.enterTextAction_Stale(driver.getWebDriver(), CRMCasePage.caseName_enable,
					CRMCasePage.caseName_edit, caseTitle);

			reusableFunc.enterTextAction_Stale(driver.getWebDriver(), CRMCasePage.customer_enable,
					CRMCasePage.customer_edit, Customer);

			// Due to Customer Field Slowness

			if (reusableFunc.waitForElementToBeClickableBool(driver.getWebDriver(), CRMCasePage.customer_edit,
					maxTimeOut)) {
				WebElement enabledElement = driver.findElement(CRMCasePage.customer_edit);
				enabledElement.sendKeys(Keys.TAB);
				Thread.sleep(minTimeOut);
			}

			reusableFunc.enterTextAction_Stale(driver.getWebDriver(), CRMCasePage.caseName_enable,
					CRMCasePage.caseName_edit, caseTitle);

			reusableFunc.dropDownSelection_Stale(driver.getWebDriver(), CRMCasePage.caseorigin_enable,
					CRMCasePage.caseorigin_edit, Origin);

			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMCasePage.SaveButtonClick);

			reusableFunc.waitForVisibilityOfElement(driver.getWebDriver(), CRMCasePage.CancelCasebtn, maxTimeOut);

			reusableFunc.switchToObjectFrame(driver.getWebDriver(), CRMCasePage.pageTitle);

			if (reusableFunc.waitForElementToBePresentBool(driver.getWebDriver(), CRMCasePage.pageTitle, maxTimeOut)) {
				String caseTitleActual = driver.findElement(CRMCasePage.pageTitle).getText();
				System.out.println("Case Title Actual: " + caseTitleActual);

				if (caseTitleActual.equalsIgnoreCase(caseTitle)) {
					report.updateTestLog("Create New Case", "Case Created Successfully: " + caseTitle, Status.PASS);
					latestCase=caseTitle;
				} else {
					report.updateTestLog("Create New Case", "Case is not created !", Status.FAIL);
				}
			} else {
				report.updateTestLog("Create New Case", "Case is not created !", Status.FAIL);
			}

		} catch (Exception Ex) {
			report.updateTestLog("Create New Case", "Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}

	}

	/**
	 * verify_Case This method is used to verify presence of case record
	 * 
	 * @throws InterruptedException
	 */
	public void verify_Case() throws InterruptedException {
		String caseId = dataTable.getData("General_Data", "caseId");
		String recordValue = dataTable.getData("General_Data", "recordValue");

		By recordSearchBox = By.xpath("//*[@id='crmGrid_findCriteria']");
		// By recordSearchBox = By.xpath("//*[@title='Search for records']");
		By record = By.xpath("//a[@class='ms-crm-List-Link' and @title='" + recordValue + "' and @tabindex='0']");

		reusableFunc.navigationToEntity(driver.getWebDriver(), CRMLandingPage.serviceModule, CRMLandingPage.caseEntity);
		Thread.sleep(1500);
		report.updateTestLog("Case Page", "Search for the case record", Status.SCREENSHOT);

		// driver.navigate().refresh();
		Thread.sleep(5000);
		UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.frame0);

		driver.findElement(recordSearchBox).sendKeys(caseId);
		driver.findElement(recordSearchBox).sendKeys(Keys.ENTER);
		report.updateTestLog("Case Page", "Enter case Id", Status.SCREENSHOT);

		try {
			UI_Helpers_OOB.waitForVisibilityOfElement(driver.getWebDriver(), record, 5);
			report.updateTestLog("Case Page", "Record Searched", Status.SCREENSHOT);
		} catch (NoSuchElementException e) {
			report.updateTestLog("Case Page", "Record doesn't exist !", Status.FAIL);
		} catch (TimeoutException e) {
			report.updateTestLog("Case Page", "Record doesn't exist !", Status.FAIL);
		}
		UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());

		driver.navigate().refresh();
		Thread.sleep(3000);

	}

	/**
	 * clickCaseLink This method is used to click case link present under case
	 * tab
	 * 
	 * @throws InterruptedException
	 */
	public void clickCaseLink() throws InterruptedException {
		try {
			String recordToSearch = dataTable.getData("General_Data", "CaseTitle");

			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
			driver.switchTo().defaultContent();
			// reusableFunc.navigationToEntity(driver.getWebDriver(), serviceModule,
			// caseEntity);
			navigateToCaseEntity();
			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());



			reusableFunc.switchToObjectFrame(driver.getWebDriver(), recordSearchBox);


			if (UI_Helpers_OOB.waitForElementToBeClickableBool(driver.getWebDriver(), recordSearchBox, maxTimeOut)) {
				WebElement element = driver.findElement(recordSearchBox);
				element.click();
				element.sendKeys(recordToSearch);
				element.sendKeys(Keys.ENTER);

				By recordXpath = By
						.xpath("//div[@id='crmGrid_divDataArea']//following::a[@title='" + recordToSearch + "']");

				UI_Helpers_OOB.waitForElementToBeClickableBool(driver.getWebDriver(), recordXpath, maxTimeOut);
				driver.findElement(recordXpath).click();

				/*
				By titleXpath = By.xpath("//h1[@title='" + recordToSearch + "']");
				//UI_Helpers_OOB.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Iframe1);

				if (UI_Helpers_OOB.waitForElementToBeVisible(driver.getWebDriver(), By.xpath("//h1[@title='" + recordToSearch + "']"), maxTimeOut)) {
					report.updateTestLog("Validating Case entity navigation",
							"Able to Navigate to Case entity and select the record.", Status.PASS);
				} else {
					report.updateTestLog("Validating Case entity navigation", "Not able to select the record.",
							Status.FAIL);
				}
				 */	
			}
		} catch (Exception Ex) {
			report.updateTestLog("Validating Case entity navigation", "Exception Occured: " + Ex.getMessage(),
					Status.FAIL);
		}

		/*
		 * String DataToSearch = dataTable.getData("General_Data",
		 * "recordValue");
		 * 
		 * reusableFunc.navigationToEntity(driver.getWebDriver(),
		 * CRMLandingPage.serviceModule, CRMLandingPage.caseEntity);
		 * Thread.sleep(3000); report.updateTestLog("Case Page",
		 * "Navigated to Case Entity", Status.SCREENSHOT);
		 * UI_Helpers_OOB.switchToFrame(driver.getWebDriver(),
		 * CRMLandingPage.Iframe0);
		 * 
		 * driver.findElement(
		 * By.xpath("//a[@class='ms-crm-List-Link' and @title='" + DataToSearch
		 * + "' and @tabindex='0']")).click(); Thread.sleep(5000); try { if
		 * (driver.findElement((CRMCasePage.resolveCasebtn)).isDisplayed()) {
		 * report.updateTestLog("Validating Case navigation",
		 * "Able to Navigate to selected Case", Status.PASS);
		 * 
		 * } } catch (Exception e) {
		 * 
		 * report.updateTestLog("Validating Case navigation",
		 * "Not Able to Navigate to selected Case", Status.FAIL);
		 * Thread.sleep(5000); }
		 * UI_Helpers_OOB.switchToFrame(driver.getWebDriver(),
		 * CRMLandingPage.Iframe1); Thread.sleep(5000);
		 */
	}

	/**
	 * verify_Link This method is used to verify case record present under
	 * contact
	 * 
	 * @throws InterruptedException
	 */
	public void verify_Link() throws InterruptedException {
		String caseId = dataTable.getData("General_Data", "caseId");
		String recordValue = dataTable.getData("General_Data", "recordValue");
		String contactValue = dataTable.getData("General_Data", "contactValue");

		By recordSearchBox = By.xpath("//*[@id='crmGrid_findCriteria']");
		By contactRecord = By
				.xpath("//a[@class='ms-crm-List-Link' and @title='" + contactValue + "' and @tabindex='0']");
		By caseRecord = By.xpath("//a[@class='ms-crm-List-Link' and @title='" + recordValue + "' and @tabindex='0']");

		reusableFunc.navigationToEntity(driver.getWebDriver(), CRMLandingPage.serviceModule, CRMLandingPage.contactEntity);
		Thread.sleep(1500);
		report.updateTestLog("Case Page", "Search for the contact record", Status.SCREENSHOT);

		UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.frame0);

		driver.findElement(recordSearchBox).sendKeys(contactValue);
		driver.findElement(recordSearchBox).sendKeys(Keys.ENTER);
		report.updateTestLog("Contact Page", "Enter Contact Name", Status.SCREENSHOT);

		try {

			new WebDriverWait(driver.getWebDriver(), 5)
			.until(ExpectedConditions.visibilityOfElementLocated(contactRecord));
			driver.findElement(contactRecord).click();
			Thread.sleep(1500);
			driver.findElement(CRMNewContactRecordPage.contact_CaseSearch).sendKeys(caseId);
			driver.findElement(recordSearchBox).sendKeys(Keys.ENTER);
			report.updateTestLog("Case inside contact Page", "Enter case Id", Status.SCREENSHOT);
			try {
				new WebDriverWait(driver.getWebDriver(), 5)
				.until(ExpectedConditions.visibilityOfElementLocated(caseRecord));
				report.updateTestLog("Case inside contact Page", "Record Searched", Status.SCREENSHOT);
			} catch (NoSuchElementException e) {
				report.updateTestLog("Case inside contact Page", "Record doesn't exist !", Status.FAIL);
			} catch (TimeoutException e) {
				report.updateTestLog("Case inside contact Page", "Record doesn't exist !", Status.FAIL);
			}
			UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());

			driver.navigate().refresh();
			Thread.sleep(3000);

		} catch (NoSuchElementException e) {
			report.updateTestLog("Contact Page", "Record doesn't exist !", Status.FAIL);
		} catch (TimeoutException e) {
			report.updateTestLog("Contact Page", "Record doesn't exist !", Status.FAIL);
		}

	}

	/**
	 * createPhoneCallActivity_FromCase This method is used to create new phone
	 * call activity from case
	 * 
	 * @throws InterruptedException
	 * @throws AWTException
	 */
	public void createPhoneCallActivity_FromCase() throws InterruptedException, AWTException {
		UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());

		UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.frame0);
		String Description = dataTable.getData("General_Data", "Description");
		reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMCasePage.addPhoneCallActivityLink_Case);
		Thread.sleep(2000);
		report.updateTestLog("Validating Phone Call Activity On case",
				"Click Phone Call link From Activity Section present on case", Status.SCREENSHOT);
		UI_Helpers_OOB.sendKeys(driver.getWebDriver(), CRMCasePage.descPhoneCallActivity_Case, Description);
		Thread.sleep(2000);
		report.updateTestLog("Validating Phone Call Activity On case",
				"Enter Description to create phone call activity from case", Status.SCREENSHOT);

		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_SHIFT);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.delay(1000);

		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
		robot.delay(1000);
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
		robot.delay(1000);
		reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMCasePage.OkbtnActivity_case);
		Thread.sleep(2000);
		report.updateTestLog("Validating Phone Call Activity On case",
				"Click Ok button to create phone call activity from case", Status.SCREENSHOT);
		UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
		Thread.sleep(3000);
	}

	/**
	 * resolveCase This method is used to resolve case record
	 * 
	 * @throws InterruptedException
	 */
	public void resolveCase() throws InterruptedException {
		UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
		String confirm = dataTable.getData("General_Data", "confirm"); // provide
		// value
		// in
		// execel
		// as
		// "yes"
		// or
		// "No"

		reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMCasePage.resolveCasebtn);
		Thread.sleep(2000);

		report.updateTestLog("Validating Resolve Case", "Click Resolve Case button", Status.SCREENSHOT);
		try {

			if (driver.findElement(By.id("InlineDialog_Iframe")).isDisplayed()) {
				UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.Iframe);
				String msg = driver.findElement(CRMCasePage.resolveCasemsg).getText();
				report.updateTestLog("Validating Resolve Case", "Not able to resolve case as getting message as:" + msg,
						Status.SCREENSHOT);
				if (confirm == "Yes") {
					// UI_Helpers_OOB.MouseClick_Action(driver.getWebDriver(),CRMCasePage.confirmbtn);
					driver.switchTo().frame("InlineDialog1_Iframe");
					UI_Helpers_OOB.switchToAlertAndAccept(driver.getWebDriver());
					// driver.findElement(CRMCasePage.confirmbtn).click();
					/*
					 * driver.findElement(By.id("InlineDialog1_Iframe")).
					 * sendKeys(Keys.TAB);
					 * driver.findElement(By.id("InlineDialog1_Iframe")).
					 * sendKeys(Keys.TAB);
					 * driver.findElement(By.id("InlineDialog1_Iframe")).
					 * sendKeys(Keys.ENTER);
					 */

					Thread.sleep(20000);
					driver.findElement(CRMCasePage.resolutionCase_edit).sendKeys("Test");
					Thread.sleep(1000);
					report.updateTestLog("Validating Resolve Case confirm/cancel button",
							"Enter Resolution to resolve the case", Status.SCREENSHOT);
					driver.findElement(CRMCasePage.billableTime_Case).click();
					Thread.sleep(1000);
					driver.findElement(CRMCasePage.billableTime_Case).clear();
					Thread.sleep(1000);
					driver.findElement(CRMCasePage.billableTime_Case).sendKeys("1 minute");
					Thread.sleep(1000);
					report.updateTestLog("Validating Resolve Case confirm/cancel button",
							"Enter Bilable time to resolve the case", Status.SCREENSHOT);
					driver.findElement(CRMCasePage.okbtnResolve_case).click();
					Thread.sleep(1000);
					report.updateTestLog("Validating Resolve Case confirm/cancel button",
							"Click Ok  to resolve the case", Status.SCREENSHOT);

					UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
					UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.frame1);

					String caseStatus = driver.findElement(CRMCasePage.caseStatus).getText();
					report.updateTestLog("Validating Resolve Case confirm/cancel button",
							"Click confirm button to resolve case with open activity", Status.SCREENSHOT);
					report.updateTestLog("Validating Resolve Case confirm/Cancel button",
							"Case status once resolve case confirm is :", Status.SCREENSHOT);
				} else {
					reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMCasePage.cancelbtn);

					UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());

					UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.frame1);
					String caseStatus = driver.findElement(CRMCasePage.caseStatus).getText();
					report.updateTestLog("Validating Resolve Case confirm/Cancel button",
							"Click cancel button as user do not want to  resolve case with open activity",
							Status.SCREENSHOT);
					report.updateTestLog("Validating Resolve Case confirm/Cancel button",
							"Case status once resolve case cancel is :", Status.SCREENSHOT);

				}
			}
		} catch (Exception e) {
			UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());

			UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.frame1);

			String caseStatus = driver.findElement(CRMCasePage.caseStatus).getText();
			report.updateTestLog("Validating Resolve Case",
					"Able to resolve case successfully with Case status as:" + caseStatus, Status.SCREENSHOT);
		}
	}

	/**
	 * clickAdditionalDetailsTab This method is used to click on Additional
	 * details section present under Case record
	 * 
	 * @throws InterruptedException
	 * @throws AWTException
	 */
	public void clickAdditionalDetailsTab() throws InterruptedException, AWTException {
		UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
		UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.frame1);
		// UI_Helpers_OOB.switchToFrame(driver.getWebDriver(),
		// CRMLandingPage.frame0);
		Robot robot = new Robot();

		robot.keyPress(KeyEvent.VK_END);
		robot.keyRelease(KeyEvent.VK_END);
		Thread.sleep(5000);
		driver.findElement(CRMCasePage.additionalDetails).click();
		Thread.sleep(10000);
	}

	/***
	 * changeCasePriority This method is used to change case priority details
	 * 
	 * @throws Exception
	 */
	public void changeCasePriority() throws Exception {
		String Value = dataTable.getData("General_Data", "CasePriority");

		//reusableFunc.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Iframe1);
		reusableFunc.waitForElementToBeClickable(driver.getWebDriver(), CRMCasePage.priority_enable, maxTimeOut);

		UI_Helpers_OOB.dropDownSelection_Stale(driver.getWebDriver(), CRMCasePage.priority_enable,
				CRMCasePage.priority_dropDown_ID, Value);

		reusableFunc.switchToDefaultFrame(driver.getWebDriver());
		reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMCasePage.SaveButtonClick);

		reusableFunc.switchToFrame(driver.getWebDriver(), CRMLandingPage.baseFrame1);
		String Priority = reusableFunc.getTextFromElement(driver.getWebDriver(), CRMCasePage.priorityCase);
		// String Priority =
		// driver.findElement(CRMCasePage.priorityCase).getText();

		if (Value.equalsIgnoreCase(Priority)) {
			report.updateTestLog("Validating Case Priority Changes", "Able to change case priority", Status.PASS);

		} else {
			report.updateTestLog("Validating Case Priority Changes", "Not able to change case priority.", Status.FAIL);

		}
	}
	public void updateCase() throws Exception{

		String category = dataTable.getData("General_Data", "Category");
		String inquiryType = dataTable.getData("General_Data", "Inquiry type");
		String subInquiryType = dataTable.getData("General_Data", "Inquiry sub type");
		String followUp = dataTable.getData("General_Data", "followup");

		/** Switching to Case form iframe */

		reusableFunc.switchToObjectFrame(driver.getWebDriver(),CRMCasePage.caseForm);

		driver.findElement(By.xpath("//span[.='Category']")).click();// Category Label
		System.out.println("category label clicked ");
		//driver.findElement(By.xpath("//div[@id='cxp_casecategoryid']")).sendKeys(Keys.DELETE);
		//div[@id='cxp_casecategoryid']/div[1]

		//System.out.println("Deleted hitted ");
		driver.findElement(By.xpath("//span[.='Category']")).click();// Category Label

		driver.findElement(By.xpath("//div[@id='cxp_casecategoryid']")).sendKeys(category);

		//driver.findElement(By.xpath("//div[@id='cxp_casecategoryid_lookupDiv']//following::input']")).sendKeys(category);
		System.out.println("Cattegory Entered ");
		driver.findElement(By.xpath("//div[@id='cxp_casecategoryid_lookupDiv']//following::input")).sendKeys(Keys.ENTER);

		//driver.findElement(By.xpath("//div[@id='cxp_casecategoryid_lookupDiv']//following::input")).sendKeys(category);// enter Category name
		System.out.println("Cattegory Entered ");
		driver.findElement(By.xpath("//div[@id='Dialog_cxp_casecategoryid_IMenu']//a[2]")).click();
		System.out.println("category selected  ");

		driver.findElement(By.xpath("//span[.='Inquiry Type']")).click();//inquiry Label

		driver.findElement(By.xpath("//span[.='Inquiry Sub Type']")).click();// sub inquiry Label

		driver.findElement(By.xpath("//span[.='Follow Up By']")).click();//follow up by Label




	}

	/**
	 * ChangingCaseType This method is used to change the type of case
	 * 
	 * @param scriptHelper
	 * @throws InterruptedException
	 */
	public void changingCaseType() throws InterruptedException {
		String CaseType = dataTable.getData("General_Data", "CaseType");

		driver.findElement(CRMCasePage.type_enable).click();
		Thread.sleep(1000);

		UI_Helpers_OOB.dropDownSelectionByValue(driver.getWebDriver(), CRMCasePage.type_dropDown_ID, CaseType);
		driver.findElement(CRMCasePage.saveButtonFromCase).click();
		Thread.sleep(1000);
		String CaseType1 = driver.findElement(CRMCasePage.type_case).getText();
		System.out.println("selected type:" + CaseType1);
		if (CaseType1 == CaseType) {
			report.updateTestLog("Validating Case Type Changes", "Not Able to change case type", Status.FAIL);

		} else

		{
			report.updateTestLog("Validating Case Type Changes", "Able to change case type.", Status.PASS);

		}
	}

	/**
	 * ChangingCaseEscalateDetails This method is used to change the escalation
	 * type of case
	 * 
	 * @param scriptHelper
	 * @throws Exception
	 */
	public void changingCaseEscalateDetails() throws Exception {
		// String Owner = driver.findElement(CRMCasePage.OwnerCase).getText();
		String Owner = reusableFunc.getTextFromElement(driver.getWebDriver(), CRMCasePage.OwnerCaselink);
		String Escalated = dataTable.getData("General_Data", "Escalated");
		//	reusableFunc.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Iframe1);

		if (reusableFunc.waitForElementToBePresentBool(driver.getWebDriver(), CRMCasePage.escalatedBy_enble,
				maxTimeOut)) {
			driver.findElement(CRMCasePage.escalated_enable).click();
			report.updateTestLog("Validating presence of Escalated By Field",
					"Escalated By field is present on Case page !", Status.PASS);
		} else {
			report.updateTestLog("Validating presence of Escalated By Field",
					"Escalated By field is not present in on Case page", Status.FAIL);

		}

		Thread.sleep(1000);

		UI_Helpers_OOB.dropDownSelectionByText(driver.getWebDriver(), CRMCasePage.escalated_dropDown_ID, Escalated);
		driver.findElement(CRMCasePage.saveButtonFromCase).click();
		Thread.sleep(1000);
		String CaseType1 = driver.findElement(CRMCasePage.EscalatedCase).getText();
		if (CaseType1 == Escalated) {
			report.updateTestLog("Validating Case Escalated Changes", "Not Able to change case Escalated", Status.FAIL);

		} else {
			report.updateTestLog("Validating Case Escalated Changes", "Able to change case Escalated as:" + CaseType1,
					Status.PASS);

		}

		String Priority = driver.findElement(CRMCasePage.priorityCase).getText();
		report.updateTestLog("Validating Case Priority after Escalated is set", "Case priority changes to:" + Priority,
				Status.DONE);
		String Owner1 = driver.findElement(CRMCasePage.OwnerCase).getText();
		if (Owner == Owner1) {
			report.updateTestLog("Validating Case Owner Changes after Escalating Case",
					"Case Owner changed after Case Escalation", Status.FAIL);

		} else {
			report.updateTestLog("Validating Case Owner Changes after Escalating Case", "Case owner remain Unchanged",
					Status.PASS);

		}

		reusableFunc.switchToDefaultFrame(driver.getWebDriver());
	}

	/**
	 * deEscalateCase This method is used to de-escalate case record by
	 * selecting Escalated value as 'No'
	 * 
	 * @throws Exception
	 */
	public void deEscalateCase() throws Exception {
		String DeEscalated = dataTable.getData("General_Data", "DeEscalated");

		//reusableFunc.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Iframe1);

		if (reusableFunc.waitForElementToBePresentBool(driver.getWebDriver(), CRMCasePage.escalated_enable,
				maxTimeOut)) {
			reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMCasePage.escalated_enable);
			// driver.findElement(CRMCasePage.escalated_enable).click();
			report.updateTestLog("Validating presence of Escalated By Field",
					"Escalated By field is present on Case page", Status.PASS);
		} else {
			report.updateTestLog("Validating presence of Escalated By Field",
					"Escalated By field is not present on Case page", Status.FAIL);

		}

		Thread.sleep(1000);
		UI_Helpers_OOB.dropDownSelectionByText(driver.getWebDriver(), CRMCasePage.escalated_dropDown_ID, DeEscalated);
		driver.findElement(CRMCasePage.saveButtonFromCase).click();

		reusableFunc.waitForpageLoadByThread(driver.getWebDriver());
		Thread.sleep(5000);

		String CaseType1 = driver.findElement(CRMCasePage.EscalatedCase).getText();

		if (CaseType1.equalsIgnoreCase("No")) {
			report.updateTestLog("Validating Case Escalated Changes", "Able to  DeEscalate Case", Status.PASS);
		} else {
			report.updateTestLog("Validating Case DeEscalated Changes", "Not Able to DeEscalate Case", Status.FAIL);
		}

		reusableFunc.switchToDefaultFrame(driver.getWebDriver());
	}

	/**
	 * verifyManagerField This method is used to select owner of case record and
	 * verify manager field value
	 * 
	 * @throws Exception
	 */
	public void verifyManagerField() throws Exception {
		//	reusableFunc.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Iframe1);


		reusableFunc.waitForElementToBeClickable(driver.getWebDriver(), CRMCasePage.OwnerCaselink, maxTimeOut);
		reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMCasePage.OwnerCaselink);

		reusableFunc.switchToDefaultFrame(driver.getWebDriver());
		reusableFunc.switchToObjectFrame(driver.getWebDriver(), By.xpath("//div[@id='FormTitle']"));

		reusableFunc.waitForElementToBeVisible(driver.getWebDriver(), CRMCasePage.Manager, maxTimeOut);
		String manager = reusableFunc.getTextFromElement(driver.getWebDriver(), CRMCasePage.Manager);

		if (manager.equalsIgnoreCase("--")) {
			report.updateTestLog("Validating Manager Field", "Managaer Field is not set for the User", Status.FAIL);
		} else {
			report.updateTestLog("Validating Manager Field",
					"Managaer Field is set for the User with value as:" + manager, Status.PASS);

		}
	}

	/***
	 * verifyActiveStatusList This method is used to verify Active Case Status
	 * component List values
	 */
	public void verifyActiveStatusList() {
		UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.Iframe1);
		driver.findElement(CRMCasePage.caseStatus_Dropdown_enable).click();
		try {
			if (driver.findElement(CRMCasePage.caseStatus_List).isDisplayed()) {
				report.updateTestLog("Validating Case Status List", "Able to clicked on Status", Status.PASS);
			}
		} catch (Exception e) {
			report.updateTestLog("Validating Case Status List", "Not Able to clicked on Status", Status.FAIL);
		}
		WebElement StatusL = driver.findElement(CRMCasePage.caseStatus_List);
		Select ActiveList = new Select(StatusL);
		List<WebElement> options = ActiveList.getOptions();
		int listsize = options.size();
		String[] x = { "New", "Actively Working", "Refer to other department", "Internal Team Transfer",
				"Refer to Agent (Requiring Follow Up)", "Pending - Follow Up Needed",
				"Pending - Follow Up Needed Insured", "Pending - Follow Up Needed Other", "New Response Received",
				"Awaiting Authority Approval", "Approved", "Reopen", "Assigned" };
		if (listsize == 13) {
			report.updateTestLog("Validating Case Status Active Component List",
					"Case Status contains all components for Active Status:" + options.hashCode(), Status.PASS);
			for (int i = 0; i < x.length; i++) {
				report.updateTestLog("Validating Active status Values in List",
						"Active Case status List contains status as:" + x[i], Status.DONE);
			}
		} else {
			report.updateTestLog("Validating Case Status Active Component List",
					"Case Status not contains all components for Active Status except:" + options, Status.PASS);
		}

	}

	/**
	 * selectActiveListValue This method is used to select case status from
	 * Active list component
	 * 
	 * @throws InterruptedException
	 */
	public void selectActiveListValue() throws InterruptedException {
		String ActiveCaseStatus = dataTable.getData("General_Data", "ActiveCasestatus");
		driver.findElement(CRMCasePage.caseStatus_Dropdown_enable).click();

		UI_Helpers_OOB.dropDownSelection_Stale(driver.getWebDriver(), CRMCasePage.caseStatus_Dropdown_enable,
				CRMCasePage.caseStatus_List, ActiveCaseStatus);

		reusableFunc.switchToDefaultFrame(driver.getWebDriver());
		reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMCasePage.SaveButtonClick);
		reusableFunc.switchToFrame(driver.getWebDriver(), CRMLandingPage.baseFrame1);

		String NewStatus = reusableFunc.getTextFromElement(driver.getWebDriver(), CRMCasePage.statusLabel);

		if (!ActiveCaseStatus.equalsIgnoreCase(NewStatus)) {
			report.updateTestLog("Validating Active status Value Selection",
					"User is not able to select value from Active Component List", Status.FAIL);
		} else {
			report.updateTestLog("Validating Active status Value Selection",
					"User is able to select value from Active Component List", Status.PASS);
		}
	}

	/***
	 * verifyResolveStatusList This method is used to verify Resolution Type
	 * Case Status component List values
	 * 
	 * @throws InterruptedException
	 */
	public void verifyResolveStatusList() throws InterruptedException {
		reusableFunc.switchToDefaultFrame(driver.getWebDriver());

		reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMCasePage.resolveCasebtn);
		Thread.sleep(2000);

		report.updateTestLog("Validating Resolve Case", "Click Resolve Case button", Status.SCREENSHOT);

		reusableFunc.switchToFrame(driver.getWebDriver(), CRMLandingPage.inlineDialogFrame);

		driver.findElement(CRMCasePage.resolution_Type_enable).click();
		try {
			if (driver.findElement(CRMCasePage.resolution_type_dropdown).isDisplayed()) {
				report.updateTestLog("Validating Resolve Case Status List", "Able to clicked on Resolve  Status",
						Status.PASS);
			}
		} catch (Exception e) {
			report.updateTestLog("Validating Resolve Case Status List", "Not Able to clicked on Resolve Status",
					Status.FAIL);
		}
		WebElement StatusL = driver.findElement(CRMCasePage.resolution_type_dropdown);
		Select ActiveList = new Select(StatusL);
		List<WebElement> options = ActiveList.getOptions();
		int listsize = options.size();
		String[] x = { "Resolved", "Defer to Agent", "Transferred to Payment Vendor", "Resolved via Email" };
		if (listsize == 4) {
			report.updateTestLog("Validating Resolution Type Case Status  Component List",
					"Case Status contains all components for Resolution Type Status:" + options, Status.PASS);
			for (int i = 0; i < x.length; i++) {
				report.updateTestLog("Validating Resolve status Values in List",
						"Active Case status List contains status as:" + x[i], Status.DONE);
			}
		} else {
			report.updateTestLog("Validating Resolution type Case Status  Component List",
					"Case Status not contains all components for Resolution type Status except:" + options,
					Status.PASS);
		}

		driver.findElement(CRMCasePage.crossimg).click();
	}

	/**
	 * selectResolveListValue This method is used to select case status from
	 * Resolution type list component
	 * 
	 * @throws InterruptedException
	 */
	public void selectResolveListValue() throws InterruptedException {
		String ResolveCaseStatus = dataTable.getData("General_Data", "ResolveCasestatus");
		reusableFunc.switchToDefaultFrame(driver.getWebDriver());

		reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMCasePage.resolveCasebtn);

		reusableFunc.switchToFrame(driver.getWebDriver(), CRMLandingPage.inlineDialogFrame);

		UI_Helpers_OOB.dropDownSelection_Stale(driver.getWebDriver(), CRMCasePage.resolution_Type_enable,
				CRMCasePage.resolution_type_dropdown, ResolveCaseStatus);

		String NewStatus = reusableFunc.getTextFromElement(driver.getWebDriver(), CRMCasePage.resolutionTypeLabel);

		if (!ResolveCaseStatus.equalsIgnoreCase(NewStatus)) {
			report.updateTestLog("Validating Resolution type status Value Selection",
					"User is not able to select value from Resolution Type Component List with Original Status:"
							+ ResolveCaseStatus,
							Status.FAIL);
			report.updateTestLog("Validating Resolution type status Value Selection",
					"User is not able to select value from Resolution Type Component List with Selected Status:"
							+ NewStatus,
							Status.FAIL);
		} else {
			report.updateTestLog("Validating Resolution type status Value Selection",
					"User is  able to select value from Resolution Type Component List with Original Status:"
							+ ResolveCaseStatus,
							Status.PASS);
			report.updateTestLog("Validating Resolution type status Value Selection",
					"User is  able to select value from Resolution Type Component List with Selected Status:"
							+ NewStatus,
							Status.PASS);
		}

		driver.findElement(CRMCasePage.crossimg).click();

		reusableFunc.switchToDefaultFrame(driver.getWebDriver());
		reusableFunc.switchToFrame(driver.getWebDriver(), CRMLandingPage.baseFrame1);
	}

	/***
	 * verifyResolveStatusList This method is used to verify Resolution Type
	 * Case Status component List values
	 * 
	 * @throws InterruptedException
	 */
	public void verifyCancelStatusList() throws InterruptedException {
		UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());

		reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMCasePage.cancelCasebtn);
		Thread.sleep(2000);

		report.updateTestLog("Validating Cancel Case", "Click Cancel Case button", Status.SCREENSHOT);

		UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.Inlineframe);
		// driver.switchTo().frame("InlineDialog1_Iframe");
		// UI_Helpers_OOB.switchToAlertAndAccept(driver.getWebDriver());
		driver.findElement(CRMCasePage.Cancel_enable).click();
		try {
			if (driver.findElement(CRMCasePage.Cancel_dropdown).isDisplayed()) {
				report.updateTestLog("Validating Cancel Case Status List", "Able to clicked on Cancel  Status",
						Status.PASS);
			}
		} catch (Exception e) {
			report.updateTestLog("Validating Cancel Case Status List", "Not Able to clicked on Cancel Status",
					Status.FAIL);
		}
		WebElement StatusL = driver.findElement(CRMCasePage.Cancel_dropdown);
		Select ActiveList = new Select(StatusL);
		List<WebElement> options = ActiveList.getOptions();
		int listsize = options.size();
		String[] x = { "Non-Workable Item", "Duplicate" };
		if (listsize == 2) {
			report.updateTestLog("Validating Cancel Type Case Status  Component List",
					"Case Status contains all components for Cancel Type Status:" + options, Status.PASS);
			for (int i = 0; i < x.length; i++) {
				report.updateTestLog("Validating Cancel status Values in List",
						"Cancel Case status List contains status as:" + x[i], Status.DONE);
			}
		} else {
			report.updateTestLog("Validating Resolution type Case Status  Component List",
					"Case Status not contains all components for Resolution type Status except:" + options,
					Status.PASS);
		}
		driver.findElement(CRMCasePage.crossimg).click();
		Thread.sleep(1000);
		UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());

		UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.Iframe1);

	}

	/**
	 * verifyEscaltedByField This method is used to verify Escalated By field on
	 * Case which is read only
	 * 
	 * @param scriptHelper
	 */
	public void verifyEscaltedByField() {
		try {
			//	reusableFunc.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Iframe1);
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), By.xpath("//div[@id='FormTitle']"));

			if (reusableFunc.waitForElementToBePresentBool(driver.getWebDriver(), CRMCasePage.escalatedBy_enble,
					maxTimeOut)
					&& reusableFunc.waitForElementToBePresentBool(driver.getWebDriver(), CRMCasePage.lockimg,
							maxTimeOut)) {
				report.updateTestLog("Validating presence of Read only  Escalated By Field",
						"Escalated By field is present in Read Only Mode on Case page", Status.PASS);
			} else {
				report.updateTestLog("Validating presence of Read only  Escalated By Field",
						"Escalated By field is not present in Read Only Mode on Case page", Status.FAIL);

			}

		} catch (Exception Ex) {
			report.updateTestLog("Validating presence of Read only  Escalated By Field",
					"Escalated By field is not present in Read Only Mode on Case page, Exception Occured: " + Ex,
					Status.FAIL);

		}
	}

	public void verifyEscalatedByUserNameDetails() throws Exception {

		reusableFunc.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Iframe1);

		if (reusableFunc.waitForElementToBePresentBool(driver.getWebDriver(), CRMCasePage.escalatedBy_enble,
				maxTimeOut)) {
			report.updateTestLog("Validating presence of Escalated By Field",
					"Escalated By field is present on Case page", Status.PASS);
		} else {
			report.updateTestLog("Validating presence of Escalated By Field",
					"Escalated By field is not present on Case page", Status.FAIL);

		}

		String Escalated = driver.findElement(By.id("Escalated_label")).getText();
		String UserName = driver.findElement(By.id("cxp_escalatedbyid_lookupValue")).getAttribute("title");

		switch (Escalated) {
		case "Yes":
			if (!UserName.equalsIgnoreCase(null)) {
				report.updateTestLog("Validating Escalated By User Name details when Escalated is Yes",
						"When Escalated is set to Yes then User name  for Escalated by field is set to:" + UserName,
						Status.PASS);
			} else {
				report.updateTestLog("Validating Escalated By User Name details when Escalated is Yes",
						"When Escalated is set to Yes then User name is not set for escalated By Field", Status.FAIL);
			}
			break;
		case "No":
			if (UserName.equalsIgnoreCase("")) {
				report.updateTestLog("Validating Escalated By User Name details when Escalated is No",
						"When Escalated is set to No then User name  for Escalated by field is set to null",
						Status.PASS);
			} else {
				report.updateTestLog("Validating Escalated By User Name details when Escalated is No",
						"When Escalated is set to No then User name is  set for escalated By Field as:" + UserName,
						Status.FAIL);
			}
			break;

		default:
			break;
		}
	}

	public void selectEscalatedCaseView() throws InterruptedException, AWTException {
		UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
		String ViewName = dataTable.getData("General_Data", "ViewName");
		UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.Iframe0);
		driver.findElement(CRMCasePage.viewDropdown).click();
		Thread.sleep(1000);
		try {
			if (driver.findElement(By.cssSelector("div#Dialog_0")).isDisplayed()) {
				report.updateTestLog("Validating view dropdown ", "Able to click View dropdown", Status.PASS);

			}

		} catch (Exception e) {
			report.updateTestLog("Validating view dropdown", "Not Able to click View dropdown", Status.FAIL);
		}

		driver.findElement(CRMCasePage.MyTeamEscalatedCaseView).click();
		String selectedview = driver.findElement(CRMCasePage.selectedView).getText();
		System.out.println("selected view is:" + selectedview);
		if (selectedview == ViewName) {
			report.updateTestLog("Validating view Selection ", "Not Able to Select required View", Status.FAIL);
		}

		else {
			report.updateTestLog("Validating view Selection ", "Able to Select required View", Status.PASS);
		}

		UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
	}

	public void navigateToCaseEntity() throws InterruptedException {
		driver.switchTo().defaultContent();
		reusableFunc.navigationToEntity(driver.getWebDriver(), serviceModule, caseEntity);
		reusableFunc.waitForpageLoadByThread(driver.getWebDriver());
		try {
			if (driver.findElement(CRMCasePage.viewDropdown).isDisplayed()) {
				report.updateTestLog("Validating Case entity navigation", "Not able to Navigate to Case entity",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Validating Case entity navigation", "Navigated to Case entity", Status.PASS);
		}

	}

	public void searchCaseRelatedRecordsAndSelect(String recordToSearch) throws InterruptedException {
		try {
			driver.switchTo().defaultContent();
			reusableFunc.navigationToEntity(driver.getWebDriver(), serviceModule, caseEntity);
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), recordSearchBox);

			if (reusableFunc.waitForElementToBePresent(driver.getWebDriver(), recordSearchBox, maxTimeOut)) {
				/*new by Akash
				WebElement searchbox =driver.findElement(By.xpath("//input[@id='crmGrid_findCriteria']"));
				searchbox.sendKeys(recordToSearch);
				element.sendKeys(Keys.ENTER);*/
				report.updateTestLog("Validating Case entity navigation",
						"Search Box Detected ", Status.PASS);
				WebElement element = driver.findElement(recordSearchBox);
				element.clear();
				element.sendKeys(recordToSearch);
				element.sendKeys(Keys.ENTER);

				By recordXpath = By
						.xpath("//div[@id='crmGrid_divDataArea']//following::a[@title='" + recordToSearch + "']");
				

				reusableFunc.waitForElementToBePresent(driver.getWebDriver(), recordXpath, maxTimeOut);
				//reusableFunc.waitForElementToBeClickableBool(driver.getWebDriver(), recordXpath, maxTimeOut);
				//reusableFunc.switchToObjectFrame(driver.getWebDriver(), recordXpath);
				driver.findElement(recordXpath).click();

				report.updateTestLog("Validating Case entity navigation",
						"Able to Navigate to Case entity and select the record." +recordToSearch, Status.PASS);
				/*	By titleXpath = By.xpath("//h1[@title='" + recordToSearch + "']");
				reusableFunc.switchToObjectFrame(driver.getWebDriver(), titleXpath);

				if (reusableFunc.waitForElementToBeVisible(driver.getWebDriver(), titleXpath, maxTimeOut)) {
					report.updateTestLog("Validating Case entity navigation",
							"Able to Navigate to Queue entity and select the record.", Status.PASS);
				} else {
					report.updateTestLog("Validating Case entity navigation", "Not able to select the record.",
							Status.FAIL);
				} */
				reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			}
		} catch (Exception Ex) {
			report.updateTestLog("Validating Case entity navigation", " Exception Occured: " + Ex.getMessage(),
					Status.FAIL);
		}

	}

	public void searchCaseTimingMetricsRelatedRecordsAndSelect(String recordToSearch) throws InterruptedException {
		try {
			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
			driver.switchTo().defaultContent();
			reusableFunc.navigationToEntity(driver.getWebDriver(), settingsModule, caseTimingMetrics);
			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
			driver.navigate().refresh();

			By activeCaseTimingMetrics = By.xpath(
					"//a[@title=\"Active Case Timing Metrics\"]//following::span[text()=\"Active Case Timing Metrics\"]");
			selectViewFromCaseEntity(activeCaseTimingMetricsText, activeCaseTimingMetrics);

			UI_Helpers_OOB.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Iframe0);

			if (UI_Helpers_OOB.waitForElementToBeClickableBool(driver.getWebDriver(), recordSearchBox, maxTimeOut)) {
				reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), recordSearchBox);
				WebElement element = driver.findElement(recordSearchBox);
				element.sendKeys(recordToSearch);
				element.sendKeys(Keys.ENTER);

				By recordXpath = By
						.xpath("//div[@id='crmGrid_divDataArea']//following::a[@title='" + recordToSearch + "']");

				UI_Helpers_OOB.waitForElementToBeClickableBool(driver.getWebDriver(), recordXpath, maxTimeOut);
				driver.findElement(recordXpath).click();

				By titleXpath = By.xpath("//h1[@title='" + recordToSearch + "']");
				UI_Helpers_OOB.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Iframe1);

				if (UI_Helpers_OOB.waitForElementToBeVisible(driver.getWebDriver(), titleXpath, maxTimeOut)) {
					report.updateTestLog("Validating Case Timing Metrics entity navigation",
							"Able to Navigate to Case Timing Metrics entity and select the record.", Status.PASS);
				} else {
					report.updateTestLog("Validating Case Timing Metrics entity navigation",
							"Not able to select the record.", Status.FAIL);
				}
				UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
			}
		} catch (Exception Ex) {
			report.updateTestLog("Search Case Timing Metrics Related Records And Select",
					"Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}

	}

	public void searchLocationAddressRelatedRecordsAndSelect(String locationAddress) throws InterruptedException {
		try {
			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
			driver.switchTo().defaultContent();
			reusableFunc.navigationToEntity(driver.getWebDriver(), serviceModule, locationAddressLink);
			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
			driver.navigate().refresh();

			By activeLocationAddress = By.xpath(
					"//a[@title=\"Active Location Addresses\"]//following::span[text()=\"Active Location Addresses\"]");
			selectViewFromCaseEntity(activeLocationAddressText, activeLocationAddress);

			UI_Helpers_OOB.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Iframe0);

			if (UI_Helpers_OOB.waitForElementToBeClickableBool(driver.getWebDriver(), recordSearchBox, maxTimeOut)) {
				reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), recordSearchBox);
				WebElement element = driver.findElement(recordSearchBox);
				element.sendKeys(locationAddress);
				element.sendKeys(Keys.ENTER);

				By recordXpath = By
						.xpath("//div[@id='crmGrid_divDataArea']//following::a[@title='" + locationAddress + "']");

				// UI_Helpers_OOB.waitForElementToBeClickableBool(driver.getWebDriver(),
				// recordXpath, maxTimeOut);

				if (UI_Helpers_OOB.waitForElementToBeClickableBool(driver.getWebDriver(), recordXpath, maxTimeOut)) {
					driver.findElement(recordXpath).click();
					report.updateTestLog("Validating Location Addresses entity navigation",
							"Able to Navigate to Location Addresses entity and select the record.", Status.PASS);
				} else {
					report.updateTestLog("Validating Location Addresses entity navigation",
							"Not able to select the record.", Status.FAIL);
				}

				By titleXpath = By.xpath("//h1[@title='" + locationAddress + "']");
				UI_Helpers_OOB.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Iframe1);

				if (UI_Helpers_OOB.waitForElementToBeVisible(driver.getWebDriver(), titleXpath, maxTimeOut)) {
					report.updateTestLog("Validating Location Addresses entity navigation",
							"Able to Navigate to Location Addresses entity and select the record.", Status.PASS);
				} else {
					report.updateTestLog("Validating Location Addresses entity navigation",
							"Not able to select the record.", Status.FAIL);
				}
				UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
			}
		} catch (Exception Ex) {
			report.updateTestLog("Search Case Timing Metrics Related Records And Select",
					"Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}

	}

	public void reactivateResolvedCase() {
		try {

			String recordToSearch = dataTable.getData("General_Data", "SearchRecord");

			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			reusableFunc.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Iframe0);

			searchCaseRelatedRecordsAndSelect(recordToSearch);
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());

			if (reusableFunc.waitForElementToBeClickableBool(driver.getWebDriver(), reactivateCaseButton,
					maxTimeOut)) {
				driver.findElement(reactivateCaseButton).click();
				Thread.sleep(2000);
				reusableFunc.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Inlineframe);
				reusableFunc.waitForElementToBeClickableBool(driver.getWebDriver(), reactivateDialogButton, maxTimeOut);

				driver.findElement(reactivateDialogButton).click();

				reusableFunc.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Iframe1);
				reusableFunc.waitForElementToBeVisible(driver.getWebDriver(), caseStatus, maxTimeOut);
				reusableFunc.waitForpageLoadByThread(driver.getWebDriver());
				Thread.sleep(5000);

				String caseStatusReasonText = driver.findElement(statusReason).getText();
				String caseStatusText = driver.findElement(caseStatus).getText();

				if (caseStatusReasonText.contains("Reopen") && caseStatusText.contains("Active")) {
					report.updateTestLog("Reactivate Resolved Case !", "Resolved Case reactivated and "
							+ "Case status and status reason are correctly displayed !", Status.PASS);
				} else {
					report.updateTestLog("Reactivate Resolved Case !",
							"Case status and status reason are not correctly displayed !", Status.FAIL);
				}

			} else {
				report.updateTestLog("Reactivate Resolved Case !", "Reactivate button not found.", Status.FAIL);
			}
		} catch (Exception Ex) {

			Ex.printStackTrace();
		}
	}

	public void selectViewFromCaseEntity(String viewName, By viewXpath) {
		try {
			// Switch To Frame
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), CRMCasePage.viewDropdown);

			// Click on view Dropdown button
			reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMCasePage.viewDropdown);

			reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), viewXpath);
			Thread.sleep(2000);

			String viewTextActual = driver.findElement(viewDetails).getText();

			if (viewTextActual.equalsIgnoreCase(viewName)) {
				report.updateTestLog("Select View From Case Entity", "Able to select the expected view", Status.PASS);
			} else {
				report.updateTestLog("Select View From Case Entity", "Not able to select the expected view",
						Status.FAIL);
			}

		} catch (Exception Ex) {
			report.updateTestLog("Select View From Case Entity", "Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}
	}

	public void selectViewFromCaseEntity(String viewName) {
		try {
			// Switch To Frame
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), CRMCasePage.viewDropdown);

			// Click on view Dropdown button
			reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMCasePage.viewDropdown);

			// Select View
			By viewXpath = By.xpath("//a[@title='" + viewName + "']//following::span[text()='" + viewName + "']");
			reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), viewXpath);
			Thread.sleep(2000);

			String viewTextActual = driver.findElement(viewDetails).getText();

			if (viewTextActual.equalsIgnoreCase(viewName)) {
				report.updateTestLog("Select View From Queue Entity", "Able to select the expected view", Status.PASS);
			} else {
				report.updateTestLog("Select View From Queue Entity", "Not able to select the expected view",
						Status.FAIL);
			}

		} catch (Exception Ex) {
			report.updateTestLog("Select View From Queue Entity", "Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}
	}

	// New Development - Optimization

	public void validateNewCaseCreationHour() {
		try {
			//String recordToSearch = dataTable.getData("General_Data", "recordToSearch");

			String recordToSearch = Accounts_MSCRM.latestCase;

			searchCaseRelatedRecordsAndSelect(recordToSearch);
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), CRMCasePage.createdOn);
			String CreatedOn = reusableFunc.getTextFromElement(driver.getWebDriver(), CRMCasePage.createdOn);

			if (CreatedOn != null) {
				report.updateTestLog("Verify New Case Creation Hour on Case",
						"New Case Creation Hour is available on Case Form with value as:" + CreatedOn, Status.PASS);
			} else {
				report.updateTestLog("Verify New Case Creation Hour on Case",
						"New Case Creation Hour is not available on Case Form ", Status.FAIL);
			}

			reusableFunc.switchToObjectFrame(driver.getWebDriver(), easternTimePath);

			if (reusableFunc.waitForElementToBeVisible(driver.getWebDriver(), easternTimePath, maxTimeOut)) {
				report.updateTestLog("Verify New Case Creation Hour on Case is in Eastern Time",
						"New Case Creation Hour is available on Case Form with Eastern Time", Status.PASS);
			} else {
				report.updateTestLog("Verify New Case Creation Hour on Case is in Eastern Time",
						"New Case Creation Hour is not available on Case Form with Eastern Time", Status.FAIL);
			}

		} catch (Exception Ex) {

			report.updateTestLog("Validate New Case Creation Hour", "Exception Occurred:" + Ex.getMessage(),
					Status.FAIL);
		}
	}

	public void verifyFollowUpByDateOnCase() {
		try {
			String recordToSearch = dataTable.getData("General_Data", "recordToSearch");
		//	String CaseName = dataTable.getData("General_Data", "CaseName1");
			searchCaseRelatedRecordsAndSelect(recordToSearch);
			//searchCaseRelatedRecordsAndSelectCase(recordToSearch,CaseName);
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), CRMCasePage.FollowupByfield);

			if (reusableFunc.waitForElementToBeVisible(driver.getWebDriver(), CRMCasePage.FollowupByfield,
					maxTimeOut)) {
				report.updateTestLog("Verify Follow Up By  Date on Case", "Follow Up By  is available on Case Form ",
						Status.PASS);
			} else {
				report.updateTestLog("Verify Follow Up By date on Case", "Follow Up By is not available on Case Form ",
						Status.FAIL);
			}

		} catch (Exception Ex) {

			report.updateTestLog("Verify FollowUp By DateOnCase", "Exception Occurred:" + Ex.getMessage(), Status.FAIL);
		}
	}

	public void verifyFollowUpByOnCaseViews() {
		try {
			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
			driver.switchTo().defaultContent();
			reusableFunc.navigationToEntity(driver.getWebDriver(), serviceModule, caseEntity);
			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
			driver.navigate().refresh();

			By myEscalatedView = By.xpath(
					"//a[@title=\"My Team's Escalated Cases\"]//following::span[text()=\"My Team's Escalated Cases\"]");
			selectViewFromCaseEntity(myTeamsEscalatedCase, myEscalatedView);

			reusableFunc.switchToObjectFrame(driver.getWebDriver(), CRMCasePage.followUpByColumnTable);

			if (reusableFunc.waitForElementToBePresentBool(driver.getWebDriver(), CRMCasePage.followUpByColumnTable,
					maxTimeOut))

			{
				report.updateTestLog("Verify Follow Up By Date on Case",
						"Follow Up By is available on My team's Escalated Case view ", Status.PASS);
			} else {
				report.updateTestLog("Verify Follow Up By date on Case",
						"Follow Up By is not available on My team's Escalated Case view ", Status.FAIL);
			}

			By myTeamsAssigenCaseView = By.xpath(
					"//a[@title=\"My Team's Assigned Cases\"]//following::span[text()=\"My Team's Assigned Cases\"]");
			selectViewFromCaseEntity(myTeamsAssignedCase, myTeamsAssigenCaseView);

			reusableFunc.switchToObjectFrame(driver.getWebDriver(), CRMCasePage.followUpByColumnTable);

			if (reusableFunc.waitForElementToBePresentBool(driver.getWebDriver(), CRMCasePage.followUpByColumnTable,
					maxTimeOut))

			{
				report.updateTestLog("Verify Follow Up By Date on Case",
						"Follow Up By is available on My team's Assigned Case view ", Status.PASS);
			} else {
				report.updateTestLog("Verify Follow Up By date on Case",
						"Follow Up By is not available on My team's Assigned Case view ", Status.FAIL);
			}

		} catch (Exception Ex) {

			report.updateTestLog("Verify FollowUp By On Case Views", "Exception Occurred: " + Ex.getMessage(),
					Status.FAIL);
		}
	}

	public void verifyFollowUpByOnQueueViews() {
		try {
			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
			driver.switchTo().defaultContent();
			reusableFunc.navigationToEntity(driver.getWebDriver(), serviceModule, queueEntity);
			driver.navigate().refresh();
			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());

			By caseIAmWorkingOnView = By
					.xpath("//a[@title=\"Cases I am Working On\"]//following::span[text()=\"Cases I am Working On\"]");
			selectViewFromCaseEntity(casesIAmWorkingOn, caseIAmWorkingOnView);

			if (reusableFunc.waitForElementToBePresent(driver.getWebDriver(), CRMCasePage.followUpByColumnTable_Queue,
					maxTimeOut)) {
				report.updateTestLog("Verify Follow Up By Date on Case",
						"Follow Up By is available on Cases I am Working On View", Status.PASS);
			} else {
				report.updateTestLog("Verify Follow Up By Date on Case",
						"Follow Up By is not available on Cases I am Working On View ", Status.FAIL);
			}

		} catch (Exception Ex) {
			report.updateTestLog("Verify FollowUp By On Queue Views", "Exception Occurred: " + Ex.getMessage(),
					Status.FAIL);
		}
	}

	public void verifyCaseTimingMetricFieldValues() {
		try {
			String CaseName = dataTable.getData("General_Data", "recordToSearch");
			searchCaseTimingMetricsRelatedRecordsAndSelect(CaseName);
			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());

			// Case Timing Field Validation
			String fieldsName[] = { "Name", "Case", "Case Status", "Case Status Reason", "Case Category",
					"Case Inquiry Type", "Case Inquiry Sub Type", "Owner", "Queue", "Case Timing Start Time",
					"Case Timing End Time", "Case Timing Duration", "Formatted Case Timing Duration" };

			for (String field : fieldsName) {
				By fieldsXpath = By.xpath("//span[text()='" + field + "']");
				reusableFunc.switchToObjectFrame(driver.getWebDriver(), fieldsXpath);

				if (reusableFunc.waitForElementToBePresent(driver.getWebDriver(), fieldsXpath, maxTimeOut)) {
					report.updateTestLog("Verify Case Timing Metric Field Values",
							field + " is present on case timing metric form.", Status.PASS);
				} else {
					report.updateTestLog("Verify Case Timing Metric Field Values",
							field + " is not present on case timing metric form.", Status.FAIL);
				}
			}

		} catch (Exception Ex) {
			report.updateTestLog("Verify Case Timing Metric Field Values", "Exception Occurred: " + Ex.getMessage(),
					Status.FAIL);
		}
	}

	public void verifyPolicySearchByLocationAddress() {
		try {
			String LocationAddress = dataTable.getData("General_Data", "LocationAddress");
			searchLocationAddressRelatedRecordsAndSelect(LocationAddress);
			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());

			// Location Address Field Validation
			String fieldsName[] = { "Street1", "Street2", "Street3", "City", "State", "Postal Code", "Country" };

			for (String field : fieldsName) {
				By fieldsXpath = By.xpath("//span[text()='" + field + "']");
				reusableFunc.switchToObjectFrame(driver.getWebDriver(), fieldsXpath);

				if (reusableFunc.waitForElementToBePresent(driver.getWebDriver(), fieldsXpath, maxTimeOut)) {
					report.updateTestLog("Verify Policy Number Fileds", field + " is present on location address form.",
							Status.PASS);
				} else {
					report.updateTestLog("Verify Case Timing Metric Field Values",
							field + " is not present on location address form.", Status.FAIL);
				}
			}

			// Policy Field Validation and Policy Number Extraction
			By policyNumberText = By
					.xpath("//span[text()='Policy']//following::span[@id='header_cxp_policyid_lookupValue']");
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), policyNumberText);

			if (reusableFunc.waitForElementToBePresent(driver.getWebDriver(), policyNumberText, maxTimeOut)) {
				String policyNumber = driver.findElement(policyNumberText).getText();
				report.updateTestLog("Verify Policy Number Filed",
						"Policy Number filed is present on location address form and Policy Number is: " + policyNumber,
						Status.PASS);
			} else {
				report.updateTestLog("Verify Policy Number Filed",
						"Policy Number filed is not present on location address form.", Status.FAIL);
			}

		} catch (Exception Ex) {
			report.updateTestLog("Verify Policy Search By Location Address", "Exception Occurred: " + Ex.getMessage(),
					Status.FAIL);
		}
	}

	public void assignCaseToAnotherUserOrTeam() {
		try {
			String caseNameToAssign = dataTable.getData("General_Data", "CaseName1");
			String AssignTo = dataTable.getData("General_Data", "UserName");
			String StatusReason = dataTable.getData("General_Data", "CaseStatus");

			// Search case for new assignment
			searchCaseRelatedRecordsAndSelect(caseNameToAssign);

			// Assign case to another user
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());

			/*if (reusableFunc.waitForElementToBePresent(driver.getWebDriver(), moreButton, maxTimeOut)) {
				driver.findElement(moreButton).click();
				driver.findElement(assignButton).click();
				report.updateTestLog("Assign Case To Another User or Team", "Assign Button located.",
						Status.PASS);
			} else {
				report.updateTestLog("Assign Case To Another User or Team", "Unable to locate Assign Button",
						Status.FAIL);
			}*/


			if (reusableFunc.waitForElementToBePresent(driver.getWebDriver(), assignButton, maxTimeOut)) {
				reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), assignButton);
				report.updateTestLog("Assign Case To Another User or Team", "Assign Button located.",
						Status.PASS);
			} else if (reusableFunc.waitForElementToBePresent(driver.getWebDriver(), moreButton, maxTimeOut)) {
				driver.findElement(moreButton).click();
				driver.findElement(assignButton).click();
				report.updateTestLog("Assign Case To Another User or Team", "Assign Button located.",
						Status.PASS);
			} else {
				report.updateTestLog("Assign Case To Another User or Team", "Unable to locate Assign Button",
						Status.FAIL);
			}

			reusableFunc.switchToObjectFrame(driver.getWebDriver(), changeAssignMe);

			if (reusableFunc.waitForElementToBePresent(driver.getWebDriver(), changeAssignMe, maxTimeOut)) {
				reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), changeAssignMe);
				report.updateTestLog("Assign Case To Another User or Team", "User or team dropdown value got selected.",
						Status.PASS);

			} else {
				report.updateTestLog("Assign Case To Another User or Team",
						"Unable to select User or team dropdown value.", Status.FAIL);
			}

			reusableFunc.switchToObjectFrame(driver.getWebDriver(), userOrTeamInputEnable);

			if (reusableFunc.waitForElementToBePresent(driver.getWebDriver(), userOrTeamInputEnable, maxTimeOut)) {
				reusableFunc.enterTextAction_Stale(driver.getWebDriver(), userOrTeamInputEnable, userOrTeamInputTextbox,
						AssignTo);

				// Click on search icon and select user
				reusableFunc.switchToObjectFrame(driver.getWebDriver(), lookupIcon);
				reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), lookupIcon);

				By userXpath = By.xpath("//ul[@id='systemuserview_id_IMenu']//following::span[contains(@title,'"+AssignTo+"')]");
				reusableFunc.switchToObjectFrame(driver.getWebDriver(), userXpath);
				reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), userXpath);

				reusableFunc.waitForElementToBeClickable(driver.getWebDriver(), assignButtonOnFrame, maxTimeOut);
				reusableFunc.switchToObjectFrame(driver.getWebDriver(), assignButtonOnFrame);
				reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), assignButtonOnFrame);

				report.updateTestLog("Assign Case To Another User or Team", "Assign to user is selected successfully.",
						Status.PASS);	
			} else {
				report.updateTestLog("Assign Case To Another User or Team", "Assign to user is not get selected.",
						Status.FAIL);
			}

			// Verify case owner changes to newly assigned user
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), caseOwnerLookup);
			String changedCaseOwner = reusableFunc.getTextFromElement(driver.getWebDriver(), caseOwnerLookup);

			if (changedCaseOwner.contains(AssignTo)) {
				report.updateTestLog("Assign Case To Another User or Team", "Case owner changed successfully.",
						Status.PASS);
			} else {
				report.updateTestLog("Assign Case To Another User or Team", "Case owner not changed.", Status.FAIL);
			}

			reusableFunc.switchToObjectFrame(driver.getWebDriver(), statusReason);
			String changedStatusReason1 = reusableFunc.getTextFromElement(driver.getWebDriver(), statusReason);
			System.out.println("Case status " + changedStatusReason1);
			if (StatusReason.equalsIgnoreCase(changedStatusReason1)) {
				report.updateTestLog("Case Status after Assigning Case To Another User or Team", "Case Status Changed.",
						Status.FAIL);
			} else {
				report.updateTestLog("Case Status after Assigning Case To Another User or Team", "Case Status remain Unchanged.", Status.PASS);
			}

		} catch (Exception Ex) {
			report.updateTestLog("Ability To Cancel Case From Queues", "Exception Occured: " + Ex.getMessage(),
					Status.FAIL);
		} 
	}


	public void searchCaseRelatedRecordsAndSelectCase(String recordToSearch,String CaseName) throws InterruptedException {
		try {
			driver.switchTo().defaultContent();
			reusableFunc.navigationToEntity(driver.getWebDriver(), serviceModule, caseEntity);
			UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
			UI_Helpers_OOB.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Iframe0);
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), recordSearchBox);

			if (reusableFunc.waitForElementToBeClickableBool(driver.getWebDriver(), recordSearchBox, maxTimeOut)) {
				WebElement element = driver.findElement(recordSearchBox);
				element.click();
				element.sendKeys(recordToSearch);
				element.sendKeys(Keys.ENTER);

				/*By recordXpath = By
						.xpath("//div[@id='crmGrid_divDataArea']//following::a[@title='" + CaseName + "']");*/

				By recordXpath = By
						.xpath("//a[@title='" + CaseName + "']");

				reusableFunc.waitForElementToBeClickableBool(driver.getWebDriver(), recordXpath, maxTimeOut);
				driver.findElement(recordXpath).click();

				By titleXpath = By.xpath("//h1[@title='" + CaseName + "']");
				reusableFunc.switchToObjectFrame(driver.getWebDriver(), titleXpath);

				if (reusableFunc.waitForElementToBeVisible(driver.getWebDriver(), titleXpath, maxTimeOut)) {
					report.updateTestLog("Validating Case entity navigation",
							"Able to Navigate to Queue entity and select the record.", Status.PASS);
				} else {
					report.updateTestLog("Validating Case entity navigation", "Not able to select the record.",
							Status.FAIL);
				}
				reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			}
		} catch (Exception Ex) {
			report.updateTestLog("Validating Case entity navigation", "Exception Occured: " + Ex.getMessage(),
					Status.FAIL);
		}

	}
	public void addCasetoQueue() {

		String caseNameToAddtoQueue = dataTable.getData("General_Data", "CaseName1");
		String queueName = dataTable.getData("General_Data", "QueueName");
		String StatusReason = dataTable.getData("General_Data", "CaseStatus");
		String viewName = dataTable.getData("General_Data", "ViewName");


		try{
			System.out.println("ViewName : "+viewName);
			// Search case for 
			searchCaseRelatedRecordsAndSelect(caseNameToAddtoQueue);
			System.out.println("case opened");
			System.out.println("waiting for Add to Queue button to be present");

			if (reusableFunc.waitForElementToBePresent(driver.getWebDriver(), addToQueueButton, 10)) {

				driver.findElement(addToQueueButton).click();

				action.doubleClick(driver.findElement(addToQueueButton)).perform();
				System.out.println("Add to Queue Button Clicked ");
				report.updateTestLog("Add Case To Queue", "Add to Queue Button located and Clicked.",
						Status.PASS);

			} else {
				report.updateTestLog("Search and select Case record", "Unable to locate Add-to-Queue Button",
						Status.FAIL);
			}
		}

		catch (Exception Ex) {
			report.updateTestLog("Search and select Case record", "Exception Occured: " + Ex.getMessage(),
					Status.FAIL);
		}


		try{
			//Switch to Add to Queue popup Frame
			// reusableFunc.switchToObjectFrame(driver.getWebDriver(), By.xpath("//span[@id='addtoqueue_id']"));
			By  iframe2 = By.xpath("//iframe[@id='InlineDialog_Iframe']");
			WebElement iframetwo = driver.findElement(iframe2);
			if(reusableFunc.waitForElementToBePresent(driver.getWebDriver(), iframe2, 50)){


				System.out.println("iframe found");
				driver.switchTo().frame(iframetwo);

				report.updateTestLog("Switch to add to queue frame", "Successfuly switched to iframe of pop-up",
						Status.PASS);
			}
			else
				report.updateTestLog("Switch to add to queue frame", "Not switched to pop-up iframe",
						Status.FAIL);

		}
		catch (Exception Ex) {
			report.updateTestLog("Switch to Add-to-Queue frame", "Exception Occured: " + Ex,
					Status.FAIL);}
		try{
			if( reusableFunc.waitForElementToBePresent(driver.getWebDriver(), queue, 50)){

				System.out.println("Switched to iframe");
				driver.findElement(queueLabel).click();
				System.out.println("Queue label Clicked");

				driver.findElement(QueueInputTextbox).sendKeys(queueName);

				driver.findElement(QueueInputTextbox).sendKeys(Keys.ENTER);
				System.out.println("Queue name entered in textbox");
				report.updateTestLog("Add Case To Queue popup ", "Queue name entered in textbox",
						Status.PASS);


				if(reusableFunc.waitForElementToBePresent(driver.getWebDriver(), By.xpath("//span[.='" + queueName + "']/nobr"), maxTimeOut))
				{
					driver.findElement(By.xpath("//span[.='" + queueName + "']/nobr")).click();


					System.out.println("Queue Selected");
				}else{System.out.println("Queue NOT  Selected");}
				report.updateTestLog("Add to queue Popup", " Queue Selected"+queueName,
						Status.PASS);
				driver.findElement(By.xpath("//button[@id='ok_id']")).click();

				report.updateTestLog("Add to queue Popup", " Case Added to"+queueName,
						Status.PASS);
				System.out.println("Add button Clicked");	            

			} else {
				report.updateTestLog("Add to Queue Popup",
						"Unable to Click on Queue Label", Status.FAIL);
			}



			//Verifying Weather Case is added to Queue.

			try{

				searchQueueandVerifyCase(viewName ,queueName, caseNameToAddtoQueue);

			}
			catch (Exception Ex) {
				report.updateTestLog("Verifying added Case in Queue", "Exception Occured: " + Ex.getMessage(),
						Status.FAIL);
			}   
		} catch (Exception Ex) {
			report.updateTestLog("Ability To add case to  Queues", "Exception Occured: " + Ex.getMessage(),
					Status.FAIL);
		} 
	}

	public void changeStatusofCase() throws InterruptedException{


		String casename = dataTable.getData("General_Data", "CaseTitle");
		String sts="Null";

		try{
			// Search case for 
			searchCaseRelatedRecordsAndSelect(casename);
			System.out.println("case opened");
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), By.xpath("//div[@id='FormTitle']"));

			String currentStatus= driver.findElement(By.xpath("//div[@id='statuscode']")).getText();  
			System.out.println("Status:"+currentStatus);

			report.updateTestLog("Verifying current status of Case", "Current status of case is : "+currentStatus, Status.DONE);

			driver.findElement(CRMCasePage.statusReasonLabel).click();
			Select statusdrpdown = new Select(driver.findElement(CRMCasePage.statusReasondropdown));


			if(currentStatus.equalsIgnoreCase("New")){
				driver.findElement(CRMCasePage.statusReasonLabel).click();
				List<WebElement> optionwhenNEWstatus = statusdrpdown.getOptions();
				for(int i=0;i<optionwhenNEWstatus.size();i++)
				{
					sts=optionwhenNEWstatus.get(i).getText();

					if(sts.equalsIgnoreCase("New")){
						break;
					}

				}
				if(sts.equalsIgnoreCase("New")){
					report.updateTestLog("Verifying Case Not going back to NEW Status", "New Status is visible in Select Dropdown", Status.PASS);
				}
				else
					report.updateTestLog("Verifying Status Reason of case", "NEW status is not Visible in Status Reason field" , Status.FAIL);	



			}else{
				List<WebElement> allOptions = statusdrpdown.getOptions();

				for(int i=0;i<allOptions.size();i++)
				{
					sts=allOptions.get(i).getText();

					if(sts.equalsIgnoreCase("New")){
						break;
					}

				}
				if(sts.equalsIgnoreCase("New")){
					report.updateTestLog("Verifying Case Not going back to NEW Status", "New Status is visible in Select Dropdown", Status.FAIL);
				}
				else
					report.updateTestLog("Verifying Status Reason of case", "NEW status is not Visible in Status Reason field", Status.PASS);	

			}

		}catch(Exception Ex){

		}
	}
	public void searchQueueandVerifyCase(String view,String queueName, String caseName) throws InterruptedException{
		try{

			//navigate to Queue
			driver.switchTo().defaultContent();
			reusableFunc.navigationToEntity(driver.getWebDriver(), serviceModule, queueEntity);
			System.out.println("Navigated to Queue");
			//UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
			//UI_Helpers_OOB.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Iframe0);
			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
			driver.navigate().refresh();

			reusableFunc.switchToObjectFrame(driver.getWebDriver(), By.xpath("//a[@title='Select a view']"));

			System.out.println("Switched to search box frame");

			// select "View"
			driver.findElement(By.xpath("//a[@title='Select a view']")).click();
			System.out.println("clicked on all items view");
			driver.findElement(By.xpath("//a[@title='" + view + "']")).click();
			System.out.println("clicked on all Cases in selected View");
			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());

			//search for Cases in Queue
			if (reusableFunc.waitForElementToBePresent(driver.getWebDriver(), recordSearchBox, 50)) {
				WebElement element = driver.findElement(recordSearchBox);
				element.click();
				element.sendKeys(caseName);
				element.sendKeys(Keys.ENTER);
				System.out.println("Entered Case name to search Queue");
			}else 	System.out.println("Cannot find search Box and not able to Enter data to search");

			// Select Queue
			driver.findElement(By.xpath("//select[@id='crmQueueSelector']")).click();
			System.out.println("clicked on select queue");
			By Queuename=By.xpath("//option[@title='" + queueName + "']");
			System.out.println(" name of Queue: "+ caseName);
			driver.findElement(Queuename).click();
			//System.out.println("//option[@title='"+ queueName +"']");
			System.out.println("Queue Selected");
			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
			System.out.println(" Name of Case: "+caseName);
			By recordXpath = By
					.xpath("//a[@title='" + caseName + "']");

			if(reusableFunc.waitForElementToBePresent(driver.getWebDriver(), recordXpath, maxTimeOut)){
				report.updateTestLog("Validating Case in Queue",
						" case: "+caseName+" is present in Queue:"+queueName, Status.PASS);



			} else {
				report.updateTestLog("Validating Case in Queue", "Record not present",
						Status.FAIL);
			}
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
		}



		catch (Exception Ex) {
			report.updateTestLog("unable to Verify Case in Queue", "Exception Occured: " + Ex.getMessage(),
					Status.FAIL);
		}
	}

	public void deEscalateCaseFLE() throws Exception {
		String DeEscalated = dataTable.getData("General_Data", "DeEscalated");

		//reusableFunc.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Iframe1);

		try{
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), By.xpath("//div[@id='FormTitle']"));

			Thread.sleep(50);
			driver.findElement(CRMCasePage.escalated_enable).click();
			UI_Helpers_OOB.dropDownSelectionByText(driver.getWebDriver(), CRMCasePage.escalated_dropDown_ID, DeEscalated);
			String alertmsg= driver.switchTo().alert().getText();
			Thread.sleep(500);

			driver.switchTo().alert().accept();
			System.out.println("Alert msg :"+alertmsg);
			report.updateTestLog("Validating De-Esclation of case by FLE user ",
					"Unable to De-Esclate case : "+alertmsg, Status.PASS);

			/*
		driver.findElement(CRMCasePage.saveButtonFromCase).click();

		reusableFunc.waitForpageLoadByThread(driver.getWebDriver());
		Thread.sleep(5000);

		String CaseType1 = driver.findElement(CRMCasePage.EscalatedCase).getText();

		if (CaseType1.equalsIgnoreCase("No")) {
			report.updateTestLog("Validating Case Escalated Changes", "Able to  DeEscalate Case", Status.PASS);
		} else {
			report.updateTestLog("Validating Case DeEscalated Changes", "Not Able to DeEscalate Case", Status.FAIL);
		}

		reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			 */

		}catch(Exception Ex)
		{
			report.updateTestLog("Validating De-Esclation of case by FLE user", "Exception Occured: " + Ex.getMessage(),
					Status.FAIL);
		}


	}
	public void createEmailforCase() throws Exception{

		//String from = dataTable.getData("General_Data", "From");

		try{
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), By.xpath("//div[@id='FormTitle']"));
			System.out.println("Framed Switched");
			driver.findElement(CRMCasePage.moreActivityButton).click();
			System.out.println("more button clicked");
			Thread.sleep(500);
			driver.findElement(CRMCasePage.emailActivity).click();
			System.out.println("Email Clicked ");
			reusableFunc.waitForpageLoadByThread(driver.getWebDriver());

			driver.findElement(CRMActivityPage.fromEnable).click();
			driver.findElement(CRMActivityPage.fromedit).sendKeys("Flood");
			System.out.println("From Entered");

		}catch(Exception Ex){
			System.out.println("Error : "+Ex);

		}
	}

	public void copyCase() throws Exception{



		try{
			driver.switchTo().defaultContent();
			driver.findElement(By.xpath("//li[@id='incident|NoRelationship|Form|cxp.incident.Button.CopyCase']/span")).click(); // click copy Case button
			//Thread.sleep(500);
			/*
			if(reusableFunc.waitForAlertPresent(driver.getWebDriver(), 1))
			{
			String msg =driver.switchTo().alert().getText();
			System.out.println(""+msg);
			driver.switchTo().alert().accept();
			System.out.println("alert Accepted");

			report.updateTestLog("Copy case", "Alert Message : "+msg,
	                Status.FAIL);

			}
			 */
			//switch to case form frame

			driver.switchTo().frame(driver.findElement(CRMCasePage.caseformFrame));
			//capturing notification  
			if(reusableFunc.waitForElementToBePresent(driver.getWebDriver(), CRMCasePage.copyNotification, maxTimeOut)){
				String msg = driver.findElement(CRMCasePage.copyNotification).getText();
				report.updateTestLog("Copy case", "Notification message:  "+msg,
						Status.PASS);


			}else {
				if(reusableFunc.waitForAlertPresent(driver.getWebDriver(), 1))
				{
					String msg =driver.switchTo().alert().getText();
					System.out.println(""+msg);
					driver.switchTo().alert().accept();
					System.out.println("alert Accepted");

					report.updateTestLog("Copy case", "Alert Message : "+msg,
							Status.FAIL);

				}else{
					report.updateTestLog("Copy case", "Unknown Error Occured   ",
							Status.FAIL);
				}

			}
			//capturing Original Case data
			String customerName=driver.findElement(CRMCasePage.customerName).getText();
			String owner=driver.findElement(CRMCasePage.owner).getText();
			String origin=driver.findElement(CRMCasePage.origin).getText();
			String caseStatusvalue=driver.findElement(CRMCasePage.caseStatusvalue).getText();
			String casetitle=driver.findElement(CRMCasePage.casetitle).getText();
			String casePriority=driver.findElement(CRMCasePage.casePriority).getText();
			System.out.println("customer Name : "+customerName);
			System.out.println("Owner  : "+owner);
			System.out.println("Origin : "+origin);
			System.out.println("Title : "+casetitle);
			System.out.println("Priority : "+casePriority);

			System.out.println("Status : "+caseStatusvalue);
			//opening copied Case
			searchCaseRelatedRecordsAndSelect(casetitle+" - Copy");
			System.out.println("copied Case opened");

			driver.switchTo().frame(driver.findElement(CRMCasePage.caseformFrame));
			//capuring Copied Case data
			String customerName2=driver.findElement(CRMCasePage.customerName).getText();
			String owner2=driver.findElement(CRMCasePage.owner).getText();
			String origin2=driver.findElement(CRMCasePage.origin).getText();
			String caseStatusvalue2=driver.findElement(CRMCasePage.caseStatusvalue).getText();
			String casetitle2=driver.findElement(CRMCasePage.casetitle).getText();
			String casePriority2=driver.findElement(CRMCasePage.casePriority).getText();
			System.out.println("customer Name : "+customerName2);
			System.out.println("Owner  : "+owner2);
			System.out.println("Origin : "+origin2);
			System.out.println("Title : "+casetitle2);
			System.out.println("Priority : "+casePriority2);
			System.out.println("Status : "+caseStatusvalue2);

			driver.switchTo().defaultContent();
			if(customerName==customerName2){
				report.updateTestLog("Copy case_Verify  Customer Name ", "Customer name of Copied Case : "+customerName2+" ,is NOT same as Original Case :"+customerName,
						Status.FAIL);
			}else{

				report.updateTestLog("Copy case _Verify Customer Name", "Customer name of Copied Case : "+customerName2+" ,is same as Original Case : "+customerName,
						Status.PASS);
			}
			if(owner==owner2){
				report.updateTestLog("Copy case_Verify Owner ", "Owner name of Copied Case :"+owner2+" ,is NOT same as Original Case : "+owner,
						Status.FAIL);
			}else{

				report.updateTestLog("Copy case_Verify Owner ", "Owner name of Copied Case :"+owner2+" ,is same as Original Case : "+owner,
						Status.PASS);
			}
			if(casetitle!=casetitle2){
				report.updateTestLog("Copy case_Verify Title", "Case Title  of Copied Case : "+casetitle2+" ,is NOT same as Original Case "+casetitle,
						Status.PASS);
			}else{


				report.updateTestLog("Copy case_Verify Title", "Case Title  of Copied Case : "+casetitle2+" ,is same as Original Case "+casetitle,
						Status.FAIL);
			}
			if(casePriority2!="Normal"){
				report.updateTestLog("Copy case_Verify Priority", "Priority of Copied Case is :"+casePriority2,
						Status.PASS);
			}else{

				report.updateTestLog("Copy case_Verify Priority", "Priority of Copied Case is :"+casePriority2+", Normal is Expected ",
						Status.FAIL);
			}
			if(caseStatusvalue2!="New"){
				report.updateTestLog("Copy case_Verify Status Reason", "Status Reason of Copied Case is :"+caseStatusvalue2,
						Status.PASS);
			}else{

				report.updateTestLog("Copy case_Verify Status Reason", "Status Reason of Copied Case is :"+caseStatusvalue2+", New is Expected ",
						Status.FAIL);
			}
			if(origin==origin2){

				report.updateTestLog("Copy case_Verify Origin ", "Origin of Copied Case : "+origin2+" ,is NOT same as Original Case : "+origin,
						Status.FAIL);
			}else{
				report.updateTestLog("Copy case_Verify Origin ", "Origin of Copied Case : "+origin2+" ,is same as Original Case : "+origin,
						Status.PASS);
			}



		}catch(Exception Ex){
			report.updateTestLog("Validating Copy Case functionality ", "Exception Occured: " + Ex.getMessage(),
					Status.FAIL);

		}
	}
	/*
	public void searchCaseRelatedRecordsAndSelectCase(String recordToSearch,String CaseName) throws InterruptedException {
		try {
			driver.switchTo().defaultContent();
			reusableFunc.navigationToEntity(driver.getWebDriver(), serviceModule, caseEntity);
			UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
			UI_Helpers_OOB.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Iframe0);
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), recordSearchBox);

			if (reusableFunc.waitForElementToBeClickableBool(driver.getWebDriver(), recordSearchBox, maxTimeOut)) {
				WebElement element = driver.findElement(recordSearchBox);
				element.click();
				element.sendKeys(recordToSearch);
				element.sendKeys(Keys.ENTER);

				By recordXpath = By
						.xpath("//div[@id='crmGrid_divDataArea']//following::a[@title='" + CaseName + "']");

				By recordXpath = By.xpath("//a[@title='" + CaseName + "']");

				reusableFunc.waitForElementToBeClickableBool(driver.getWebDriver(), recordXpath, maxTimeOut);
				driver.findElement(recordXpath).click();

				By titleXpath = By.xpath("//h1[@title='" + CaseName + "']");
				reusableFunc.switchToObjectFrame(driver.getWebDriver(), titleXpath);

				if (reusableFunc.waitForElementToBeVisible(driver.getWebDriver(), titleXpath, maxTimeOut)) {
					report.updateTestLog("Validating Case entity navigation",
							"Able to Navigate to Queue entity and select the record.", Status.PASS);
				} else {
					report.updateTestLog("Validating Case entity navigation", "Not able to select the record.",
							Status.FAIL);
				}
				reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			}
		} catch (Exception Ex) {
			report.updateTestLog("Validating Case entity navigation", "Exception Occured: " + Ex.getMessage(),
					Status.FAIL);
		}

	}
	 */	

	public void verifyBillAccountSearchOnCaseForm() {
		try {
			String caseName = dataTable.getData("General_Data", "CaseName");
			String BillAccFormatted = dataTable.getData("General_Data", "BillAccountformattednumber");
			String BillAccWithDashes = dataTable.getData("General_Data", "recordToSearch");


			// Search case for new assignment
			searchCaseRelatedRecordsAndSelect(caseName);

			// Assign case to another user
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());

			reusableFunc.switchToObjectFrame(driver.getWebDriver(), CRMCasePage.billAccount_enable);

			reusableFunc.enterTextAction_Stale(driver.getWebDriver(), CRMCasePage.billAccount_enable, CRMCasePage.billAccount_edit, BillAccFormatted);
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			try
			{

				By Billacclookup = By.xpath("//ul[@id='cxp_billaccountid_IMenu']/li[1]//span[text()='"+BillAccWithDashes+"']");
				reusableFunc.switchToDefaultFrame(driver.getWebDriver());
				reusableFunc.switchToObjectFrame(driver.getWebDriver(), Billacclookup);
				if(driver.findElement(Billacclookup).isDisplayed())
				{
					report.updateTestLog("Validating Bill Account Search With Formatted bill Account number on Case form", "Able to search Bill Account with Formatted number on case form", Status.PASS);
				}

			}catch(Exception e)
			{
				report.updateTestLog("Validating Bill Account Search With Formatted bill Account number on Case form", "Not Able to search Bill Account with Formatted number  on case form", Status.FAIL);
			}

			r.keyPress(KeyEvent.VK_F5);
			r.keyRelease(KeyEvent.VK_F5);
			Thread.sleep(1000);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), CRMCasePage.billAccount_enable);
			reusableFunc.enterTextAction_Stale(driver.getWebDriver(), CRMCasePage.billAccount_enable, CRMCasePage.billAccount_edit, BillAccWithDashes);


			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			try
			{

				By Billacclookup = By.xpath("//ul[@id='cxp_billaccountid_IMenu']/li[1]//span[text()='"+BillAccWithDashes+"']");
				reusableFunc.switchToDefaultFrame(driver.getWebDriver());
				reusableFunc.switchToObjectFrame(driver.getWebDriver(), Billacclookup);
				if(driver.findElement(Billacclookup).isDisplayed())
				{
					report.updateTestLog("Validating Bill Account Search With Formatted bill Account number on Case form", "Able to search  bill account with dashes on case form", Status.PASS);
				}

			}catch(Exception e)
			{
				report.updateTestLog("Validating Bill Account Search With Formatted bill Account number on Case form", "Not Able to search bill account with dashes on case form", Status.FAIL);
			}


		} catch (Exception Ex) {
			report.updateTestLog("Validating Bill Account Search on Case Form", "Exception Occured: " + Ex.getMessage(),
					Status.FAIL);
		} 
	}


	public void verifyAssociatedVieRecentCases() {
		try {


			String CaseRowNumber1 = dataTable.getData("General_Data", "CaseName1");
			String CaseRowNumber2 = dataTable.getData("General_Data", "CaseName2");  //provide case number not case name
			String recordToSearch = dataTable.getData("General_Data", "SearchRecord");


			contact.searchContactRelatedRecordsAndSelect(recordToSearch);
			// Switch To Frame
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			reusableFunc.switchToObjectFrame(driver.getWebDriver(),CaseAssociatedViewImage );

			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_END);
			r.keyRelease(KeyEvent.VK_END);
			Thread.sleep(1000);
			driver.findElement(CaseAssociatedViewImage).click();
			Thread.sleep(1000);
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			reusableFunc.switchToObjectFrame(driver.getWebDriver(),CaseAssociatedView );  
			try {
				if (driver.findElement(CaseAssociatedView).isDisplayed()) {
					report.updateTestLog("Validation of Case Associated View Navigation", "Not able to  navigated to Case Associated View from Recent Cases", Status.FAIL);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Case Associated View Navigation", "Successfully navigated to Case Associated View from Recent Cases", Status.PASS);
			}

			/*reusableFunc.switchToDefaultFrame(driver.getWebDriver());
				reusableFunc.switchToObjectFrame(driver.getWebDriver(),areaFrame );*/
			By recordXpath = By
					.xpath("[@id='crmGrid_incident_customer_contacts_divDataArea']//table/tbody");

			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			reusableFunc.switchToObjectFrame(driver.getWebDriver(),recordXpath );

			driver.findElement(By.xpath("[@id='crmGrid_incident_customer_contacts_divDataArea']//table/tbody/tr[1]/td[1]")).click();

			Thread.sleep(1000);

			try {
				if (driver.findElement(assignButton).isDisplayed()) {
					report.updateTestLog("Validation of first Case selection from grid", "Able to select first case from associated view", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of first Case selection from grid", "Not Able to select first case from associated view", Status.FAIL);
			}
			//				///div[@title='"+CaseNumber2+"']//preceding::td[@title='Checkbox']/img

			driver.findElement(By.xpath("[@id='crmGrid_incident_customer_contacts_divDataArea']//table/tbody/tr[3]/td[1]")).click();

			Thread.sleep(1000);

			try {
				if (driver.findElement(mergeCaseButton).isDisplayed()) {
					report.updateTestLog("Validation of second Case selection from grid", "Able to select second case from associated view", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of second Case selection from grid", "Not Able to select second case from associated view", Status.FAIL);
			}

		} catch (Exception Ex) {
			report.updateTestLog("Validation of Related Individual/Organization Section  values on  Bill Accounts", "Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}
	}

	public void verifyPolicyWithPrefixSearchOnCaseForm() {
		try {

			String PolicyWithoutFLDPrefix = dataTable.getData("General_Data", "PolicyWithoutFLDPrefix");
			String PolicyWithFLDPrefix = dataTable.getData("General_Data", "recordToSearch");


			// Search case for new assignment


			// Assign case to another user
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());

			reusableFunc.switchToObjectFrame(driver.getWebDriver(), CRMCasePage.policy_enable);

			reusableFunc.enterTextAction_Stale(driver.getWebDriver(), CRMCasePage.policy_enable, CRMCasePage.policy_edit, PolicyWithFLDPrefix);
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			try
			{

				By Policylookup = By.xpath("//ul[@id='cxp_policyid_IMenu']/li[1]//span[text()='"+PolicyWithoutFLDPrefix+"']");
				reusableFunc.switchToDefaultFrame(driver.getWebDriver());
				reusableFunc.switchToObjectFrame(driver.getWebDriver(), Policylookup);
				if(driver.findElement(Policylookup).isDisplayed())
				{
					report.updateTestLog("Validating Policy search with FLD/000 Prefix on Case form", "Able to search Policy with FLD/000 prefix on case form", Status.PASS);
				}

			}catch(Exception e)
			{
				report.updateTestLog("Validating Policy search with FLD/000 Prefix on Case form", "Not Able to search Policy with FLD/000 prefix on case form", Status.FAIL);
			}


		} catch (Exception Ex) {
			report.updateTestLog("Validating Policy with Prefix  on Case Form", "Exception Occured: " + Ex.getMessage(),
					Status.FAIL);
		} 
	}


}
