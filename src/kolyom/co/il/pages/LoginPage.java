package kolyom.co.il.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePageFunctions{
	@FindBy(css="#email-input")
	WebElement emailInput;
	@FindBy(css="#password-input")
	WebElement passwordInput;
	@FindBy(css="#login-form button[type='submit']")
	WebElement loginFormSubmit;
	@FindBy(css="input[name='remember'")
	WebElement remembermeChkbox;
	
	@FindBy(css="a[href*='recover']")
	WebElement forgotPasswordLink;
	@FindBy(css="a[href*='register']")
	WebElement createAccountLink;
	
	
	//base page header
	@FindBy(css="header.topnavbar-wrapper")
	WebElement pageHeader;
	
	/*
	 * TODO: using remembermeChkbox in some function
	 * --- adding the errors shown when form not valid
	 * 
	 * 
	 * */
	
	public LoginPage(WebDriver driver) {
		super(driver);	
	}
	
	/***
	 * function will login with real and valid credentials
	 */
	public void login() {
		fillText(emailInput,"sbenzvi2@gmail.com");
		fillText(passwordInput,"NiloNilo");
		click(loginFormSubmit);
		//wait till the login page is shown
		
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOf(pageHeader));
	}
	
	/***
	 * function will try to login with credentials given to the function
	 * @param i_email - populate email input with
	 * @param i_password - populate password input with
	 */
	public void login(String i_email, String i_password) {
		fillText(emailInput,i_email);
		fillText(passwordInput,i_password);
		click(loginFormSubmit);
	}
	
	/**
	 * click the create account link
	 * */
	public void createAccount() {
		click(createAccountLink);
	}
	
	/**
	 * click forgot password link 
	 * */
	public void forgotPassword() {
		click(forgotPasswordLink);
	}	
}
