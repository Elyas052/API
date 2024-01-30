package io.loopcamp.test.day_04_a_json_path;

import io.loopcamp.utils.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for verifying JSON response using RestAssured and JsonPath for Zip Code API.
 */
public class ZipCodeApiJsonPathTest {

    /**
     * Zip code task http://api.zippopotam.us/us/22031
     * Given Accept application/json
     * And path zipcode is 22031
     * When I send a GET request to /us endpoint
     * --------------------
     * Then status code must be 200
     * And content type must be application/json
     * And Server header is cloudflare
     * And Report-To header exists
     * And body should contain following information
     * post code is 22031
     * country  is United States
     * country abbreviation is US
     * place name is Fairfax
     * state is Virginia
     * latitude is 38.8604
     */

    @BeforeEach
    public void setUp() {
        baseURI = ConfigurationReader.getProperty("zipcode.api.url");
    }

    @DisplayName("GET /us/zipcode")
    @Test
    public void zipCodeJsonPathTest() {

        // Sending a GET request to /us/{zipcode}
        Response response = given().accept(ContentType.JSON)
                .pathParam("country", "us")
                .pathParam("zipcode", "22031")
                .when().get("/{country}/{zipcode}");

        // Validating status code and content type
        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());

        // Assigning response Json payload/body to JsonPath
        JsonPath jsonPath = response.jsonPath();

        // Extracting and verifying specific values using JsonPath
        System.out.println("Post Code: " + jsonPath.getString("'post code'"));
        assertEquals("22031", jsonPath.getString("'post code'"));

        // Calling the reusable method for additional verification
        verifyZipCode("22031", jsonPath);

        assertEquals("United States", jsonPath.getString("country"));
        assertEquals("US", jsonPath.getString("'country abbreviation'"));
        assertEquals("Fairfax", jsonPath.getString("places[0].'place name'"));
        assertEquals("Virginia", jsonPath.getString("places[0].state"));
        assertEquals("38.8604", jsonPath.getString("places[0].latitude"));
    }

    // Since we can to all with .path() method as well, why we do it with jsonPath?
    // 1. jsonPath and some more methods will help us to filter the result directly in the assertions -- which we will see later.
    // 2. We can use a Json path when we want to call a reusable method.

    /**
     * Reusable method to verify the zip code.
     *
     * @param expZipCode - Expected zip code value
     * @param jsonPath   - JsonPath object for response
     */
    public void verifyZipCode(String expZipCode, JsonPath jsonPath) {
        assertEquals(expZipCode, jsonPath.getString("'post code'"));
    }
}
