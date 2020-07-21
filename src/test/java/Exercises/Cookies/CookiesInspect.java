package Exercises.Cookies;

import org.junit.*;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Iterator;
import java.util.Set;

public class CookiesInspect {
    public static WebDriver driver;

    //create method for retrieving all cookies
    private Set<Cookie> getAllCookies(){
        return driver.manage().getCookies();
    }

    @Before
    public void openApp() {
        driver = new ChromeDriver();
        driver.navigate().to("https://compendiumdev.co.uk/selenium/search.php");

        //start fresh by deleting all cookies
        driver.manage().deleteAllCookies();
    }

    //Return all cookies
    @Test
    public void getAllCookiesInfo(){
        driver.navigate().refresh();

        Set<Cookie> listOfCookies = driver.manage().getCookies();
        Iterator<Cookie> iterator = listOfCookies.iterator();

        Cookie myCookie;
        String myCookieName = "";

        while(iterator.hasNext()){
            myCookie = iterator.next();
            System.out.println("Cookie Name: " + myCookie.getName()
                    + "; Cookie domain: " + myCookie.getDomain()
                    + "; Cookie Value: " + myCookie.getValue()
                    + "; Path: " + myCookie.getPath()
                    + "; Expiry date: " + myCookie.getExpiry()
                    + "; Secure: " + myCookie.isSecure());
            if(myCookie.getName().equals("seleniumSimplifiedSearchLastVisit")){
                myCookieName = myCookie.getName();
            }
        }
        //assert that a certain cookie is found in the list
        Assert.assertEquals("Expected cookie", "seleniumSimplifiedSearchLastVisit", myCookieName);
    }

    @Test
    public void numberOfCookiesFromMethod(){
        driver.navigate().refresh();

        Assert.assertEquals("Second method for retrieving no of cookies", 3, getAllCookies().size());
    }

    @Test
    public void numberOfCookiesFromIteration(){
        driver.navigate().refresh();
        int count = 0;
        Iterator<Cookie> iterator = getAllCookies().iterator();

        while(iterator.hasNext()){
            Cookie myCookie = iterator.next();
            count = count + 1;
        }
        Assert.assertEquals("First method for retrieving number of cookies", 3, count);
    }

    @Test
    public void returnCookieValue(){
        driver.navigate().refresh();
        Cookie myCookie;
        String cookieValue = "";
        Iterator<Cookie> iterator = getAllCookies().iterator();

        while(iterator.hasNext()){
            myCookie = iterator.next();
            if(myCookie.getValue().equals("1")){
                cookieValue = myCookie.getValue();
            }
        }
        Assert.assertEquals("Cookie value returned by first method", "1", cookieValue);
    }

    @Test
    public void returnCookieValueFromList(){
        driver.navigate().refresh();

        Assert.assertEquals("Second method to return a value", "1",
                driver.manage().getCookieNamed("seleniumSimplifiedSearchNumVisits").getValue());
    }

    @After
    public void closeDriver() {
        driver.close();
    }

    @AfterClass
    public static void quitDriver() {
        driver.quit();
    }
}
