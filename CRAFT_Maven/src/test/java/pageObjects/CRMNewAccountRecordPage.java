package pageObjects;

import org.openqa.selenium.By;

/**
 * UI Map for Login Page
 */

public class CRMNewAccountRecordPage {
	
	public static final By paysyncAccountManager = By.cssSelector("td#cxp_paysyncaccountmanager_c");
	public static final By paysyncAccountManagerlock = By.cssSelector("span#cxp_paysyncaccountmanager_lock");

	// Navigation
	public static final By homeArrow = By.cssSelector("span#TabSFA");
	public static final By salesModule = By.cssSelector("a#SFA img");
	public static final By accountEntity = By.cssSelector("a#nav_accts img");
	public static final By salesModuleMSCRM = By.cssSelector("a#SFA img");
	public static final By accountEntityMSCRM = By.cssSelector("a#nav_accts img");

	// New Record Button
	public static final By NewButtonClick = By.xpath(
			"//a[@class='ms-crm-Menu-Label']//preceding::img[@class='ms-crm-ImageStrip-New_16 ms-crm-commandbar-image16by16']");

	// Field Values

	public static final By accountName_enable = By.cssSelector("div#name");
	public static final By accountName_edit = By.cssSelector("input#name_i");

	public static final By primaryContactId_enable = By.cssSelector("div#primarycontactid");
	public static final By primaryContactId_edit = By.cssSelector("input#primarycontactid_ledit");

	public static final By accountNumber_enable = By.cssSelector("div#accountnumber");
	public static final By accountNumber_edit = By.cssSelector("input#accountnumber_i");

	public static final By parentaccountid_enable = By.cssSelector("div#parentaccountid");
	public static final By parentaccountid_edit = By.cssSelector("input#parentaccountid_ledit");

	public static final By emailaddress_enable = By.cssSelector("div#emailaddress1");
	public static final By emailaddress_edit = By.cssSelector("input#emailaddress1_i");

	public static final By telephone1_enable = By.cssSelector("div#telephone1");
	public static final By telephone1_edit = By.cssSelector("input#telephone1_i");

	public static final By telephone2_enable = By.cssSelector("div#telephone2");
	public static final By telephone2_edit = By.cssSelector("input#telephone2_i");

	public static final By telephone1_text = By
			.xpath("//div[@id='telephone1']//following::label[@id='Business Phone_label']/a");
	public static final By telephone2_text = By
			.xpath("//div[@id='telephone2']//following::label[@id='Mobile Phone_label']/a");

	public static final By fax_enable = By.cssSelector("div#fax");
	public static final By fax_edit = By.cssSelector("input#fax_i");

	public static final By websiteurl_enable = By.cssSelector("div#websiteurl");
	public static final By websiteurl_edit = By.cssSelector("input#websiteurl_i");

	// Save Button Click
	public static final By SaveButtonClick = By.xpath(
			"//a[@class='ms-crm-Menu-Label']//preceding::img[@class='ms-crm-ImageStrip-Save_16 ms-crm-commandbar-image16by16']");

	// Account Details Page

	public static final By notesAndActivities = By.xpath("//h2[@id='notes and activities_header_h2']");
	public static final String addActivityRecordimg = "//img[@alt='Add Activity record.']";
	public static final By taskbtnOnAccount = By.xpath("(//li[@id='AddtaskButton']/span/a)[2]");
	public static final By emailbtnOnAccount = By.xpath("(//li[@id='AddemailButton']/span/a)[2]");// working
	public static final By NotesAccount_edit = By.cssSelector("input#createNote_notesTitleBox");
	public static final By appointmentbtnOnAccount = By.xpath("(//li[@id='AddappointmentButton']/span/a)[2]");
	public static final By phonecallbtnOnAccount = By.xpath("(//li[@id='AddphonecallButton']/span/a)[2]");
	public static final By mergebtn = By
			.xpath("//a[@class='ms-crm-Menu-Label']//preceding::span[contains(@command,'MergeRecords')]");
	public static final By okBtnmegePg = By.id("butBegin");
	public static final By serviceActivitybtnOnAccount = By
			.xpath("(//li[@id='AddserviceappointmentButton']/span/a)[2]");

	// Field for Contact creation
	public static final By createContactImg = By.xpath("//img[@alt='Add Contact record.']");
	public static final By expand = By.xpath("//div[@id='contacts_header_image_div']/a");
	public static final By saveButtonFromAccount = By.xpath("//button[contains(@id,'save')]");

	// New Development - Neha
	public static final String AddSpecialInstructionimg = "//a[@id='SpecialInstruction_addImageButton']//img[@alt='Add Special Instruction record.']";
	
	public static final By TitleSpecialInsturction = By.xpath("//label[text()='Title']");
	public static final By DescriptionSpecialInsturction = By
			.xpath("//a[@title='Sort by Description']//label[text()='Description']");
	public static final By CreatedBySpInstruction = By
			.xpath("//a[@title='Sort by Created By']//label[text()='Created By']");
	public static final By CreatedOnSpInstruction = By.xpath(
			"//table[@id='SpecialInstruction_gridBar']//tbody//a[@title='Sort by Created On']//label[text()='Created On']");
	public static final By Title = By.id("cxp_title_i");
	public static final By Title_enable = By
			.xpath("//div[contains(@id,'cxp_title') and @data-attributename='cxp_title']");
	public static final By Title_edit = By.xpath("//input[contains(@id,'cxp_title')]");
	public static final By SpInsTitle_text = By.xpath(
			"//div[@id='SpecialInstruction_divDataArea']//following::a[contains(@id,'gridBodyTable_primaryField')]");
	public static final By Desc_enable = By
			.xpath("//div[contains(@id,'cxp_description') and @data-attributename='cxp_description']");
	public static final By Desc_edit = By.xpath("//input[contains(@id,'cxp_description')]");
	public static final By SaveButton = By
			.xpath("//div[@id='globalquickcreate_actionsdiv_NavBarGloablQuickCreate']/button[text()='Save']");
	public static final String AssociatedSpecialInstructionimg = "//a[@id='SpecialInstruction_openAssociatedGridViewImageButton']//img[@alt='See the records associated with this view.']";
	public static final String DeleteSpecialInstructionimg = "//li[contains(@title,'Delete Special')]//a[@class='ms-crm-Menu-Label']/img[contains(@class,'Delete')]";
	public static final String Delete1SpecialInstructionimg = "//button[@id='butBegin']/div";

	// New Development - 12/19
	
	public static final By specialInstructionRecordSearch =  By.xpath("//input[contains(@id,'cxp_specialinstruction')]");
	public static final By checkBoxPath = By.xpath("//input[@id='chkAll']//following-sibling::img[contains(@id,'specialinstruction')]");
	public static final By addSpecialInstructionImgButton = By.xpath("//li[contains(@title,'Add New Special Instruction')]//a[@class='ms-crm-Menu-Label']/img[contains(@class,'New')]");
}
