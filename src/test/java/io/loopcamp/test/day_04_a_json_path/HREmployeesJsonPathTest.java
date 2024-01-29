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
import java.util.Objects;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HREmployeesJsonPathTest extends HRApiTestBase {

    @DisplayName("GET /employees?limit=2")
    @Test
    public void jsonPathEmployeeTest() {
        /**
         *
         Map<String, Object> queryMap = new HashMap<>();
         queryMap.put("limit", 2);

         Response response = given().accept(ContentType.JSON)
         .and().queryParams(queryMap)
         .when().get("/employees");
         */

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("limit", 2)
                .when().get("/employees");
        //response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();
        System.out.println("1st emp name: " + jsonPath.getString("items[0].first_name"));
        System.out.println("1st emp job id: " + jsonPath.getString("items[0].job_id"));

        List<String> emails = jsonPath.getList("items.email");
        System.out.println("Emails = " + emails.size());
        System.out.println("Emails = " + emails);

        assertTrue(emails.contains("NYANG"));

    }
}
