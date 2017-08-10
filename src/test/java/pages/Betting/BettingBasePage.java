package pages.Betting;

import org.openqa.selenium.By;
import step_defs.SharedDriver;
import pages.BasePage;

public abstract class BettingBasePage extends BasePage {

    public BettingBasePage(SharedDriver webDriver){
        super(webDriver);
    }

    static final By LINK_POPULAR_FOOTBALL = By.cssSelector("ul#desktop-sidebar-quick-links li#nav-football > a");

    public void goToFootball(){
        waitforPage();
        click(LINK_POPULAR_FOOTBALL);
        waitforPage();
    }
}
