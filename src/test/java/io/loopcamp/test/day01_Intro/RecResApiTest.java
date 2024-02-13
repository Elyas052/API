package io.loopcamp.test.day01_Intro;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class RecResApiTest {
    /**
     * When a user sends GET request to
     * https://reqres.in/api/users/
     * ----------------------------------------
     * Then RESPONSE STATUS CODE is 200
     * And RESPONSE BODY should contain "George"
     */

    String endpoint = "https://reqres.in/api/users/";

    @DisplayName("GET all users")
    @Test
    public void usersGetTest() {

        // When user sends GET request to
        // Response response = RestAssured.get(endpoint);
        // Response response = get(endpoint); // since we did the static import, I can use the get() directly.
        Response response = when().get(endpoint); // This one and one above they do the same thing. Just for GHERKIN language format we used when();

        // Then RESPONSE STATUS CODE is 200
        System.out.println("Status Code: " + response.statusCode());
        assertEquals(200, response.statusCode());

        // BDD Syntax - GHERKIN syntax
        response.then().statusCode(200); // This will do the exact same validation as the one above
        response.then().assertThat().statusCode(200);  // This will also do the exact same validation as the one above

        // And RESPONSE BODY should contain "George"

        // The Difference between prettyPrint and asSting method
        // Pretty print shows it in JSON format
        // asString shows it in a single line
        System.out.println("Print the RESPONSE BODY: " + response.asString());
        response.prettyPrint();

        assertTrue(response.asString().contains("George"));
    }
}
