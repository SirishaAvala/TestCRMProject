package pageObjects;

import org.openqa.selenium.By;

/**
 * UI Map for Login Page 
 */

public class CRMNewLeadRecordPage {

		public static final By leadEntity = By.cssSelector("a#nav_leads img");
		
		// Field Values 		
		public static final By topic_enable = By.cssSelector("div#subject");
		public static final By topic_edit = By.cssSelector("input#subject_i");

		public static final By FullName = By.cssSelector("div#fullname");
		
		public static final By firstName_enable = By.cssSelector("div#fullname_compositionLinkControl_firstname");
		public static final By firstName_edit = By.cssSelector("input#fullname_compositionLinkControl_firstname_i");
		
		public static final By lastName_enable = By.cssSelector("div#fullname_compositionLinkControl_lastname");
		public static final By lasttName_edit = By.cssSelector("input#fullname_compositionLinkControl_lastname_i");
		
		public static final By jobTitle_enable = By.cssSelector("div#jobtitle");
		public static final By jobTitle_edit = By.cssSelector("input#jobtitle_i");
		
		public static final By Email_enable = By.cssSelector("div#emailaddress1");
		public static final By Email_edit = By.cssSelector("input#emailaddress1_i");

		public static final By businessPhone_enable = By.cssSelector("div#telephone1");
		public static final By businessPhone_edit = By.cssSelector("input#telephone1_i");
		
		public static final By mobilePhone_enable = By.cssSelector("div#mobilephone");
		public static final By mobilePhone_edit = By.cssSelector("input#mobilephone_i");
		
		public static final By company_enable = By.cssSelector("div#companyname");
		public static final By company_edit = By.cssSelector("input#companyname_i");
		
		public static final By websiteurl_enable = By.cssSelector("div#websiteurl");
		public static final By websiteurl_edit = By.cssSelector("input#websiteurl_i");
		
		public static final By address = By.cssSelector("div#address1_composite");
		
		public static final By street1_enable = By.cssSelector("div#address1_composite_compositionLinkControl_address1_line1");
		public static final By street1_edit = By.cssSelector("input#address1_composite_compositionLinkControl_address1_line1_i");
		
		public static final By street2_enable = By.cssSelector("div#address1_composite_compositionLinkControl_address1_line2");
		public static final By street2_edit = By.cssSelector("input#address1_composite_compositionLinkControl_address1_line2_i");
		
		public static final By street3_enable = By.cssSelector("div#address1_composite_compositionLinkControl_address1_line3");
		public static final By street3_edit = By.cssSelector("input#address1_composite_compositionLinkControl_address1_line3_i");
		
		public static final By city_enable = By.cssSelector("div#address1_composite_compositionLinkControl_address1_city");
		public static final By city_edit = By.cssSelector("input#address1_composite_compositionLinkControl_address1_city_i");
		
		public static final By state_enable = By.cssSelector("div#address1_composite_compositionLinkControl_address1_stateorprovince");
		public static final By state_edit = By.cssSelector("input#address1_composite_compositionLinkControl_address1_stateorprovince_i");

		public static final By postalCode_enable = By.cssSelector("div#address1_composite_compositionLinkControl_address1_postalcode");
		public static final By postalCode_edit = By.cssSelector("input#address1_composite_compositionLinkControl_address1_postalcode_i");

		public static final By country_enable = By.cssSelector("div#address1_composite_compositionLinkControl_address1_country");
		public static final By country_edit = By.cssSelector("input#address1_composite_compositionLinkControl_address1_country_i");

		// Done Button Click
		public static final By doneButtonClick_fullName = By.cssSelector("button#fullname_compositionLinkControl_flyoutLoadingArea-confirm");
		public static final By doneButtonClick_address = By.cssSelector("button#address1_composite_compositionLinkControl_flyoutLoadingArea-confirm");
		
		public static final By recordSearchBox = By.xpath("//*[@id='crmGrid_findCriteria']");
		//input#crmGrid_findCriteria
}		