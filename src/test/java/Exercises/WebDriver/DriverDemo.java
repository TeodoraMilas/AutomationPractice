package Exercises.WebDriver;

//A singleton style manager to maintain Drivers to prevent
// test slowdown for creating a browser for each class with tests.

//Also counts time to start a browser
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

public class DriverDemo extends Thread{
    private static WebDriver aDriver = null;
    private static long browserStartTime = 0L;
    private static  long savedTimecount = 0L;
    public static final long DEFAULT_TIMEOUT_SECONDS = 10;
    private static boolean avoidRecursiveCall = false;

    public enum BrowserName{FIREFOX, CHROME, OPERA, IE}

    public static BrowserName currentDriver;

    private static BrowserName useThisDriver = BrowserName.CHROME;

    public static String PROXY = "localhost:8080";

    public static void set(BrowserName aBrowser){
        useThisDriver = aBrowser;
    }

    public static WebDriver get(){
        if(aDriver == null) {
            long stratBrowserTime = System.currentTimeMillis();

            switch (useThisDriver) {
                case FIREFOX:
                    //FirefoxProfile profile = new FirefoxProfile();

                    aDriver = new FirefoxDriver();
                    currentDriver = BrowserName.FIREFOX;
                    break;

                case CHROME:
                    aDriver = new ChromeDriver();
                    currentDriver = BrowserName.CHROME;
                    break;

                case IE:
                    aDriver = new InternetExplorerDriver();
                    currentDriver = BrowserName.IE;
                    break;

                case OPERA:
                    aDriver = new OperaDriver();
                    currentDriver = BrowserName.OPERA;
                    break;
            }
        long browserStratedTime = System.currentTimeMillis();
            browserStartTime = browserStratedTime - stratBrowserTime;

            //we want to shutdown the shared browser when the test finish
            Runtime.getRuntime().addShutdownHook(
                    new Thread(){
                        public void run(){
                            aDriver.quit();
                        }
                    }
            );
        }else{
            try{
                //is browser still alive
                if(aDriver.getWindowHandle() != null){
                    //assume it is still alive
                }
            } catch (Exception e){
                //something has gone wrong
                throw new RuntimeException();
            }

            aDriver.quit();
            aDriver = null;
            avoidRecursiveCall = true;
            return get();
        }
        savedTimecount = savedTimecount + browserStartTime;
        System.out.println("Saved another " + browserStartTime + "ms : total saved " + savedTimecount + "ms");
        avoidRecursiveCall = false;
        return aDriver;
    }


}

