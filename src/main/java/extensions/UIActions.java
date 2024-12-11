package extensions;

import org.openqa.selenium.JavascriptExecutor;
import utilities.CommonOperations;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UIActions extends CommonOperations {

    public static void setText(WebElement elem, String value) {
        wait.until(ExpectedConditions.elementToBeClickable(elem));
        elem.clear();
        elem.sendKeys(value);
    }

    public static void mouseHoverOnElement(WebElement elem) {
        wait.until(ExpectedConditions.visibilityOf(elem));
        action.moveToElement(elem).build().perform();
    }

}