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

    @FindBy(how = How.XPATH, using = "//*[@class=\"alert alert-info\"]")
    private WebElement emptyCartMessage;

    @FindBy(how = How.XPATH, using = "//*[@class=\"lead text-primary cart-item-total\"]")
    private WebElement totalPricePerItem;

    @FindBy(how = How.XPATH, using = "//*[@class=\"lead text-primary cart-item-price\"]")
    private WebElement itemPrice;

    @FindBy(how = How.XPATH, using = "//*[@class=\"cart-item-description\"]")
    private WebElement itemName;

    @FindBy(how = How.XPATH, using = "//*[@class=\"form-control line_item_quantity\"]")
    private WebElement itemQuantity;

    @FindBy(how = How.ID_OR_NAME, using = "update-button")
    private WebElement updateButton;

    @FindBy(how = How.XPATH, using = "//*[@class=\"glyphicon glyphicon-minus-sign\"]")
    private WebElement deleteItem;

    @FindBy(how = How.XPATH, using = "//*[@id=\"clear_cart_link\"]/input")
    private WebElement deleteCart;

    @FindBy(how = How.XPATH, using = "//*[@class=\"lead\"]")
    private WebElement totalCartPrice;

    public String getEmptyCartMessage(){
        return emptyCartMessage.getText();
    }

    public double getTotalPricePerItem(){
        char currency = totalPricePerItem.getText().charAt(0);
        double price = Double.parseDouble(totalPricePerItem.getText().substring(1));
        return price;
    }

    public double getItemPrice(){
        char currency = itemPrice.getText().charAt(0);
        double price = Double.parseDouble(itemPrice.getText().substring(1));
        return price;
    }

    public String getItemName(){
        return itemName.getText();
    }

    public int getItemQuantity(){
        return Integer.parseInt(itemQuantity.getAttribute("value"));
    }

    public void modifyItemQuantity(String value){
        itemQuantity.clear();
        itemQuantity.sendKeys(value);
    }

    public void updateCart(){
        updateButton.click();
    }

    public void deleteItem(){
        deleteItem.click();
    }

    public void deleteCart(){
        deleteCart.click();
    }

    public double getTotalCartPrice(){
        char currency = totalCartPrice.getText().charAt(0);
        double price = Double.parseDouble(totalCartPrice.getText().substring(1));
        return price;
    }
}
