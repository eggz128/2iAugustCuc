package uk.co.edgewords.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {
    private WebDriver driver;

    private final SharedDictionary dict;

    public Hooks(SharedDictionary dict) {
        this.dict = dict;
    }

    @Before("@GUI")
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        dict.addDict("mywebdriver", driver);
//        WebDriverManager.firefoxdriver().setup();
//        driver = new FirefoxDriver();
    }

    @After("@GUI")
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

    @Before("@API")
    public void APISetup(){
        var req = RestAssured.given().baseUri("http://localhost:2002");
        dict.addDict("apirequest", req);
    }
}
