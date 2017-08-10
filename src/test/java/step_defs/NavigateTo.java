package step_defs;

import cucumber.api.java.en.Given;
import pages.Betting.BettingFootballPage;
import pages.Betting.BettingHomePage;

import static junit.framework.TestCase.fail;

public class NavigateTo {

    private BettingFootballPage bettingFootballPage;
    private BettingHomePage bettingHomePage;

    public NavigateTo(SharedDriver webDriver){
        bettingFootballPage = new BettingFootballPage(webDriver);
        bettingHomePage = new BettingHomePage(webDriver);
    }

    @Given("^I navigate to the '([^\"]*)' '([^\"]*)' page$")
    public void i_navigate_to_section_of_page(String parentPage, String section){
        switch (parentPage.toUpperCase()){
            case "FOOTBALL":
                bettingHomePage.goToFootball();
                bettingFootballPage.goToSection(section);
                break;
            default:
                fail(parentPage.toUpperCase() + " does not have a case in the go to switch");
        }

    }

}
