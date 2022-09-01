package uk.co.edgewords;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.hamcrest.MatcherAssert;
import uk.co.edgewords.hooks.Hooks;
import uk.co.edgewords.hooks.SharedDictionary;

import static org.hamcrest.CoreMatchers.*;
//import static uk.co.edgewords.hooks.Hooks.driver;

public class GoogleSteps {

    private final SharedDictionary dict;
    private WebDriver driver;

    public GoogleSteps(SharedDictionary dict) {
        this.dict = dict;
        this.driver = (WebDriver)dict.readDict("mywebdriver");

    }



    @When("I search for Edgewords")
    public void i_search_for_edgewords() {
       iSearchFor("Edgewords");
    }

    @Then("Edgewords is the top result")
    public void edgewords_is_the_top_result() {

        String firstResult = driver.findElement(By.cssSelector("#rso > div:first-child")).getText();
        MatcherAssert.assertThat(firstResult, containsString("Edgewords"));
    }


    @When("I search for {string}")
    //@When("^I search for \"([^\"]*)\"$")
    public void iSearchFor(String searchTerm) {
        String thetitle = (String)dict.readDict("thetitle");
        System.out.println(thetitle);
        driver.findElement(By.name("q")).sendKeys(searchTerm + Keys.ENTER);

    }

    //A comment here
    @Then("{string} is the top result")
    public void edgewordsIsTheTopResult(String result) {
        String firstResult = driver.findElement(By.cssSelector("#rso > div:first-child")).getText();
        MatcherAssert.assertThat(firstResult, containsString(result));
    }

    @Then("I see in the results")
    public void i_see_in_the_results(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.

        String searchResultText = driver.findElement(By.id("rso")).getText();

        List<Map<String, String>> table = dataTable.asMaps(String.class, String.class);

        for (var row: table) {
            MatcherAssert.assertThat(searchResultText, containsString(row.get("url")));
            MatcherAssert.assertThat(searchResultText, containsString(row.get("title")));
        }
    }
}
