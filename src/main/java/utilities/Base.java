package utilities;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.jsoup.nodes.Document;
import pageObjects.HomePage;

import pageObjects.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.SearchResultsPage;

public abstract class Base {

    public static WebDriver driver;
    public static Actions action;
    public static WebDriverWait wait;
    public static LoginPage LoginPage;
    public static HomePage HomePage;
    public static SearchResultsPage SearchResultsPage;


    public static RequestSpecification httpRequest;
    public static Response response;
    public static JSONObject RequestParameters = new JSONObject();
    public static JsonPath jp;
    public static Document doc;


}
