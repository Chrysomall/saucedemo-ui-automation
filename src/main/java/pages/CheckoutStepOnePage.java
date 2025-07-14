package pages;

import components.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutStepOnePage extends AbstractComponents {
    private WebDriver driver;

    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Locators
    @FindBy(id = "first-name")
    WebElement firstName;

    @FindBy(id = "last-name")
    WebElement lastName;

    @FindBy(id = "postal-code")
    WebElement postalCode;

    @FindBy(id = "continue")
    WebElement continueButton;

    //Actions
    public CheckoutStepTwoPage fillInForm(String firstName, String lastName, String postalCode) {
        this.firstName.sendKeys(firstName);
        this.lastName.sendKeys(lastName);
        this.postalCode.sendKeys(postalCode);
        continueButton.click();
        return new CheckoutStepTwoPage(driver);
    }
}
