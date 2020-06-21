//Documentation:
//https://github.com/SeleniumHQ/selenium/wiki/InternetExplorerDriver
package DriverManager;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;

import javax.security.auth.login.Configuration;

public class IEDriverManagerAbstraction extends DriverManagerAbstraction {
    public void createWebDriver(){
        System.setProperty("webdriver.ie.driver", "C:\\InternetExplorerServer\\IEDriverServer.exe");
        InternetExplorerOptions options = new InternetExplorerOptions();
        //set your browser-specific options here
        options.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, Configuration.getConfiguration());
        options.setCapability(CapabilityType.BROWSER_NAME, "internet explorer");
        options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
        options.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
        //options.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "about:blank");
        options.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
        options.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
        options.setCapability(InternetExplorerDriver.NATIVE_EVENTS, true);
        options.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
        options.setCapability("requireWindowFocus", false);
        options.setCapability("ignoreProtectedModeSettings", true);
        options.setCapability("disable-popup-blocking", true);

        this.driver = new InternetExplorerDriver(options);
    }
}
