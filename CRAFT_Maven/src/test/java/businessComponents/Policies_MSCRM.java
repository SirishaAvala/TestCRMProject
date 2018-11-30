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

public class Policies_MSCRM extends ReusableLibrary {

	public Policies_MSCRM(ScriptHelper scriptHelper) {
		super(scriptHelper);
		// TODO Auto-generated constructor stub
	}

	// Page Objects
	int maxTimeOut = 60;
	int minTimeOut = 10;
	reusableFunctions reusableFunc = new reusableFunctions(scriptHelper);
	Case_MSCRM caseModule = new Case_MSCRM(scriptHelper);
	BillAccounts_MSCRM billAccModule = new BillAccounts_MSCRM(scriptHelper);

	public static final By serviceModule = By.xpath("//img[@alt='Service']//parent::span");
	public static final By policiesEntity = By.cssSelector("a#cxp_policy");
	
	public static final By recordSearchBox = By.cssSelector("input#crmGrid_findCriteria");
	
	public static final By locationAddressSection=By.xpath("//h3[text='LOCATION ADDRESSES']");
	
	public static final By viewDetails = By.xpath("//a[@title='Select a view']");
	public static final By queueDropDown = By.xpath("//select[@id='crmQueueSelector']");
	public static final By General = By.xpath("//label[text()='General']");
    public static final By street1Label=By.cssSelector("span#cxp_street1_cl");
    public static final By street2Label=By.cssSelector("span#cxp_street2_cl");
    public static final By street3Label=By.cssSelector("span#cxp_street3_cl");
    public static final By cityLabel=By.cssSelector("span#cxp_city_cl");
    public static final By stateLabel=By.cssSelector("span#cxp_state_cl");
    public static final By postalCodeLabel=By.cssSelector("span#cxp_postalcode_cl");
    public static final By countryLabel=By.cssSelector("span#cxp_country_cl");
    public static final By policyLabel=By.cssSelector("span#header_cxp_policyid_cl");
    public static final By street1txt = By.xpath("//label[@id='Street1_label']/div");
    public static final By street2txt = By.xpath("//label[@id='Street2_label']/div");
    public static final By street3txt = By.xpath("//label[@id='Street3_label']/div");
    public static final By citytxt = By.xpath("//label[@id='City_label']/div");
    public static final By statetxt = By.xpath("//label[@id='State_label']/div");
    public static final By countrytxt = By.xpath("//label[@id='Country_label']/div");
    public static final By postalCodetxt = By.xpath("//label[@id='Postal Code_label']/div");
    public static final By policytxt = By.cssSelector("span#header_cxp_policyid_lookupValue");
    public static final By readonly_policy = By.xpath("//span[text()='Read only']");
    public static final By policy = By.xpath("//table[@title='Policy']");
    public static final By street1 = By.xpath("//table[@title='Street1']");
    public static final By street2 = By.xpath("//table[@title='Street2']");
    public static final By street3 = By.xpath("//table[@title='Street3']");
    public static final By city = By.xpath("//table[@title='City']");
    public static final By state = By.xpath("//table[@title='State']");
    public static final By postalCode = By.xpath("//table[@title='Postal Code']");
    public static final By country = By.xpath("//table[@title='Country']");
    public static final By policyInformationSection = By.xpath("//h2[text()='Policy Information']");
	public static final By policySummarySection = By.xpath("//h3[text()='POLICY SUMMARY']");
	public static final By agencyDetailSection = By.xpath("//h3[text()='AGENCY DETAILS']");
	public static final By policyDetailSection = By.xpath("//h3[text()='POLICY DETAIL']");
	public static final By policyClaimSection = By.xpath("//h2[text()='CLAIMS']");
	public static final By policyNumber = By.cssSelector("span#cxp_policynumber_cl");
	public static final By policyHolder = By.cssSelector("span#cxp_policyholder_cl");
	public static final By searchURL = By.cssSelector("span#cxp_underwritingsystemurlsearch_cl");
	public static final By transactionURL = By.cssSelector("span#cxp_underwritingsystemurltransactionhistory_cl");
	public static final By region = By.cssSelector("span#cxp_region_cl");
	public static final By account = By.cssSelector("span#cxp_accountid_cl");
	public static final By contact = By.cssSelector("span#cxp_contactid_cl");
	public static final By coInsured = By.cssSelector("span#cxp_coinsured_cl");
	public static final By billAccount = By.cssSelector("span#cxp_billaccountid_cl");
	public static final By effectiveDate = By.cssSelector("span#cxp_effectivedate_cl");
	public static final By expirationDate = By.cssSelector("span#cxp_expirationdate_cl");
	public static final By agencyName = By.cssSelector("span#cxp_agencyname_cl");
	public static final By agencyCode = By.cssSelector("span#cxp_agencycode_cl");
	public static final By phoneNumber = By.cssSelector("span#cxp_agencyphonenumber_cl");
	public static final By presidentClub = By.cssSelector("span#cxp_agencylastyearpresidentsclub_cl");
	public static final By payPlan = By.cssSelector("span#cxp_payplan_cl");
	public static final By CSSRegStatus = By.cssSelector("span#cxp_cssregistrationstatus_cl");
	public static final By claimNumber = By.cssSelector("//table[@title='Claim Number']");
	public static final By claimStatus = By.cssSelector("//table[@title='Claim Status']");
	public static final By lossDate = By.cssSelector("//table[@title='Loss Date']");
	public static final By lossDesc = By.cssSelector("//table[@title='Loss Description']");
	public static final By Policy = By.cssSelector("//table[@title='Policy']");
	public static final By UnderwriterDetailSection = By.xpath("//h3[text()='UNDERWRITER DETAILS']");
	public static final By phoneNumberUW = By.cssSelector("span#cxp_underwriterphonenumber_cl");
	public static final By Email = By.cssSelector("span#cxp_underwriteremail_cl");
	public static final By UnderWriter = By.cssSelector("span#cxp_underwriter_cl");
	
	
	
	
	public void navigateToPoliciesEntity() throws InterruptedException {
		driver.switchTo().defaultContent();
		reusableFunc.navigationToEntity(driver.getWebDriver(), serviceModule, policiesEntity);
		
		Thread.sleep(2000);
		try {
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), CRMCasePage.viewDropdown);
			if (reusableFunc.waitForElementToBePresent(driver.getWebDriver(), CRMCasePage.viewDropdown, maxTimeOut)) {
				report.updateTestLog("Validating Policies entity navigation", "Able to Navigate to Policies entity",
						Status.PASS);
			}
		} catch (Exception e) {
			report.updateTestLog("Validating Policies entity navigation", "Not able to Navigate to Policies entity",
					Status.FAIL);
		}

	}
	public void searchPolicyFromLocationAddressRelatedRecords() throws InterruptedException {
		try {
		
		String locationAddress = dataTable.getData("General_Data","recordToSearch");
		
		String Policy = dataTable.getData("General_Data","Policy");
			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
			driver.switchTo().defaultContent();
			reusableFunc.navigationToEntity(driver.getWebDriver(), serviceModule, Case_MSCRM.locationAddressLink);
			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
			driver.navigate().refresh();

			By activeLocationAddress = By.xpath(
					"//a[@title=\"Active Location Addresses\"]//following::span[text()=\"Active Location Addresses\"]");
			caseModule.selectViewFromCaseEntity(Case_MSCRM.activeLocationAddressText, activeLocationAddress);

			UI_Helpers_OOB.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Iframe0);

			if (UI_Helpers_OOB.waitForElementToBeClickableBool(driver.getWebDriver(), recordSearchBox, maxTimeOut)) {
				reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), recordSearchBox);
				WebElement element = driver.findElement(recordSearchBox);
				element.sendKeys(locationAddress);
				element.sendKeys(Keys.ENTER);

				By recordXpath = By
						.xpath("//div[@id='crmGrid_divDataArea']//following::a[contains(@title,'"+Policy+"')]");

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

				By titleXpath = By.xpath("//h1[contains(@title,'"+Policy+"')]");
				UI_Helpers_OOB.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Iframe1);

				if (UI_Helpers_OOB.waitForElementToBeVisible(driver.getWebDriver(), titleXpath, maxTimeOut)) {
					report.updateTestLog("Validating Policy navigation from Location Addresses entity",
							"Able to Navigate to Policy from Location Addresses entity", Status.PASS);
				} else {
					report.updateTestLog("Validating Policy navigation from Location Addresses entity",
							"Not Able to Navigate to Policy from Location Addresses entity", Status.FAIL);
				}
				UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
			}
		} catch (Exception Ex) {
			report.updateTestLog("Search Policy Related Records And Select",
					"Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}

	}
	
	public void navigateToPolicyFromLocationAddress() throws InterruptedException {
		try {
		
		String Policy = dataTable.getData("General_Data","Policy");
		
			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
				
				By recordXpath = By
						.xpath("//label[text()='"+Policy+"']");

				if (UI_Helpers_OOB.waitForElementToBeClickableBool(driver.getWebDriver(), recordXpath, maxTimeOut)) {
					driver.findElement(recordXpath).click();
					
					report.updateTestLog("Validating Policy navigation from Location Address",
							"Able to Navigate to Policy from Location Addresses ", Status.PASS);
				} else {
					report.updateTestLog("Validating Policy navigation from Location Address",
							"Not Able to Navigate to Policy from Location Addresses ", Status.FAIL);
				}

				By titleXpath = By.xpath("//div[@id='FormTitle']");
				driver.switchTo().defaultContent();
				reusableFunc.switchToObjectFrame(driver.getWebDriver(), titleXpath);
				//UI_Helpers_OOB.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Iframe1);

				if (UI_Helpers_OOB.waitForElementToBeVisible(driver.getWebDriver(), titleXpath, maxTimeOut)) {
					report.updateTestLog("Validating Policy navigation from Location Address",
							"Able to Navigate to Policy from Location Addresses ", Status.PASS);
				} else {
					report.updateTestLog("Validating Policy navigation from Location Address",
							"Not Able to Navigate to Policy from Location Addresses ", Status.FAIL);
				}
				UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
			
		} catch (Exception Ex) {
			report.updateTestLog("Navigate to Policy from Location Address",
					"Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}

	}
	
	public void searchPolicyRelatedRecords() throws InterruptedException {
		try {
		
		String UnformattedPolicy = dataTable.getData("General_Data","UnformattedPolicy");
		String Policy = dataTable.getData("General_Data","Policy");
		
			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
			driver.switchTo().defaultContent();
			reusableFunc.navigationToEntity(driver.getWebDriver(), serviceModule, policiesEntity);
			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
			driver.navigate().refresh();

			By activeLocationAddress = By.xpath(
					"//a[@title=\"Active Policies\"]//following::span[text()=\"Active Policies\"]");
			//caseModule.selectViewFromCaseEntity(Case_MSCRM.activeLocationAddressText, activeLocationAddress);

			UI_Helpers_OOB.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Iframe0);

			if (UI_Helpers_OOB.waitForElementToBeClickableBool(driver.getWebDriver(), recordSearchBox, maxTimeOut)) {
				reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), recordSearchBox);
				WebElement element = driver.findElement(recordSearchBox);
				element.sendKeys(UnformattedPolicy);
				element.sendKeys(Keys.ENTER);

				By recordXpath = By
						.xpath("//a[@title='"+Policy+"']");

				// UI_Helpers_OOB.waitForElementToBeClickableBool(driver.getWebDriver(),
				// recordXpath, maxTimeOut);
reusableFunc.switchToObjectFrame(driver.getWebDriver(), recordXpath);
				if (UI_Helpers_OOB.waitForElementToBeClickableBool(driver.getWebDriver(), recordXpath, maxTimeOut)) {
					driver.findElement(recordXpath).click();
					report.updateTestLog("Validating Policies entity navigation",
							"Able to Navigate to Policies entity and select the record.", Status.PASS);
				} else {
					report.updateTestLog("Validating Policies entity navigation",
							"Not able to select the record.", Status.FAIL);
				}

				By titleXpath = By.xpath("//h1[contains(@title,'"+Policy+"')]");
				UI_Helpers_OOB.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Iframe1);

				if (UI_Helpers_OOB.waitForElementToBeVisible(driver.getWebDriver(), titleXpath, maxTimeOut)) {
					report.updateTestLog("Validating Policies entity navigation",
							"Able to Navigate to Policies entity and select the record.", Status.PASS);
				} else {
					report.updateTestLog("Validating Policies entity navigation",
							"Not able to select the record.", Status.FAIL);
				}
				UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
			}
		} catch (Exception Ex) {
			report.updateTestLog("Search Policies Related Records And Select",
					"Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}

	}
	
public void verifyLocationAddressDetailsonPolicyForm() {
			
			
			try {
				
				String Street1 = dataTable.getData("General_Data", "Street1");
				String Street2 = dataTable.getData("General_Data", "Street2");
				String Street3 = dataTable.getData("General_Data", "Street3");
				String City = dataTable.getData("General_Data", "City");
				String State = dataTable.getData("General_Data", "State");
				String Country = dataTable.getData("General_Data", "Country");
				String PostalCode = dataTable.getData("General_Data", "PostalCode");
				String Policy = dataTable.getData("General_Data", "Policy");
				
				Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_END);
				robot.keyRelease(KeyEvent.VK_END);
				Thread.sleep(5000);
				// Switch To Frame
				reusableFunc.switchToDefaultFrame(driver.getWebDriver());
				reusableFunc.switchToObjectFrame(driver.getWebDriver(), street1);
			
				try {
					if (driver.findElement(street1).isDisplayed()) {
						report.updateTestLog("Validation of Street1 column under Location Address section on Policy Form", "Street1 col is available under location address on policy form", Status.PASS);
					}
				} catch (Exception e) {
					report.updateTestLog("Validation of Street1 column under Location Address section on Policy Form", "Street1 col is  not available under location address on policy form", Status.FAIL);
				}
				
				
				try {
					if (driver.findElement(street2).isDisplayed()) {
						report.updateTestLog("Validation of Street2 column under Location Address section on Policy Form", "Street2 col is available under location address on policy form", Status.PASS);
					}
				} catch (Exception e) {
					report.updateTestLog("Validation of Street2 column under Location Address section on Policy Form", "Street2 col is not available under location address on policy form", Status.FAIL);
				}
				
				try {
					if (driver.findElement(street3).isDisplayed()) {
						report.updateTestLog("Validation of Street3 column under Location Address section on Policy Form", "Street3 col is available under location address on policy form", Status.PASS);
					}
				} catch (Exception e) {
					report.updateTestLog("Validation of Street3 column under Location Address section on Policy Form", "Street3 col is not  available under location address on policy form", Status.FAIL);
				}
				
				try {
					if (driver.findElement(city).isDisplayed()) {
						report.updateTestLog("Validation of City column under Location Address section on Policy Form", "City col is available under location address on policy form", Status.PASS);
					}
				} catch (Exception e) {
					report.updateTestLog("Validation of City column under Location Address section on Policy Form", "City col is not  available under location address on policy form", Status.FAIL);
				}
				
				try {
					if (driver.findElement(state).isDisplayed()) {
						report.updateTestLog("Validation of State column under Location Address section on Policy Form", "State col is  available under location address on policy form", Status.PASS);
					}
				} catch (Exception e) {
					report.updateTestLog("Validation of State column under Location Address section on Policy Form", "State col is not available under location address on policy form", Status.FAIL);
				}
				try {
					if (driver.findElement(country).isDisplayed()) {
						report.updateTestLog("Validation of Country fcolumn under Location Address section on Policy Form", "Country col is available under location address on policy form", Status.PASS);
					}
				} catch (Exception e) {
					report.updateTestLog("Validation of Country column under Location Address section on Policy Form", "Country col is not  available under location address on policy form", Status.FAIL);
				}
				try {
					if (driver.findElement(postalCode).isDisplayed()) {
						report.updateTestLog("Validation of Postal Code column under Location Address section on Policy Form", "Postal Code col is  available under location address on policy form", Status.PASS);
					}
				} catch (Exception e) {
					report.updateTestLog("Validation of Postal Code column under Location Address section on Policy Form", "Postal Code col isnot  available under location address on policy form", Status.FAIL);
				}
				
				try {
					if (driver.findElement(policy).isDisplayed()) {
						report.updateTestLog("Validation of Policy field on Location Address Form", "Policy is available on location address form", Status.PASS);
					}
				} catch (Exception e) {
					report.updateTestLog("Validation of Policy field on Location Address Form", "Policy is not available on location address form", Status.FAIL);
				}
				
				
				
			}catch (Exception Ex) {
				report.updateTestLog("Location Address form validation", "Exception Occured: " + Ex.getMessage(), Status.FAIL);
			}
		}



