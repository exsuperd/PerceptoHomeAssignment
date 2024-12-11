package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class SearchResultsPage {

    @FindBy(how = How.CSS, using = "li.container_2pxt div a.tx-link-a")
    public List<WebElement> searchResultsTextsList;

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'bold')]")
    public List<WebElement> pricesList;

}
