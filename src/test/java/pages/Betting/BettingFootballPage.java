package pages.Betting;

import org.openqa.selenium.By;
import step_defs.SharedDriver;

import static junit.framework.TestCase.fail;

public class BettingFootballPage extends BettingBasePage {

    private static final By LINK_COMPETITIONS = By.cssSelector("li#nav-football-competitions > a");

    public BettingFootballPage(SharedDriver webDriver) {
        super(webDriver);
    }

    public void goToSection(String section) {
        waitforPage();
        switch (section.toUpperCase()) {
            case "COMPETITIONS":
                click(LINK_COMPETITIONS);
                waitForOverLayToDissapear();
                break;
            default:
                fail(section.toUpperCase() + " does not have a case in this switch");
        }
    }
}
