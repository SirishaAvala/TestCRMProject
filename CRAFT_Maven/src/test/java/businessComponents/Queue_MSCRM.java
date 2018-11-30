package businessComponents;

import pageObjects.*;
import com.cognizant.Craft.*;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.cognizant.framework.Status;


public class Queue_MSCRM extends ReusableLibrary {

	public Queue_MSCRM(ScriptHelper scriptHelper) {
		super(scriptHelper);
		// TODO Auto-generated constructor stub
	}

	// Page Objects
	int maxTimeOut = 60;
	int minTimeOut = 10;
	reusableFunctions reusableFunc = new reusableFunctions(scriptHelper);
	Case_MSCRM caseModule = new Case_MSCRM(scriptHelper);

	public static final By serviceModule = By.xpath("//img[@alt='Service']//parent::span");
	public static final By queueEntity = By.cssSelector("a#nav_queues");
	public static final By recordSearchBox = By.cssSelector("input#crmGrid_findCriteria");
	public static final By pickButton = By.xpath("//span[text()=' Pick ']");
	public static final By pickButtonDialog = By.xpath("//button[@id='ok_id']");
	public static final By errorMsgDialog = By.xpath("//span[@id='ErrorMessage']");
	public static final By errorOkButton = By.xpath("//button[@id='butBegin']");
	public static final By viewDetails = By.xpath("//a[@title='Select a view']");
	public static final By queueDropDown = By.xpath("//select[@id='crmQueueSelector']");
	public static final By removeButton = By.xpath(
			"//a[@class='ms-crm-Menu-Label']//preceding::img[@class='ms-crm-ImageStrip-RemoveFromQueue_16 ms-crm-commandbar-image16by16']");
	public static final By status = By.xpath("//label[@id='State_label']");

