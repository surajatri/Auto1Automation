package automation.autohero.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import automation.autohero.elements.DropDown;
import automation.autohero.elements.Element;
import automation.autohero.elements.Element.LocatorType;
import automation.autohero.reporting.ReportUtil;
import automation.autohero.utils.VerifyFunctions;

public class AutoHeroSearchPage {

	WebDriver driver;
	private static DropDown eleFirstRegistrationYear = new DropDown("//*[@data-qa-selector='filter-year']//select[@data-qa-selector='select']", LocatorType.XPATH);
	private static DropDown eleSortBy = new DropDown("//div[@data-qa-selector='sort-select']//select", LocatorType.XPATH);
	private static Element eleSortBylabel = new Element("//label[contains(text(),'Sortiert nach')]", LocatorType.XPATH);
	private static Element eleListForYear = new Element("//ul[@data-qa-selector='spec-list']/li[1]/font", LocatorType.XPATH);
	private static Element eleListForCarPrice = new Element("//div[@data-qa-selector='price']", LocatorType.XPATH);
	
	public static VerifyFunctions verify;
	
	/**
	 * Creating Object for VerifyFunctions class
	 * 
	 * @return null
	 */
	static
	{
		verify =new VerifyFunctions();	
	}

	/**
	 * Creating Constructor to Initialize driver for session
	 * 
	 * @return null
	 */
	public AutoHeroSearchPage(WebDriver driver)
	{
			this.driver=driver;
	}
	
	/**
	 * Verify that all registration year for cars after applying the filter is same or recent that <registrationYear>
	 * 
	 * @return null
	 */
	public void verifyRegistrationYearFilterValues(String registrationYear)
	{
	try {
		boolean btnStatus =true;
		int intRegistrationYear= Integer.parseInt(registrationYear);
		ReportUtil.logInfo("Method verifyRegistrationYearFilterValues started");
		List<WebElement> yearList = eleListForYear.getElements(); 
		for (int i = 0; i<yearList.size(); i++)
		{
			String tempVal = yearList.get(i).getText();
			String[] arr =tempVal.split("\n");
			int year = Integer.parseInt(arr[1]);
			if(year<intRegistrationYear){
				ReportUtil.logFail("Car At index "+ (i+1) + " is older than 2015 registration year, Actual Year: " +year + "\n" +"Expected Year :2015" );
				btnStatus = false;
			}
		}
		if(btnStatus){
			ReportUtil.logPass("All cars selected are latest with minimium first registration as 2015+" );
		}
		ReportUtil.logInfo("Method verifyRegistrationYearFilterValues Ended");
		}
		catch (Exception e) {
			e.printStackTrace();
			ReportUtil.logFail("Exception Occured for verifyRegistrationYearFilterValues with excpetion: "+ e.getMessage() );
		}
	}

	/**
	 * Select filter Type <strFilterType> And Then select the <intYearValue>
	 * @param
	 * <strFilterType> - Filter tYpe to be selected
	 * <intYearValue> - Year Value to be selected.
	 * @return null
	 */	
	public void clickFilterTypeAndSelectYear(String strFilterType, String strYearValue)
	{
	try {
		ReportUtil.logInfo("Method selectStartingYearFilter started");
		
		WebElement eleFilterType = driver.findElement(By.xpath("//span[text()='"+strFilterType+"']"));
		eleFilterType.click();
		ReportUtil.logInfo("Clicked on filter type link : " + strFilterType);
		
		DropDown drpDownYear = new DropDown("//span[text()='"+strFilterType+"']/../..//Select", LocatorType.XPATH);
		drpDownYear.isDisplayed(true, "First Registration Year dropdown is displayed");
		
		eleFirstRegistrationYear.selectDrpDwnByVisibleText(strYearValue);
		Thread.sleep(2000);
		
		ReportUtil.logInfo("Selected Filter Year value as: " +strYearValue);
		
		ReportUtil.logInfo("Method selectStartingYearFilter End");
		}
		catch (Exception e) {
			e.printStackTrace();
			ReportUtil.logFail("Exception Occured for selectStartingYearFilter with excpetion: "+ e.getMessage() );
		}
	}
	
	/**
	 * Sort by the displayed result with value <strValue>
	 * @param
	 * <strValue> - Sort by value
	 * 
	 * @return null
	 */	
	public void selectSortBy( String strValue)
	{
	try {
		ReportUtil.logInfo("Method selectSortBy Started");
		eleSortBylabel.isDisplayed(true, "Sort by label is displayed");
		
		eleSortBy.isDisplayed(true, "Sort By dropdown is displayed");
		
		eleSortBy.selectDrpDwnByVisibleText(strValue);
		
		ReportUtil.logInfo("Sort by drop down value is selected as : " +strValue);
		
		ReportUtil.logInfo("Method selectSortBy End");	
		}
		catch (Exception e) {
			e.printStackTrace();
			ReportUtil.logFail("Exception Occured for selectSortBy with excpetion: "+ e.getMessage() );
		}
	}
	
	/**
	 * Veify All cars are sorted by Price in order <strSortingOrder>
	 * @param
	 * <strValue> - Sort by value
	 * 
	 * @return null
	 */	
	public void verifyCarPriceSortedOrderBy(String strSortingOrder)
	{
		try {
			ReportUtil.logInfo("Method verifyCarPriceSortedOrderBy started");
			Thread.sleep(5000);
			int carPrice =0;
			List<WebElement> carPriceList = eleListForCarPrice.getElements(); 
			List<Integer> priceList = new ArrayList<Integer>();

			for (int i = 0; i<carPriceList.size(); i++)
			{
				String tempVal = carPriceList.get(i).getText();
				String[] arr =tempVal.split(" ");
				carPrice = Integer.parseInt(arr[0].replace(".", ""));
				priceList.add(carPrice);		
			}
			List<Integer> sortedPriceList = new ArrayList<Integer>(priceList);
			if(strSortingOrder.toLowerCase().equals("ascending")){
				Collections.sort(sortedPriceList);
			}else{
				Collections.sort(sortedPriceList, Collections.reverseOrder());
			}
			System.out.println(sortedPriceList.equals(priceList));
			if(sortedPriceList.equals(priceList)){
				ReportUtil.logPass("Car price is sorted by order "+strSortingOrder+" Actual Value : "+priceList+"\n"+"  Expected: " +sortedPriceList);
			}else{
				ReportUtil.logFail("Car price is not sorted by order "+strSortingOrder+" Actual Value : "+priceList+"\n"+"  Expected: " +sortedPriceList);
			}
			ReportUtil.logInfo("Method verifyCarPriceSortedOrderBy started");
		}
		catch (Exception e) {
			e.printStackTrace();
			ReportUtil.logFail("Exception Occured for verifyCarPriceSortedOrderBy with excpetion: "+ e.getMessage() );
		}
	}
	
}


