package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class CartPage extends BasePage{

    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    By productNames = By.cssSelector("div.inventory_item_name");
    By productPrices = By.cssSelector("div.inventory_item_price");

    public List<String> getSortedProductNames() {
        List<String> stringProductNames = new ArrayList<>();
        getWebElementList(productNames).forEach(webElement -> stringProductNames.add(webElement.getText()));
        return stringProductNames.stream().sorted().collect(Collectors.toList());
    }



}
