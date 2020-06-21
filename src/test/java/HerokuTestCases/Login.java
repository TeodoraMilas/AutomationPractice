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

public class Login {
   private WebDriver driver;
    DriverManagerAbstraction driverManager = DriverManagerFactory.getDriverManager();

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
    public void loginWithValidData(){
        userIdentity.navigateToLoginForm();
        userIdentity.clearFields();
        userIdentity.sendUserDataLogin(VALID_EMAIL, VALID_PASSWORD);

        Assert.assertEquals(true,
                productListPage.getValidationMessage().contains("Logged in successfully"));
    }

    @Test
    public void loginWithInvalidData(){
        userIdentity.navigateToLoginForm();
        userIdentity.clearFields();
        userIdentity.sendUserDataLogin("bla@bla", "bla");

        Assert.assertEquals(true,
                userIdentity.loginErrorMessageText().contains("Invalid email or password."));
        driverInUse.close();
    }

    @Test
    public void loginWithNonExistingUser(){
        userIdentity.navigateToLoginForm();
        userIdentity.clearFields();
        userIdentity.sendUserDataLogin("teodora.elena.milas@gmail.com", "teo123");

        Assert.assertEquals(true,
                userIdentity.loginErrorMessageText().contains("Invalid email or password."));
    }

    @Test
    public void loginWithEmptyData(){
        userIdentity.navigateToLoginForm();
        userIdentity.clearFields();
        userIdentity.sendUserDataLogin("", "");

        Assert.assertEquals(true,
                userIdentity.loginErrorMessageText().contains("Invalid email or password."));
    }

    @Test
    public void logout(){
        userIdentity.navigateToLoginForm();
        userIdentity.clearFields();
        userIdentity.sendUserDataLogin(VALID_EMAIL, VALID_PASSWORD);
        userIdentity.logout();

        Assert.assertEquals(true,
                productListPage.getValidationNotice().contains("Signed out successfully."));
    }

    @After
    public void closeDriver(){
        driverManager.quitWebDriver();
    }
}
