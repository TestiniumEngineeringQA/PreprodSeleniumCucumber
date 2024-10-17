package com.testinium;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


/**
* The type Hooks.
* This class sets driver, capabilities and some of options
*/
public class BaseTest {

    protected static WebDriver driver;
    protected static Actions actions;
    public Logger logger = Logger.getLogger(getClass());

    /**
    * Selected Browser name and platform, initialize capabilities
    *
    * @param capabilities
    */
    String browserName = "chrome";
    DesiredCapabilities capabilities;

    /**
    * @return the web driver
    */
    public static WebDriver getWebDriver() {
        return driver;
    }

    /**
    * Before test
    * This method Checks testinium key and initialize the webdriver correctly from web_driver package
    */
    @Before
    public void beforeTest() {
    logger.info("************************************  BeforeScenario  ************************************");
    try {
        ChromeOptions options = new ChromeOptions();
        capabilities = DesiredCapabilities.chrome();
        options.setExperimentalOption("w3c", false);
        options.addArguments("disable-translate");
        options.addArguments("--disable-notifications");
        options.addArguments("--start-fullscreen");
        Map<String, Object> prefs = new HashMap<>();
        options.setExperimentalOption("prefs", prefs);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        capabilities.setCapability("key", System.getProperty("key"));
        browserName = System.getenv("browser");
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        actions = new Actions(driver);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
    * After test.
    * Quit driver
    */
    @After
    public void afterTest() {
        driver.quit();
    }
}

