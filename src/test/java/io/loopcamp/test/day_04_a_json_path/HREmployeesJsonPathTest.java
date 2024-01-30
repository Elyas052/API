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

        // Get all employees first name who work for department_id=90
        /*
            SELECT first_name FROM employees
            WHERE department_id = 90;
         */

        // findAll() --- >  comes from scripting groovy language (based on java)
        List<String> firstNames = jsonPath.getList("items.first_name");
        System.out.println("First Names: " + firstNames);
        System.out.println(firstNames.size());

        // For those who are under department_id 90
        List<String> firstNamesWithDepId90 = jsonPath.getList("items.findAll{it.department_id==90}.first_name");
        System.out.println("First Names for those with Dep_ID 90: " + firstNamesWithDepId90);

        // Find all first_name for whose who works as IT_PROG
        List<String> firstNamesWithIt_Prog = jsonPath.getList("items.findAll{it.job_id=='IT_PROG'}.first_name");
        System.out.println("First Names for those with Dep_ID 90: " + firstNamesWithIt_Prog);

        // Find All first name making more than 5000
        List<String> allNameForSalaryMoreThan5000 = jsonPath.getList("items.findAll{it.salary > 5_000}.first_name");
        System.out.println("All Names For Salary More Than 5000: " + allNameForSalaryMoreThan5000);

        // Find the first name for the max salary -- > max{}
        String maxSalaryName = jsonPath.getString("items.max{it.salary}.first_name");
        System.out.println("Max Salary Name = " + maxSalaryName);

        // Find the first name for the mix salary -- > max{}
        String minSalaryName = jsonPath.getString("items.min{it.salary}.first_name");
        System.out.println("Min Salary Name = " + minSalaryName);

        // What is the min salary with related infos?
        String minSalary = jsonPath.getString("items.min{it.salary}");
        System.out.println("Min Salary: " + minSalary);
    }
}
