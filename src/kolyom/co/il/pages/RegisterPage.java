package kolyom.co.il.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePageFunctions {
	@FindBy(css="#register-form #email-input")
	WebElement emailInput;
	@FindBy(css="#register-form #signup-input-password")
	WebElement passwordInput;
	@FindBy(css="#register-form input[name='retype-password']")
	WebElement retypePasswordInput;
	
	@FindBy(css="#register-form #register-submit-button")
	WebElement step1SubmitBtn;
	String emailErrorCss = "#parsley-id-5 > li";
	WebElement emailError;
	String passwordErrorCss = "#parsley-id-7 > li";
	WebElement passwordError;
	String passwordRetypeErrorCss = "#parsley-id-9 > li";
	WebElement passwordRetypeError;
	

	@FindBy(css="a.btn[href='/']")
	WebElement signinLink;
	@FindBy(css="#phone-panel")
	WebElement phonePanel;
	
	public RegisterPage(WebDriver driver) {
		super(driver);	
	}
	
	/***
	 * submitting empty form
	 */
	public void register() {
		register(null, null, null);
	}
	
	/***
	 * fill registration form with email, don't fill other fields
	 */
	public void register(String i_email) {
		register(i_email, null, null);
	}
	
	/***
	 * fill registration form with email and password, don't fill other fields
	 */
	public void register(String i_email , String i_password) {
		register(i_email, i_password, null);
	}
	
	/***
	 * fill all fields with some input
	 */
	public void register(String i_email , String i_password, String i_retypePassword) {
		fillText(emailInput, i_email);
		fillText(passwordInput, i_password);
		fillText(retypePasswordInput, i_retypePassword);
		click(step1SubmitBtn);
	}

	/**
	 * get email error, when its shown, if no error exists return null
	 * */
	public String getEmailError() {
		return getDynamicElementTextByCss(emailErrorCss);
	}
	
	/**
	 * get password error, when its shown, if no error exists return null
	 * */
	public String getPasswordError() {
		return getDynamicElementTextByCss(passwordErrorCss);
	}

	/**
	 * get retyped password error, when it shown, if no error exists return null
	 * */
	public String getPasswordRetypeError() {
		return getDynamicElementTextByCss(passwordRetypeErrorCss);
	}
	
	public Boolean isTwoFactorAuthShown() {
		waitForElement(phonePanel);
		return isElementVisble(phonePanel);
	}
	
	/**
	 * click the "sign up" link
	 * */
	public void signupClick() {
		click(signinLink);
	}
}
