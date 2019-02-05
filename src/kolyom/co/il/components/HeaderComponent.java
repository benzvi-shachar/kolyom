package kolyom.co.il.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import kolyom.co.il.pages.BasePageFunctions;

public class HeaderComponent extends BasePageFunctions {
	@FindBy(css="header a.navbar-brand")
	WebElement logo;
	
	@FindBy(css="#search-input")
	WebElement searchInput;
	
	@FindBy(css="ul.navbar-nav.navbar-right>li.dropdown:nth-of-type(1n) a")
	WebElement userInfo;
	
	@FindBy(css="ul.navbar-nav.navbar-right>li.dropdown:nth-of-type(1n) ul")
	WebElement userInfoPop;
	
	@FindBy(css="ul.navbar-nav.navbar-right>li.dropdown:nth-of-type(1n) ul a[href*='logout']")
	WebElement userInfoPopLogout;
	
	
	public HeaderComponent(WebDriver driver) {
		super(driver);	
	}
	
	/**
	 * clicking on the logo needs to navigate user to the "dashboard" page
	 * */
	public void clickLogo() {
		click(logo);
	}
	
	/***
	 * Search for project in the system
	 * @param i_projectName - name to search
	 */
	public void search(String i_projectName) {
		fillText(searchInput, i_projectName);
		
		//TODO return click the project if project exits
		
	}
	
	/**
	 * Logout of the system 
	 * */
	public void logout() {
		click(userInfo);
		waitForElement(userInfoPopLogout);
		click(userInfoPopLogout);
	}
	
}
