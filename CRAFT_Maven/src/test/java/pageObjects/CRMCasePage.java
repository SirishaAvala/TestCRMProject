package pageObjects;

import org.openqa.selenium.By;

/**
 * UI Map for Login Page
 */

public class CRMCasePage {
	
	public static final By caseForm =By.xpath("//div[@id='FormTitle']"); 

	// New case creation button
	public static final By newCaseButton = By.xpath("//ul[@role='application']/li[1]/span/a");

	public static final By serviceHomeArrow = By.cssSelector("span#TabCS");
	public static final By marketingHomeArrow = By.cssSelector("span#TabMA");
	
	// Case form Frame
	public static final By caseformFrame = By.xpath("//iframe[@id='contentIFrame1']");
	// case fields
	public static final By caseName_enable = By.cssSelector("div#title");
	public static final By caseName_edit = By.cssSelector("input#title_i");

	public static final By caseSubject_enable = By.cssSelector("div#subjectid");
	public static final By caseSubject_dropdown = By.cssSelector("img#subjectid_i");
	public static final By caseSubject_select = By.xpath("//*[@id='subjectid_treediv']/ul/li[3]/ins");

	public static final By customer_enable = By.cssSelector("div#customerid");
	public static final By customer_edit = By.cssSelector("input#customerid_ledit");

	public static final By policy_enable = By.cssSelector("div#cxp_policyid");
	public static final By policy_edit = By.cssSelector("input#cxp_policyid_ledit");

	public static final By billAccount_enable = By.cssSelector("div#cxp_billaccountid");
	public static final By billAccount_edit = By.cssSelector("input#cxp_billaccountid_ledit");

	public static final By policyLookupIcon = By.cssSelector("td#cxp_policyid_lookupSearch");
	public static final By billAccountLookupIcon = By.cssSelector("td#cxp_billaccountid_lookupSearch");
	
	public static final By statusReasonLabel =By.xpath("//span[@id='statuscode_cl']"); 
	public static final By statusReasondropdown =By.xpath("//select[@id='statuscode_i']"); 

	
	 
	public static final By policyRecordTitle = By
			.xpath("//ul[@id='cxp_policyid_IMenu']/li[2]//a[@title='Look Up More Records']");

	
	public static final By caseorigin_enable = By.cssSelector("div#caseorigincode");
	public static final By caseorigin_edit = By.cssSelector("select#caseorigincode_i");

	public static final By contact_enable = By.cssSelector("div#primarycontactid");
	public static final By contact_edit = By.cssSelector("input#primarycontactid_ledit");

	public static final By caseproduct_enable = By.cssSelector("div#productid");
	public static final By caseproduct_edit = By.cssSelector("input#productid_ledit");

	public static final By SaveButtonClick = By.xpath(
			"//a[@class='ms-crm-Menu-Label']//preceding::img[@class='ms-crm-ImageStrip-Save_16 ms-crm-commandbar-image16by16']");

	public static final By descPhoneCallActivity_Case = By
			.xpath("//div[contains(@id,'description')]//following::div[2]/textarea[@title='Description']");
	public static final By OkbtnActivity_case = By.xpath("//button[contains(@onclick,'save')]");
	public static final By addPhoneCallActivityLink_Case = By.xpath("//a[@title='Phone Call']");
	public static final By resolveCasebtn = By
			.xpath("//li[contains(@id,'Resolve')]//span/a/span[contains(@command,'Resolve')]");

	public static final By CancelCasebtn = By
			.xpath("//li[contains(@id,'Cancel')]//span/a/span[contains(@command,'Cancel')]");

	public static final By resolveCasemsg = By.xpath("//div[@id='dvDescription']/div");
	public static final By confirmbtn = By.id("butBegin");
	public static final By cancelbtn = By.id("cmdDialogCancel");
	public static final By caseStatus = By.xpath("//div[@id='header_statuscode']/div/label/div[1]");
	public static final By pageTitle = By.xpath("//div[@id='FormTitle']");

