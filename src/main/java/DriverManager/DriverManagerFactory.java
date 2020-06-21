package DriverManager;

public class DriverManagerFactory {
    public static final String BROWSER_PROPERTY_NAME = "property.webdriver";
    private static final  String DEFAULT_BROWSER = "CHROME";

    public static DriverManagerAbstraction getDriverManager() {
        DriverManagerAbstraction driverManager;
        // to allow setting the browser as a property or an environment variable
        String defaultBrowser = PropertyReader.getPropertyOrEnv(BROWSER_PROPERTY_NAME, DEFAULT_BROWSER);
        switch (defaultBrowser) {
            case "CHROME":
                driverManager = new ChromeDriverManagerAbstraction();
                break;
            case "FIREFOX":
                driverManager = new FirefoxDriverManagerAbstraction();
                break;
            case "OPERA":
                driverManager = new OperaDriverManagerAbstraction();
                break;
            case "EDGE":
                driverManager = new EdgeDriverManagerAbstraction();
                break;
            case "IE":
                driverManager = new IEDriverManagerAbstraction();
                break;
            default:
                throw new RuntimeException("Unknown Browser in " + BROWSER_PROPERTY_NAME + ": " + defaultBrowser);
        }
        return driverManager;
    }
}

