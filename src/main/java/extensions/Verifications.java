package extensions;

import org.codehaus.groovy.transform.SourceURIASTTransformation;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utilities.CommonOperations;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Verifications extends CommonOperations {

    //Lists verifications

    //In case all list indexes have the same text
    public static void repeatableTextInEachListIndex(List<String> items, String repeatableText) {
        for (int i = 0; i < items.size(); i++) {
            Assert.assertTrue(items.get(i).equalsIgnoreCase(repeatableText));
        }
    }

    //In case all list indexes includes a common text
    public static void verifyAllElementsContainsSpecificText(List<WebElement> list, String targetText) {
        for (int i = 0; i < list.size(); i++) {
            Assert.assertTrue(list.get(i).getText().contains(targetText));
        }
    }

    //In case all list indexes includes a common text
    public static void verifyNotAllElementsContainsSpecificText(List<WebElement> list, String targetText) {
        List<Boolean> targetTextStatusList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getText());
            if (list.get(i).getText().contains(targetText)) {
                System.out.println("Index number " + i + " Contains " + targetText);
                targetTextStatusList.add(i, true);
            } else {
                System.out.println("Index number " + i + " Doesn't Contain " + targetText);
                targetTextStatusList.add(i, false);
            }
        }
        assertTrue(targetTextStatusList.contains(false));
    }

    public static void visibilityOfAllElementsOfAList(List<WebElement> list) {
        for (int i = 0; i < list.size(); i++) {
            assertTrue(list.get(i).isDisplayed());
        }
    }

    public static void textIsIncludedInEachListIndex(List<WebElement> items, String[] expectedValues) throws InterruptedException {
        for (int i = 0; i < items.size(); i++) {
            Thread.sleep(250);
            assertTrue((items.get(i).getText()).contains(expectedValues[i]));
        }
    }

    public static void verifyListSize(List<WebElement> list, int expectedSize) {
        {
            Assert.assertTrue((list.size() == expectedSize));
        }
    }

    public static void verifyStringListSize(List<String> list, int expectedSize) {
        {
            Assert.assertTrue((list.size() == expectedSize));
        }
    }

    public static void textInEachStringListIndex(List<String> items, String[] expectedValues) {
        for (int i = 0; i < items.size(); i++) {
            Assert.assertEquals((items.get(i)), expectedValues[i]);
        }
    }


    //Single element verifications

    public static void verifyATextIsIncludedInAString(String str, String expectedValue) {
        Assert.assertTrue(str.contains(expectedValue));
    }

    public static void verifyTargetTextIsIncludedInTargetList(String str, String[] availableValues) {
        List<String> targetList = new ArrayList<>();
        for (int i = 0; i < availableValues.length; i++) {
            targetList.add(i, availableValues[i]);
        }
        assertTrue(targetList.contains(str));
    }

    public static void verifyATextIsNotIncludedInAString(String str, String expectedValue) {
        Assert.assertFalse(str.contains(expectedValue));
    }

    public static void compareIntValues(int actual, int expectedValue) {
        Assert.assertEquals(actual, expectedValue);
    }

    public static void compareStringValues(String actual, String expectedValue) {
        Assert.assertEquals(actual, expectedValue);
    }

    public static void textInEachElementIndex(Elements items, String[] expectedValues) {
        for (int i = 0; i < items.size(); i++) {
            Assert.assertEquals((items.get(i).text()), expectedValues[i]);
        }
    }

    public static void booleanElementVerification(boolean actualBooleanResult, boolean expectedStatus) {
        Assert.assertEquals(actualBooleanResult, expectedStatus);
    }

    public static void getAndVerifyFontSizeInPixelsAndRem(WebElement elem, String expectedFontSizeInPixels, double expectedFontSizeInREM) {
        String actualFontSizeInPixels = elem.getCssValue("font-size");
        System.out.println("Element font size in pixels is: " + actualFontSizeInPixels);
        double elementSize = Double.parseDouble(actualFontSizeInPixels.replace("px", ""));
        double rootSize = Double.parseDouble(actualFontSizeInPixels.replace("px", ""));
        double actualElementREMValue = elementSize / rootSize;
        System.out.println("Element font size in rem is :" + actualElementREMValue);
        assertEquals(expectedFontSizeInPixels, actualFontSizeInPixels);
        assertEquals(expectedFontSizeInREM, actualElementREMValue);
    }
}