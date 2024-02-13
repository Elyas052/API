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
     * Given an accepted type is Json
     * And ID path parameter value is 5
     *  When a user sends GET request to /api/minions/{id}
     * ----------------------------------------
     * Then response status code should be 200
     * And response content-type: application/json
     * And "Blythe" should be in response payload(body)
     */

    String apiMethod = "http://44.212.56.101:8000/api/minions";

    @DisplayName("GET /api/minions/{id}")
    @Test
    public void getSingleMinionTest() {

        // OPTION 1
        int id = 5;
        given().accept(ContentType.JSON) // ContentType.JSON -- > again this ENUM. Saying =, hey api, I need to get the data as JSON format.
                .when().get(apiMethod + "/" + id); // we basically concatenated id into the URL/ Ami Method / Request ENDPOINT

        // OPTION 2
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 5) // ID value is passed using the .pathParam() method / more readable & understandable. You can use the variable instead of 5 - > id
                .when().get(apiMethod + "/{id}"); //5

        //response.prettyPrint();

        // Then response status code should be 200
        //System.out.println("Status Code: " + response.statusCode());
        //assertEquals(200, response.statusCode());
        assertEquals(HttpStatus.SC_OK, response.statusCode()); // This will give HttpStatus.SC_OK which is 200

        // And response content-type: application/json
        //System.out.println("Content Type: " + response.contentType());
        //System.out.println("Content-Type: " + response.getHeader("content-type")); // this is another way to get the content-type
        assertEquals(ContentType.JSON.toString(), response.contentType());
        //assertEquals("application/json", response.contentType());

        // And "Blythe" should be in response payload(body)
        assertTrue(response.body().asString().contains("Blythe"));
    }

    /**
     * Given except a type is Json
     * And ID parameter value is 500
     * When a user sends GET request to /api/minions/{id}
     * ----------------------------------------
     * Then response status code should be 404
     * And response content-type: application/json
     * And "Not Found" message should be in response payload
     */

    @DisplayName("GET /api/minions/{id} with invalid id")
    @Test
    public void getSingleMinionNotFoundTest() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 500)
                .when().get(apiMethod + "/{id}");

        //System.out.println(response.statusCode());
        //assertEquals(404, response.statusCode());
        assertEquals(HttpStatus.SC_NOT_FOUND, response.statusCode());

        assertEquals(ContentType.JSON.toString(), response.contentType());
        //assertEquals("application/json", response.contentType());

        //assertEquals("Not Found", response.body().asString());
        assertTrue(response.body().asString().contains("Not Found"));
    }
}
