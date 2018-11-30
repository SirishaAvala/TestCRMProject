package pageObjects;

import org.openqa.selenium.By;

public class MarketingListPage {

	public static final By NameML_enable = By.cssSelector("div#listname");
	public static final By NameML_edit = By.cssSelector("input#listname_i");
	public static final By TypeML_enable = By.cssSelector("div#type");
	public static final By TypeML_edit = By.cssSelector("select#type_i");
	public static final By TargatedAtML_enable = By.cssSelector("div#createdfromcode");
	public static final By TargatedAtML_edit = By.cssSelector("select#createdfromcode_i");
	public static final By ManageMemberMLbtn = By.xpath("//a[@class='ms-crm-Menu-Label']//preceding::span[contains(@command,'ManageMembersForm')]");
	public static final String ManageMemberbtn="//a[@class='ms-crm-Menu-Label']//preceding::span[contains(@command,'ManageMembersForm')]";
	public static final By NameCampaingn_enable = By.cssSelector("div#name");
	public static final By NameCampaingn_edit = By.cssSelector("input#name_i");
	//public static final String camapignCode="//span[text()='Campaign Code']//following::td[@id='codename_d']//label[@id='Campaign Code_label']/div[1]";
	public static final String camapignCode="//td[contains(@title,'CMP')]";

}
