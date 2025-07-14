package pages;

import components.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends AbstractComponents {

    public ConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = ".complete-header")
    private WebElement confirmationMessage;

    public boolean isConfirmationVisible() {
        try {
            waitForVisibilityOfElement(confirmationMessage, 10);
            return confirmationMessage.isDisplayed() &&
                    confirmationMessage.getText().trim().equalsIgnoreCase("Thank you for your order!");
        } catch (Exception e) {
            return false;
        }
    }
}

