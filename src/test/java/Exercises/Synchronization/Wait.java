package Exercises.Synchronization;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static java.lang.Thread.sleep;
import static java.util.concurrent.TimeUnit.SECONDS;

public class Wait {
    public static WebDriver driver;

    @Before
    public void openApp(){
        driver = new ChromeDriver();
        driver.navigate().to("http://compendiumdev.co.uk/selenium/basic_ajax.html");
    }

    @Test
    public void waitWithThreadSleep(){
        //select option from first dropdown
        WebElement dropdown1 = driver.findElement(By.id("combo1"));
        dropdown1.click();
        WebElement option = driver.findElement(By.xpath("//*[@id=\"combo1\"]/option[3]"));
        option.click();
        new WebDriverWait(driver, 20).until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"combo1\"]/option[3]")));
        Assert.assertEquals("Server", option.getText());


        //select option from second dropdown
        WebElement dropdown2 = driver.findElement(By.id("combo2"));
        dropdown2.click();

        // Create instance of Javascript executor
        List<WebElement> optionList = driver.findElements(By.tagName("option"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", optionList.get(3));
        //using Thread sleep to wait
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement option2 = driver.findElement(By.xpath("//*[@id=\"combo2\"]/option[4]"));
        option2.click();

       //submit form
        WebElement submit = driver.findElement(By.name("submitbutton"));
        submit.submit();

       //Identify element on new page
        WebElement number = driver.findElement(By.id("_valuelanguage_id"));
        Assert.assertEquals("23", number.getText());
    }

    @Test
    public void waitWithImplicitWait(){
        //select option from first dropdown
        WebElement dropdown1 = driver.findElement(By.id("combo1"));
        dropdown1.click();
        WebElement option = driver.findElement(By.xpath("//*[@id=\"combo1\"]/option[3]"));
        option.click();
        new WebDriverWait(driver, 20).until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"combo1\"]/option[3]")));
        Assert.assertEquals("Server", option.getText());

        //select option from second dropdown
        WebElement dropdown2 = driver.findElement(By.id("combo2"));
        dropdown2.click();

        // Create instance of Javascript executor
        List<WebElement> optionList = driver.findElements(By.tagName("option"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", optionList.get(3));

        //using implicit wait to wait for the element to appear
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement option2 = driver.findElement(By.xpath("//*[@id=\"combo2\"]/option[4]"));
        option2.click();

        //submit form
        WebElement submit = driver.findElement(By.name("submitbutton"));
        submit.submit();

       //Identify element on new page
        WebElement number = driver.findElement(By.id("_valuelanguage_id"));
        Assert.assertEquals("23", number.getText());
    }

    @Test
    public void waitWithExplicitWait(){
        //select option from first dropdown
        WebElement dropdown1 = driver.findElement(By.id("combo1"));
        dropdown1.click();
        WebElement option = driver.findElement(By.xpath("//*[@id=\"combo1\"]/option[3]"));
        option.click();
        new WebDriverWait(driver, 20).until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"combo1\"]/option[3]")));
        Assert.assertEquals("Server", option.getText());

        //select option from second dropdown
        WebElement dropdown2 = driver.findElement(By.id("combo2"));
        dropdown2.click();

        // Create instance of Javascript executor
        List<WebElement> optionList = driver.findElements(By.tagName("option"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", optionList.get(3));


        //using explicit wait to wait for the element to appear
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"combo2\"]/option[4]")));

        WebElement option2 = driver.findElement(By.xpath("//*[@id=\"combo2\"]/option[4]"));
        option2.click();

        //submit form
        WebElement submit = driver.findElement(By.name("submitbutton"));
        submit.submit();

        //Identify element on new page
        WebElement number = driver.findElement(By.id("_valuelanguage_id"));
        Assert.assertEquals("23", number.getText());}

    @Test
    public void waitWithFluentWait(){
        //select option from first dropdown
        WebElement dropdown1 = driver.findElement(By.id("combo1"));
        dropdown1.click();
        WebElement option = driver.findElement(By.xpath("//*[@id=\"combo1\"]/option[3]"));
        option.click();
        new WebDriverWait(driver, 20).until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"combo1\"]/option[3]")));
        Assert.assertEquals("Server", option.getText());

        //select option from second dropdown
        WebElement dropdown2 = driver.findElement(By.id("combo2"));
        dropdown2.click();

        // Create instance of Javascript executor
        List<WebElement> optionList = driver.findElements(By.tagName("option"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", optionList.get(3));

        //using fluent wait
        FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
                .withTimeout(60, SECONDS) // this defines the total amount of time to wait for
                .pollingEvery(2, SECONDS) // this defines the polling frequency
                .ignoring(NoSuchElementException.class); // this defines the exception to ignore
        WebElement option2 = fluentWait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver)  //in this method defined your own subjected conditions for which we need to wait for
            {  return driver.findElement(By.xpath("//*[@id=\"combo2\"]/option[4]"));
            }});

        option2.click();

        //submit form
        WebElement submit = driver.findElement(By.name("submitbutton"));
        submit.submit();

        //Identify element on new page
        WebElement number = driver.findElement(By.id("_valuelanguage_id"));
        Assert.assertEquals("23", number.getText());}

    @After
    public void closeDriver(){
        driver.close();
    }
    @AfterClass
    public static void quitDriver(){
        driver.quit();
    }
}
