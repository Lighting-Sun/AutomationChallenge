package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductsPage extends BasePage {



    private List<String> addedProducts = new ArrayList<>();

    @FindBy (xpath = "//span[@class='title']")
            private WebElement pageHeaderTitle;

    @FindBy (id = "react-burger-menu-btn")
            private WebElement burgerMenuButton;

    @FindBy (id = "logout_sidebar_link")
            private WebElement logoutSidebarButton;

    @FindBy (xpath = "//select[@class='product_sort_container']")
            private WebElement productSortContainer;

    @FindBy (xpath = "//option[contains(text(),'Price (low to high)')]")
            private WebElement lowToHighSortOption;

    @FindBy (css = "div.inventory_item_price")
            private List<WebElement> productsPrices;

    @FindBy (xpath = "//div[@class='inventory_item_description']")
            private List<WebElement> productInfo;

    @FindBy (xpath = "//button[contains(text(),'Add to cart')]")
            private List<WebElement> addToCartButton;

    @FindBy (xpath = "//button[contains(text(),'Remove')]")
            private List<WebElement> removeFromCartButton;

    @FindBy (css = "span.shopping_cart_badge")
            private WebElement cartQuantityBadge;

    @FindBy(xpath = "//div[@class='inventory_item_name']")
            private List<WebElement> productTitle;

    public ProductsPage() throws IOException {
    }


    public WebElement getPageHeaderTitle (){
        return pageHeaderTitle;
    }

    public void clickBurgerMenuButton (){
        burgerMenuButton.click();
    }

    public WebElement getLogoutSidebarButton (){
        return logoutSidebarButton;
    }

    public WebElement getProductSortContainer (){
        return productSortContainer;
    }

    public WebElement getLowToHighSortOption (){
        return lowToHighSortOption;
    }

    public List<WebElement> getProductsPrices (){
        return productsPrices;
    }

    public List<WebElement> getProductInfo (){
        return productInfo;
    }

    public List<WebElement> getAddToCartButtons (){
        return addToCartButton;
    }

    public List<WebElement> getRemoveFromCartButtons(){
        return removeFromCartButton;
    }

    public List<WebElement> getProductTitles () {
        return productTitle;
    }

    public WebElement getCartQuantityBadge(){
        return cartQuantityBadge;
    }

    public List<String> getAddedProductsName(){
        return addedProducts;
    }


    public void compareProductsPricesOrder(){

        List<Double> originalPrices = new ArrayList<>();
        getProductsPrices()
                .forEach(priceWithSymbol -> Stream.of(priceWithSymbol)
                .map(price -> price.getText().split("\\$"))
                .flatMap(Stream::of)
                        .filter(priceWithBlanks -> !priceWithBlanks.isEmpty())
                        .forEach(price -> originalPrices.add(Double.parseDouble(price))));

        List<Double> copyPrices = originalPrices.stream().sorted().collect(Collectors.toList());

        Assert.assertEquals(originalPrices,copyPrices);
    }

    public void compareAddedProductsWithCartNumber(){
        Assert.assertEquals(getRemoveFromCartButtons().size(), Integer.parseInt(getCartQuantityBadge().getText()));
    }

    public void comparePageTittle (String expectedTittle) {
        Assert.assertEquals(getPageHeaderTitle().getText(),expectedTittle);
    }

    public void addRandomProductsToCart() {

        addedProducts.clear();

        List<WebElement> addToCartButtons = new ArrayList<>(getAddToCartButtons());
        List<WebElement> productTitles = getProductTitles();
        List<Integer> productIndexes = new ArrayList<>();

        int productQuantity = (int) ((Math.random() * (addToCartButtons.size()+1 - 2)) + 2);
        while ( productQuantity > 0){
            productIndexes.add(addItem(productIndexes, addToCartButtons.size()));
            productQuantity = productQuantity -1;
        }

        productIndexes.forEach((productIndex) -> {
            addToCartButtons.get(productIndex).click();
            addedProducts.add(productTitles.get(productIndex).getText());
        });

    }

    /**
     * @param productsToAdd
     * @param maxRangeSize
     * @return
     */
    public int addItem (List<Integer> productsToAdd, int maxRangeSize) {
        int pickedNumber = (int) (Math.random() * (maxRangeSize));
        return !productsToAdd.contains(pickedNumber) ? pickedNumber : addItem(productsToAdd,maxRangeSize);
    }

    public void AddProductToCartFromName (String productName) {

        addedProducts.clear();

        List<WebElement> addToCartButtons = new ArrayList<>(getAddToCartButtons());
        List<WebElement> productTitles = getProductTitles();

        int index = 0;

        for (int i = 0 ; i < productTitles.size(); i++) {
            if (productName.equalsIgnoreCase(productTitles.get(i).getText())){
                index = i;
            }
        }

        addToCartButtons.get(index).click();
        addedProducts.add(productTitles.get(index).getText());
    }



}
