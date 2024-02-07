package io.loopcamp.test.tasks.day4;

import io.loopcamp.utils.HRApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class Task_1 extends HRApiTestBase {

    /**
     * Q1
     * Given except a type is Json
     * Path param value - US
     * When users send request to /countries
     * Then status code is 200
     * And Content - Type is Json
     * And country_id is US
     * And Country_name is the United States of America
     * And Region_id is 10
     */
    @DisplayName("GET /countries with JsonPath")
    @Test
    public void verifyCountryDetailsUsingJsonPath() {

        // Given an accepted type is Json
        // When users send a request to /countries with path param value - US
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("country", "US")
                .when().get("/countries/{country}");

        // Then status code is 200
        assertEquals(HttpStatus.SC_OK, response.statusCode());

        // And Content-Type is Json
        assertEquals(ContentType.JSON.toString(), response.contentType());

        // Assigning response Json payload/body to JsonPath
        JsonPath jsonPath = response.jsonPath();

        // Extract values using JsonPath and assert
        assertEquals("US", jsonPath.getString("country_id"));
        assertEquals("United States of America", jsonPath.getString("country_name"));
        assertEquals("10", jsonPath.getString("region_id"));
    }

    /**
     * Q2
     * Given accept type is Json
     * Query param value - q={"department_id":80}
     * When users send request to /employees
     * Then status code is 200
     * And Content - Type is Json
     * And all job_ids start with 'SA'
     * And all department_ids are 80
     * Count is 25
     */
    @DisplayName("GET /employees with JsonPath")
    @Test
    public void verifyEmployeeDetails() {

        // Given accept type is Json
        // And query param value - q={"department_id":80}
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"department_id\":80}")
                .when().get("/employees");

        // Then status code is 200
        assertEquals(HttpStatus.SC_OK, response.statusCode());

        // And Content-Type is Json
        assertEquals(ContentType.JSON.toString(), response.contentType());

        // Assigning response Json payload/body to JsonPath
        JsonPath jsonPath = response.jsonPath();

        // Extract job_ids using JsonPath and verify they start with 'SA'
        List<String> jobIds = jsonPath.getList("items.job_id");
        for (String jobId : jobIds) {
            assertTrue(jobId.startsWith("SA"));
        }

        // Extract department_ids using JsonPath and verify they are all 80
        List<Integer> departmentIds = jsonPath.getList("items.department_id");
        for (Integer departmentId : departmentIds) {
            assertEquals(80, departmentId);
        }

        // The count of employees is 25
        assertEquals(25, jsonPath.getInt("limit"));
    }

    /**
     * Q3
     * Given an accepted type is Json
     * Query param value q= region_id 3
     * When users send a request to /countries
     * Then status code is 200
     * And all region_ids are 30
     * And count is 6
     * And hasMore is false
     * And Country_names are Australia, China, India, Japan, Malaysia, Singapore
     */
    @DisplayName("GET /countries with JsonPath")
    @Test
    public void verifyCountriesDetails() {

        // Given accept type is Json
        // And query param value q= region_id 3
        Response response = given().log().all().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":30}")
                .when().get("/countries");

        // Then status code is 200
        assertEquals(HttpStatus.SC_OK, response.statusCode());

        // Assigning response Json payload/body to JsonPath
        JsonPath jsonPath = response.jsonPath();

        // Verify all region_ids are 30
        List<Integer> regionIds = jsonPath.getList("items.region_id");
        for (Integer regionId : regionIds) {
            assertEquals(30, regionId);
        }

        // Verify count is 6
        assertEquals(6, jsonPath.getInt("count"));

        // Verify hasMore is false
        assertFalse(jsonPath.getBoolean("hasMore"));

        // Verify Country_names are Australia, China, India, Japan, Malaysia, Singapore
        List<String> expectedCountryNames =  Arrays.asList("China", "Israel", "India", "Japan", "Kuwait", "Singapore");
        List<String> actualCountryNames = jsonPath.getList("items.country_name");
        assertEquals(expectedCountryNames, actualCountryNames);
    }
}
