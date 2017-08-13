package pages;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import step_defs.SharedDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static junit.framework.TestCase.fail;

public abstract class BasePage {

    public static SharedDriver webDriver;

    public static Actions actions;

    public static Wait<WebDriver> fluentWait;

    public JavascriptExecutor jstExecutor;

    public boolean mobile = mobile();

    private static final By WH_OVERLAY = By.cssSelector("div#wh-global-overlay");

    public BasePage(SharedDriver webDriver) {
        BasePage.webDriver = webDriver;
        fluentWait = new FluentWait<WebDriver>(webDriver)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(2, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotFoundException.class)
                .ignoring(ElementNotVisibleException.class);
        jstExecutor = webDriver;
    }

    public void addToTestData(String key, Object value) {
        webDriver.addToTestData(key, value);
    }

    public Object getFromTestData(String key) {
        return webDriver.getFromTestData(key);
    }

    public void hover(By locator) {
        actions = new Actions(webDriver);
        actions.moveToElement(webDriver.findElement(locator)).build().perform();
    }

    public boolean elementPresent(By locator){
        waitForPageLoadActions();
        try {
            return webDriver.findElement(locator).isDisplayed();
        } catch (ElementNotVisibleException ignore){
            return false;
        }
    }

    public boolean isEnabled(By locator) {
        return fluentWait.until(webDriver -> webDriver != null ? webDriver.findElement(locator) : null).isEnabled();
    }

    public void click(By locator) {
        fluentWait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void click(WebElement element) {
        element.click();
    }

    public void enterText(By locator, String text) {
        try {
            fluentWait.until(webDriver -> webDriver != null ? webDriver.findElement(locator) : null).sendKeys(text);
        }catch (Exception e){
            fail("Failed to enter " + text + " into element " + locator.toString());
        }
//        webDriver.findElement(locator).sendKeys(text);
    }

    public void enterText(WebElement element, String text) {
        element.sendKeys(text);
    }

    public WebElement getElementFromListByIndex(By locator, int index) {
        return fluentWait.until(webDriver -> webDriver != null ? webDriver.findElements(locator) : null).get(index);
    }

    public String getTextFromElement(WebElement element) {
        return element.getText();
    }

    public String getTextFromElement(By locator) {
        return fluentWait.until(webDriver -> webDriver != null ? webDriver.findElement(locator) : null).getText();
    }

    public String getAttributeFromElement(WebElement element, String attrKey){
        return element.getAttribute(attrKey);
    }

    public String getAttributeFromElement(By locator, String attrKey){
        return webDriver.findElement(locator).getAttribute(attrKey);
    }

    public List<WebElement> getAllElements(By locator) {
        return fluentWait.until(webDriver -> webDriver != null ? webDriver.findElements(locator) : null);
    }


    public void waitforPage() {
        String status;
        do {
            status = (String) jstExecutor.executeScript("return document.readyState");
        } while (!status.equals("complete") && !status.equals("interactive"));
        waitForOverLayToDissapear();
    }

    public void waitForPageLoadActions() {
        waitforPage();
        naughtySleep(1);
        waitforPage();
    }

    public void naughtySleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitForOverLayToDissapear() {
        fluentWait.until(ExpectedConditions.invisibilityOfElementLocated(WH_OVERLAY));
    }

    public String replaceSpacesWithDashes(String stringWithSpaces) {
        return stringWithSpaces.replaceAll(" ", "-");
    }

    private boolean mobile(){
        try{
            return System.getProperty("deviceType").equalsIgnoreCase("mobile");
        }catch (NullPointerException ignored){
            return false;
        }
    }
}
