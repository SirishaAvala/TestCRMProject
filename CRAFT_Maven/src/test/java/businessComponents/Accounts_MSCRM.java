package businessComponents;

import pageObjects.*;
import com.cognizant.Craft.*;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
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
public class Accounts_MSCRM extends ReusableLibrary {
	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */

	WebElement element = null;
	Case_MSCRM caseClass = new Case_MSCRM(scriptHelper);
	Contacts_MSCRM contactClass = new Contacts_MSCRM(scriptHelper);
	reusableFunctions reusableFunc = new reusableFunctions(scriptHelper);

	// Page Objects
	static int maxTimeOut = 40;
	static int minTimeOut = 10;
	public static final By recordSearchBox = By.cssSelector("input#crmGrid_findCriteria");
	public static final By serviceModule = By.xpath("//img[@alt='Service']//parent::span");
	public static final By accountEntity = By.cssSelector("a#nav_accts img");
	public static final By notesTab = By.xpath("//div[@id='header_notescontrol']//a[@title='NOTES']");
	public static final By notesDescriptionField = By.xpath("//textarea[@id='createNote_notesTextBox']");
	public static final By notesTitleField = By.xpath("//input[@id='createNote_notesTitleBox']");
	public static final By doneButton = By.xpath("//button[@title='Done']");
	public static final By notesCreationVerification = By.xpath("//span[text()='Just now']");
	
	// latest created case name
	 public static  String latestCase= null;
	
	public Accounts_MSCRM(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}
	
