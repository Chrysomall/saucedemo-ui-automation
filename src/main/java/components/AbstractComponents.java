package components;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class AbstractComponents {
    protected WebDriver driver;
    protected WebDriverWait wait;
    SoftAssert softAssert = new SoftAssert();

    public AbstractComponents(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Locators
    @FindBy(className = "footer")
    public WebElement footer;

    @FindBy(css = ".footer a")
    List<WebElement> footerLinks;


    //Actions
    public void waitForVisibilityOfElement(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitElementToBeClickable(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public List<WebElement> getFooterLinks() {
        return footerLinks;
    }

    public void scrollToElement(String element, int timeout) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(" + element + ").scrollTop = " + timeout + ";");
    }

    public void scrollBy(int x, int y) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 500);");
        Thread.sleep(2000);  //
    }

    public void testAllLinks(List<WebElement> links, SoftAssert softAssert) {
        for (WebElement link : links) {
            String url = link.getAttribute("href");

            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setRequestMethod("HEAD");
                connection.connect();

                int responseCode = connection.getResponseCode();
                System.out.println("✅ " + url + " returned " + responseCode);

                softAssert.assertTrue(responseCode < 400, "❌ Broken link: " + url + " returned " + responseCode);
            } catch (Exception e) {
                System.out.println("❌ Exception checking: " + url);
                softAssert.fail("❌ Failed to connect: " + url);
            }
        }
    }

}
