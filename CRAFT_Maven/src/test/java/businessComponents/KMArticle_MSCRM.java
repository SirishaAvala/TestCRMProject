package businessComponents;

import pageObjects.*;
import com.cognizant.Craft.*;

import java.awt.Robot;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.cognizant.framework.Status;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;
import com.sun.glass.events.KeyEvent;

import javafx.scene.control.Tab;


public class KMArticle_MSCRM extends ReusableLibrary{
	
	reusableFunctions reusableFunc = new reusableFunctions(scriptHelper);
	String Title1;
	
	Actions action = new Actions(driver.getWebDriver());
	public KMArticle_MSCRM(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}
	
	public static final By serviceModule = By.xpath("//a[@id='CS']/span");
	public static final By article =  By.cssSelector("a#nav_answers");
	public static final By rxd = By.xpath("//tr//following::span[.='KBA-01005-F6Y9R8']//following::td[3]/nobr/a");
	
	
 public void createKMArticle() throws Exception{
	 
		String aa = dataTable.getData("General_Data", "Title");
		String Template = dataTable.getData("General_Data", "Template");
		String subject = dataTable.getData("General_Data", "Subject");
		String keyword = dataTable.getData("General_Data", "Key Words");
		
		String Title = aa+UI_Helpers_OOB.getcurrenttime();	
		
	 try{
		
		 UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
		// reusableFunc.navigationToEntity(driver.getWebDriver(), serviceModule, CRMLandingPage.caseEntity);
		 reusableFunc.navigationToEntity(driver.getWebDriver(), serviceModule, article);
	 
	 UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
	 driver.findElement(CRMKmArticlePage.articleNewButton).click();
	 driver.findElement(CRMKmArticlePage.articleNewButton).click();
	 
	//navigate to newly opened window and select template 
	 String winHandleMain = driver.getWindowHandle();
	 for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
					}
	 
	
	 String winHandletemplate = driver.getWindowHandle();
	 UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
	 action.doubleClick(driver.findElement(CRMKmArticlePage.procedure)).perform();
	// driver.findElement(CRMKmArticlePage.selectButton).click();

	 //navigate to newly opened window and create Article 
	 for(String winHandle : driver.getWindowHandles()){
		 driver.switchTo().window(winHandle);}
	 String winHandleArticle = driver.getWindowHandle();
	 UI_Helpers_OOB.waitForpageLoadByThread(driver.getWebDriver());
	 reusableFunc.switchToObjectFrame(driver.getWebDriver(), CRMKmArticlePage.pagetitle);
	 driver.findElement(CRMKmArticlePage.titleEnable).click();
	 driver.findElement(CRMKmArticlePage.titleEdit).sendKeys(Title);
	
	 driver.findElement(By.xpath("//label[.='Subject']")).click();
	 System.out.println("Subject label Clicked");
	
	reusableFunc.waitForElementToBeClickable(driver.getWebDriver(), CRMKmArticlePage.subjectsearch, 50);
	 driver.findElement(CRMKmArticlePage.subjectsearch).click();
	 
	 reusableFunc.switchToObjectFrame(driver.getWebDriver(), CRMKmArticlePage.selectSubjectTitle);
	 driver.findElement(CRMKmArticlePage.query).click();
	 
	 action.doubleClick(driver.findElement(CRMKmArticlePage.query_information)).perform();
	// driver.findElement(CRMKmArticlePage.query_information).click();
	// driver.findElement(CRMKmArticlePage.selectButton);
	 Thread.sleep(20);
	// driver.switchTo().window(winHandleArticle);
	 
	
	 reusableFunc.switchToObjectFrame(driver.getWebDriver(), CRMKmArticlePage.pagetitle);
	 reusableFunc.waitForElementToBePresent(driver.getWebDriver(), CRMKmArticlePage.keyWordEnalble, 100);
	driver.findElement(CRMKmArticlePage.keyWordEnalble).click();
	driver.findElement(CRMKmArticlePage.keyWordEdit).sendKeys(keyword);
	
	driver.switchTo().defaultContent();
	action.doubleClick(driver.findElement(CRMKmArticlePage.saveCloseButton)).perform();
	//driver.findElement(CRMKmArticlePage.saveClose);
	
	 
	 driver.switchTo().window( winHandletemplate);
	 driver.findElement(By.xpath("//button[.='Cancel']")).click();
	 driver.switchTo().window(winHandleMain);
	 
	 report.updateTestLog("Create KM Article ", "Km Article Successfully created  ", 
				Status.PASS); 
	 
	 }catch(Exception Ex){
		 report.updateTestLog("Create KM Article ", "Exception occured : "+Ex.getMessage() , 
					Status.FAIL);
	 }
 }
 
 public void publishKMArticle() throws Exception{
	 String articleNumber = dataTable.getData("General_Data", "articleNumber");
		
	 
	
	 
	try{
		 reusableFunc.navigationToEntity(driver.getWebDriver(), serviceModule, article);
		 
		 reusableFunc.switchToObjectFrame(driver.getWebDriver(), CRMKmArticlePage.selectArticleView);
		 
		 driver.findElement(CRMKmArticlePage.selectArticleView).click();
		System.out.print("View clicked");
		 
		 driver.findElement(CRMKmArticlePage.allArticleView).click();
		 System.out.print("View Selected ");
	
		 if (reusableFunc.waitForElementToBePresentBool(driver.getWebDriver(), rxd, 50)){
		 driver.findElement(By.xpath("//tr//following::span[.='"+articleNumber+"']//following::td[3]/nobr/a")).click();
		 
		 String windowBefore=driver.getWindowHandle();
		
		 for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);}
		 driver.findElement(CRMKmArticlePage.submitButton).click();	
		 report.updateTestLog("Publish KM Article", "Successfully Submitted KM Article" , 
					Status.PASS);
		 }else
			 report.updateTestLog("Search Km Article", "Articel not found" , 
						Status.PASS);
	}catch(Exception Ex) {
		report.updateTestLog("Publish KM Article", "Exception occured : "+Ex.getMessage() , 
				Status.FAIL);
	}
		
	 
	 
 }

 public void createandPublishKMArticle() throws Exception{
	 
	 
	 
	 
		 String aa = dataTable.getData("General_Data", "Title");
			String Template = dataTable.getData("General_Data", "Template");
			String subject = dataTable.getData("General_Data", "Subject");
			String keyword = dataTable.getData("General_Data", "Key Words");
			String emailAddress = dataTable.getData("General_Data", "Username");
			
			String Title = aa+UI_Helpers_OOB.getcurrenttime();	
			
		 try{
			 /*
			 	// navigate and click on Access It Here
				 reusableFunc.navigationToEntity(driver.getWebDriver(), serviceModule, article);
				 reusableFunc.switchToObjectFrame(driver.getWebDriver(), CRMKmArticlePage.selectArticleView);
				 driver.findElement(CRMKmArticlePage.accessItHere).click();
				 
				// switch to Tab
				
				 String windowHandle = driver.getWindowHandle();
				    driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");
				// create New Article
				    
				   reusableFunc.waitForpageLoadByThread(driver.getWebDriver());
				   driver.findElement(By.xpath("")).click();
				 */
			 driver.get("https://selectiveinsurancecrmqa.crm.dynamics.com/nga/engagementhub.aspx?org=org38e8bc9c&server=https%3a%2f%2fselectiveinsurancecrmqa.crm.dynamics.com");
			
			 
			 reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMLoginPage.userEmailAddress);
				reusableFunc.EnterTextAction_Stale(driver.getWebDriver(), CRMLoginPage.userEmailAddress,emailAddress);
				reusableFunc.MouseClickAction_Stale(driver.getWebDriver(), CRMLoginPage.nextButton);
				Thread.sleep(100);
				
			
			 driver.findElement(By.xpath("//div[@class='navBarSiteMapAreaDropDownButton']")).click();
			 driver.findElement(By.xpath("//div[@id='nav_KnowledgeArticles']")).click();
			 
			reusableFunc.waitForElementToBePresent(driver.getWebDriver(), By.xpath("//button[@data-id='knowledgearticle|NoRelationship|HomePageGrid|Mscrm.HomepageGrid.knowledgearticle.NewRecord']"), 20);
			driver.findElement(By.xpath("//button[@data-id='knowledgearticle|NoRelationship|HomePageGrid|Mscrm.HomepageGrid.knowledgearticle.NewRecord']")).click();
			System.out.print("new clicked");
			reusableFunc.waitForpageLoadByThread(driver.getWebDriver());

			//title
			reusableFunc.waitForElementToBePresent(driver.getWebDriver(), By.xpath("//span[.='Title']"), 30);
			driver.findElement(By.xpath("//span[.='Title']")).click();
			System.out.print("title clicked");
			driver.findElement(By.xpath("//span[.='Title']//following::input")).sendKeys("Demo test Article1 ");
			System.out.print("title Entered ");
			Thread.sleep(200);
			
			//Category
			driver.findElement(By.xpath("//span[.='Keywords']")).click();
			System.out.print("category  clicked");
			driver.findElement(By.xpath("//span[.='Keywords']//following::textarea")).sendKeys("CategoryBilling");
			System.out.print("Entered ");
			Thread.sleep(200);
			//save
			driver.findElement(By.xpath("//button[@title='Save']")).click();
			System.out.println("Save clicked");
			Thread.sleep(200);
			
			//Author 
			driver.findElement(By.xpath("//span[.='Author']")).click();
			
			System.out.println("Author clicked");
			if(reusableFunc.isAlertPresent()){
				driver.switchTo().alert().accept();
			}
			// Subject :
			
			
			action.sendKeys(Keys.TAB).perform();
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(200);
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(200);
			action.sendKeys(Keys.TAB).perform();
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(500);
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(200);
			action.sendKeys(Keys.TAB).perform();
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(200);
			driver.findElement(By.xpath("//button[@title='Save']")).click();
			System.out.println("Save clicked");
			Thread.sleep(200);
			
			/*
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);
			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			//Primary Author
			Thread.sleep(1000);
			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);
			//Mark Complete
			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);
			
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			
			//Next Button
			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			*/
			/*
			 
			//subject 
			driver.findElement(By.xpath("//span[.='Article Subject']")).click();
			System.out.println("Subject  clicked");
			driver.wait(10);
			driver.findElement(By.xpath("//div[@data-fieldname='subjectid_edit']")).click();
		//	driver.findElement(By.xpath("//input[@type='search']")).click(); 
			System.out.println("Subject box  clicked");
			driver.findElement(By.xpath("//input[@type='search']//following::button")).click(); 
			driver.wait(10);
			//dropdown
			driver.findElement(By.xpath("//div[.='Default Subject']")).click();
			System.out.print("Default  clicked");
			driver.wait(10);
			//mark comp-lete
			reusableFunc.waitForElementToBePresent(driver.getWebDriver(), By.xpath("//span[.='Mark Complete']"), 30);
			driver.findElement(By.xpath("//span[.='Mark Complete']")).click();
			System.out.println("Mark Complete  clicked");
			driver.wait(10);
			*/
			//nextStage
		//	driver.findElement(By.xpath("//span[@id='flyoutNextId']")).click();
			System.out.println("next stage  clicked");
			
			
			//review
			driver.findElement(By.xpath("//span[.='Review']")).click();
			System.out.println("Review  clicked");
			//approve
			
			Select slt = new Select(driver.findElement(By.xpath("//select[@class='comboBox']")));
			slt.selectByVisibleText("Approved");
			
			//alert
	driver.switchTo().alert().accept();
	driver.findElement(By.xpath("//span[.='Next Stage']")).click();
			//
			driver.findElement(By.xpath("//span[.='Set Product Associations']")).click();
			driver.findElement(By.xpath("//span[.='Set Expiration Date']")).click();
			driver.findElement(By.xpath("//span[@title='Calendar_Title']")).click();
			
			driver.findElement(By.xpath("//a[.='30']"));
			
			
			//save
			driver.findElement(By.xpath("//button[@title='Save']")).click(); 
			//more
			driver.findElement(By.xpath("//button[@title='More']")).click();
			//publish
			driver.findElement(By.xpath("//button[@title='Publish']")).click();
			//submit
			driver.findElement(By.xpath("//button[@class='button SimpleButton']")).click();
			
				 
	 }catch(Exception Ex)
	 {
		 report.updateTestLog("Create and Publish KM Article", "Exception occured : "+Ex.getMessage() , 
					Status.FAIL); 
	 }
	 
 }

}