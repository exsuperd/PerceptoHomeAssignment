package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {

    @FindBy(how = How.ID, using = "qa-login-email-input")
    public WebElement emailInputField;
    @FindBy(how = How.ID, using = "qa-login-password-input")
    public WebElement passwordInputField;
    @FindBy(how = How.XPATH, using = "(//button[contains(@class, 'submit-btn')])[1]")
    public WebElement submitButton;
}
