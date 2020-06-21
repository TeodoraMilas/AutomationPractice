package HerokuPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CartDetailsScreen {
    private static WebDriver driver;

    public CartDetailsScreen(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"content\"]")
    private WebElement emptyCartMessage;

    @FindBy(how = How.XPATH, using = "//*[@id=\"line_items\"]/tr/td[5]")
    private WebElement totalPricePerItem;

    public String getEmptyCartMessage(){
        return emptyCartMessage.getText();
    }

    public String getTotalPricePerItem(){
        return totalPricePerItem.getText();
    }
}
