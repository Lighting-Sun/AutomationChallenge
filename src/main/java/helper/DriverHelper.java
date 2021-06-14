package helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BrowserFactory;

import java.io.IOException;

public class DriverHelper {

    protected WebDriver driver= BrowserFactory.getDriver();
    private WebDriverWait webDriverWait;
    private static final int WAIT_TIME=60;

    /**
     * Method used to help in the initialization of the POM for all the pages
     */
    public DriverHelper() throws IOException {
        PageFactory.initElements(driver, this);
        webDriverWait = new WebDriverWait(driver, WAIT_TIME);
    }

    /**
     * @param element the WebElement that the driver will wait for to appear
     */
    public void waitDriverForVisibility(WebElement element){
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }
}
