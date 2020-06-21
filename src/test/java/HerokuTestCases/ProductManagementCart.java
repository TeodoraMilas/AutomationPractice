package HerokuTestCases;

import HerokuPages.CartDetailsScreen;
import HerokuPages.ProductDetailsPage;
import HerokuPages.ProductListPage;
import HerokuPages.ProductListPage.ProductId;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ProductManagementCart {
    WebDriver driver = new ChromeDriver();
    ProductListPage productListPage = new ProductListPage(driver);
    ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);
    CartDetailsScreen cartDetailsScreen = new CartDetailsScreen(driver);

    @Before
    public void navigateToMainPage(){
        productListPage.get();
    }

    @Test
    public void addProductToCart(){
        Assert.assertEquals(true, productListPage.cardButtonState().contains("Empty"));
        productListPage.openCartDetails();
        Assert.assertEquals(true, cartDetailsScreen.getEmptyCartMessage().contains("Your cart is empty"));
        driver.navigate().back();
        productListPage.product(ProductId.SPAGHETTI);
        System.out.println(productListPage.productPrice);
        productDetailsPage.addProductToCart();
        System.out.println(cartDetailsScreen.getTotalPricePerItem());
        //Assert.assertEquals(productListPage.productPrice.contains("$19.99"), cartDetailsScreen.getTotalPricePerItem());

    }

    @Test
    public void increaseAndDecreaseItem(){}

    @Test
    public void deleteProductFromCart(){}

    @Test
    public void emptyCart(){}

//    @After
//    public void close(){
//        driver.close();
//    }
}
