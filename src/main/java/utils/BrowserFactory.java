package utils;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BrowserFactory {


    private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();



    public static void initializeDriver() throws IOException {
        WebDriver driver;

        Properties properties = new Properties();
        //this class is used to set browsers

        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\data.properties");
        //location of the properties file

        properties.load(fileInputStream);
        String browserName = properties.getProperty("browser");
        switch (browserName){
            case "chrome":
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\resources\\geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            case "edge":
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\resources\\msedgedriver.exe");
                driver = new EdgeDriver();
                break;
            default:
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe");
                driver = new ChromeDriver();
                break;
        }

        threadDriver.set(driver);
    }

    public static WebDriver getDriver() throws IOException {
        if (threadDriver.get()==null){
            initializeDriver();
        }
        return (WebDriver) threadDriver.get();
    }

    public static void quitDriver(){
        threadDriver.get().manage().deleteAllCookies();
        threadDriver.get().quit();
        threadDriver.remove();
    }



    //method to take a screenshot from the driver!!
    public String getScreenshotPath(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        //getting a file!
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "\\reports\\" + testCaseName +".png";
        FileUtils.copyFile(source, new File(destination));
        return destination;
    }


}
