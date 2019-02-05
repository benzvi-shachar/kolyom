package kolyom.co.il.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageFunctions {
	WebDriver driver;
	JavascriptExecutor jse;
	   
	/***
	 * Constractor 
	 * @param driver - web driver to test with
	 */
	public BasePageFunctions(WebDriver driver) {
		this.driver = driver;
		this.jse = (JavascriptExecutor) driver;
		PageFactory.initElements(driver,this);
	}
	
	/**
	 * Scroll to element
	 * @param el - WebElement to find and to scroll to
	 * */
	public void scrollToElementByElement(WebElement el) {
		Coordinates coordinates = ((Locatable)el).getCoordinates();
	    coordinates.inViewPort();
	}
	
	
	/***
	 * Highlight a specific element, for better visual understanding of the current focused element  
	 * @param el - WebElement to highlight with color
	 */
	public void highlightElement(WebElement el) {
		scrollToElementByElement(el);
    	jse.executeScript("arguments[0].setAttribute('style', arguments[1]);", el, "color: red; border: 2px solid red;");
    	try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	jse.executeScript("arguments[0].setAttribute('style', arguments[1]);",  el, "");
	}

	/***
	 * Click on given WebElement
	 * @param el - WebElement to click
	 */
	public void click(WebElement el) {
		highlightElement(el);
		el.click();
	}
	
	/**
	 * Set WebElement with text
	 * @param el - WebElement to set text on 
	 * @param text - String to set the WebElement with
	 */
	public void fillText(WebElement el, String text) {
		highlightElement(el);
		el.clear();
		el.sendKeys(text);
	}
	
	/***
	 * 
	 * @param el - WebElemnt to fill
	 * @param text - array of text to fill by
	 * @param delimiter - string to add to each row of the text
	 */
	public void fillArrayOfText(WebElement el, String[] text, String delimiter) {
		String generalString = "";
		
		for (String singleText : text) {
			generalString = generalString + singleText + delimiter;
		}
		
		fillText(el, generalString);
	}
	
	/***
	 * Get WebElement Text string
	 * @param el - WebElement to retrieve current text
	 * @return given WebElement Text
	 */
	public String getText(WebElement el) {
		highlightElement(el);
		String elText = "";
		if(el.isDisplayed()) {
			elText = el.getText();
		}
		return elText;
	}
	
	/***
	 * Clear WebElement text string
	 * @param el - WebElement to Clear its text string
	 */
	public void clearText(WebElement el) {
		highlightElement(el);
		el.clear();
	}
	
	/***
	 * Set Select WebElement with specific text value. 
	 * @param el - WebElement that is a select element
	 * @param selectedValue - the value to set
	 */
	public void setSelectedOptionByValue(WebElement el, String selectedValue) {
		highlightElement(el);
		if( (selectedValue == "") || (selectedValue == null)) {
			return; //exit as there is no "real" value to select
		}
		Select sEl = new Select(el);
		sEl.selectByValue(selectedValue);
	}
	/***
	 * Set Select WebElement with specific text value. 
	 * @param el - WebElement that is a select element
	 * @param selectedValue - the value to set
	 */
	public void setSelectedOptionByText(WebElement el, String selectedText) {
		highlightElement(el);
		if( (selectedText == "") || (selectedText == null)) {
			return; //exit as there is no "real" value to select
		}
		Select sEl = new Select(el);
		sEl.selectByVisibleText(selectedText);
	}
	
	
	/***
	 * Wait for element to be visible on screen
	 * @param el - element to wait for
	 */
	public void waitForElement(WebElement el) {
		WebDriverWait wait = new WebDriverWait(driver, 3);
		wait.until(ExpectedConditions.elementToBeClickable(el));
	}
	
	/**
	 * Check is WebElement is visable
	 * @param el - WebElement to check
	 * @return - True in case the element is visible 
	 */
	public boolean isElementVisble(WebElement el) {
		boolean elVisible = false;
		if(el.isDisplayed()) {
			elVisible = true;
		}
		return elVisible;
	}
	
	/***
	 * Check if WebElement is clickable 
	 * @param el - WebElement to check
	 * @return - True if element is clickable
	 */
	public boolean isElementClickable(WebElement el) {
		scrollToElementByElement(el);
		boolean elClickable = false;
		if(el.isEnabled()) {
			elClickable = true;
		}
		return elClickable;
	}
	
	/**
	 * Check if class exists in WebElement class list
	 * @param el - to check its class list
	 * @param className - class name we are looking for
	 * @return true in case class name exists in class list
	 */
	public boolean hasClass(WebElement el, String className) {
	    String classes = el.getAttribute("class");
	    for (String c : classes.split(" ")) {
	        if (c.equals(className)) {
	            return true;
	        }
	    }

	    return false;
	}
		
	
	/**
	 * hover effect
	 * @param i_el - WebElement to hover over
	 * */
	public void hoverElement(WebElement i_el) {
		scrollToElementByElement(i_el);
		Actions builder = new Actions(driver);
		Actions hoverOverRegistrar = builder.moveToElement(i_el);
		hoverOverRegistrar.perform();
	}
	
	/***
	 * Get a dynamic element 
	 * @param i_elCss - how to locate the el 
	 * @return WebElement found by i_elCss
	 */
	public WebElement getDynamicElementByCss(String i_elCss) {
		By elCss = By.cssSelector(i_elCss);
		WebDriverWait wait = new WebDriverWait(driver, 3);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(elCss));
		return element;
	}
	
	public List<WebElement> getDynamicElementListByCss(String i_elCss) {
		By elCss = By.cssSelector(i_elCss);
		//WebDriverWait wait = new WebDriverWait(driver, 3);
		//WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(elCss));
		List<WebElement> allElements = driver.findElements(By.cssSelector(i_elCss));
		return allElements;
	}
	
	/***
	 * Get a dynamic element text
	 * @param i_elCss - how to locate the el
	 * @return dynamic object text
	 */
	public String getDynamicElementTextByCss(String i_elCss) {
		String errorMsg = null;
		WebElement errorText = getDynamicElementByCss(i_elCss);
		
		if(isElementVisble(errorText)) {
			errorMsg = getText(errorText);
		}		
		
		return errorMsg;
	}
	
	/**
	 * get the number of times of an element by a css
	 * @param i_elCss - elements css to find by
	 * @return - 0 if no such elements, or the number of elements found
	 */
	public int countDynamicElementByCss(String i_elCss) {
		List<WebElement> elements = getDynamicElementListByCss(i_elCss);
		if(elements == null) {return 0;}
		return elements.size();
	}
	
	/**
	 * set checkbox state
	 * @param i_el - WebElement to check and set state
	 * @param i_state - the required state
	 */
	public void setCheckboxState(WebElement i_el ,boolean i_state) {
		if(i_el.isSelected() != i_state) {
			i_el.click();
		}
	}
	

}
