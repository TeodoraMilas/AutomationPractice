package HerokuTestCases;

import DriverManager.DriverManagerAbstraction;
import DriverManager.DriverManagerFactory;
import HerokuPages.CartDetailsScreen;
import HerokuPages.ProductDetailsPage;
import HerokuPages.ProductListPage;
import HerokuPages.ProductListPage.ProductId;
import org.testng.Assert;
import org.testng.annotations.*;

//by default test are executed in alphabetical order in testng
public class ProductManagementCart {
    DriverManagerAbstraction driverManager =
            DriverManagerFactory.getDriverManager();

    ProductListPage productList;
    ProductDetailsPage productDetails;
    CartDetailsScreen cartDetails;

    @BeforeMethod(alwaysRun = true)
    public void navigateToMainPage(){
        productList = new ProductListPage(driverManager.getWebDriver());
        productDetails = new ProductDetailsPage(driverManager.getWebDriver());
        cartDetails = new CartDetailsScreen(driverManager.getWebDriver());
        productList.get();
    }

    @Test(priority = 1, groups = {"sanity"})
    public void addProductToCart(){
        Assert.assertEquals(true, productList.cardButtonState().contains("Empty"));
        productList.openCartDetails();
        Assert.assertEquals(true, cartDetails.getEmptyCartMessage().contains("Your cart is empty"));
        driverManager.getWebDriver().navigate().back();
        productList.product(ProductId.SPAGHETTI);
        double productDetailsPrice = productDetails.getPrice();
        productDetails.addProductToCart();
        Assert.assertTrue(productDetailsPrice == cartDetails.getItemPrice());
        Assert.assertTrue(cartDetails.getItemName().contains("Spaghetti"));
    }

    @Test(priority = 2, enabled = false, groups = "regression")
    public void increaseItemQty(){
        productList.product(ProductId.SPAGHETTI);
        productDetails.addProductToCart();
        System.out.println(cartDetails.getItemQuantity());
        Assert.assertEquals(1, cartDetails.getItemQuantity());
        cartDetails.modifyItemQuantity("3");
        cartDetails.updateCart();
        Assert.assertEquals(3, cartDetails.getItemQuantity());
        Assert.assertTrue(cartDetails.getItemPrice() * 3 == cartDetails.getTotalPricePerItem());
        Assert.assertTrue(cartDetails.getTotalPricePerItem() == cartDetails.getTotalCartPrice());
    }

    @Test(priority = 3, groups = {"regression"})
    public void decreaseItemQty(){
        productList.product(ProductId.SPAGHETTI);
        productDetails.addProductToCart();
        Assert.assertEquals(1, cartDetails.getItemQuantity());
        cartDetails.modifyItemQuantity("3");
        cartDetails.updateCart();
        cartDetails.modifyItemQuantity("2");
        Assert.assertEquals(2, cartDetails.getItemQuantity());
    }

    @Test(priority = 4, groups = {"regression"})
    public void updateQtyToZero(){
        productList.product(ProductId.SPAGHETTI);
        productDetails.addProductToCart();
        Assert.assertEquals(1, cartDetails.getItemQuantity());
        cartDetails.modifyItemQuantity("0");
        cartDetails.updateCart();
        Assert.assertEquals(true, cartDetails.getEmptyCartMessage().contains("Your cart is empty"));
    }

    @Test(priority = 5, groups = {"sanity"})
    public void deleteItem(){
        productList.product(ProductId.SPAGHETTI);
        productDetails.addProductToCart();
        Assert.assertEquals(1, cartDetails.getItemQuantity());
        cartDetails.deleteItem();
        Assert.assertEquals(true, cartDetails.getEmptyCartMessage().contains("Your cart is empty"));
    }

    @Test(priority = 6, groups = {"sanity", "regression"})
    public void emptyCart(){
        productList.product(ProductId.SPAGHETTI);
        productDetails.addProductToCart();
        Assert.assertEquals(1, cartDetails.getItemQuantity());
        cartDetails.deleteCart();
        Assert.assertEquals(true, cartDetails.getEmptyCartMessage().contains("Your cart is empty"));
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver(){
        driverManager.quitWebDriver();
    }
}
