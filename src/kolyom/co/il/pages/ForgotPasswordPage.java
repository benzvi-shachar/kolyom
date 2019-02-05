package kolyom.co.il.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ForgotPasswordPage extends BasePageFunctions {
	@FindBy(css="#resetInputEmail1")
	WebElement emailInput;
	@FindBy(css="button[type='submit']")
	WebElement submitBtn;
	
	String emailErrorCss = "#parsley-id-5";
	WebElement emailError;
	
	String popupTextCSS = ".swal-modal .swal-text";
	WebElement popupText;
	
	String popupOkBtnCss = ".swal-modal .swal-button.swal-button--confirm";
	WebElement popupOkBtn;
	
	public ForgotPasswordPage(WebDriver driver) {
		super(driver);	
	}
	
	/***
	 * fill email address and submit forgot password form
	 */
	public void forgotPassword(String i_email) {
		fillText(emailInput, i_email);
		click(submitBtn);
	}
	
	/**
	 * Function will return the email error string if it exists and shown,
	 * in case the error is empty / not shown , return null
	 * */
	public String getEmailError() {
		return getDynamicElementTextByCss(emailErrorCss);
	}
	
	/**
	 * Get the main text (Error text) of the popup
	 * */
	public String getPopupErrorMessage() {
		return getDynamicElementTextByCss(popupTextCSS);
	}
	
	public void closePopup() {
		popupOkBtn = getDynamicElementByCss(popupOkBtnCss);
	}
}
