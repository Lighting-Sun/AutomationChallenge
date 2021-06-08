package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class BasePage {

    WebDriver driver;
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    enum ValidationType {
        isDisplayed,
        isEnabled,
        isSelected
    }

    public void clickOnWebElement (By elementLocator){
        driver.findElement(elementLocator).click();
    }

    public String getElementText (By elementLocator){
       return driver.findElement(elementLocator).getText();
    }

    public String getElementAttributeValue (By elementLocator, String attributeName){
        return driver.findElement(elementLocator).getAttribute(attributeName);
    }

    public void setField (By elementLocator, String fieldValue){
        driver.findElement(elementLocator).sendKeys(fieldValue);
    }

    public List<WebElement> getWebElementList (By elementsLocator){
        return driver.findElements(elementsLocator);
    }

    public int getWebElementListSize(By elementsLocator){
        return driver.findElements(elementsLocator).size();
    }



    public WebElement getWebElement (By elementLocator){
        return driver.findElement(elementLocator);
    }

    public boolean getBooleanComparison(By elementLocator,ValidationType validationType){
        if (validationType.toString().equalsIgnoreCase("isDisplayed")){
            return driver.findElement(elementLocator).isDisplayed();
        }else if (validationType.toString().equalsIgnoreCase("isEnabled")){
            return driver.findElement(elementLocator).isEnabled();
        }else if (validationType.toString().equalsIgnoreCase("isSelected")){
            return driver.findElement(elementLocator).isSelected();
        }else {
            Assert.fail("An invalid validation type has been introduced");
            return false;
        }
    }



}
