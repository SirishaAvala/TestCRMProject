package businessComponents;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.cognizant.framework.Status;
import com.cognizant.Craft.*;
import pageObjects.*;

public class Claims_MSCRM extends ReusableLibrary {

	public Claims_MSCRM(ScriptHelper scriptHelper) {
		super(scriptHelper);
		// TODO Auto-generated constructor stub
	}

	// Page Objects
	int maxTimeOut = 60;
	int minTimeOut = 10;
	reusableFunctions reusableFunc = new reusableFunctions(scriptHelper);
	Case_MSCRM caseModule = new Case_MSCRM(scriptHelper);
	Accounts_MSCRM accModule = new Accounts_MSCRM(scriptHelper);

	public static final By serviceModule = By.xpath("//img[@alt='Service']//parent::span");
	public static final By claimEntity = By.cssSelector("a#cxp_claim");
	public static final By recordSearchBox = By.cssSelector("input#crmGrid_findCriteria");
	
	public static final By viewDetails = By.xpath("//a[@title='Select a view']");
	public static final By queueDropDown = By.xpath("//select[@id='crmQueueSelector']");
	public static final By claimNumber = By.cssSelector("span#cxp_claimnumber_cl");
	public static final By claimNumbertxt = By.xpath("//label[@id='Claim Number_label']");
	public static final By claimStatus = By.cssSelector("span#cxp_claimstatus_cl");
	public static final By claimStatustxt = By.xpath("//label[@id='Claim Status_label']");
	public static final By claimLossDate = By.cssSelector("span#cxp_lossdate_cl");
	public static final By claimLossDatetxt = By.xpath("//label[@id='Loss Date_label']");
	public static final By claimLossDescription = By.cssSelector("span#cxp_lossdescription_cl");
	public static final By claimLossDescriptiontxt = By.xpath("//label[@id='Loss Description_label']");
	public static final By claimClosedDate = By.cssSelector("span#cxp_closeddate_cl");
	public static final By claimClosedDatetxt = By.xpath("//label[@id='Closed Date_label']");
	public static final By claimPolicy = By.cssSelector("span#cxp_policyid_cl");
	public static final By claimPolicytxt = By.xpath("//label[@id='Policy_label']");
	public static final By adjusterSection = By.xpath("//h2[text()='Adjusters']");
	public static final By phoneNumberadjuster = By.xpath("//label[text()='Phone Number (Adjuster)']");
	public static final By adjuster = By.xpath("//label[text()='Adjuster']");
	public static final By emailAdjuster = By.xpath("//label[text()='Email (Adjuster)']");
	public static final By titleAdjuster = By.xpath("//label[text()='Title (Adjuster)']");
	public static final By roleAdjuster = By.xpath("//label[text()='Role (Adjuster)']");
	public static final By claimantSection = By.xpath("//h2[text()='Claimants']");
	public static final By claim_claimantSection = By.xpath("//label[text()='Claim']");
	public static final By individual_claimantSection = By.xpath("//label[text()='Individual']");
	public static final By mobilePhoneInd_claimantSection = By.xpath("//label[text()='Mobile Phone (Individual)']");
	public static final By primaryEmailInd_claimantSection = By.xpath("//label[text()='Primary Email (Individual)']");
	public static final By Organization_claimantSection = By.xpath("//label[text()='Organization']");
	public static final By mobilePhoneOrg_claimantSection = By.xpath("//label[text()='Mobile Phone (Organization)']");
	public static final By primaryEmailOrg_claimantSection = By.xpath("//label[text()='Primary Email (Organization)']");
	public static final By role_claimantSection = By.xpath("//label[text()='Role']");
	public static final By readonly_claim = By.xpath("//span[text()='Read only']");
   public static final By claim_collapse_Org = By.xpath("(//a[@title='Collapse this tab'])[4]");
   public static final By claim_rec_link = By.xpath("//a[contains(@title,'Claim Number')]//following::table[5]//a");
	

public void navigateToClaimEntity() throws InterruptedException {
		driver.switchTo().defaultContent();
		reusableFunc.navigationToEntity(driver.getWebDriver(), serviceModule, claimEntity);
		try {
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), CRMCasePage.viewDropdown);
			if (reusableFunc.waitForElementToBePresent(driver.getWebDriver(), CRMCasePage.viewDropdown, maxTimeOut)) {
				report.updateTestLog("Validating Claims entity navigation", "Able to Navigate to Claims entity",
						Status.PASS);
			}
		} catch (Exception e) {
			report.updateTestLog("Validating Claims entity navigation", "Not able to Navigate to Claims entity",
					Status.FAIL);
		}

	}

	public void searchClaimRelatedRecordsAndSelect() throws InterruptedException {
		try {
			
			String recordToSearch = dataTable.getData("General_Data", "recordToSearch");
			String viewName = dataTable.getData("General_Data", "viewName");
			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
			driver.switchTo().defaultContent();
			reusableFunc.navigationToEntity(driver.getWebDriver(), serviceModule, claimEntity);
			
			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
			driver.navigate().refresh();
			selectViewFromClaimsEntity(viewName);
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
					report.updateTestLog("Validating Claims entity navigation",
							"Able to Navigate to Claims entity and select the record.", Status.PASS);
				} else {
					report.updateTestLog("Validating Claims entity navigation", "Not able to select the record.",
							Status.FAIL);
				}
				UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
			}
		} catch (Exception Ex) {
			report.updateTestLog("Validating Claims entity navigation", "Exception Occured: " + Ex.getMessage(),
					Status.FAIL);
		}

	}

	public void searchClaimRelatedRecords(String recordToSearch) throws InterruptedException {
		try {
			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
			driver.switchTo().defaultContent();
			reusableFunc.navigationToEntity(driver.getWebDriver(), serviceModule, claimEntity);
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

	
	public void selectViewFromClaimsEntity(String viewName) {
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
				report.updateTestLog("Select View From Claims Entity", "Able to select the expected view", Status.PASS);
			} else {
				report.updateTestLog("Select View From Claims Entity", "Not able to select the expected view",
						Status.FAIL);
			}

		} catch (Exception Ex) {
			report.updateTestLog("Select View From Claims Entity", "Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}
	}

	
	
	public void selectClaimsRelatedRecord(String recordToSearch,String viewName) throws InterruptedException {
		try {
			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
			driver.switchTo().defaultContent();
			reusableFunc.navigationToEntity(driver.getWebDriver(), serviceModule, claimEntity);
			
			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
			driver.navigate().refresh();
			selectViewFromClaimsEntity(viewName);
			UI_Helpers_OOB.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Iframe0);

			if (UI_Helpers_OOB.waitForElementToBeClickableBool(driver.getWebDriver(), recordSearchBox, maxTimeOut)) {
				
				By recordXpath = By
						.xpath("//div[@id='crmGrid_divDataArea']//following::a[text()='" + recordToSearch + "']");

				UI_Helpers_OOB.waitForElementToBeClickableBool(driver.getWebDriver(), recordXpath, maxTimeOut);
				driver.findElement(recordXpath).click();

				By titleXpath = By.xpath("//h1[@title='" + recordToSearch + "']");
				UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.baseFrame1);

				if (UI_Helpers_OOB.waitForElementToBeVisible(driver.getWebDriver(), titleXpath, maxTimeOut)) {
					report.updateTestLog("Validating Claims entity navigation",
							"Able to Navigate to Claims entity and select the record.", Status.PASS);
				} else {
					report.updateTestLog("Validating Claims entity navigation", "Not able to select the record.",
							Status.FAIL);
				}
				UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
			}
		} catch (Exception Ex) {
			report.updateTestLog("Validating Claims entity navigation", "Exception Occured: " + Ex.getMessage(),
					Status.FAIL);
		}

	}
	
	public void verifySummarySectionClaimForm() {
		try {
			// Switch To Frame
			
			
			String ClaimNumber = dataTable.getData("General_Data", "ClaimNumber");
			String ClaimStatus = dataTable.getData("General_Data", "ClaimStatus");
			String LossDate = dataTable.getData("General_Data", "LossDate");
			String LossDescription = dataTable.getData("General_Data", "LossDescription");
			String ClosedDate = dataTable.getData("General_Data", "ClosedDate");
			String Policy = dataTable.getData("General_Data", "Policy");
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), claimNumber);
			String claimNumber = driver.findElement(claimNumbertxt).getText();
			String claimStatus = driver.findElement(claimStatustxt).getText();
			String claimLossDate = driver.findElement(claimLossDatetxt).getText();
			String claimLossDesc = driver.findElement(claimLossDescriptiontxt).getText();
			String claimClosedDate = driver.findElement(claimClosedDatetxt).getText();
			String claimPolicy = driver.findElement(claimPolicytxt).getText();
			//String claimNumber = driver.findElement(claimNumbertxt).getText();
			

			if (claimNumber.equalsIgnoreCase(ClaimNumber)) {
				report.updateTestLog("Validation Claim Number on  Claims", "Claim Number is not available on cliam form", Status.FAIL);
			} else {
				report.updateTestLog("Validation Claim Number on  Claims", "Claim Number is available on claim form with value as :"+claimNumber, Status.PASS);
			}

			if (claimStatus.equalsIgnoreCase(ClaimStatus)) {
				report.updateTestLog("Validation Claim Status on  Claims", "Claim Status is not available on claim form", Status.FAIL);
			} else {
				report.updateTestLog("Validation Claim Status on  Claims", "Claim Status is available on claim form  with value as :"+claimStatus, Status.PASS);
						
			}
			if (claimLossDate.equalsIgnoreCase(LossDate)) {
				report.updateTestLog("Validation LossDate  on  Claims", "LossDate is available on claim form ", Status.FAIL);
			} else {
				report.updateTestLog("Validation LossDate on  Claims", "LossDate is not available on claim form with value as :"+claimLossDate, Status.PASS);
			}

			if (claimLossDesc.equalsIgnoreCase(LossDescription)) {
				report.updateTestLog("Validation LossDescription on  Claims", "LossDescription is available on claim form ", Status.FAIL);
			} else {
				report.updateTestLog("Validation LossDescription on  Claims", "LossDescription is not available on claim form with value as :"+claimLossDesc, Status.PASS);
						
			}
			if (claimClosedDate.equalsIgnoreCase(ClosedDate)) {
				report.updateTestLog("Validation ClosedDate on  Claims", "ClosedDate is available on cliam form ", Status.FAIL);
			} else {
				report.updateTestLog("Validation ClosedDate on  Claims", "ClosedDate is not available on claim form with value as :"+claimClosedDate, Status.PASS);
			}

			if (claimPolicy.equalsIgnoreCase(Policy)) {
				report.updateTestLog("Validation Policy on  Claims", "Policy is available on claim form ", Status.FAIL);
			} else {
				report.updateTestLog("Validation Policy on  Claims", "Policy is not available on claim form with value as :"+claimPolicy, Status.PASS);
						
			}
			
		} catch (Exception Ex) {
			report.updateTestLog("Select View From Claims Entity", "Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}
	}

	public void verifyAdjusterSectionClaimForm() {
		
		
		try {
			
			String AdjusterName = dataTable.getData("General_Data", "AdjusterName");
			String AdjusterRole = dataTable.getData("General_Data", "AdjusterRole");
			String AdjusterTitle = dataTable.getData("General_Data", "AdjusterTitle");
			String AdjusterEmail = dataTable.getData("General_Data", "AdjusterEmail");
			String AdjusterPhnumber = dataTable.getData("General_Data", "AdjusterPhnumber");
			
			// Switch To Frame
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), adjuster);
		
			try {
				if (driver.findElement(adjuster).isDisplayed()) {
					report.updateTestLog("Validation of Adjuster column on  Claims", "Adjuster column is available on claim form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Adjuster column on  Claims", "Adjuster column is not available on claim form", Status.FAIL);
			}
			
			
			try {
				if (driver.findElement(emailAdjuster).isDisplayed()) {
					report.updateTestLog("Validation of email Adjuster column on  Claims", "Email Adjuster column is available on claim form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of email Adjuster column on  Claims", "Email Adjuster column is not available on claim form", Status.FAIL);
			}
			
			try {
				if (driver.findElement(titleAdjuster).isDisplayed()) {
					report.updateTestLog("Validation of Title Adjuster column on  Claims", "Title Adjuster column is available on claim form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Title Adjuster column on  Claims", "Title Adjuster column is not available on claim form", Status.FAIL);
			}
			
			try {
				if (driver.findElement(phoneNumberadjuster).isDisplayed()) {
					report.updateTestLog("Validation of Phone Adjuster column on  Claims", "Phone Adjuster column is available on claim form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Phone  Adjuster column on  Claims", "Phone Adjuster column is not available on claim form", Status.FAIL);
			}
			
			try {
				if (driver.findElement(roleAdjuster).isDisplayed()) {
					report.updateTestLog("Validation of Role Adjuster column on  Claims", "Role Adjuster column is available on claim form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Role Adjuster column on  Claims", "Role Adjuster column is not available on claim form", Status.FAIL);
			}
			try {
				if (driver.findElement(By.xpath("//span[text()='"+AdjusterName+"']")).isDisplayed()) {
					report.updateTestLog("Validation of Adjuster Name on  Claims", "Adjuster Name on  Claims is available on claim form with value as:"+AdjusterName, Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Adjuster Name on  Claims", "Adjuster Name on  Claims is not available on claim form", Status.FAIL);
			}
			try {
				if (driver.findElement(By.xpath("//div[text()='"+AdjusterRole+"']")).isDisplayed()) {
					report.updateTestLog("Validation of Adjuster Role on  Claims", "Adjuster Role on  Claims is available on claim form with value as:"+AdjusterRole, Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Adjuster Role on  Claims", "Adjuster Role on  Claims is not available on claim form", Status.FAIL);
			}
			
			try {
				if (driver.findElement(By.xpath("//div[text()='"+AdjusterTitle+"']")).isDisplayed()) {
					report.updateTestLog("Validation of Adjuster Title on  Claims", "Adjuster Title on  Claims is available on claim form with value as:"+AdjusterTitle, Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Adjuster Title on  Claims", "Adjuster Title on  Claims is not available on claim form", Status.FAIL);
			}
			
			
			try {
				if (driver.findElement(By.xpath("//span[text()='"+AdjusterName+"']//following::div")).isDisplayed()) {
					report.updateTestLog("Validation of Adjuster Email on  Claims", "Adjuster Email on  Claims is available on claim form with value as:"+AdjusterEmail, Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Adjuster Email on  Claims", "Adjuster Email on  Claims is not available on claim form", Status.FAIL);
			}
			
			try {
				if (driver.findElement(By.xpath("//span[text()='"+AdjusterName+"']//following::td[2]")).isDisplayed()) {
					report.updateTestLog("Validation of Adjuster Phone number on  Claims", "Adjuster Phone number on  Claims is available on claim form with value as:"+AdjusterPhnumber, Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Adjuster Phone number on  Claims", "Adjuster Phone number on  Claims is not available on claim form", Status.FAIL);
			}
		} catch (Exception Ex) {
			report.updateTestLog("Validation of Adjuster column on  Claims", "Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}
	}
	
	public void verifyClaimAsReadOnly() {
		try {
			
			
			// Switch To Frame
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), readonly_claim);
		
			try {
				if (driver.findElement(readonly_claim).isDisplayed()) {
					report.updateTestLog("Validation of form as Read Only", "Form is Read only to User", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of form as Read Only", "Form is not Read only to User", Status.FAIL);
			}
			
		} catch (Exception Ex) {
			report.updateTestLog("Validation of form as Read Only", "Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}
	}
	
	
	public void verifyClaimantSectionClaimForm() {
		try {
			
			
			// Switch To Frame
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), claim_claimantSection);
		
			try {
				if (driver.findElement(claim_claimantSection).isDisplayed()) {
					report.updateTestLog("Validation of Claim column on  Claims under Claimant section", "Claim column is available on claim form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Claim column on  Claims under Claimant section", "Claim column is not available on claim form", Status.FAIL);
			}
			
			
			try {
				if (driver.findElement(individual_claimantSection).isDisplayed()) {
					report.updateTestLog("Validation of Individual column on  Claims under Claimant section", "Individual column is available on claim form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of email Individul column on  Claims under Claimant section", "Individual column is not available on claim form", Status.FAIL);
			}
			
			try {
				if (driver.findElement(mobilePhoneInd_claimantSection).isDisplayed()) {
					report.updateTestLog("Validation of Mobile Phone individual column on  Claims under Claimant Section", "Mobile Phone individual column is available on claim form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Mobile Phone individual column on  Claims under Claimant Section", "Mobile Phone individual column is not available on claim form", Status.FAIL);
			}
			
			try {
				if (driver.findElement(primaryEmailInd_claimantSection).isDisplayed()) {
					report.updateTestLog("Validation of Primary Email individual column on  Claims under Claimant Section", "Primary Email individual column is available on claim form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Primary Email individual column on  Claims under Claimant Section", "Primary Email individual column is not available on claim form", Status.FAIL);
			}
			
			try {
				if (driver.findElement(Organization_claimantSection).isDisplayed()) {
					report.updateTestLog("Validation of Organization column on  Claims under Claimant Section", "Organization column is available on claim form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Organization column on  Claims under Claimant Section", "Organization column is not  available on claim form", Status.FAIL);
			}
			
			try {
				if (driver.findElement(mobilePhoneOrg_claimantSection).isDisplayed()) {
					report.updateTestLog("Validation of Mobile Phone Organization column on  Claims under Claimant Section", "Mobile Phone Organization column is available on claim form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Mobile Phone Organization column on  Claims under Claimant Section", "Mobile Phone Organization column is not  available on claim form", Status.FAIL);
			}
			
			try {
				if (driver.findElement(primaryEmailOrg_claimantSection).isDisplayed()) {
					report.updateTestLog("Validation of Primary Email Organization column on  Claims under Claimant Section", "Primary Email Organization column is available on claim form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Primary Email Organization column on  Claims under Claimant Section", "Primary Email Organization column is not  available on claim form", Status.FAIL);
			}
			
			try {
				if (driver.findElement(role_claimantSection).isDisplayed()) {
					report.updateTestLog("Validation of Role column on  Claims under Claimant Section", "Role column is available on claim form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Role column on  Claims under Claimant Section", "Role column is not  available on claim form", Status.FAIL);
			}
			
		} catch (Exception Ex) {
			report.updateTestLog("Validation of Claimant Section on  Claims", "Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}
		
		
	}
	
	public void navigateToClaimFromAcc()
	{
		try{
			
			
			String Organization = dataTable.getData("General_Data", "Organization");
			String ClaimNumber = dataTable.getData("General_Data", "ClaimNumber");
			
			accModule.searchAccountRelatedRecordsAndSelect(Organization);
			By titleXpath = By.xpath("//h1[@title='" + Organization + "']");
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), titleXpath);
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), claim_collapse_Org);
			
			
				
				try{
					if((driver.findElement(claim_collapse_Org).isDisplayed()))
							{
						report.updateTestLog("Validating Claim Section expanded Under Org", "Claim Section is in Expanded mode under Organization", Status.PASS);
							}
				}
			catch(Exception e) {
				report.updateTestLog("Validating Claim Section expanded Under Org", "Claim Section is not in Expanded mode under Organization", Status.FAIL);	
				
			}
				driver.findElement(claim_rec_link).click();
				
				Thread.sleep(2000);
				reusableFunc.switchToDefaultFrame(driver.getWebDriver());
				By titleXpath1 = By.xpath("//h1[contains(@title,'" + ClaimNumber + "')]");
				reusableFunc.switchToObjectFrame(driver.getWebDriver(), titleXpath1);
				
				try{
					if(driver.findElement(titleXpath1).isDisplayed())
							{
						report.updateTestLog("Validating Claim Form navigation From Org", "Navigated to claim form  with opening as new claim form", Status.FAIL);
							}
				}
			catch(Exception e) {
				report.updateTestLog("Validating Claim Form navigation From Org", "Navigated to claim form  successfully without opening as new  claim form", Status.PASS);	
				
			}
			
		}catch (Exception Ex) {
			report.updateTestLog("Validating Claim Form navigation From Org", "Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}
	}
}

	

