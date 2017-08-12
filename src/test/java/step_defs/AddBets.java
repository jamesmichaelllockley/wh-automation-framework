package step_defs;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.Betting.BettingFootballEventPage;

public class AddBets {

    private BettingFootballEventPage bettingFootballEventPage;

    public AddBets(SharedDriver webDriver) {
        bettingFootballEventPage = new BettingFootballEventPage(webDriver);
    }

    @When("^I add a £([0-9].*) bet for ([^\"]*)$")
    public void i_add_a_bet(String betAmount, String side) {
        bettingFootballEventPage.clickOnSide(side);
        bettingFootballEventPage.enterSinglesBetAmount(betAmount);
    }

    @Then("^the selected bet should be added to my bet slip$")
    public void selected_bet_should_be_added_to_my_betslip(){
        bettingFootballEventPage.selectedBetDisplayedInBetSlip();
    }

    @Then("^the return offered is based on the correct odds$")
    public void return_offered_based_on_odds(){
        bettingFootballEventPage.returnOfferedBasedOnOdds();
    }

}
