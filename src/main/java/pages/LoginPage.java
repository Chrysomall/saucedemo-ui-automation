package pages;

import components.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractComponents {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        //initialization
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Page factory
    @FindBy(id = "user-name")
    WebElement username;

    @FindBy(css = "input[data-test=\"password\"]")
    WebElement password;

    @FindBy(css = "input[class*=\"submit\"]")
    WebElement submitLoginButton;

    @FindBy(css = ".error-message-container")
    WebElement errorMsg;

    @FindBy(css = "#login_credentials")
    WebElement loginCredential;

    @FindBy(className = "login_password")
    WebElement loginPassword;

    @FindBy(css = "h3[data-test=\"error\"]")
    WebElement loginError;

    //Actions
    public InventoryPage login(String user, String pass) {
        username.sendKeys(user);
        password.sendKeys(pass);
        submitLoginButton.click();
        return new InventoryPage(driver);
    }

    public InventoryPage loginStandardUser() {
        login(getStandardUsername(), getStandardPassword());
        return new InventoryPage(driver);
    }

    public String getStandardUsername() {
        String loginCredentials = loginCredential.getText();
        return loginCredentials.split("\\n")[1];
    }

    public String getStandardPassword() {
        String standardPasswordArea = loginPassword.getText();
        return standardPasswordArea.split("\\n")[1];
    }
    public boolean isErrorMessageDisplayed() {
        try {
            return loginError.isDisplayed() && !loginError.getText().trim().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public String getLoginError() {
        return loginError.getText();
    }
}
