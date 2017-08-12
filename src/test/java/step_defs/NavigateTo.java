package step_defs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import pages.Betting.BettingFootballEventPage;
import pages.Betting.BettingFootballPage;
import pages.Betting.BettingHomePage;

import static junit.framework.TestCase.fail;

public class NavigateTo {

    private BettingFootballPage bettingFootballPage;
    private BettingHomePage bettingHomePage;
    private BettingFootballEventPage bettingFootballEventPage;

    public NavigateTo(SharedDriver webDriver) {
        bettingFootballPage = new BettingFootballPage(webDriver);
        bettingHomePage = new BettingHomePage(webDriver);
        bettingFootballEventPage = new BettingFootballEventPage(webDriver);
    }

    @Given("^I navigate to the '([^\"]*)' '([^\"]*)' page$")
    public void i_navigate_to_section_of_page(String parentPage, String section) {
        switch (parentPage.toUpperCase()) {
            case "FOOTBALL":
                bettingHomePage.goToFootball();
                bettingFootballPage.goToSection(section);
                break;
            default:
                fail(parentPage.toUpperCase() + " does not have a case in the go to switch");
        }
    }

    @When("^I navigate to an '([^\"]*)' football event$")
    public void i_navigate_to_football_event(String footballEvent) {
        bettingFootballPage.expandCompetition(footballEvent);
        bettingFootballPage.clickOnFirstEvent();
        bettingFootballEventPage.selectedEventDisplayed();
    }
}
