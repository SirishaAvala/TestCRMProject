package businessComponents;

import pageObjects.*;
import com.cognizant.Craft.*;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import com.cognizant.framework.Status;



/**
 * Class for storing general purpose business components
 * 
 * @author Cognizant
 */
public class Activity_MSCRM extends ReusableLibrary {
	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	WebElement element = null;

	public Activity_MSCRM(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	reusableFunctions reusableFunc = new reusableFunctions(scriptHelper);
	
	/**
	 * navigateToTaskFromActivity This method is used to navigate to new task
	 * from Activity Tab
	 * 
	 * @throws Exception
	 */
	public void navigateToTaskFromActivity() throws Exception {
		reusableFunc.navigationToEntity(driver.getWebDriver(), CRMNewAccountRecordPage.salesModule, CRMLandingPage.activityEntity);
		Thread.sleep(7000);
		report.updateTestLog("Activity Entity", "Click on Activity Tab", Status.SCREENSHOT);
		reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMActivityPage.activitytaskbtn);
		report.updateTestLog("Activity Entity", "Click on new task button", Status.SCREENSHOT);
		Thread.sleep(5000);
	}

	/**
	 * navigateToMailFromActivity This method is used to navigate to new mail
	 * from Activity Tab
	 * 
	 * @throws Exception
	 */
	public void navigateToMailFromActivity() throws Exception {
		reusableFunc.navigationToEntity(driver.getWebDriver(), CRMNewAccountRecordPage.salesModule, CRMLandingPage.activityEntity);
		Thread.sleep(7000);
		report.updateTestLog("Activity Entity", "Click on Activity Tab", Status.SCREENSHOT);
		reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMActivityPage.activityemailbtn);
		report.updateTestLog("Activity Entity", "Click on new email button", Status.SCREENSHOT);
		Thread.sleep(5000);
	}

	/**
	 * navigateToAppontmentFromActivity This method is used to navigate to new
	 * Appointment from Activity Tab
	 * 
	 * @throws Exception
	 */
	public void navigateToAppoinementFromActivity() throws Exception {
		reusableFunc.navigationToEntity(driver.getWebDriver(), CRMNewAccountRecordPage.salesModule, CRMLandingPage.activityEntity);
		Thread.sleep(7000);
		report.updateTestLog("Activity Entity", "Click on Activity Tab", Status.SCREENSHOT);
		reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMActivityPage.activityappointmentbtn);
		report.updateTestLog("Activity Entity", "Click on new task button", Status.SCREENSHOT);
		Thread.sleep(5000);
	}

	/**
	 * navigateToPhoneCallFromActivity This method is used to navigate to new
	 * task from Activity Tab
	 * 
	 * @throws Exception
	 */
	public void navigateToPhoneCallFromActivity() throws Exception {
		reusableFunc.navigationToEntity(driver.getWebDriver(), CRMNewAccountRecordPage.salesModule, CRMLandingPage.activityEntity);
		Thread.sleep(7000);
		report.updateTestLog("Activity Entity", "Click on Activity Tab", Status.SCREENSHOT);
		reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMActivityPage.activityphoneCalltbtn);
		report.updateTestLog("Activity Entity", "Click on new task button", Status.SCREENSHOT);
		Thread.sleep(5000);

	}

	/**
	 * navigateToServiceActivityFromActivity This method is used to navigate to
	 * new task from Activity Tab
	 * 
	 * @throws Exception
	 */
	public void navigateToServiceActivityFromActivity() throws Exception {

		String Parent_Window = driver.getWindowHandle();
		reusableFunc.navigationToEntity(driver.getWebDriver(), CRMNewAccountRecordPage.salesModule, CRMLandingPage.activityEntity);
		Thread.sleep(7000);
		report.updateTestLog("Activity Entity", "Click on Activity Tab", Status.SCREENSHOT);
		UI_Helpers_OOB.closeAllOtherWindows(driver.getWebDriver());
		Thread.sleep(2000);
		reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMActivityPage.activityServiceActivitytbtn);
		report.updateTestLog("Activity Entity", "Click on new Service Activity button", Status.SCREENSHOT);
		Thread.sleep(5000);

	}

	/**
	 * create_TaskFromActivity This method is used to enter task details to
	 * create new task
	 * 
	 * @throws Exception
	 */
	public void create_TaskFromActivity() throws Exception {

		String Subject = dataTable.getData("General_Data", "Subject");
		UI_Helpers_OOB.implicitWait(driver.getWebDriver(), 2);
		UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.frame1);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMActivityPage.subjectNewtask_enable,
				CRMActivityPage.subjectNewtask_edit, Subject);
		Thread.sleep(5000);
		UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());

		report.updateTestLog("Activity Entity", "Enter Subject for new Task", Status.SCREENSHOT);

		reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMActivityPage.activitySave);
		report.updateTestLog("Save", "Click the save button to create new task", Status.SCREENSHOT);
		try {
			if (driver.findElement(CRMActivityPage.activityMarkomplete).isDisplayed()) {
				report.updateTestLog("Validating new task", "Record is saved successfully and new task is created",
						Status.PASS);
			}
		} catch (Exception e) {
			report.updateTestLog("Validating new task", "Record is not saved successfully and new task is not created",
					Status.FAIL);
		}

		Thread.sleep(5000);
	}

	/**
	 * create_NewMailFromActivity This method is used to enter Mail details to
	 * create new task
	 * 
	 * @throws Exception
	 */
	public void create_NewMailFromActivity() throws Exception {

		String To = dataTable.getData("General_Data", "To");
		String Cc = dataTable.getData("General_Data", "Cc");
		String Bcc = dataTable.getData("General_Data", "Bcc");
		String Subject = dataTable.getData("General_Data", "Subject");
		UI_Helpers_OOB.implicitWait(driver.getWebDriver(), 2);
		Thread.sleep(5000);

		UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.frame1);
		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMActivityPage.activityToimg_enable,
				CRMActivityPage.activityToimg_edit, To);

		report.updateTestLog("Activity Entity", "Enter To mail address for new Mail", Status.SCREENSHOT);

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

	/**
	 * create_AppointmentFromActivity This method is used to enter Appointment
	 * details to create new appointment
	 * 
	 * @throws Exception
	 */
	public void create_AppointmentFromActivity() throws Exception {

		String Subject = dataTable.getData("General_Data", "Subject");

		UI_Helpers_OOB.implicitWait(driver.getWebDriver(), 2);

		UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.frame1);
		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMActivityPage.subjectNewtask_enable,
				CRMActivityPage.subjectNewtask_edit, Subject);
		Thread.sleep(5000);
		UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());

		report.updateTestLog("Activity Entity for Appointment ", "Enter Subject for new Appointment",
				Status.SCREENSHOT);

		reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMActivityPage.activitySave);
		report.updateTestLog("Save", "Click the save button to create new appointment", Status.SCREENSHOT);
		try {
			if (driver.findElement(CRMActivityPage.activityMarkomplete).isDisplayed()) {
				report.updateTestLog("Validating new appointment",
						"Record is saved successfully and new appointment is created", Status.PASS);
			}
		} catch (Exception e) {
			report.updateTestLog("Validating new appoinemtent",
					"Record is not saved successfully and new appointment is not created", Status.FAIL);
		}

		Thread.sleep(5000);
	}

	/**
	 * create_NewPhoneCallFromActivity This method is used to enter Phone Call
	 * details to create new phone call
	 * 
	 * @throws Exception
	 */

	public void create_NewPhoneCallFromActivity() throws Exception {

		String To = dataTable.getData("General_Data", "To");

		String Subject = dataTable.getData("General_Data", "Subject");

		UI_Helpers_OOB.implicitWait(driver.getWebDriver(), 10);

		UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.frame1);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMActivityPage.activityToimg_enable,
				CRMActivityPage.activityToimg_edit, To);

		report.updateTestLog("Activity Entity", "Enter To mail address for new Mail", Status.SCREENSHOT);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMActivityPage.activitymailSubject_enable,
				CRMActivityPage.activitymailSubject_edit, Subject);

		report.updateTestLog("Activity Entity", "Enter Subject for new Mail", Status.SCREENSHOT);
		Thread.sleep(2000);

		UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());

		reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMActivityPage.activitySave);
		report.updateTestLog("Save", "Click the save button to create new task", Status.SCREENSHOT);
		try {
			if (driver.findElement(CRMActivityPage.activityMarkomplete).isDisplayed()) {
				report.updateTestLog("Validating new appointment",
						"Record is saved successfully and new appointment is created", Status.PASS);
			}
		} catch (Exception e) {
			report.updateTestLog("Validating new appoinemtent",
					"Record is not saved successfully and new appointment is not created", Status.FAIL);
		}
		Thread.sleep(5000);
	}

	/**
	 * create_NewServiceActivityFromActivity This method is used to enter
	 * Service Activity details to create new Service Activity
	 * 
	 * @throws Exception
	 */
	public void create_NewServiceActivityFromActivity() throws Exception {

		String Subject = dataTable.getData("General_Data", "Subject");
		String Service = dataTable.getData("General_Data", "Service");
		String StartTime = dataTable.getData("General_Data", "StartTime");
		String EndTime = dataTable.getData("General_Data", "EndTime");
		UI_Helpers_OOB.implicitWait(driver.getWebDriver(), 2);
		Thread.sleep(5000);

		UI_Helpers_OOB.switchWindow(driver.getWebDriver(),
				"Service Activity: New Service Activity - Microsoft Dynamics 365");
		driver.manage().window().maximize();
		Thread.sleep(2000);
		UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.frame);
		Thread.sleep(2000);

		driver.findElement(CRMActivityPage.serviceActivitySubject).sendKeys(Subject);
		report.updateTestLog("Craetion of New Srvice Activity", "Enter Subject for new service activity",
				Status.SCREENSHOT);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMActivityPage.servicetxtboxOnServiceActivity_enable,
				CRMActivityPage.servicetxtboxOnServiceActivity_edit, Service);
		report.updateTestLog("Craetion of New Srvice Activity", "Enter service for new service activity",
				Status.SCREENSHOT);
		WebElement start = driver.findElement(CRMActivityPage.scheduleStartTimeServiceActivity);
		driver.findElement(CRMActivityPage.scheduleStartTimeServiceActivity).clear();
		((JavascriptExecutor) driver.getWebDriver()).executeScript("arguments[0].value='" + StartTime + "'", start);

		WebElement end = driver.findElement(CRMActivityPage.scheduleEndTimeServiceActivity);
		driver.findElement(CRMActivityPage.scheduleEndTimeServiceActivity).clear();
		((JavascriptExecutor) driver.getWebDriver()).executeScript("arguments[0].value='" + EndTime + "'", end);
		Thread.sleep(2000);
		report.updateTestLog("Craetion of New Srvice Activity", "Enter Start and End  Date for new service activity",
				Status.SCREENSHOT);

		UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());

		reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMActivityPage.saveServiceActivitybtn);

		report.updateTestLog("Craetion of New Srvice Activity", "Click save button", Status.SCREENSHOT);
	}
}