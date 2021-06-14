package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends BrowserFactory implements ITestListener  {

    WebDriver driver = null;
    ExtentReports extent = ExtentReporterNG.getExtentReports();
    ExtentTest test;

    //making the reports thread safe
    ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<ExtentTest>();

    @Override
    public void onTestStart(ITestResult result) {
        // creating the report for the test that will be executed
        test = extent.createTest(result.getMethod().getMethodName());
        extentTestThreadLocal.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTestThreadLocal.get().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        // getting test case name
       String testCaseName = result.getMethod().getMethodName();
        extentTestThreadLocal.get().fail(result.getThrowable());
        try {
            // getting the local WebDriver thread
            driver = BrowserFactory.getDriver();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            // adding the screenshot to the report
            extentTestThreadLocal.get().addScreenCaptureFromPath(getScreenshotPath(testCaseName, driver),result.getMethod().getMethodName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
