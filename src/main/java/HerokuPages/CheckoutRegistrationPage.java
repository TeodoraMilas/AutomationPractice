package HerokuPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CheckoutRegistrationPage {
    private WebDriver driver;

    public CheckoutRegistrationPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.LINK_TEXT, using = "Login as Existing Customer")
    private WebElement loginLink;

    @FindBy(how = How.XPATH, using = "//*[@id=\"spree_user_email\"]")
    private WebElement emailField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"spree_user_password\"]")
    private WebElement passwordField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"spree_user_password_confirmation\"]")
    private WebElement passwordConfirmationField;

    @FindBy(how = How.XPATH, using = "//*[@class=\"btn btn-lg btn-success btn-block\"][@value=\"Create\"]")
    private WebElement newUserSubmitButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"order_email\"]")
    private WebElement guestUserEmail;

    @FindBy(how = How.XPATH, using = "//*[@class=\"btn btn-lg btn-success btn-block\"][@value=\"Continue\"]")
    private WebElement guestUserSubmitButton;

    public void openLogin(){
        loginLink.click();
    }

    public void sendUserDataSignup(String user, String password) {
        emailField.sendKeys(user);
        passwordField.sendKeys(password);
        passwordConfirmationField.sendKeys(password);
        newUserSubmitButton.submit();
    }

    public void sendDataAsGuest(String email){
        guestUserEmail.sendKeys(email);
        guestUserSubmitButton.submit();
    }
}
