package io.loopcamp.test.day02_a_headers;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains tests for the Minion APIs "Hello World" endpoint.
 */
public class MinionApiHelloWorld {

    // API endpoint for the "Hello World" resource
    String apiMethod = "http://44.212.56.101:8000/api/hello";

    /**
     * Test case for sending a GET request to the "Hello World" API endpoint.
     * Checks if the status code is 200, response body is "Hello from Minion",
     * And a content type is "text/plain;charset=ISO-8859-1".
     */
    @DisplayName("GET api/hello")
    @Test
    public void helloApiTest() {

        // Send a GET request and save the response
        Response response = when().get(apiMethod);

        // Check if the status code is 200
        assertEquals(200, response.getStatusCode());

        // Check if the response body is equal to "Hello from Minion"
        assertEquals("Hello from Minion", response.body().asString());

        // Check if the content type is "text/plain;charset=ISO-8859-1"
        assertEquals("text/plain;charset=ISO-8859-1", response.contentType());
    }

    /**
     * Test case for sending a GET request to the "Hello World" API endpoint using BDD style.
     * Checks if the status code is 200 and content type is "text/plain;charset=ISO-8859-1".
     */
    @DisplayName("GET api/hello - BDD")
    @Test
    public void helloApiBddTest() {

        when().get(apiMethod)
                .then().assertThat().statusCode(200)
                .and().contentType("text/plain;charset=ISO-8859-1");
    }
}
