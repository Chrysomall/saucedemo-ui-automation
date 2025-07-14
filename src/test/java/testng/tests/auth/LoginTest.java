package testng.tests.auth;

import testng.components.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import testng.testdata.LoginDataProvider;

import java.util.HashMap;

public class LoginTest extends BaseTest {

    @Test(description = "Verify login for all types of users from SauceDemo.",
            groups = {"auth", "smoke", "regression"},
            dataProvider = "loginUsers", dataProviderClass = LoginDataProvider.class)
    public void loginWithVariousUsers(HashMap<String, String> getLoginData) {
        String username = getLoginData.get("username");
        String password = getLoginData.get("password");
        String expectedError = getLoginData.getOrDefault("expectedError", "");
        boolean expectedSuccess = Boolean.parseBoolean(getLoginData.get("expectedSuccess"));
        LoginPage loginPage = new LoginPage(driver);
        if (expectedSuccess) {
            InventoryPage inventoryPage = loginPage.login(username, password);
            Assert.assertTrue(inventoryPage.isPageLoaded(), username + " failed to load inventory page.");
        } else {
            loginPage.login(username, password);
            String actualError = loginPage.getLoginError().trim().replaceAll("\\s+", " ");
            Assert.assertEquals(actualError, expectedError, username + " error message mismatch.");
        }
    }
}
