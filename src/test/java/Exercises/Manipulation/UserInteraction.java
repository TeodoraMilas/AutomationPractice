package Exercises.Manipulation;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class UserInteraction {
    public static WebDriver driver;

    @Before
    public void openApp(){
        driver = new ChromeDriver();
        driver.navigate().to("https://demo-teo.herokuapp.com/");
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
    }

    private void openLogin(){
        WebElement loginButton;
        loginButton = driver.findElement(By.id("link-to-login"));
        loginButton.click();
    }

    //login with non-existent user
    //experiment with actions
    @Test
    public void nonExistingUser(){
        openLogin();
        WebElement emailField;
        emailField = driver.findElement(By.id("spree_user_email"));
        WebElement passwordField;
        passwordField = driver.findElement(By.id("spree_user_password"));
        WebElement loginAction;
        loginAction = driver.findElement(By.name("commit"));

        Actions action = new Actions(driver);

        //perform actions on elements
        action.click(emailField).sendKeys("teodora.straton@gmail.com");
        action.click(passwordField).sendKeys("password");
        action.click(loginAction).perform();

        //assert
        WebElement errorMessage;
        errorMessage = driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]"));
        org.hamcrest.MatcherAssert.assertThat("Error message displayed",
                errorMessage.getText().contentEquals("Invalid email or password."));
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
