package io.loopcamp.test.day02_a_headers;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class MinionApiHelloWorld {

    /**
     * When I send GET request to http://44.212.56.101:8000/api/hello
     * ----------------------------------------
     * Then status code should be 200
     * And response body should be equal to "Hello from Minion"
     * And a content type is "text/plain;charset=ISO-8859-1"
     */

    String apiMethod = "http://44.212.56.101:8000/api/hello";

    @DisplayName("GET api/hello")
    @Test
    public void helloApiTest() {
        Response response = when().get(apiMethod);

        //System.out.println("Response Status Code: " + response.statusCode());
        assertEquals(200, response.statusCode());

        // And response body should be equal to "Hello from Minion"
        // response.prettyPrint();
        assertEquals("Hello from Minion", response.body().asString());

        // And content type is "text/plain;charset=ISO-8859-1"
        // System.out.println("Response Content Type: " + response.getContentType());
        assertEquals("text/plain;charset=ISO-8859-1", response.contentType());
    }

    /**
     * When I send GET request to http://44.212.56.101:8000/api/hello
     * ----------------------------------------
     * Then status code should be 200
     * And content type is ""text/plain;charset=ISO-8859-1"
     * Lets not worry abut body section
     * BDD -> given, when, then, and keywords. Making readable --> chaining
     */

    @DisplayName(" GET api/hello - bdd")
    @Test
    public void helloApiBddTest() {
        when().get(apiMethod)
                .then().assertThat().statusCode(200)
                .and().contentType("text/plain;charset=ISO-8859-1");
    }
}
