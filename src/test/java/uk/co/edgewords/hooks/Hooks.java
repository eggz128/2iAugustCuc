package uk.co.edgewords.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {
    private WebDriver driver;

    private final SharedDictionary dict;

    public Hooks(SharedDictionary dict) {
        this.dict = dict;
    }

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        dict.addDict("mywebdriver", driver);
//        WebDriverManager.firefoxdriver().setup();
//        driver = new FirefoxDriver();
    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}
