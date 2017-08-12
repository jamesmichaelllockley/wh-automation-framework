package step_defs;

import config.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SharedDriver extends EventFiringWebDriver {

    private static final WebDriver DRIVER = new DriverFactory().getDriver();
    private static final Thread THREAD = new Thread(DRIVER::quit);

    public Map<String, Object> testData = new HashMap<>();

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

    public void addToTestData(String key, Object value){
        testData.put(key,value);
    }

    public Object getFromTestData(String key){
        return testData.get(key);
    }

}
