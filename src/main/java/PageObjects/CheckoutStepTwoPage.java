package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.io.IOException;


public class CheckoutStepTwoPage extends BasePage{


    public CheckoutStepTwoPage() throws IOException {

    }


    @FindBy (id = "finish")
            private WebElement finishButton;

    @FindBy (css = "span.title")
            private WebElement pageTitle;


    public WebElement getFinishButton (){
        return finishButton;
    }

    public void checkPageTitle(String expectedTitle){
        Assert.assertEquals(pageTitle.getText(),expectedTitle);
    }

}
