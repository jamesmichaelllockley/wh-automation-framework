package config;


import org.apache.commons.lang3.ObjectUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    public static DesiredCapabilities caps = new DesiredCapabilities();
    private PropertiesReader properties;

    public DriverFactory() {
        properties = new PropertiesReader();
    }

    public WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\James Lockley\\IdeaProjects\\william_hill_framework\\src\\test\\resources\\chromedriver.exe");
        WebDriver webDriver;
        if (mobile()) {
            Map<String, String> mobileEmulation = new HashMap<>();
            mobileEmulation.put("deviceName", "Nexus 5");

            Map<String, Object> chromeOptions = new HashMap<>();
            chromeOptions.put("mobileEmulation", mobileEmulation);
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
            webDriver = new ChromeDriver(capabilities);

        } else {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36");
            webDriver = new ChromeDriver(options);
            webDriver.manage().timeouts().implicitlyWait(Long.parseLong(properties.getProperty("driver.implicit.wait")), TimeUnit.SECONDS);
            webDriver.manage().timeouts().pageLoadTimeout(Long.parseLong(properties.getProperty("driver.page.load.timeout")), TimeUnit.SECONDS);
        }
        return webDriver;
    }

    private boolean mobile(){
        try{
            return System.getProperty("deviceType").equalsIgnoreCase("mobile");
        }catch (NullPointerException ignored){
            return false;
        }
    }
}