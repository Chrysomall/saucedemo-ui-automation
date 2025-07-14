//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WindowType;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.Assert;
//
//import java.util.Set;
//
//public class OpenMultipleTabs {
//
//    public static void main(String[] args) {
//        WebDriver driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.get("https://www.saucedemo.com/");
//
//        //Login
//        driver.findElement(By.id("user-name")).sendKeys(getStandardUsername(driver));
//        driver.findElement(By.cssSelector("input[data-test=\"password\"]")).sendKeys(getStandardPassword(driver));
//        driver.findElement(By.cssSelector("input[class*=\"submit\"]")).click();
//
//        //Eshop
//        String appLogo = driver.findElement(By.className("app_logo")).getText();
//        Assert.assertEquals(appLogo, "Swag Labs", "App Logo is not correct");
//
//
//        driver.switchTo().newWindow(WindowType.TAB);
//
//        Set<String> windowHandles = driver.getWindowHandles();
//        windowHandles.iterator().forEachRemaining(windowHandle -> {
//            driver.switchTo().window(windowHandle);
//        });
//
//    }
//
//    public static String getStandardUsername(WebDriver driver) {
//        String loginCredentials = driver.findElement(By.cssSelector("#login_credentials")).getText();
//        return loginCredentials.split("\\n")[1];
//    }
//
//    public static String getStandardPassword(WebDriver driver) {
//        String standardPasswordArea = driver.findElement(By.className("login_password")).getText();
//        return standardPasswordArea.split("\\n")[1];
//    }
//}
