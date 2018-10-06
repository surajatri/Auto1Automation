package automation.autohero.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;


/**
 * Button Specific implementation for WebElements
 *
 */
public class DropDown extends Element {
	

	/**
	 * Constructor invoking super.
	 * @param by : The by reference to set.
	 */
	public DropDown(By by) {
		super(by);
		// TODO Auto-generated constructor stub
	}

	/**
	 * constructor invoking super.
	 * 
	 * @param locator
	 *            : The locator to set.
	 * @param locatorType
	 *            : The locatorType to set.
	 */
	public DropDown(String locator, LocatorType locatorType) {
		super(locator,locatorType);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Performs the submit operation over any webelement
	 */
	public void selectDrpDwnByVisibleText(String strValue) {
		getElement();
		Select drpDownEle = new Select(element);
		drpDownEle.selectByVisibleText(strValue);
	
	}
}
