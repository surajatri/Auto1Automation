package automation.autohero.testcases;

import java.util.Properties;
import org.testng.annotations.Test;

import automation.autohero.pages.AutoHeroSearchPage;
import automation.autohero.reporting.ReportUtil;
import automation.autohero.setup.BaseTest;
import automation.autohero.utils.LoadProperties;


public class AutoHeroTestCases extends BaseTest {
	
	
	public static AutoHeroSearchPage autoSearchPage;

	/**
	 * verify successfully login 
	 * 
	 * @return null
	 */
	@Test(enabled=true)
	public void AutoHeroSearchScenario()
	{			
		autoSearchPage = new AutoHeroSearchPage(driver);

		//Select registration year from filter with value <registrationYear>
		autoSearchPage.clickFilterTypeAndSelectYear(propData.getProperty("filterType"), propData.getProperty("registrationYear"));
		
		//Select the sorting criteria for the result
		autoSearchPage.selectSortBy(propData.getProperty("sortByValue"));
		
		//Verify the registration year value for result, All car should have first registration as 2015+
		autoSearchPage.verifyRegistrationYearFilterValues(propData.getProperty("registrationYear"));
		
		//Verify All cars are displayed in price descending order
		autoSearchPage.verifyCarPriceSortedOrderBy("descending");
		
		
		
	}	
	
	

}