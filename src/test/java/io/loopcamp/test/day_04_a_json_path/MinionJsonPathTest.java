package io.loopcamp.test.day_04_a_json_path;

import io.loopcamp.utils.MinionTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for verifying JSON response using RestAssured and JsonPath for Minion API.
 */
public class MinionJsonPathTest extends MinionTestBase {

    /**
     * Given except is json
     * And path param_id is 13
     * When I send get request to /minions/{id}
     * --------------------
     * Then status code is 200
     * And content a type is json
     * And id value is 13
     * And name is "Jaimie"
     * And gender is "Female"
     * And phone is "7842554879"
     */

    @DisplayName("GET /minions/{id}")
    @Test
    public void getMinionJsonPathTest() {

        // Sending a GET request to /minions/{id} with path parameter
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 13)
                .when().get("/minions/{id}");

        // Uncomment the next line to print the response body for debugging purposes
        // response.prettyPrint();

        // Validating status code
        System.out.println("Status Code: " + response.statusCode());
        assertEquals(HttpStatus.SC_OK, response.statusCode());

        // Validating a content type
        System.out.println("Content type: " + response.contentType());
        assertEquals("application/json", response.contentType());
        // Another way to validate a content type using the header method
        assertEquals(ContentType.JSON.toString(), response.getHeader("Content-Type"));

        // Creating a JsonPath object from the response body
        JsonPath jsonPath = response.jsonPath();

        // Extracting and printing information about the minion
        System.out.println("id: " + jsonPath.getInt("id"));
        System.out.println("gender: " + jsonPath.getString("gender"));
        System.out.println("name: " + jsonPath.getString("name"));
        System.out.println("phone: " + jsonPath.getString("phone"));

        // Asserting specific values using JsonPath
        assertEquals(13, jsonPath.getInt("id"));
        assertEquals("Female", jsonPath.getString("gender"));
        assertEquals("Jaimie", jsonPath.getString("name"));
        assertEquals("7842554879", jsonPath.getString("phone"));
    }
}
