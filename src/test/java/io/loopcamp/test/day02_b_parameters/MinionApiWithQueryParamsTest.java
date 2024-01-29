package io.loopcamp.test.day02_b_parameters;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class MinionApiWithQueryParamsTest {

    /**
     * Given an accepted type is JSON
     * And query parameter values are:
     * gender|Female
     * nameContains|e
     * When a user sends GET request to /api/minions/search
     * ---------------------------------------------------------
     * Then response status code should be 200
     * And response content-type: application/json
     * And "Female" should be in response payload
     * And "Janette" should be in response payload
     */
    String endpoint = "http://44.212.56.101:8000/api/minions/search";

    @DisplayName("GET /api/minions/search with query params")
    @Test
    public void searchForMinionTest() {
        // Sending a GET request to the specified endpoint with query parameters
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("gender", "Female")
                .and().queryParam("nameContains", "e")
                .when().get(endpoint);

        // Verify if the status code is 200 (OK)
        assertEquals(HttpStatus.SC_OK, response.statusCode());

        // Verify that the response header content type is JSON
        assertEquals(ContentType.JSON.toString(), response.contentType());

        // Assert that "Female" is present in the response payload
        assertTrue(response.body().asString().contains("Female"));

        // Assert that "Male" is not present in the response payload
        assertTrue(!response.body().asString().contains("Male"));

        // Assert that "Janette" is present in the response payload
        assertTrue(response.body().asString().contains("Janette"));
    }
}