package testng.components;

import config.ConfigReader;
import factory.DriverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        driver = DriverFactory.initializeDriver();
        driver.get(ConfigReader.get("baseUrl"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (org.openqa.selenium.WebDriverException e) {
                System.out.println("Session already closed or not available: " + e.getMessage());
            }
        }
    }

    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Save screenshot in: /reports/screenshots/
        String screenshotDir = System.getProperty("user.dir") + "/reports/screenshots/";
        String fullPath = screenshotDir + testCaseName + ".png";

        FileUtils.copyFile(src, new File(fullPath));

        // Return relative path from report HTML to image
        return "screenshots/" + testCaseName + ".png";
    }
}
