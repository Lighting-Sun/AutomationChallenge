package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class LoginPage extends BasePage{

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    By usernameField = By.id("user-name");
    By passwordField = By.id("password");
    By loginButton = By.id("login-button");
    By loginErrorMessage = By.xpath("//h3[@data-test='error']");

    public void setUsernameField (String username){
        setField(usernameField,username);
    }

    public void setPasswordField (String password){
        setField(passwordField,password);
    }

    public void clickLoginButton(){
        clickOnWebElement(loginButton);
    }

    public String getLoginButtonText (String attributeName) {
       return getElementAttributeValue(loginButton, attributeName);
    }

    public boolean isErrorMessagePresent (){
        return getBooleanComparison(loginErrorMessage, ValidationType.isDisplayed);
    }


}
