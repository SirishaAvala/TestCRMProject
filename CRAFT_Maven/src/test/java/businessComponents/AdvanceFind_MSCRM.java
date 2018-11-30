package businessComponents;

import pageObjects.*;
import com.cognizant.Craft.*;

import com.cognizant.framework.Status;
import com.sun.glass.ui.Robot;

import javax.swing.Action;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


public class AdvanceFind_MSCRM extends ReusableLibrary{
	

	public AdvanceFind_MSCRM(ScriptHelper scriptHelper) {
		super(scriptHelper);
		//  Auto-generated constructor stub
	}
	reusableFunctions reusableFunc = new reusableFunctions(scriptHelper);
	public void navigateToAdvanceFind() throws InterruptedException{
		driver.findElement(CRMAdvanceFindPage.advanceFindIcon).click();
		UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
		report.updateTestLog("Navigate to Advance Find", "Succesfully clicked on Advance Find icon",
				Status.PASS);
		
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
				
		}

		// Perform the actions on new window

		// Close the new window, if that window no more required
	//	driver.close();

		// Switch back to original browser (first window)
		//driver.switchTo().window(winHandleBefore);

		// Continue with original browser (first window)
	}
	public  void verifyContactfromAdvanceFind(String contactname, String firstname, String lastname) throws InterruptedException{
		// Store the current window handle
		String winHandleBefore = driver.getWindowHandle();
		try{
		navigateToAdvanceFind();
		UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
		reusableFunc.switchToObjectFrame(driver.getWebDriver(), CRMAdvanceFindPage.lookForDropdown);
		Select lookFor = new Select(driver.findElement(CRMAdvanceFindPage.lookForDropdown));
		lookFor.selectByVisibleText("Individuals");
		
		System.out.println("individual selected ");
		
		Select view = new Select(driver.findElement(CRMAdvanceFindPage.useSavedView));
		view.selectByVisibleText("Individuals with Contact ID");
		System.out.println("Individuals with Contact ID");

		UI_Helpers_OOB.waitForElementToBeVisible(driver.getWebDriver(), CRMAdvanceFindPage.selectFilterLabel, 30);
		driver.findElement(CRMAdvanceFindPage.selectFilterLabel).click();
		Select selectFilter = new Select(driver.findElement(CRMAdvanceFindPage.selectFilterDropdown));
		// Full name Filter 
		/*
		selectFilter.selectByVisibleText("Full Name");
		System.out.println("Full name selected ");
		
		driver.findElement(CRMAdvanceFindPage.fullnameEqualLabel).click();
		Select equalDropdownfullname = new Select(driver.findElement(CRMAdvanceFindPage.fullnameequalDropdown));
		equalDropdownfullname.selectByVisibleText("Contains");
		System.out.println("Contains selected ");
		
		driver.findElement(CRMAdvanceFindPage.enterTextFullName_enable).click();
		driver.findElement(CRMAdvanceFindPage.enterTextFullName_edit).sendKeys(contactname);
		System.out.println("Contact  Full Name Entered ");	
		*/
		// First Name Filter 
		driver.findElement(CRMAdvanceFindPage.selectFilterLabel).click();		
		selectFilter.selectByVisibleText("First Name");
		System.out.println("First  name selected ");
		
		driver.findElement(CRMAdvanceFindPage.firstnameEqualLabel).click();
		System.out.println("full name Equlas Clickced");
		Select equalDropdownfirstname = new Select(driver.findElement(CRMAdvanceFindPage.firstnameequalDropdown));
		equalDropdownfirstname.selectByVisibleText("Contains");
		System.out.println("Contains selected ");
		
		driver.findElement(CRMAdvanceFindPage.enterTextFirstname_enable).click();
		driver.findElement(CRMAdvanceFindPage.enterTextFirstname_edit).sendKeys(firstname);
		System.out.println("Contact  First Name Entered ");	
		
		// Last name Filter 
		driver.findElement(CRMAdvanceFindPage.selectFilterLabel).click();		
		selectFilter.selectByVisibleText("Last Name");
		System.out.println("LAst  name selected ");
		
		driver.findElement(CRMAdvanceFindPage.lastnameEqualLabel).click();
		Select equalDropdownlastname = new Select(driver.findElement(CRMAdvanceFindPage.lastnameequalDropdown));
		equalDropdownfirstname.selectByVisibleText("Contains");
		System.out.println("Contains selected ");
		
		driver.findElement(CRMAdvanceFindPage.enterTextlastname_enable).click();
		driver.findElement(CRMAdvanceFindPage.enterTextlastname_edit).sendKeys(lastname);
		System.out.println("Contact  Last Name Entered ");	
		
		/*
		// Created on Filter 
		driver.findElement(CRMAdvanceFindPage.selectFilterLabel).click();
		selectFilter.selectByVisibleText("Created On");
		
		driver.findElement(CRMAdvanceFindPage.createdOn_ON_Label).click();
		Select on = new Select(driver.findElement(CRMAdvanceFindPage.onDropdown));
		on.selectByVisibleText("Last X Hours");
		driver.findElement(CRMAdvanceFindPage.time_enable).click();
		driver.findElement(CRMAdvanceFindPage.time_edit).sendKeys("1");
		*/
		//Edit Column
		/*
				reusableFunc.switchToDefaultFrame(driver.getWebDriver());
				
				driver.findElement(CRMAdvanceFindPage.editColumn).click();
				System.out.println("Edit Column clicked");
				UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
				
				//reusableFunc.switchToObjectFrame(driver.getWebDriver(), CRMAdvanceFindPage.editColumnHeader);
				driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='InlineDialog_Iframe']")));
				driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='viewEditor']")));
				
				reusableFunc.waitForElementToBePresent(driver.getWebDriver(), CRMAdvanceFindPage.addColumn, 60);
				driver.findElement(CRMAdvanceFindPage.addColumn).click();
				System.out.println("Add Column clicked");
				UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
				
				reusableFunc.switchToObjectFrame(driver.getWebDriver(), CRMAdvanceFindPage.recordTypeLabel);
				//driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='InlineDialog1_Iframe']")));
				
				driver.findElement(CRMAdvanceFindPage.columnName).click();
				System.out.println("Contact ID  clicked");
				driver.findElement(CRMAdvanceFindPage.okButton).click();
				
				System.out.println("OK Button clicked");
				
				//reusableFunc.switchToObjectFrame(driver.getWebDriver(), CRMAdvanceFindPage.editColumnHeader);
				driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='InlineDialog_Iframe']")));
				driver.findElement(CRMAdvanceFindPage.okButton).click();
				System.out.println("OK Button clicked");
		*/
				report.updateTestLog("Verifying Individual ", "Advance find Query  ",Status.PASS);
		
		reusableFunc.switchToDefaultFrame(driver.getWebDriver());
		driver.findElement(CRMAdvanceFindPage.result).click();
		
		System.out.println("result button clicked");
		
		report.updateTestLog("Advance find Navigation ", "Result button Clicked  ", 
				Status.PASS);
		UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
		driver.findElement(By.xpath("//a[@title='Individual']")).click();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='contentIFrame0']")));
		
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='resultFrame']")));
		
		//reusableFunc.switchToObjectFrame(driver.getWebDriver(), CRMAdvanceFindPage.fullnameColumnLabel);
		
		System.out.println("Frame Swithed");
	//	driver.findElement(CRMAdvanceFindPage.contactID_columnResult).click();
		
		
		if(reusableFunc.waitForElementToBePresentBool(driver.getWebDriver(), CRMAdvanceFindPage.noRecordFound, 50))
		{
			System.out.println("Entered in No record found = True ");
			report.updateTestLog("Verifying Individual ", "NO Individual Record found : "+contactname,Status.FAIL);
			
					
		}
		else{
			System.out.println("Entered in No record found = false ");
			
			String Contactid=driver.findElement(By.xpath("//a[@title='"+contactname+"']//following::td[2]/nobr")).getText();
			
			if(Contactid !=""){
				report.updateTestLog("Verifying Individual  ", "Contact id of Individual : "+Contactid,Status.PASS); 
				System.out.println("contact id"+Contactid);
			
			}else 
			{
				report.updateTestLog("Verifying Individual   ", "Contact id of Individual : "+contactname+" is not generated  ",Status.PASS);
				
			}
			
			}
		
		}catch (Exception e) {
			report.updateTestLog("Advance find Navigation ", "Exception occured : "+e.getMessage() , 
					Status.FAIL);
	}
}
}

