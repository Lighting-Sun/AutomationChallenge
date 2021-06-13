package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.io.IOException;


public class CheckoutCompletePage extends BasePage{


    public CheckoutCompletePage() throws IOException {

    }


    @FindBy (css = "span.title")
            private WebElement pageTitle;

    @FindBy (css = "h2.complete-header")
            private WebElement purchaseConfirmationMessage;

    public void checkIfConfirmationMessageIsPresent(){
        Assert.assertTrue(purchaseConfirmationMessage.isDisplayed());
    }

    public WebElement getPageTitle() {
        return pageTitle;
    }
}
