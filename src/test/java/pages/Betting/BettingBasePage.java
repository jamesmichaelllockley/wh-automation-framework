package pages.Betting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import step_defs.SharedDriver;
import pages.BasePage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public abstract class BettingBasePage extends BasePage {

    public BettingBasePage(SharedDriver webDriver) {
        super(webDriver);
    }

    static final By LINK_CLEAR_SLIP = By.cssSelector("div#betslipwrapper a.clear");

    static final By LINK_POPULAR_FOOTBALL = By.cssSelector("ul#desktop-sidebar-quick-links li#nav-football > a");
    static final By INPUT_SINGLES_STAKE = By.cssSelector("div#bets-container-singles input.betslip-selection__stake-input");
    static final By LABEL_SINGLES_TITLE = By.cssSelector("div#bets-container-singles span.betslip-selection__name label");
    static final By TOTAL_STAKE_PRICE = By.cssSelector("span#total-stake-price");
    static final By TOTAL_RETURN_PRICE = By.cssSelector("span#total-to-return-price");

    static final By LINK_POPULAR_FOOTBALL_MOBILE = By.cssSelector("div#nav-football");
    static final By LINK_BETSLIP_MOBILE = By.cssSelector("div#betslip-btn-toolbar");
    static final By LABEL_BETSLIP_COUNT_MOBILE = By.cssSelector("span#mobile-betslip-count");
    static final By LINK_CLOSE_BETSLIP = By.cssSelector("a[data-ng-click*='betslipToolbarClose']");

    public void clearBetSlip() {
        if (mobile) {
            if (elementPresent(LABEL_BETSLIP_COUNT_MOBILE)){
                click(LINK_BETSLIP_MOBILE);
                click(LINK_CLEAR_SLIP);
                click(LINK_CLOSE_BETSLIP);
            }
        } else {
            if (elementPresent(LINK_CLEAR_SLIP))
                click(LINK_CLEAR_SLIP);
        }
    }

    public void goToFootball() {
        waitforPage();
        if (mobile)
            click(LINK_POPULAR_FOOTBALL_MOBILE);
        else
            click(LINK_POPULAR_FOOTBALL);
        waitforPage();
    }

    public void enterSinglesBetAmount(String betAmount) {
        waitForPageLoadActions();
        addToTestData("betAmount", betAmount);
        if (mobile) {
            click(LINK_BETSLIP_MOBILE);
            enterBetWithNumPad(betAmount);
        } else enterText(INPUT_SINGLES_STAKE, betAmount);
    }

    public void selectedBetDisplayedInBetSlip() {
        singlesCorrectSideSelectionDisplayed();
        totalStakeMatchesBets();
    }

    public void returnOfferedBasedOnOdds() {
        Double returnOffered = Double.parseDouble(getTextFromElement(TOTAL_RETURN_PRICE));
        Integer dataNum = Integer.parseInt(getFromTestData("data-num").toString());
        Integer dataDenom = Integer.parseInt(getFromTestData("data-denom").toString());
        Double betAmount = Double.parseDouble(getFromTestData("betAmount").toString());
        assertEquals(returnOffered, round(calculateReturns(dataNum, dataDenom, betAmount)));
    }

    private void enterBetWithNumPad(String betAmount) {
        click(INPUT_SINGLES_STAKE);
        WebElement numpad = webDriver.findElement(By.cssSelector("div#numberpad"));
        char[] arr = betAmount.toCharArray();
        for (char str : arr) {
            click(numpad.findElement(By.cssSelector("button[data-value='" + str + "']")));
        }
    }

    private Double calculateReturns(Integer dataNum, Integer dataDenom, Double betAmount) {
        return ((float) dataNum / dataDenom + 1) * betAmount;
    }

    private void singlesCorrectSideSelectionDisplayed() {
        assertEquals(getTextFromElement(LABEL_SINGLES_TITLE), getFromTestData("selectedSide"));
    }

    private void totalStakeMatchesBets() {
        assertEquals(getTextFromElement(TOTAL_STAKE_PRICE), getFromTestData("betAmount"));
    }

    private static double round(double value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(2, RoundingMode.DOWN);
        return bd.doubleValue();
    }
}
