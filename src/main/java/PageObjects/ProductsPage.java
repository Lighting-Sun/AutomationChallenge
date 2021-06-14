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


    /**
     * This method compares a list of prices with it's sorted duplicate
     * First, it gets the prices, then removes the "$" symbol, after that a new list is filled with this values
     * and then, this list is copied, sorted and compared with the filled list
     */
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


    /**
     * Compares the number of already selected products or Remove buttons with the number present in the page cart
     */
    public void compareAddedProductsWithCartNumber(){
        Assert.assertEquals(getRemoveFromCartButtons().size(), Integer.parseInt(getCartQuantityBadge().getText()));
    }

    /**
     * @param expectedTittle String that represents the expected ProductsPage page title
     */
    public void comparePageTittle (String expectedTittle) {
        Assert.assertEquals(getPageHeaderTitle().getText(),expectedTittle);
    }


    /**
     * Adds random products to the cart by first getting the products quantity, then picking number between 0 and the
     * products found quantity, using this number a productIndexes list will be filled by a unique product index
     * picked only ONCE!, and finally, this list will be used to select the products in the web page
     */
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
     * This method picks a unique index from an Integer list
     * @param productsToAdd an Integer list of indexes of elements from which the picker will choose from
     * @param maxRangeSize represents the max possible number that the picker will try to pick from productsToAdd
     * @return a unique index that hasn't been selected from the productsToAdd list
     */
    public int addItem (List<Integer> productsToAdd, int maxRangeSize) {
        int pickedNumber = (int) (Math.random() * (maxRangeSize));
        return !productsToAdd.contains(pickedNumber) ? pickedNumber : addItem(productsToAdd,maxRangeSize);
    }

    /**
     * This method selects only ONE product based on it's name in the web page
     * First it gets the products quantity, then it compares every product name with the productName parameter that has
     * been provided, and after finding a match, it will select that corresponding element
     * @param productName a String that represents the product name that will be selected from the ProductsPage
     */
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
