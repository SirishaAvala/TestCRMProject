package businessComponents;

import pageObjects.*;
import com.cognizant.Craft.*;
import org.openqa.selenium.Alert;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Random;
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
import org.openqa.selenium.support.ui.WebDriverWait;
import com.cognizant.framework.Status;
import com.google.common.base.CaseFormat;

/**
 * Class for storing general purpose business components
 * 
 * @author Cognizant
 */
public class Contacts_MSCRM extends ReusableLibrary {
	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	WebElement element = null;
	reusableFunctions reusableFunc = new reusableFunctions(scriptHelper);
	// Page Objects
	int maxTimeOut = 40;
	int minTimeOut = 5;
	public static final By recordSearchBox = By.cssSelector("input#crmGrid_findCriteria");
	public static final By serviceModule = By.xpath("//img[@alt='Service']//parent::span");
	public static final By contactEntity = By.cssSelector("a#nav_contacts");
	public static final By newButton =By.xpath("//li[@id='contact|NoRelationship|HomePageGrid|Mscrm.HomepageGrid.contact.NewRecord']/span");
	public static final By saveClose = By.xpath("//li[@id='contact|NoRelationship|Form|Mscrm.Form.contact.SaveAndClose']/span");
	
	

	Actions action = new Actions(driver.getWebDriver());
	
 	public Contacts_MSCRM(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	public void pressEnterRobot(int delay) throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(delay);
	}

	/**
	 * create_Contact This method is used to create contact from Account
	 * 
	 * @throws Exception
	 */
	public void create_Contact() throws Exception {

		String FirstName = dataTable.getData("General_Data", "FirstName");
		String LastName = dataTable.getData("General_Data", "LastName");
		String JobTitle = dataTable.getData("General_Data", "JobTitle");
		String AccountName = dataTable.getData("General_Data", "AccountName");
		String Email = dataTable.getData("General_Data", "Email");
		String BusinessPhone = dataTable.getData("General_Data", "BusinessPhone");
		String MobilePhone = dataTable.getData("General_Data", "MobilePhone");
		String ConnectionRole = dataTable.getData("General_Data", "ConnectionRole");
		String ContactMethod = dataTable.getData("General_Data", "ContactMethod");
		String Street1 = dataTable.getData("General_Data", "Street1");
		String Street2 = dataTable.getData("General_Data", "Street2");
		String Street3 = dataTable.getData("General_Data", "Street3");
		String City = dataTable.getData("General_Data", "City");
		String State = dataTable.getData("General_Data", "State");
		String PostalCode = dataTable.getData("General_Data", "PostalCode");
		String Country = dataTable.getData("General_Data", "Country");
		boolean FromAccount = false;

		WebElement iframeElement;

		try {
			driver.findElement(CRMNewContactRecordPage.decisionEntity);
		} catch (NoSuchElementException e) {
			FromAccount = true;
		}

		if (FromAccount == false) {

			UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.frame1);

			UI_Helpers_OOB.mouseClick(driver.getWebDriver(), CRMNewContactRecordPage.FullName);
			report.updateTestLog("Contact Entity", "Click on composite name element", Status.SCREENSHOT);
			UI_Helpers_OOB.implicitWait(driver.getWebDriver(), 3);

		} else {
			UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());

			new WebDriverWait(driver.getWebDriver(), 30).until(
					ExpectedConditions.visibilityOfElementLocated(By.cssSelector("iframe#NavBarGloablQuickCreate")));

