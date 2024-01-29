package io.loopcamp.test.day01_Intro;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * This class contains a simple test case for making a Hello World GET request to a specified API endpoint.
 */
public class HelloWorldApiTest {

    // API Endpoint
    String url = "https://sandbox.api.service.nhs.uk/hello-world/hello/world";

    /**
     * Test case for Hello World GET request.
     */
    @DisplayName("Hello World GET request")
    @Test
    public void helloWorldGetRequestTest() {

        // Send a get request and save response inside the Response object
        Response response = RestAssured.get(url);

        // Print response body in a formatted way (in this case JSON format) - RESPONSE BODY
        response.prettyPrint(); // This also returns a String

        // Status code of the response
        System.out.println("Status Code: " + response.getStatusCode()); // 200
        System.out.println("Status Line: " + response.getStatusLine()); // HTTP/1.1 200 OK

        // Assert/Validate that Response Code is 200
        Assertions.assertEquals(200, response.getStatusCode(), "Response Code is not 200");

        // You can also assign RESPONSE STATUS CODE into a variable and then use the variable to Assert
        int actualStatusCode = response.getStatusCode();
        Assertions.assertEquals(200, actualStatusCode, "Response Code is not 200");

        // .asString() method will return the RESPONSE BODY as a String
        String actualResponseBody = response.prettyPrint();

        // Assert that "Hello World!" is in the body
        Assertions.assertTrue(actualResponseBody.contains("Hello World!"));
    }
}
