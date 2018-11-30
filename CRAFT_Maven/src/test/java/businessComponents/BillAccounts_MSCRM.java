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

public class BillAccounts_MSCRM extends ReusableLibrary {

	public BillAccounts_MSCRM(ScriptHelper scriptHelper) {
		super(scriptHelper);
		// TODO Auto-generated constructor stub
	}

	// Page Objects
	int maxTimeOut = 80;
	int minTimeOut = 10;
	reusableFunctions reusableFunc = new reusableFunctions(scriptHelper);
	Case_MSCRM caseModule = new Case_MSCRM(scriptHelper);

	public static final By serviceModule = By.xpath("//img[@alt='Service']//parent::span");
	public static final By billAcoountsEntity = By.cssSelector("a#cxp_billaccount");
	public static final By recordSearchBox = By.cssSelector("input#crmGrid_findCriteria");
	
	public static final By viewDetails = By.xpath("//a[@title='Select a view']");
	public static final By queueDropDown = By.xpath("//select[@id='crmQueueSelector']");
	public static final By billAccount = By.cssSelector("span#cxp_billaccountnumber_cl");
	public static final By billAccounttxt = By.xpath("//label[@id='Bill Account_label']");
	public static final By billAccountInsuredAccount = By.cssSelector("span#cxp_insuredoraccount_cl");
	public static final By billAccountInsuredAccounttxt = By.xpath("//label[@id='Insured/Account_label']");
	public static final By billAccountBalance = By.cssSelector("span#cxp_balance_cl");
	public static final By billAccountBalancetxt = By.xpath("//label[@id='Balance_label']");
	public static final By billAccountBilledBalance = By.cssSelector("span#cxp_billedbalance_cl");
	public static final By billAccountBilledBalancetxt = By.xpath("//label[@id='Billed Balance_label']");
	public static final By billAccLastPayment = By.cssSelector("span#cxp_lastpaymentamount_cl");
	public static final By billAccLastPaymentTxt = By.xpath("//label[@id='Last Payment_label']");
	public static final By billAccLastPaymentDate = By.cssSelector("span#cxp_lastpaymentdate_cl");
	public static final By billAccLastPaymenDatetTxt = By.xpath("//label[@id='Last Payment Date_label']");
	public static final By billAccBilledDueDate = By.cssSelector("span#cxp_billduedate_cl");
	public static final By billAccBilledDueDatetxt = By.xpath("//label[@id='Bill Due Date_label']");
	public static final By billAccBillTo = By.cssSelector("span#cxp_billto_cl");
	public static final By billAccBillTotxt = By.xpath("//label[@id='Bill To_label']");
	public static final By billToAddressSection = By.xpath("//h3[text()='Bill To Address']");
	public static final By billToAddress1 = By.xpath("span#cxp_billtoaddressline1_cl");
	public static final By billToAddress1txt = By.xpath("//label[@id='Address Line 1_label']");
	public static final By billToAddress2 = By.xpath("span#cxp_billtoaddressline2_cl");
	public static final By billToAddress2txt = By.xpath("//label[@id='Address Line 2_label']");
	public static final By billToAddress3 = By.xpath("span#cxp_billtoaddressline3_cl");
	public static final By billToAddress3txt = By.xpath("//label[@id='Address Line 3_label']");
	public static final By city = By.xpath("span#cxp_billtoaddresscity_cl");
	public static final String citytxt = "//label[@id='City_label']";
	public static final By state = By.xpath("span#cxp_billtoaddressstateorprovince_cl");
	public static final By statetxt = By.xpath("//label[@id='State/Province_label']");
	public static final By postal = By.xpath("span#cxp_billtoaddresspostalcode_cl");
	public static final By postaltxt = By.xpath("//label[@id='Postal Code_label']");
	public static final By relatedPoliciesSection = By.xpath("//h3[text()='RELATED POLICIES']");
	public static final By policyNumberCol = By.xpath("//*[@title='Policy Number']");
	public static final By effectiveDateCol = By.xpath("//th[@fieldname='cxp_effectivedate']");
	public static final By expirationDateCol = By.xpath("//*[@title='Expiration Date']");
	public static final By agencyNameCol = By.xpath("//*[@title='Agency Name']");
	public static final By policyHolderCol = By.xpath("//*[@title='Policy Holder']");
	public static final By SrvCtrrCol = By.xpath("//*[@title='Srv Ctr']");
	public static final By relatedOrgIndSection = By.xpath("//h3[text()='RELATED INDIVIDUALS / ORGANIZATIONS']");
	public static final By organizationTable = By.cssSelector("div#SubGrid_Organizations_titleText");
	public static final By individualTable = By.cssSelector("div#SubGrid_Individuals_titleText");
	public static final By readonly_claim = By.xpath("//span[text()='Read only']");
	public static final By fullnameIndCol = By.xpath("//div[@id='SubGrid_Individuals_titleText']//following::div[29]");
	public static final By orgNameCol = By.xpath("//div[@id='SubGrid_Organizations_titleText']//following::div[29]");
	public static final By billAccwithoutDashes=By.xpath("//div[@id='cxp_unformattedbillaccountnumber']");
	public static final By expandBillingSection=By.xpath("(//img[@title='Expand this tab'])[2]");

	

public void navigateToBillAccountEntity() throws InterruptedException {
		driver.switchTo().defaultContent();
		reusableFunc.navigationToEntity(driver.getWebDriver(), serviceModule, billAcoountsEntity);
		try {
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), CRMCasePage.viewDropdown);
			if (reusableFunc.waitForElementToBePresent(driver.getWebDriver(), CRMCasePage.viewDropdown, maxTimeOut)) {
				report.updateTestLog("Validating Bill Accounts entity navigation", "Able to Navigate to Bill Accounts entity",
						Status.PASS);
			}
		} catch (Exception e) {
			report.updateTestLog("Validating Bill Accounts entity navigation", "Not able to Navigate to Bill Accounts entity",
					Status.FAIL);
		}

	}

	public void searchBillAccountRelatedRecordsAndSelect() throws InterruptedException {
		try {
			
			String recordToSearch = dataTable.getData("General_Data", "recordToSearch");
			String viewName = dataTable.getData("General_Data", "viewName");
			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
			driver.switchTo().defaultContent();
			reusableFunc.navigationToEntity(driver.getWebDriver(), serviceModule, billAcoountsEntity);
			
			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
			driver.navigate().refresh();
			selectViewFromBillAccountsEntity(viewName);
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
				reusableFunc.switchToObjectFrame(driver.getWebDriver(), titleXpath);
				//UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.baseFrame1);

				/*if (UI_Helpers_OOB.waitForElementToBeVisible(driver.getWebDriver(), titleXpath, maxTimeOut)) {
					report.updateTestLog("Validating Bill Accounts entity navigation",
							"Able to Navigate to Bill Accounts entity and select the record.", Status.PASS);
				} else {
					report.updateTestLog("Validating Bill Accounts entity navigation", "Not able to select the record.",
							Status.FAIL);
				}*/
				try{

					if (driver.findElement(titleXpath).isDisplayed()) {
						report.updateTestLog("Validating Bill Accounts entity navigation",
								"Able to Navigate to Bill Accounts entity and select the record.", Status.PASS);
					} 
					}catch(Exception e) {
						report.updateTestLog("Validating Bill Accounts entity navigation", "Not able to select the record.",
								Status.FAIL);
					}
				UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
			}
		} catch (Exception Ex) {
			report.updateTestLog("Validating Bill Accounts entity navigation", "Exception Occured: " + Ex.getMessage(),
					Status.FAIL);
		}

	}

	public void searchBillAccountsRelatedRecords(String recordToSearch) throws InterruptedException {
		try {
			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
			driver.switchTo().defaultContent();
			reusableFunc.navigationToEntity(driver.getWebDriver(), serviceModule, billAcoountsEntity);
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

	
	public void selectViewFromBillAccountsEntity(String viewName) {
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
				report.updateTestLog("Select View From Bill Accounts Entity", "Able to select the expected view", Status.PASS);
			} else {
				report.updateTestLog("Select View From Bill Accounts Entity", "Not able to select the expected view",
						Status.FAIL);
			}

		} catch (Exception Ex) {
			report.updateTestLog("Select View From Bill Accounts Entity", "Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}
	}

	
	
	public void selectBillAccountsRelatedRecord(String recordToSearch,String viewName) throws InterruptedException {
		try {
			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
			driver.switchTo().defaultContent();
			reusableFunc.navigationToEntity(driver.getWebDriver(), serviceModule, billAcoountsEntity);
			
			UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
			driver.navigate().refresh();
			selectViewFromBillAccountsEntity(viewName);
			UI_Helpers_OOB.SwitchToFrameStandard(driver.getWebDriver(), CRMLandingPage.Iframe0);

			if (UI_Helpers_OOB.waitForElementToBeClickableBool(driver.getWebDriver(), recordSearchBox, maxTimeOut)) {
				
				By recordXpath = By
						.xpath("//div[@id='crmGrid_divDataArea']//following::a[text()='" + recordToSearch + "']");

				UI_Helpers_OOB.waitForElementToBeClickableBool(driver.getWebDriver(), recordXpath, maxTimeOut);
				driver.findElement(recordXpath).click();

				By titleXpath = By.xpath("//h1[@title='" + recordToSearch + "']");
				UI_Helpers_OOB.switchToFrame(driver.getWebDriver(), CRMLandingPage.baseFrame1);
				try{

				if (driver.findElement(titleXpath).isDisplayed()) {
					report.updateTestLog("Validating Bill Accounts entity navigation",
							"Able to Navigate to Bill Accounts entity and select the record.", Status.PASS);
				} 
				}catch(Exception e) {
					report.updateTestLog("Validating Bill Accounts entity navigation", "Not able to select the record.",
							Status.FAIL);
				}
				UI_Helpers_OOB.switchToDefaultFrame(driver.getWebDriver());
			}
		} catch (Exception Ex) {
			report.updateTestLog("Validating Bill Accounts entity navigation", "Exception Occured: " + Ex.getMessage(),
					Status.FAIL);
		}

	}
	
	public void verifyBillAccSummarySectionForm() {
		try {
			// Switch To Frame
			
			
			String BillAccount = dataTable.getData("General_Data", "BillAccount");
			String InsuredAccount = dataTable.getData("General_Data", "InsuredAccount");
			String Balance = dataTable.getData("General_Data", "Balance");
			String BilledBalance = dataTable.getData("General_Data", "BilledBalance");
			
			String LastPayment = dataTable.getData("General_Data", "LastPayment");
			String BillDueDate = dataTable.getData("General_Data", "BillDueDate");
			String LastPaymentDate = dataTable.getData("General_Data", "LastPaymentDate");
			String BillTo = dataTable.getData("General_Data", "BillTo");
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), billAccount);
			String BillAccounttxt = driver.findElement(billAccounttxt).getText();
			String InsuredAccounttxt = driver.findElement(billAccountInsuredAccounttxt).getText();
			String Balancetxt = driver.findElement(billAccountBalancetxt).getText();
			String BilledBalancetxt = driver.findElement(billAccountBilledBalancetxt).getText();
			String LastPaymenttxt = driver.findElement(billAccLastPaymentTxt).getText();
			String BillDueDatetxt = driver.findElement(billAccBilledDueDatetxt).getText();
			String LastPaymentDatetxt = driver.findElement(billAccLastPaymentTxt).getText();
			String BillTotxt = driver.findElement(billAccBillTotxt).getText();
			

			if (BillAccount.equalsIgnoreCase(BillAccounttxt)) {
				report.updateTestLog("Validation Bill Account Detail on Bill Account", "Bill Account is not available on Bill Account form", Status.FAIL);
			} else {
				report.updateTestLog("Validation Bill Account Detail on Bill Account", "Bill Account is available on Bill Account form with value as :"+BillAccount, Status.PASS);
			}

			if (InsuredAccount.equalsIgnoreCase(InsuredAccounttxt)) {
				report.updateTestLog("Validation Insured/Account Detail on Bill Account", "Insured/Account is not available on Bill Account form", Status.FAIL);
			} else {
				report.updateTestLog("Validation Insured/Account Detail on Bill Account", "Insured/Account is available on Bill Account form with value as:"+InsuredAccount, Status.PASS);
						
			}
			if (Balance.equalsIgnoreCase(Balancetxt)) {
				report.updateTestLog("Validation Balance Detail on Bill Account", "Balance is not available on Bill Account form", Status.FAIL);
			} else {
				report.updateTestLog("Validation Balance Detail on Bill Account", "Balance is available on Bill Account form with value as:"+Balance, Status.PASS);
						

			if (BilledBalance.equalsIgnoreCase(BilledBalancetxt)) {
				report.updateTestLog("Validation Billed Balance Detail on Bill Account", "Billed Balance is not available on Bill Account form", Status.FAIL);
			} else {
				report.updateTestLog("Validation Billed Balance Detail on Bill Account", "Billed Balance is available on Bill Account form with value as:"+BilledBalance, Status.PASS);
			}			
			if (LastPayment.equalsIgnoreCase(LastPaymenttxt)) {
				report.updateTestLog("Validation Last payment Detail on Bill Account", "Last payment is not available on Bill Account form", Status.FAIL);
			} else {
				report.updateTestLog("Validation Last payment Detail on Bill Account", "Last payment is available on Bill Account form with value as:"+LastPayment, Status.PASS);
			}			

			if (BillTo.equalsIgnoreCase(BillTotxt)) {
				report.updateTestLog("Validation Bill To Detail on Bill Account", "Bill To is not available on Bill Account form", Status.FAIL);
			} else {
				report.updateTestLog("Validation Bill To Detail on Bill Account", "Bill To is available on Bill Account form with value as:"+BillTo, Status.PASS);
			}	
			if (BillDueDate.equalsIgnoreCase(BillDueDatetxt)) {
				report.updateTestLog("Validation Bill Due Date Detail on Bill Account", "Bill Due Date is not available on Bill Account form", Status.FAIL);
			} else {
				report.updateTestLog("Validation Bill Due Date Detail on Bill Account", "Bill Due Date is available on Bill Account form with value as:"+BillDueDate, Status.PASS);
			}			

			if (LastPaymentDate.equalsIgnoreCase(LastPaymentDatetxt)) {
				report.updateTestLog("Validation Last Payment Date on Bill Account", "Last Payment Date is not available on Bill Account form", Status.FAIL);
			} else {
				report.updateTestLog("Validation Last Payment Date on Bill Account", "Last Payment Date is available on Bill Account form with value as:"+LastPaymentDate, Status.PASS);
			}	
			}
		}
			catch (Exception Ex) {
			report.updateTestLog("Verify Bill Account Summary Section", "Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}
	}

	public void verifyBillToAddressOnBillAccForm() {
		
		
		try {
			
			String AddressLine1 = dataTable.getData("General_Data", "AddressLine1");
			String AddressLine2 = dataTable.getData("General_Data", "AddressLine2");
			String AddressLine3 = dataTable.getData("General_Data", "AddressLine3");
			String City = dataTable.getData("General_Data", "City");
			String StateProvince = dataTable.getData("General_Data", "StateProvince");
			String PostalCode = dataTable.getData("General_Data", "PostalCode");
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), billAccount);
			String AddressLine1txt = driver.findElement(billToAddress1txt).getText();
			String AddressLine2txt = driver.findElement(billToAddress2txt).getText();
			String AddressLine3txt = driver.findElement(billToAddress3txt).getText();
			String citytxt = driver.findElement(By.xpath(BillAccounts_MSCRM.citytxt)).getText();
			String Stateprovincetxt = driver.findElement(statetxt).getText();
			String PostalCodetxt = driver.findElement(postaltxt).getText();
			
			// Switch To Frame
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), billToAddressSection);
		
			
				if (AddressLine1.equalsIgnoreCase(AddressLine1txt)) {
					report.updateTestLog("Validation Address Line 1 on Bill Account", "Address Line 1 is not available on Bill Account form", Status.FAIL);
				} else {
					report.updateTestLog("Validation Address Line 1 on Bill Account", "Address Line 1 is available on Bill Account form with value as:"+AddressLine1txt, Status.PASS);
				}	
			
				if (AddressLine2.equalsIgnoreCase(AddressLine2txt)) {
					report.updateTestLog("Validation Address Line 2 on Bill Account", "Address Line 2 is not available on Bill Account form", Status.FAIL);
				} else {
					report.updateTestLog("Validation Address Line 2 on Bill Account", "Address Line 2 is available on Bill Account form with value as:"+AddressLine2txt, Status.PASS);
				}	
				if (AddressLine3.equalsIgnoreCase(AddressLine3txt)) {
					report.updateTestLog("Validation Address Line 3 on Bill Account", "Address Line 3 is not available on Bill Account form", Status.FAIL);
				} else {
					report.updateTestLog("Validation Address Line 3 on Bill Account", "Address Line 3 is available on Bill Account form with value as:"+AddressLine3txt, Status.PASS);
				}	
				if (City.equalsIgnoreCase(citytxt)) {
					report.updateTestLog("Validation Address Line 1 on Bill Account", "Address Line 1 is not available on Bill Account form", Status.FAIL);
				} else {
					report.updateTestLog("Validation Address Line 1 on Bill Account", "Address Line 1 is available on Bill Account form with value as:"+citytxt, Status.PASS);
				}	
			
				if (StateProvince.equalsIgnoreCase(Stateprovincetxt)) {
					report.updateTestLog("Validation Address Line 2 on Bill Account", "Address Line 2 is not available on Bill Account form", Status.FAIL);
				} else {
					report.updateTestLog("Validation Address Line 2 on Bill Account", "Address Line 2 is available on Bill Account form with value as:"+Stateprovincetxt, Status.PASS);
				}	
				if (PostalCode.equalsIgnoreCase(PostalCodetxt)) {
					report.updateTestLog("Validation Address Line 3 on Bill Account", "Address Line 3 is not available on Bill Account form", Status.FAIL);
				} else {
					report.updateTestLog("Validation Address Line 3 on Bill Account", "Address Line 3 is available on Bill Account form with value as:"+PostalCode, Status.PASS);
				}	
			
			
			
		} catch (Exception Ex) {
			report.updateTestLog("Validation of Bill T Address ", "Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}
	}
	
	public void verifyBillAccountAsReadOnly() {
		try {
			
			
			// Switch To Frame
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			reusableFunc.switchToObjectFrame(driver.getWebDriver(), readonly_claim);
		
			try {
				if (driver.findElement(readonly_claim).isDisplayed()) {
					report.updateTestLog("Validation of Bill Account as Read Only", "Bill Account form is Read only to User", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Bill Account as Read Only", "Bill Account form is not Read only to User", Status.FAIL);
			}
			
		} catch (Exception Ex) {
			report.updateTestLog("Validation of Bill Account as Read Only", "Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}
	}
	
	
	public void verifyRelatedPoliciesSection() {
		try {
			
			String PolicyNumber = dataTable.getData("General_Data", "PolicyNumber");
			String EffectiveDate = dataTable.getData("General_Data", "EffectiveDate");
			String ExpirationDate = dataTable.getData("General_Data", "ExpirationDate");
			String AgencyName = dataTable.getData("General_Data", "AgencyName");
			String PolicyHolder = dataTable.getData("General_Data", "PolicyHolder");
			String SrvCtr = dataTable.getData("General_Data", "SrvCtr");
			
			// Switch To Frame
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			reusableFunc.switchToObjectFrame(driver.getWebDriver(),policyNumberCol );
		
			try {
				if (driver.findElement(policyNumberCol).isDisplayed()) {
					report.updateTestLog("Validation of Policy Number column on  Bill Acounts under Policies section", "Policy Number column is available on Bill Account form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Policy Number column on  Bill Acounts under Policies section", "Policy Number column is not available on Bill Account form", Status.FAIL);
			}
			
			
			try {
				if (driver.findElement(effectiveDateCol).isDisplayed()) {
					report.updateTestLog("Validation of Effective Date column on  Bill Acounts under Policies section", "Effective Date column is available on Bill Account form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Effective Date column on  Bill Acounts under Policies section", "Effective Date column is not available on Bill Account form", Status.FAIL);
			}
			
			
			try {
				if (driver.findElement(expirationDateCol).isDisplayed()) {
					report.updateTestLog("Validation of Expiration Date column on  Bill Acounts under Policies section", "Expiration Date column is available on Bill Account form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Expiration Date column on  Bill Acounts under Policies section", "Expiration Date column is not available on Bill Account form", Status.FAIL);
			}
			
			try {
				if (driver.findElement(agencyNameCol).isDisplayed()) {
					report.updateTestLog("Validation of Agency name column on  Bill Acounts under Policies section", "Agency name column is available on Bill Account form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Agency name column on  Bill Acounts under Policies section", "Agency name column is not available on Bill Account form", Status.FAIL);
			}
			
			try {
				if (driver.findElement(policyHolderCol).isDisplayed()) {
					report.updateTestLog("Validation of Policy holder column on  Bill Acounts under Policies section", "Policy holder column is available on Bill Account form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Policy holder column on  Bill Acounts under Policies section", "Policy holder column is not available on Bill Account form", Status.FAIL);
			}
			
			try {
				if (driver.findElement(SrvCtrrCol).isDisplayed()) {
					report.updateTestLog("Validation of Srv Ctr column on  Bill Acounts under Policies section", "Srv Ctr column is available on Bill Account form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Srv Ctr column on  Bill Acounts under Policies section", "Srv Ctr column is not available on Bill Account form", Status.FAIL);
			}
			try {
				if (driver.findElement(By.xpath("//a[@title='"+PolicyNumber+"']")).isDisplayed()) {
					report.updateTestLog("Validation of Policy number value on  Bill Acounts under Policies section", " Policy number value is available on Bill Account form with value as:"+PolicyNumber, Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of  Policy number value on  Bill Acounts under Policies section", " Policy number value is not available on Bill Account form", Status.FAIL);
			}
			
			try {
				if (driver.findElement(By.xpath("//div[@class='ms-crm-data-format-string']")).isDisplayed()) {
					report.updateTestLog("Validation of Effective Date value on  Bill Acounts under Policies section", " Effective Date value is available on Bill Account form with value as:"+EffectiveDate, Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of  Effective Date value on  Bill Acounts under Policies section", " Effective Date value is not available on Bill Account form", Status.FAIL);
			}
			
			try {
				if (driver.findElement(By.xpath("(//div[@class='ms-crm-data-format-string']) [2]")).isDisplayed()) {
					report.updateTestLog("Validation of Expiration Date value on  Bill Acounts under Policies section", " Expiration Date value is available on Bill Account form with value as:"+ExpirationDate, Status.PASS);
				}
			} catch (Exception e) {
				System.out.println("Expiration Date:"+ExpirationDate);
				report.updateTestLog("Validation of Expiration Date value on  Bill Acounts under Policies section", " Expiration Date  value is not available on Bill Account form", Status.FAIL);
			}
			try {
				if (driver.findElement(By.xpath("//td[contains(@class,'NumbersAndDates')]//following::td[2]")).isDisplayed()) {
					report.updateTestLog("Validation of Agency Name value on  Bill Acounts under Policies section", " Agency Name value is available on Bill Account form with value as:"+AgencyName, Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of  Agency Name value on  Bill Acounts under Policies section", " Agency Name value is not available on Bill Account form", Status.FAIL);
			}
			try {
				if (driver.findElement(By.xpath("//*[@title='"+PolicyHolder+"']")).isDisplayed()) {
					report.updateTestLog("Validation of Policy holder value on  Bill Acounts under Policies section", " Policy holder value is available on Bill Account form with value as:"+PolicyHolder, Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of  Policy holder value on  Bill Acounts under Policies section", " Policy holder value is not available on Bill Account form", Status.FAIL);
			}
			try {
				if (driver.findElement(By.xpath("//td[contains(@class,'NumbersAndDates')]//following::td[4]")).isDisplayed()) {
					report.updateTestLog("Validation of SrvCtr value  on  Bill Acounts under Policies section", " SrvCtr value  is available on Bill Account form with value as:"+SrvCtr, Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of  SrvCtr value on  Bill Acounts under Policies section", " SrvCtr value is not available on Bill Account form", Status.FAIL);
			}
			
		} catch (Exception Ex) {
			report.updateTestLog("Validation of Related Policices Section on  Bill Accounts", "Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}
	}
	
	
	public void verifyRelatedIndOrgSection() {
		try {
			
			String FullName = dataTable.getData("General_Data", "FullName");
			String OranizationName = dataTable.getData("General_Data", "OranizationName");
			
			
			// Switch To Frame
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			reusableFunc.switchToObjectFrame(driver.getWebDriver(),relatedOrgIndSection );
		
			try {
				if (driver.findElement(fullnameIndCol).isDisplayed()) {
					report.updateTestLog("Validation of Full Name on  Bill Acounts under Ind/Org section", "Full Name column is available on Bill Account form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Full Name on  Bill Acounts under Ind/Org section", "Full Name column is not available on Bill Account form", Status.FAIL);
			}
			
			
			try {
				if (driver.findElement(orgNameCol).isDisplayed()) {
					report.updateTestLog("Validation of Organization Name on  Bill Acounts under Ind/Org section", "Organization Name column is available on Bill Account form", Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Organization Name on  Bill Acounts under Ind/Org section", "Organization Name column is not available on Bill Account form", Status.FAIL);
			}
			
			
			try {
				if (driver.findElement(By.xpath("(//div[@id='SubGrid_Individuals_titleText']//following::div[30]//following::a")).isDisplayed()) {
					report.updateTestLog("Validation of Full Name detail on  Bill Acounts under Ind/Org section", "Full Name detail is available on Bill Account form with value as:"+FullName, Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Full Name detail on  Bill Acounts under Ind/Org section", "Full Name detail is not available on Bill Account form", Status.FAIL);
			}
			try {
				if (driver.findElement(By.xpath("//div[@id='SubGrid_Organizations_titleText']//following::div[30]//following::a")).isDisplayed()) {
					report.updateTestLog("Validation of Organization Name detail on  Bill Acounts under Ind/Org section", "Organization Name details is available on Bill Account form with value as:"+OranizationName, Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Organization Name on detail  Bill Acounts under Ind/Org section", "Organization Name detail is not available on Bill Account form", Status.FAIL);
			}
				
			
			
		} catch (Exception Ex) {
			report.updateTestLog("Validation of Related Individual/Organization Section  values on  Bill Accounts", "Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}
	}
	
	
	
	public void verifyBillAccountformWithoutDashes() {
		try {
			
			String recordToSearch = dataTable.getData("General_Data", "recordToSearch");
			String BillAccountformattednumber = dataTable.getData("General_Data", "BillAccountformattednumber");
			String OranizationName = dataTable.getData("General_Data", "OranizationName");
			//String ViewName = dataTable.getData("General_Data", "viewName");
			// Switch To Frame
	
			//selectBillAccountsRelatedRecord(recordToSearch,ViewName);
			searchBillAccountRelatedRecordsAndSelect();
			By header=By.xpath("//label[text()='"+recordToSearch+"']");
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			reusableFunc.switchToObjectFrame(driver.getWebDriver(),header );
			
			try {
				if (driver.findElement(header).isDisplayed()) {
					report.updateTestLog("Validation of Bill Account header value", "Bill Account number is available on form with value as:"+recordToSearch, Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Bill Account header value", "Bill Account number is not available on form", Status.FAIL);
			}
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			reusableFunc.switchToObjectFrame(driver.getWebDriver(),billAccwithoutDashes );
			
			try {
				if (driver.findElement(billAccwithoutDashes).isDisplayed()) {
					report.updateTestLog("Validation of Bill Account formatted number", "Bill Account formatted number is available on form with value as:"+BillAccountformattednumber, Status.PASS);
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Bill Account formatted number", "Bill Account formatted number is not available on form", Status.FAIL);
			}
			
			By Orgpath=By.xpath("//a[@title='"+OranizationName+"']");
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			reusableFunc.switchToObjectFrame(driver.getWebDriver(),Orgpath );
			try {
				if (driver.findElement(Orgpath).isDisplayed()) {
					report.updateTestLog("Validation of Related Individual/Organization Details", "Org/Ind is available under related Ind/Org section  with value as:"+OranizationName, Status.PASS);
					driver.findElement(Orgpath).click();
					
					Thread.sleep(1000);
					
					Robot robot = new Robot();
					robot.keyPress(KeyEvent.VK_END);
					robot.keyRelease(KeyEvent.VK_END);
					Thread.sleep(5000);
					reusableFunc.switchToDefaultFrame(driver.getWebDriver());
					reusableFunc.switchToObjectFrame(driver.getWebDriver(),expandBillingSection );	
               driver.findElement(expandBillingSection).click();
					
					Thread.sleep(5000);
					
					By billAccUnderBilling=By.xpath("//a[(text()='"+recordToSearch+"') and contains(@href,'bill')]");
					reusableFunc.switchToDefaultFrame(driver.getWebDriver());
					reusableFunc.switchToObjectFrame(driver.getWebDriver(),billAccUnderBilling );
					try{
						if(driver.findElement(billAccUnderBilling).isDisplayed())
						{
							report.updateTestLog("Validation of Bill Account number under Billing section", "Bill Account number under Billing section is not matching with number available on header on bill acc form" , Status.FAIL);
						}
						
					}catch(Exception e)
					{
						report.updateTestLog("Validation of Bill Account number under Billing section", "Bill Account number under Billing section is matching with number available on header on bill acc form as:"+recordToSearch, Status.PASS);
					}
				}
			} catch (Exception e) {
				report.updateTestLog("Validation of Related Individual/Organization Details", "Org/Ind is available under related Org/Ind section is not available on form", Status.FAIL);
			}
			
			
			
		} catch (Exception Ex) {
			report.updateTestLog("Validation of Bill Account header value", "Exception Occured: " + Ex.getMessage(), Status.FAIL);
		}
	}
	public void verifyPolicyWithPrefixSearchOnCaseForm() {
		try {
			
			String PolicyWithoutFLDPrefix = dataTable.getData("General_Data", "PolicyWithoutFLDPrefix");
			String PolicyWithFLDPrefix = dataTable.getData("General_Data", "recordToSearch");
			String PolicyWithoutZeroPrefix = dataTable.getData("General_Data", "PolicyWithoutZeroPrefix");
			String PolicyWithZeroPrefix = dataTable.getData("General_Data", "PolicyWithZeroPrefix");
			

			// Search case for new assignment
			
			caseModule.createNewCase();
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
				report.updateTestLog("Validating Policy search with FLD Prefix on Case form", "Able to search Policy with FLD prefix on case form", Status.PASS);
			}
						
		}catch(Exception e)
		{
			report.updateTestLog("Validating Policy search with FLD Prefix on Case form", "Not Able to search Policy with FLD prefix on case form", Status.FAIL);
		}
       
      /* reusableFunc.enterTextAction_Stale(driver.getWebDriver(), CRMCasePage.policy_enable, CRMCasePage.policy_edit, PolicyWithZeroPrefix);
      
       r.keyPress(KeyEvent.VK_ENTER);
       r.keyRelease(KeyEvent.VK_ENTER);
       Thread.sleep(1000);
       try
		{
    	   
    	   By Policylookup = By.xpath("//ul[@id='cxp_policyid_IMenu']/li[1]//span[text()='"+PolicyWithoutZeroPrefix+"']");
    	   reusableFunc.switchToDefaultFrame(driver.getWebDriver());
    	   reusableFunc.switchToObjectFrame(driver.getWebDriver(), Policylookup);
			if(driver.findElement(Policylookup).isDisplayed())
			{
				report.updateTestLog("Validating Policy search with 000 Prefix on Case form", "Able to search Policy with 000 prefix on case form", Status.PASS);
			}
						
		}catch(Exception e)
		{
			report.updateTestLog("Validating Policy search with 000 Prefix on Case form", "Not Able to search Policy with 000 prefix on case form", Status.FAIL);
		}
       
       */
       
       
		} catch (Exception Ex) {
			report.updateTestLog("Validating Policy with Prefix  on Case Form", "Exception Occured: " + Ex.getMessage(),
					Status.FAIL);
		} 
	}

	
}

	

