package io.loopcamp.test.day_04_a_json_path;

import io.loopcamp.utils.HRApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for verifying JSON response using RestAssured and JsonPath for HR Employees API.
 */
public class HREmployeesJsonPathTest extends HRApiTestBase {

    @DisplayName("GET /employees?limit=2")
    @Test
    public void jsonPathEmployeeTest() {

        /**
         Map<String, Object> queryMap = new HashMap<>();
         queryMap.put("limit", 2);

         Response response = given().accept(ContentType.JSON)
         .and().queryParams(queryMap)
         .when().get("/employees");
         */

        // Sending a GET request to /employees with a limit of 2
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("limit", 2)
                .when().get("/employees");

        // Uncomment the next line to print the response body for debugging purposes
        // response.prettyPrint();

        // Creating a JsonPath object from the response body
        JsonPath jsonPath = response.jsonPath();

        // Extracting and printing information about the first employee
        System.out.println("1st emp name: " + jsonPath.getString("items[0].first_name"));
        System.out.println("1st emp job id: " + jsonPath.getString("items[0].job_id"));

        // Extracting the list of email addresses and printing their size and values
        List<String> emails = jsonPath.getList("items.email");
        System.out.println("Emails = " + emails.size());
        System.out.println("Emails = " + emails);

        // Asserting that the list of emails contains the specified email address "NYANG"
        assertTrue(emails.contains("NYANG"));
    }
}
