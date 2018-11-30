package businessComponents;

import pageObjects.*;
import com.cognizant.Craft.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.cognizant.framework.Status;


public class Marketing_MSCRM extends ReusableLibrary {
	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper
	 *            The {@link ScriptHelper} object passed from the
	 *            {@link DriverScript}
	 */
	WebElement element = null;

	public Marketing_MSCRM(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	reusableFunctions reusableFunc = new reusableFunctions(scriptHelper);
	
	/**
	 * navigateToLeads This method is used to navigate to lead entity
	 * 
	 * @throws InterruptedException
	 */
	public void navigateToLeads() throws InterruptedException {
		reusableFunc.navigationToEntity(driver.getWebDriver(), CRMLandingPage.marketingModule, CRMLandingPage.leadEntity);
		Thread.sleep(5000);
		try {
			if (driver.findElement(By.xpath("//a[@title='Select a view']//span[contains(text(),'Leads')]"))
					.isDisplayed()) {
				report.updateTestLog("Validating navigation To Lead Entity", "Successfully navigated to Lead Entity",
						Status.PASS);
				report.updateTestLog("Validating navigation To Lead Entity", "Successfully navigated to Lead Entity",
						Status.SCREENSHOT);
			}
		} catch (Exception e) {
			report.updateTestLog("Validating navigation To Lead Entity", "Successfully navigated to Lead Entity",
					Status.FAIL);
			report.updateTestLog("Validating navigation To Lead Entity", "Successfully navigated to Lead Entity",
					Status.SCREENSHOT);
		}
	}

	/**
	 * navigateToMarketingList This method is used to navigate to Marketing List
	 * entity
	 * 
	 * @throws InterruptedException
	 */

	public void navigateToMarketingList() throws InterruptedException {
		reusableFunc.navigationToEntity(driver.getWebDriver(), CRMLandingPage.marketingModule, CRMLandingPage.marketingListEntity);
		Thread.sleep(5000);
		try {
			if (driver.findElement(By.xpath("//a[@title='Select a view']//span[contains(text(),'Marketing List')]"))
					.isDisplayed()) {
				report.updateTestLog("Validating navigation To Marketing List Entity",
						"Successfully navigated to Marketing List Entity", Status.PASS);
				report.updateTestLog("Validating navigation To Marketing List Entity",
						"Successfully navigated to Marketing List Entity", Status.SCREENSHOT);
			}
		} catch (Exception e) {
			report.updateTestLog("Validating navigation To Marketing List Entity",
					"Successfully navigated to Marketing List Entity", Status.FAIL);
			report.updateTestLog("Validating navigation To Marketing List Entity",
					"Successfully navigated to Marketing List Entity", Status.SCREENSHOT);
		}
	}

	/**
	 * create_MarketingList This method is used to create to marketing list
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public void create_MarketingList() throws Exception {

		String MLName = dataTable.getData("General_Data", "MLName");
		String ListType = dataTable.getData("General_Data", "ListType");
		String TargatedAt = dataTable.getData("General_Data", "TargatedAt");
		String Source = dataTable.getData("General_Data", "Source");

		report.updateTestLog("Enter user credentials", "Specify " + "MarketingListname = " + MLName + ", "
				+ "ListType = " + ListType + ", " + "TargatedAt = " + TargatedAt + ", " + "Source = " + Source + ", ",
				Status.PASS);

		reusableFunc.navigationToEntity(driver.getWebDriver(), CRMLandingPage.marketingModule, CRMLandingPage.marketingListEntity);
		Thread.sleep(5000);
		report.updateTestLog("Marketing Entity", "Navigated to marketing list page", Status.SCREENSHOT);
		reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMNewAccountRecordPage.NewButtonClick);
		report.updateTestLog("Marketing Entity", "Click on new button", Status.SCREENSHOT);
		Thread.sleep(2000);

		UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.frame1);

		UI_Helpers_OOB.type_Stale(driver.getWebDriver(), MarketingListPage.NameML_enable, MarketingListPage.NameML_edit,
				MLName);
		report.updateTestLog("Marketing Entity", "Enter marketing List name", Status.SCREENSHOT);
		UI_Helpers_OOB.dropDownSelection_Stale(driver.getWebDriver(), MarketingListPage.TargatedAtML_enable,
				MarketingListPage.TargatedAtML_edit, TargatedAt);
		Thread.sleep(2000);
		report.updateTestLog("Marketing Entity", "Select Targated At value from dropdown", Status.SCREENSHOT);

		if ("ListType" == "static") {
			Thread.sleep(2000);
			report.updateTestLog("Marketing Entity", "Selected Type value from dropdown is static:", Status.SCREENSHOT);
		} else {
			driver.findElement(MarketingListPage.TypeML_enable).click();
			Thread.sleep(2000);

			report.updateTestLog("Marketing Entity", "Selected Type value from dropdown is Dynamic:",
					Status.SCREENSHOT);
		}

		Thread.sleep(2000);
		UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
		reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMActivityPage.activitySave);
		report.updateTestLog("Save", "Click the save button to create new appointment", Status.SCREENSHOT);
		Thread.sleep(5000);

		try {
			if (driver.findElement(By.xpath(MarketingListPage.ManageMemberbtn)).isDisplayed()) {
				report.updateTestLog("Validating ML", "Marketing List is created succssfully", Status.SCREENSHOT);
			}
		} catch (Exception e) {
			report.updateTestLog("Validating ML", "Marketing List is not  created succssfully", Status.SCREENSHOT);
		}
	}

}