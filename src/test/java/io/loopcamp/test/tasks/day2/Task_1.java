package io.loopcamp.test.tasks.day2;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task_1 {

    String apiUrl = "https://jsonplaceholder.typicode.com";

    /**
     * Test to send a GET request and print the response for SC_200 OK.
     * ---------------------------------------------------------
     * - Given accept type is Json
     * - When user sends request to https://jsonplaceholder.typicode.com/posts
     * - Then print response body
     * - And status code is 200
     * - And Content - Type is Json
     */
    @DisplayName("Send Get request and response SC_200 OK")
    @Test
    public void sendGetRequestTest() {

        // Send a GET request to the specified API endpoint
        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get(apiUrl + "/posts");

        // Print the pretty-printed response to the console
        response.prettyPrint();
    }

    /**
     * Test to send a GET request with ID 1 and validate the response.
     * ---------------------------------------------------------
     * - Given accept type is Json
     * - Path param "id" value is 1
     * - When user sends request to https://jsonplaceholder.typicode.com/posts/{id}
     * - Then status code is 200
     * - And json body contains "repellat provident"
     * - And header Content - Type is Json
     * - And header "X-Powered-By" value is "Express"
     * - And header "X-Ratelimit-Limit" value is 1000
     * - And header "Age" value is more than 100
     * - And header "NEL" value contains "success_fraction"
     */
    @DisplayName("Send Get request and response id_1")
    @Test
    public void sendGetRequestId() {
        int id = 1;

        // Send a GET request to the specified API endpoint with ID 1
        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get(apiUrl + "/posts/" + id);

        // Assert the status code
        assertEquals(HttpStatus.SC_OK, response.statusCode());

        // Assert response body contains "repellat provident"
        assertTrue(response.body().asString().contains("repellat provident"));

        // Assert Content-Type header
        assertEquals("application/json; charset=utf-8", response.contentType());

        // Assert headers
        assertEquals("Express", response.getHeader("X-Powered-By"));
        assertEquals("1000", response.getHeader("X-Ratelimit-Limit"));

        // Assert Age header value as an integer
        assertTrue(Integer.parseInt(response.getHeader("Age")) > 100);

        // Assert NEL header contains "success_fraction"
        assertTrue(response.getHeader("NEL").contains("success_fraction"));
    }

    /**
     * Test to send a GET request and validate the response for SC_404 Not Found.
     * ---------------------------------------------------------
     * - Given accept type is Json
     * - Path param "id" value is 12345
     * - When user sends request to https://jsonplaceholder.typicode.com/posts/{id}
     * - Then status code is 404
     * <p>
     * - And json body contains "{}"
     */
    @DisplayName("Send Get request and response SC_404 Not Found")
    @Test
    public void getRequest404() {
        int id = 12345;

        // Send a GET request to the specified API endpoint with ID 12345
        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get(apiUrl + "/posts/" + id);

        // Assert the status code
        assertEquals(HttpStatus.SC_NOT_FOUND, response.statusCode());

        // Assert response body contains "{}"
        assertTrue(response.asString().contains("{}"));
    }

    /**
     * Test to send a GET request and validate the response body.
     * ---------------------------------------------------------
     * - Given an accepted type is Json
     * - Path param "id" value is 2
     * - When user sends request to https://jsonplaceholder.typicode.com/posts/{id}/comments
     * - Then status code is 200
     * - And header Content - Type is Json
     * - And json body contains "Presley.Mueller@myrl.com", "Dallas@ole.me", "Mallory_Kunze@marie.org"
     */
    @DisplayName("Send Get request and response body contains")
    @Test
    public void getRequestAndResponseBody() {
        int id = 2;

        // Send a GET request to the specified API endpoint with ID 2
        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get(apiUrl + "/posts/" + id + "/comments");

        // Assert the status code
        assertEquals(HttpStatus.SC_OK, response.statusCode());

        // Assert response body contains specified email addresses
        assertTrue(response.asString().contains("Presley.Mueller@myrl.com")
                && response.asString().contains("Dallas@ole.me")
                && response.asString().contains("Mallory_Kunze@marie.org"));
    }

    /**
     * Test to send a GET request and validate the response for a specific postId.
     * ---------------------------------------------------------
     * - Given an accept type is Json
     * - Query Param "postId" value is 1
     * - When user sends request to https://jsonplaceholder.typicode.com/comments
     * - Then status code is 200
     * - And header Content - Type is Json
     * - And header "Connection" value is "keep-alive"
     * - And json body contains "Lew@alysha.tv"
     */
    @DisplayName("Send Get request and response postId")
    @Test
    public void getRequestAndResponsePostId() {

        // Send a GET request to the specified API endpoint with postId parameter
        Response response = given()
                .accept(ContentType.JSON)
                .and().queryParam("postId", 1)
                .when()
                .get(apiUrl + "/comments");

        // Assert the status code
        assertEquals(HttpStatus.SC_OK, response.statusCode());

        // Assert Content-Type header
        assertTrue(response.getHeader("Content-Type").equals("application/json; charset=utf-8"));

        // Assert Connection header
        assertTrue(response.getHeader("Connection").equals("keep-alive"));

        // Assert response body contains "Lew@alysha.tv"
        assertTrue(response.asString().contains("Lew@alysha.tv"));
    }

    /**
     * Test to send a GET request and validate the response by specifying a parameter (postId).
     * ---------------------------------------------------------
     * - Given accept type is Json
     * - Query Param "postId" value is 333
     * - When user sends request to https://jsonplaceholder.typicode.com/comments
     * - And header Content - Type is Json
     * - And header "Content-Length" value is 2
     * - And json body contains "[]"
     */
    @DisplayName("Send Get request and response by value")
    @Test
    public void getRequestAndResponseByValue() {

        // Send a GET request to the specified API endpoint with postId parameter
        Response response = given()
                .accept(ContentType.JSON)
                .and().queryParam("postId", 333)
                .when()
                .get(apiUrl + "/comments");

        // Assert Content-Type header
        assertTrue(response.getHeader("Content-Type").equals("application/json; charset=utf-8"));

        // Assert Connection header
        assertEquals(response.getHeader("Content-Length"), "2");

        // Assert response body contains "[]"
        assertTrue(response.body().asString().contains("[]"));
    }
}
