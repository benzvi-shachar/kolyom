package kolyom.co.il.pages.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import kolyom.co.il.pages.RegisterPage;

public class RegisterTest {
	WebDriver driver;
	String startPageUrl = "https://www.kolyom.co.il/register";
	RegisterPage rp;

	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\automation\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(startPageUrl);
		rp = new RegisterPage(driver);
	}

	@AfterMethod
	public void close() {
		driver.quit();
	}
	
	/*
	 * NOTE: we will test for now only the first step of the register , as it has email and two factor auth
	 * TODO: fill and check tests
	 * */

	
	@Test
	/*
	 * Try register with an empty form
	 * */
	public void emptyRegister() {
		rp.register();
		
		String actual = rp.getEmailError().toLowerCase();
		String expected = "This value is required".toLowerCase();
		Assert.assertEquals(actual.contains(expected), true);
	}
	
	@Test
	/*
	 * Try register with only email input (not a valid email format)
	 * */
	public void registerOnlyNotValidEmail() {
		rp.register("tim");
		
		String actual = rp.getEmailError().toLowerCase();
		String expected = "This value should be a valid email".toLowerCase();
		Assert.assertEquals(actual.contains(expected), true);
	}
	
	@Test
	/*
	 * Try register with only email input  (valid email input)
	 * */
	public void registerOnlyValidEmail() {
		rp.register("tim@test.com");
		
		String actual = rp.getPasswordError().toLowerCase();
		String expected = "This value is required".toLowerCase();
		Assert.assertEquals(actual.contains(expected), true);
	}
	
	@Test
	/*
	 * Try register with email string and password string
	 * */
	public void registerWithEmailAndPassword() {		
		rp.register("tim@test.com","a22fadva");
		
		String actual = rp.getPasswordRetypeError().toLowerCase();
		String expected = "This value is required".toLowerCase();
		Assert.assertEquals(actual.contains(expected), true);
	}
	
	@Test
	/*
	 * Try register with NO email string, but with both passwords
	 * */
	public void registerWithPasswordsNoEmail() {
		rp.register(null,"a22fadva","a22fadva22");
		
		String actual = rp.getEmailError().toLowerCase();
		String expected = "This value is required".toLowerCase();
		Assert.assertEquals(actual.contains(expected), true);
	}
	
	@Test
	/*
	 * Try register with mismatching passwords, valid email
	 * */
	public void registerMissmatchingPasswords() {
		rp.register("tim@test.com","asdasd2","asdasdppp");
		
		String actual = rp.getEmailError().toLowerCase();
		String expected = "This value should be the same.".toLowerCase();
		Assert.assertEquals(actual.contains(expected), true);
	}
	
	
	
	@Test
	/*
	 * Try register with email, pasword, repassword
	 * */
	public void registerAllParamsStep1() {;
		rp.register("tim112d3@test.com","tomerAit27","tomerAit27");
				
		boolean  actual = rp.isTwoFactorAuthShown();
		boolean expected = true;
		Assert.assertEquals(actual, expected);
		
	}
	
	@Test
	/*
	 * click Sign in link
	 * */
	public void signin() {
		rp.signupClick();
		
		String  actual = driver.getCurrentUrl();
		String expected = "https://www.kolyom.co.il/login";
		Assert.assertEquals(actual.contains(expected), true);
	}
		
}
