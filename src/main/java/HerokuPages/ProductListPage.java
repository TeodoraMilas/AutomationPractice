package HerokuPages;

import DriverManager.DriverManagerAbstraction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static HerokuPages.DataToUse.MAIN_PATH;

public class ProductListPage{
    private static WebDriver driver;
    DataToUse base = new DataToUse(driver);
    private String searchText;
    public String currency;
    public double productPrice;

    @FindBy(how = How.XPATH, using = "//*[@id=\"taxon\"]")
    private WebElement dropdown;

    @FindBy(how = How.XPATH, using = "//*[@id=\"search-bar\"]/form/input[2]")
    private WebElement submitButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"sidebar_products_search\"]/div/h4")
    private WebElement priceRangeFilter;

    @FindBy(how = How.XPATH, using = "//*[@id=\"keywords\"]")
    private WebElement searchField;

    @FindBy(how = How.XPATH, using = "//div[@class=\"alert alert-success\"]")
    private WebElement validationMessage;

    @FindBy(how = How.XPATH, using = "//div[@class=\"alert alert-notice\"]")
    private WebElement validationNotice;

    @FindBy(how = How.ID_OR_NAME, using = "link-to-cart")
    private WebElement linkToCartIcon;

    public ProductListPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void get(){
        driver.navigate().to(MAIN_PATH);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public enum ProductId {SPAGHETTI(4), MUG(13), SHIRT(5), JERSEY(7), BAG(3), TOTE(1);
    private int value;
        ProductId(int value) {
            this.value = value;
        }

        public int getValue(){
            return value;
        }
    }

    public void product(ProductId ProductId){
        WebElement element = driver.findElement(By.xpath("//*[@id=\"product_"
                                                + ProductId.getValue() + "\"]"));
        element.click();
    }

//    public void getPrice(ProductId ProductId){
//        WebElement element = driver.findElement(By.xpath("//*[@id=\"product_"
//                                                + ProductId + "\"]/div/div[2]"));
//        String fullPrice = element.getText();
//        currency = String.valueOf(fullPrice.charAt(0));
//        productPrice = fullPrice.subSequence(1, 5);
//    }

    public void openDropdown(){
        dropdown.click();
        //using implicit wait to wait for the element to appear
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public void selectCategoryDropdown(int categoryId){
        String option = "//*[@id=\"taxon\"]/option[" + categoryId + "]";
        WebElement category = driver.findElement(By.xpath(option));
        category.click();
    }

    public String breadcrumbs(int breadcrumbId){
        String crumb = "//*[@id=\"breadcrumbs\"]/ol/li[" + breadcrumbId + "]/span/a/span";
        WebElement element = driver.findElement(By.xpath(crumb));
        return element.getText();
    }

    public String sidebarFilter(){
        return priceRangeFilter.getText();
    }

    public void submit(){
        submitButton.click();
    }

    public void searchByKeyword(String text){
        searchField.sendKeys(text);
        searchText = text;
    }

    public boolean searchResult(){
        WebElement element = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/h6"));
        return element.getText().contains(searchText);
    }

    public void sidebarCategory(int category, int childCategory){
        WebElement element = driver.findElement(By.xpath("//*[@id=\"taxonomies\"]/div["
                                                            + category + "]/a["
                                                            + childCategory + "]"));
        element.click();
    }

    public String categoryTitle(){
        WebElement element = driver.findElement(By.xpath("//*[@id=\"content\"]/h1"));
        return element.getText();
    }

    public void paginationBar(int paginationId){
        WebElement element = driver.findElement(By.xpath("//*[@id=\"content\"]/div/ul/li["
                                                        + paginationId + "]/a"));
        element.click();
    }

    public String getValidationMessage(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"alert alert-success\"]")));
        return validationMessage.getText();
    }

    public String getValidationNotice(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"alert alert-notice\"]")));
        return validationNotice.getText();
    }

    public String cardButtonState(){
        return linkToCartIcon.getText();
    }

    public void openCartDetails(){
        linkToCartIcon.click();
    }
}
