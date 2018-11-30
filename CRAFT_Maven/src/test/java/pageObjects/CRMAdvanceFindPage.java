package pageObjects;


import org.openqa.selenium.By;




public class CRMAdvanceFindPage {
	
	public static final By advanceFindIcon= By.xpath("//span[@id='AdvFindSearch']/a");
	public static final By lookForDropdown =By.xpath("//select[@id='slctPrimaryEntity']");
	public static final By useSavedView =By.xpath("//select[@id='savedQuerySelector']");
	public static final By editColumn = By.xpath("//a[@id='Mscrm.AdvancedFind.Groups.View.EditColumns-Medium']");
	public static final By addColumn= By.xpath("//div[@id='_TBAddColumnsoActivefalse']");
	public static final By editColumnFormFrame =By.xpath("//iframe[@id='viewEditor']");
	
	
	//Example
	public static final By columnName =By.xpath("//label[.='Contact ID']");
	public static final By recordTypeLabel=By.xpath("//div[@id='trEntityList']//label");
	public static final By okButton = By.xpath("//button[.='OK']");
	public static final By result=By.xpath("//a[@id='Mscrm.AdvancedFind.Groups.Show.Results-Large']");
	public static final By selectFilterLabel= By.xpath("//div[@title='Select']");
	public static final By selectFilterDropdown =By.xpath("//select[@id='advFindE_fieldListFLDCTL']");
	// fullname filter 
	public static final By enterTextFullName_enable=By.xpath("//div[@id='advFindEFGRP0FFLD1CCVALLBL']");
	public static final By enterTextFullName_edit=By.xpath("//input[@id='advFindEFGRP0FFLD1CCVALCTL']");
	public static final By fullnameEqualLabel =By.xpath("//div[@id='advFindEFGRP0FFLD1OPASCNTRL']"); 
	public static final By noRecordFound=By.xpath("//td[.='No Individual records are available in this view.']");
	public static final By contactID= By.xpath("//td//a[contains(text(), 'Lokesh')]//following::td[2]");
	public static final By fullnameColumnLabel=By.xpath("//th[@field='fullname']");
	public static final By editColumnHeader =By.xpath("//div[@id='dialogHeaderDesc']");
	
	public static final By fullnameequalLabel =By.xpath("//div[@id='advFindEFGRP1FFLD0OPASLBL']/span");
	public static final By fullnameequalDropdown =By.xpath("//select[@id='advFindEFGRP0FFLD1OPASCTL']");  
	
	//First name Filter 
	
	public static final By firstnameEqualLabel =By.xpath("//div[@id='advFindEFGRP0FFLD1OPASCNTRL']"); 
	public static final By firstnameequalLabel =By.xpath("//div[@id='advFindEFGRP1FFLD0OPASLBL']/span");
	public static final By firstnameequalDropdown =By.xpath("//select[@id='advFindEFGRP0FFLD1OPASCTL']");
	public static final By enterTextFirstname_enable=By.xpath("//div[@id='advFindEFGRP0FFLD1CCVALLBL']");
	public static final By enterTextFirstname_edit=By.xpath("//input[@id='advFindEFGRP0FFLD1CCVALCTL']");
	
	//Last name Filter 
	
	public static final By lastnameEqualLabel =By.xpath("//div[@id='advFindEFGRP0FFLD2OPASCNTRL']"); 
	public static final By lastnameequalLabel =By.xpath("//div[@id='advFindEFGRP1FFLD2OPASLBL']/span");
	public static final By lastnameequalDropdown =By.xpath("//select[@id='advFindEFGRP0FFLD2OPASCTL']");
	public static final By enterTextlastname_enable=By.xpath("//div[@id='advFindEFGRP0FFLD2CCVALLBL']");
	public static final By enterTextlastname_edit=By.xpath("//input[@id='advFindEFGRP0FFLD2CCVALCTL']");
		
	
	
	//created on filter
	public static final By createdOn_ON_Label =By.xpath("//div[@id='advFindEFGRP0FFLD2OPASCNTRL']");
	public static final By onDropdown =By.xpath("//select[@id='advFindEFGRP0FFLD2OPASCTL']");
	public static final By time_enable =By.xpath("//div[@id='advFindEFGRP0FFLD2CCVALLBL']");
	public static final By time_edit =By.xpath("//input[@id='advFindEFGRP0FFLD2CCVALCTL']");
	
	public static final By contactID_columnResult=By.xpath("//label[.='Contact ID']");
	
	
	//div[@id='advFindEFGRP0FFLD1OPASCNTRL']
	
}
