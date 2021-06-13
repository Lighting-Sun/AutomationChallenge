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

    public void checkPageTitle(WebElement actualTitle, String expectedTitle){
        Assert.assertEquals(actualTitle.getText(),expectedTitle);
    }

    public void openWebSite(){
        logger.info("Navigating to landing page");
        driver.get("https://www.saucedemo.com/");
    }





}
