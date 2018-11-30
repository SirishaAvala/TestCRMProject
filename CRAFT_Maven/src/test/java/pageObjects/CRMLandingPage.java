package pageObjects;

import org.openqa.selenium.By;

/**
 * UI Map for Login Page
 */

public class CRMLandingPage {

	// Navigation

	public static final By homeArrowDecision = By.xpath("//span[@id='TabnavTabLogoTextId_splitter']//following-sibling::span[2]/span[1]/a/span/span");
	public static final By homeArrow = By.cssSelector("span#TabSFA");
	public static final By salesModule = By.cssSelector("a#SFA img");
	public static final By serviceModule = By.xpath("//img[@alt='Service']//parent::span");
	public static final By marketingModule = By.xpath("//img[@alt='Marketing']//parent::span");

	public static final By accountEntity = By.cssSelector("a#nav_accts img");
	public static final By caseEntity = By.cssSelector("a#nav_cases");
	public static final By contactEntity = By.cssSelector("a#nav_contacts");
	public static final By activityEntity = By.cssSelector("a#nav_activities img");
	public static final By leadEntity = By.cssSelector("a#nav_leads");
	public static final By marketingListEntity = By.cssSelector("a#nav_lists");
	public static final By campaignsEntity = By.cssSelector("a#nav_campaigns");
	public static final By quickCampaignsEntity = By.cssSelector("a#nav_minicamps");

	public static final By profileIcon = By.xpath("//span[@id='navTabButtonChangeProfileImageLink']/img");

	public static final By signOutLink = By.xpath("//span[@id='navTabButtonUserInfoDropDownId']//following::a[@id='navTabButtonUserInfoSignOutId']");
	public static final By Logo = By.cssSelector("img#background_background_image");

	public static final By globalSearchImg = By.xpath("//span[@title='Start search']");
	public static final By globalSearchtxtfld = By.id("search");
	public static final By globalSearchFindbtn = By.xpath("//a[@id='findCriteriaButton']/img");
	public static final By filterWithDropDwn = By.xpath("//select[@id='filterCombo']");
	public static final By filterWithDropDwn_enable = By.cssSelector("div#inputContainer");
	public static final By filterWithDropDwn_edit = By.cssSelector("select#filterCombo");

	public static final By errorMsgMandatoryField = By
			.xpath("//img[@alt='Error']//following::div[contains(text(),'You must provide a value')]");

	// Assignment

	public static final By More = By.xpath("//img[@src='/_imgs/more_16.png']");
	// public static final By Iframe = By.id("InlineDialog_Iframe");
	public static final By AssignButtonClick = By.xpath("//img[contains(@class,'Assign')]");
	public static final By AssignToClick = By.cssSelector("div#rdoMe_id");
	public static final By AssignToOtherEnable = By.cssSelector("div#systemuserview_id");
	public static final By AssignToOtherEdit = By.cssSelector("input#systemuserview_id_ledit");
	public static final By Assign = By.cssSelector("button#ok_id");

	// Sharing

	public static final By ShareTabClick = By.xpath("//img[contains(@class,'Sharing')]");
	public static final By AddUser_Team = By.linkText("Add User/Team");
	// public static final By LookupIframe = By.id("InlineDialog1_Iframe");
	public static final String LookupIframe = "//iframe[@id='InlineDialog1_Iframe']";
	public static final By LookFor = By.id("selObjects");
	public static final By SearchFor = By.xpath("//input[@id='crmGrid_findCriteria' and @tabindex='3']");

	public static final By AddSelectedMember = By.cssSelector("button#btnAdd");
	public static final By AddMember = By.xpath("//button[contains(text(),'Add')]");
	public static final By Share = By.xpath("//button[@title='Share']");
	/*
	 * public static final By frame0=By.id("contentIFrame0"); public static
	 * final By frame1=By.id("contentIFrame1"); public static final By
	 * frame2=By.id("contentIFrame2"); public static final By
	 * frame=By.id("contentIFrame"); public static final String
	 * frame4="//iframe[@id='contentIFrame0']";
	 */
	public static final By baseFrame = By.id("contentIFrame");
	public static final By baseFrame0 = By.id("contentIFrame0");
	public static final By baseFrame1 = By.id("contentIFrame1");
	public static final By inlineDialogFrame = By.id("InlineDialog_Iframe");
	public static final By inlineDialogErrorFrame = By.id("InlineDialog1_Iframe");

	public static final String frame0 = "//iframe[@id='contentIFrame0']";
	public static final String frame1 = "//iframe[@id='contentIFrame1']";
	public static final String frame2 = "//iframe[@id='contentIFrame2']";
	public static final String frame = "//iframe[@id='contentIFrame']";
	public static final String Iframe = "//iframe[@id='InlineDialog_Iframe']";
	public static final String Iframe0 = "contentIFrame0";
	public static final String Iframe1 = "contentIFrame1";
	public static final String Inlineframe = "InlineDialog_Iframe";
	public static final String InlineErrorframe = "InlineDialog1_Iframe";
	public static final String Iframe2 = "contentIFrame2";

	// New Development

	public static final String IframeSpinstruction = "NavBarGloablQuickCreate";
	public static final String IframespecialinstExpan = "area_cxp_account_cxp_specialinstruction_CustomerFrame";
	public static final By IframespecialinstExpan0 = By.id("area_cxp_account_cxp_specialinstruction_CustomerFrame");
	public static final By advanceFindimg = By.cssSelector("span#advancedFindImage");
	public static final String Resultframe = "resultFrame";
	public static final String Resultframe0 = "//iframe[@id='resultFrame']";
	
	public static final By pendingEmailiframe =By.xpath("//iframe[@id='InlineDialog_Iframe']");
}
