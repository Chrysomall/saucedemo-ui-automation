package testng.tests.navigation;

import testng.components.BaseTest;
import components.AbstractComponents;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LandingPage;
import pages.LoginPage;

import java.util.List;

public class FooterLinkValidationTest extends BaseTest {

    @Test(description = "Verify all footer links are not broken",
            groups = {"regression", "navigation", "brokenlink"})
    public void shouldValidateAllFooterLinksAreWorking() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        // Go to landing and login
        LandingPage landingPage = new LandingPage(driver);
        LoginPage loginPage = landingPage.goTo();
        loginPage.loginStandardUser();

        // Scroll to footer
        AbstractComponents abstractComponents = new AbstractComponents(driver);
        abstractComponents.scrollBy(0, 500);

        // Collect and test all footer links
        List<WebElement> footerLinks = abstractComponents.getFooterLinks();
        abstractComponents.testAllLinks(footerLinks, softAssert);

        softAssert.assertAll(); // Collect all failures
    }
}