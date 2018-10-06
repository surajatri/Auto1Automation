package automation.autohero.elements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import automation.autohero.driver.DriverFactory;
import automation.autohero.reporting.ReportUtil;

/**
 * Element Class to define different types of Elements
 */
public class Element {

	/**
	 * The WebDriver driver
	 */
	protected WebDriver driver = DriverFactory.getDriver();

	/**
	 * The WebElement element
	 */
	protected WebElement element = null;
	
	private List<WebElement> elements = null;

	/**
	 * The String locator
	 */
	private String locator = null;

	/**
	 * The By by
	 */
	private By by = null;

	/**
	 * The enum LocatorType for different types of Locator.
	 */
	public static enum LocatorType {
		ID, NAME, CLASS_NAME, LINK_TEXT, PARTIAL_LINK_TEXT, CSS_SELECTOR, TAG_NAME, XPATH,
	}

	/**
	 * Constructor creating a by reference.
	 * 
	 * @param by
	 */
	public Element(By by) {
		this.by = by;
	}

	/**
	 * Constructor creating element with a locator and locatorType
	 * 
	 * @param locator
	 * @param locatorType
	 */
	public Element(String locator, LocatorType locatorType) {
		this.locator = locator;
		this.by = getLocatorBy(locator, locatorType);
	}

	/**
	 * Method for fetching the locator based on locatorType
	 * 
	 * @param locator
	 * @param locatorType
	 * @return
	 */
	private By getLocatorBy(final String locator, final LocatorType locatorType) {
		switch (locatorType) {

		case ID:
			return By.id(locator);

		case NAME:
			return By.name(locator);

		case CLASS_NAME:
			return By.className(locator);

		case LINK_TEXT:
			return By.linkText(locator);

		case PARTIAL_LINK_TEXT:
			return By.partialLinkText(locator);

		case CSS_SELECTOR:
			return By.cssSelector(locator);

		case TAG_NAME:
			return By.tagName(locator);

		case XPATH:
			return By.xpath(locator);

		default:
			return By.xpath(locator);
		}
	}

	/**
	 * Creates the element with explicit wait.
	 */
	protected  WebElement getElement() {
		ExpectedCondition<Boolean> expectation = new
				ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), DriverFactory
				.getConfig().getExplicitWaitTimeout());
		wait.until(expectation);
		return element =wait.until(ExpectedConditions.visibilityOfElementLocated(by));

	}
	
	public  List<WebElement> getElements() {
		return elements = (driver.findElements(by));
	}

	/**
	 * clicks on the intended element.
	 */
	public void click() {
		getElement();
		element.click();
	}

	/**
	 * sends the character stream to intended element.
	 * 
	 * @param arg0
	 *            : chars to send.
	 */
	public void sendKeys(final CharSequence arg0) {
		getElement().clear();;
		
		getElement().sendKeys(arg0);
	}

	/**
	 * Checks if the text is present for intended element.
	 * 
	 * @param text
	 *            - text to check.
	 * @return true if the text is present, else false.
	 */
	public boolean isTextPresent(final String text) {
		getElement();
		return element.getText().contains(text);
	}

	/**
	 * checks if a particular element is enabled.
	 * 
	 * @return true if the element is enabled, else false.
	 */
	public boolean isEnabled() {
		getElement();
		return element.isEnabled();
	}

	/**
	 * checks if a particular element is selected.
	 * 
	 * @return true if the element is selected, else false.
	 */
	public boolean isSelected() {
		getElement();
		return element.isSelected();
	}

	/**
	 * checks if a element id displayed.
	 * 
	 * @return true if the element is displayed, else false.
	 */
	public boolean isDisplayed(boolean bDisplayed, String details ) {
		try {
			getElement();
			if(element.isDisplayed()==bDisplayed)
			{ 
				ReportUtil.logPass(details);
				}		
			else
			{
				ReportUtil.logFail(details);
			}
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Gets the value attribute for the element.
	 * 
	 * @return value attribute of the element.
	 */
	public String getValue() {
		getElement();
		return element.getAttribute("value");
	}

	/**
	 * Gets the text for the element.
	 * 
	 * @return text for the element.
	 */
	public String getText() {
		getElement();
		return element.getText();
	}


	/**
	 * Gets the locator
	 * 
	 * @return locator for the element
	 */
	public String getLocator() {
		return locator;
	}


}
