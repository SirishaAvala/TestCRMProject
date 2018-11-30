package businessComponents;

import org.openqa.selenium.By;

import com.cognizant.Craft.ReusableLibrary;
import com.cognizant.Craft.ScriptHelper;
import com.cognizant.framework.Status;

import pageObjects.CRMCasePage;
import pageObjects.CRMLandingPage;
import pageObjects.UI_Helpers_OOB;
import pageObjects.reusableFunctions;

public class CaseManagement extends ReusableLibrary{
	

	public CaseManagement(ScriptHelper scriptHelper) {
		super(scriptHelper);
		// TODO Auto-generated constructor stub
	}

	reusableFunctions reFun =new reusableFunctions(scriptHelper);
	Case_MSCRM cs=new Case_MSCRM(scriptHelper);
	
	
	public void createCaseNew(){
		try{
			String caseTitle1 = dataTable.getData("General_Data", "CaseTitle");
			String Customer = dataTable.getData("General_Data", "Customer");
			String Origin = dataTable.getData("General_Data", "Origin");
			if(caseTitle1.equalsIgnoreCase(null)){
				caseTitle1 = "Automated Case_" + UI_Helpers_OOB.getcurrenttime();
			}
			
			
			// Navigate to Entity
			reFun.navigationToEntity(driver.getWebDriver(), CRMLandingPage.serviceModule, CRMLandingPage.caseEntity);
			System.out.println("Navigated to CAse Entity");
			reFun.waitForpageLoadByThread(driver.getWebDriver());
			
			// Click on New Button 
			driver.findElement(By.xpath("//li[@id='incident|NoRelationship|HomePageGrid|Mscrm.HomepageGrid.incident.CaseFlow']/span")).click();
			reFun.waitForpageLoadByThread(driver.getWebDriver());
			report.updateTestLog("Case Entity", "Click on new button", Status.DONE);
			System.out.println("Click on new button ");
			
			// Switch to Edit case form FRAME
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='contentIFrame1']")));
			
			System.out.println("Switched to edit form Frame");
			
			//Enter Details :
			UI_Helpers_OOB.type_Stale(driver.getWebDriver(), CRMCasePage.caseName_enable, CRMCasePage.caseName_edit,
					caseTitle1);
			
			
			
			UI_Helpers_OOB.searchAndSelectOnForm(driver.getWebDriver(), CRMCasePage.customer_enable, CRMCasePage.customer_edit, Customer);
			
			
			UI_Helpers_OOB.dropDownSelection_Stale(driver.getWebDriver(), CRMCasePage.caseorigin_enable,
					CRMCasePage.caseorigin_edit, Origin);
			
			
			
			driver.switchTo().defaultContent();
			driver.findElement(CRMCasePage.SaveButtonClick).click();
			
			
			reFun.waitForpageLoadByThread(driver.getWebDriver());
			report.updateTestLog("Creating Case Entity", "Details filled and clicked on Save Button", Status.DONE);
			
			cs.searchCaseRelatedRecordsAndSelect(caseTitle1);
			
			
			
			
		}catch(Exception Ex){
			
		}
		
	}
}
