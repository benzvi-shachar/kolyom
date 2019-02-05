package kolyom.co.il.pages.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import kolyom.co.il.pages.BasePage;

public class BaseTest {
	WebDriver driver;
	String startPageUrl = "https://www.kolyom.co.il/";
	BasePage bp;
	
	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\automation\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(startPageUrl);
		bp = new BasePage(driver);
	}

	@AfterMethod
	public void close() {
		driver.quit();
	}

	
	@Test
	/*
	 * Test Header component
	 * Test the clicking on the logo changes navigation the the dashboard
	 * */
	public void clickLogo() {
		bp.getHeader().clickLogo();
		
		String actual = driver.getCurrentUrl();
		String expected = "https://www.kolyom.co.il/";
		Assert.assertEquals(actual,expected);	
	}
	
	@Test
	/* Test Header component
	 * Test logout
	 * */
	public void logout() {
		bp.getHeader().logout();
		
		String actual = driver.getCurrentUrl();
		String expected = "https://www.kolyom.co.il/login";
		Assert.assertEquals(actual.contains(expected),true);
	}
	
	@Test
	/* Test Sidebar component
	 * Test navigation switch to "dashboard" page
	 * */
	public void clickDashboard() {
		bp.getSidebar().switchToDashboard();
		
		String actual = driver.getCurrentUrl();
		String expected = "https://www.kolyom.co.il/";
		Assert.assertEquals(actual,expected);
	}
	
	@Test
	/* Test Sidebar component
	 * Test navigation switch to "my projects" page
	 * */
	public void clickMyProjects() {
		bp.getSidebar().switchToMyProjects();
		
		String actual = driver.getCurrentUrl();
		String expected = "https://www.kolyom.co.il/projects";
		Assert.assertEquals(actual,expected);
	}
	
	@Test
	/* Test Sidebar component
	 * Test navigation switch to "Site insights" page
	 * */
	public void clickSiteInsights() {
		bp.getSidebar().switchToSiteInsights();
		
		String actual = driver.getCurrentUrl();
		String expected = "https://www.kolyom.co.il/site-insights";
		Assert.assertEquals(actual,expected);
	}
}
