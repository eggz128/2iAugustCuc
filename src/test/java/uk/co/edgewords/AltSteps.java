package uk.co.edgewords;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import uk.co.edgewords.hooks.SharedDictionary;

import java.time.Duration;

public class AltSteps {

    private final SharedDictionary dict;
    private WebDriver driver;

    public AltSteps(SharedDictionary dict) {
        this.dict = dict;
        this.driver = (WebDriver)dict.readDict("mywebdriver");

    }
    @Given("^I am on the (?i)Google(?-i) Homepage$")
    @Given("I am on the Google search page")
    public void i_am_on_the_google_homepage() {


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get("https://www.google.co.uk/");
        driver.findElement(By.cssSelector("#L2AGLb > div")).click();
        String title = driver.getTitle();
        dict.addDict("thetitle", title);
    }

}
