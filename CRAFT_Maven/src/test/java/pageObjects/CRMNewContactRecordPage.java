package pageObjects;

import org.openqa.selenium.By;

/**
 * UI Map for Login Page 
 */

public class CRMNewContactRecordPage {

		public static final By contactEntity = By.cssSelector("a#nav_conts img");
		public static final By decisionEntity=By.xpath("//span[contains(@id,'conts')][1]");
		
		//Buttons
		
		public static final By deactivateButton = By.xpath("//li[contains(@id,'Deactivate')]//span/a/span[contains(@command,'Deactivate')]");
		public static final By deleteButton = By.xpath("//li[contains(@id,'Delete')]//span/a/span[contains(@command,'Delete')]");
		public static final By connectButton = By.xpath("//li[contains(@id,'Connect')]//span/a/span[contains(@command,'Connect')]");
		public static final By allNotes =By.xpath("//li[@id='contact|NoRelationship|Form|Cxp.Contact.AllNotes']/span");
		
		// Field Values 
		public static final By FullName = By.cssSelector("div#fullname");

		public static final By firstName_enable = By.xpath("//div[contains(@id,'firstname') and @data-attributename='firstname']");
		public static final By firstName_edit = By.xpath("//input[contains(@id,'firstname')]"); 
		
		public static final By lastName_enable = By.xpath("//div[contains(@id,'lastname') and @data-attributename='lastname']");
		public static final By lasttName_edit = By.xpath("//input[contains(@id,'lastname')]");
		
		public static final By connectionRole_enable = By.xpath("//div[contains(@id,'cxp_connectionroleid') and @data-attributename='cxp_connectionroleid']");
		public static final By connectionRole_edit = By.xpath("//input[contains(@id,'cxp_connectionroleid')]");
		
		public static final By jobTitle_enable = By.cssSelector("div#jobtitle");
		public static final By jobTitle_edit = By.cssSelector("input#jobtitle_i");
		
		public static final By accountName_enable = By.cssSelector("div#parentcustomerid");
		public static final By accountName_edit = By.cssSelector("input#parentcustomerid_ledit");
		
		public static final By Email_enable = By.cssSelector("div#emailaddress1");
		public static final By Email_edit = By.cssSelector("input#emailaddress1_i");
		
		public static final By primaryEmailAddress_Enable = By.cssSelector("div#cxp_primaryemail");
		public static final By primaryEmailAddress_edit = By.cssSelector("input#cxp_primaryemail_i");
		public static final By primaryAddress_Enable = By.xpath("//span[@id='address1_composite_cl']");
		public static final By primaryAddress_Edit = By.xpath("//textarea[@id='address1_composite_i']");

		public static final By homePhone_enable = By.cssSelector("div#telephone1");
		public static final By homePhone_edit = By.cssSelector("input#telephone1_i");
		
		public static final By businessPhone_enable = By.cssSelector("div#telephone2");
		public static final By businessPhone_edit = By.cssSelector("input#telephone2_i");
		
		public static final By homePhone_text = By.xpath("//div[@id='telephone1']//following::label[@id='Home Phone_label']/a");
		public static final By businessPhone_text = By.xpath("//div[@id='telephone2']//following::label[@id='Business Phone_label']/a");
		
		public static final By mobilePhone_enable = By.cssSelector("div#mobilephone");
		public static final By mobilePhone_edit = By.cssSelector("input#mobilephone_i");
		
		public static final By contactMethod_parent_ID = By.cssSelector("div#preferredcontactmethodcode");
		public static final By contactMethod_dropDown_ID = By.cssSelector("select#preferredcontactmethodcode_i");
		
		public static final By address = By.cssSelector("div#address1_composite");
		
		public static final By street1_enable = By.xpath("//span[@id='address1_composite_compositionLinkControl_address1_line1_cl_span']");
		public static final By street1_edit = By.xpath("//input[@id='address1_composite_compositionLinkControl_address1_line1_i']");
		
		public static final By street2_enable = By.xpath("//span[@id='address1_composite_compositionLinkControl_address1_line2_cl_span']");
		public static final By street2_edit = By.xpath("//input[@id='address1_composite_compositionLinkControl_address1_line2_i']");
		
		public static final By street3_enable = By.xpath("//span[@id='address1_composite_compositionLinkControl_address1_line3_cl_span']");
		public static final By street3_edit = By.xpath("//input[@id='address1_composite_compositionLinkControl_address1_line3_i']");
		
		public static final By city_enable = By.xpath("//span[@id='address1_composite_compositionLinkControl_address1_city_cl_span']");
		public static final By city_edit = By.xpath("//input[@id='address1_composite_compositionLinkControl_address1_city_i']");
		
		public static final By state_enable = By.xpath("//span[@id='address1_composite_compositionLinkControl_address1_stateorprovince_cl_span']");
		public static final By state_edit = By.xpath("//input[@id='address1_composite_compositionLinkControl_address1_stateorprovince_i']");

		public static final By postalCode_enable = By.xpath("//span[@id='address1_composite_compositionLinkControl_address1_postalcode_cl_span']");
		public static final By postalCode_edit = By.xpath("//input[@id='address1_composite_compositionLinkControl_address1_postalcode_i']");

		public static final By country_enable = By.xpath("//span[@id='address1_composite_compositionLinkControl_address1_country_cl_span']");
		public static final By country_edit = By.xpath("//input[@id='address1_composite_compositionLinkControl_address1_country_i']");

		public static final By contact_CaseView=By.cssSelector("img#contactcasessgrid_openAssociatedGridViewImageButtonImage");
		public static final By contact_CaseSearch=By.cssSelector("input#crmGrid_incident_customer_contacts_findCriteria");
				
		
		
		
		// Done Button Click
		public static final By doneButtonClick_fullName = By.cssSelector("button#fullname_compositionLinkControl_flyoutLoadingArea-confirm");
		public static final By doneButtonClick_address = By.cssSelector("button#address1_composite_compositionLinkControl_flyoutLoadingArea-confirm");
		
		//fields for case save
		public static final By createCase=By.xpath("//img[@alt='Add Case record.']");
		public static final By saveButtonFromContact=By.xpath("//button[contains(@id,'save_button')]");
		
		//invalid details:
		public static final By homePhone_Message=By.xpath("//span[text()='Please enter a valid home phone number']");
		public static final By mobilePhone_Message=By.xpath("//span[text()='Please enter a valid mobile phone number']");
		public static final By businessPhone_Message=By.xpath("//span[text()='Please enter a valid business phone number']");
		public static final By email_Message=By.xpath("//div[@notificationid='EMAIL']//span");
}		