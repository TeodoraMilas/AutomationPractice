package HerokuTestCases;

import DriverManager.DriverManagerAbstraction;
import DriverManager.DriverManagerFactory;
import HerokuPages.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static HerokuPages.DataToUse.NEW_USER_EMAIL;
import static HerokuPages.DataToUse.VALID_EMAIL;
import static HerokuPages.DataToUse.VALID_PASSWORD;

public class CheckoutAddressActions {
    DriverManagerAbstraction driverManager =
            DriverManagerFactory.getDriverManager();

    ProductListPage productList;
    ProductDetailsPage productDetails;
    CartDetailsScreen cartDetails;
    CheckoutAddressPage checkoutAddressPage;
    CheckoutRegistrationPage checkoutRegistrationPage;
    UserIdentityPage userIdentityPage;
    CheckoutDeliveryPage checkoutDeliveryPage;

    @BeforeMethod(alwaysRun = true)
    public void navigateToMainPage(){
        productList = new ProductListPage(driverManager.getWebDriver());
        productDetails = new ProductDetailsPage(driverManager.getWebDriver());
        cartDetails = new CartDetailsScreen(driverManager.getWebDriver());
        checkoutAddressPage = new CheckoutAddressPage(driverManager.getWebDriver());
        checkoutRegistrationPage = new CheckoutRegistrationPage(driverManager.getWebDriver());
        userIdentityPage = new UserIdentityPage(driverManager.getWebDriver());
        checkoutDeliveryPage = new CheckoutDeliveryPage(driverManager.getWebDriver());

        productList.get();
        productList.product(ProductListPage.ProductId.SPAGHETTI);
        productDetails.addProductToCart();
    }

    @Test(priority = 4, groups = {"regression"})
    public void continueWithNoAddressData(){
        cartDetails.navigateToCheckout();
        checkoutRegistrationPage.openLogin();
        userIdentityPage.sendUserDataLogin(VALID_EMAIL, VALID_PASSWORD);
        Assert.assertTrue(checkoutAddressPage.getValidationMessage().contains("Logged in successfully"));
        checkoutAddressPage.clearAllFields();
        checkoutAddressPage.saveAndContinue();
        Boolean requiredField = Boolean.valueOf(checkoutAddressPage.getFirstNameAttribute());
        Assert.assertTrue(requiredField);
    }

    @Test(priority = 1, groups = {"sanity", "regression"})
    public void continueWithValidAddress(){
        cartDetails.navigateToCheckout();
        checkoutRegistrationPage.openLogin();
        userIdentityPage.sendUserDataLogin(VALID_EMAIL, VALID_PASSWORD);
        Assert.assertTrue(checkoutAddressPage.getValidationMessage().contains("Logged in successfully"));
        checkoutAddressPage.clearAllFields();
        checkoutAddressPage.insertValidDataInAllFields();
        checkoutAddressPage.saveAndContinue();
        Assert.assertTrue(checkoutDeliveryPage.getPageTitle().contains("Delivery"));
    }

    @Test(priority = 3, groups = {"sanity", "regression"})
    public void updateExistingAddressData(){
        cartDetails.navigateToCheckout();
        checkoutRegistrationPage.openLogin();
        userIdentityPage.sendUserDataLogin(VALID_EMAIL, VALID_PASSWORD);
        Assert.assertTrue(checkoutAddressPage.getValidationMessage().contains("Logged in successfully"));
        checkoutAddressPage.clearAllFields();
        checkoutAddressPage.insertValidDataInAllFields();
        checkoutAddressPage.selectState(55);
        checkoutAddressPage.saveAndContinue();
        Assert.assertTrue(checkoutDeliveryPage.getPageTitle().contains("Delivery"));
        driverManager.getWebDriver().navigate().back();
        checkoutAddressPage.updateDataInAllFields();
        checkoutAddressPage.selectState(2);
        checkoutAddressPage.saveAndContinue();
        Assert.assertTrue(checkoutDeliveryPage.getPageTitle().contains("Delivery"));
        Assert.assertTrue(checkoutDeliveryPage.getErrorMessage().contains("The order has already been updated."));
    }

    @Test(priority = 8, groups = {"regression"})
    public void addressInfoForNewUser(){
        cartDetails.navigateToCheckout();
        checkoutRegistrationPage.openLogin();
        userIdentityPage.sendUserDataLogin(NEW_USER_EMAIL, VALID_PASSWORD);
        Assert.assertTrue(checkoutAddressPage.getValidationMessage().contains("Logged in successfully"));
        Assert.assertEquals(checkoutAddressPage.getFirstNameValue().length(), 0);
        checkoutAddressPage.saveAndContinue();
        Boolean requiredField = Boolean.valueOf(checkoutAddressPage.getFirstNameAttribute());
        Assert.assertTrue(requiredField);
    }

    @Test(priority = 2, groups = {"regression"})
    public void addressInfoForExistingUser(){
        cartDetails.navigateToCheckout();
        checkoutRegistrationPage.openLogin();
        userIdentityPage.sendUserDataLogin(VALID_EMAIL, VALID_PASSWORD);
        Assert.assertTrue(checkoutAddressPage.getValidationMessage().contains("Logged in successfully"));
        Assert.assertFalse(checkoutAddressPage.getFirstNameValue().length() == 0);
        checkoutAddressPage.saveAndContinue();
        Assert.assertTrue(checkoutDeliveryPage.getPageTitle().contains("Delivery"));
    }

    @Test(priority = 7, enabled = false, groups = {"sanity, regression"})
    public void checkoutForNewCreatedUser(){
        cartDetails.navigateToCheckout();
        checkoutRegistrationPage.sendUserDataSignup("teodora.straton+026@gmail.com", "teo123");
        Assert.assertTrue(checkoutAddressPage.getSignupSuccessMessage()
                .contains("Welcome! You have signed up successfully."));
        checkoutAddressPage.insertValidDataInAllFields();
        checkoutAddressPage.selectState(56);
        checkoutAddressPage.saveAndContinue();
        Assert.assertTrue(checkoutDeliveryPage.getPageTitle().contains("Delivery"));
    }

    @Test(priority = 5, enabled = false, groups = {"regression"})
    public void checkoutForAnonymousUser(){
        cartDetails.navigateToCheckout();
        checkoutRegistrationPage.sendDataAsGuest("teodora.straton@gmail.com");
        checkoutAddressPage.insertValidDataInAllFields();
        checkoutAddressPage.selectState(56);
        checkoutAddressPage.saveAndContinue();
        Assert.assertTrue(checkoutDeliveryPage.getPageTitle().contains("Delivery"));
    }

    @Test(priority = 6, groups = {"regression"})
    public void checkoutForLoggedUser(){
        cartDetails.navigateToCheckout();
        checkoutRegistrationPage.openLogin();
        userIdentityPage.sendUserDataLogin(VALID_EMAIL, VALID_PASSWORD);
        Assert.assertTrue(checkoutAddressPage.getValidationMessage().contains("Logged in successfully"));
        checkoutAddressPage.saveAndContinue();
        Assert.assertTrue(checkoutDeliveryPage.getPageTitle().contains("Delivery"));
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver(){
        driverManager.quitWebDriver();
    }
}
