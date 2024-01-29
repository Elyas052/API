package io.loopcamp.test.day02_b_parameters;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class MinionApiWithParamsTest {

    /**
     * Given an accepted type is JSON
     * And ID path parameter value is 5
     * When a user sends GET request to /api/minions/{id}
     * ---------------------------------------------------------
     * Then response status code should be 200
     * And response content-type: application/json
     * And "Blythe" should be in response payload(body)
     */

    String apiMethod = "http://44.212.56.101:8000/api/minions";

    @DisplayName("GET /api/minions/{id}")
    @Test
    public void getSingleMinionTest() {

        // OPTION 1: Sending a GET request without using path parameters explicitly
        int id = 5;
        given().accept(ContentType.JSON) // Specify that the response should be in JSON format
                .when().get(apiMethod + "/" + id); // Concatenate the ID into the URL to specify the endpoint

        // OPTION 2: Sending a GET request using path parameters for better readability
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 5) // Pass the ID value using .pathParam() for improved readability
                .when().get(apiMethod + "/{id}");

        // Uncomment the following line to print the response body in a readable format
        // response.prettyPrint();

        // Validate that the response status code is 200 (OK)
        assertEquals(HttpStatus.SC_OK, response.statusCode());

        // Validate that the response content-type is application/json
        assertEquals(ContentType.JSON.toString(), response.contentType());

        // Validate that the response body contains the minion's name "Blythe"
        assertTrue(response.body().asString().contains("Blythe"));
    }

    /**
     * Given an accepted type is JSON
     * And ID parameter value is 500
     * When a user sends GET request to /api/minions/{id}
     * ---------------------------------------------------------
     * Then response status code should be 404
     * And response content-type: application/json
     * And "Not Found" message should be in response payload
     */

    @DisplayName("GET /api/minions/{id} with invalid id")
    @Test
    public void getSingleMinionNotFoundTest() {

        // Send a GET request with invalid ID and accept JSON response
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 500)
                .when().get(apiMethod + "/{id}");

        // Uncomment the following line to print the status code to the console
        // System.out.println(response.statusCode());

        // Assert that the response status code is 404 (HttpStatus.SC_NOT_FOUND)
        assertEquals(HttpStatus.SC_NOT_FOUND, response.statusCode());

        // Assert that the content type of the response is JSON
        assertEquals(ContentType.JSON.toString(), response.contentType());

        // Uncomment the following line to print the response body to the console
        // System.out.println(response.body().asString());

        // Assert that the response body contains the expected "Not Found" message
        assertTrue(response.body().asString().contains("Not Found"));
    }
}
