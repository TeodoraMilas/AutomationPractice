package Exercises.Manipulation;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WindowsManipulation {
    public static WebDriver driver;

    @Before
    public void openApp(){
        driver = new ChromeDriver();
        driver.navigate().to("https://testpages.herokuapp.com/styled/css-media-queries.html");
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
    }

    @Test
    public void resizePage(){
        Dimension dimension = driver.manage().window().getSize();
        Point point = driver.manage().window().getPosition();

        //make window half the size
        driver.manage().window().setSize(new Dimension(dimension.width / 2, dimension.height / 2));

        //move window to center of the screen
        driver.manage().window().setPosition(new Point(dimension.width / 4, dimension.height / 4));

        //fullscreen
        driver.manage().window().fullscreen();

        //maximize
        driver.manage().window().maximize();

        //set to original size and position
        driver.manage().window().setSize(dimension);
        driver.manage().window().setPosition(point);
    }
    //Write some test code to resize the browser and check that the various h2 elements disappear
    @Test
    public void changeDimensions(){
        Dimension dimension = driver.manage().window().getSize();

        driver.manage().window().fullscreen();
        WebElement element = driver.findElement(By.xpath("/html/body/div/div[3]/h2[3]"));
        WebElement smallElement = driver.findElement(By.xpath("/html/body/div/div[3]/h2[6]"));
        Assert.assertEquals("1400 Min Width", element.getText());

        driver.manage().window().setSize(new Dimension(dimension.width - 200, dimension.height - 200));

        Assert.assertEquals("1400 Min Width in not displayed anymore", false, element.isDisplayed());
        Assert.assertEquals("800 Min Width", smallElement.getText());

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
