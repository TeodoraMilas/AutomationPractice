package HerokuTestCases;

import DriverManager.DriverManagerAbstraction;
import DriverManager.DriverManagerFactory;
import HelperMethods.ScreenshotMethodWithTestWatcher;
import HelperMethods.ScreenshotRuleWithMethodRule;
import HerokuPages.*;
import org.junit.*;
import org.junit.rules.TestRule;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import static HerokuPages.ProductListPage.ProductId;

public class ProductActions{
       private WebDriver driver;
        DriverManagerAbstraction driverManager =
                DriverManagerFactory.getDriverManager();

        public WebDriver getDriver(){
            driver = driverManager.getWebDriver();
            return driver;
        }

        WebDriver driverInUse = getDriver();

        ProductListPage productList = new ProductListPage(driverInUse);
        ProductDetailsPage productDetails = new ProductDetailsPage(driverInUse);

    //Rule to take screenshots in case a test fails
    //I'be built this rule in 3 different ways
    @Rule
    public ScreenshotRuleWithMethodRule screenshotTestRule = new ScreenshotRuleWithMethodRule(driverInUse);

//    @Rule
//    public ScreenshotRuleWithTestWatcherAndDirectory screenshotRule = new ScreenshotRuleWithTestWatcherAndDirectory(driverInUse);

//    @Rule
//    public TestRule screenshotTaker = new ScreenshotMethodWithTestWatcher((TakesScreenshot) driverInUse);

    @Before
    public void navigateToPage(){
        productList.get();
    }

    @Test
    public void productDetails(){
        productList.product(ProductId.SPAGHETTI);

        Assert.assertEquals("Ruby on Rails Jr. Spaghetti", productDetails.getTitle());
        driverManager.quitWebDriver();
    }

//    @Test
//    public void productDetailsFail(){
//        productList.product(ProductId.SPAGHETTI);
//
//        Assert.assertEquals("Bla", productDetails.getTitle());
//        driverManager.quitWebDriver();
//    }

    @Test
    public void productSearchByCategory(){
        productList.openDropdown();
        productList.selectCategoryDropdown(2);
        productList.submit();

        Assert.assertEquals("Categories", productList.breadcrumbs(3));
        Assert.assertEquals("Price Range", productList.sidebarFilter());
        driverManager.quitWebDriver();
    }

    //need to build a more complex test that also matches some products in list
    @Test
    public void productSearchByKeyword(){
        productList.searchByKeyword("bla");
        productList.submit();

        Assert.assertEquals(true, productList.searchResult());
        driverManager.quitWebDriver();
    }

    @Test
    public void productSearchByCategoryAndKeyword(){
        productList.openDropdown();
        productList.selectCategoryDropdown(2);
        productList.searchByKeyword("bla");
        productList.submit();

        Assert.assertEquals(true, productList.searchResult());
        Assert.assertEquals("Categories", productList.breadcrumbs(3));
        driverManager.quitWebDriver();
    }

    @Test
    public void productListFromSidebarCategories(){
        //category 1 or 2
        //child between 1 - 3 for 1 and 1 - 4 for 2
        productList.sidebarCategory(2, 4);

        Assert.assertEquals("Rails", productList.categoryTitle());
        driverManager.quitWebDriver();
    }

    @Test
    public void productListPagination(){
        //paginationID can be between 1-4
        productList.paginationBar(2);
        driver.navigate().refresh();
        productList.paginationBar(1);
        productList.product(ProductId.MUG);
        driver.navigate().back();
        driver.navigate().forward();

        Assert.assertEquals("Ruby on Rails Mug", productDetails.getTitle());
        driverManager.quitWebDriver();
    }

}
