package PageObjects;

import helper.DriverHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;


public class BasePage extends DriverHelper {

    //using the logger feature! :D
    public static Logger logger = LogManager.getLogger(BasePage.class.getName());

    public BasePage() throws IOException {
    }

    /**
     * This method compares the title that appears inside the page with another that is expected
     * @param actualTitle WebElement that contains the page title found in the web page
     * @param expectedTitle the expected text that the title should have
     */
    public void checkPageTitle(WebElement actualTitle, String expectedTitle){
        Assert.assertEquals(actualTitle.getText(),expectedTitle);
    }


    /**
     * this method navigates to the page https://www.saucedemo.com/
     */
    public void openWebSite(){
        logger.info("Navigating to landing page");
        driver.get("https://www.saucedemo.com/");
    }





}
