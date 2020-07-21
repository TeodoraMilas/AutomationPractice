package Exercises.Interrogation;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.pagefactory.ByChained;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class DomLevelInterrogation {
    public static WebDriver driver;

    @Before
    public void openApp(){
        driver = new ChromeDriver();
        driver.navigate().to("https://demo-teo.herokuapp.com/");
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
    }

    @Test
    public void openProductDetails(){
            WebElement productButton = driver.findElement(By.id("product_4"));

        Assert.assertEquals("Ruby on Rails Jr. Spaghetti\n" +
                "$19.99", productButton.getText());
    }

    @Test
    public void identifyByLinkText(){
        WebElement loginButton = driver.findElement(By.linkText("LoginPractice"));
        Assert.assertEquals("LoginPractice", loginButton.getText());
    }

//    @Test
//    public void identifyByName(){
//        WebElement name = driver.findElement(By.name("Ruby on Rails Jr. Spaghetti"));
//        Assert.assertEquals("Ruby on Rails Jr. Spaghetti", name.getText());
//    }

    @Test
    public void identifyByPartialLinkText(){
        driver.navigate().to("https://demo-teo.herokuapp.com/login");
        WebElement partialLink = driver.findElement(By.partialLinkText("Create"));

        Assert.assertEquals("Create a new account", partialLink.getText());
    }

//    @Test
//    public void identifyByClassName(){
//        driver.navigate().to("https://demo-teo.herokuapp.com/products/ruby-on-rails-jr-spaghetti");
//        WebElement className = driver.findElement(By.className(""));
//        Assert.assertEquals("product-title", className.getAttribute("h1 class"));
//    }

    @Test
    public void identifyList(){
        List<WebElement> elements;
        elements = driver.findElements(By.className("list-group-item"));
        System.out.println(elements);
//        Set<String> foundTags = new HashSet<String>();
//
//        for(WebElement e : elements){
//            foundTags.add(e.getTagName());
//        }

        Assert.assertEquals(7, elements.size());
//        Assert.assertTrue("expected bags", elements.contains("bags"));
//        Assert.assertTrue("expected mugs", elements.contains("bags"));
//        Assert.assertTrue("expected clothing", elements.contains("clothing"));
}
    @Test
    public void elementChain(){
        WebElement element;
        element = driver.findElement(
                new ByChained(
                        By.id("taxonomies"),
                        By.className("taxonomy-root")));
        Assert.assertEquals("expected class", "Shop by Categories", element.getText());
    }
    @Test
    public void identifyByCss(){
        WebElement element;
        element = driver.findElement(By.cssSelector("#product_4"));

        Assert.assertEquals("Ruby on Rails Jr. Spaghetti\n" +
                "$19.99", element.getText());
    }

    @Test
    public void identifyByCss2(){
        WebElement element;
        element = driver.findElement(By.cssSelector(".taxonomy-root"));

        Assert.assertEquals("Shop by Categories", element.getText());
    }

    @Test
    public void idByXpath(){
        WebElement element;
        element = driver.findElement(By.xpath("//*[@id=\"product_4\"]"));

        Assert.assertEquals("Ruby on Rails Jr. Spaghetti\n" +
                "$19.99", element.getText());
    }

    @Test
    public void classByXpath(){
        WebElement element;
        element = driver.findElement(By.xpath("//*[@id=\"link-to-cart\"]/a/text()"));

        Assert.assertEquals(" Cart: (Empty)", element.getText());
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
