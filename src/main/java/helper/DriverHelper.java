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

    public DriverHelper() throws IOException {
        PageFactory.initElements(driver, this);
        webDriverWait = new WebDriverWait(driver, WAIT_TIME);
    }

    /**
     * @param element the webElement that will wait for appear in given range of time
     */
    public void waitDriverForVisibility(WebElement element){
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }
}
