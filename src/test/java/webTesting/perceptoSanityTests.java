package webTesting;


import extensions.Verifications;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.CommonOperations;
import utilities.ManagePages;
import workFlows.terminalXFlows;

import java.util.List;
import java.util.Map;

import static utilities.UsefulMethods.getData;


public class perceptoSanityTests extends CommonOperations {

    final String url = "https://www.terminalx.com/women";
    final String browser = "chrome";
    final static String searchedItemText = "hello";
    final static String expectedCommonText = "Hello Kitty";
    final static String expectedLoggedInUser ="per"; //Only added one user as the one of the credentials stopped working
    final static boolean arePricesSorted = false;

    @BeforeClass
    public void startWebSession() {
        initBrowser(browser, url, Integer.parseInt(getData("generalTimeout")),
                Integer.parseInt(getData("pageLoadTimeout")));
        ManagePages.initWeb();
    }

    @Test(description = "test_01:loginUsingRandomUser")
    public static void test_01_loginUsingRandomUser() throws InterruptedException {
        // int selectedUserIndex = getRandomNumber(0,1);
        int selectedUserIndex = 1;
        //For some reason the first credentials stopped working (getting an error) so I entered the second user hardcoded
        System.out.println("Selected user index is " + selectedUserIndex);
        List<Map<String, String>> allCredentials = terminalXFlows.getCredentials();
        Map<String, String> selectedUser = allCredentials.get(selectedUserIndex);
        String selectedUsername = selectedUser.get("username");
        String selectedUserPassword = selectedUser.get("password");
        terminalXFlows.login(selectedUsername,selectedUserPassword,2,expectedLoggedInUser);
    }

    @Test(description = "test_02:searchAndVerifyResults")
    public static void test_02_searchItemAndVerifyResults() throws InterruptedException {
       terminalXFlows.searchAndVerifyResults(searchedItemText, expectedCommonText);
    }

    @Test(description = "test_03:checkIfPricesAreSortedOrNot")
    public static void test_03_verifyPricesNotSorted() throws InterruptedException {
        List<String> extracted = terminalXFlows.getAndReturnPricesList();
        boolean sorted = terminalXFlows.isSortedAscending(extracted);
        System.out.println(sorted);
        Verifications.booleanElementVerification(sorted,arePricesSorted);
    }

    @AfterClass
    public void closeSession() {
        driver.quit();
    }

}
