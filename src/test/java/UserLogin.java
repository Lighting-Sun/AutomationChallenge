import PageObjects.CartPage;
import PageObjects.LoginPage;
import PageObjects.ProductsPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.base;

import java.io.IOException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class UserLogin extends base {

    //using the logger feature! :D
    public static Logger logger = LogManager.getLogger(base.class.getName());

    public WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;

    String expectedHeaderTitle = "PRODUCTS";
    String expectedLoginButtonText = "LOGIN";

    @BeforeTest
    public void setUpDriver() throws IOException {
        this.driver = initializeDriver();
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        logger.info("Driver initialized");
    }

    @Test(groups = "validLogin")
    public void userLoginValid () {
        driver.get("https://www.saucedemo.com/");
        logger.info("Navigating to the landing page");
        loginPage.setUsernameField("standard_user");
        loginPage.setPasswordField("secret_sauce");
        loginPage.clickLoginButton();

        Assert.assertEquals(expectedHeaderTitle, productsPage.getPageHeaderTitle());

    }

    @Test
    public void userLoginInvalid () {
        driver.get("https://www.saucedemo.com/");
        logger.info("Navigating to the landing page");
        loginPage.setUsernameField("invalid_user");
        loginPage.setPasswordField("secret_sauce");
        loginPage.clickLoginButton();

        Assert.assertTrue(loginPage.isErrorMessagePresent());
    }

    @Test
    public void userLogout () {
        userLoginValid();
        productsPage.clickBurgerMenuButton();
        productsPage.clickLogoutSidebarButton();

        Assert.assertTrue(loginPage.getLoginButtonText("value").equalsIgnoreCase(expectedLoginButtonText));
    }

    @Test
    public void sortProductsByLowToHighPrice () {

        userLoginValid();
        productsPage.clickProductSortContainer();
        productsPage.clickLowToHighSortOption();
        productsPage.compareProductsPricesOrder();

    }

    @Test
    public void addMultipleProducts () {
        userLoginValid();
        productsPage.addProductsToCart();
        Assert.assertEquals(productsPage.getRemoveFromCartButtons(), Integer.parseInt(productsPage.getCartQuantity()));
        productsPage.clickOnCart();
        Assert.assertEquals(productsPage.getAddedProductsName(),cartPage.getProductNames());


    }



//    @AfterTest
//    public void quitDriver(){
//        driver.quit();
//    }

}
