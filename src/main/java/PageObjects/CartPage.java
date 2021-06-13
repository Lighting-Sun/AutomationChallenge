package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CartPage extends BasePage{


    public CartPage() throws IOException {

    }

    @FindBy (css = "div.inventory_item_name")
            private List<WebElement> productNames;

    @FindBy (id = "checkout")
            private WebElement checkoutButton;


    public List<String> getProductNames() {
        List<String> stringProductNames = new ArrayList<>();
        productNames.forEach(webElement -> stringProductNames.add(webElement.getText()));
        return stringProductNames;
    }

    public WebElement getCheckoutButton (){
        return checkoutButton;
    }


    public void compareProducts(List<String> addedProductsName) {
        Assert.assertEquals(getProductNames(),addedProductsName);
    }
}
