package step_defs;

import cucumber.api.java.en.Given;
import pages.Betting.BettingFootballPage;
import pages.Betting.BettingHomePage;

import static junit.framework.TestCase.fail;

public class GoTo {

    private BettingFootballPage bettingFootballPage;
    private BettingHomePage bettingHomePage;

    public GoTo(SharedDriver webDriver){
        bettingFootballPage = new BettingFootballPage(webDriver);
        bettingHomePage = new BettingHomePage(webDriver);
    }

    @Given("^I go to the '([^\"]*)' page$")
    public void go_to_page(String page){
        switch (page.toUpperCase()){
            case "SPORTS BETTING":
                bettingHomePage.goTo();
                break;
            default:
                fail(page.toUpperCase() + " does not have a case in the go to switch");
        }

    }

}
