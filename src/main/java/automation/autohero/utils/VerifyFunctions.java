package automation.autohero.utils;

import automation.autohero.reporting.ReportUtil;

public class VerifyFunctions {	
	
	/**
	 * verify Strings
	 * 
	 * @return null
	 */
	public void verify(String actual, String expected, String details)
	{
		if(actual.toLowerCase().contains(expected.toLowerCase()))
		{ 
			ReportUtil.logPass(details);
			}		
		else
		{
			ReportUtil.logFail(details);
		}
	}
	
	/**
	 * verify Integer
	 * 
	 * @return null
	 */
	public void verify(int actual, int expected, String details)
	{
		if(actual==expected)
		{ 
			ReportUtil.logPass(details);
			}		
		else
		{
			ReportUtil.logFail(details);
		}
	}
	
	/**
	 * verify double
	 * 
	 * @return null
	 */
	public void verify(double actual, double expected, String details)
	{
		if(actual==expected)
		{ 
			ReportUtil.logPass(details);
			}		
		else
		{
			ReportUtil.logFail(details);
		}
	}
	
	/**
	 * verify boolean
	 * 
	 * @return null
	 */
	public void verify(Boolean actual, Boolean expected, String details)
	{
		if(actual==expected)
		{ 
			ReportUtil.logPass(details);
			}		
		else
		{
			ReportUtil.logFail(details);
		}
	}
	
	public void syncWait()
	{
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void syncWaitExtend()
	{
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
