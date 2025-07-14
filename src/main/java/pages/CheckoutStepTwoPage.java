package pages;

import components.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutStepTwoPage extends AbstractComponents {
    private WebDriver driver;

    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Locators
    @FindBy(id = "finish")
    WebElement finish;

    //Actions
    public ConfirmationPage finalizeCheckout() {
        finish.click();
        return new ConfirmationPage(driver);
    }
}
