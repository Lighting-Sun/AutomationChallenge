package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductsPage extends BasePage{

    WebDriver driver;

    public ProductsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    private List<String> addedProducts = new ArrayList<>();

    By pageHeaderTitle =  By.xpath("//span[@class='title']");
    By burgerMenuButton = By.id("react-burger-menu-btn");
    By logoutSidebarButton = By.id("logout_sidebar_link");
    By productSortContainer = By.xpath("//select[@class='product_sort_container']");
    By lowToHighSortOption = By.xpath("//option[contains(text(),'Price (low to high)')]");
    By productsPrices = By.cssSelector("div.inventory_item_price");

    By productInfo = By.xpath("//div[@class='inventory_item_description']");
    By addToCartButton = By.xpath("//button[contains(text(),'Add to cart')]");
    By removeFromCartButtons = By.xpath("//button[contains(text(),'Remove')]");
    By cartQuantity = By.cssSelector("span.shopping_cart_badge");

    By productTitle = By.xpath("//div[@class='inventory_item_name']");



    public String getPageHeaderTitle (){
        return getElementText(pageHeaderTitle);
    }

    public void clickBurgerMenuButton (){
        clickOnWebElement(burgerMenuButton);
    }

    public void clickLogoutSidebarButton (){
        clickOnWebElement(logoutSidebarButton);
    }

    public void clickProductSortContainer (){
        clickOnWebElement(productSortContainer);
    }

    public void clickLowToHighSortOption (){
        clickOnWebElement(lowToHighSortOption);
    }

    public List<WebElement> getProductsPrices (){
        return getWebElementList(productsPrices);
    }

    public void clickOnCart (){
        clickOnWebElement(cartQuantity);
    }

    public List<WebElement> getProductInfo (){
        return getWebElementList(productInfo);
    }


    public int getRemoveFromCartButtons (){
        return getWebElementListSize(removeFromCartButtons);
    }

    public String getCartQuantity(){
        return getElementText(cartQuantity);
    }

    public List<String> getSortedAddedProducts(){
        return addedProducts = addedProducts.stream().sorted().collect(Collectors.toList());
    }


    public boolean compareProductsPrices (){
        int i = 0;
        List<Double> doublesList = new ArrayList<Double>();
        getProductsPrices()
                .forEach(priceWithSymbol -> Stream.of(priceWithSymbol)
                .map(price -> price.getText().split("\\$"))
                .flatMap(Stream::of)
                        .filter(priceWithBlanks -> !priceWithBlanks.isEmpty())
                        .forEach(price -> doublesList.add(Double.parseDouble(price))));

        while ((doublesList.size()-1) > i){
            if (doublesList.get(i) > doublesList.get(i+1)){
                return false;
            }
            i = i +1;
        }
        return true;
    }

    public void addProductsToCart () {

       List<WebElement> productsAvailable = getProductInfo();
       int productQuantity = (int) ((Math.random() * (productsAvailable.size()+1 - 2)) + 2);

        while ( productQuantity  > 0){
            int productToSelect = (int) ((Math.random() * (productsAvailable.size())));
            int index = productToSelect+1;
            productsAvailable.get(productToSelect).findElement(addToCartButton).click();
            addedProducts.add(productsAvailable.get(productToSelect).findElement(By.xpath("(//div[@class='inventory_item_name'])["+index+"]")).getText());
            productsAvailable.remove(productToSelect);
            productQuantity = productQuantity -1;
        }

    }



}
