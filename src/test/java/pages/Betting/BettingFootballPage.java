package pages.Betting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import step_defs.SharedDriver;

import static junit.framework.TestCase.fail;

public class BettingFootballPage extends BettingBasePage {

    private static final By LINK_COMPETITIONS = By.cssSelector("li#nav-football-competitions > a");
    private static final By LINK_90_MINUTES_EVENTS = By.cssSelector("div[data-marketgroupname='90 Minutes'] a.btmarket__name--featured");

    private static final By LINK_COMPETITIONS_MOBILE = By.cssSelector("li.navCompetitions");

    public BettingFootballPage(SharedDriver webDriver) {
        super(webDriver);
    }

    public void goToSection(String section) {
        waitforPage();
        switch (section.toUpperCase()) {
            case "COMPETITIONS":
                goToCompetitions();
                waitForOverLayToDissapear();
                break;
            default:
                fail(section.toUpperCase() + " does not have a case in this switch");
        }
    }

    public void expandCompetition(String event) {
        waitforPage();
        By selectorString = By.xpath("//div[contains(@id,'competitions-tab-content')]//li[contains(@class,'header-dropdown')]//a[contains(@href,'" + replaceSpacesWithDashes(event) + "')]");
        if (!isEnabled(selectorString)) {
            click(selectorString);
        }
    }

    public void clickOnFirstEvent() {
        waitforPage();
        WebElement firstEvent = getElementFromListByIndex(LINK_90_MINUTES_EVENTS, 0);
        addToTestData("selectedEvent", getAttributeFromElement(firstEvent,"title"));
        click(firstEvent);
        waitforPage();
    }

    private void goToCompetitions(){
        if(mobile) click(LINK_COMPETITIONS_MOBILE);
        else click(LINK_COMPETITIONS);
    }

}
