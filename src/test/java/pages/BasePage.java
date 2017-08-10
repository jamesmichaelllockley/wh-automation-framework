package pages;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import step_defs.SharedDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;

public abstract class BasePage {

    public SharedDriver webDriver;

    public static Actions actions;

    public static Wait<WebDriver> fluentWait;

    public JavascriptExecutor jstExecutor;

    private static final By WH_OVERLAY = By.cssSelector("div#wh-global-overlay");

    public BasePage(SharedDriver webDriver) {
        this.webDriver = webDriver;
        fluentWait = new FluentWait<WebDriver>(webDriver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(2, TimeUnit.SECONDS)
                .ignoring(ElementNotInteractableException.class)
                .ignoring(ElementNotFoundException.class)
                .ignoring(ElementNotVisibleException.class);
        jstExecutor = webDriver;
    }

    public void hover(By locator) {
        actions = new Actions(webDriver);
        actions.moveToElement(webDriver.findElement(locator)).build().perform();
    }

    public void click(By locator) {
        fluentWait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void enterText(By locator, String text) {
        fluentWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        webDriver.findElement(locator).sendKeys(text);
    }

    public void waitforPage() {
        String status;
        do {
            status = (String) jstExecutor.executeScript("return document.readyState");
        } while (!status.equals("complete") && !status.equals("interactive"));
        waitForOverLayToDissapear();
    }

    public void waitForPageLoadActions(){
        waitforPage();
        naughtySleep(1);
        waitforPage();
    }

    private void naughtySleep(int seconds){
        try {
            Thread.sleep(seconds * 1000 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitForOverLayToDissapear(){
        fluentWait.until(ExpectedConditions.invisibilityOfElementLocated(WH_OVERLAY));
    }
}
