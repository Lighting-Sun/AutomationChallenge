package PageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.io.IOException;


public class LoginPage extends BasePage {

    @FindBy (id = "user-name")
            private WebElement usernameField;

    @FindBy (id = "password")
            private WebElement passwordField;

    @FindBy (id = "login-button")
            private WebElement loginButton;

    @FindBy (xpath = "//h3[@data-test='error']")
            private WebElement loginErrorMessage;

    public LoginPage() throws IOException {
    }


    /**
     * This method fills the login form
     * @param username user's username
     * @param password user's password
     */
    public void fillLogInForm(String username, String password){
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
    }


    public WebElement getLoginButton () {
        return loginButton;
    }


    /**
     * This method is used to return the text inside the login button
     * @param attributeName the attribute's name for the loginButton WebElement
     * @return a String with the WebElement's attribute value
     */
    public String getLoginButtonText (String attributeName) {
       return loginButton.getAttribute(attributeName);
    }

    public void ValidateErrorMessagePresent (){
        Assert.assertTrue(loginErrorMessage.isDisplayed());
    }


}
