package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import config.ConfigReader;

import java.time.Duration;
import java.util.HashMap;

public class DriverFactory {

    public static WebDriver initializeDriver() {
        String browser = System.getProperty("browser") != null ? System.getProperty("browser") : ConfigReader.get("browser");
        boolean isHeadless = Boolean.parseBoolean(ConfigReader.get("headless"));
        WebDriver driver;

        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-infobars");

                if (isHeadless) {
                    chromeOptions.addArguments("--headless=new");
                    chromeOptions.addArguments("--window-size=1920,1080");
                }

                chromeOptions.setExperimentalOption("prefs", new HashMap<String, Object>() {{
                    put("credentials_enable_service", false);
                    put("profile.password_manager_enabled", false);
                }});
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (isHeadless) {
                    firefoxOptions.addArguments("-headless");
                }
                driver = new FirefoxDriver(firefoxOptions);
                break;

            default:
                throw new RuntimeException(" Unsupported browser: " + browser);
        }

        driver.manage().window().maximize();
        long timeout = Long.parseLong(ConfigReader.get("timeout"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
        driver.manage().deleteAllCookies();
        return driver;
    }
}
