package Exercises.Cookies;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Time;
import java.util.Set;

public class CookiesManipulation {
    public static WebDriver driver;
    private WebElement inputField;
    private WebElement submitButton;

    private void refreshPageElements(){
        inputField = driver.findElement(By.name("q"));
        submitButton = driver.findElement(By.name("btnG"));
    }

    @Before
    public void openApp() {
        driver = new ChromeDriver();
        driver.navigate().to("https://compendiumdev.co.uk/selenium/search.php");

        driver.manage().deleteAllCookies();

        refreshPageElements();
    }

    //delete a cookie with a certain name
    @Test
    public void deleteACookieByName(){
        driver.manage().deleteCookieNamed("DYNSRV");

        Assert.assertEquals("Cookie should be deleted",
                null, driver.manage().getCookieNamed("DYNSRV"));
    }
    //delete all cookies
    @Test
    public void deleteAllCookies(){
        driver.manage().deleteAllCookies();

        Assert.assertEquals("There should be no cookies left",
                0, driver.manage().getCookies().size());
    }

    //delete cookie created by user
    @Test
    public void deleteUserCookies(){
        driver.manage().addCookie(new Cookie("CookieName", "1"));
        driver.manage().deleteCookieNamed("CookieName");
        Assert.assertEquals("my cookie is dead",
                null, driver.manage().getCookieNamed("CookieName"));
    }

    //add a new cookie
    @Test
    public void createNewCookieFirstMethod(){
        driver.manage().addCookie(new Cookie("mycookie", "val 1"));
        Assert.assertEquals("my sweet cookie",
                "mycookie", driver.manage().getCookieNamed("mycookie").getName());
        Assert.assertEquals("return cookie value",
                "val 1", driver.manage().getCookieNamed("mycookie").getValue());
    }

    @Test
    public void createNewCookieSecondMethod(){
        Cookie mySweetCookie = new Cookie("cookieName", "cookieValue",
                "https://compendiumdev.co.uk/selenium/search.php",
                "/mypath", new Time(1653051571), true);
        Assert.assertEquals("return name of my sweet cookie",
                "cookieName", mySweetCookie.getName());
    }

    //add multiple cookies
    @Test
    public void createSetOfCookies(){
        driver.navigate().refresh();
        Set<Cookie> myListOfCookies = driver.manage().getCookies();
        Cookie mySweetCookie = new Cookie("sweet", "1");
        Cookie myBitterCookie = new Cookie("bitter", "2");
        myListOfCookies.add(mySweetCookie);
        myListOfCookies.add(myBitterCookie);

        Assert.assertEquals("there should be 5 cookies", 5, myListOfCookies.size());
    }
    //create cookie with builder
    @Test
    public void createCookieWithBuilder(){
        Cookie choco = new Cookie.Builder("cookieName", "val1").build();
        driver.manage().addCookie(choco);
        Assert.assertEquals("cookieName", choco.getName());
    }
    //refresh page and get new value
    @Test
    public void retrieveDifferentValue(){
        inputField.clear();
        inputField.sendKeys("selenium");
        submitButton.click();
        refreshPageElements();
        String bla = driver.manage().getCookieNamed("seleniumSimplifiedSearchNumVisits").getValue();

        inputField.clear();
        inputField.sendKeys("driver");
        submitButton.click();
        String blabla = driver.manage().getCookieNamed("seleniumSimplifiedSearchNumVisits").getValue();
        int newValue = Integer.parseInt(blabla);
        int oldValue = Integer.parseInt(bla);
        int difference = newValue - oldValue;
        Assert.assertEquals(1, difference);
    }
    //clone a cookie
    @Test
    public void cloneCookie(){
        inputField.clear();
        inputField.sendKeys("selenium");
        submitButton.click();

        refreshPageElements();

        Cookie myCookie = driver.manage().getCookieNamed("seleniumSimplifiedSearchNumVisits");
        Assert.assertEquals("Should be my first visit", 1, Integer.parseInt(myCookie.getValue()));

        //clone cookie
        //you can also do this by using cookie builder
        //you can also do this by creating a new cookie
        Cookie myNewCookie = new Cookie(myCookie.getName(),
                String.valueOf(4),
                myCookie.getDomain(),
                myCookie.getPath(),
                myCookie.getExpiry(),
                myCookie.isSecure());
        //delete all cookie and keep only the cloned one
        driver.manage().deleteCookie(myCookie);
        driver.manage().addCookie(myNewCookie);

        inputField.clear();
        inputField.sendKeys("driver");
        submitButton.click();

        myCookie = driver.manage().getCookieNamed("seleniumSimplifiedSearchNumVisits");
        Assert.assertEquals("Should be my third visit", 5, Integer.parseInt(myCookie.getValue()));
    }
    //do same thing with cookie builder
    @Test
    public void getRefreshedValueWithBuilder(){
        inputField.clear();
        inputField.sendKeys("selenium");
        submitButton.click();

        refreshPageElements();

        Cookie myCookie = driver.manage().getCookieNamed("seleniumSimplifiedSearchNumVisits");
        Assert.assertEquals("Should be my first visit", 1, Integer.parseInt(myCookie.getValue()));
        //clone cookie with cookie builder
        Cookie myNewCookie = new Cookie.Builder(myCookie.getName(),
                                            String.valueOf(25))
                                            .domain(myCookie.getDomain())
                                            .path(myCookie.getPath())
                                            .expiresOn(myCookie.getExpiry())
                                            .isSecure(myCookie.isSecure())
                                            .build();
        driver.manage().deleteCookie(myCookie);
        driver.manage().addCookie(myNewCookie);

        inputField.clear();
        inputField.sendKeys("driver");
        submitButton.click();
        myCookie = driver.manage().getCookieNamed("seleniumSimplifiedSearchNumVisits");
        Assert.assertEquals("My new cookie", 26, Integer.parseInt(myCookie.getValue()));
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
