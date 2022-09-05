package uk.co.edgewords.apitests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;

public class firstapitests {
    @Test
    void firstApiTest(){
        given().baseUri("http://localhost/").port(2002)
        .when().get("api/products")
        .then().statusCode(200);
    }

    @Test
    void api(){
        var req = RestAssured.given().baseUri("http://localhost:2002");
        var res = req.when().get("/api/products");
        var val = res.then();
        val.assertThat().statusCode(200).log().body();

    }
}
