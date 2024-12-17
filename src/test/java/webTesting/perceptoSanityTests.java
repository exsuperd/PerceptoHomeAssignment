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
import static utilities.UsefulMethods.getRandomNumber;
import static workFlows.terminalXFlows.getAndVerifySelectedProductProperties;
import static workFlows.terminalXFlows.logOut;


public class perceptoSanityTests extends CommonOperations {

    final String url = "https://www.terminalx.com/women";
    final String browser = "chrome";
    final static String searchedItemText = "hello";
    final static String expectedCommonText = "Hello Kitty";
    final static String[] expectedAvailableUserNames = {"per", "checker"};
    final static boolean arePricesSorted = false;
    final static String expectedPrice = "338.00 ₪";
    final static String expectedPixelsFontSize = "21.6px";
    final static double expectedREMFontSize = 1.0;

    @BeforeClass
    public void startWebSession() {
        initBrowser(browser, url, Integer.parseInt(getData("generalTimeout")),
                Integer.parseInt(getData("pageLoadTimeout")));
        ManagePages.initWeb();
    }

    @Test(description = "test_01:loginUsingRandomUser")
    public static void test_01_loginUsingRandomUser() throws InterruptedException {
        int selectedUserIndex = getRandomNumber(0,1);
        System.out.println("Selected user index is " + selectedUserIndex);
        List<Map<String, String>> allCredentials = terminalXFlows.getCredentials();
        Map<String, String> selectedUser = allCredentials.get(selectedUserIndex);
        String selectedUsername = selectedUser.get("username");
        String selectedUserPassword = selectedUser.get("password");
        terminalXFlows.login(selectedUsername ,selectedUserPassword,2,expectedAvailableUserNames);
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

    @Test(description = "test_04:checkThirdProduct")
    public static void test_04_verifyThirdProductProperties()  {
     getAndVerifySelectedProductProperties(2, expectedPrice,expectedPixelsFontSize, expectedREMFontSize);
    }

    @AfterClass
    public void closeSession() {
        //logOut();
        driver.quit();
    }
}
