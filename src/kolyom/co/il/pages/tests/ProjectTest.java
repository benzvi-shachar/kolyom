package kolyom.co.il.pages.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import kolyom.co.il.pages.ProjectPage;

public class ProjectTest {
	WebDriver driver;
	String startPageUrl = "https://www.kolyom.co.il/";
	ProjectPage pp;
	
	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\automation\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(startPageUrl);
		
		pp = new ProjectPage(driver);
		pp.getSidebar().switchToMyProjects();
		
	}

	@AfterClass
	public void close() {
		driver.quit();
	}

	
	@Test
	/*
	 * Add keywords to project
	 * */
	public void addKeywordsToProject() {
		
		pp.clickProject("man");
		pp.refreshWebElements();
		pp.addKeywords( new String[] { "top 10 deals", "clean water", "great deals" });
		
		String actual = driver.getCurrentUrl();
		String expected = "https://www.kolyom.co.il/projects/";
		Assert.assertEquals(actual.contains(expected),true);	
		pp.getSidebar().switchToMyProjects();
	}
}
