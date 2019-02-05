package kolyom.co.il.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

public class ProjectsPage extends BasePage{

	@FindBy(css="#mainview-wrapper #add-project-button")
	WebElement addNewProjectBtn;
	@FindBy(css="#mainview-wrapper #delete-project-button")
	WebElement deleteProjectBtn;
	
	@FindBy(css="#add-project-modal #project-name")
	WebElement newProjectName;
	@FindBy(css="#add-project-modal #project-domain")
	WebElement newProjectDomain;
	@FindBy(css="#add-project-modal #keywords-textarea")
	WebElement newProjectKeyWords;
	@FindBy(css="#add-project-modal #add-project-submit-button")
	WebElement newProjectSubmitBtn;
	@FindBy(css="#add-project-modal button[data-dismiss='modal']")
	WebElement newProjectCancelBtn;
	
	//delete projects popup
	@FindBy(css=".swal-modal .swal-footer .swal-button--confirm")
	WebElement deleteProjectsDeleteBtn;
	@FindBy(css=".swal-modal .swal-footer .swal-button--cancel")
	WebElement deleteProjectsCancelBtn;
	
	@FindBy(css="#projects-table #checkbox-select-all + .fa-check")
	WebElement selectAllrowsBtn; 
	
	@FindBy(css="#projects-table #checkbox-select-all")
	WebElement selectAllrows;
	//WebElement[] tableTrs;
	
	@FindBy(css="#headingTwo .project-title-line-container")
	WebElement singleProjectPageContainer;
	
	
	
	public ProjectsPage(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * Fill the new project form
	 * @param i_projectName - name for the project in the system
	 * @param i_domainName - the domain that will be tested
	 * @param i_keyWords - array of keywords to check in the domain
	 * @param i_create - submit to form or cancel 
	 * NOTE: when canceling - the form still holds last known information, and we need to clear all fields
	 */
	public void createNewProject(String i_projectName, String i_domainName, String[] i_keyWords, boolean i_create) {
		click(addNewProjectBtn);
		waitForElement(newProjectName);
		
		fillText(newProjectName, i_projectName);
		fillText(newProjectDomain, i_domainName);
		fillArrayOfText(newProjectKeyWords, i_keyWords,",");
		
		if(i_create == true) {
			click(newProjectSubmitBtn);
			waitForElement(singleProjectPageContainer);
			
		}else {
			click(newProjectCancelBtn);
		}
	}
	
	public void clickProject(String i_projectName) {
		List <WebElement> projectesLinks = getDynamicElementListByCss("#projects-table tr td a");
		WebElement projectToClick = null;
		for (WebElement el : projectesLinks) {
			if(el.getText().equals(i_projectName)) {
				projectToClick = el;
			}
		}
		
		if(projectToClick != null) {
		click(projectToClick);
		}
	}
	
	/**
	 * Delete project
	 * @param i_confirmDelete - set to true to confirm projects delete
	 */
	public void deleteProject(boolean i_confirmDelete) {
		click(deleteProjectBtn);
		waitForElement(deleteProjectsDeleteBtn);
		if(i_confirmDelete == true) {
			click(deleteProjectsDeleteBtn);
		}else {
			click(deleteProjectsCancelBtn);
		}
	}
	
	/**
	 * Delete all projects in the table
	 */
	public boolean deleteAllProjects() {
		click(selectAllrowsBtn);
		setCheckboxState(selectAllrows,true);
		deleteProject(true);
		boolean success = false;
		if(countDynamicElementByCss("#projects-table tr.row") == 0 ) {
			success = true;	
		}
		return success;
	}
	
	
	
	
	
	
	
}
