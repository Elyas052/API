package io.loopcamp.test.day04_b_xml_path_deserialization;

import io.loopcamp.pojo.Minion;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class AllMinionsPojoTest extends MinionToMapTest {


    /**
     * Given except a type is Json
     * when I send GET request to /minions
     * ------------------------------------
     * Then status code is 200
     * And content type is json
     * And I can convert json to list of minion POJOs
     */

    @DisplayName("GET /minions -- with POJO")
    @Test
    public void allMinionsPojoTest() {

        Response response = given().accept(ContentType.JSON)
                .when().get("/minions");

        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());

        JsonPath jsonPath = response.jsonPath();

        List<Minion> allMinions = jsonPath.getList("", Minion.class); // "" -- > hey start from beginning in JSON response body - > like from here {

        List<Minion> allFemaleMinions = new ArrayList<>();

        for (Minion each : allMinions) {
            if (each.getGender().equals("Female")) {
                allFemaleMinions.add(each);
            }
        }

        System.out.println(allFemaleMinions);

        // How many Female Minions we have?
        System.out.println("Female minion count: " + allFemaleMinions.size());
    }
}
