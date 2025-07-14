package testng.tests.checkout;

import testng.components.BaseTest;
import testng.components.RetryMechanism;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PlaceOrderTest extends BaseTest {

    @Test(description = "Place an order with 1 product and verify confirmation",
            groups = {"smoke", "regression", "checkout"})
    public void shouldPlaceOrderWithOneProductSuccessfully() {
        // Login
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = loginPage.loginStandardUser();

        // Add item to cart
        inventoryPage.addItemToCart("Sauce Labs Backpack");

        // Go to cart
        CartPage cartPage = inventoryPage.proceedToCheckout();
        Assert.assertTrue(cartPage.isItemInCart("Sauce Labs Backpack"), "❌ Item not found in cart.");

        // Checkout step one
        CheckoutStepOnePage checkoutStepOnePage = cartPage.clickOnCheckoutButton();
        CheckoutStepTwoPage checkoutStepTwoPage = checkoutStepOnePage.fillInForm("TestName", "TestLastName", "1234");

        // Finalize order
        ConfirmationPage confirmationPage = checkoutStepTwoPage.finalizeCheckout();

        // Verify confirmation
        Assert.assertTrue(confirmationPage.isConfirmationVisible(), "❌ Confirmation message not displayed.");
    }

    @Test(description = "Place an order with multiple products and verify confirmation",
            groups = {"regression", "checkout"},
            retryAnalyzer = RetryMechanism.class)
    public void shouldPlaceOrderWithMultipleProductsSuccessfully() {
        // Prepare product list
        Set<String> chosenShoppingItems = new HashSet<>(Arrays.asList(
                "Sauce Labs Backpack",
                "Sauce Labs Bolt T-Shirt"
        ));

        // Login
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = loginPage.loginStandardUser();

        // Add selected items to cart
        inventoryPage.addItemsToCart(chosenShoppingItems);

        // Go to cart and verify items
        CartPage cartPage = inventoryPage.proceedToCheckout();
        for (String item : chosenShoppingItems) {
            Assert.assertTrue(cartPage.isItemInCart(item), "❌ " + item + " was not found in cart.");
        }

        // Proceed to checkout step one
        CheckoutStepOnePage checkoutStepOnePage = cartPage.clickOnCheckoutButton();

        // Fill form and continue
        CheckoutStepTwoPage checkoutStepTwoPage = checkoutStepOnePage.fillInForm("Test", "User", "12345");

        // Finalize checkout
        ConfirmationPage confirmationPage = checkoutStepTwoPage.finalizeCheckout();

        // Assert confirmation
        Assert.assertTrue(confirmationPage.isConfirmationVisible(), "❌ Order confirmation not displayed.");
    }
}




