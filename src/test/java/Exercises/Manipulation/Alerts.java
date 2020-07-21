package Exercises.Manipulation;

import org.junit.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Alerts {
    public static WebDriver driver;

    @Before
    public void openApp(){
        driver = new ChromeDriver();
        driver.navigate().to("https://testpages.herokuapp.com/styled/alerts/alert-test.html");
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
    }

    @Test
    public void alert(){
        WebElement alertButton;
        alertButton = driver.findElement(By.id("alertexamples"));

        alertButton.click();

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("I am an alert box!", alert.getText());
        alert.accept();
    }

    @Test
    public void confirmCancel(){
        WebElement alertButton;
        alertButton = driver.findElement(By.id("confirmexample"));
        WebElement confirmationResult;
        confirmationResult = driver.findElement(By.id("confirmreturn"));

        alertButton.click();

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("I am a confirm alert", alert.getText());
        alert.dismiss();

        Assert.assertEquals("false", confirmationResult.getText());
    }

    @Test
    public void confirmOk(){
        WebElement alertButton;
        alertButton = driver.findElement(By.id("confirmexample"));
        WebElement confirmationResult;
        confirmationResult = driver.findElement(By.id("confirmreturn"));

        alertButton.click();

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("I am a confirm alert", alert.getText());
        alert.accept();

        Assert.assertEquals("true", confirmationResult.getText());
    }

    @Test
    public void promptCancel(){
        WebElement alertButton;
        alertButton = driver.findElement(By.id("promptexample"));
        WebElement confirmationResult;
        confirmationResult = driver.findElement(By.id("confirmreturn"));

        alertButton.click();

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("I prompt you", alert.getText());
        alert.dismiss();

        Assert.assertEquals("", confirmationResult.getText());
    }

    @Test
    public void promptOk(){
        WebElement alertButton;
        alertButton = driver.findElement(By.id("promptexample"));
        WebElement confirmationResult;
        confirmationResult = driver.findElement(By.id("confirmreturn"));

        alertButton.click();

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("I prompt you", alert.getText());
        alert.sendKeys("");
        alert.accept();

        Assert.assertEquals("", confirmationResult.getText());
    }

    @Test
    public void promptSendText(){
        WebElement alertButton;
        alertButton = driver.findElement(By.id("promptexample"));
        WebElement confirmationResult;
        confirmationResult = driver.findElement(By.xpath("//*[@id=\"promptreturn\"]"));

        alertButton.click();

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("I prompt you", alert.getText());
        alert.sendKeys("blabla");
        alert.accept();

        Assert.assertEquals("blabla", confirmationResult.getText());
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