	public void navigateToQueueEntity() throws InterruptedException {
		driver.switchTo().defaultContent();
		reusableFunc.navigationToEntity(driver.getWebDriver(), serviceModule, queueEntity);
		try {
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), CRMCasePage.viewDropdown);
			if (reusableFunc.waitForElementToBePresent(driver.getWebDriver(), CRMCasePage.viewDropdown, maxTimeOut)) {
				report.updateTestLog("Validating Queue entity navigation", "Able to Navigate to Queue entity",
						Status.PASS);
			}
		} catch (Exception e) {
			report.updateTestLog("Validating Queue entity navigation", "Not able to Navigate to Queue entity",
					Status.FAIL);
		}

	}

	public void searchQueueRelatedRecordsAndSelect(String recordToSearch) throws InterruptedException {
		try {
			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
			driver.switchTo().defaultContent();
			reusableFunc.navigationToEntity(driver.getWebDriver(), serviceModule, queueEntity);
			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
			driver.navigate().refresh();
			
			UI_Helpers_OOB.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Iframe0);

			if (UI_Helpers_OOB.waitForElementToBeClickableBool(driver.getWebDriver(), recordSearchBox, maxTimeOut)) {
				WebElement element = driver.findElement(recordSearchBox);
				element.click();
				element.sendKeys(recordToSearch);
				element.sendKeys(Keys.ENTER);

				By recordXpath = By
						.xpath("//div[@id='crmGrid_divDataArea']//following::a[text()='" + recordToSearch + "']");

				UI_Helpers_OOB.waitForElementToBeClickableBool(driver.getWebDriver(), recordXpath, maxTimeOut);
				driver.findElement(recordXpath).click();

				By titleXpath = By.xpath("//h1[@title='" + recordToSearch + "']");
				UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.baseFrame1);

				if (UI_Helpers_OOB.waitForElementToBeVisible(driver.getWebDriver(), titleXpath, maxTimeOut)) {
					report.updateTestLog("Validating Queue entity navigation",
							"Able to Navigate to Queue entity and select the record.", Status.PASS);
				} else {
					report.updateTestLog("Validating Queue entity navigation", "Not able to select the record.",
							Status.FAIL);
				}
				UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
			}
		} catch (Exception Ex) {
			report.updateTestLog("Validating Queue entity navigation", "Exception Occured: " + Ex.getMessage(),
					Status.FAIL);
		}

	}

	public void searchQueueRelatedRecords(String recordToSearch) throws InterruptedException {
		try {
			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
			driver.switchTo().defaultContent();
			reusableFunc.navigationToEntity(driver.getWebDriver(), serviceModule, queueEntity);
			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
			driver.navigate().refresh();

			UI_Helpers_OOB.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Iframe0);

			if (UI_Helpers_OOB.waitForElementToBeClickableBool(driver.getWebDriver(), recordSearchBox, maxTimeOut)) {
				WebElement element = driver.findElement(recordSearchBox);
				element.click();
				element.sendKeys(recordToSearch);
				element.sendKeys(Keys.ENTER);

				By recordXpath = By
						.xpath("//div[@id='crmGrid_divDataArea']//following::a[text()='" + recordToSearch + "']");

				if (UI_Helpers_OOB.waitForElementToBeVisible(driver.getWebDriver(), recordXpath, maxTimeOut)) {
					report.updateTestLog("Validating Record Visibility !!", "Record is found !!", Status.PASS);
				} else {
					report.updateTestLog("Validating Record Visibility !!", "Record doesn't exist !!", Status.FAIL);
				}
			}
		} catch (Exception Ex) {
			report.updateTestLog("Validating Record Visibility !!", "Exception Occured: " + Ex.getMessage(),
					Status.FAIL);
		}

	}

	public void caseSelectedByMultipleUserErrorValidation() {
		try {
			String caseName = dataTable.getData("General_Data", "SearchRecord");
			String viewName = dataTable.getData("General_Data", "ViewName");
			String dropDownName = dataTable.getData("General_Data", "QueueName");
			String caseNameToSelect = dataTable.getData("General_Data", "CaseName1");

			//searchQueueRelatedRecords(recordToSearch);
			
			// Navigate to queue entity
			navigateToQueueEntity();

			// Select View
			selectViewFromQueueEntity(viewName);

			// Select Queue Dropdown
			reusableFunc.dropDownSelection(driver.getWebDriver(), queueDropDown, dropDownName);

			// Search for case
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), recordSearchBox);

			if (reusableFunc.waitForElementToBeClickableBool(driver.getWebDriver(), recordSearchBox, maxTimeOut)) {
				WebElement element = driver.findElement(recordSearchBox);
				element.click();
				element.sendKeys(caseName);
				element.sendKeys(Keys.ENTER);

				By recordXpath = By
						.xpath("//div[@id='crmGrid_divDataArea']//following::a[@title='" + caseNameToSelect + "']");

				if (reusableFunc.waitForElementToBeClickableBool(driver.getWebDriver(), recordXpath, maxTimeOut)) {
					report.updateTestLog("Validating Record Visibility !!", "Record is found !!", Status.PASS);
				} else {
					report.updateTestLog("Validating Record Visibility !!", "Record doesn't exist !!", Status.FAIL);
				}
			}
					
			By checkBox = By.xpath("//a[@class='ms-crm-List-Link' and @title='" + caseNameToSelect
					+ "' and @tabindex='0']//preceding::td[@title='Checkbox']");
			
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), checkBox);
			
			if (reusableFunc.waitForElementToBeVisible(driver.getWebDriver(), checkBox, maxTimeOut)) {

				driver.findElement(checkBox).click();

				reusableFunc.switchToDefaultFrame(driver.getWebDriver());
				reusableFunc.waitForElementToBeClickableBool(driver.getWebDriver(), pickButton, maxTimeOut);

				driver.findElement(pickButton).click();

				reusableFunc.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Inlineframe);
				reusableFunc.waitForElementToBeClickableBool(driver.getWebDriver(), pickButtonDialog, maxTimeOut);

				driver.findElement(pickButtonDialog).click();
				reusableFunc.switchToDefaultFrame(driver.getWebDriver());

				reusableFunc.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.InlineErrorframe);
				
				if (reusableFunc.waitForElementToBeClickableBool(driver.getWebDriver(), errorOkButton, maxTimeOut)) {
					report.updateTestLog("Validating Error Message !!", "Error Message Found.", Status.PASS);
					driver.findElement(errorOkButton).click();
				} else {
					report.updateTestLog("Validating Error Message !!", "Error Message not Found.", Status.FAIL);
				}
				
				reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			}

		} catch (Exception Ex) {
			Ex.printStackTrace();
		}
	}

	/**
	 * assign_Entity This method is used to assign owner to any entity
	 * 
	 * @throws InterruptedException
	 */
	public void assign_Entity() throws InterruptedException {
		driver.switchTo().defaultContent();
		String AssignTo = dataTable.getData("General_Data", "assignTo");
		String User_Team = dataTable.getData("General_Data", "user/Team");
		try {
			driver.findElement(CRMLandingPage.AssignButtonClick);
			Thread.sleep(5000);
		} catch (NoSuchElementException e) {
			reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMLandingPage.More);
			report.updateTestLog("Entity", "click More", Status.SCREENSHOT);
		}

		UI_Helpers_OOB.waitForVisibilityOfElement(driver.getWebDriver(), CRMLandingPage.AssignButtonClick, 100);
		reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMLandingPage.AssignButtonClick);
		Thread.sleep(5000);
		report.updateTestLog("Entity", "clicked on  Assign", Status.SCREENSHOT);
		UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
		UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.Iframe);

		// UI_Helpers_OOB.waitForVisibilityOfElement(driver.getWebDriver(),
		// CRMLandingPage.AssignToClick, 100);

		if (AssignTo.equalsIgnoreCase("Me")) {

			driver.findElement(CRMLandingPage.Assign).click();
			report.updateTestLog("Entity", "click Assignment submit", Status.SCREENSHOT);
			UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());

			Thread.sleep(6000);

		} else if (AssignTo.equalsIgnoreCase("user/Team")) {
			reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMLandingPage.AssignToClick);
			report.updateTestLog("Entity", "click Assign", Status.SCREENSHOT);

			UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMLandingPage.AssignToOtherEnable,
					CRMLandingPage.AssignToOtherEdit, User_Team);
			driver.findElement(CRMLandingPage.AssignToOtherEdit).sendKeys(Keys.TAB);
			report.updateTestLog("Entity", "Assign To Others", Status.SCREENSHOT);

			driver.findElement(CRMLandingPage.Assign).click();
			report.updateTestLog("Entity", "click Assignment submit", Status.SCREENSHOT);
			UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());

			Thread.sleep(2000);
		}

	}

	public void selectViewFromQueueEntity(String viewName) {
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

	public void selectItemFromQueueAndRemove(String queueItem) {
		try {
			// Switch To Frame
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			reusableFunc.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Iframe0);

			// Click on Checkbox
			 By checkBoxPath =
			 By.xpath("//a[text()='"+queueItem+"']//preceding::img[@title='Checkbox']");

			/*By checkBoxPath = By.xpath("//input[@id='chkAll']//following-sibling::img");
			reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), checkBoxPath);*/

			// Click on remove button
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			reusableFunc.MouseDoubleClickAction_Stale(driver.getWebDriver(), removeButton);

			// Switch to alert and accept it
			reusableFunc.switchToAlertAndPerformAction(driver.getWebDriver(), true);

		} catch (Exception Ex) {
			report.updateTestLog("Select View From Queue Entity", "Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}
	}

	/**
	 * @author sharma3 Pick first case in the queue and and remove that case
	 *         later verify that selected case is cancelled
	 */
	public void abilityToCancelCaseFromQueues() {
		try {
			String caseName = dataTable.getData("General_Data", "CaseName1");
			String viewName = dataTable.getData("General_Data", "ViewName");
			String dropDownName = dataTable.getData("General_Data", "QueueName");
			String caseNameToCancel = dataTable.getData("General_Data", "CaseName2");

			// Navigate to queue entity
			navigateToQueueEntity();

			// Select View
			selectViewFromQueueEntity(viewName);

			// Select Queue Dropdown
			reusableFunc.dropDownSelection(driver.getWebDriver(), queueDropDown, dropDownName);

			// Search for case
			UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
			UI_Helpers_OOB.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Iframe0);

			/*if (UI_Helpers_OOB.waitForElementToBeClickableBool(driver.getWebDriver(), recordSearchBox, maxTimeOut)) {
				WebElement element = driver.findElement(recordSearchBox);
				element.click();
				element.sendKeys(caseName);
				element.sendKeys(Keys.ENTER);

				By recordXpath = By
						.xpath("//div[@id='crmGrid_divDataArea']//following::a[@title='" + caseNameToCancel + "']");

				if (UI_Helpers_OOB.waitForElementToBeClickableBool(driver.getWebDriver(), recordXpath, maxTimeOut)) {
					report.updateTestLog("Validating Record Visibility !!", "Record is found !!", Status.PASS);
				} else {
					report.updateTestLog("Validating Record Visibility !!", "Record doesn't exist !!", Status.FAIL);
				}
			}*/

			// Select The Case And Remove
			selectItemFromQueueAndRemove(caseNameToCancel);

			// Verify Case Canceled
			caseModule.searchCaseRelatedRecordsAndSelect(caseNameToCancel);
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			UI_Helpers_OOB.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Iframe1);
			String caseStatus = driver.findElement(status).getText();

			if (caseStatus.contains("Canceled")) {
				report.updateTestLog("Ability To Cancel Case From Queues", "Case is canceled", Status.PASS);
			} else {
				report.updateTestLog("Ability To Cancel Case From Queues", "Case not canceled", Status.FAIL);
			}

		} catch (Exception Ex) {
			report.updateTestLog("Ability To Cancel Case From Queues", "Exception Occured: " + Ex.getMessage(),
					Status.FAIL);
		}
	}

	public void abilityToPickCaseFromQueuesandVerifyWorkedBy() {
		try {
			String caseNameToPick = dataTable.getData("General_Data", "CaseName1");
			String viewName = dataTable.getData("General_Data", "ViewName1");
			String dropDownName = dataTable.getData("General_Data", "QueueName1");
			String userName = dataTable.getData("General_Data", "UserName");
			String viewName1 = dataTable.getData("General_Data", "ViewName2");
			String caseNameToSearch = dataTable.getData("General_Data", "CaseName2");

			// Navigate to queue entity
			navigateToQueueEntity();

			// Select View
			selectViewFromQueueEntity(viewName);

			// Select Queue Dropdown
			reusableFunc.dropDownSelection(driver.getWebDriver(), queueDropDown, dropDownName);

			// Search for case
			//UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
			driver.switchTo().defaultContent();
			driver.switchTo().frame(CRMLandingPage.Iframe0);
			//UI_Helpers_OOB.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Iframe0);

			if (UI_Helpers_OOB.waitForElementToBeClickableBool(driver.getWebDriver(), recordSearchBox, maxTimeOut)) {
				WebElement element = driver.findElement(recordSearchBox);
				element.click();
				element.clear();
				element.click();
				element.sendKeys(caseNameToSearch);
				element.sendKeys(Keys.ENTER);

				By recordXpath = By
						.xpath("//div[@id='crmGrid_divDataArea']//following::a[@title='" + caseNameToPick + "']");
				reusableFunc.switchToObjectFrame(driver.getWebDriver(), recordXpath);
				if (UI_Helpers_OOB.waitForElementToBeClickableBool(driver.getWebDriver(), recordXpath, maxTimeOut)) {
					report.updateTestLog("Validating Record Visibility !!", "Record is found !!", Status.PASS);
				} else {
					report.updateTestLog("Validating Record Visibility !!", "Record doesn't exist !!", Status.FAIL);
				}
			}

			// Select The Case And Pick
			By checkBoxPath = By.xpath("//input[@id='chkAll']//following-sibling::img");
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), checkBoxPath);
			reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), checkBoxPath);

			// Click on pick button
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), pickButton);
			reusableFunc.MouseDoubleClickAction_Stale(driver.getWebDriver(), pickButton);

			// Switch to alert and accept it
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), pickButtonDialog);
			reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), pickButtonDialog);
			reusableFunc.waitForpageLoadByThread(driver.getWebDriver());

			driver.switchTo().defaultContent();
			reusableFunc.navigationToEntity(driver.getWebDriver(), serviceModule, Case_MSCRM.caseEntity);
			// Verify Case Assigned
			navigateToQueueEntity();

			// Select View
			selectViewFromQueueEntity(viewName1);

			// Select Queue Dropdown
			reusableFunc.dropDownSelection(driver.getWebDriver(), queueDropDown, dropDownName);

			// Search for case
			UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
			UI_Helpers_OOB.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Iframe0);

			if (UI_Helpers_OOB.waitForElementToBeClickableBool(driver.getWebDriver(), recordSearchBox, maxTimeOut)) {
				WebElement element = driver.findElement(recordSearchBox);
				element.click();
				element.sendKeys(caseNameToSearch);
				element.sendKeys(Keys.ENTER);

				By recordXpath = By
						.xpath("//div[@id='crmGrid_divDataArea']//following::a[@title='" + caseNameToPick + "']");
				reusableFunc.switchToObjectFrame(driver.getWebDriver(), recordXpath);
				
				if (UI_Helpers_OOB.waitForElementToBeClickableBool(driver.getWebDriver(), recordXpath, maxTimeOut)) {
					report.updateTestLog("Validating Record Visibility !!", "Record is found !!", Status.PASS);

					By workedByXpath = By
							.xpath("//table[@id='gridBodyTable']//following::tr[@class='ms-crm-List-Row']/td[7]");
					reusableFunc.switchToObjectFrame(driver.getWebDriver(), workedByXpath);
					String workedByText = driver.findElement(workedByXpath).getAttribute("rawlookupitemname");
					
					if (workedByText.equalsIgnoreCase(userName)) {
						report.updateTestLog("Ability To Pick Case From Queues",
								"Worked by haven't got changed: " , Status.FAIL);
					} else {
						report.updateTestLog("Ability To Pick Case From Queues", "Worked by got changed."+ workedByText,
								Status.PASS);
					}

				} else {
					report.updateTestLog("Validating Record Visibility !!", "Record doesn't exist !!", Status.FAIL);
				}
			}

		} catch (Exception Ex) {
			report.updateTestLog("Ability To Pick Case From Queues", "Exception Occured: " + Ex.getMessage(),
					Status.FAIL);
		}
	}

	
	
	
	
	public void abilityToPickCaseFromQueuesandVerifyStatus() {
		try {
			String caseNameToPick = dataTable.getData("General_Data", "CaseName1");
			String viewName = dataTable.getData("General_Data", "ViewName2");
			String dropDownName = dataTable.getData("General_Data", "QueueName2");
			String CaseStatus = dataTable.getData("General_Data", "CaseStatus");

			// Navigate to queue entity
			navigateToQueueEntity();

			// Select View
			selectViewFromQueueEntity(viewName);

			// Select Queue Dropdown
			reusableFunc.dropDownSelection(driver.getWebDriver(), queueDropDown, dropDownName);

			// Search for case
			UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
			UI_Helpers_OOB.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Iframe0);

			/*if (UI_Helpers_OOB.waitForElementToBeClickableBool(driver.getWebDriver(), recordSearchBox, maxTimeOut)) {
				WebElement element = driver.findElement(recordSearchBox);
				element.click();
				element.sendKeys(caseNameToPick);
				element.sendKeys(Keys.ENTER);*/

				By recordXpath = By
						.xpath("//div[@id='crmGrid_divDataArea']//following::a[@title='" + caseNameToPick + "']");
				reusableFunc.switchToObjectFrame(driver.getWebDriver(), recordXpath);
				if (UI_Helpers_OOB.waitForElementToBeClickableBool(driver.getWebDriver(), recordXpath, maxTimeOut)) {
					report.updateTestLog("Validating Record Visibility !!", "Record is found !!", Status.PASS);
				} else {
					report.updateTestLog("Validating Record Visibility !!", "Record doesn't exist !!", Status.FAIL);
				}
		//	}
					By CaseStatusxpath = By
							.xpath("//a[@title='"+caseNameToPick+"']//following::td[10]//span");
					reusableFunc.switchToObjectFrame(driver.getWebDriver(), CaseStatusxpath);
					//driver.findElement(By.xpath("//a[@title='"+caseNameToPick+"']")).click();
					String CaseStatusText = driver.findElement(CaseStatusxpath).getText();
					
					if (CaseStatusText.equalsIgnoreCase(CaseStatus)) {
						report.updateTestLog("Ability To Pick Case From Queues",
								"Case Status got changed: " + CaseStatusText, Status.PASS);
					} else {
						report.updateTestLog("Ability To Pick Case From Queues", "Case Status  got changed.",
								Status.FAIL);
					}

				
			

		} catch (Exception Ex) {
			report.updateTestLog("Ability To Pick Case From Queues", "Exception Occured: " + Ex.getMessage(),
					Status.FAIL);
		}
	}

	public void verifyCancelledReminderQueueView() {
		try {
			
			String viewName = dataTable.getData("General_Data", "ViewName1");
			String dropDownName = dataTable.getData("General_Data", "QueueName1");
			
			// Navigate to queue entity
			navigateToQueueEntity();
			

			// Select View
			selectViewFromQueueEntity(viewName);

			// Select Queue Dropdown
			reusableFunc.dropDownSelection(driver.getWebDriver(), queueDropDown, dropDownName);

			// Search for case
			UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
			UI_Helpers_OOB.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Iframe0);
			try{
			if (driver.findElement(dueDate).isDisplayed()) {
				report.updateTestLog("Validating Due Date under Cancelled Reminder View",
						"Due Date is present under Cancelled Reminder View ", Status.PASS);
			}
		} catch (Exception e) {
			report.updateTestLog("Validating Due Date under Cancelled Reminder View",
					"Due Date is not present under Cancelled Reminder View ", Status.FAIL);

		}
		try {
			if (driver.findElement(enteredQueue).isDisplayed()) {
				report.updateTestLog("Validating Entered Queue under Cancelled Reminder View",
						"Entered Queue is present under Cancelled Reminder View ", Status.PASS);
			}
		} catch (Exception e) {
			report.updateTestLog("Validating Entered Queue under Cancelled Reminder View",
					"Entered Queue is not  present under Cancelled Reminder View ", Status.FAIL);

		}
		try {
			if (driver.findElement(title).isDisplayed()) {
				report.updateTestLog("Validating Title under Cancelled Reminder View",
						"Title is present under Cancelled Reminder View ", Status.PASS);
			}
		} catch (Exception e) {
			report.updateTestLog("Validating Title under Cancelled Reminder View",
					"Title is not present under Cancelled Reminder View ", Status.FAIL);

		}
		try {
			if (driver.findElement(description).isDisplayed()) {
				report.updateTestLog("Validating Description under Cancelled Reminder View",
						"Description is present under Cancelled Reminder View ", Status.PASS);
			}
		} catch (Exception e) {
			report.updateTestLog("Validating Description under Cancelled Reminder View",
					"Description is not  present under Cancelled Reminder View ", Status.FAIL);

		}

		try {
			if (driver.findElement(customer).isDisplayed()) {
				report.updateTestLog("Validating Customer under Cancelled Reminder View",
						"Customer is present under Cancelled Reminder View ", Status.PASS);
			}
		} catch (Exception e) {
			report.updateTestLog("Validating Customer under Cancelled Reminder View",
					"Customer is not present under Cancelled Reminder View ", Status.FAIL);

		}
		try {
			if (driver.findElement(queue).isDisplayed()) {
				report.updateTestLog("Validating Queue under Cancelled Reminder View",
						"Queue is  present under Cancelled Reminder View ", Status.PASS);
			}
		} catch (Exception e) {
			report.updateTestLog("Validating Queue under Cancelled Reminder View",
					"Queue is not  present under Cancelled Reminder View ", Status.FAIL);

		}
		try {
			if (driver.findElement(owner).isDisplayed()) {
				report.updateTestLog("Validating Owner under Cancelled Reminder View",
						"Owner is present under Cancelled Reminder View ", Status.PASS);
			}
		} catch (Exception e) {
			report.updateTestLog("Validating Owner under Cancelled Reminder View",
					"Owner is not present under Cancelled Reminder View ", Status.FAIL);
		}
		try {
			if (driver.findElement(workedBy).isDisplayed()) {
				report.updateTestLog("Validating Due Date under Cancelled Reminder View",
						"Due Date is present under Cancelled Reminder View ", Status.PASS);
			}
		} catch (Exception e) {
			report.updateTestLog("Validating Worked By under Cancelled Reminder View",
					"Worked By is not present under Cancelled Reminder View ", Status.FAIL);

		}
		
		verifyUpcomingTaskReminderNA();

		} catch (Exception Ex) {
			report.updateTestLog("Ability To Pick Case From Queues", "Exception Occured: " + Ex.getMessage(),
					Status.FAIL);
		}
		
		
	}

	
	public void verifyCancelledReminderViewMenuOptions() {
		
		
		try {
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), exportToExcel);
			if (driver.findElement(exportToExcel).isDisplayed()) {
				report.updateTestLog("Validating Export To excel for Cancelled Reminder View",
						"Export To Excel is present for Cancelled Reminder View ", Status.PASS);
			}
		} catch (Exception e) {
			report.updateTestLog("Validating Export To excel for Cancelled Reminder View",
					"Export To Excel is not present for Cancelled Reminder View ", Status.FAIL);

		}
		try {
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), setAsDefaultView);
			if (driver.findElement(setAsDefaultView).isDisplayed()) {
				report.updateTestLog("Validating Set As Default View for Cancelled Reminder View",
						"Set As Default View  is present for Cancelled Reminder View ", Status.PASS);
			}
		} catch (Exception e) {
			report.updateTestLog("Validating Set As Default View for  Cancelled Reminder View",
					"Set As Default View  is not  present for Cancelled Reminder View ", Status.FAIL);

		}
	
	}
	
	public void verifyUpcomingTaskReminderNA(){
		try {
			// Switch To Frame
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), CRMCasePage.viewDropdown);

			// Click on view Dropdown button
			reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMCasePage.viewDropdown);

             try{
			if (driver.findElement(By.xpath("//span[@title='Upcoming Task Reminder']")).isDisplayed()) 
			{
				report.updateTestLog("Verfiy Upcoming Task Reminder View", "Upcoming Task Reminder view is available", Status.FAIL);
			} 
               }catch(Exception e) {
				report.updateTestLog("Verfiy Upcoming Task Reminder View", "Upcoming Task Reminder view is not available", Status.PASS);
			}

		} catch (Exception Ex) {
			report.updateTestLog("Verfiy Upcoming Task Reminder View", "Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}
	}
	
	
	
	public void verfiycompletedReminderStatus() {
		try {
			
			String viewName = dataTable.getData("General_Data", "ViewName");
			String dropDownName = dataTable.getData("General_Data", "QueueName");
			String CaseName = dataTable.getData("General_Data", "recordToSearch");
			
			selectQueueRelatedRecord(CaseName,viewName);

			reusableFunc.dropDownSelection(driver.getWebDriver(), queueDropDown, dropDownName);
			
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), CaseStatus1);
			
			By CaseStatusxpath = By
					.xpath("//label[@id='Activity Status_label']");
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), CaseStatusxpath);
			
			String CaseStatusText = driver.findElement(CaseStatusxpath).getText();
			
			if (CaseStatusText.contains("Completed")) {
				report.updateTestLog("Verify Completed Reminder View Case Status ",
						"Case Status from Completed Reminder Queue is " + CaseStatusText, Status.PASS);
			} else {
				report.updateTestLog("Verify Completed Reminder View Case Status", "Case Status  not set as Completed under Completed Reminder Queue.",
						Status.FAIL);
			}
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), recentlyViewedImage);
			/*driver.findElement(recentlyViewedImage).click();
			Thread.sleep(1000);
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), completedReminderRecentlyView);
			driver.findElement(completedReminderRecentlyView).click();
			Thread.sleep(1000);*/
			
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
		} catch (Exception Ex) {
			report.updateTestLog("Verify Completed Reminder View Case Status", "Exception Occured: " + Ex.getMessage(),
					Status.FAIL);
		}
		
		
	}
	
	public void verfiyCancelledReminderStatus() {
		try {
			
			String viewName = dataTable.getData("General_Data", "ViewName1");
		//	String dropDownName = dataTable.getData("General_Data", "QueueName1");
			String CaseName = dataTable.getData("General_Data", "CaseName1");
			
			
			// Select Queue Dropdown
			
			selectQueueRelatedRecord(CaseName,viewName);

			//reusableFunc.dropDownSelection(driver.getWebDriver(), queueDropDown, dropDownName);
			
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), CaseStatus1);
			
			By CaseStatusxpath = By
					.xpath("//label[@id='Activity Status_label']");
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), CaseStatusxpath);
			
			String CaseStatusText = driver.findElement(CaseStatusxpath).getText();
			System.out.println("Status:"+CaseStatusText);
			
			if (CaseStatusText.contains("Canceled")) {
				report.updateTestLog("Verify Cancelled Reminder View Case Status ",
						"Case Status from Cancelled Reminder Queue is " + CaseStatusText, Status.PASS);
			} else {
				report.updateTestLog("Verify Cancelled Reminder View Case Status", "Case Status  not set as Canceled under Cancelled Reminder Queue.",
						Status.FAIL);
			}


		} catch (Exception Ex) {
			report.updateTestLog("Verify Cancelled Reminder View Case Status", "Exception Occured: " + Ex.getMessage(),
					Status.FAIL);
		}
		
		
	}
	
	public void selectQueueRelatedRecord(String recordToSearch,String viewName) throws InterruptedException {
		try {
			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
			driver.switchTo().defaultContent();
			reusableFunc.navigationToEntity(driver.getWebDriver(), serviceModule, queueEntity);
			
			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
			driver.navigate().refresh();
			selectViewFromQueueEntity(viewName);
			UI_Helpers_OOB.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Iframe0);

			if (UI_Helpers_OOB.waitForElementToBeClickableBool(driver.getWebDriver(), recordSearchBox, maxTimeOut)) {
				
				By recordXpath = By
						.xpath("//div[@id='crmGrid_divDataArea']//following::a[text()='" + recordToSearch + "']");

				UI_Helpers_OOB.waitForElementToBeClickableBool(driver.getWebDriver(), recordXpath, maxTimeOut);
				
				reusableFunc.dropDownSelection(driver.getWebDriver(), queueDropDown, "All Queues");
				Thread.sleep(1000);

				driver.findElement(recordXpath).click();

				By titleXpath = By.xpath("//h1[@title='" + recordToSearch + "']");
				UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.baseFrame1);

				if (UI_Helpers_OOB.waitForElementToBeVisible(driver.getWebDriver(), titleXpath, maxTimeOut)) {
					report.updateTestLog("Validating Queue entity navigation",
							"Able to Navigate to Queue entity and select the record.", Status.PASS);
				} else {
					report.updateTestLog("Validating Queue entity navigation", "Not able to select the record.",
							Status.FAIL);
				}
				UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
			}
		} catch (Exception Ex) {
			report.updateTestLog("Validating Queue entity navigation", "Exception Occured: " + Ex.getMessage(),
					Status.FAIL);
		}

	}



	public static final By dueDate = By.xpath("//a[@title='Sort by Due Date (Object)']");
	public static final By enteredQueue = By.xpath("//a[@title='Sort by Entered Queue']");
	public static final By title = By.xpath("//a[@title='Sort by Title']");
	public static final By description = By.xpath("//a[@title='Sort by Description (Object)']");
	public static final By customer = By.xpath("//a[@title='Sort by Customer (Object)']");
	public static final By queue = By.xpath("//a[@title='Sort by Queue']");
	public static final By owner = By.xpath("//a[@title='Sort by Owner (Object)']");
	public static final By workedBy = By.xpath("//a[@title='Sort by Worked By']");
	public static final By exportToExcel = By.xpath("//span[text()=' Export to Excel ']");
	public static final By setAsDefaultView = By.xpath("//span[text()=' Set As Default View ']");
	public static final By CaseStatus1 = By.xpath("//label[@id='Activity Status_label']");
	public static final By recentlyViewedImage = By.xpath("//span[text()='navTabGlobalMruImage_TabGlobalMruNode']");
	public static final By completedReminderRecentlyView=By.xpath("//span[text()='Queue Item: Completed Reminders']");
		
	
	
	
}
