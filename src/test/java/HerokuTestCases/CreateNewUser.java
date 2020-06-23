package HerokuTestCases;

import DriverManager.DriverManagerAbstraction;
import DriverManager.DriverManagerFactory;
import HerokuPages.ProductListPage;
import HerokuPages.UserIdentityPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static HerokuPages.DataToUse.VALID_EMAIL;
import static HerokuPages.DataToUse.VALID_PASSWORD;

public class CreateNewUser {
    private WebDriver driver;
    DriverManagerAbstraction driverManager =
            DriverManagerFactory.getDriverManager();

    public WebDriver getDriver(){
        driver = driverManager.getWebDriver();
        return driver;
    }

    WebDriver driverInUse = getDriver();

    UserIdentityPage userIdentity = new UserIdentityPage(driverInUse);
    ProductListPage productListPage = new ProductListPage(driverInUse);

    @Before
    public void navigateToMainPage(){
        productListPage.get();
    }

    @Test
    public void navigateToSignUpForm(){
        userIdentity.navigateToLoginForm();
        userIdentity.clearFields();
        Assert.assertEquals("Login as Existing Customer", userIdentity.formTitleText);
        userIdentity.navigateToSignUpForm();
        Assert.assertEquals("New Customer", userIdentity.formTitleText);
    }

//    @Test
//    public void createUserWithValidData(){
//        userIdentity.navigateToLoginForm();
//        userIdentity.navigateToSignUpForm();
//        userIdentity.clearFields();
//        //last one used was 15
//        userIdentity.sendUserDataSignup("teodora.straton+015@gmail.com", "teo123");
//        Assert.assertEquals("Welcome! You have signed up successfully.",
//                productListPage.getValidationNotice());
//    }

    @Test
    public void createUserWithEmptyData(){
        userIdentity.navigateToLoginForm();
        userIdentity.navigateToSignUpForm();
        userIdentity.clearFields();

        userIdentity.sendUserDataSignup("", "");
        Assert.assertEquals(true,
                userIdentity.signUpErrorMessageText().contains("2 errors prohibited this record from being saved:"));
    }

    @Test
    public void createUserWithInvalidData(){
        userIdentity.navigateToLoginForm();
        userIdentity.navigateToSignUpForm();
        userIdentity.clearFields();

        userIdentity.sendUserDataSignup("bla@bla", "bla");
        Assert.assertEquals(true,
                userIdentity.signUpErrorMessageText().contains("2 errors prohibited this record from being saved:"));
    }

    @Test
    public void alreadyExistingUser(){
        userIdentity.navigateToLoginForm();
        userIdentity.navigateToSignUpForm();
        userIdentity.clearFields();

        userIdentity.sendUserDataSignup(VALID_EMAIL, VALID_PASSWORD);
        Assert.assertEquals(true,
                userIdentity.signUpErrorMessageText().contains("Email has already been taken"));
    }

    @After
    public void closeDriver(){
        driverManager.quitWebDriver();
    }
}
