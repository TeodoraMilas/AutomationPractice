package DriverManager;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;

import java.io.File;

public class FirefoxDriverManagerAbstraction extends DriverManagerAbstraction {
    private File downloadDir;

    public void createWebDriver(){
        FirefoxOptions options = new FirefoxOptions();
        //set your browser-specific options here
//        options.addPreference("browser.startup.homepage", "https://www.google.com");
//        options.addPreference("browser.download.folderList", 2);
//        options.addPreference("browser.download.manager.showWhenStarting", false);
//        options.addPreference("browser.download.dir",
//                this.downloadDir.getAbsolutePath());
//        options.addPreference("network.automatic-ntlm-auth.trusted-uris", "http://,https://");
//        options.addPreference("network.automatic-ntlm-auth.allow-non-fqdn", true);
//        options.addPreference("network.negotiate-auth.delegation-uris", "http://,https://");
//        options.addPreference("network.negotiate-auth.trusted-uris", "http://,https://");
//        options.addPreference("network.http.phishy-userpass-length", 255);
//        options.addPreference("security.csp.enable", false);
//        options.addPreference("network.proxy.no_proxies_on", "");
//        options.addPreference("network.proxy.type", 1);
//        options.addPreference("network.proxy.http", proxyAddress);
//        options.addPreference("network.proxy.http_port", proxyPort);
//        options.addPreference("network.proxy.ssl", proxyAddress);
//        options.addPreference("network.proxy.ssl_port", proxyPort);
//        options.addPreference("network.proxy.share_proxy_settings", true);
//        options.addPreference("browser.startup.homepage", "about:blank");
//        options.addPreference("startup.homepage_welcome_url", "about:blank");
//        options.addPreference("startup.homepage_welcome_url.additional", "about:blank");
//        options.addArguments("-headless");
//        options.setCapability("screenResolution", "1280x1024x24");
        options.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
        options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
        options.setLogLevel(FirefoxDriverLogLevel.INFO);
//        options.setHeadless(true);
        this.driver = new FirefoxDriver(options);
    }
}
