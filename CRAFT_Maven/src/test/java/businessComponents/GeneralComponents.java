package businessComponents;

import org.openqa.selenium.By;

import com.cognizant.Craft.DriverScript;
import com.cognizant.Craft.ReusableLibrary;
import com.cognizant.Craft.ScriptHelper;
import com.cognizant.framework.FrameworkException;
import com.cognizant.framework.Status;

import pageObjects.CRMLandingPage;
import pageObjects.CRMLoginPage;
import pageObjects.MasterPage;
import pageObjects.SignOnPage;
import pageObjects.reusableFunctions;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Class for storing general purpose business components
 * 
 * @author Cognizant
 */
public class GeneralComponents extends ReusableLibrary {
	
	
	static int maxTimeOut = 60;
	static int minTimeOut = 10;
	public static final By signOutValidationLogo = By.xpath("//img[@class='background-logo']");
	reusableFunctions reusableFunc = new reusableFunctions(scriptHelper);
	/**
	 * Constructor to initialize the component library
	 * @param scriptHelper The {@link ScriptHelper} object passed from the {@link DriverScript}
	 */
	public GeneralComponents(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}
	
	@Step
	public void invokeApplication() {
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
									properties.getProperty("ApplicationUrl"), Status.DONE);
		
		driver.get(properties.getProperty("ApplicationUrl"));
	}
	
	@Step
	public void login() {
		String userName = dataTable.getData("General_Data", "Username");
		String password = dataTable.getData("General_Data", "Password");
		
		report.updateTestLog("Enter user credentials", "Specify " +
									"username = " + userName + ", " +
									"password = " + password, Status.PASS);
		driver.findElement(SignOnPage.txtUsername).sendKeys(userName);
		driver.findElement(SignOnPage.txtPassword).sendKeys(password);
		
		report.updateTestLog("Login", "Click the sign-in button", Status.SCREENSHOT);
		driver.findElement(SignOnPage.btnLogin).click();
	}
	
	@Step
	public void verifyLoginSuccessful() {
		if(driverUtil.objectExists(MasterPage.lnkSignOff)&&
				driver.findElement(MasterPage.lnkSignOff).isDisplayed()) {
			report.updateTestLog("Verify Login", "Login succeeded for valid user", Status.PASS);
		} else {
			frameworkParameters.setStopExecution(true);
			throw new FrameworkException("Verify Login", "Login failed for valid user");
		}
	}
	
	@Step
	public void verifyLoginFailed() {
		if(!driverUtil.objectExists(MasterPage.lnkSignOff)) {
			report.updateTestLog("Verify Login", "Login failed for invalid user", Status.PASS);
		} else {
			report.updateTestLog("Verify Login", "Login succeeded for invalid user", Status.FAIL);
		}
	}
	
	@Step
	public void logout() {
		report.updateTestLog("Logout", "Click the logout link", Status.SCREENSHOT);
		driver.findElement(MasterPage.lnkSignOff).click();
	}
	
	public void logout_rerun() {
		report.updateTestLog("Contact", "Click the contactlink", Status.SCREENSHOT);
		driver.findElement(MasterPage.lnkSignOffRerun).click();
	}
	public void singleSignOn() {
		try {
			String emailAddress = dataTable.getData("General_Data", "Username");
/*
			driver.findElement(CRMLoginPage.userEmailAddress).click();
			driver.findElement(CRMLoginPage.userEmailAddress).sendKeys(emailAddress);
			driver.findElement(CRMLoginPage.nextButton).click();
			*/
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());

			reusableFunc.waitForElementToBeVisible(driver.getWebDriver(), CRMLoginPage.signInTitle, maxTimeOut);
			reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMLoginPage.userEmailAddress);
			reusableFunc.EnterTextAction_Stale(driver.getWebDriver(), CRMLoginPage.userEmailAddress,emailAddress);
			reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMLoginPage.nextButton);
			
			System.out.println("Next button Clicked");
							
				Thread.sleep(100);
			
			reusableFunc.waitForElementToBeVisible(driver.getWebDriver(), CRMLoginPage.loginValidation, maxTimeOut);
        
			if (reusableFunc.waitForElementToBeVisible(driver.getWebDriver(), CRMLoginPage.loginValidation,
					maxTimeOut)) {
				
				report.updateTestLog("Single Sign On", "Enter Email Address", Status.PASS);
			} else {
				report.updateTestLog("Single Sign On", "Error While Single Sign On", Status.FAIL);
			}

			reusableFunc.waitForpageLoadByThread(driver.getWebDriver());
			// to handle Pending Email pop-up
			if(reusableFunc.waitForElementToBePresent(driver.getWebDriver(), CRMLandingPage.pendingEmailiframe, maxTimeOut)){
				
				driver.switchTo().frame(driver.findElement(CRMLandingPage.pendingEmailiframe));
				driver.findElement(By.xpath("//button[.='Close']")).click();
				driver.switchTo().defaultContent();
				report.updateTestLog("Single Sign On", "handel Pending Emial pop-up", Status.PASS);
			}
			Thread.sleep(2000);
		} catch (Exception Ex) {

			report.updateTestLog("Single Sign On", "Error While Single Sign On:" + Ex, Status.FAIL);
			
		}
	}
	
	public void sSO(){
		try{
			String emailAddress = dataTable.getData("General_Data", "Username");
			reusableFunc.switchToDefaultFrame(driver.getWebDriver());
			Thread.sleep(200);
			if(reusableFunc.waitForElementToBePresent(driver.getWebDriver(), CRMLoginPage.signInTitle, maxTimeOut))
			//if(driver.findElement(CRMLoginPage.signInTitle).isDisplayed())
			{
				reusableFunc.waitForElementToBeVisible(driver.getWebDriver(), CRMLoginPage.signInTitle, maxTimeOut);
				reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMLoginPage.userEmailAddress);
				reusableFunc.EnterTextAction_Stale(driver.getWebDriver(), CRMLoginPage.userEmailAddress,emailAddress);
				reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMLoginPage.nextButton);
				System.out.println("Entering Email address not skipped");
			}
			reusableFunc.waitForpageLoadByThread(driver.getWebDriver());
if(reusableFunc.waitForElementToBePresent(driver.getWebDriver(), CRMLandingPage.pendingEmailiframe, maxTimeOut)){
				
				driver.switchTo().frame(driver.findElement(CRMLandingPage.pendingEmailiframe));
				driver.findElement(By.xpath("//button[.='Close']")).click();
				driver.switchTo().defaultContent();
				report.updateTestLog("Single Sign On", "handel Pending Emial pop-up", Status.PASS);
				System.out.println("Email pop-up closed"); 
			}
			Thread.sleep(500);
			
			
			
			reusableFunc.waitForElementToBeVisible(driver.getWebDriver(), CRMLoginPage.loginValidation, maxTimeOut);
	        
			if (reusableFunc.waitForElementToBeVisible(driver.getWebDriver(), CRMLoginPage.loginValidation,
					maxTimeOut)) {
				
				report.updateTestLog("Single Sign On", "Enter Email Address", Status.PASS);
			} else {
				report.updateTestLog("Single Sign On", "Error While Single Sign On", Status.FAIL);
			}
			
			
		}catch(Exception Ex){
			report.updateTestLog("Single Sign On", "Error While Single Sign On:" + Ex, Status.FAIL);
		}
	}

}