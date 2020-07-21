package Exercises.Interrogation;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class DriverLevelInterrogation {
    public static WebDriver driver;

    @Before
    public void openApp(){
        driver = new ChromeDriver();
        driver.navigate().to("https://demo-teo.herokuapp.com/");
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
    }

    @Test
    public void getPageTitle(){
        Assert.assertTrue("title", driver.getTitle().contains("Spree Demo"));
    }

    @Test
    public void getURL(){
        Assert.assertTrue("url", driver.getCurrentUrl().contentEquals("https://demo-teo.herokuapp.com/"));
    }

    @Test
    public void getPageSource(){
        Assert.assertTrue("page source", driver.getPageSource().contains("Spree Demo Site"));
    }

    @After
    public void closeDriver(){
        driver.close();
    }
    @AfterClass
    public static void quitDriver(){
        driver.quit();
    }

}
