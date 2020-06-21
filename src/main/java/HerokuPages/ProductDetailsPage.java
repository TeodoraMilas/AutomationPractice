package HerokuPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage{
    private static WebDriver driver;

    @FindBy(how = How.XPATH, using = "//*[@id=\"product-description\"]/h1")
    private WebElement productTitle;

    @FindBy(how = How.ID_OR_NAME, using = "add-to-cart-button")
    private WebElement addToCartButton;

    public ProductDetailsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getTitle(){
        return productTitle.getText();
    }

    public void addProductToCart(){
        addToCartButton.click();
    }
}