	public static final By resolutionCase_enable = By.cssSelector("div#resolution_id");
	public static final By resolutionCase_edit = By.cssSelector("input#resolution_id_i");
	public static final By billableTime_Case = By.id("billabletime_id_iSelectInput");
	public static final By okbtnResolve_case = By.id("ok_id");
	public static final By additionalDetails = By.cssSelector("h2#ADDITIONALDETAILS_TAB_header_h2");

	
	public static final By type_enable = By.cssSelector("div#casetypecode");
	public static final By type_case = By.id("Type_label");
	public static final By priorityCase = By.id("Priority_label");
	public static final By priority_parent_ID = By
			.cssSelector("div.ms-crm-Inline-Edit ms-crm-Inline-OptionSet noScroll  ms-crm-HeaderTile");
	
	
	public static final By priority_enable = By.cssSelector("div#header_prioritycode_c");
	public static final By priority_dropDown_ID = By.cssSelector("select#header_prioritycode_i");
	public static final By saveButtonFromCase = By.xpath("//img[contains(@id,'savefooter')]");

	public static final By type_dropDown_ID = By.cssSelector("select#casetypecode_i");
	public static final By escalated_dropDown_ID = By.cssSelector("select#isescalated_i");
	public static final By EscalatedCase = By.id("Escalated_label");
	public static final By escalated_enable = By.cssSelector("div#isescalated");
	public static final By OwnerCase = By.id("Owner_label");
	public static final By OwnerCaselink = By
			.xpath("//span[@class='ms-crm-Lookup-Item'  and @tabindex='0' and @id='header_ownerid_lookupValue']");
	// public static final By OwnerCaselink=By.id("header_ownerid_lookupValue");
	// public static final By
	// oldManager=By.id("parentsystemuserid_lookupValue");
	public static final By Manager = By
			.xpath("//td[@id='parentsystemuserid_c']//following::span[@id='parentsystemuserid_lookupValue']");
	public static final By site_edit = By.id("siteid_ledit");
	public static final By caseStatus_Dropdown_enable = By.cssSelector("div#statuscode");
	public static final By caseStatus_List = By.cssSelector("select#statuscode_i");
	public static final By statusLabel = By.xpath("//div[@id='statuscode']//label[@id='Status Reason_label']");
	public static final By resolution_Type_enable = By.cssSelector("div#resolutionType_id");
	public static final By resolution_type_dropdown = By.cssSelector("select#resolutionType_id_i");
	public static final By resolutionTypeLabel = By
			.xpath("//div[@id='resolutionType_id']//label[@id='Resolution Type_label']");
	public static final By cancelResolveCasebtn = By.id("cancel_id");
	public static final By cancelCasebtn = By
			.xpath("//li[contains(@id,'Cancel')]//span/a/span[contains(@command,'Cancel')]");
	public static final By Cancel_enable = By.cssSelector("div#statusCode_id");
	public static final By Cancel_dropdown = By.cssSelector("select#statusCode_id_i");
	public static final By crossimg = By.id("imgCross");
	public static final By escalatedBy_enble = By.cssSelector("div#cxp_escalatedbyid");
	public static final By lockimg = By.cssSelector("span#cxp_escalatedbyid_lock");
	public static final By escaltedlable = By.cssSelector("lable#Escalated_label");
	public static final By viewDropdown = By.xpath("//img[@alt='Select a view']");
	public static final By selectedView = By.xpath("//a[@title='Select a view']/span");
	public static final By MyTeamEscalatedCaseView = By
			.xpath("//li/a[2]/span//span[@title=\"My Team's Escalated Cases\"]");

	// New Development

	public static final By createdOn = By.xpath(
			"//span[@id='header_createdon_cl']//following::div[@id='header_createdon']/div[1]/label");
	public static final By Followupdate = By.id("followupby_iDateInput");
	public static final By FollowupByfield = By.xpath("//span[@id='followupby_cl']");
	public static final By followUpByColumnTable = By.xpath("//table[@title='Follow Up By']");
	public static final By followUpByColumnTable_Queue = By.xpath("//table[@title='Follow Up By (Object)']");
	public static final By MyTeamAssignedCaseView = By
			.xpath("//li/a[2]/span//span[@title=\"My Team's Assigned Cases\"]");
	
