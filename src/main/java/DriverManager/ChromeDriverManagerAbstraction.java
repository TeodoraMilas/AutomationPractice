//Some documentation for arguments
//https://chromium.googlesource.com/chromium/src/+/master/chrome/common/chrome_switches.cc
//https://chromium.googlesource.com/chromium/src/+/master/chrome/common/pref_names.cc
//https://peter.sh/experiments/chromium-command-line-switches/
//http://www.assertselenium.com/java/list-of-chrome-driver-command-line-arguments/
package DriverManager;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverManagerAbstraction extends DriverManagerAbstraction {
    public void createWebDriver(){
        ChromeOptions options = new ChromeOptions();
        //set your browser-specific options here
        options.addArguments("disable-infobars");
//        options.addArguments("create-browser-on-startup-for-tests");
//        options.addArguments("diagnostics");
//        options.addArguments("disable-background-networking");
//        options.addArguments("disable-default-apps");
//        options.addArguments("disable-zero-browsers-open-for-tests");
//        options.addArguments("homepage");
//        options.addArguments("incognito");
//        options.addArguments("keep-alive-for-test");
//        options.addArguments("make-default-browser");
//        options.addArguments("no-default-browser-check");
//        options.addArguments("no-proxy-server");
//        options.addArguments("new-window");
//        options.addArguments("product-version");
//        options.addArguments("restore-last-session");
//        options.addArguments("start-maximized");
//        options.addArguments("user-agent");
//        options.addArguments("version");
//        options.addArguments("window-size");
//        options.addArguments("disable_screenshots");
//        options.addArguments("homepage_is_newtabpage");
//        options.addArguments("--crash-test");
//        options.addArguments("--ignore-certificate-errors");
//        options.addArguments("--disable-popup-blocking");
//        options.addArguments("test-type");
//        options.addArguments("--window-size=1920,1080");
//        options.addArguments("--enable-precise-memory-info");
//        options.addArguments("--disable-popup-blocking");
//        options.addArguments("test-type=browser");
//        options.addArguments("enable-automation");
        this.driver = new ChromeDriver(options);
    }
}
