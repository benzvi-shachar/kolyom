package kolyom.co.il.pages.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import kolyom.co.il.pages.ProjectsPage;

public class ProjectsTest {
	WebDriver driver;
	String startPageUrl = "https://www.kolyom.co.il/";
	ProjectsPage pp;
	
	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\automation\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(startPageUrl);
		pp = new ProjectsPage(driver);
		pp.getSidebar().switchToMyProjects();
	}

	@AfterClass
	public void close() {
		driver.quit();
	}

	
	@Test
	/*
	 * create a project
	 * */
	public void createProject() {
		pp.createNewProject("walla", "www.walla.co.il", new String[] { "OneOne", "Two", "Three" }, true);
		
		String actual = driver.getCurrentUrl();
		String expected = "https://www.kolyom.co.il/projects/";
		Assert.assertEquals(actual.contains(expected),true);	
		pp.getSidebar().switchToMyProjects();
	}
	
	@Test
	/*
	 * open new project popup , but cancel
	 * */
	public void createProjectCancel() {
		pp.createNewProject("testim", "testim.io", new String[] { "אוטומציה", "בלוג", "בדיקות אוטומציה" }, false);
		
		String actual = driver.getCurrentUrl();
		String expected = "https://www.kolyom.co.il/projects/";
		Assert.assertEquals(actual.contains(expected),false);	
	}
	
	@Test(priority = 999)
	/**
	 * Delete all projects -last test 
	 */
	public void deleteAllProjects() {
		//pp.getSidebar().switchToMyProjects();
		boolean actual =  pp.deleteAllProjects();
		boolean expected = true;
		Assert.assertEquals(actual,expected);	
	}
	
	
}
