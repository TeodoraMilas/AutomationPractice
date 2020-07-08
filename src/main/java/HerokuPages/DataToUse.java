package HerokuPages;

import org.openqa.selenium.WebDriver;

public class DataToUse {
    private WebDriver driver;

    public DataToUse(WebDriver driver){
        this.driver = driver;
    }

    //define constants
    static final String MAIN_PATH = "https://demo-teo.herokuapp.com/";
    static final String SIGNUP_PATH = "/signup";

    //valid user data
    final public static String VALID_EMAIL = "teodora.straton@gmail.com";
    final public static String VALID_PASSWORD = "teo123";

    //new user data
    final public static String NEW_USER_EMAIL = "teodora.straton+05@gmail.com";
}