public void verifyLocationAddressSectionValuesDeatilsOnPolicyForm() {
	try {
		// Switch To Frame
		
		
		String Street1 = dataTable.getData("General_Data", "Street1");
		String Street2 = dataTable.getData("General_Data", "Street2");
		String Street3 = dataTable.getData("General_Data", "Street3");
		String City = dataTable.getData("General_Data", "City");
		String State = dataTable.getData("General_Data", "State");
		String Country = dataTable.getData("General_Data", "Country");
		String PostalCode = dataTable.getData("General_Data", "PostalCode");
		String Policy = dataTable.getData("General_Data", "Policy");
		
		reusableFunc.switchToDefaultFrame(driver.getWebDriver());
	
		reusableFunc.switchToObjectFrame(driver.getWebDriver(), street1txt);
	/*	String street1 = reusableFunc.getTextFromElement(driver.getWebDriver(),street2txt );
		String street2 = reusableFunc.getTextFromElement(driver.getWebDriver(),street3txt );
		String street3 = reusableFunc.getTextFromElement(driver.getWebDriver(),street2txt );
		String city = reusableFunc.getTextFromElement(driver.getWebDriver(),citytxt );
		String country = reusableFunc.getTextFromElement(driver.getWebDriver(),countrytxt );
		String state = reusableFunc.getTextFromElement(driver.getWebDriver(),statetxt );
		String postalCode = reusableFunc.getTextFromElement(driver.getWebDriver(),postalCodetxt );
		System.out.println("Street2 is :"+street2);*/
		try {
			if (driver.findElement(By.xpath("//a[@title='"+Street1+"']")).isDisplayed()) 
			{
				report.updateTestLog("Validation Street1 on  Policy under Location Address section", "Street1 is not available under Location Address on Policy", Status.FAIL);
			}
		} catch (Exception e) {
			
			report.updateTestLog("Validation Street1 on  Policy under  Location Address", "Street1 is available under Location Address on Policy :"+Street1, Status.PASS);
		}
		try {
			if (driver.findElement(By.xpath("//div[@title='"+Street2+"']")).isDisplayed()) 
			{
				report.updateTestLog("Validation Street2 on  Policy under Location Address section", "Street2 is not available under Location Address on Policy", Status.FAIL);
			}
		} catch (Exception e) {
			
			report.updateTestLog("Validation Street2 on  Policy under  Location Address", "Street2 is available under Location Address on Policy :"+Street2, Status.PASS);
		}
		try {
			if (driver.findElement(By.xpath("//div[@title='"+Street3+"']")).isDisplayed()) 
			{
				report.updateTestLog("Validation Street3 on  Policy under Location Address section", "Street3 is not available under Location Address on Policy", Status.FAIL);
			}
		} catch (Exception e) {
			
			report.updateTestLog("Validation Street3 on  Policy under  Location Address", "Street3 is available under Location Address on Policy :"+Street3, Status.PASS);
		}
		try {
			if (driver.findElement(By.xpath("//div[@title='"+City+"']")).isDisplayed()) 
			{
				report.updateTestLog("Validation City on  Policy under Location Address section", "City is not available under Location Address on Policy", Status.FAIL);
			}
		} catch (Exception e) {
			
			report.updateTestLog("Validation City on  Policy under  Location Address", "City is available under Location Address on Policy :"+City, Status.PASS);
		}
		
		try {
			if (driver.findElement(By.xpath("//div[@title='"+Country+"']")).isDisplayed()) 
			{
				report.updateTestLog("Validation Country on  Policy under Location Address section", "Country is not available under Location Address on Policy", Status.FAIL);
			}
		} catch (Exception e) {
			
			report.updateTestLog("Validation Country on  Policy under  Location Address", "Country is available under Location Address on Policy :"+Country, Status.PASS);
		}
		try {
			if (driver.findElement(By.xpath("//div[@title='"+State+"']")).isDisplayed()) 
			{
				report.updateTestLog("Validation State on  Policy under Location Address section", "State is not available under Location Address on Policy", Status.FAIL);
			}
		} catch (Exception e) {
			
			report.updateTestLog("Validation State on  Policy under  Location Address", "State is available under Location Address on Policy :"+State, Status.PASS);
		}
		
		try {
			if (driver.findElement(By.xpath("//td[@rawlookupitemname='"+Policy+"']")).isDisplayed()) 
			{
				report.updateTestLog("Validation Policy on  Policy under Location Address section", "Policy is not available under Location Address on Policy", Status.FAIL);
			}
		} catch (Exception e) {
			
			report.updateTestLog("Validation Policy on  Policy under  Location Address", "Policy is available under Location Address on Policy :"+Policy, Status.PASS);
		}
		
		try {
			if (driver.findElement(By.xpath("//div[@title='"+PostalCode+"']")).isDisplayed()) 
			{
				report.updateTestLog("Validation PostalCode on  Policy under Location Address section", "PostalCode is not available under Location Address on Policy", Status.FAIL);
			}
		} catch (Exception e) {
			
			report.updateTestLog("Validation PostalCode on  Policy under  Location Address", "PostalCode is available under Location Address on Policy :"+PostalCode, Status.PASS);
		}
		
		
		
		
	} catch (Exception Ex) {
		report.updateTestLog("Location address validation on Policy form", "Exception Occured: " + Ex.getMessage(), Status.FAIL);
	}
}

 public void verifyPolicySummarySection()
 {
	 try{
		 reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), policyInformationSection);
			try {
				if (driver.findElement(policyInformationSection).isDisplayed()) {
					report.updateTestLog("Validation of Policy Information on Policy Form", "Policy Information Section is available on policy form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Policy Information on Policy Form", "Policy Information Section is not available on policy form", Status.FAIL);
			}
		
			try {
				if (driver.findElement(policySummarySection).isDisplayed()) {
					report.updateTestLog("Validation of Policy Summary on Policy Form", "Policy Summary Section is available on policy form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Policy Summary on Policy Form", "Policy Summary Section is not available on policy form", Status.FAIL);
			}
			
			try {
				if (driver.findElement(policyNumber).isDisplayed()) {
					report.updateTestLog("Validation of Policy Number on Policy Form", "Policy Number  is available on policy form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Policy Number on Policy Form", "Policy Number  is not available on policy form", Status.FAIL);
			}
			try {
				if (driver.findElement(policyHolder).isDisplayed()) {
					report.updateTestLog("Validation of Policy Holder on Policy Form", "Policy Holder  is available on policy form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Policy Holder on Policy Form", "Policy Holder is not available on policy form", Status.FAIL);
			}
			try {
				if (driver.findElement(searchURL).isDisplayed()) {
					report.updateTestLog("Validation of Search URL on Policy Form", "Search URL  is available on policy form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Search URL on Policy Form", "Search URL is not available on policy form", Status.FAIL);
			}
			try {
				if (driver.findElement(transactionURL).isDisplayed()) {
					report.updateTestLog("Validation of Transaction URL on Policy Form", "Transaction URL  is available on policy form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Transaction URL on Policy Form", "Transaction URL is not available on policy form", Status.FAIL);
			}
			try {
				if (driver.findElement(region).isDisplayed()) {
					report.updateTestLog("Validation of Region on Policy Form", "Region  is available on policy form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Region on Policy Form", "Region is not available on policy form", Status.FAIL);
			}
			
			try {
				if (driver.findElement(account).isDisplayed()) {
					report.updateTestLog("Validation of Account on Policy Form", "Account  is available on policy form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Account on Policy Form", "Account is not available on policy form", Status.FAIL);
			}
			
			try {
				if (driver.findElement(contact).isDisplayed()) {
					report.updateTestLog("Validation of Contact on Policy Form", "Contact  is available on policy form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Contact on Policy Form", "Contact is not available on policy form", Status.FAIL);
			}
			try {
				if (driver.findElement(billAccount).isDisplayed()) {
					report.updateTestLog("Validation of Bill Account on Policy Form", "Bill Account  is available on policy form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Bill Account on Policy Form", "Bill Account is not available on policy form", Status.FAIL);
			}
			try {
				if (driver.findElement(effectiveDate).isDisplayed()) {
					report.updateTestLog("Validation of Effective Date on Policy Form", "Effective Date  is available on policy form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Effective Date on Policy Form", "Effective Date is not available on policy form", Status.FAIL);
			}
			try {
				if (driver.findElement(expirationDate).isDisplayed()) {
					report.updateTestLog("Validation of Expiration Date on Policy Form", "Expiration Date  is available on policy form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Expiration Date on Policy Form", "Expiration Date is not available on policy form", Status.FAIL);
			}
			
 }catch (Exception Ex) {
		report.updateTestLog("Summary Section validation on Policy form", "Exception Occured: " + Ex.getMessage(), Status.FAIL);
 }
	 
 }
 
 public void verifyPolicySummarySectionValues()
 {
	 try{
		 
		 String PolicyNumber = dataTable.getData("General_Data", "Policy");
			String EffectiveDate = dataTable.getData("General_Data", "EffectiveDate");
			String ExpirationDate = dataTable.getData("General_Data", "ExpirationDate");
			String BillAccount = dataTable.getData("General_Data", "BillAccount");
			String PolicyHolder = dataTable.getData("General_Data", "PolicyHolder");
			String CoInsured = dataTable.getData("General_Data", "CoInsured");
			String SearchURL = dataTable.getData("General_Data", "SearchURL");
			String TransactionURL = dataTable.getData("General_Data", "TransactionURL");
			String Region = dataTable.getData("General_Data", "Region");
			String Account = dataTable.getData("General_Data", "Account");
			String Contact = dataTable.getData("General_Data", "Contact");
			
		 reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), policyNumber);
			
			try {
				if (driver.findElement(By.xpath("//*[@title='"+PolicyNumber+"']")).isDisplayed()) {
					report.updateTestLog("Validation of Policy Number on Policy Form", "Policy Number  is available on policy form with values as:"+PolicyNumber, Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Policy Number on Policy Form", "Policy Number  is not available on policy form", Status.FAIL);
			}
			try {
				if (driver.findElement(By.xpath("//label[contains(text(),'"+PolicyHolder+"')]")).isDisplayed()) {
					report.updateTestLog("Validation of Policy Holder on Policy Form", "Policy Holder  is not available on policy form ", Status.FAIL);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Policy Holder on Policy Form", "Policy Holder is available on policy form with value as:"+PolicyHolder, Status.PASS);
			}
			try {
				if (driver.findElement(By.xpath("//*[@title='"+SearchURL+"']")).isDisplayed()) {
					report.updateTestLog("Validation of Search URL on Policy Form", "Search URL  is available on policy form with value as:"+SearchURL, Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Search URL on Policy Form", "Search URL is not available on policy form", Status.FAIL);
			}
			try {
				if (driver.findElement(By.xpath("//*[@title='"+TransactionURL+"']")).isDisplayed()) {
					report.updateTestLog("Validation of Transaction URL on Policy Form", "Transaction URL  is available on policy form with value as:"+TransactionURL, Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Transaction URL on Policy Form", "Transaction URL is not available on policy form", Status.FAIL);
			}
			try {
				if (driver.findElement(By.xpath("//*[contains(@title,'"+Region+"')]")).isDisplayed()) {
					report.updateTestLog("Validation of Region on Policy Form", "Region  is available on policy form with value as:"+Region, Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Region on Policy Form", "Region is not available on policy form", Status.FAIL);
			}
			
			try {
				if (driver.findElement(By.xpath("//*[@title='"+Account+"']")).isDisplayed()) {
					report.updateTestLog("Validation of Account on Policy Form", "Account  is available on policy form with value as:"+Account, Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Account on Policy Form", "Account is not available on policy form", Status.FAIL);
			}
			
			try {
				if (driver.findElement(By.xpath("//*[@title='"+Contact+"']")).isDisplayed()) {
					report.updateTestLog("Validation of Contact on Policy Form", "Contact  is available on policy form with value as:"+Contact, Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Contact on Policy Form", "Contact is not available on policy form", Status.FAIL);
			}
			try {
				if (driver.findElement(By.xpath("//*[@title='"+BillAccount+"']")).isDisplayed()) {
					report.updateTestLog("Validation of Bill Account on Policy Form", "Bill Account  is available on policy form with value as:"+BillAccount, Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Bill Account on Policy Form", "Bill Account is not available on policy form", Status.FAIL);
			}
			try {
				if (driver.findElement(By.xpath("//label[text()='"+EffectiveDate+"']")).isDisplayed()) {
					report.updateTestLog("Validation of Effective Date on Policy Form", "Effective Date  is available on policy form with value as:"+EffectiveDate, Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Effective Date on Policy Form", "Effective Date is not available on policy form", Status.FAIL);
			}
			try {
				if (driver.findElement(By.xpath("//label[text()='"+ExpirationDate+"']")).isDisplayed()) {
					report.updateTestLog("Validation of Expiration Date on Policy Form", "Expiration Date  is available on policy form with value as:"+ExpirationDate, Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Expiration Date on Policy Form", "Expiration Date is not available on policy form", Status.FAIL);
			}
			try {
				if (driver.findElement(By.xpath("//span[@id='cxp_coinsured_lookupValue' and text()='"+CoInsured+"']")).isDisplayed()) {
					report.updateTestLog("Validation of CoInsured on Policy Form", "CoInsured  is available on policy form with value as:"+CoInsured, Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of CoInsured on Policy Form", "CoInsured is not available on policy form", Status.FAIL);
			}
			
 }catch (Exception Ex) {
		report.updateTestLog("Summary Section validation on Policy form", "Exception Occured: " + Ex.getMessage(), Status.FAIL);
 }
	 
 }
 
 public void verifyPolicyAsReadOnly() {
		try {
			
			
			// Switch To Frame
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), readonly_policy);
		
			try {
				if (driver.findElement(readonly_policy).isDisplayed()) {
					report.updateTestLog("Validation of Policy as Read Only", "Policy form is Read only to User", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Policy as Read Only", "Policy form is not Read only to User", Status.FAIL);
			}
			
		} catch (Exception Ex) {
			report.updateTestLog("Validation of Policy as Read Only", "Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}
	}
 
 
 public void verifyAgencyDetailSection()
 {
	 try {
		 String AgencyName = dataTable.getData("General_Data", "AgencyName");
			String AgencyCode = dataTable.getData("General_Data", "AgencyCode");
			String PhoneNumber = dataTable.getData("General_Data", "PhoneNumber");
			String PresidentClub = dataTable.getData("General_Data", "PresidentClub");
			
			// Switch To Frame
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), agencyDetailSection);
		
			try {
				if (driver.findElement(agencyDetailSection).isDisplayed()) {
					report.updateTestLog("Validation of agency Detail Section on Policy", "Agency Detail Section is available on Policy form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of agency Detail Section on Policy", "Agency Detail Section is not  available on Policy form", Status.FAIL);
			}
			
			try {
				if (driver.findElement(agencyName).isDisplayed()) {
					report.updateTestLog("Validation of agency Name on Policy", "Agency Name is available on Policy form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of agency Name on Policy", "Agency Name is not  available on Policy form", Status.FAIL);
			}
			try {
				if (driver.findElement(agencyCode).isDisplayed()) {
					report.updateTestLog("Validation of agency Code on Policy", "Agency Code is available on Policy form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of agency Code on Policy", "Agency Code is not  available on Policy form", Status.FAIL);
			}
			
			try {
				if (driver.findElement(phoneNumber).isDisplayed()) {
					report.updateTestLog("Validation of Phone Number on Policy", "Phone Number is available on Policy form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Phone Number on Policy", "Phone Number is not  available on Policy form", Status.FAIL);
			}
			try {
				if (driver.findElement(presidentClub).isDisplayed()) {
					report.updateTestLog("Validation of President Club on Policy", "President Club is available on Policy form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of President Club on Policy", "President Club is not  available on Policy form", Status.FAIL);
			}
			try {
				if (driver.findElement(By.xpath("//*[@title='"+AgencyName+"']")).isDisplayed()) {
					report.updateTestLog("Validation of AgencyName on Policy Form", "AgencyName  is available on policy form with value as:"+AgencyName, Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of AgencyName on Policy Form", "AgencyName is not available on policy form", Status.FAIL);
			}
			try {
				if (driver.findElement(By.xpath("//*[@title='"+AgencyCode+"']")).isDisplayed()) {
					report.updateTestLog("Validation of AgencyCode on Policy Form", "AgencyCode  is available on policy form with value as:"+AgencyCode, Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of AgencyCode on Policy Form", "v is not available on policy form", Status.FAIL);
			}
			try {
				if (driver.findElement(By.xpath("//*[@title='"+PhoneNumber+"']")).isDisplayed()) {
					report.updateTestLog("Validation of PhoneNumber on Policy Form", "PhoneNumber  is available on policy form with value as:"+PhoneNumber, Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of PhoneNumber on Policy Form", "PhoneNumber is not available on policy form", Status.FAIL);
			}
			try {
				if (driver.findElement(By.xpath("//div[@id='cxp_agencylastyearpresidentsclub']//label[text()='"+PresidentClub+"']")).isDisplayed()) {
					report.updateTestLog("Validation of PresidentClub on Policy Form", "PresidentClub  is available on policy form with value as:"+PresidentClub, Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of PresidentClub on Policy Form", "PresidentClub is not available on policy form", Status.FAIL);
			}
			
		} catch (Exception Ex) {
			report.updateTestLog("Validation of Agency Details", "Exception Occured: " + Ex.getMessage(), Status.FAIL);
		} 
 }
 
 public void verifyUnderWriterDetailSection()
 {
	 try {
		 String UnderWriter = dataTable.getData("General_Data", "UnderWriter");
			String Email = dataTable.getData("General_Data", "Email");
			String PhoneNumberUW = dataTable.getData("General_Data", "PhoneNumberUW");
			String Region = dataTable.getData("General_Data", "Region");
			
			
			// Switch To Frame
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), UnderwriterDetailSection);
		if(Region!="Flood")
		{
			try {
				if (driver.findElement(UnderwriterDetailSection).isDisplayed()) {
					report.updateTestLog("Validation of UnderWriter Detail Section on Policy", "UnderWriter Detail Section is available on Policy form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of UnderWriter Detail Section on Policy", "UnderWriter Detail Section is not  available on Policy form", Status.PASS);
			}
			
			try {
				if (driver.findElement(Policies_MSCRM.UnderWriter).isDisplayed()) {
					report.updateTestLog("Validation of UnderWriter on Policy", "UnderWriter is available on Policy form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of UnderWriter on Policy", "UnderWriter is not  available on Policy form", Status.PASS);
			}
			try {
				if (driver.findElement(Policies_MSCRM.Email).isDisplayed()) {
					report.updateTestLog("Validation of Email on Policy", "Email is available on Policy form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Email on Policy", "Email is not  available on Policy form", Status.PASS);
			}
			
			try {
				if (driver.findElement(phoneNumberUW).isDisplayed()) {
					report.updateTestLog("Validation of UnderWriter Phone Number on Policy", "UnderWriter Phone Number is available on Policy form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of UnderWriter Phone Number on Policy", "UnderWriter Phone Number is not  available on Policy form", Status.PASS);
			}
			
			try {
				if (driver.findElement(By.xpath("//*[@title='"+UnderWriter+"']")).isDisplayed()) {
					report.updateTestLog("Validation of UnderWriter on Policy Form", "UnderWriter  is available on policy form with value as:"+UnderWriter, Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of UnderWriter on Policy Form", "UnderWriter is not available on policy form with required value as:"+UnderWriter, Status.FAIL);
			}
			try {
				if (driver.findElement(By.xpath("//div[@id='cxp_underwriteremail']//label[text()='"+Email+"']")).isDisplayed()) {
					report.updateTestLog("Validation of Email on Policy Form", "Email  is available on policy form with value as:"+Email, Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Email on Policy Form", "Email is not available on policy form with required value as:"+Email, Status.FAIL);
			}
			try {
				if (driver.findElement(By.xpath("//div[@id='cxp_underwriterphonenumber']//label[text()='"+PhoneNumberUW+"']")).isDisplayed()) {
					report.updateTestLog("Validation of UnderWriter PhoneNumber on Policy Form", "UnderWriter PhoneNumber  is available on policy form with value as:"+PhoneNumberUW, Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of UnderWriter PhoneNumber on Policy Form", "UnderWriter PhoneNumber is not available on policy form with required value as:"+PhoneNumberUW, Status.FAIL);
			}
		}else
		{
			report.updateTestLog("Validation of UnderWriter Details on Policy Form", "UnderWriter Details are not available on policy form  For Flood Policies", Status.PASS);
		}
			
		} catch (Exception Ex) {
			report.updateTestLog("Validation of UnderWriter details on Policy ", "Exception Occured: " + Ex.getMessage(), Status.FAIL);
		} 
	}

 
 public void verifyPolicyDetailSection()
 {
	 try {
		 String PayPlan = dataTable.getData("General_Data", "PayPlan");
			String CSSRegStatus = dataTable.getData("General_Data", "CSSRegStatus");
			
			
			
			// Switch To Frame
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), UnderwriterDetailSection);
		
			try {
				if (driver.findElement(policyDetailSection).isDisplayed()) {
					report.updateTestLog("Validation of Policy Detail Section on Policy", "Policy Detail Section is available on Policy form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Policy Detail Section on Policy", "Policy Detail Section is not  available on Policy form", Status.PASS);
			}
			
			try {
				if (driver.findElement(payPlan).isDisplayed()) {
					report.updateTestLog("Validation of PayPlan on Policy", "PayPlan is available on Policy form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of PayPlan on Policy", "PayPlan is not  available on Policy form", Status.PASS);
			}
			try {
				if (driver.findElement(Policies_MSCRM.CSSRegStatus).isDisplayed()) {
					report.updateTestLog("Validation of CSS Registration Status on Policy", "CSS Registration Status is available on Policy form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of CSS Registration Status on Policy", "CSS Registration Status is not  available on Policy form", Status.PASS);
			}
			
			
			try {
				if (driver.findElement(By.xpath("//*[@title='"+PayPlan+"']")).isDisplayed()) {
					report.updateTestLog("Validation of PayPlan on Policy Form", "PayPlan  is available on policy form with value as:"+PayPlan, Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of PayPlan on Policy Form", "PayPlan is not available on policy form", Status.FAIL);
			}
			try {
				if (driver.findElement(By.xpath("//*[@title='"+CSSRegStatus+"']")).isDisplayed()) {
					report.updateTestLog("Validation of CSSRegStatus on Policy Form", "CSSRegStatus  is available on policy form with value as:"+CSSRegStatus, Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of CSSRegStatus on Policy Form", "CSSRegStatus is not available on policy form", Status.FAIL);
			}
			
			
		} catch (Exception Ex) {
			report.updateTestLog("Validation of Policy Detail section on Policy form", "Exception Occured: " + Ex.getMessage(), Status.FAIL);
		} 
	}
 
 
 
 public void verifyClaimSection()
 {
	 try {
		 String ClaimNumber = dataTable.getData("General_Data", "ClaimNumber");
			String ClaimStatus = dataTable.getData("General_Data", "ClaimStatus");
			String LossDate = dataTable.getData("General_Data", "LossDate");
			String LossDescription = dataTable.getData("General_Data", "LossDescription");
			String Policy = dataTable.getData("General_Data", "Policy");
			
			
			
			
			// Switch To Frame
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), UnderwriterDetailSection);
		
			try {
				if (driver.findElement(claimNumber).isDisplayed()) {
					report.updateTestLog("Validation of claim Number on Policy under Claims Section", "claim Number is available on Policy form under Claims Section", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of claim Number  on Policy under Claims Section", "claim Number is not  available on Policy form under Claims Section", Status.PASS);
			}
			
			try {
				if (driver.findElement(claimStatus).isDisplayed()) {
					report.updateTestLog("Validation of claim Status on Policy under Claims Section", "claim Status is available on Policy form under Claims Section", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of claim Status on Policy under Claims Section", "claim Status is not  available on Policy form under Claims Section", Status.PASS);
			}
			try {
				if (driver.findElement(lossDate).isDisplayed()) {
					report.updateTestLog("Validation of Loss Date on Policy under Claims Section", "Loss Date is available on Policy form under Claims Section", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Loss Date on Policy under Claims Section", "Loss Date is not  available on Policy form under Claims Section", Status.PASS);
			}
			try {
				if (driver.findElement(lossDesc).isDisplayed()) {
					report.updateTestLog("Validation of Loss Description on Policy under Claims Section", "Loss Description is available on Policy form under Claims Section", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Loss Description on Policy under Claims Section", "Loss Description is not  available on Policy form under Claims Section", Status.PASS);
			}
			try {
				if (driver.findElement(LocationAddress_MSCRM.policy).isDisplayed()) {
					report.updateTestLog("Validation of Policy col on Policy under Claims Section", "Policy col is available on Policy form under Claims Section", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Policy col on Policy under Claims Section", "Policy col is not  available on Policy form under Claims Section", Status.PASS);
			}
			
			try {
				if (driver.findElement(By.xpath("//*[@title='"+ClaimNumber+"']")).isDisplayed()) {
					report.updateTestLog("Validation of ClaimNumber on Policy Form under Claims Section", "ClaimNumber  is available on policy form under Claims Section with value as:"+ClaimNumber, Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of ClaimNumber on Policy Form under Claims Section", "ClaimNumber is not available on policy form under Claims Section", Status.FAIL);
			}
			try {
				if (driver.findElement(By.xpath("//*[@title='"+ClaimStatus+"']")).isDisplayed()) {
					report.updateTestLog("Validation of ClaimStatus on Policy Form under Claims Section", "ClaimStatus  is available on policy form under Claims Section with value as:"+ClaimStatus, Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of ClaimStatus on Policy Form under Claims Section", "ClaimStatus is not available on policy form under Claims Section", Status.FAIL);
			}
			try {
				if (driver.findElement(By.xpath("//*[@title='"+LossDate+"']")).isDisplayed()) {
					report.updateTestLog("Validation of LossDate on Policy Form under Claims Section", "LossDate  is available on policy form under Claims Section with value as:"+LossDate, Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of LossDate on Policy Form under Claims Section", "LossDate is not available on policy form under Claims Section", Status.FAIL);
			}
			try {
				if (driver.findElement(By.xpath("//*[@title='"+LossDescription+"']")).isDisplayed()) {
					report.updateTestLog("Validation of LossDescription on Policy Form under Claims Section", "LossDescription  is available on policy form under Claims Section with value as:"+LossDescription, Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of LossDescription on Policy Form under Claims Section", "LossDescription is not available on policy form under Claims Section", Status.FAIL);
			}
			try {
				if (driver.findElement(By.xpath("//*[@title='"+Policy+"']")).isDisplayed()) {
					report.updateTestLog("Validation of Policy on Policy Form under Claims Section", "Policy  is available on policy form under Claims Section with value as:"+Policy, Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Policy on Policy Form under Claims Section", "Policy is not available on policy form under Claims Section", Status.FAIL);
			}
			
		} catch (Exception Ex) {
			report.updateTestLog("Validation of Policy Detail section on Policy form", "Exception Occured: " + Ex.getMessage(), Status.FAIL);
		} 
	}
 
 public  void verifyAgencyBilledPolicy() throws Exception {
	 try{
		String policyno = dataTable.getData("General_Data", "Policy");
		reusableFunc.navigationToEntity(driver.getWebDriver(), serviceModule, policiesEntity);
		//reusableFunc.waitForpageLoadByThread(driver.getWebDriver());
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='contentIFrame0']")));
		
		System.out.println("Switched to iframe 0");
		 
		 
		// if (reusableFunc.waitForElementToBePresent(driver.getWebDriver(), recordSearchBox, maxTimeOut)) {
		 
			report.updateTestLog("Validating Policy entity navigation",
					"Search Box Detected ", Status.PASS);
			WebElement element = driver.findElement(recordSearchBox);
			element.clear();
			element.sendKeys(policyno);
			element.sendKeys(Keys.ENTER);

			By recordXpath = By
					.xpath("//div[@id='crmGrid_divDataArea']//following::a[@title='" + policyno + "']");
			//By recordXpath = By
			//		.xpath("//nobr[@title='" + recordToSearch + "']");

			reusableFunc.waitForElementToBePresent(driver.getWebDriver(), recordXpath, maxTimeOut);
			
			driver.findElement(recordXpath).click();

			report.updateTestLog("Validating Policy sub-entity navigation",
					"Able to Navigate to policy Sub-entity and select the record." +policyno, Status.PASS);
			
		//	} 
	 	
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			reusableFunc.waitForpageLoadByThread(driver.getWebDriver());
			
			driver.switchTo().frame(driver.findElement(CRMPolicyPage.iframetitle));
			String AgencyBilledDetails =driver.findElement(CRMPolicyPage.agencyBilledField).getText();
			
			report.updateTestLog("Validation of Agency Billed Policy", "policy : "+policyno+" is Agency Billed = "+AgencyBilledDetails, Status.PASS);
		
	 }catch(Exception Ex){
		 report.updateTestLog("Validation of Agency Billed Policy", "Exception Occured: " + Ex.getMessage(), Status.FAIL);

	 }
 }
}
	
			
	

	

		
	
	
	
	
	
	
	
		