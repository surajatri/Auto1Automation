package automation.autohero.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Enum for generating Specific Driver Type.
 */
public enum DriverType implements IDriverSetup {

	FIREFOX {

		public WebDriver getDriverObject(DesiredCapabilities dc) {

			// TODO Auto-generated method stub
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"/Jars/geckodriver.exe");
			return new FirefoxDriver();

		}

		public DesiredCapabilities getDesiredCapabilities(DriverConfig config) {
			// TODO Auto-generated method stub
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setBrowserName("firefox");
			return capabilities;
		}

	},
	CHROME {

		public WebDriver getDriverObject(DesiredCapabilities dc) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir")+"/Jars/chromedriver.exe");
			return new ChromeDriver();

		}

		public DesiredCapabilities getDesiredCapabilities(DriverConfig config) {
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			return capabilities;
		}
	},

	IE {

		public WebDriver getDriverObject(DesiredCapabilities dc) {
			// TODO Auto-generated method stub
			System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"/Jars/IEDriverServer.exe");			
			return new InternetExplorerDriver();
		}

		public DesiredCapabilities getDesiredCapabilities(DriverConfig config) {
			// TODO Auto-generated method stub
			DesiredCapabilities capabilities =  DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
			capabilities.setCapability("requireWindowFocus",true);
			capabilities.setCapability("ie.ensureCleanSession",true);
			capabilities.setCapability("ignoreZoomingSetting",true);
			return capabilities;
		}
	}
}
