import PageObjects.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.BrowserFactory;

import java.io.IOException;


public class UserLogin extends BasePage {



    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    CheckoutStepOnePage checkoutStepOnePage;
    CheckoutStepTwoPage checkoutStepTwoPage;
    CheckoutCompletePage checkoutCompletePage;

    String expectedHeaderTitle = "PRODUCTS";
    String expectedLoginButtonText = "LOGIN";
    String expectedCheckoutStepOneTitle = "CHECKOUT: YOUR INFORMATION";
    String expectedCheckoutStepTwoTitle = "CHECKOUT: OVERVIEW";
    String expectedCheckoutCompleteTitle = "CHECKOUT: COMPLETE!";

    public UserLogin() throws IOException {
    }

    @BeforeMethod
    public void setUpDriver() throws IOException {
        loginPage = new LoginPage();
        productsPage = new ProductsPage();
        cartPage = new CartPage();
        checkoutStepOnePage = new CheckoutStepOnePage();
        checkoutStepTwoPage = new CheckoutStepTwoPage();
        checkoutCompletePage = new CheckoutCompletePage();

        logger.info("Driver initialized");
    }

    @Test
    public void userLoginValid() {
        logger.info("Navigating to the landing page");
        loginPage.openWebSite();
        loginPage.waitDriverForVisibility(loginPage.getLoginButton());
        loginPage.fillLogInForm("standard_user", "secret_sauce");
        loginPage.getLoginButton().click();
        productsPage.waitDriverForVisibility(productsPage.getPageHeaderTitle());
        productsPage.comparePageTittle(expectedHeaderTitle);

    }

    @Test
    public void userLoginInvalid() {
        loginPage.openWebSite();
        loginPage.fillLogInForm("invalid_user", "secret_sauce");
        loginPage.getLoginButton().click();
        loginPage.ValidateErrorMessagePresent();
    }

    @Test
    public void userLogout() {
        userLoginValid();
        productsPage.clickBurgerMenuButton();
        productsPage.waitDriverForVisibility(productsPage.getLogoutSidebarButton());
        productsPage.getLogoutSidebarButton().click();
        Assert.assertTrue(loginPage.getLoginButtonText("value").equalsIgnoreCase(expectedLoginButtonText));
    }

    @Test
    public void sortProductsByLowToHighPrice() {

        userLoginValid();
        productsPage.getProductSortContainer().click();
        productsPage.getLowToHighSortOption().click();
        productsPage.compareProductsPricesOrder();

    }

    @Test
    public void addMultipleProducts() {
        userLoginValid();
        productsPage.addRandomProductsToCart();
        productsPage.compareAddedProductsWithCartNumber();
        productsPage.getCartQuantityBadge().click();
        cartPage.compareProducts(productsPage.getAddedProductsName());

    }

    @Test
    public void addProductByName() {
        userLoginValid();
        productsPage.AddProductToCartFromName("Sauce Labs Onesie");
        productsPage.compareAddedProductsWithCartNumber();
        productsPage.getCartQuantityBadge().click();
        cartPage.compareProducts(productsPage.getAddedProductsName());
        //TODO add a dataProvider!!!!
    }

    @Test
    public void completeAPurchase(){
        addMultipleProducts();
        cartPage.waitDriverForVisibility(cartPage.getCheckoutButton());
        cartPage.getCheckoutButton().click();
        checkoutStepOnePage.waitDriverForVisibility(checkoutStepOnePage.getContinueButton());
        checkoutStepOnePage.checkPageTitle(expectedCheckoutStepOneTitle);
        checkoutStepOnePage.fillCheckoutForm("name","lastName","code");
        checkoutStepOnePage.getContinueButton().click();
        checkoutStepTwoPage.checkPageTitle(expectedCheckoutStepTwoTitle);
        checkoutStepTwoPage.waitDriverForVisibility(checkoutStepTwoPage.getFinishButton());
        checkoutStepTwoPage.getFinishButton().click();
        checkoutCompletePage.waitDriverForVisibility(checkoutCompletePage.getPageTitle());
        checkoutCompletePage.checkPageTitle(expectedCheckoutCompleteTitle);
        checkoutCompletePage.checkIfConfirmationMessageIsPresent();

    }

    @AfterMethod
    public void quitDriver(){
        BrowserFactory.quitDriver();
    }

}
