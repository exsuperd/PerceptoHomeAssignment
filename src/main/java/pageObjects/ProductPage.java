package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProductPage {
    @FindBy(how = How.CSS, using = "div[data-test-id='qa-pdp-price-final']")
    public WebElement selectedProductPrice;
}
