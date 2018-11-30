package pageObjects;

import org.openqa.selenium.By;

/**
 * UI Map for Login Page 
 */

public class CRMLoginPage {
	
		// Single Sign On	
		public static final By signInTitle = By.xpath("//div[@id='loginHeader']");
		public static final By userEmailAddress = By.xpath("//input[@type='email']");
		public static final By nextButton = By.xpath("//input[@type='submit']");
		public static final By loginValidation = By.xpath("//img[@alt='Microsoft Dynamics 365']");
		
		// Text boxes
		public static final By txtUsername = By.cssSelector("input#cred_userid_inputtext");
		public static final By txtPassword = By.cssSelector("input#cred_password_inputtext");
		
		// Buttons
		public static final By checkBox = By.cssSelector("input#cred_keep_me_signed_in_checkbox");
		public static final By btnLogin = By.cssSelector("button#cred_sign_in_button");
		
		//Already existing Account
		public static final By useAnotherAccountIcon = By.cssSelector("img.ad_glyph.use_another_account");
									
		//CRMload
		public static final By chart=By.cssSelector("label#Component8738712_findHintText");
	}
	
