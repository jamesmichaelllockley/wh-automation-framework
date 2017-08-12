package pages.Betting;

import step_defs.SharedDriver;

public class BettingHomePage extends BettingBasePage {

    private static final String URL = "http://sports.williamhill.com/sr-admin-set-white-list-cookie.html";

    public BettingHomePage(SharedDriver webDriver) {
        super(webDriver);
    }

    public void goTo() {
        webDriver.manage().deleteAllCookies();
        webDriver.navigate().to(URL);
    }
}
