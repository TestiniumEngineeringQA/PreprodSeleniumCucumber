package com.sahabt;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Hooks {

  public String URL = "http://hub.testinium.io/wd/hub";
  public static WebDriver webDriver;


  @Before
  public void beforeTest() {
    DesiredCapabilities capabilities = DesiredCapabilities.chrome();
    String key = System.getProperty("key", "");
    if (key.isEmpty()) {
      System.setProperty("webdriver.chrome.driver", "web_driver/chromedriver");
      webDriver = new ChromeDriver();
    } else {
      capabilities.setCapability(CapabilityType.PLATFORM, "LINUX");
      capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");
      capabilities.setCapability(CapabilityType.VERSION, "LATEST");
      capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
      capabilities.setCapability("recordsVideo", true);
      capabilities.setCapability("screenResolution", "FHD");
      capabilities.setCapability("key", System.getProperty("key"));
      try {
        webDriver = new RemoteWebDriver(new URL(URL), capabilities);
      } catch (MalformedURLException e) {
        e.printStackTrace();
      }
    }
    webDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    webDriver.manage().window().fullscreen();
  }

  @After
  public void afterTest() {
    webDriver.quit();
    webDriver = null;
  }

  public static WebDriver getWebDriver() {
    return webDriver;
  }
}
