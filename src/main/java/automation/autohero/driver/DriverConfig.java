package automation.autohero.driver;

import org.openqa.selenium.WebDriver;

public class DriverConfig {

    private WebDriver driver;
    private String chromeDriverPath;
    private String chromeBinPath;
    public static final int DEFAULT_IMPLICIT_WAIT_TIMEOUT = 5;
    public static final int DEFAULT_EXPLICIT_WAIT_TIME_OUT = 15;
    public static final int DEFAULT_PAGE_LOAD_TIMEOUT = 90;
    private double implicitWaitTimeout = DEFAULT_IMPLICIT_WAIT_TIMEOUT;
    private int explicitWaitTimeout = DEFAULT_EXPLICIT_WAIT_TIME_OUT;

    /**
     * @return chromeBinPath
     */
    public String getChromeBinPath() {
        return chromeBinPath;
    }
    
    /**
     * @return implicitWaitTimeout
     */
    public double getImplicitWaitTimeout() {
        return implicitWaitTimeout;
    }

    /**
     * @return chromeDriverPath
     */
    public String getChromeDriverPath() {
        return chromeDriverPath;
    }

    /**
     * @return driver
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * @return explicitWaitTimeout
     */
    public int getExplicitWaitTimeout() {
        if (explicitWaitTimeout < getImplicitWaitTimeout()) {
            return (int) getImplicitWaitTimeout();
        } else {
            return explicitWaitTimeout;
        }
    }

 
}