	public static final By CasesIamWorkingOnView = By.xpath("//li/a[2]/span//span[@title=\"Cases I am Working On\"]");
	public static final By createdOnColumn = By
			.xpath("//span[@class='ms-crm-List-Sortable'  and @tabindex='0' and @title='Sort by Created On']");
	public static final By Name_CaseMetric = By.id("cxp_name_cl");
	public static final By Nametext_CaseMetric = By.id("Name_label");
	// public static final By Owner_CaseMetric=By.id("ownerid_cl");
	public static final By Ownertext_CaseMetric = By.id("ownerid_cl");
	public static final By Owner_CaseMetric = By
			.xpath("//span[@class='ms-crm-Lookup-Item'  and @tabindex='0' and @id='ownerid_lookupValue']");
	public static final By Case_CaseMetric = By.xpath("//span[text()='Case']");
	public static final By casetext_CaseMetric = By
			.xpath("//span[@class='ms-crm-Lookup-Item'  and @tabindex='0' and @id='cxp_caseid_lookupValue']");
	public static final By Queue_CaseMetric = By.id("cxp_queueid_cl");
	public static final By Queuetext_CaseMetric = By.xpath("//label[@id='Queue_label']/div");
	public static final By Status_CaseMetric = By.id("cxp_casestatus_cl");
	public static final By Statustext_CaseMetric = By.xpath("//label[@id='Case Status_label']/div");
	public static final By StartTime_CaseMetric = By.id("cxp_casetimingstarttime_cl");
	public static final By StartTimetext_CaseMetric = By.xpath("//label[@id='Case Timing Start Time_label']/div");
	public static final By StatusReason_CaseMetric = By.id("cxp_casestatusreason_cl");
	public static final By StatusReasontext_CaseMetric = By.xpath("//label[@id='Case Status Reason_label']/div");
	public static final By EndTime_CaseMetric = By.id("cxp_casetimingendtime_cl");
	public static final By EndTimetext_CaseMetric = By.xpath("//label[@id='Case Timing End Time_label']/div");
	public static final By CaseCategory_CaseMetric = By.id("cxp_casecategoryid_cl");
	public static final By CaseCategorytext_CaseMetric = By.xpath("//label[@id='Case Category_label']/div");
	public static final By CaseTimingDuration_CaseMetric = By.id("cxp_casetimingduration_cl");
	public static final By CaseTimingDurationtext_CaseMetric = By
			.xpath("//label[@id='Case Timing Duration_label']/div");
	public static final By CaseInquiryType_CaseMetric = By.id("cxp_caseinquirytypeid_cl");
	public static final By CaseInquiryTypetext_CaseMetric = By.xpath("//label[@id='Case Inquiry Type_label']/div");
	public static final By FormattedDuration_CaseMetric = By.id("cxp_formattedcasetimingduration_cl");
	public static final By FormattedDurationtext_CaseMetric = By
			.xpath("//label[@id='Formatted Case Timing Duration_label']/div");
	public static final By CaseInquirySubType_CaseMetric = By.id("cxp_caseinquirysubtypeid_cl");
	public static final By CaseInquirySubTypetext_CaseMetric = By
			.xpath("//label[@id='Case Inquiry Sub Type_label']/div");
	public static final By lookForAdvFind = By.id("slctPrimaryEntity");
	public static final By SavedViewAdvFind = By.id("savedQuerySelector");
	public static final By ResultsAdvFind = By.xpath("//a[contains(@id,'Results')]/span/span/img");
	
	// Activities
	public static final By moreActivityButton= By.xpath("//a[@id='moreActivitiesButton']");
	public static final By emailActivity = By.xpath("//a[@id='moreActivitiesButton']//following::li[@id='AddemailButton']");
	
	// Notification Message
	public static final By copyNotification = By.xpath("//div[@id='Notification0']"); 
	//validating field data
	
	public static final By customerName = By.xpath("//span[@id='customerid_lookupValue']");
	public static final By owner = By.xpath("//span[@id='header_ownerid_lookupValue']"); 
	public static final By origin = By.xpath("//label[@id='Origin_label']");
	public static final By caseStatusvalue = By.xpath("//label[@id='Status Reason_label']");
	public static final By casetitle = By.xpath("//label[@id='Case Title_label']"); 
	public static final By casePriority = By.xpath("//label[@id='Priority_label']"); 
	
	
	

}
