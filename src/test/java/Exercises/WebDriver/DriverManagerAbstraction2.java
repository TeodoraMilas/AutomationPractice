package Exercises.WebDriver;

//A singleton style manager to maintain Drivers to prevent
// test slowdown for creating a browser for each class with tests.

import org.openqa.selenium.UnsupportedCommandException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

public class DriverManagerAbstraction2 extends Thread{
    private static WebDriver driver = null;
    private static boolean avoidRecursiveCall = false;

    public enum BrowserName{FIREFOX, CHROME, OPERA, IE}

    public static BrowserName currentDriver;
    private static BrowserName useThisDriver = BrowserName.CHROME;


    public static void set(BrowserName browser){
        useThisDriver = browser;
    }

    public static WebDriver get(){
        if(driver == null) {
            switch (useThisDriver) {
                case FIREFOX:
                    //FirefoxProfile profile = new FirefoxProfile();

                    driver = new FirefoxDriver();
                    currentDriver = BrowserName.FIREFOX;
                    break;

                case CHROME:
                    driver = new ChromeDriver();
                    currentDriver = BrowserName.CHROME;
                    break;

                case IE:
                    driver = new InternetExplorerDriver();
                    currentDriver = BrowserName.IE;
                    break;

                case OPERA:
                    driver = new OperaDriver();
                    currentDriver = BrowserName.OPERA;
                    break;
            }
            //we want to shutdown the shared browser when the test finish
            Runtime.getRuntime().addShutdownHook(new Thread(){
                public void run(){ driver.quit();}
                    }
            );
        }else{
            try{
                //is browser still alive
                if(driver.getWindowHandle() != null){
                    //assume it is still alive
                }
            } catch (Exception e){
                //something has gone wrong
                throw new RuntimeException();
            }

            driver.quit();
            driver = null;
            avoidRecursiveCall = true;
            return get();
        }
        avoidRecursiveCall = false;
        return driver;
    }

    public static WebDriver get(String aURL, boolean maximize){
        get();
        driver.get(aURL);
        if(maximize){
            try{
                driver.manage().window().maximize();
            }
            catch (UnsupportedCommandException e){
                System.out.println("Remote Driver does not support maximise");
            }
            catch (UnsupportedOperationException e){
                System.out.println("Opera driver dows not support maximize yet");
            }
        }
        return driver;
    }

    public static WebDriver get(String aURL){
        return get(aURL, true);
    }

    public static void quit(){
        if(driver != null){
            try{
                driver.quit();
                driver = null;
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

