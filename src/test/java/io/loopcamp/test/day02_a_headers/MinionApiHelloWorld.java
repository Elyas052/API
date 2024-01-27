package io.loopcamp.test.day02_a_headers;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class MinionApiHelloWorld {

    /**
     * When I send GET request to http://52.23.174.40:8000/api/hello
     * ---------------------------------------------------------
     * Then status code should be 200
     * And response body should be equal to "Hello from Minion"
     * And a content type is "text/plain;charset=ISO-8859-1"
     */

    String apiMethod = "http://52.23.174.40:8000/api/hello";

    @DisplayName("GET api/hello")
    @Test
    public void helloApiTest() {

        Response response = when().get(apiMethod);
        //System.out.println("Response Status Code: " + response.getStatusCode());
        assertEquals(200, response.getStatusCode());

        // And response body should be equal to "Hello from Minion"
        //response.prettyPrint();
        //assertTrue(response.body().asString().contains("Hello from Minion"));
        assertEquals("Hello from Minion", response.body().asString());


        // And a content type is "text/plain;charset=UTF-8"
        //System.out.println("Response Content Type: " + response.getContentType());
        //assertTrue(response.contentType().contains("text/plain"));
        assertEquals("text/plain;charset=ISO-8859-1", response.contentType());
    }

    /**
     * When I send GET request to http://52.23.174.40:8000/api/hello"
     * ---------------------------------------------------------
     * Then status code should be 200
     * And response body should be equal to "Hello from Minion"
     * And content type is "text/plain;charset=ISO-8859-1"
     */

    @DisplayName("GET api/hello - BDD")
    @Test
    public void helloApiBddTest() {
        when().get(apiMethod)
                .then().assertThat().statusCode(200)
                .and().contentType("text/plain;charset=ISO-8859-1");
    }
}
