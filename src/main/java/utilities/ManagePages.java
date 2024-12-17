package utilities;

import org.openqa.selenium.support.PageFactory;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.ProductPage;
import pageObjects.SearchResultsPage;

public class ManagePages extends Base {
    public static void initWeb() {
        HomePage = PageFactory.initElements(driver, HomePage.class);
        LoginPage = PageFactory.initElements(driver, LoginPage.class);
        SearchResultsPage = PageFactory.initElements(driver, SearchResultsPage.class);
        ProductPage = PageFactory.initElements(driver, ProductPage.class);
    }
}
