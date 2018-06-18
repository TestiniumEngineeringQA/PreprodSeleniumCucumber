package com.sahabt;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Hooks {

  public String URL = "http://localhost:4444/wd/hub";
  public static WebDriver webDriver;


  @Before
  public void before() {
    DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//    capabilities.setCapability("key", System.getProperty("key"));
    try {
      webDriver = new RemoteWebDriver(new URL(URL), capabilities);
//      webDriverWait = new WebDriverWait(webDriver, timeOut, sleepTime);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
  }

  @After
  public void afterMethod() {
    webDriver.quit();
    webDriver = null;
  }

  public static WebDriver getWebDriver() {
    return webDriver;
  }
}
