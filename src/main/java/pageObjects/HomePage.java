package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class HomePage {

    @FindBy(how = How.CSS, using = "button[data-test-id='qa-header-login-button']")
    public WebElement homePageConnectButton;
   @FindBy(how = How.CSS, using = "button[data-test-id='qa-header-search-button']")
    public WebElement homePageSearchButton;
    @FindBy(how = How.CSS, using = "input[data-test-id='qa-search-box-input']")
    public WebElement homePageSearchInputField;
    @FindBy(how = How.XPATH, using = "//button[contains(@class,'profile-button')]/span")
    public List<WebElement> logedInTextsList;
    @FindBy(how = How.XPATH, using = " //button[text() = 'Log Out']")
    public WebElement logOutButton;
}
