package pageObjects;

import org.openqa.selenium.By;

public class CRMKmArticlePage {
	
	
	public static final By serviceModule = By.xpath("//img[@alt='Service']//parent::span");
	public static final By article =  By.xpath("//a[@title='Articles']");
	
	public static final By articleNewButton = By.xpath("//li[@id='kbarticle|NoRelationship|HomePageGrid|Mscrm.HomepageGrid.kbarticle.NewRecord']/span");
	
	public static final By procedure = By.xpath("//li[@itemtitle='Procedure']");
	public static final By questionAnswer = By.xpath("//li[@itemtitle='Question & Answer']");
	public static final By solutiontoProblem = By.xpath("//li[@itemtitle='Solution to a Problem']");
	public static final By standardKBArticle = By.xpath("//li[@itemtitle='Standard KB Article']");
	public static final By pagetitle=By.xpath("//h1[.='New Article']");
	public static final By titleEnable=By.xpath("//label[.='Title']");
	public static final By titleEdit=By.xpath("//input[@id='title']");
	public static final By subjectsearch =By.xpath("//img[@id='subjectid']");
	public static final By selectSubjectTitle =By.xpath("//div[@id='DlgHdBodyContainer']");
	public static final By query = By.xpath("//label[.='Query']");
	public static final By query_information = By.xpath("//label[.='Information']");
	public static final By selectButton=By.xpath("//button[@id='butBegin']");
	public static final By keyWordEnalble = By.xpath("//label[.='Key Words']");
	public static final By keyWordEdit = By.xpath("//input[@id='keywords']");
	public static final By saveCloseButton =By.xpath("//a[@id='kbarticle|NoRelationship|Form|Mscrm.Form.kbarticle.SaveAndClose-Large']");
	
	public static final By selectArticleView =By.xpath("//a[@id='crmGrid_SavedNewQuerySelector']");		
	public static final By allArticleView = By.xpath("//span[@title='All Articles']");
	public static final By submitButton =By.xpath("//a[@id='kbarticle|NoRelationship|Form|Mscrm.Form.kbarticle.Submit-Large']");
	public static final By saveButton=By.xpath("//a[@id='kbarticle|NoRelationship|Form|Mscrm.Form.kbarticle.Save-Large']");
	
	public static final By accessItHere =By.xpath("//span[.='Access it here']");
}
