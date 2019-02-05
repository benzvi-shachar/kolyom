package kolyom.co.il.pages.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import kolyom.co.il.pages.LoginPage;

public class LoginTest {
	WebDriver driver;
	String startPageUrl = "https://www.kolyom.co.il/login";
	LoginPage lp;

	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\automation\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(startPageUrl);
		lp = new LoginPage(driver);	
	}

	@AfterMethod
	public void close() {
		driver.quit();
	}

	
	@Test
	/*
	 * Test simple login, just test that this is the relevant url
	 * */
	public void simpleLogin() {
		String  actual = driver.getTitle();
		String expected = "בדיקת מיקומים בגוגל בחינם | כליום";
		Assert.assertEquals(actual, expected);
	}
	
	@Test
	/*
	 * Test valid login
	 * */
	public void validLogin() {
		lp.login();
		
		String expectedUrl = "https://www.kolyom.co.il/";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}
	
	
	@Test
	/*
	 * Test forgot password link
	 * */
	public void forgotPassword() {;	
		lp.forgotPassword();
		
		String expectedUrl = "https://www.kolyom.co.il/recover";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}
	
	@Test
	/*
	 * Test create account link - register
	 * */
	public void createAccount() {
		lp.createAccount();
		
		String expectedUrl = "https://www.kolyom.co.il/register";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
	}
	

	/*TODO: add more TESTS*/
	/*
	 * non valid email format
	 * wrong email - empty password
	 * valid email - empty password
	 * valid email - wrong password
	 * */
}
