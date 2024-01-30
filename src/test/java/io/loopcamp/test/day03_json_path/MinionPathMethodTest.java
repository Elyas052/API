package io.loopcamp.test.day03_json_path;

import io.loopcamp.utils.MinionTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for verifying Json response using RestAssured and JsonPath.
 */
public class MinionPathMethodTest extends MinionTestBase {

    /**
     * Given an accepting is Json
     * And path param_id is 13
     * When I send get request to /minions/{id}
     * ---------------------------------------------------------
     * Then status code is 200
     * And content type is json
     * And id value is 13
     * And name is "Jaimie"
     * And gender is "Female"
     * And phone is "7842554879"
     */
    @DisplayName("GET /minions/{id}")
    @Test
    public void readMinionJsonUsingPathTest() {

        // Send a GET request to /minions/{id} with path parameter
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 13)
                .when().get("/minions/{id}");

        // Print the pretty-printed response to the console
        response.prettyPrint();

        // Validate status code and content type
        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());

        // Validate specific values using JsonPath
        assertEquals(new Integer(13), response.path("id"));
        assertEquals("Jaimie", response.path("name"));
        assertEquals("Female", response.path("gender"));
        assertEquals("7842554879", response.path("phone"));
    }

    /**
     * Given an accepting is Json
     * When I send get request to /minions
     * ---------------------------------------------------------
     * Then status code is 200
     * And content type is json
     * And I can navigate JSon array object
     */
    @DisplayName("GET /minions with path()")
    @Test
    public void readMinionJsonArrayUsingPathTest() {

        // Send a GET request to /minions
        Response response = given().accept(ContentType.JSON)
                .when().get("/minions");

        // Validate status code and content type
        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());

        // Print all ids
        System.out.println("All Ids: " + response.path("id"));

        // Print 1st minion id and name
        System.out.println("1st minion id: " + response.path("[0].id"));

        // Print last minion id and name
        System.out.println("Last minion id: " + response.path("id[-1]"));

        // Store all the ids in a List
        List<Integer> listId = response.path("id");
        System.out.println("Total Minions: " + listId.size());
        System.out.println("All Ids: " + listId);

        // Store all the names in a List, print a personalized greeting
        List<String> listName = response.path("name");
        for (String name : listName) {
            System.out.println("Hi " + name + "!");
        }
    }
}
