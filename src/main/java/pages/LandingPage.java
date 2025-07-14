package pages;

import components.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LandingPage extends AbstractComponents {
    WebDriver driver;
    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public LoginPage goTo()
    {
        driver.get("https://www.saucedemo.com/");
        String title = driver.getTitle();
        Assert.assertEquals(title, "Swag Labs", "Wrong title.Expected Swag Labs but was: " + title);
        System.out.println("Current URL: " + driver.getCurrentUrl());
        return new LoginPage(driver);
    }
}
