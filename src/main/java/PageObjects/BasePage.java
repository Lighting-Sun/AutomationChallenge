package PageObjects;

import helper.DriverHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;


public class BasePage extends DriverHelper {

    //using the logger feature! :D
    public static Logger logger = LogManager.getLogger(BasePage.class.getName());

    public BasePage() throws IOException {
    }

    public void openWebSite(){
        driver.get("https://www.saucedemo.com/");
    }





}
