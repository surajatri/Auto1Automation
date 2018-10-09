package automation.autohero.setup;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentTest;

import automation.autohero.driver.DriverFactory;
import automation.autohero.reporting.ReportManager;
import automation.autohero.reporting.ReportTestManager;
import automation.autohero.reporting.ReportUtil;
import automation.autohero.utils.LoadProperties;

public class BaseTest {

	/**
	 * The ExtentTest test
	 */
	protected ExtentTest test;
	
	/**
	 * The WebDriver driver
	 */
	protected static WebDriver driver;
	public static String driverName;
	public static Properties  propData;
	public static Properties propMain;
	LoadProperties properties;
	
	/**
	 * @BeforSuite: Will start the reporter for reporting purposes
	 */
	@BeforeSuite
	public void mainSetup() {
		System.out.println("Before Suite");
		ReportManager.getReporter("./Reports/Reporter.html");
	}
	
	/**
	 * @param test
	 */
	@BeforeTest
	public void instantiaze(ITestContext test) {

	}

	/**
	 * @BeforeMethod: setting up the prerequisites before running any test
	 * @param test the test to execute
	 * @param method TestMethod to start
	 */
	@BeforeMethod(alwaysRun = true)
	public void setup(ITestContext test, Method method) {
		System.out.println("in before method");
		driverName = test.getCurrentXmlTest().getParameter("browser");
		DriverFactory.initiateDriver(test.getCurrentXmlTest().getParameter(
				"browser"));
		driver=DriverFactory.getDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.MILLISECONDS);
		driver.manage().window().maximize();
		ReportTestManager.startTest(method.getName());
		ReportUtil.logInfo("Testcase "+method.getName()+ " started");
		System.out.println(ReportTestManager.getTest().toString());
		ReportTestManager.getTest().assignCategory(
				method.getDeclaringClass().getSimpleName());
		properties = new LoadProperties();
		 try {
			propMain = properties.loadProperties("main");
			propData = properties.loadProperties("Data");
			driver.get(propMain.getProperty("url"));
		} catch (IOException e) {
			e.printStackTrace();
						
		}
		
	}

	
	/**
	 * @AfterMethod: To shutdown the driver and quit the driver.
	 * @param method the TestMethod to stop.
	 * @throws InterruptedException
	 */
	@AfterMethod
	public void tearDown(ITestContext test,Method method) throws InterruptedException {
		DriverFactory.quitDriver();
		ReportUtil.logInfo("Testcase "+method.getName()+ " completed successfully");
		ReportTestManager.endTest();
		ReportManager.getReporter().flush();
	}

}
