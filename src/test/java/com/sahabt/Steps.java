package com.sahabt;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Steps {

  private Logger logger = LoggerFactory.getLogger(getClass());
  public WebDriver webDriver;
  public WebDriverWait webDriverWait;
  private final int timeOut = 30;
  private final int sleepTime = 150;

  public Steps() {
    this.webDriver = Hooks.getWebDriver();
    this.webDriverWait = new WebDriverWait(webDriver, timeOut, sleepTime);
  }

  public List<WebElement> finds(By by) {
    logger.debug("Find Element {}", by.toString());
    List<WebElement> webElements = null;
    try {
      webElements = webDriver.findElements(by);
    } catch (Exception e) {
      Assert.fail("WebElements Not Found");
    }
    Assert.assertNotNull("WebElements Not Found", webElements);
    return webElements;
  }

  public WebElement find(By by) {
    logger.debug("Find Element {}", by.toString());
    WebElement webElement = null;
    try {
      webElement = webDriver.findElement(by);
    } catch (Exception e) {
      Assert.fail("WebElement Not Found");
    }
    Assert.assertNotNull("WebElement Not Found", webElement);
    return webElement;
  }


  public WebElement findByClickable(By by) {
    logger.debug("Find Element Clickable {}", by.toString());
    WebElement webElement = webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
    Assert.assertNotNull("WebElement Not Clickable", webElement);
    return webElement;
  }

  public List<WebElement> findsByClickable(By by) {
    logger.debug("Find Elements Clickable {}", by.toString());

    List<WebElement> webElements = webDriverWait.until(new ExpectedCondition<List<WebElement>>() {
      @Nullable
      @Override
      public List<WebElement> apply(@Nullable WebDriver webDriver) {
        List<WebElement> elements = ExpectedConditions.visibilityOfAllElementsLocatedBy(by)
            .apply(webDriver);
        try {
          for (WebElement element : elements) {
            if (element == null || !element.isEnabled()) {
              return null;
            }
          }
          return elements;
        } catch (StaleElementReferenceException e) {
          return null;
        }
      }
    });

    Assert.assertNotNull("WebElements Not Clickable", webElements);
    return webElements;
  }

  public WebElement findByCss(String css) {
    return find(By.cssSelector(css));
  }

  public List<WebElement> findsByCss(String css) {
    return finds(By.cssSelector(css));
  }

  public WebElement findById(String id) {
    return find(By.id(id));
  }

  public List<WebElement> findsById(String id) {
    return finds(By.id(id));
  }

  public WebElement findByXpath(String xpath) {
    return find(By.xpath(xpath));
  }

  public List<WebElement> findsByXpath(String xpath) {
    return finds(By.xpath(xpath));
  }

  public void checkElementVisible(By by) {
    Assert.assertTrue("WebElement Not Visible", find(by).isDisplayed());
  }

  @And("^Check element visible by id \"([^\"]*)\"$")
  public void checkElementByIdVisible(String id) {
    Assert.assertTrue("WebElement Not Visible", find(By.id(id)).isDisplayed());
  }

  @And("^Check element visible by css \"([^\"]*)\"$")
  public void checkElementByCssVisible(String css) {
    Assert.assertTrue("WebElement Not Visible", find(By.cssSelector(css)).isDisplayed());
  }

  @And("^Check element visible by xpath \"([^\"]*)\"$")
  public void checkElementByXpathVisible(String xpath) {
    Assert.assertTrue("WebElement Not Visible", find(By.xpath(xpath)).isDisplayed());
  }

  public void clickElement(By by) {
    find(by).click();
  }

  @And("^Click element by id \"([^\"]*)\"$")
  public void clickElementById(String id) {
    clickElement(By.id(id));
  }

  @And("^Click element by css \"([^\"]*)\"$")
  public void clickElementByCss(String css) {
    clickElement(By.cssSelector(css));
  }

  @And("^Click element by xpath \"([^\"]*)\"$")
  public void clickElementByXpath(String xpath) {
    clickElement(By.xpath(xpath));
  }

  public void sendKeys(By by, String text) {
    find(by).sendKeys(text);
  }

  @And("^Send keys to element by id \"([^\"]*)\" text \"([^\"]*)\"$")
  public void sendKeysById(String id, String text) {
    sendKeys(By.id(id), text);
  }

  @And("^Send keys to element by css \"([^\"]*)\" text \"([^\"]*)\"$")
  public void sendKeysByCss(String css, String text) {
    sendKeys(By.cssSelector(css), text);
  }

  @And("^Send keys to element by xpath \"([^\"]*)\" text \"([^\"]*)\"$")
  public void sendKeysByXpath(String xpath, String text) {
    sendKeys(By.xpath(xpath), text);
  }

  public void sendKeyCode(By by, String code) {
    find(by).sendKeys(Keys.valueOf(code));
  }

  @And("^Send key code to element by id \"([^\"]*)\" code \"([^\"]*)\"$")
  public void sendKeyCodeById(String id, String code) {
    sendKeyCode(By.id(id), code);
  }

  @And("^Send key code to element by css \"([^\"]*)\" code \"([^\"]*)\"$")
  public void sendKeyCodeByCss(String css, String code) {
    sendKeyCode(By.cssSelector(css), code);
  }

  @And("^Send key code to element by xpath \"([^\"]*)\" code \"([^\"]*)\"$")
  public void sendKeyCodeByXpath(String xpath, String code) {
    sendKeyCode(By.xpath(xpath), code);
  }


  public void clearElement(By by) {
    find(by).clear();
  }

  @And("^Clear element by id \"([^\"]*)\"$")
  public void clearElementById(String id) {
    clearElement(By.id(id));
  }

  @And("^Clear element by css \"([^\"]*)\"$")
  public void clearElementByCss(String css) {
    clearElement(By.cssSelector(css));
  }

  @And("^Clear element by xpath \"([^\"]*)\"$")
  public void clearElementByXpath(String xpath) {
    clearElement(By.xpath(xpath));
  }

  public String getUrl() {
    return webDriver.getCurrentUrl();
  }

  @And("^Url equals to \"([^\"]*)\"$")
  public void checkUrlEquals(String url) {
    Assert.assertEquals(url, getUrl());
  }

  public String getPageSource() {
    return webDriver.getPageSource();
  }

  @And("^Print page source$")
  public void printPageSource() {
    logger.info(webDriver.getPageSource());
  }

  public String getPageTitle() {
    return webDriver.getTitle();
  }

  @And("^Page title equals to \"([^\"]*)\"$")
  public void checkPageTitleEquals(String title) {
    Assert.assertEquals(title, getPageTitle());
  }

  public Navigation getNavigation() {
    return webDriver.navigate();
  }

  @And("^Refresh page$")
  public void refresh() {
    getNavigation().refresh();
  }

  @And("^Navigate to forward$")
  public void forward() {
    getNavigation().forward();
  }

  @And("^Navigate to back$")
  public void back() {
    getNavigation().back();
  }

  @Given("^Go to \"([^\"]*)\"$")
  public void goToUrl(String url) {
    getNavigation().to(url);
  }

  public void goToUrl(URL url) {
    getNavigation().to(url);
  }

  @And("^Close$")
  public void close() {
    webDriver.close();
  }

  @And("^Switch to main content$")
  public void switchToMainContent(String frameId) {
    webDriver.switchTo().defaultContent();
  }

  @And("^Switch to parent frame$")
  public void switchToParentFrame() {
    webDriver.switchTo().parentFrame();
  }

  @And("^Switch to frame by index \"([^\"]*)\"$")
  public void switchToFrameByIndex(int index) {
    webDriver.switchTo().frame(index);
  }

  @And("^Switch to frame by id \"([^\"]*)\"$")
  public void switchToFrameById(String frameId) {
    webDriver.switchTo().frame(frameId);
  }

  @And("^Switch to window by name \"([^\"]*)\"$")
  public void switchToWindow(String windowName) {
    webDriver.switchTo().window(windowName);
  }

  public int getCurrentWindowIndex() {
    Set<String> windowHandles = webDriver.getWindowHandles();
    int currentIndex = 0;
    for (String windowHandle : windowHandles) {
      if (windowHandle.equals(webDriver.getWindowHandle())) {
        break;
      }
      currentIndex++;
    }
    return currentIndex;
  }

  @And("^Open next tab$")
  public void switchToNextTab() {
    switchToWindow(
        webDriver.getWindowHandles().stream().skip(getCurrentWindowIndex()).findFirst().orElse(""));
  }

  @And("^Open prev tab$")
  public void switchToPrevTab() {
    switchToWindow(
        webDriver.getWindowHandles().stream().skip(getCurrentWindowIndex() - 1).findFirst()
            .orElse(""));
  }

  @And("^Switch to alert$")
  public Alert switchToAlert() {
    Alert alert = webDriver.switchTo().alert();
    Assert.assertNotNull("Switchable Alert Not Found", alert);
    return alert;
  }

  @And("^Accept alert$")
  public void acceptAlert() {
    switchToAlert().accept();
  }

  @And("^Dismiss alert$")
  public void dismissAlert() {
    switchToAlert().dismiss();
  }

  @And("^Send \"([^\"]*)\" to alert$")
  public void sendTextToAlert(String keys) {
    switchToAlert().sendKeys(keys);
  }

  public Options getOptions() {
    return webDriver.manage();
  }

  @And("^Set page timeout \"([^\"]*)\" seconds$")
  public void setPageTimeOut(int pageTimeOut) {
    getOptions().timeouts().pageLoadTimeout(pageTimeOut, TimeUnit.SECONDS);
  }

  @And("^Set script timeout \"([^\"]*)\" seconds$")
  public void setScriptTimeOut(int scriptTimeOut) {
    getOptions().timeouts().setScriptTimeout(scriptTimeOut, TimeUnit.SECONDS);
  }

  @And("^Set implicitly wait \"([^\"]*)\" seconds$")
  public void setImplicitlyWait(int implicitlyWait) {
    getOptions().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);
  }

  @And("^Fullscreen to window$")
  public void fullScreen() {
    getOptions().window().fullscreen();
  }

  @And("^Add cookie name \"([^\"]*)\" value \"([^\"]*)\"$")
  public void addCookie(String cookieName, String cookieValue) {
    getOptions().addCookie(new Cookie(cookieName, cookieValue));
  }

  @And("^Delete cookie by cookieName \"([^\"]*)\"")
  public void deleteCookie(String cookieName) {
    getOptions().deleteCookieNamed(cookieName);
  }

  @And("^Delete all cookies$")
  public void deleteAllCookies() {
    getOptions().deleteAllCookies();
  }

  public JavascriptExecutor getJavaScriptExecutor() {
    return ((JavascriptExecutor) webDriver);
  }

  @And("^Wait page load complete$")
  public void waitPageLoadComplete() {
    try {
      webDriverWait.until(
          driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState")
              .toString()
              .equals("complete"));
    } catch (Throwable error) {
      error.printStackTrace();
    }
  }

  @And("^Wait angular load complete$")
  public void waitForAngularLoad() {
    Boolean existAngular = (Boolean) getJavaScriptExecutor()
        .executeScript("return (typeof(angular) != 'undefined')");
    if (existAngular) {
      try {
        webDriverWait.until(driver -> ((Boolean) ((JavascriptExecutor) driver).executeScript(
            "return angular.element(document).injector().get('$http').pendingRequests.length === 0")));
      } catch (Throwable error) {
        error.printStackTrace();
      }
    }
  }

  @And("^Wait jquery request complete$")
  public void waitJQueryComplete() {
    Boolean existJquery = (Boolean) getJavaScriptExecutor()
        .executeScript("return (typeof(jQuery) != 'undefined')");
    if (existJquery) {
      try {
        webDriverWait.until(driver -> (Boolean) ((JavascriptExecutor) driver)
            .executeScript("return jQuery.active == 0"));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  @And("^Wait all request complete$")
  public void waitAll() {
    waitPageLoadComplete();
    waitJQueryComplete();
  }

  @When("^Wait \"([^\"]*)\" seconds$")
  public void waitSeconds(int seconds) {
    waitMillis(seconds * 1000);
  }

  @When("^Wait \"([^\"]*)\" ms$")
  public void waitMillis(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
