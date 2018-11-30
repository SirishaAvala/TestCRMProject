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


public class LocationAddress_MSCRM extends ReusableLibrary {

	public LocationAddress_MSCRM(ScriptHelper scriptHelper) {
		super(scriptHelper);
		// TODO Auto-generated constructor stub
	}

	// Page Objects
	int maxTimeOut = 60;
	int minTimeOut = 10;
	reusableFunctions reusableFunc = new reusableFunctions(scriptHelper);
	Case_MSCRM caseModule = new Case_MSCRM(scriptHelper);
	Accounts_MSCRM accountClass = new Accounts_MSCRM(scriptHelper);
	Contacts_MSCRM contactClass = new Contacts_MSCRM(scriptHelper);

	public static final By serviceModule = By.xpath("//img[@alt='Service']//parent::span");
	public static final By locationAddressEntity = By.cssSelector("a#cxp_locationaddress");
	
	public static final By recordSearchBox = By.cssSelector("input#crmGrid_findCriteria");
	
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
	
    public static final By policy = By.xpath("//a[@title='Sort by Policy']");
    public static final By street1 = By.xpath("//a[@title='Sort by Street1']");
    public static final By street2 = By.xpath("//a[@title='Sort by Street2']");
    public static final By street3 = By.xpath("//a[@title='Sort by Street3']");
    public static final By city = By.xpath("//a[@title='Sort by City']");
    public static final By state = By.xpath("//a[@title='Sort by State']");
    public static final By postalCode = By.xpath("//a[@title='Sort by Postal Code']");
    public static final By country = By.xpath("//a[@title='Sort by Country']");
    public static final By locationAddressSection = By.xpath("//h2[text()='LOCATION ADDRESSES']");
	
	
	public void navigateToLocationAddressEntity() throws InterruptedException {
		driver.switchTo().defaultContent();
		reusableFunc.navigationToEntity(driver.getWebDriver(), serviceModule, locationAddressEntity);
		
		Thread.sleep(2000);
		try {
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), CRMCasePage.viewDropdown);
			if (reusableFunc.waitForElementToBePresent(driver.getWebDriver(), CRMCasePage.viewDropdown, maxTimeOut)) {
				report.updateTestLog("Validating Location Address entity navigation", "Able to Navigate to Location Address entity",
						Status.PASS);
			}
		} catch (Exception e) {
			report.updateTestLog("Validating Location Address entity navigation", "Not able to Navigate to Location Address entity",
					Status.FAIL);
		}

	}

	/*public void searchLocationAddressRelatedRecordsAndSelect() throws InterruptedException {
		try {
			
			String recordToSearch = dataTable.getData("General_Data", "recordToSearch");
			String Street1 = dataTable.getData("General_Data", "Street1");
			String viewName = dataTable.getData("General_Data", "viewName");
			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
			driver.switchTo().defaultContent();
			reusableFunc.navigationToEntity(driver.getWebDriver(), serviceModule, locationAddressEntity);
			
			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
			driver.navigate().refresh();
			
			UI_Helpers_OOB.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Iframe0);

			if (UI_Helpers_OOB.waitForElementToBeClickableBool(driver.getWebDriver(), recordSearchBox, maxTimeOut)) {
				WebElement element = driver.findElement(recordSearchBox);
				element.click();
				element.sendKeys(recordToSearch);
				element.sendKeys(Keys.ENTER);
				
				Thread.sleep(7000);

				By recordXpath = By
						.xpath("//div[@id='crmGrid_divDataArea']//following::a[@title='" + Street1 + "']");
				driver.switchTo().defaultContent();
			 reusableFunc.switchToObjectFrame(driver.getWebDriver(), recordXpath);

				UI_Helpers_OOB.waitForElementToBeClickableBool(driver.getWebDriver(), recordXpath, maxTimeOut);
				
				driver.findElement(recordXpath).click();

				By titleXpath = By.xpath("//h1[@title='" + Street1 + "']");
				UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.baseFrame1);

				if (UI_Helpers_OOB.waitForElementToBeVisible(driver.getWebDriver(), titleXpath, maxTimeOut)) {
					report.updateTestLog("Validating Location Address entity navigation",
							"Able to Navigate to Location Address entity and select the record.", Status.PASS);
				} else {
					report.updateTestLog("Validating Location Address entity navigation", "Not able to select the record.",
							Status.FAIL);
				}
				UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
			}
		} catch (Exception Ex) {
			report.updateTestLog("Validating Location Address entity navigation", "Exception Occured: " + Ex.getMessage(),
					Status.FAIL);
		}

	}*/

	public void searchLocationAddressRelatedRecords() throws InterruptedException {
		try {
		
		String locationAddress = dataTable.getData("General_Data","recordToSearch");
		String Street1 = dataTable.getData("General_Data","Street1");
		String LocAddName = dataTable.getData("General_Data","AddressName");
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
						.xpath("//div[@id='crmGrid_divDataArea']//following::a[contains(@title,'"+LocAddName+"')]");

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

				By titleXpath = By.xpath("//h1[contains(@title,'"+LocAddName+"')]");
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
	
	
	public void verifySummarySectionDetailsLocationAddressForm() {
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
			//caseModule.searchLocationAddressRelatedRecordsAndSelect(Street1);
			searchLocationAddressRelatedRecords();
	
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
				if (driver.findElement(By.xpath("//label[@id='Street1_label']/div")).isDisplayed()) 
				{
					report.updateTestLog("Validation Street1 on  Location Address", "Street1 is not available on Location Address form", Status.FAIL);
				}
			} catch (Exception e) {
				
				report.updateTestLog("Validation Street1 on  Location Address", "Street1 is available on Location Address form with value as :"+Street1, Status.PASS);
			}
			try {
				if (driver.findElement(By.xpath("//label[@id='Street2_label']/div")).isDisplayed()) 
				{
					report.updateTestLog("Validation Street2 on  Location Address", "Street2 is not available on Location Address form", Status.FAIL);
				}
			} catch (Exception e) {
				
				report.updateTestLog("Validation Street2 on  Location Address", "Street2 is available on Location Address form with value as :"+Street2, Status.PASS);
			}
			try {
				if (driver.findElement(By.xpath("//label[@id='Street3_label']/div")).isDisplayed()) 
				{
					report.updateTestLog("Validation Street3 on  Location Address", "Street3 is not available on Location Address form", Status.FAIL);
				}
			} catch (Exception e) {
				
				report.updateTestLog("Validation Street3 on  Location Address", "Street3 is available on Location Address form with value as :"+Street3, Status.PASS);
			}
			try {
				if (driver.findElement(By.xpath("//label[text()='"+City+"']")).isDisplayed()) 
				{
					report.updateTestLog("Validation City on  Location Address", "City is not available on Location Address form", Status.FAIL);
				}
			} catch (Exception e) {
				
				report.updateTestLog("Validation City on  Location Address", "City is available on Location Address form with value as :"+City, Status.PASS);
			}
			
			
			try {
				if (driver.findElement(By.xpath("//label[text()='"+Country+"']")).isDisplayed()) 
				{
					report.updateTestLog("Validation Country on  Location Address", "Country is not available on Location Address form", Status.FAIL);
				}
			} catch (Exception e) {
				
				report.updateTestLog("Validation Country on  Location Address", "Country is available on Location Address form with value as :"+Country, Status.PASS);
			}
			try {
				if (driver.findElement(By.xpath("//label[text()='"+State+"']")).isDisplayed()) 
				{
					report.updateTestLog("Validation State on  Location Address", "State is not available on Location Address form", Status.FAIL);
				}
			} catch (Exception e) {
				
				report.updateTestLog("Validation State on  Location Address", "State is available on Location Address form with value as :"+State, Status.PASS);
			}
			
			try {
				if (driver.findElement(By.xpath("//label[text()='"+Policy+"']")).isDisplayed()) 
				{
					report.updateTestLog("Validation Policy on  Location Address", "Policy is not available on Location Address form", Status.FAIL);
				}
			} catch (Exception e) {
				
				report.updateTestLog("Validation Policy on  Location Address", "Policy is available on Location Address form with value as :"+Policy, Status.PASS);
			}
			
			try {
				if (driver.findElement(By.xpath("//label[text()='"+PostalCode+"']")).isDisplayed()) 
				{
					report.updateTestLog("Validation PostalCode on  Location Address", "PostalCode is not available on Location Address form", Status.FAIL);
				}
			} catch (Exception e) {
				
				report.updateTestLog("Validation PostalCode on  Location Address", "PostalCode is available on Location Address form with value as :"+PostalCode, Status.PASS);
			}
			
			
		} catch (Exception Ex) {
			report.updateTestLog("Select View From Claims Entity", "Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}
	}

	public void verifyLocationAddressForm() {
		
		
		try {
			
			
			
			// Switch To Frame
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), street1Label);
		
			try {
				if (driver.findElement(street1Label).isDisplayed()) {
					report.updateTestLog("Validation of Street1 field on Location Address Form", "Street1 is available on location address form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Street1 field on Location Address Form", "Street1 is not available on location address form", Status.FAIL);
			}
			
			
			try {
				if (driver.findElement(street2Label).isDisplayed()) {
					report.updateTestLog("Validation of Street2 field on Location Address Form", "Street2 is available on location address form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Street2 field on Location Address Form", "Street2 is not available on location address form", Status.FAIL);
			}
			
			try {
				if (driver.findElement(street3Label).isDisplayed()) {
					report.updateTestLog("Validation of Street3 field on Location Address Form", "Street3 is available on location address form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Street3 field on Location Address Form", "Street3 is not available on location address form", Status.FAIL);
			}
			
			try {
				if (driver.findElement(cityLabel).isDisplayed()) {
					report.updateTestLog("Validation of City field on Location Address Form", "City is available on location address form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of City field on Location Address Form", "City is not available on location address form", Status.FAIL);
			}
			
			try {
				if (driver.findElement(stateLabel).isDisplayed()) {
					report.updateTestLog("Validation of State field on Location Address Form", "State is available on location address form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of State field on Location Address Form", "State is not available on location address form", Status.FAIL);
			}
			try {
				if (driver.findElement(countryLabel).isDisplayed()) {
					report.updateTestLog("Validation of Country field on Location Address Form", "Country is available on location address form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Country field on Location Address Form", "Country is not available on location address form", Status.FAIL);
			}
			try {
				if (driver.findElement(postalCodeLabel).isDisplayed()) {
					report.updateTestLog("Validation of Postal Code field on Location Address Form", "Postal Code is available on location address form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Postal Code field on Location Address Form", "Postal Code is not available on location address form", Status.FAIL);
			}
			
			try {
				if (driver.findElement(policyLabel).isDisplayed()) {
					report.updateTestLog("Validation of Policy field on Location Address Form", "Policy is available on location address form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Policy field on Location Address Form", "Policy is not available on location address form", Status.FAIL);
			}
			
			
			
		}catch (Exception Ex) {
			report.updateTestLog("Location Address form validation", "Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}
	}
	
	public void verifyLocationAddressView() {
		try {
			
			
			
			// Navigate to queue entity
			navigateToLocationAddressEntity();

			
			

			// Search for case
			UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
			UI_Helpers_OOB.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Iframe0);
			try{
			if (driver.findElement(policy).isDisplayed()) {
				report.updateTestLog("Validating Policy under Location Address view",
						"Policy is present under Location Address view ", Status.PASS);
			}
		} catch (Exception e) {
			report.updateTestLog("Validating Policy under Location Address view",
					"Policy is not present under Location Address view ", Status.FAIL);

		}
		try {
			if (driver.findElement(street1).isDisplayed()) {
				report.updateTestLog("Validating Street1 under Location Address view",
						"Street1 is present under Location Address view ", Status.PASS);
			}
		} catch (Exception e) {
			report.updateTestLog("Validating Street1 under Location Address view",
					"Street1 is not present under Location Address view ", Status.FAIL);

		}
		try {
			if (driver.findElement(street2).isDisplayed()) {
				report.updateTestLog("Validating Street2 under Location Address view",
						"Street2 is present under Location Address view ", Status.PASS);
			}
		} catch (Exception e) {
			report.updateTestLog("Validating Street2 under Location Address view",
					"Street2 is notpresent under Location Address view ", Status.FAIL);
		}
	
		try {
			if (driver.findElement(street3).isDisplayed()) {
				report.updateTestLog("Validating Street3 under Location Address view",
						"Street3 is present under Location Address view ", Status.PASS);
			}
		} catch (Exception e) {
			report.updateTestLog("Validating Street3 under Location Address view",
					"Street3 is notpresent under Location Address view ", Status.FAIL);
		}

		try {
			if (driver.findElement(city).isDisplayed()) {
				report.updateTestLog("Validating City under Location Address view",
						"City is present under Location Address view ", Status.PASS);
			}
		} catch (Exception e) {
			report.updateTestLog("Validating City under Location Address view",
					"City is notpresent under Location Address view ", Status.FAIL);
		}
		
		try {
			if (driver.findElement(state).isDisplayed()) {
				report.updateTestLog("Validating State under Location Address view",
						"State is present under Location Address view ", Status.PASS);
			}
		} catch (Exception e) {
			report.updateTestLog("Validating State under Location Address view",
					"State is not present under Location Address view ", Status.FAIL);
		}
		try {
			if (driver.findElement(postalCode).isDisplayed()) {
				report.updateTestLog("Validating Postal Code under Location Address view",
						"Postal Code is present under Location Address view ", Status.PASS);
			}
		} catch (Exception e) {
			report.updateTestLog("Validating Postal Code under Location Address view",
					"Postal Code is notpresent under Location Address view ", Status.FAIL);
		}
		try {
			if (driver.findElement(country).isDisplayed()) {
				report.updateTestLog("Validating Country under Location Address view",
						"Country is present under Location Address view ", Status.PASS);
			}
		} catch (Exception e) {
			report.updateTestLog("Validating Country under Location Address view",
					"Country is notpresent under Location Address view ", Status.FAIL);
		}
		

		} catch (Exception Ex) {
			report.updateTestLog("Validating Location Address View", "Exception Occured: " + Ex.getMessage(),
					Status.FAIL);
		}
	}
		
		
	public void verifyLocationAddressDetailsonOrganizationForm() {
		
		
		try {
			String Organization = dataTable.getData("General_Data", "Organization");
			
			accountClass.searchAccountRelatedRecordsAndSelect(Organization);
			
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_END);
			robot.keyRelease(KeyEvent.VK_END);
			Thread.sleep(5000);
			// Switch To Frame
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			//reusableFunc.switchToObjectFrame(driver.getWebDriver(), street1);
		
			try {
				if (driver.findElement(locationAddressSection).isDisplayed()) {
					report.updateTestLog("Validation of Location Address section on Organization Form", "Location Address Section is available on Organization Form", Status.FAIL);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Location Address section on Organization Form", "Location Address Section is not  available on Organization Form", Status.PASS);
			}
			
			
		}catch (Exception Ex) {
			report.updateTestLog("Location Address form validation", "Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}
	}

public void verifyLocationAddressDetailsonIndividualForm() {
		
		
		try {
			String Individual = dataTable.getData("General_Data", "Individual");
			
			contactClass.searchContactRelatedRecordsAndSelect(Individual);
			
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_END);
			robot.keyRelease(KeyEvent.VK_END);
			Thread.sleep(5000);
			// Switch To Frame
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			//reusableFunc.switchToObjectFrame(driver.getWebDriver(), street1);
		
			try {
				if (driver.findElement(locationAddressSection).isDisplayed()) {
					report.updateTestLog("Validation of Location Address section on Individual Form", "Location Address Section is available on Individual Form", Status.FAIL);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Location Address section on Individual Form", "Location Address Section is not  available on Individual Form", Status.PASS);
			}
			
			
		}catch (Exception Ex) {
			report.updateTestLog("Location Address form validation", "Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}
	}

	}


	
			
	

	

		
	
	
	
	
	
	
	
		