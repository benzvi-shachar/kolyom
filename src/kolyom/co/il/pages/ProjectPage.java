package kolyom.co.il.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProjectPage extends ProjectsPage{
	@FindBy(css="#remove-project-button")
	WebElement deleteProjectBtn;
	@FindBy(css=".swal-overlay .swal-button--confirm")
	WebElement deleteProjectPopupConfirmBtn;
	
	//@FindBy(css="a[title='Add Keywords']")
	WebElement addKeyWordsBtn;
	//@FindBy(css="#add-keyword-form #keyword-textarea")
	WebElement addKeyWordsPopupTextarea;
	//@FindBy(css="#add-keyword-form #add-keyword-submit-button")
	WebElement addKeyWordsPopupSubmitBtn;
	
	public ProjectPage(WebDriver driver) {
		super(driver);
	}
	
	public void refreshWebElements() {
		deleteProjectBtn = getDynamicElementByCss("#remove-project-button");
		addKeyWordsBtn = getDynamicElementByCss("a[title='Add Keywords']");
		
	}
	
	public void refreshWebElementsAddKeyWordsPopup() {
		addKeyWordsPopupTextarea = getDynamicElementByCss("#add-keyword-form #keywords-textarea");
		addKeyWordsPopupSubmitBtn = getDynamicElementByCss("#add-keyword-form #add-keyword-submit-button");
	}
	
	public void addKeywords(String[] i_keywords) {
		waitForElement(addKeyWordsBtn);
		click(addKeyWordsBtn);
		refreshWebElementsAddKeyWordsPopup();		
		fillArrayOfText(addKeyWordsPopupTextarea, i_keywords,",");
		click(addKeyWordsPopupSubmitBtn);
	}
	
	public void deleteProject() {
		click(deleteProjectBtn);
		deleteProjectPopupConfirmBtn = getDynamicElementByCss(".swal-overlay .swal-button--confirm");
		click(deleteProjectPopupConfirmBtn);
	}
	 	
}
