package pageObjects;

import org.openqa.selenium.By;

public class CRMActivityPage {
	
	public static final By activitytab = By.xpath("//span[text()='Activities']");
	
	//public static final By subjectNewTask = By.id("subject_i");
	public static final By activitySave=By.xpath("//a[@class='ms-crm-Menu-Label']//preceding::span[text()=' Save ']");
	public static final By activityMarkomplete = By.xpath("//a[@class='ms-crm-Menu-Label']//preceding::span[text()=' Mark Complete ']");
	public static final By activitySaveAndClose=By.xpath("//div[@id='commandContainer3']/ul/li[3]/span/a");
	public static final By activityForm=By.xpath("//div[@id='commandContainer3']/ul/li[4]/span/a");
	public static final By activitytaskbtn=By.xpath("//a[@class='ms-crm-Menu-Label']//preceding::span[contains(@command,'CreateTask')]");
	public static final By activityemailbtn=By.xpath("//a[@class='ms-crm-Menu-Label']//preceding::span[contains(@command,'CreateEmail')]");
	public static final By activityappointmentbtn=By.xpath("//a[@class='ms-crm-Menu-Label']//preceding::span[contains(@command,'CreateAppointment')]");
	public static final By activityphoneCalltbtn=By.xpath("//a[@class='ms-crm-Menu-Label']//preceding::span[contains(@command,'CreatePhoneCall')]");
	public static final By activityServiceActivitytbtn=By.xpath("//a[@class='ms-crm-Menu-Label']//preceding::span[contains(@command,'CreateServiceAppointment')]");
	
	public static final By subjectNewtask_enable = By.cssSelector("div#subject");
	public static final By subjectNewtask_edit = By.xpath("//input[@id='subject_i']");
	
	//New Email Page
	public static final By activityToimg_enable=By.cssSelector("div#to");
	public static final By activityToimg_edit=By.cssSelector("input#to_ledit_multi");
	
	//public static final By activityToimg=By.xpath("//img[@id='to_lookupSearchIcon']");
	public static final By activityCcimg_enable=By.cssSelector("div#cc");
	public static final By activityCcimg_edit=By.cssSelector("input#cc_ledit_multi");
	//public static final By activityCcimg=By.xpath("//td[@id='cc_lookupSearch']/img[@id='cc_i']");
	public static final By activityBccimg_enable=By.cssSelector("div#bcc");
	//public static final By activityBCcimg=By.xpath("//td[@id='bcc_lookupSearch']/img[@id='bcc_i']");
	public static final By activityBccimg_edit=By.cssSelector("input#bcc_ledit_multi");
	public static final By activitymailSubject_enable=By.cssSelector("div#subject");
	public static final By activitymailSubject_edit=By.cssSelector("input#subject_i");
	public static final By activitymailbody_enable=By.xpath("//body");
	public static final By sendmailbtn=By.xpath("//span[text()='Send']");
	public static final By mailInsertTemplatebtn=By.xpath("//span[text()='Insert Template']");
	public static final By mailInsertArticlebtn=By.xpath("//span[text()='Insert Article']");
	public static final By mailInsertSignaturebtn=By.xpath("//span[text()='Insert Signature']");
	
	//New Service Activity Page
	
	public static final By serviceActivitySubject=By.id("subject");
	public static final By servicetxtboxOnServiceActivity=By.id("serviceid_ledit");
	public static final By scheduleStartTimeServiceActivity=By.xpath("//table[@id='scheduledstart']/tbody/tr/td/input");
	public static final By scheduleEndTimeServiceActivity=By.xpath("//table[@id='scheduledend']/tbody/tr/td/input");
	public static final By saveServiceActivitybtn=By.xpath("//a[contains(@id,'Save-Large')]");
	public static final By servicetxtboxOnServiceActivity_enable=By.xpath("//table[@id='serviceid_lookupTable']/tbody/tr/td/div");
	public static final By servicetxtboxOnServiceActivity_edit=By.cssSelector("input#serviceid_ledit");
	
	//Activity from Case
	
	public static final By fromEnable=By.xpath("//span[@id='from_cl']");
	public static final By fromedit=By.xpath("//td[@id='from_d']/div");
	
	
}
