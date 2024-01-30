package io.loopcamp.test.day02_a_headers;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains tests related to headers for interacting with a Minion API.
 */
public class MinionHeadersTest {

    // Base URL for the Minion API
    String requestUrl = "http://44.212.56.101:8000/api/minions";

    /**
     * Test to ensure that a GET request to /api/minions returns a status code of 200
     * And the response is in XML format.
     */
    @DisplayName("GET /api/minions and expect defaulted XML format")
    @Test
    public void getAllMinionsHeadersTest() {

        when().get(requestUrl)
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.XML); // This will be more dynamic instead of putting "application/xml"
    }

    /**
     * Test to verify that when sending a GET request to /api/minions with the Accept header set to
     * application/json, the response status code is 200, and the content type is in JSON format.
     */
    @DisplayName("GET /api/minions with requested header ")
    @Test
    public void acceptTypeHeaderTest() {

        given().accept(ContentType.JSON) // More dynamic using ENUMs
                .when().get(requestUrl)
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON);  // More dynamic using ENUMs
    }

    /**
     * Test to validate that when sending a GET request to /api/minions with the Accept header set to
     * application/xml, the response status code is 200, and the headers can be read and validated.
     */
    @DisplayName("GET /api/minions with requested header JSON - read headers")
    @Test
    public void readResponseHeadersTest() {

        Response response = given().accept(ContentType.JSON)
                .when().get(requestUrl);

        // Validate the response status code
        assertEquals(200, response.statusCode());

        // How to read each header - .getHeader(String str - Key);
        System.out.println("Date Header: " + response.getHeader("Date"));
        System.out.println("Content-type Header: " + response.getHeader("Content-Type"));
        System.out.println("Connection Header: " + response.getHeader("Connection"));

        // How to read all Headers - .getHeaders();
        System.out.println("\nAll Headers: \n" + response.getHeaders());

        // How to validate if any of the header is not empty
        assertTrue(response.getHeader("Keep-Alive") != null);
    }
}
