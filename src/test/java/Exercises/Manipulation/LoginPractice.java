package Exercises.Manipulation;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginPractice {
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

    //navigate to login page from main page
    @Test
    public void navigateToLoginForm(){
        openLogin();

        WebElement loginFormTitle;
        loginFormTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div[1]/h3"));

        Assert.assertEquals("Verify login form title", "LoginPractice as Existing Customer", loginFormTitle.getText());
    }

    //verify that cursor is focused on first field(autofocus)
    @Test
    public void autofocus(){
        openLogin();

        Assert.assertEquals("Incorrect focus", driver.switchTo().activeElement(),
                driver.findElement(By.id("spree_user_email")));
    }

    //clear fields if prefilled
    @Test
    public void clearFields(){
        openLogin();

        WebElement emailField;
        emailField = driver.findElement(By.id("spree_user_email"));
        WebElement passwordField;
        passwordField = driver.findElement(By.id("spree_user_password"));
        WebElement rememberMe;
        rememberMe = driver.findElement(By.id("spree_user_remember_me"));

        emailField.clear();
        passwordField.clear();

        org.hamcrest.MatcherAssert.assertThat("Field should be empty", emailField.getText().isEmpty());
        org.hamcrest.MatcherAssert.assertThat("Field should be empty", passwordField.getText().isEmpty());
        Assert.assertFalse("Field should be empty", rememberMe.isSelected());
    }

    //try to login with empty fields
    @Test
    public void loginNoData(){
        //navigate to login form
        openLogin();

        //identify elements and login with no data
        WebElement loginAction;
        loginAction = driver.findElement(By.name("commit"));
        loginAction.click();
        WebElement errorMessage;
        errorMessage = driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]"));
        org.hamcrest.MatcherAssert.assertThat("Error message displayed",
                errorMessage.getText().contentEquals("Invalid email or password."));
    }

    //experiment with submit method
    //experiment with sendKeys
    @Test
    public void loginWithInvalidData(){
        openLogin();
        //identify elements
        WebElement emailField;
        emailField = driver.findElement(By.id("spree_user_email"));
        WebElement passwordField;
        passwordField = driver.findElement(By.id("spree_user_password"));
        WebElement loginAction;
        loginAction = driver.findElement(By.name("commit"));

        //insert invalid data in fields
        emailField.sendKeys("test@test");
        passwordField.sendKeys("test");
        loginAction.submit();

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
