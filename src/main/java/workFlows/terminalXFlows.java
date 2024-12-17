package workFlows;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import extensions.Verifications;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pageObjects.HomePage;
import utilities.CommonOperations;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static extensions.UIActions.*;
import static extensions.Verifications.*;

public class terminalXFlows extends CommonOperations {

    public static List<Map<String, String>> getCredentials() {
        List<Map<String, String>> credentialsList = new ArrayList<>();

        try {
            ObjectMapper mapper = new ObjectMapper();
            String projectRoot = System.getProperty("user.dir");
            JsonNode rootNode = mapper.readTree(new File(projectRoot + "/testData/credentials.json"));
            JsonNode usersNode = rootNode.get("users");

            for (JsonNode user : usersNode) {
                Map<String, String> credentials = new HashMap<>();
                credentials.put("username", user.get("username").asText());
                credentials.put("password", user.get("password").asText());
                credentialsList.add(credentials);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return credentialsList;
    }

    public static void login(String username, String password, int expectedLoggedInComponents, String[] randomUsernames)
            throws InterruptedException {
        HomePage.homePageConnectButton.click();
        wait.until(ExpectedConditions.visibilityOf(LoginPage.emailInputField));
        LoginPage.emailInputField.click();
        setText(LoginPage.emailInputField, username);
        LoginPage.passwordInputField.click();
        setText(LoginPage.passwordInputField, password);
        LoginPage.submitButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(HomePage.homePageSearchButton));
        Thread.sleep(5000);
        visibilityOfAllElementsOfAList(HomePage.logedInTextsList);
        verifyListSize(HomePage.logedInTextsList, expectedLoggedInComponents);
        verifyTargetTextIsIncludedInTargetList(HomePage.logedInTextsList.get(1).getText(), randomUsernames);
    }

    public static void searchAndVerifyResults(String searchInputText, String expectedIncludedText) throws InterruptedException {
        HomePage.homePageSearchButton.click();
        HomePage.homePageSearchInputField.click();
        setText(HomePage.homePageSearchInputField, searchInputText);
        Thread.sleep(2000);
        Verifications.verifyNotAllElementsContainsSpecificText(SearchResultsPage.searchResultsTextsList, expectedIncludedText);
    }

    public static List<String> getAndReturnPricesList() {
        List<String> extractedPricesList = new ArrayList<>();
        for (int i = 0; i < SearchResultsPage.pricesList.size(); i++) {
            extractedPricesList.add(i, SearchResultsPage.pricesList.get(i).getText());
            System.out.println(extractedPricesList.get(i));
        }
        return extractedPricesList;
    }

    public static boolean isSortedAscending(List<String> prices) {
        try {
            // Convert strings to floats
            List<Float> floatPrices = getAndReturnPricesList().stream()
                    .map(price -> Float.parseFloat(price.replace("$", "").replace(",", "")))
                    .toList();

            // Compare each element with the next one
            for (int i = 0; i < floatPrices.size() - 1; i++) {
                if (floatPrices.get(i) > floatPrices.get(i + 1)) {
                    return false;
                }
            }
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Error converting prices: " + e.getMessage());
            return false;
        }
    }

    public static void getAndVerifySelectedProductProperties(int selectedSearchResultIndex, String expectedPrice,
                                                             String expectedPixelsFontSize, double expectedREMFontSIZE) {
        SearchResultsPage.searchResultsTextsList.get(selectedSearchResultIndex).click();
        wait.until(ExpectedConditions.visibilityOf(ProductPage.selectedProductPrice));
        Verifications.compareStringValues(ProductPage.selectedProductPrice.getText(), expectedPrice);
        getAndVerifyFontSizeInPixelsAndRem(ProductPage.selectedProductPrice, expectedPixelsFontSize,expectedREMFontSIZE);
    }

    public static void logOut(String url, String expectedLoginButtonText) {
        driver.navigate().to(url);
        HomePage.logedInTextsList.get(1).click();
        wait.until(ExpectedConditions.urlContains("account"));
        HomePage.logOutButton.click();
        wait.until(ExpectedConditions.visibilityOf(HomePage.homePageConnectButton));
        compareStringValues(HomePage.homePageConnectButton.getText(), expectedLoginButtonText);
    }
}
