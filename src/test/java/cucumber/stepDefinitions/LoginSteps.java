package cucumber.stepDefinitions;

import config.ConfigReader;
import factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.InventoryPage;
import pages.LoginPage;
import testng.components.BaseTest;

public class LoginSteps extends BaseTest {
    LoginPage loginPage = new LoginPage(driver);

    @Given("I open the SauceDemo login page")
    public void iOpenTheSauceDemoLoginPage() {
        driver = DriverFactory.initializeDriver();
        driver.get(ConfigReader.get("baseUrl"));
        loginPage = new LoginPage(driver);
    }

    @When("I login with username {string} and password {string}")
    public void iLoginWithUsernameAndPassword(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("I should {string} see the inventory page")
    public void iShouldResultSeeTheInventoryPage(String condition) {
        boolean isLoaded = new InventoryPage(driver).isPageLoaded();
        if (condition.equalsIgnoreCase("not")) {
            Assert.assertFalse(isLoaded, "Inventory page should not be visible");
        } else {
            Assert.assertTrue(isLoaded, "Inventory page should be visible");
        }
    }

    @And("I should {string} see the error message {string}")
    public void iShouldErrorVisibilitySeeTheErrorMessage(String errorVisibility, String errorMessage) {
        if (errorVisibility.equalsIgnoreCase("not") || errorMessage.isBlank()) {
            Assert.assertFalse(loginPage.isErrorMessageDisplayed(), "Expected no error message, but it was shown.");
        } else {
            String actualMessage = loginPage.getLoginError().trim().replaceAll("\\s+", " ");
            Assert.assertEquals(actualMessage, errorMessage, "Error message mismatch.");
        }
    }
}

