package Exercises.Manipulation;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class iFrames {
    public static WebDriver driver;

    @Before
    public void openApp(){
        driver = new ChromeDriver();
        driver.navigate().to("https://testpages.herokuapp.com/styled/iframes-test.html");
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
    }

    @Test
    public void switchToSecondFrameByIndex(){
        driver.switchTo().frame(1);
        final WebElement element = driver.findElement(By.tagName("h1"));
        Assert.assertEquals("Nested Page Example", element.getText());
    }

    @Test
    public void switchToFrameByName(){
        driver.switchTo().frame("thedynamichtml");
        final WebElement element = driver.findElement(By.id("iframe3"));
        Assert.assertEquals("iFrame List Item 3", element.getText());
    }

    @Test
    public void switchToWebelement(){
        final WebElement title = driver.findElement(By.cssSelector("iframe#theheaderhtml"));
        driver.switchTo().frame(title);

        final WebElement element = driver.findElement(By.tagName("h1"));
        Assert.assertEquals("Nested Page Example", element.getText());
    }
    @Test
    public void switchToDefaultContext(){
        driver.switchTo().frame(0);
        Assert.assertEquals("iFrame", driver.findElement(By.tagName("h1")).getText());

        driver.switchTo().defaultContent();
        Assert.assertEquals("iFrames Example", driver.findElement(By.xpath("/html/body/div/h1")).getText());
    }

    @Test
    public void switchToParentFrame(){
        driver.switchTo().frame(0);
        Assert.assertEquals("iFrame", driver.findElement(By.tagName("h1")).getText());

        driver.switchTo().parentFrame();
        Assert.assertEquals("iFrames Example", driver.findElement(By.xpath("/html/body/div/h1")).getText());
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
