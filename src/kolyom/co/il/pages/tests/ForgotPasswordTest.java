package kolyom.co.il.pages.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import kolyom.co.il.pages.ForgotPasswordPage;

public class ForgotPasswordTest {
	WebDriver driver;
	String startPageUrl = "https://www.kolyom.co.il/recover";
	ForgotPasswordPage fpp;
	
	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\automation\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(startPageUrl);
		fpp = new ForgotPasswordPage(driver);
	}

	@AfterMethod
	public void close() {
		driver.quit();
	}

	
	@Test
	/*
	 * validate relevant error shown for a valid format but wrong email
	 * */
	public void emailDosentExists() {
		fpp.forgotPassword("tim@test.com");
		
		String actual = fpp.getPopupErrorMessage().toLowerCase();
		String expected = "email is not registered".toLowerCase();
		Assert.assertEquals(actual.contains(expected), true);	
	}
		
	@Test
	/*
	 * validate relevant error shown for a valid format but wrong email
	 * */
	public void wrongEmailformatAfterEmptyEmail() {
		emptyEmail();
		fpp.forgotPassword("tim@test");
		
		String  actual = fpp.getEmailError().toLowerCase();
		String expected = "This value should be a valid email.".toLowerCase();
		Assert.assertEquals(actual, expected);	
	}
    
	@Test
	/*
	 * validate relevant error shown for a valid format but wrong email
	 * */
	public void emptyEmail() {
		fpp.forgotPassword("");
		
		String  actual = fpp.getEmailError().toLowerCase();
		String expected = "this value is required.";
		Assert.assertEquals(actual, expected);
	}
	
	@Test
	/*
	 * TODO: check multiple inputs
	 * validate relevant error shown for a wrong formated email
	 * */
	public void wrongFormatEmail() {
		fpp.forgotPassword("rimrom");
		
		String  actual = fpp.getEmailError().toLowerCase();
		String expected = "this value is required.";
		Assert.assertEquals(actual, expected);
	}
	
	@Test
	/*
	 * ---DONT RUN this test ---
	 * valid email account to reset password for
	 * 
	 * */
	public void validEmail() {
		//DONT RUN this test 
		//ForgotPasswordPage fpp = new ForgotPasswordPage(driver);	
		//fpp.forgotPassword("sbenzvi2@gmail.com");
	}
	
}
