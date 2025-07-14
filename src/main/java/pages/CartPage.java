package pages;

import components.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponents {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Locators
    @FindBy(className ="checkout_button" )
    WebElement checkoutButton;

    @FindBy(css = ".cart_item .inventory_item_name")
    private List<WebElement> cartItems;

    //Actions
    public CheckoutStepOnePage clickOnCheckoutButton() {
        checkoutButton.click();
        return new CheckoutStepOnePage(driver);
    }

    public boolean isItemInCart(String productName) {
        return cartItems.stream().anyMatch(item -> item.getText().equalsIgnoreCase(productName));
    }

}
