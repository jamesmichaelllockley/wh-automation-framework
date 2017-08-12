package pages.Betting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import step_defs.SharedDriver;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class BettingFootballEventPage extends BettingBasePage {

    private static final By H_EVENT_TITLE = By.cssSelector("h1.header-panel__title");
    private static final By BUTTON_BET_ON_SIDE = By.cssSelector("div.event:first-child button.betbutton");

    public BettingFootballEventPage(SharedDriver webDriver) {
        super(webDriver);
    }

    public void selectedEventDisplayed() {
        assertEquals(
                getEventTitle(),
                getFromTestData("selectedEvent").toString());
    }

    public void clickOnSide(String side) {
        List<WebElement> elementList = getAllElements(BUTTON_BET_ON_SIDE);
        WebElement sideToClick = null;
        switch (side.toUpperCase()) {
            case "HOME":
                sideToClick = elementList.get(0);
                break;
            case "DRAW":
                sideToClick = elementList.get(1);
                break;
            case "AWAY":
                sideToClick = elementList.get(2);
                break;
            default:
                fail(side + " does not have a case in this switch");
        }
        addToTestData("selectedSide",getAttributeFromElement(sideToClick,"data-player"));
        addToTestData("data-num",getAttributeFromElement(sideToClick,"data-num"));
        addToTestData("data-denom",getAttributeFromElement(sideToClick,"data-denom"));
        click(sideToClick);
    }

    private String getEventTitle() {
        waitforPage();
        return getTextFromElement(H_EVENT_TITLE);
    }

}