	/**
	 * create_Account This method is used to create new Account from Account
	 * entity
	 * 
	 * @throws Exception
	 * 
	 * reusableFunctions reusableFunc = new reusableFunctions(scriptHelper);
	 */
	public void create_Account() throws Exception {

		String AccountName = dataTable.getData("General_Data", "AccountName");
		String PrimaryContactID = dataTable.getData("General_Data", "PrimaryContactID");
		String AccountNumber = dataTable.getData("General_Data", "AccountNumber");
		String ParentContactID = dataTable.getData("General_Data", "ParentContactID");
		String EmailAddress = dataTable.getData("General_Data", "EmailAddress");
		String Telephone1 = dataTable.getData("General_Data", "Telephone1");
		String Telephone2 = dataTable.getData("General_Data", "Telephone2");
		String FaxNumber = dataTable.getData("General_Data", "FaxNumber");
		String WebsiteURL = dataTable.getData("General_Data", "WebsiteURL");

		report.updateTestLog("Enter user credentials",
				"Specify " + "PrimaryContactID = " + PrimaryContactID + ", " + "AccountNumber = " + AccountNumber + ", "
						+ "ParentContactID = " + ParentContactID + ", " + "EmailAddress = " + EmailAddress + ", "
						+ "Telephone1 = " + Telephone1 + ", " + "Telephone2 = " + Telephone2 + ", " + "FaxNumber = "
						+ FaxNumber + ", " + "WebsiteURL = " + WebsiteURL + ", " + "AccountName = " + AccountName,
				Status.PASS);

		reusableFunc.navigationToEntity(driver.getWebDriver(), CRMNewAccountRecordPage.salesModule,
				CRMNewAccountRecordPage.accountEntity);
		Thread.sleep(5000);

		/*
		 * element = driver.findElement(CRMNewRecordPage.NewButtonClick); new
		 * Actions(driver.getWebDriver()).moveToElement(element, 0,
		 * 0).click().perform(); report.updateTestLog("Account Entity",
		 * "Click on new button", Status.SCREENSHOT);
		 */

		reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.NewButtonClick);
		report.updateTestLog("Account Entity", "Click on new button", Status.SCREENSHOT);
		Thread.sleep(2000);
		UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.frame1);
		// WebElement iframeElement =
		// driver.findElement(By.id("contentIFrame1"));
		// driver.switchTo().frame(iframeElement);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.primaryContactId_enable,
				CRMNewAccountRecordPage.primaryContactId_edit, PrimaryContactID);
		report.updateTestLog("Account Entity", "Fill Primary Contact ID", Status.SCREENSHOT);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.accountName_enable,
				CRMNewAccountRecordPage.accountName_edit, AccountName);
		report.updateTestLog("Account Entity", "Fill Account Name", Status.SCREENSHOT);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.accountNumber_enable,
				CRMNewAccountRecordPage.accountNumber_edit, AccountNumber);
		report.updateTestLog("Account Entity", "Fill Account Number", Status.SCREENSHOT);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.parentaccountid_enable,
				CRMNewAccountRecordPage.parentaccountid_edit, ParentContactID);
		report.updateTestLog("Account Entity", "Fill Parent Account ID", Status.SCREENSHOT);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.emailaddress_enable,
				CRMNewAccountRecordPage.emailaddress_edit, EmailAddress);
		report.updateTestLog("Account Entity", "Fill Email Address", Status.SCREENSHOT);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.telephone1_enable,
				CRMNewAccountRecordPage.telephone1_edit, Telephone1);
		report.updateTestLog("Account Entity", "Fill Telephone Number 1 ", Status.SCREENSHOT);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.telephone2_enable,
				CRMNewAccountRecordPage.telephone2_edit, Telephone2);
		report.updateTestLog("Account Entity", "Fill Telephone Number 2 ", Status.SCREENSHOT);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.fax_enable,
				CRMNewAccountRecordPage.fax_edit, FaxNumber);
		report.updateTestLog("Account Entity", "Fill Fax Number ", Status.SCREENSHOT);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.websiteurl_enable,
				CRMNewAccountRecordPage.websiteurl_edit, WebsiteURL);
		report.updateTestLog("Account Entity", "Fill Website Url ", Status.SCREENSHOT);

		UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
		// driver.switchTo().defaultContent();
		Thread.sleep(5000);

		reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.SaveButtonClick);
		report.updateTestLog("Save", "Click the save entity", Status.SCREENSHOT);

		Thread.sleep(5000);
	}

	/***
	 * verify_Account This method is used to verify presence of account record.
	 * 
	 * @throws Exception
	 */
	public void verify_Account() throws Exception {
		try {

			String recordValue = dataTable.getData("General_Data", "recordValue");

			reusableFunc.navigationToEntity(driver.getWebDriver(), CRMNewAccountRecordPage.salesModule,
					CRMNewAccountRecordPage.accountEntity);
			Thread.sleep(1500);
			report.updateTestLog("Account Page", "Search for the account record", Status.SCREENSHOT);

			driver.navigate().refresh();
			Thread.sleep(3000);

			// Verify Account

			By recordSearchBox = By.xpath("//*[@id='crmGrid_findCriteria']");
			By recordLink = By.xpath("//a[@title='" + recordValue + "']");

			UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.frame0);

			UI_Helpers_OOB.waitForElementToBeClickable(driver.getWebDriver(), recordSearchBox, 5);

			WebElement staleElement = driver.findElement(recordSearchBox);

			Actions builder = new Actions(driver.getWebDriver());
			builder.moveToElement(staleElement, 1, 1).click();
			staleElement.sendKeys(recordValue);
			staleElement.sendKeys(Keys.ENTER);
			UI_Helpers_OOB.implicitWait(driver.getWebDriver(), 10);

			report.updateTestLog("Account Page", "Click on the account record", Status.SCREENSHOT);
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
				report.updateTestLog("Account Page", "Record doesn't exist !", Status.FAIL);
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
			report.updateTestLog("Account Page", "Record doesn't exist !", Status.FAIL);
		} catch (NoSuchElementException ex) {
			// System.out.println("Record doesn't exist !");
			report.updateTestLog("Account Page", "Record doesn't exist !", Status.FAIL);
		} catch (TimeoutException ex) {
			// System.out.println("Record doesn't exist !");
			report.updateTestLog("Account Page", "Record doesn't exist !", Status.FAIL);

		}

		report.updateTestLog("Account Page", "Account Verified Successfully !!", Status.SCREENSHOT);
	}

	/*****
	 * method ='navigateToTaskFromAccount' This function is used to navigate to
	 * new task Activity from Selected Account detail page *****
	 * 
	 * @throws Exception
	 */
	public void navigateAndCreateTaskFromAccount() throws Exception {
		// WebElement iframeElement =
		// driver.findElement(By.id("contentIFrame1"));
		// driver.switchTo().frame(iframeElement);
		UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.frame1);
		if (driver.findElement(By.xpath(CRMNewAccountRecordPage.addActivityRecordimg)).isDisplayed()) {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyRelease(KeyEvent.VK_DOWN);
			Thread.sleep(5000);
			driver.findElement((CRMNewAccountRecordPage.NotesAccount_edit)).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath(CRMNewAccountRecordPage.addActivityRecordimg)).click();
			Thread.sleep(1000);

			// UI_Helpers_OOB.MouseClick_Action(driver.getWebDriver(),
			// CRMNewAccountRecordPage.taskbtnOnAccount);
			reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.taskbtnOnAccount);
			Thread.sleep(1000);
			Thread.sleep(10000);

			UI_Helpers_OOB.switchWindow(driver.getWebDriver(), "Task: New Task");

			// frame id is different than that on direct new task creation page
			// driver.switchTo().defaultContent();
			// driver.switchTo().frame("contentIFrame0");
			UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.frame0);

			UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMActivityPage.subjectNewtask_enable,
					CRMActivityPage.subjectNewtask_edit, "Subject");
			report.updateTestLog("Activity Entity", "Enter Subject for new Task", Status.SCREENSHOT);
			Thread.sleep(5000);

			// driver.switchTo().defaultContent();

			UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
			reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMActivityPage.activitySave);
			report.updateTestLog("Save", "Click the save button to create new task", Status.SCREENSHOT);
			Thread.sleep(5000);
		} else {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_END);
			robot.keyRelease(KeyEvent.VK_END);
			robot.delay(1000);

			reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.notesAndActivities);
			Thread.sleep(1000);

			driver.findElement(By.xpath(CRMNewAccountRecordPage.addActivityRecordimg)).click();
			Thread.sleep(1000);

			driver.findElement((CRMNewAccountRecordPage.taskbtnOnAccount)).click();
			Thread.sleep(10000);

			UI_Helpers_OOB.switchWindow(driver.getWebDriver(), "Task: New Task");

			// frame id is different than that on direct new task creation page
			// driver.switchTo().defaultContent();
			UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
			UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.frame0);
			// driver.switchTo().frame("contentIFrame0");

			UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMActivityPage.subjectNewtask_enable,
					CRMActivityPage.subjectNewtask_edit, "Subject");
			report.updateTestLog("Activity Entity", "Enter Subject for new Task", Status.SCREENSHOT);
			Thread.sleep(5000);
			UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
			// driver.switchTo().defaultContent();

			reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMActivityPage.activitySave);
			report.updateTestLog("Save", "Click the save button to create new task", Status.SCREENSHOT);
			Thread.sleep(5000);

		}

	}

	/*****
	 * method ='navigateAndCreateMailFromAccount' This function is used to
	 * navigate to new mail activity from Selected Account detail page *****
	 * 
	 * @throws Exception
	 */
	public void navigateAndCreateMailFromAccount() throws Exception {
		UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.frame1);
		String To = dataTable.getData("General_Data", "To");
		String Cc = dataTable.getData("General_Data", "Cc");
		String Bcc = dataTable.getData("General_Data", "Bcc");
		String Subject = dataTable.getData("General_Data", "Subject");
		String Signature = dataTable.getData("General_Data", "Signature");
		try {
			if (driver.findElement(By.xpath(CRMNewAccountRecordPage.addActivityRecordimg)).isDisplayed()) {
				Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_DOWN);
				robot.keyRelease(KeyEvent.VK_DOWN);
				Thread.sleep(5000);
				driver.findElement((CRMNewAccountRecordPage.NotesAccount_edit)).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath(CRMNewAccountRecordPage.addActivityRecordimg)).click();
				Thread.sleep(1000);

				reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.emailbtnOnAccount);
				Thread.sleep(1000);
				Thread.sleep(10000);

				UI_Helpers_OOB.switchWindow(driver.getWebDriver(), "Email: New Email");

				UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
				UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.frame0);

				UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMActivityPage.activityCcimg_enable,
						CRMActivityPage.activityCcimg_edit, Cc);

				report.updateTestLog("Activity Entity", "Enter Cc mail address for new Mail", Status.SCREENSHOT);

				UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMActivityPage.activityBccimg_enable,
						CRMActivityPage.activityBccimg_edit, Bcc);

				report.updateTestLog("Activity Entity", "Enter Bcc mail address for new Mail", Status.SCREENSHOT);
				UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMActivityPage.activitymailSubject_enable,
						CRMActivityPage.activitymailSubject_edit, Subject);
				report.updateTestLog("Activity Entity", "Enter Subject for new Mail", Status.SCREENSHOT);
				UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());

				reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMActivityPage.activitySave);
				report.updateTestLog("Save", "Click the save button to create new task", Status.SCREENSHOT);
				Thread.sleep(5000);

			}
		} catch (Exception e) {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_END);
			robot.keyRelease(KeyEvent.VK_END);
			robot.delay(1000);

			reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.notesAndActivities);
			Thread.sleep(1000);

			driver.findElement(By.xpath(CRMNewAccountRecordPage.addActivityRecordimg)).click();
			Thread.sleep(1000);

			driver.findElement((CRMNewAccountRecordPage.emailbtnOnAccount)).click();
			Thread.sleep(10000);

			UI_Helpers_OOB.switchWindow(driver.getWebDriver(), "Email: New Email");

			UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());

			UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.frame0);

			UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMActivityPage.activityCcimg_enable,
					CRMActivityPage.activityCcimg_edit, Cc);

			report.updateTestLog("Activity Entity", "Enter Cc mail address for new Mail", Status.SCREENSHOT);

			UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMActivityPage.activityBccimg_enable,
					CRMActivityPage.activityBccimg_edit, Bcc);

			report.updateTestLog("Activity Entity", "Enter Bcc mail address for new Mail", Status.SCREENSHOT);
			UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMActivityPage.activitymailSubject_enable,
					CRMActivityPage.activitymailSubject_edit, Subject);
			report.updateTestLog("Activity Entity", "Enter Subject for new Mail", Status.SCREENSHOT);

			driver.switchTo().frame("descriptionEditIFrame");
			UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMActivityPage.activitymailbody_enable,
					CRMActivityPage.activitymailbody_enable, Signature);
			report.updateTestLog("Activity Entity", "Enter Subject for new Mail", Status.SCREENSHOT);
			UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());

			reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMActivityPage.activitySave);
			report.updateTestLog("Save", "Click the save button to create new task", Status.SCREENSHOT);
			Thread.sleep(5000);

		}

	}

	/*****
	 * method ='navigateAndCreateAppointmentFromAccount' This function is used
	 * to navigate to new appointment Activity from Selected Account detail page
	 * *****
	 * 
	 * @throws Exception
	 */
	public void navigateAndCreateAppointmentFromAccount() throws Exception {
		UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.frame1);

		String Subject = dataTable.getData("General_Data", "Subject");

		try {
			if (driver.findElement(By.xpath(CRMNewAccountRecordPage.addActivityRecordimg)).isDisplayed()) {
				Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_DOWN);
				robot.keyRelease(KeyEvent.VK_DOWN);
				Thread.sleep(5000);
				driver.findElement((CRMNewAccountRecordPage.NotesAccount_edit)).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath(CRMNewAccountRecordPage.addActivityRecordimg)).click();
				Thread.sleep(1000);

				reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.appointmentbtnOnAccount);
				Thread.sleep(1000);
				Thread.sleep(10000);

				UI_Helpers_OOB.switchWindow(driver.getWebDriver(), "Appointment: New Appointment");

				UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
				UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.frame0);

				UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMActivityPage.subjectNewtask_enable,
						CRMActivityPage.subjectNewtask_edit, Subject);
				report.updateTestLog("Activity Entity", "Enter Subject for new appointment", Status.SCREENSHOT);

				UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
				reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMActivityPage.activitySave);
				report.updateTestLog("Save", "Click the save button to create new appointment", Status.SCREENSHOT);
				Thread.sleep(5000);

			}
		} catch (Exception e) {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_END);
			robot.keyRelease(KeyEvent.VK_END);
			robot.delay(1000);

			reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.notesAndActivities);
			Thread.sleep(1000);

			driver.findElement(By.xpath(CRMNewAccountRecordPage.addActivityRecordimg)).click();
			Thread.sleep(1000);

			driver.findElement((CRMNewAccountRecordPage.appointmentbtnOnAccount)).click();
			Thread.sleep(10000);

			UI_Helpers_OOB.switchWindow(driver.getWebDriver(), "Appointment: New Appointment");

			UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());

			UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.frame0);

			UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMActivityPage.subjectNewtask_enable,
					CRMActivityPage.subjectNewtask_edit, Subject);
			report.updateTestLog("Activity Entity", "Enter Subject for new appointment", Status.SCREENSHOT);

			UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
			reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMActivityPage.activitySave);
			report.updateTestLog("Save", "Click the save button to create new appointment", Status.SCREENSHOT);
			Thread.sleep(5000);

		}

	}

	/*****
	 * method ='navigateAndCreatePhoneCallFromAccount' This function is used to
	 * navigate to new phone call activity from Selected Account detail page
	 * *****
	 * 
	 * @throws Exception
	 */
	public void navigateAndCreatePhoneCallFromAccount() throws Exception {
		UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.frame1);

		String Subject = dataTable.getData("General_Data", "Subject");
		String To = dataTable.getData("General_Data", "To");

		try {
			if (driver.findElement(By.xpath(CRMNewAccountRecordPage.addActivityRecordimg)).isDisplayed()) {
				Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_DOWN);
				robot.keyRelease(KeyEvent.VK_DOWN);
				Thread.sleep(5000);
				driver.findElement((CRMNewAccountRecordPage.NotesAccount_edit)).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath(CRMNewAccountRecordPage.addActivityRecordimg)).click();
				Thread.sleep(1000);

				reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.phonecallbtnOnAccount);
				Thread.sleep(1000);
				Thread.sleep(10000);

				UI_Helpers_OOB.switchWindow(driver.getWebDriver(), "Phone Call: New Phone Call");

				UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
				UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.frame0);

				UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMActivityPage.activityToimg_enable,
						CRMActivityPage.activityToimg_edit, To);

				report.updateTestLog("Activity Entity", "Enter To mail address for new Mail", Status.SCREENSHOT);

				UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMActivityPage.activitymailSubject_enable,
						CRMActivityPage.activitymailSubject_edit, Subject);

				report.updateTestLog("Activity Entity", "Enter Subject for new Mail", Status.SCREENSHOT);
				Thread.sleep(2000);
				UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());

				reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMActivityPage.activitySave);
				report.updateTestLog("Save", "Click the save button to create new appointment", Status.SCREENSHOT);
				Thread.sleep(5000);

			}
		} catch (Exception e) {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_END);
			robot.keyRelease(KeyEvent.VK_END);
			robot.delay(1000);

			reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.notesAndActivities);
			Thread.sleep(1000);

			driver.findElement(By.xpath(CRMNewAccountRecordPage.addActivityRecordimg)).click();
			Thread.sleep(1000);

			driver.findElement((CRMNewAccountRecordPage.phonecallbtnOnAccount)).click();
			Thread.sleep(10000);

			UI_Helpers_OOB.switchWindow(driver.getWebDriver(), "Phone Call: New Phone Call");

			UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());

			UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.frame0);

			UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMActivityPage.activityToimg_enable,
					CRMActivityPage.activityToimg_edit, To);

			report.updateTestLog("Activity Entity", "Enter To mail address for new Mail", Status.SCREENSHOT);

			UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMActivityPage.activitymailSubject_enable,
					CRMActivityPage.activitymailSubject_edit, Subject);

			report.updateTestLog("Activity Entity", "Enter Subject for new Mail", Status.SCREENSHOT);
			Thread.sleep(2000);
			UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());

			reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMActivityPage.activitySave);
			report.updateTestLog("Save", "Click the save button to create new appointment", Status.SCREENSHOT);
			Thread.sleep(5000);

		}

	}

	/*****
	 * method ='account_Merging' This function is used to Merge accounts *****
	 * 
	 * @throws Exception
	 */
	public void account_Merging() throws Exception {
		String[] AccountName = dataTable.getData("General_Data", "AccountName").split(",");
		reusableFunc.navigationToEntity(driver.getWebDriver(), CRMNewAccountRecordPage.salesModule,
				CRMNewAccountRecordPage.accountEntity);
		Thread.sleep(5000);

		UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.frame0);

		UI_Helpers_OOB.closeAllOtherWindows(driver.getWebDriver());
		Thread.sleep(2000);

		UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.frame0);
		for (int i = 0; i < AccountName.length; i++) {
			Actions builder = new Actions(driver.getWebDriver());
			builder.moveToElement(driver.findElement(
					By.xpath("//input[@title='" + AccountName[i] + "']//following-sibling::img[@alt='Checkbox']")))
					.build().perform();

			Thread.sleep(5000);

			reusableFunc.MouseClickAction_Stale(driver.getWebDriver(),
					By.xpath("//input[@title='" + AccountName[i] + "']//following-sibling::img"));

		}
		report.updateTestLog("Account Merging", "Select Account for merging", Status.SCREENSHOT);

		UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
		reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.mergebtn);
		Thread.sleep(5000);
		report.updateTestLog("Account Merging", "Click Merge button", Status.SCREENSHOT);
		UI_Helpers_OOB.switchWindow(driver.getWebDriver(), "Merge Records");
		Thread.sleep(5000);

		UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());

		driver.switchTo().frame("InlineDialog_Iframe");
		reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.okBtnmegePg);

		UI_Helpers_OOB.switchToAlertAndAccept(driver.getWebDriver());
		Thread.sleep(5000);
		report.updateTestLog("Account Merging", "Click OK button on Merge Account page", Status.SCREENSHOT);

	}

	/*****
	 * method ='navigateAndCreateServiceActivityFromAccount' This function is
	 * used to navigate to Service Activity from Selected Account detail page
	 * *****
	 * 
	 * @throws Exception
	 */
	public void navigateToServiceActivityFromAccount() throws Exception {

		UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.frame1);

		try {
			if (driver.findElement(By.xpath(CRMNewAccountRecordPage.addActivityRecordimg)).isDisplayed()) {
				Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_DOWN);
				robot.keyRelease(KeyEvent.VK_DOWN);
				Thread.sleep(5000);
				driver.findElement((CRMNewAccountRecordPage.NotesAccount_edit)).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath(CRMNewAccountRecordPage.addActivityRecordimg)).click();
				Thread.sleep(1000);

				reusableFunc.MouseClickAction_Stale(driver.getWebDriver(),
						CRMNewAccountRecordPage.serviceActivitybtnOnAccount);
				Thread.sleep(1000);
				Thread.sleep(10000);
			}
		} catch (Exception e) {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_END);
			robot.keyRelease(KeyEvent.VK_END);
			robot.delay(1000);

			reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.notesAndActivities);
			Thread.sleep(1000);

			driver.findElement(By.xpath(CRMNewAccountRecordPage.addActivityRecordimg)).click();
			Thread.sleep(1000);

			driver.findElement((CRMNewAccountRecordPage.serviceActivitybtnOnAccount)).click();
			Thread.sleep(10000);
		}
	}

	/**
	 * navigateContact_FromAccount This function is used to navigate to contact
	 * detail page from account
	 * 
	 * @throws InterruptedException
	 */
	public void navigateContact_FromAccount() throws InterruptedException {

		UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.frame1);

		try {
			if (driver.findElement(CRMNewAccountRecordPage.createContactImg).isDisplayed()) {
				reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.createContactImg);
				Thread.sleep(2000);
				report.updateTestLog("Contact Entity", "Click on New Contact button", Status.SCREENSHOT);

			}
		} catch (Exception e) {
			reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.expand);
			Thread.sleep(2000);
			report.updateTestLog("Contact Entity", "Click on Expand Contact button", Status.SCREENSHOT);

		}

	}

	public void create_AccountMSCRM() throws Exception {

		String AccountName = dataTable.getData("General_Data", "AccountName");
		String PrimaryContactID = dataTable.getData("General_Data", "PrimaryContactID");
		String AccountNumber = dataTable.getData("General_Data", "AccountNumber");
		String ParentContactID = dataTable.getData("General_Data", "ParentContactID");
		String EmailAddress = dataTable.getData("General_Data", "EmailAddress");
		String Telephone1 = dataTable.getData("General_Data", "Telephone1");
		String Telephone2 = dataTable.getData("General_Data", "Telephone2");
		String FaxNumber = dataTable.getData("General_Data", "FaxNumber");
		String WebsiteURL = dataTable.getData("General_Data", "WebsiteURL");

		report.updateTestLog("Enter user credentials",
				"Specify " + "PrimaryContactID = " + PrimaryContactID + ", " + "AccountNumber = " + AccountNumber + ", "
						+ "ParentContactID = " + ParentContactID + ", " + "EmailAddress = " + EmailAddress + ", "
						+ "Telephone1 = " + Telephone1 + ", " + "Telephone2 = " + Telephone2 + ", " + "FaxNumber = "
						+ FaxNumber + ", " + "WebsiteURL = " + WebsiteURL + ", " + "AccountName = " + AccountName,
				Status.PASS);

		reusableFunc.navigationToEntity(driver.getWebDriver(), CRMNewAccountRecordPage.salesModule,
				CRMNewAccountRecordPage.accountEntity);
		Thread.sleep(5000);

		/*
		 * element = driver.findElement(CRMNewRecordPage.NewButtonClick); new
		 * Actions(driver.getWebDriver()).moveToElement(element, 0,
		 * 0).click().perform(); report.updateTestLog("Account Entity",
		 * "Click on new button", Status.SCREENSHOT);
		 */

		reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.NewButtonClick);
		report.updateTestLog("Account Entity", "Click on new button", Status.SCREENSHOT);
		Thread.sleep(2000);
		UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.frame1);
		// WebElement iframeElement =
		// driver.findElement(By.id("contentIFrame1"));
		// driver.switchTo().frame(iframeElement);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.primaryContactId_enable,
				CRMNewAccountRecordPage.primaryContactId_edit, PrimaryContactID);
		report.updateTestLog("Account Entity", "Fill Primary Contact ID", Status.SCREENSHOT);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.accountName_enable,
				CRMNewAccountRecordPage.accountName_edit, AccountName);
		report.updateTestLog("Account Entity", "Fill Account Name", Status.SCREENSHOT);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.accountNumber_enable,
				CRMNewAccountRecordPage.accountNumber_edit, AccountNumber);
		report.updateTestLog("Account Entity", "Fill Account Number", Status.SCREENSHOT);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.parentaccountid_enable,
				CRMNewAccountRecordPage.parentaccountid_edit, ParentContactID);
		report.updateTestLog("Account Entity", "Fill Parent Account ID", Status.SCREENSHOT);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.emailaddress_enable,
				CRMNewAccountRecordPage.emailaddress_edit, EmailAddress);
		report.updateTestLog("Account Entity", "Fill Email Address", Status.SCREENSHOT);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.telephone1_enable,
				CRMNewAccountRecordPage.telephone1_edit, Telephone1);
		report.updateTestLog("Account Entity", "Fill Telephone Number 1 ", Status.SCREENSHOT);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.telephone2_enable,
				CRMNewAccountRecordPage.telephone2_edit, Telephone2);
		report.updateTestLog("Account Entity", "Fill Telephone Number 2 ", Status.SCREENSHOT);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.fax_enable,
				CRMNewAccountRecordPage.fax_edit, FaxNumber);
		report.updateTestLog("Account Entity", "Fill Fax Number ", Status.SCREENSHOT);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.websiteurl_enable,
				CRMNewAccountRecordPage.websiteurl_edit, WebsiteURL);
		report.updateTestLog("Account Entity", "Fill Website Url ", Status.SCREENSHOT);

		UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
		// driver.switchTo().defaultContent();
		Thread.sleep(5000);

		reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.SaveButtonClick);
		report.updateTestLog("Save", "Click the save entity", Status.SCREENSHOT);

		Thread.sleep(5000);
	}

	public void searchAccountRelatedRecordsAndSelect(String recordToSearch) throws InterruptedException {
		try {
			driver.switchTo().defaultContent();
			reusableFunc.navigationToEntity(driver.getWebDriver(), serviceModule, accountEntity);
			reusableFunc.waitForpageLoadByThread(driver.getWebDriver());
			driver.navigate().refresh();
			reusableFunc.waitForpageLoadByThread(driver.getWebDriver());
			
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), recordSearchBox);

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

				if (reusableFunc.waitForElementToBeVisible(driver.getWebDriver(), titleXpath, maxTimeOut)) {
					report.updateTestLog("Validating Account entity navigation",
							"Able to Navigate to Account entity and select the record.", Status.PASS);
				} else {
					report.updateTestLog("Validating Account entity navigation", "Not able to select the record.",
							Status.FAIL);
				}
				reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			}
		} catch (Exception Ex) {
			report.updateTestLog("Validating Account entity navigation", "Exception Occured: " + Ex.getMessage(),
					Status.FAIL);
		}
	}

	
	public void verifyBillAccountRecordsPresentForOrganization() {
		try {
			Random rand = new Random();
			String Customer = dataTable.getData("General_Data", "CustomerName_P");
			String Origin = dataTable.getData("General_Data", "Origin");
			String caseTitle = "CXDesktopCase_" + rand.nextInt(99999);
			
			// Create Case
			//createNewCase(Customer, Origin, caseTitle);
			caseClass.createNewCase();
			
			By billAccountXpath = By.xpath("//ul[@id='cxp_billaccountid_IMenu']/li[1]");

			caseClass.searchCaseRelatedRecordsAndSelect(caseTitle);

			// Validate Bill Account on Account Customer - Positive

			reusableFunc.switchToObjectFrame(driver.getWebDriver(), CRMCasePage.billAccount_enable);

			reusableFunc.lookupRecordValidationBillAccount(driver.getWebDriver(), CRMCasePage.billAccount_enable,
					CRMCasePage.billAccountLookupIcon, billAccountXpath, false);

			reusableFunc.switchToDefaultFrame(driver.getWebDriver());

		} catch (Exception Ex) {
			report.updateTestLog("Validating Bill Accounts Associated with Accounts",
					"Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}
	}

	public void verifyBillAccountRecordsPresentForIndividuals() {
		try {
			Random rand = new Random();
			String Customer = dataTable.getData("General_Data", "ContactName");
			String Origin = dataTable.getData("General_Data", "Origin");
			String caseTitle = "CXDesktopCase_" + rand.nextInt(99999);

			caseClass.createNewCase();
			
			By billAccountXpath = By.xpath("//ul[@id='cxp_billaccountid_IMenu']/li[1]");

			caseClass.searchCaseRelatedRecordsAndSelect(caseTitle);

			// Validate Bill Account on Account Customer - Positive

			reusableFunc.switchToObjectFrame(driver.getWebDriver(), CRMCasePage.billAccount_enable);

			reusableFunc.lookupRecordValidationBillAccount(driver.getWebDriver(), CRMCasePage.billAccount_enable,
					CRMCasePage.billAccountLookupIcon, billAccountXpath, false);

			reusableFunc.switchToDefaultFrame(driver.getWebDriver());

		} catch (Exception Ex) {
			report.updateTestLog("Validating Bill Accounts Associated with Accounts",
					"Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}
	}

	public void verifyPolicyNumberRecordsPresentForOrganization() {
		try {
			Random rand = new Random();
			String Customer = dataTable.getData("General_Data", "CustomerName_P");
			String Origin = dataTable.getData("General_Data", "Origin");
			String caseTitle = "CXDesktopCase_" + rand.nextInt(99999);

			caseClass.createNewCase();

			By policyXpath = By.xpath("//ul[@id='cxp_policyid_IMenu']/li[1]");

			caseClass.searchCaseRelatedRecordsAndSelect(caseTitle);

			// Validate Bill Account on Account Customer - Positive

			reusableFunc.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Iframe1);

			reusableFunc.lookupRecordValidationPolicyNumber(driver.getWebDriver(), CRMCasePage.policy_enable,
					CRMCasePage.policyLookupIcon, policyXpath, false);

			reusableFunc.switchToDefaultFrame(driver.getWebDriver());

		} catch (Exception Ex) {
			report.updateTestLog("Validating Policy Number Associated with Accounts",
					"Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}
	}

	public void verifyPolicyNumberRecordsPresentForIndividuals() {
		try {
			Random rand = new Random();
			String Customer = dataTable.getData("General_Data", "ContactName");
			String Origin = dataTable.getData("General_Data", "Origin");
			String caseTitle = "CXDesktopCase_" + rand.nextInt(99999);

			caseClass.createNewCase();

			By policyXpath = By.xpath("//ul[@id='cxp_policyid_IMenu']/li[1]");

			caseClass.searchCaseRelatedRecordsAndSelect(caseTitle);

			// Validate Bill Account on Account Customer - Positive

			reusableFunc.switchToObjectFrame(driver.getWebDriver(), CRMCasePage.policy_enable);

			reusableFunc.lookupRecordValidationPolicyNumber(driver.getWebDriver(), CRMCasePage.policy_enable,
					CRMCasePage.policyLookupIcon, policyXpath, false);

			reusableFunc.switchToDefaultFrame(driver.getWebDriver());

		} catch (Exception Ex) {
			report.updateTestLog("Validating Policy Number Associated with Accounts",
					"Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}
	}

	public void autoFormattingOfManuallyEnteredNumberAccount() {
		try {
			String nonFormattedNumber = dataTable.getData("General_Data", "Non_Formatted");
			String formattedNumber = dataTable.getData("General_Data", "Formatted");
			String contactName = dataTable.getData("General_Data", "CustomerName_P");

			searchAccountRelatedRecordsAndSelect(contactName);

			// Mobile Phone
			contactClass.enterPhoneNumber(CRMNewAccountRecordPage.telephone2_enable,
					CRMNewAccountRecordPage.telephone2_edit, nonFormattedNumber);

			// Business Phone
			contactClass.enterPhoneNumber(CRMNewAccountRecordPage.telephone1_enable,
					CRMNewAccountRecordPage.telephone1_edit, nonFormattedNumber);

			reusableFunc.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Iframe1);

			String homePhone = reusableFunc.getTextFromElement(driver.getWebDriver(),
					CRMNewAccountRecordPage.telephone2_text);
			String businessPhone = reusableFunc.getTextFromElement(driver.getWebDriver(),
					CRMNewAccountRecordPage.telephone1_text);

			if (formattedNumber.equalsIgnoreCase(homePhone) && formattedNumber.equalsIgnoreCase(businessPhone)) {
				report.updateTestLog("Validating Phone Number Formatting Associated with Accounts",
						"Phone number format matched !", Status.PASS);
			} else {
				report.updateTestLog("Validating Phone Number Formatting Associated with Accounts",
						"Phone number format doesn't match !", Status.FAIL);
			}

		} catch (Exception Ex) {
			report.updateTestLog("Validating Phone Number Formatting Associated with Accounts",
					"Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}
	}

	public void billAccountValidationForAccountCustomer_NoBillAccountsAssociated() {
		try {
			Random rand = new Random();
			String Customer = dataTable.getData("General_Data", "CustomerName_N");
			String Origin = dataTable.getData("General_Data", "Origin");
			String caseTitle = "CXDesktopCase_" + rand.nextInt(99999);
			String billAccount = dataTable.getData("General_Data", "BillAccount");

			caseClass.createNewCase();

			By billAccountXpath = By.xpath("//ul[@id='cxp_billaccountid_IMenu']/li[1]/a[@title='" + billAccount + "']");

			caseClass.searchCaseRelatedRecordsAndSelect(caseTitle);

			// Validate Bill Account on Account Customer - Positive

			reusableFunc.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Iframe1);

			reusableFunc.lookupRecordValidationBillAccount(driver.getWebDriver(), CRMCasePage.billAccount_enable,
					CRMCasePage.billAccountLookupIcon, billAccountXpath, true);

			reusableFunc.switchToDefaultFrame(driver.getWebDriver());

		} catch (Exception Ex) {
			report.updateTestLog("Validating Bill Accounts Associated with Accounts",
					"Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}
	}

	public void policyNumberValidationForAccountCustomer_NoPolicyNumberAssociated() {
		try {
			Random rand = new Random();
			String Customer = dataTable.getData("General_Data", "CustomerName_N");
			String Origin = dataTable.getData("General_Data", "Origin");
			String caseTitle = "CXDesktopCase_" + rand.nextInt(99999);
			String policyNumber = dataTable.getData("General_Data", "PolicyNumber");

			caseClass.createNewCase();

			By policyXpath = By.xpath("//ul[@id='cxp_policyid_IMenu']/li[1]/a[@title='" + policyNumber + "']");

			caseClass.searchCaseRelatedRecordsAndSelect(caseTitle);

			// Validate Bill Account on Account Customer - Positive

			reusableFunc.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Iframe1);

			reusableFunc.lookupRecordValidationPolicyNumber(driver.getWebDriver(), CRMCasePage.policy_enable,
					CRMCasePage.policyLookupIcon, policyXpath, true);

			reusableFunc.switchToDefaultFrame(driver.getWebDriver());

		} catch (Exception Ex) {
			report.updateTestLog("Validating Policy Number Associated with Accounts",
					"Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}
	}

	// New Development

	public void searchSpecialInstructions(String specialInstruction) throws InterruptedException {
		try {
			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());

			reusableFunc.switchToObjectFrame(driver.getWebDriver(), CRMNewAccountRecordPage.specialInstructionRecordSearch);

			// driver.switchTo().defaultContent();
			// UI_Helpers_OOB.switchToFrame(driver.getWebDriver(),
			// CRMLandingPage.IframespecialinstExpan0);

			if (UI_Helpers_OOB.waitForElementToBeClickableBool(driver.getWebDriver(),
					CRMNewAccountRecordPage.specialInstructionRecordSearch, maxTimeOut)) {
				WebElement element = driver.findElement(CRMNewAccountRecordPage.specialInstructionRecordSearch);
				element.click();
				element.sendKeys(specialInstruction);
				element.sendKeys(Keys.ENTER);

				By recordXpath = By.xpath("//div[contains(@id,'cxp_specialinstruction')]//following::a[@title='"
						+ specialInstruction + "']");
				reusableFunc.switchToObjectFrame(driver.getWebDriver(), recordXpath);

				if (UI_Helpers_OOB.waitForElementToBeVisible(driver.getWebDriver(), recordXpath, maxTimeOut)) {
					report.updateTestLog("Validating Search Special Instructions",
							"Able to search Special Instructions record.", Status.PASS);
				} else {
					report.updateTestLog("Validating Search Special Instructions",
							"Not able to search Special Instructions record.", Status.FAIL);
				}
				UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
			}
		} catch (Exception Ex) {
			report.updateTestLog("Validating Search Special Instructions", "Exception Occured: " + Ex.getMessage(),
					Status.FAIL);
		}

	}

	public void verifySpecialInstructionGrid() {
		try {

			String OrgName = dataTable.getData("General_Data", "OrganizationName");

			searchAccountRelatedRecordsAndSelect(OrgName);
			try {
				if (driver.findElement((CRMNewAccountRecordPage.TitleSpecialInsturction)).isDisplayed()) {
					report.updateTestLog("Validating Title column on Special Instructions",
							"Title is not present on Special Instruction Subgrid", Status.FAIL);
				}
			} catch (Exception e) {
				report.updateTestLog("Validating Title column on Special Instructions",
						"Title is present on Special Instruction Subgrid", Status.PASS);
			}
			try {
				if (driver.findElement((CRMNewAccountRecordPage.DescriptionSpecialInsturction)).isDisplayed()) {
					report.updateTestLog("Validating Description column on Special Instructions",
							"Description is not present on Special Instruction Subgrid", Status.FAIL);
				}
			} catch (Exception e) {
				report.updateTestLog("Validating Description column on Special Instructions",
						"Description is present on Special Instruction Subgrid", Status.PASS);
			}
			try {
				if (driver.findElement((CRMNewAccountRecordPage.CreatedBySpInstruction)).isDisplayed()) {
					report.updateTestLog("Validating Created By column on Special Instructions",
							"Created By is not present on Special Instruction Subgrid", Status.FAIL);
				}
			} catch (Exception e) {
				report.updateTestLog("Validating Created By column on Special Instructions",
						"Created By is  present on Special Instruction Subgrid", Status.PASS);
			}
			try {
				if (driver.findElement((CRMNewAccountRecordPage.CreatedBySpInstruction)).isDisplayed()) {
					report.updateTestLog("Validating Created On column on Special Instructions",
							"Created On is not present on Special Instruction Subgrid", Status.FAIL);
				}
			} catch (Exception e) {
				report.updateTestLog("Validating Created On column on Special Instructions",
						"Created On is present on Special Instruction Subgrid", Status.PASS);
			}

		} catch (Exception Ex) {
			report.updateTestLog("Validating Phone Number Formatting Associated with Accounts",
					"Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}
	}

	public String addSpecialInstruction() {
		String SpecialInstructionTitle = null;
		try {
			Random rand = new Random();
			String title = dataTable.getData("General_Data", "SpecialInstructionTitle");
			SpecialInstructionTitle = title + "" + rand.nextInt(99999);
			String SpecialInstructionDesc = dataTable.getData("General_Data", "SpecialInstructionDesc");

			reusableFunc.switchToObjectFrame(driver.getWebDriver(), By.xpath(CRMNewAccountRecordPage.AddSpecialInstructionimg));
			reusableFunc.MouseClickAction_Stale(driver.getWebDriver(),
					By.xpath(CRMNewAccountRecordPage.AddSpecialInstructionimg));

			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());

			reusableFunc.switchToObjectFrame(driver.getWebDriver(), CRMNewAccountRecordPage.Title_enable);

			reusableFunc.enterTextAction_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.Title_enable,
					CRMNewAccountRecordPage.Title_edit, SpecialInstructionTitle);
			reusableFunc.enterTextAction_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.Desc_enable,
					CRMNewAccountRecordPage.Desc_edit, SpecialInstructionDesc);

			reusableFunc.switchToObjectFrame(driver.getWebDriver(), CRMNewAccountRecordPage.SaveButton);
			reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.SaveButton);

			// Special Instruction Validation

			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());

			By recordXpath = By.xpath(
					"//div[contains(@id,'divDataArea')]//following::a[@title='" + SpecialInstructionTitle + "']");
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), recordXpath);

			if (driver.findElement(recordXpath).isDisplayed()) {
				report.updateTestLog("Validating Search Special Instructions - Before Deletion",
						"Able to search Special Instructions.", Status.PASS);
			} else {
				report.updateTestLog("Validating Search Special Instructions - Before Deletion",
						"Not able to search Special Instructions record.", Status.FAIL);
			}

		} catch (Exception Ex) {
			report.updateTestLog("Adding Special Instructions", "Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}
		return SpecialInstructionTitle;
	}

	public void addRemoveSpecialInstructions() {
		try {

			// Add Special Instructions
			String SpecialInstructionTitle = addSpecialInstruction();

			// Associated View - Special Instruction
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), By.xpath(CRMNewAccountRecordPage.AssociatedSpecialInstructionimg));
			reusableFunc.MouseClickAction_Stale(driver.getWebDriver(),
					By.xpath(CRMNewAccountRecordPage.AssociatedSpecialInstructionimg));

			// Search Special Instruction
			// searchSpecialInstructions(SpecialInstructionTitle);

			// Select Special Instruction - Global Check Box
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), CRMNewAccountRecordPage.checkBoxPath);

			// Nested Frames

			reusableFunc.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Iframe1);
			driver.switchTo().frame(CRMLandingPage.IframespecialinstExpan);

			reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.checkBoxPath);

			// Remove Special Instruction
			reusableFunc.MouseClickAction_Stale(driver.getWebDriver(),
					By.xpath(CRMNewAccountRecordPage.DeleteSpecialInstructionimg));

			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());

			reusableFunc.switchToObjectFrame(driver.getWebDriver(), By.xpath(CRMNewAccountRecordPage.Delete1SpecialInstructionimg));

			reusableFunc.MouseClickAction_Stale(driver.getWebDriver(),
					By.xpath(CRMNewAccountRecordPage.Delete1SpecialInstructionimg));

			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			reusableFunc.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Iframe1);
			driver.switchTo().frame(CRMLandingPage.IframespecialinstExpan);

			// Special Instruction Validation

			By noSpecialInstruct = By.xpath(
					"//div[contains(@id,'cxp_specialinstruction')]//following::td[contains(text(),'No Special Instruction records found.')]");

			if (UI_Helpers_OOB.waitForElementToBeVisible(driver.getWebDriver(), noSpecialInstruct, maxTimeOut)) {
				report.updateTestLog("Validating Search Special Instructions - After Deletion",
						"Not able to search Special Instructions record.", Status.PASS);
			} else {
				report.updateTestLog("Validating Search Special Instructions - After Deletion",
						"Able to search Special Instructions even after deleting the same.", Status.FAIL);
			}

		} catch (Exception Ex) {
			report.updateTestLog("Adding and Removing Special Instructions", "Exception Occured: " + Ex.getMessage(),
					Status.FAIL);
		}

	}

	public void addNotesToEntities(String notesTitle, String notesDescription){
		try {			
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), notesTab);
			reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), notesTab);
			
			//reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), notesDescriptionField);
			reusableFunc.EnterTextAction_Stale(driver.getWebDriver(), notesDescriptionField, notesDescription);
			
			reusableFunc.EnterTextAction_Stale(driver.getWebDriver(), notesTitleField, notesTitle);
			
			reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), doneButton);
			
			if (reusableFunc.waitForElementToBePresentBool(driver.getWebDriver(), notesCreationVerification, maxTimeOut)) {
					report.updateTestLog("Create New Note", "Note Created Successfully: " + notesTitle, Status.PASS);
			} else {
				report.updateTestLog("Create New Note", "Note is not created !", Status.FAIL);
			}

		} catch (Exception Ex) {
			report.updateTestLog("Create New Note", "Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}
	}

	public void createNotesForCaseEntity(){
		try{
			Random rand = new Random();
			
			String notesTitle = dataTable.getData("General_Data", "NotesTitle");
			String notesDescription = dataTable.getData("General_Data", "NotesDescription");
			/*String Customer = dataTable.getData("General_Data", "CustomerName");
			String Origin = dataTable.getData("General_Data", "Origin");
			String caseTitle = dataTable.getData("General_Data", "CaseTitle");*/
			
			//caseTitle = caseTitle+"_"+rand.nextInt(99999);
			notesTitle = notesTitle+"_"+rand.nextInt(99999);
			
			// Create New Case
			//createNewCase();
			
			// Add Notes to Case
			addNotesToEntities(notesTitle, notesDescription);
			
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			
		} catch (Exception Ex) {
			report.updateTestLog("Create Notes For Case Entity", "Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}
	}
	public void verifyPaysyncAccountManager()
	{
		try{
			
			
			String Organization = dataTable.getData("General_Data", "Organization");
			//String PaysyncAccManager = dataTable.getData("General_Data", "PaysyncAccManager");
			String Dept = dataTable.getData("General_Data", "Dept");
			
			searchAccountRelatedRecordsAndSelect(Organization);
			By titleXpath = By.xpath("//h1[@title='" + Organization + "']");
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), titleXpath);
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), CRMNewAccountRecordPage.paysyncAccountManager);
			
			if(Dept.equalsIgnoreCase("PaySync")){
				
				try{
					if((driver.findElement(CRMNewAccountRecordPage.paysyncAccountManager).isDisplayed()))
							{
						report.updateTestLog("Validating paySync Account Manager Field", "PaySyanc Account Manager field is visible to Paysync Dept", Status.PASS);
							}
				}
			catch(Exception e) {
				report.updateTestLog("Validating paySync Account Manager Field", "PaySyanc Account Manager field is not visible to Paysync Dept", Status.FAIL);	
				
			}
				
				try{
					if((driver.findElement(CRMNewAccountRecordPage.paysyncAccountManagerlock).isDisplayed()))
							{
						report.updateTestLog("Validating paySync Account Manager Field", "PaySyanc Account Manager field is Editable to Paysync Dept", Status.PASS);
							}
				}
			catch(Exception e) {
				report.updateTestLog("Validating paySync Account Manager Field", "PaySyanc Account Manager field is not ediatble to Paysync Dept", Status.FAIL);	
				
			}
			}else
			{
				try{
					if((driver.findElement(CRMNewAccountRecordPage.paysyncAccountManager).isDisplayed()))
							{
						report.updateTestLog("Validating paySync Account Manager Field", "PaySyanc Account Manager field is visible to other Dept", Status.PASS);
							}
				}
			catch(Exception e) {
				report.updateTestLog("Validating paySync Account Manager Field", "PaySyanc Account Manager field is not visible to other Dept", Status.FAIL);	
				
			}
				
				try{
					if((driver.findElement(CRMNewAccountRecordPage.paysyncAccountManagerlock).isDisplayed()))
							{
						report.updateTestLog("Validating paySync Account Manager Field", "PaySyanc Account Manager field is not editable to Paysync Dept", Status.PASS);
							}
				}
			catch(Exception e) {
				report.updateTestLog("Validating paySync Account Manager Field", "PaySyanc Account Manager field is ediatble to Paysync Dept", Status.FAIL);	
				
			}
			}
		}catch (Exception Ex) {
			report.updateTestLog("PaySync Account Manager", "Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}
	}
	
}
