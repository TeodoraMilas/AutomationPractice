package HerokuPages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserIdentityPage {
    private static WebDriver driver;
    public String formTitleText;

    @FindBy(how = How.XPATH, using = "//div[@class=\"panel-heading\"]")
    private WebElement formTitle;

    @FindBy(how = How.ID_OR_NAME, using = "spree_user_email")
    private WebElement emailField;

    @FindBy(how = How.ID, using = "spree_user_password")
    private WebElement passwordField;

    @FindBy(how = How.ID, using = "spree_user_password_confirmation")
    private WebElement passwordConfirmation;

    @FindBy(how = How.ID, using = "spree_user_remember_me")
    private WebElement rememberMe;

    @FindBy(how = How.XPATH, using = "//*[@id=\"new_spree_user\"]/div/p/input")
    private WebElement submitButton;

    @FindBy(how = How.ID_OR_NAME, using = "link-to-login")
    private WebElement loginButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"nav-bar\"]/li[2]/a")
    private WebElement logoutButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"existing-customer\"]/div[1]/a[1]")
    private WebElement createNewUserLink;

    @FindBy(how = How.XPATH, using = "//div[@class=\"alert alert-danger\"]")
    private WebElement signupErrorMessage;

    @FindBy(how = How.XPATH, using = "//div[@class=\"alert alert-error\"]")
    private WebElement loginErrorMessage;

    public UserIdentityPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void navigateToLoginForm(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("link-to-login")));
        loginButton.click();
        formTitleText = formTitle.getText();
    }

    public void navigateToSignUpForm(){
        createNewUserLink.click();
        formTitleText = formTitle.getText();
    }

    public void clearFields(){
        emailField.clear();
        passwordField.clear();
    }

    public void sendUserDataSignup(String user, String password){
        emailField.sendKeys(user);
        passwordField.sendKeys(password);
        passwordConfirmation.sendKeys(password);
        submitButton.submit();
    }

    public void sendUserDataLogin(String user, String password){
        emailField.sendKeys(user);
        passwordField.sendKeys(password);
        submitButton.submit();
    }
    public String signUpErrorMessageText(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"alert alert-danger\"]")));
        return signupErrorMessage.getText();
    }

    public void logout(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"nav-bar\"]/li[2]/a")));
        logoutButton.click();
    }

    public String loginErrorMessageText(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"alert alert-error\"]")));
        return loginErrorMessage.getText();
    }
}


