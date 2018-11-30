package businessComponents;

import pageObjects.*;
import com.cognizant.Craft.*;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.cognizant.framework.Status;


public class Campaign_MSCRM extends ReusableLibrary {
	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	WebElement element = null;

	public Campaign_MSCRM(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	reusableFunctions reusableFunc = new reusableFunctions(scriptHelper);
	
	/**
	 * navigateToCampaign This method is used to navigate to Campaign entity
	 * 
	 * @throws InterruptedException
	 */
	public void navigateToCampaign() throws InterruptedException {
		reusableFunc.navigationToEntity(driver.getWebDriver(), CRMLandingPage.marketingModule, CRMLandingPage.campaignsEntity);
		Thread.sleep(5000);
		try {
			if (driver.findElement(By.xpath("//a[@title='Select a view']//span[contains(text(),'Campaign')]"))
					.isDisplayed()) {
				report.updateTestLog("Validating navigation To Campaign Entity",
						"Successfully navigated to Campaign Entity", Status.PASS);
				report.updateTestLog("Validating navigation To Campaign Entity",
						"Successfully navigated to Campaign Entity", Status.SCREENSHOT);
			}
		} catch (Exception e) {
			report.updateTestLog("Validating navigation To Campaign Entity",
					"Successfully navigated to Campaign Entity", Status.FAIL);
			report.updateTestLog("Validating navigation To Campaign Entity",
					"Successfully navigated to Campaign Entity", Status.SCREENSHOT);
		}
	}

	/**
	 * navigateToQuickCampaign This method is used to navigate to Quick Campaign
	 * entity
	 * 
	 * @throws InterruptedException
	 */
	public void navigateToQuickCampaign() throws InterruptedException {
		reusableFunc.navigationToEntity(driver.getWebDriver(), CRMLandingPage.marketingModule, CRMLandingPage.quickCampaignsEntity);
		Thread.sleep(5000);

		try {
			if (driver.findElement(By.xpath("//a[@title='Select a view']//span[contains(text(),'Campaign')]"))
					.isDisplayed()) {
				report.updateTestLog("Validating navigation To Quick Campaign Entity",
						"Successfully navigated to Quick Campaign Entity", Status.PASS);
				report.updateTestLog("Validating navigation To Quick Campaign Entity",
						"Successfully navigated to Quick Campaign Entity", Status.SCREENSHOT);
			}
		} catch (Exception e) {
			report.updateTestLog("Validating navigation To Quick Campaign Entity",
					"Successfully navigated to Quick Campaign Entity", Status.FAIL);
			report.updateTestLog("Validating navigation To Quick Campaign Entity",
					"Successfully navigated to Quick Campaign Entity", Status.SCREENSHOT);
		}
	}

	/**
	 * create_Campaign This method is used to create new campaign
	 * 
	 * @throws Exception
	 */
	public void create_Campaign() throws Exception {

		String CampaignName = "CampTest" + UI_Helpers_OOB.getcurrenttime();

		report.updateTestLog("Enter user credentials", "Specify " + "CampaignName = " + CampaignName + ", ",
				Status.PASS);
		dataTable.putData("General_Data", "CampaignName", CampaignName);

		reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.NewButtonClick);
		report.updateTestLog("Campaign Entity", "Click on new button", Status.SCREENSHOT);
		Thread.sleep(2000);

		UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.frame1);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), MarketingListPage.NameCampaingn_enable,
				MarketingListPage.NameCampaingn_edit, CampaignName);
		report.updateTestLog("Campaign Entity", "Enter Campaign name", Status.SCREENSHOT);
		Thread.sleep(2000);
		UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());

		reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMActivityPage.activitySave);
		report.updateTestLog("Save", "Click the save button to create new appointment", Status.SCREENSHOT);
		Thread.sleep(5000);
		UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.frame1);

		try {
			if (driver.findElement(By.xpath(MarketingListPage.camapignCode)).isDisplayed()) {
				String CamapignCode = driver.findElement(By.xpath(MarketingListPage.camapignCode)).getText();
				dataTable.putData("General_Data", "CampaignCode", CamapignCode);
				report.updateTestLog("Validating Camapaign Code",
						"Campaign code is created succssfully as:" + CamapignCode, Status.PASS);
				report.updateTestLog("Validating Camapaign Code", "Campaign code is created succssfully",
						Status.SCREENSHOT);
			}
		} catch (Exception e) {
			report.updateTestLog("Validating Camapaign Code", "Campaign code is not created succssfully", Status.FAIL);
			report.updateTestLog("Validating Campaign", "Marketing List is not  created succssfully",
					Status.SCREENSHOT);
		}
	}

}
