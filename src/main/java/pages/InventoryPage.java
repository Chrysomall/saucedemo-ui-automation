package pages;

import components.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Set;

public class InventoryPage extends AbstractComponents {
    private WebDriver driver;

    public InventoryPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Locators
    @FindBy(className = "inventory_item")
    List<WebElement> inventoryItems;

    @FindBy(className = "inventory_item_name")
    WebElement inventoryItemName;

    @FindBy(css = "button.btn_inventory")
    WebElement btnInventory;

    @FindBy(className = "shopping_cart_link")
    WebElement shoppingCartLink;

    @FindBy(className = "inventory_list")
    WebElement inventoryList;

    By buttonInventory = By.cssSelector("button.btn_inventory");
    private By inventoryItemsName = By.className("inventory_item_name");




    //Actions

    public boolean isPageLoaded() {
        try {
            waitForVisibilityOfElement(inventoryList, 10);
            return inventoryList.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


    public void addItemToCart(String itemName) {
        WebElement selectedItem = inventoryItems.stream().filter(shoppingItem -> inventoryItemName
                        .getText().
                        equalsIgnoreCase(itemName)).
                findFirst().
                orElse(null);
        selectedItem.findElement(buttonInventory).click();
    }

    public void addItemsToCart(Set<String> itemsToBeAdded) {
        for (WebElement item : inventoryItems) {
            // Get the item name from the current product block
            String itemTitle = item.findElement(inventoryItemsName).getText().trim();

            if (itemsToBeAdded.contains(itemTitle)) {
                item.findElement(buttonInventory).click();
                System.out.println("✅ Added to cart: " + itemTitle);

                itemsToBeAdded.remove(itemTitle);

                if (itemsToBeAdded.isEmpty()) {
                    System.out.println("✅ All selected items added.");
                    break;
                }
            }
        }
    }

    public CartPage proceedToCheckout() {
        waitForVisibilityOfElement(shoppingCartLink, 10);
        shoppingCartLink.click();
        return new CartPage(driver);
    }

}
