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

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CheckoutAddressPage {
    private static WebDriver driver;

    public CheckoutAddressPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//*[@class=\"btn btn-lg btn-success\"]")
    private WebElement saveAndContinueButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"order_bill_address_attributes_firstname\"]")
    private WebElement firstNameField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"order_bill_address_attributes_lastname\"]")
    private WebElement lastNameField;

    @FindBy(how = How.XPATH, using = "//div[@class=\"alert alert-success\"]")
    private WebElement validationMessage;

    @FindBy(how = How.XPATH, using = "//*[@id=\"order_bill_address_attributes_address1\"]")
    private WebElement billingAddressField;

    @FindBy(how = How.ID_OR_NAME, using = "order_bill_address_attributes_city")
    private WebElement cityField;

    @FindBy(how = How.ID_OR_NAME, using = "order_bill_address_attributes_zipcode")
    private WebElement zipcodeField;

    @FindBy(how = How.ID_OR_NAME, using = "order_bill_address_attributes_phone")
    private WebElement phoneField;

    @FindBy(how = How.ID_OR_NAME, using = "order_bill_address_attributes_state_id")
    private WebElement stateDropdown;

    @FindBy(how = How.XPATH, using = "//div[@class=\"alert alert-notice\"]")
    private WebElement signupSuccessMessage;

    public void saveAndContinue(){
        saveAndContinueButton.click();
    }

    public String getValidationMessage(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"alert alert-success\"]")));
        return validationMessage.getText();
    }

    public void clearAllFields(){
        firstNameField.clear();
        lastNameField.clear();
        billingAddressField.clear();
        cityField.clear();
        zipcodeField.clear();
        phoneField.clear();
    }

    public void insertValidDataInAllFields(){
        firstNameField.sendKeys("bla");
        lastNameField.sendKeys("bla");
        billingAddressField.sendKeys("bla");
        cityField.sendKeys("bla");
        selectState(2);
        zipcodeField.sendKeys("05001");
        phoneField.sendKeys("123");
    }

    public void selectState(int value){
        stateDropdown.click();
        List<WebElement> optionList = driver.findElements(By.tagName("option"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", optionList.get(value));
        //using explicit wait to wait for the element to appear
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[@id=\"order_bill_address_attributes_state_id\"]/option[" + value + "]")));
        WebElement element = driver.findElement(
                By.xpath("//*[@id=\"order_bill_address_attributes_state_id\"]/option[" + value + "]"));
        element.click();
    }
    public void updateDataInAllFields(){
        firstNameField.sendKeys("blabla");
        lastNameField.sendKeys("blabla");
        billingAddressField.sendKeys("blabla");
        cityField.sendKeys("blabla");
        zipcodeField.sendKeys("05002");
        phoneField.sendKeys("1234");
    }

    public String getFirstNameValue(){
        return firstNameField.getAttribute("value");
    }

    public String getSignupSuccessMessage(){
        return signupSuccessMessage.getText();
    }

    public Boolean getFirstNameAttribute(){
        return Boolean.valueOf(firstNameField.getAttribute("required"));
    }
}
