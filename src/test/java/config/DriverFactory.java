package config;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    public static DesiredCapabilities caps = new DesiredCapabilities();
    public static RemoteWebDriver driver;
    private PropertiesReader properties;

    public DriverFactory() {
        properties = new PropertiesReader();
    }

    public WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\James Lockley\\IdeaProjects\\william_hill_framework\\src\\test\\resources\\chromedriver.exe");
        WebDriver webDriver;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        webDriver = new ChromeDriver(options);
        webDriver.manage().timeouts().implicitlyWait(Long.parseLong(properties.getProperty("driver.implicit.wait")), TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(Long.parseLong(properties.getProperty("driver.page.load.timeout")), TimeUnit.SECONDS);
        return webDriver;
    }

}