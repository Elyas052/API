package io.loopcamp.test.day03_json_path;

import io.loopcamp.utils.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for HR API GET requests using RestAssured.
 */
public class HRApiGetTest {

    /**
     * Before each test, set up the base URI using the URL from the configuration properties.
     */
    @BeforeEach
    public void setUp() {
        baseURI = ConfigurationReader.getProperty("hr.api.url");
    }

    /**
     * Test case to verify that user can see all regions when sending a GET request to /ords/hr/regions.
     * Given an accepted type is Json
     * When user sends a get request to /regions
     * ---------------------------------------------------------
     * Status code should be 200
     * Content type should be "application/json"
     * And body should contain "Europe"
     */
    @DisplayName("GET /regions")
    @Test
    public void getRegionsTest() {

        Response response = given().accept(ContentType.JSON)
                .when().get("/regions");

        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());
        assertTrue(response.body().asString().contains("Europe"));
    }

    /**
     * Test case to verify that user can get a single region using path parameter "region_id".
     * Given an accepted type is Json
     * And Path param "region_id" value is 10
     * When user sends get request to /ords/hr/regions/{region_id}
     * ---------------------------------------------------------
     * Status code should be 200
     * Content type should be "application/json"
     * And body should contain "Europe"
     */
    @DisplayName("GET /regions/{region_id}")
    @Test
    public void getSingleRegionPathParamTest() {

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("region_id", 10)
                .when().get("/regions/{region_id}");

        response.prettyPrint();

        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());
        assertTrue(response.body().asString().contains("Europe"));
    }

    /**
     * Test case to verify that user can get regions using query parameter "q".
     * Given except type is Json
     * And query param q={"region_name": "Americas"}
     * When user sends get request to /regions
     * ---------------------------------------------------------
     * Status code should be 200
     * Content type should be "application/json"
     * And region name should be "Americas"
     * And region id should be "20"
     */
    @DisplayName("GET /regions?q={\"region_name\": \"Americas\"}")
    @Test
    public void getRegionQueryParamTest() {

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_name\": \"Americas\"}")
                .when().get("/regions");

        response.prettyPrint();

        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());
        assertTrue(response.body().asString().contains("Americas"), "Does NOT contain region_name");
        assertTrue(response.body().asString().contains("20"), "Does NOT contain region_id");
    }
}
