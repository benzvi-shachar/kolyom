package kolyom.co.il.pages;

import org.openqa.selenium.WebDriver;

import kolyom.co.il.components.HeaderComponent;
import kolyom.co.il.components.SideBarComponent;

public class BasePage extends BasePageFunctions{
	/*
	 * the is for main structure of the system, will hold "header" , "sidebar", some main area 
	 *  base page example : dash board */
	LoginPage lp;
	HeaderComponent header;
	SideBarComponent sidebar;
	
	public BasePage(WebDriver driver) {
		super(driver);
		/* create a login page and do a valid login action */
		lp = new LoginPage(driver);
		lp.login();
		
		header = new HeaderComponent(driver);
		sidebar = new SideBarComponent(driver);
	}
	
	public HeaderComponent getHeader() {
		return header;
	}
	
	public SideBarComponent getSidebar() {
		return sidebar;
	}
	

}
