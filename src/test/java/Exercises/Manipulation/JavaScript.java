package Exercises.Manipulation;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class JavaScript {
    public static WebDriver driver;

    private void waitForPage(){
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
    }

    @Before
    public void openApp(){
        driver = new ChromeDriver();
        driver.navigate().to("https://demo-teo.herokuapp.com/");
    }

    //perform a sleep in the browser under test
    @Test
    public void performSleep(){
        long start = System.currentTimeMillis();
        ((JavascriptExecutor) driver).executeAsyncScript(
                "window.setTimeout(arguments[arguments.length - 1], 500);");
        System.out.println("Elapsed time: " + (System.currentTimeMillis() - start));
    }
    //make an assert on the time passed based on the sleep method
    @Test
    public void calculatePassedTime(){
        //Set ScriptTimeout with implicitWait
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

        //Declare and set start time
        long startTime = System.currentTimeMillis();

        //Declare JavascriptExecutor
        JavascriptExecutor js =(JavascriptExecutor)driver;

        //Call executeAsyncScript() method
        js.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 4000);");

        //Get the difference (currentTime - startTime) it should be greater than 1500
        System.out.println("Passed time: " + (System.currentTimeMillis() - startTime));

        //Assert that the time difference is greater than 4000
        Assert.assertTrue("Time difference must be greater than 4000 milliseconds",
                (System.currentTimeMillis() - startTime) > 4000);
    }

    //wait for the whole page to load
    @Test
    public void waitForCompletePageToLoad(){
        WebElement element = driver.findElement(By.id("home-link"));
        new WebDriverWait(driver, 20).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));

        Assert.assertEquals("Wait for element", "Home", element.getText());
    }

    //wait for an element to be displayed

    //generate alert
    @Test
    public void generateAlert(){
        waitForPage();

        JavascriptExecutor js =(JavascriptExecutor)driver;
        js.executeScript("alert('Teo was here');");
        driver.switchTo().alert().accept();
    }

    @Test
    public void persistFunction(){
        waitForPage();
        JavascriptExecutor js = (JavascriptExecutor)driver;
        //"window.webdrivercallback = function(){};"

        //this code runs as an anonymouse function with no trace left
        js.executeScript("alert('alert triggered by webdriver');");
        assertThat(driver.switchTo().alert().getText(), is(
                "stored alert triggered by webdriver"));
        driver.switchTo().alert().accept();

        //this code creates a function that will persist as we have added it to the global window
        js.executeScript("window.webdriveralert = function(){alert('stored alert triggered by webdriver');" +
                "window.webdriveralert.call();");
        assertThat(driver.switchTo().alert().getText(), is(
                "stored alert triggered by webdriver"));
        driver.switchTo().alert().accept();

        //this can only work if we managed to leave javascript lying around
        js.executeScript("window.webdriveralert.call();");
        assertThat(driver.switchTo().alert().getText(), is(
                "stored alert triggered by webdriver"));
        driver.switchTo().alert().accept();
    }
    //scrolling down
    @Test
    public void scrollDown(){
        waitForPage();

        WebElement element = driver.findElement(By.xpath("//*[@id=\"product_7\"]/div/div[1]/a/span"));
        JavascriptExecutor js =(JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView(true);",element);

        Assert.assertEquals("Last element on the page", "Apache Baseball Jersey", element.getText());
    }

    //evaluate xpath
    @Test
    public void evaluateXpath(){
        waitForPage();
        WebElement element = driver.findElement(By.xpath("//*[@id=\"product_15\"]/div/div[1]/a/span"));

        JavascriptExecutor js = (JavascriptExecutor)driver;

        String firstname = (String) js.executeScript("return arguments[0].childNodes[0].textContent", element);
        System.out.println(firstname);
    }

    //identify elements
    @Test
    public void returnTitle(){
        waitForPage();
        JavascriptExecutor js =(JavascriptExecutor)driver;
        String title = js.executeScript("return document.title;").toString();
        assertThat(driver.getTitle(), is(title));
    }
    //interact with elements
    @Test
    public void hideElement(){
        waitForPage();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("document.getElementById('home-link').style.display='none'");

        WebElement element = driver.findElement(By.xpath("//*[@id=\"home-link\"]/a"));
        Assert.assertFalse(element.isDisplayed());
    }

    @Test
    public void clickOnElements(){
        waitForPage();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement elementToClick = driver.findElement(By.xpath("//*[@id=\"product_7\"]/div/div[1]/a/span"));
        js.executeScript("arguments[0].scrollIntoView()", elementToClick);
        js.executeScript("arguments[0].click();", elementToClick);

        Assert.assertEquals("Page details should be displayed", "Apache Baseball Jersey",
                driver.findElement(By.xpath("//*[@id=\"product-description\"]/h1")).getText());
    }

    @Test
    public void sendText(){
        waitForPage();

        WebElement loginButton;
        loginButton = driver.findElement(By.id("link-to-login"));
        loginButton.click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('spree_user_email').value = 'bla';");
        js.executeScript("document.getElementById('spree_user_password').value = 'blabla';");

        WebElement elementToClick = driver.findElement(By.name("commit"));
        js.executeScript("arguments[0].click();", elementToClick);

        WebElement errorMessage;
        errorMessage = driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]"));

        assertThat("Error message displayed",
                errorMessage.getText().contentEquals("Invalid email or password."));
    }

    //changing style of the elements
    @Test
    public void highlightElement(){
        waitForPage();
        WebElement sevenButton = driver.findElement(By.xpath("//*[@id=\"taxonomies\"]/div[1]/a[3]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='3px dotted blue'", sevenButton);
    }

    //getting elements attributes
    //adding elements in the DOM
    //size of the window


    //navigate to different pages
    @Test
    public void navigateToDifferentPage(){
        waitForPage();
        JavascriptExecutor js =(JavascriptExecutor)driver;
        js.executeScript("window.location = 'https://demo-teo.herokuapp.com/t/categories/bags'");

        Assert.assertEquals("Bags", driver.findElement(By.xpath("//*[@id=\"content\"]/h1")).getText());
    }

    //refresh browser page
    @Test
    public void refreshPage(){
        waitForPage();
        JavascriptExecutor js =(JavascriptExecutor)driver;
        js.executeScript("history.go(0);");
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
