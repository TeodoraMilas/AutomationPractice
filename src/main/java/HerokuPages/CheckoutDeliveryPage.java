package HerokuPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CheckoutDeliveryPage {
    private WebDriver driver;

    public CheckoutDeliveryPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//div[@class=\"panel-heading\"]")
    private WebElement pageTitle;

    @FindBy(how = How.XPATH, using = "//div[@class=\"alert alert-error\"]")
    private WebElement validationMessage;

    public String getPageTitle(){
        return pageTitle.getText();
    }

    public String getErrorMessage(){
        return validationMessage.getText();
    }
}
