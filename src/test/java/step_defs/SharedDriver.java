package step_defs;

import config.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class SharedDriver extends EventFiringWebDriver {

    private static final WebDriver DRIVER = new DriverFactory().getDriver();
    private static final Thread THREAD =  new Thread() {
        @Override
        public void run(){
            DRIVER.quit();
        }
    };

    static {
        Runtime.getRuntime().addShutdownHook(THREAD);
    }

    public SharedDriver() {
        super(DRIVER);
    }

    @Override
    public void close(){
        if(Thread.currentThread() != THREAD){
            throw new UnsupportedOperationException();
        }
        super.close();
    }

}
