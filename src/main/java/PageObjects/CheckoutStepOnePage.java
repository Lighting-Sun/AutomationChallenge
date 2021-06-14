package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CheckoutStepOnePage extends BasePage{


    public CheckoutStepOnePage() throws IOException {

    }

    @FindBy (id = "first-name")
            private WebElement firstNameField;

    @FindBy (id = "last-name")
            private WebElement lastNameField;

    @FindBy (id = "postal-code")
            private WebElement postalCodeField;

    @FindBy (id = "continue")
            private WebElement continueButton;

    @FindBy (css = "span.title")
            private WebElement pageTitle;


    /**
     * This method fills the form present in the CheckoutStepOnePage
     * @param firstName user's first name
     * @param lastName user's last name
     * @param postalCode user's postal or Zip code
     */
    public void fillCheckoutForm (String firstName, String lastName, String postalCode){
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        postalCodeField.sendKeys(postalCode);
    }

    public WebElement getContinueButton (){
        return continueButton;
    }

    public WebElement getPageTitle (){
        return pageTitle;
    }


}
