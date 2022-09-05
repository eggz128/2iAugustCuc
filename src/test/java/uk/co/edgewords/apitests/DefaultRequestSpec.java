package uk.co.edgewords.apitests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.MatcherAssert;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.lessThan;

public class DefaultRequestSpec {

    @BeforeAll
    public static void setupDefaultRequest(){
        RequestSpecification spec = given();
        spec.baseUri("http://localhost");
        spec.port(2002);
        spec.contentType(ContentType.JSON);
        requestSpecification = spec; //requestSpecification is defined static in io.restassured.RestAssured
    }

    @Test
    void testGetWithSpec(){
        RequestSpecification httpRequest = given().contentType("application/json");

        Response response = httpRequest.get("/api/products/2");

        String body = response.getBody().asString();
        MatcherAssert.assertThat(body, containsString("iPhone X"));

        int statusCode = response.statusCode();
        MatcherAssert.assertThat(statusCode, is(200));

        System.out.println("Response body => " + body);
    }

    @Test
    void bddGetStaticWithSpec(){
        when().get("/api/products/2")
                .then().statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .body("name", equalTo("iPhone X"))
                .log().headers()
                .log().body();
    }

    @Test
    void jsonobj(){
        JSONObject requestParams = new JSONObject();
        requestParams.put("name","mouse");
        requestParams.put("price",15);

        var res = given().contentType("application/json").body(requestParams.toJSONString())
                .when().post("/api/products");
        //        .then().log().body().statusCode(201).time(lessThan(2L)).log().all();
        String responseid = res.jsonPath().get("id").toString();
        System.out.println("Product id created was: " + responseid);

    }
}