			iframeElement = driver.findElement(By.cssSelector("iframe#NavBarGloablQuickCreate"));
			driver.switchTo().frame(iframeElement);
		}

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewContactRecordPage.firstName_enable,
				CRMNewContactRecordPage.firstName_edit, FirstName);
		report.updateTestLog("Contact Entity", "Fill First Name", Status.SCREENSHOT);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewContactRecordPage.lastName_enable,
				CRMNewContactRecordPage.lasttName_edit, LastName);
		report.updateTestLog("Contact Entity", "Fill Last Name", Status.SCREENSHOT);
		UI_Helpers_OOB.implicitWait(driver.getWebDriver(), 3);

		if (FromAccount == false) {
			driver.findElement(CRMNewContactRecordPage.doneButtonClick_fullName).click();
			report.updateTestLog("Contact Entity", "Click on done button", Status.SCREENSHOT);
		}

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewContactRecordPage.jobTitle_enable,
				CRMNewContactRecordPage.jobTitle_edit, JobTitle);
		report.updateTestLog("Contact Entity", "Fill Job Title", Status.SCREENSHOT);

		if (FromAccount == false) {
			UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewContactRecordPage.accountName_enable,
					CRMNewContactRecordPage.accountName_edit, AccountName);
			report.updateTestLog("Contact Entity", "Fill AccountName", Status.SCREENSHOT);

			UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewContactRecordPage.businessPhone_enable,
					CRMNewContactRecordPage.businessPhone_edit, BusinessPhone);
			report.updateTestLog("Contact Entity", "Fill Business Phone", Status.SCREENSHOT);

			UI_Helpers_OOB.dropDownSelection_Stale(driver.getWebDriver(),
					CRMNewContactRecordPage.contactMethod_parent_ID, CRMNewContactRecordPage.contactMethod_dropDown_ID,
					ContactMethod);
			report.updateTestLog("Contact Entity", "Select contact method", Status.SCREENSHOT);
			UI_Helpers_OOB.implicitWait(driver.getWebDriver(), 3);

		}

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewContactRecordPage.Email_enable,
				CRMNewContactRecordPage.Email_edit, Email);
		report.updateTestLog("Contact Entity", "Fill Email Address", Status.SCREENSHOT);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewContactRecordPage.mobilePhone_enable,
				CRMNewContactRecordPage.mobilePhone_edit, MobilePhone);
		report.updateTestLog("Contact Entity", "Fill Mobile Phone", Status.SCREENSHOT);

		if (FromAccount == false) {
			UI_Helpers_OOB.mouseClick(driver.getWebDriver(), CRMNewContactRecordPage.address);
			report.updateTestLog("Contact Entity", "Click on composite address element", Status.SCREENSHOT);
			UI_Helpers_OOB.implicitWait(driver.getWebDriver(), 3);

			UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewContactRecordPage.street3_enable,
					CRMNewContactRecordPage.street3_edit, Street3);
			report.updateTestLog("Contact Entity", "Fill Street 3", Status.SCREENSHOT);

			UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewContactRecordPage.state_enable,
					CRMNewContactRecordPage.state_edit, State);
			report.updateTestLog("Contact Entity", "Fill state name", Status.SCREENSHOT);

			UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewContactRecordPage.country_enable,
					CRMNewContactRecordPage.country_edit, Country);
			report.updateTestLog("Contact Entity", "Fill country name", Status.SCREENSHOT);

		}

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewContactRecordPage.street1_enable,
				CRMNewContactRecordPage.street1_edit, Street1);
		report.updateTestLog("Contact Entity", "Fill Street 1", Status.SCREENSHOT);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewContactRecordPage.street2_enable,
				CRMNewContactRecordPage.street2_edit, Street2);
		report.updateTestLog("Contact Entity", "Fill Street 2", Status.SCREENSHOT);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewContactRecordPage.city_enable,
				CRMNewContactRecordPage.city_edit, City);
		report.updateTestLog("Contact Entity", "Fill city name", Status.SCREENSHOT);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewContactRecordPage.postalCode_enable,
				CRMNewContactRecordPage.postalCode_edit, PostalCode);
		report.updateTestLog("Contact Entity", "Fill postal code", Status.SCREENSHOT);

		if (FromAccount == false) {
			driver.findElement(CRMNewContactRecordPage.doneButtonClick_address).click();
			report.updateTestLog("Contact Entity", "Click on done button", Status.SCREENSHOT);

			UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
			UI_Helpers_OOB.implicitWait(driver.getWebDriver(), 3);

			reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.SaveButtonClick);
			report.updateTestLog("Save", "Click the save entity", Status.SCREENSHOT);
		} else {
			UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
			UI_Helpers_OOB.implicitWait(driver.getWebDriver(), 3);

			reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.saveButtonFromAccount);
			Thread.sleep(5000);
			report.updateTestLog("Save", "Click the contact save entity", Status.SCREENSHOT);

		}
		UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
		UI_Helpers_OOB.implicitWait(driver.getWebDriver(), 3);

		Thread.sleep(5000);
	}

	/**
	 * verify_Contact This method is used to verify presence of contact record
	 * 
	 * @throws Exception
	 */
	public void verify_Contact() throws Exception {
		try {

			String recordValue = dataTable.getData("General_Data", "recordValue");

			reusableFunc.navigationToEntity(driver.getWebDriver(), CRMNewAccountRecordPage.salesModule,
					CRMNewContactRecordPage.contactEntity);
			Thread.sleep(1500);
			report.updateTestLog("Contact Page", "Search for the contact record", Status.SCREENSHOT);

			driver.navigate().refresh();
			Thread.sleep(3000);

			// Verify Contact

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

			report.updateTestLog("Contact Page", "Click on the contact record", Status.SCREENSHOT);

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
				report.updateTestLog("Contact Page", "Record doesn't exist !", Status.FAIL);
			}
			UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());

			Thread.sleep(5000);

			/*
			 * // Qualify the leads for opportunity conversion By qualifyButton
			 * = By.
			 * xpath("//a[@class='ms-crm-Menu-Label']//preceding::img[@class='ms-crm-ImageStrip-ConvertLead_16 ms-crm-commandbar-image16by16']"
			 * ); MouseClick_Action(driver, qualifyButton);
			 * 
			 * Thread.sleep(5000);
			 * System.out.println("Lead qualified for opportunity !!!");
			 * driver.quit();
			 */
		}

		catch (InterruptedException e) {
			// System.out.println("Record doesn't exist !");
			report.updateTestLog("Contact Page", "Record doesn't exist !", Status.FAIL);
		} catch (NoSuchElementException ex) {
			// System.out.println("Record doesn't exist !");
			report.updateTestLog("Contact Page", "Record doesn't exist !", Status.FAIL);
		} catch (TimeoutException ex) {
			// System.out.println("Record doesn't exist !");
			report.updateTestLog("Contact Page", "Record doesn't exist !", Status.FAIL);

		}

		report.updateTestLog("Contact Page", "Contact Verified Successfully !!", Status.SCREENSHOT);
	}

	/**
	 * navigateCase_FromContact This method is used to navigate to case form
	 * contact record
	 * 
	 * @throws InterruptedException
	 */
	public void navigateCase_FromContact() throws InterruptedException {

		UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.frame1);
		Thread.sleep(2000);
		reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMNewContactRecordPage.createCase);
		Thread.sleep(2000);
		report.updateTestLog("Contact Entity", "Click on new button", Status.SCREENSHOT);
	}

	/**
	 * navigateContact_Direct This method is used to navigate to contact entity
	 * from contact tab
	 * 
	 * @throws InterruptedException
	 */
	public void navigateContact_Direct() throws InterruptedException {

		// Navigate to contact page
		reusableFunc.navigationToEntity(driver.getWebDriver(), CRMNewAccountRecordPage.salesModule,
				CRMNewContactRecordPage.contactEntity);
		UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());

		Thread.sleep(5000);

		reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.NewButtonClick);
		report.updateTestLog("Contact Entity", "Click on new button", Status.SCREENSHOT);

		Thread.sleep(2000);
	}

	public void navigateToContactEntity() throws InterruptedException {
		driver.switchTo().defaultContent();
		reusableFunc.navigationToEntity(driver.getWebDriver(), CRMLandingPage.serviceModule,
				CRMLandingPage.contactEntity);
		Thread.sleep(5000);
		try {
			if (driver.findElement(CRMCasePage.viewDropdown).isDisplayed()) {
				report.updateTestLog("Validating Case entity navigation", "Not able to Navigate to Case entity",
						Status.FAIL);
			}
		} catch (Exception e) {
			report.updateTestLog("Validating Case entity navigation", "Navigated to Case entity", Status.PASS);
		}

	}

	public String createContactWithMandatoryFields() throws AWTException {
		Random rand = new Random();
		String firstName = dataTable.getData("General_Data", "ContactName");
		String lastName = "" + rand.nextInt(99999);
		String connectionRole = dataTable.getData("General_Data", "ConnectionRole");
		String primaryEmail = dataTable.getData("General_Data", "PrimaryEmail");
		String contactName = firstName + " " + lastName;

		try {
			navigateToContactEntity();

			reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMCasePage.newCaseButton);

			reusableFunc.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Iframe1);

			reusableFunc.enterTextAction_Stale(driver.getWebDriver(), CRMNewContactRecordPage.connectionRole_enable,
					CRMNewContactRecordPage.connectionRole_edit, connectionRole);

			reusableFunc.enterTextAction_Stale(driver.getWebDriver(), CRMNewContactRecordPage.lastName_enable,
					CRMNewContactRecordPage.lasttName_edit, lastName);

			reusableFunc.enterTextAction_Stale(driver.getWebDriver(), CRMNewContactRecordPage.firstName_enable,
					CRMNewContactRecordPage.firstName_edit, firstName);

			pressEnterRobot(minTimeOut);

			reusableFunc.enterTextAction_Stale(driver.getWebDriver(),
					CRMNewContactRecordPage.primaryEmailAddress_Enable,
					CRMNewContactRecordPage.primaryEmailAddress_edit, primaryEmail);

			pressEnterRobot(minTimeOut);

			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMCasePage.SaveButtonClick);

			reusableFunc.switchToObjectFrame(driver.getWebDriver(), CRMCasePage.pageTitle);

			if (reusableFunc.waitForElementToBePresentBool(driver.getWebDriver(), CRMCasePage.pageTitle, maxTimeOut)) {
				String caseTitleActual = driver.findElement(CRMCasePage.pageTitle).getText();
				System.out.println("Case Title Actual: " + caseTitleActual);

				if (caseTitleActual.equalsIgnoreCase(contactName)) {
					report.updateTestLog("Create New Contact", "Contact Created Successfully: " + contactName,
							Status.PASS);
				} else {
					report.updateTestLog("Create New Contact", "Contact is not created !", Status.FAIL);
				}
				
			} else {
				report.updateTestLog("Create New Contact", "Contact is not created !", Status.FAIL);
			}
		} catch (Exception Ex) {
			report.updateTestLog("Create Contact", "Exception Occured while creating the contact: " + Ex.getMessage(),
					Status.FAIL);
		}
		return contactName;
	}

	public void searchContactRelatedRecordsAndSelect(String recordToSearch) throws InterruptedException {
		try {
			driver.switchTo().defaultContent();
			reusableFunc.navigationToEntity(driver.getWebDriver(), serviceModule, contactEntity);
			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
			//driver.navigate().refresh();
			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
			
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), recordSearchBox);
			// UI_Helpers_OOB.switchToFrame(driver.getWebDriver(),
			// CRMLandingPage.baseFrame0);

			if (reusableFunc.waitForElementToBeClickableBool(driver.getWebDriver(), recordSearchBox, maxTimeOut)) {
				WebElement element = driver.findElement(recordSearchBox);
				element.click();
				element.sendKeys(recordToSearch);
				element.sendKeys(Keys.ENTER);

				By recordXpath = By
						.xpath("//div[@id='crmGrid_divDataArea']//following::a[@title='" + recordToSearch + "']");

				reusableFunc.waitForElementToBeClickableBool(driver.getWebDriver(), recordXpath, maxTimeOut);
				driver.findElement(recordXpath).click();

				By titleXpath = By.xpath("//h1[@title='" + recordToSearch + "']");
				reusableFunc.switchToObjectFrame(driver.getWebDriver(), titleXpath);
				// UI_Helpers_OOB.switchToFrame(driver.getWebDriver(),
				// CRMLandingPage.baseFrame1);

				if (reusableFunc.waitForElementToBeVisible(driver.getWebDriver(), titleXpath, maxTimeOut)) {
					report.updateTestLog("Validating contact entity navigation",
							"Able to Navigate to contact entity and select the record.", Status.PASS);
				} else {
					report.updateTestLog("Validating contact entity navigation", "Not able to select the record.",
							Status.FAIL);
				}
				
				reusableFunc.switchToDefaultFrame(driver.getWebDriver());
				
			}else{
				report.updateTestLog("Validating contact entity navigation", "Not able to locate the search box.",
						Status.FAIL);
			}
		} catch (Exception Ex) {
			report.updateTestLog("Validating contact entity navigation", "Exception Occured: " + Ex.getMessage(),
					Status.FAIL);
		}

	}
	
	public void enterPhoneNumber(By phoneFieldEnable, By phoneFieldEdit, String phoneNumber) {
		try {

			reusableFunc.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Iframe1);

			reusableFunc.enterTextAction_Stale(driver.getWebDriver(), phoneFieldEnable, phoneFieldEdit, phoneNumber);

			driver.findElement(phoneFieldEdit).sendKeys(Keys.ENTER);

			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMCasePage.SaveButtonClick);

		} catch (Exception Ex) {
			report.updateTestLog("Enter Phone Number",
					"Exception Occured while entering phone number: " + Ex.getMessage(), Status.FAIL);
		}
	}

	public void autoFormattingOfManuallyEnteredNumberContact() {
		try {
			String nonFormattedNumber = dataTable.getData("General_Data", "Non_Formatted");
			String formattedNumber = dataTable.getData("General_Data", "Formatted");
			String contactName = createContactWithMandatoryFields();

			searchContactRelatedRecordsAndSelect(contactName);

			// Home Phone
			enterPhoneNumber(CRMNewContactRecordPage.homePhone_enable, CRMNewContactRecordPage.homePhone_edit,
					nonFormattedNumber);

			// Business Phone
			enterPhoneNumber(CRMNewContactRecordPage.businessPhone_enable, CRMNewContactRecordPage.businessPhone_edit,
					nonFormattedNumber);

			reusableFunc.switchToObjectFrame(driver.getWebDriver(), CRMNewContactRecordPage.homePhone_text);
			String homePhone = reusableFunc.getTextFromElement(driver.getWebDriver(),
					CRMNewContactRecordPage.homePhone_text);
			
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), CRMNewContactRecordPage.homePhone_text);			
			String businessPhone = reusableFunc.getTextFromElement(driver.getWebDriver(),
					CRMNewContactRecordPage.businessPhone_text);

			System.out.println(homePhone+"/ "+businessPhone);
			
			if (formattedNumber.equalsIgnoreCase(homePhone)) {
				report.updateTestLog("Validating Phone Number Formatting Associated with Contact",
						"Home Phone number format matched !", Status.PASS);
			} else {
				report.updateTestLog("Validating Phone Number Formatting Associated with Contact",
						"Home Phone number format doesn't match !", Status.FAIL);
			}
			
			if (formattedNumber.equalsIgnoreCase(businessPhone)) {
				report.updateTestLog("Validating Phone Number Formatting Associated with Contact",
						"Business Phone number format matched !", Status.PASS);
			} else {
				report.updateTestLog("Validating Phone Number Formatting Associated with Contact",
						"Business Phone number format doesn't match !", Status.FAIL);
			}

		} catch (Exception Ex) {
			report.updateTestLog("Validating Phone Number Formatting Associated with Contact",
					"Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}
	}

	public void createIndividual() throws InterruptedException{
		
		String FirstName = dataTable.getData("General_Data", "FirstName");
		String LastName = dataTable.getData("General_Data", "LastName");
		String JobTitle = dataTable.getData("General_Data", "JobTitle");
		String AccountName = dataTable.getData("General_Data", "AccountName");
		String Email = dataTable.getData("General_Data", "Email");
		String BusinessPhone = dataTable.getData("General_Data", "BusinessPhone");
		String MobilePhone = dataTable.getData("General_Data", "MobilePhone");
		String ConnectionRole = dataTable.getData("General_Data", "ConnectionRole");
		String ContactMethod = dataTable.getData("General_Data", "ContactMethod");
		String Street1 = dataTable.getData("General_Data", "Street1");
		String Street2 = dataTable.getData("General_Data", "Street2");
		String Street3 = dataTable.getData("General_Data", "Street3");
		String City = dataTable.getData("General_Data", "City");
		String State = dataTable.getData("General_Data", "State");
		String PostalCode = dataTable.getData("General_Data", "PostalCode");
		String Country = dataTable.getData("General_Data", "Country");
		String Fullname = dataTable.getData("General_Data", "fullname");
		boolean FromAccount = false;
		
		try{
			
				 	//navigate to Contacts
		reusableFunc.navigationToEntity(driver.getWebDriver(), serviceModule, contactEntity);
		UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
		//Click on New Button
		if(reusableFunc.waitForElementToBePresent(driver.getWebDriver(), newButton, 50)){
			action.doubleClick(driver.findElement(newButton)).perform();
			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
			
			// Switch to Frame
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), By.xpath("//div[@id='HeaderTitleElement']//nobr"));
			if(reusableFunc.waitForElementToBePresent(driver.getWebDriver(), CRMNewContactRecordPage.firstName_enable , 50)){
				System.out.println("switched to contact edit frame");
		// Enter Data to Create Contact
			driver.findElement(CRMNewContactRecordPage.firstName_enable).click();
			driver.findElement(CRMNewContactRecordPage.firstName_edit).sendKeys(FirstName);
			
			driver.findElement(CRMNewContactRecordPage.lastName_enable).click();
			driver.findElement(CRMNewContactRecordPage.lasttName_edit).sendKeys(LastName);
			
			driver.findElement(CRMNewContactRecordPage.primaryEmailAddress_Enable).click();
			driver.findElement(CRMNewContactRecordPage.primaryEmailAddress_edit).sendKeys(Email);
			
			driver.findElement(CRMNewContactRecordPage.businessPhone_enable).click();
			driver.findElement(CRMNewContactRecordPage.businessPhone_edit).sendKeys(BusinessPhone);
			
			driver.findElement(CRMNewContactRecordPage.mobilePhone_enable).click();
			driver.findElement(CRMNewContactRecordPage.mobilePhone_edit).sendKeys(MobilePhone);
			System.out.println("Mobile No Entered");
			
			
			 // Enter Address 
			driver.findElement(CRMNewContactRecordPage.primaryAddress_Enable).click();
			System.out.println("Address Label Clicked");
			driver.findElement(CRMNewContactRecordPage.primaryAddress_Enable).click();
			System.out.println("Address Label Clicked");
			driver.findElement(CRMNewContactRecordPage.primaryAddress_Edit).sendKeys(Keys.ENTER);
			System.out.println("Textarea Clicked");
			
		
			
			
			reusableFunc.waitForElementToBeClickable(driver.getWebDriver(), CRMNewContactRecordPage.street2_enable, 50);
			System.out.println("Waiting for street2 ");
			driver.findElement(CRMNewContactRecordPage.street2_enable).click();
			
			driver.findElement(CRMNewContactRecordPage.street2_edit).sendKeys(Street2);
			
			System.out.println("Street 2 Entered ");
			
			driver.findElement(CRMNewContactRecordPage.street1_enable).click();
			driver.findElement(CRMNewContactRecordPage.street1_enable).click();
			
			driver.findElement(CRMNewContactRecordPage.street1_edit).sendKeys(Street1);
			System.out.println("Street 1 Entered");
			
			driver.findElement(CRMNewContactRecordPage.street3_enable).click();
			driver.findElement(CRMNewContactRecordPage.street3_enable).click();
			System.out.println("Street 3 Clicked");
			driver.findElement(CRMNewContactRecordPage.street3_edit).sendKeys(Street3);
			System.out.println("Street 3 Entered");
			
			driver.findElement(CRMNewContactRecordPage.city_enable).click();
			driver.findElement(CRMNewContactRecordPage.city_enable).click();
			driver.findElement(CRMNewContactRecordPage.city_edit).sendKeys(City);
			System.out.println("City  Entered");
						
			
			driver.findElement(CRMNewContactRecordPage.state_enable).click();
			driver.findElement(CRMNewContactRecordPage.state_edit).sendKeys(State);
			System.out.println("State Entered");
			
			
			driver.findElement(CRMNewContactRecordPage.postalCode_enable).click();
			System.out.println("PostalCode Clicked ");
			
			UI_Helpers_OOB.switchToAlertAndDismiss(driver.getWebDriver());
			System.out.println("Alert Closeed");
			driver.findElement(CRMNewContactRecordPage.postalCode_enable).click();
			driver.findElement(CRMNewContactRecordPage.postalCode_edit).sendKeys(PostalCode);
			System.out.println("postal Code Entered");
			
			driver.findElement(CRMNewContactRecordPage.country_enable).click();
			System.out.println("Country label Clicked");
			UI_Helpers_OOB.switchToAlertAndDismiss(driver.getWebDriver());
			driver.findElement(CRMNewContactRecordPage.country_enable).click();
	
			driver.findElement(CRMNewContactRecordPage.country_edit).sendKeys(Country);
			
			System.out.println("Country Entered");
			driver.findElement(By.xpath("//button[@text='OK']//span")).click();
			System.out.println("Done Button Clicked Clicked");
			
			driver.findElement(CRMNewContactRecordPage.primaryAddress_Enable).click();
			System.out.println("Address Label Clicked");
			
			
			
			
			driver.findElement(CRMNewContactRecordPage.connectionRole_enable).click();
			driver.findElement(CRMNewContactRecordPage.connectionRole_edit).sendKeys(ConnectionRole);
			driver.findElement(CRMNewContactRecordPage.connectionRole_edit).sendKeys(Keys.ENTER);
			System.out.println("Connection role Entered ");
			
			driver.findElement(By.xpath("//a[@title='"+ConnectionRole+"']")).click();
			
			
			
			}else{
				System.out.println("Not abel to Switch to iframe");
			}
			
			driver.switchTo().defaultContent();
		//Click on Save& Close
			action.doubleClick(driver.findElement(saveClose)).perform();
			System.out.println("Sabe & Close button Clicked");
			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
				
			report.updateTestLog("Creating Individual", "Clicked on Save&Close button , Individual Name : "+FirstName+" "+LastName,
					Status.PASS);	
			
		
			
		}else{
			report.updateTestLog("Creating Individual", "Not able to Click on New Button",
					Status.FAIL);
					
		}
		
			reusableFunc.navigationToEntity(driver.getWebDriver(), Case_MSCRM.serviceModule, Case_MSCRM.caseEntity);
			AdvanceFind_MSCRM adFn = new AdvanceFind_MSCRM(scriptHelper);
			adFn.verifyContactfromAdvanceFind(Fullname,FirstName,LastName );
		
		}catch (Exception Ex) {
			report.updateTestLog("Creating Individual", "Exception Occurec : " + Ex.getMessage(),
					Status.FAIL);}
	}

	public void updateIndividual() throws InterruptedException{
		String FirstName = dataTable.getData("General_Data", "FirstName");
		String LastName = dataTable.getData("General_Data", "LastName");
		try{
			searchContactRelatedRecordsAndSelect(FirstName + LastName);
			
		}catch(Exception Ex){
			
		}
	} 

	public void validationMessagesForPhoneNumbersInidividual() throws Exception {
		String FirstName = dataTable.getData("General_Data", "FirstName");
		String LastName = dataTable.getData("General_Data", "LastName");
		String Email = dataTable.getData("General_Data", "Email");
		String MobilePhone = dataTable.getData("General_Data", "MobilePhone");
		String BusinessPhone = dataTable.getData("General_Data", "BusinessPhone");
		String HomePhone = dataTable.getData("General_Data", "HomePhone");
		
        
        

      //navigate to Contacts
      		reusableFunc.navigationToEntity(driver.getWebDriver(), serviceModule, contactEntity);
      		UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
      		//Click on New Button
      		if(reusableFunc.waitForElementToBePresent(driver.getWebDriver(), newButton, 50)){
      			action.doubleClick(driver.findElement(newButton)).perform();
      			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
      			
      			// Switch to Frame
      			reusableFunc.switchToObjectFrame(driver.getWebDriver(), By.xpath("//div[@id='HeaderTitleElement']//nobr"));
      			if(reusableFunc.waitForElementToBePresent(driver.getWebDriver(), CRMNewContactRecordPage.firstName_enable , 50)){
      				System.out.println("switched to contact edit frame");
      				
      		
      			// Enter Data to Create Contact
      				driver.findElement(CRMNewContactRecordPage.firstName_enable).click();
      				driver.findElement(CRMNewContactRecordPage.firstName_edit).sendKeys(FirstName);
      				
      				driver.findElement(CRMNewContactRecordPage.lastName_enable).click();
      				driver.findElement(CRMNewContactRecordPage.lasttName_edit).sendKeys(LastName);
      			// Email 	
      				driver.findElement(CRMNewContactRecordPage.primaryEmailAddress_Enable).click();
      				driver.findElement(CRMNewContactRecordPage.primaryEmailAddress_edit).sendKeys(Email);
      				driver.findElement(CRMNewContactRecordPage.mobilePhone_enable).click();
      				if (driver.findElement(CRMNewContactRecordPage.email_Message).isDisplayed()) {
	      				      					String emailMessage = reusableFunc.getTextFromElement(driver.getWebDriver(), CRMNewContactRecordPage.email_Message);
      					System.out.println("incorrect Business number Enter : "+emailMessage );
      					report.updateTestLog("Validating  Invalid Mobile Number Validation Message",
      	                            "System dispalys validation message for incorrect Mobile phone as :"+emailMessage, Status.PASS);
      				}else report.updateTestLog("Validating  Email Address  Validation Message",
                            "System does not dispalys validation message for incorrect  Email Address ", Status.FAIL);
      				
      				// Business Phone 
      				driver.findElement(CRMNewContactRecordPage.businessPhone_enable).click();
      				driver.findElement(CRMNewContactRecordPage.businessPhone_edit).sendKeys(BusinessPhone);
      				driver.findElement(CRMNewContactRecordPage.mobilePhone_enable).click();
      				
      				if (driver.findElement(CRMNewContactRecordPage.businessPhone_Message).isDisplayed()) { 					
      					String BusinessPhoneMessage = reusableFunc.getTextFromElement(driver.getWebDriver(), CRMNewContactRecordPage.businessPhone_Message);
      					System.out.println("incorrect Business number Enter : "+BusinessPhoneMessage );
      					report.updateTestLog("Validating  Invalid Mobile Number Validation Message",
      	                            "System dispalys validation message for incorrect Mobile phone as :"+BusinessPhoneMessage, Status.PASS);
      	        }else report.updateTestLog("Validating Business Phone   Validation Message",
                        "System does not dispalys validation message for incorrect  Business Phone ", Status.FAIL);
      				//Mobile Phone
      				driver.findElement(CRMNewContactRecordPage.mobilePhone_enable).click();
      				driver.findElement(CRMNewContactRecordPage.mobilePhone_edit).sendKeys(MobilePhone);
      				driver.findElement(CRMNewContactRecordPage.primaryEmailAddress_Enable).click();		
      				if (driver.findElement(CRMNewContactRecordPage.mobilePhone_Message).isDisplayed()) { 					
      					String MobilePhoneMessage = reusableFunc.getTextFromElement(driver.getWebDriver(), CRMNewContactRecordPage.mobilePhone_Message);
      					System.out.println("incorrect Business number Enter : "+MobilePhoneMessage );
      					report.updateTestLog("Validating  Invalid Mobile Number Validation Message",
      	                            "System dispalys validation message for incorrect Mobile phone as :"+MobilePhoneMessage, Status.PASS);
      	        }else report.updateTestLog("Validating Mobile Phone Validation Message",
                        "System does not dispalys validation message for incorrect  Mobile Phone ", Status.FAIL);
      				//Home Phone
      				driver.findElement(CRMNewContactRecordPage.homePhone_enable).click();
      				driver.findElement(CRMNewContactRecordPage.homePhone_edit).sendKeys(HomePhone);
      				driver.findElement(CRMNewContactRecordPage.primaryEmailAddress_Enable).click();		
      				if (driver.findElement(CRMNewContactRecordPage.mobilePhone_Message).isDisplayed()) { 					
      					String homePhoneMessage = reusableFunc.getTextFromElement(driver.getWebDriver(), CRMNewContactRecordPage.homePhone_Message);
      					System.out.println("incorrect Business number Enter : "+homePhoneMessage );
      					report.updateTestLog("Validating  Invalid Mobile Number Validation Message",
      	                            "System dispalys validation message for incorrect Mobile phone as :"+homePhoneMessage, Status.PASS);
      	        }else report.updateTestLog("Validating Home Phone Validation Message",
                        "System does not dispalys validation message for incorrect  Home Phone ", Status.FAIL);
      				
      				
      				
      				
      				
 }

}
	}

	public void verifyContactID() throws Exception{
		
		 String FirstName = dataTable.getData("General_Data", "FirstName");
			String LastName = dataTable.getData("General_Data", "LastName");
			String Fullname = dataTable.getData("General_Data", "fullname");
			
		 try{
			 AdvanceFind_MSCRM adFn = new AdvanceFind_MSCRM(scriptHelper);
				adFn.verifyContactfromAdvanceFind(Fullname,FirstName,LastName);
			
			 
		 }catch (Exception Ex){
			 report.updateTestLog("Verifying Contact ID from Advance find ",
                     "Exception occurred : "+Ex, Status.FAIL);
			 
		 }
		 		
	}
	
	public void verifyContactNotes() throws Exception {
		try{
			String fullName= dataTable.getData("General_Data", "fullname");
			
			searchContactRelatedRecordsAndSelect(fullName);
			// Store the current window handle
			String winHandleBefore = driver.getWindowHandle();
			// Perform the click operation that opens new window
			driver.findElement(CRMNewContactRecordPage.allNotes).click();
			
			// Switch to new window opened
						for(String winHandle : driver.getWindowHandles()){
						    driver.switchTo().window(winHandle);
						}
						Thread.sleep(100);
						// Perform the actions on new window
						
			
			
			

			

			

			

			
			
			   
			    
			    
			   
			    driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='resultFrame']")));
			    System.out.println("Framed Swithced");
			  //latest Notes
			    
			    String caseModiOn =driver.findElement(By.xpath("//div[@id='reportViewer_ReportViewer']//tr[2]//tr[3]/td[1]/div//div")).getText();
			    String caseTitle =driver.findElement(By.xpath("//div[@id='reportViewer_ReportViewer']//tr[2]//tr[3]/td[2]/div//div")).getText();
			    String caseStatus =driver.findElement(By.xpath("//div[@id='reportViewer_ReportViewer']//tr[2]//tr[3]/td[3]/div//div")).getText();
			    String policyNo =driver.findElement(By.xpath("//div[@id='reportViewer_ReportViewer']//tr[2]//tr[3]/td[4]/div//div")).getText();
			    String noteTitle =driver.findElement(By.xpath("//div[@id='reportViewer_ReportViewer']//tr[2]//tr[3]/td[5]/div//div")).getText();
			    String note =driver.findElement(By.xpath("//div[@id='reportViewer_ReportViewer']//tr[2]//tr[3]/td[6]/div//div")).getText();
			    String noteCreatedOn =driver.findElement(By.xpath("//div[@id='reportViewer_ReportViewer']//tr[2]//tr[3]/td[7]/div//div")).getText();
			    String noteCreatedBy =driver.findElement(By.xpath("//div[@id='reportViewer_ReportViewer']//tr[2]//tr[3]/td[8]/div//div")).getText();
			    
			    
			    
			    
			    String s = String.format("%s\n%s\n%s\n%s\n%s\n%s"
			            , "Case Modified On: "+caseModiOn
			            , " , Case Title: "+caseTitle
			            , " , Case Status: "+caseStatus
			            , " , Policy No: "+policyNo
			            , " , Case Note Title: "+noteTitle
			            , " , Case Note: "+note
			            , " , Case Note Created On: "+noteCreatedOn
			            , " , Case Note Created By: "+noteCreatedBy
			   );
			    
			   
			    
			    report.updateTestLog("Verifying Latest Notes of an individual",s, Status.PASS);
			    
			    
			    
			    //Save note into Excel
			    driver.findElement(By.xpath("//a[@title='Export']")).click();
			  
			    driver.findElement(By.xpath("//a[@title='Excel']")).click();
			    
			    Thread.sleep(1000);
			    
			    report.updateTestLog("Verifying All Notes of an individual", "Downloading Notes  ", Status.PASS);	
			    
				// Close the new window, if that window no more required
							driver.close();

				// Switch back to original browser (first window)
				driver.switchTo().window(winHandleBefore);

				// Continue with original browser (first window)
				
			
			
		}
		catch(Exception Ex){
			report.updateTestLog("Verifying All Notes of an individual", "Exception Occured : "+Ex, Status.FAIL);
		}
	}
}