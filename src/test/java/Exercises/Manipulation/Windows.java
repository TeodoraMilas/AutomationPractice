package Exercises.Manipulation;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Windows {
    public static WebDriver driver;

    @Before
    public void openApp(){
        driver = new ChromeDriver();
        driver.navigate().to("https://testpages.herokuapp.com/styled/windows-test.html");
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
    }

    //open ajax page with name
    @Test
    public void openPageWithName(){
        WebElement clickOnLink;
        clickOnLink = driver.findElement(By.id("gobasicajax"));
        clickOnLink.click();

        driver.switchTo().window("basicajax");
        Assert.assertEquals("open window and get name", "Basic Ajax Example",
                driver.findElement(By.xpath("/html/body/div/h1")).getText());
    }

    //open ajax paqe without name
    @Test
    public void openPageNoName(){
        //returns the parent page
        final String currentHandle = driver.getWindowHandle();

        //navigate to child page
        WebElement clickOnLink;
        clickOnLink = driver.findElement(By.linkText("Attributes in new page"));
        clickOnLink.click();

        //return identifier for all pages(aka handlers)
        Set<String> myWindows = driver.getWindowHandles();

        //create a variable that will hold the value for child page
        String childHandler = "";

        //compare parent value with the existing values
        for(int i = 1; i < myWindows.size(); i++){
            if(!childHandler.equals(currentHandle)){
                childHandler.equals(childHandler);
            }
        }

        //switch to child page
        driver.switchTo().window(childHandler);

        //assert
        Assert.assertEquals("Element Attributes Examples",
                driver.findElement(By.xpath("/html/body/div/h1")).getText());
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
