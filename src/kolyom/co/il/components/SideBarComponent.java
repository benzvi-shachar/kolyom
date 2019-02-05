package kolyom.co.il.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import kolyom.co.il.pages.BasePageFunctions;

public class SideBarComponent extends BasePageFunctions{
	@FindBy(css="aside nav.sidebar li a[title='Dashboard']")
	WebElement dashboard;
	
	@FindBy(css="aside nav.sidebar li a[title='Site Insights']")
	WebElement siteInsights;

	@FindBy(css="aside nav.sidebar li a[title='My Projects']")
	WebElement myProjects;
	
	public SideBarComponent(WebDriver driver) {
		super(driver);	
	}
	
	/**
	 * click on "dashboard" link
	 */
	public void switchToDashboard() {
		click(dashboard);
	}
	
	/**
	 * click on"site insights" link
	 */
	public void switchToSiteInsights() {
		click(siteInsights);
	}
	
	/**
	 * click on "My Projects" link
	 */
	public void switchToMyProjects() {
		click(myProjects);
	}
}
