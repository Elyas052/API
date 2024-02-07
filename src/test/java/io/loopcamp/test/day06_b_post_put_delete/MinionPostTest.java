package io.loopcamp.test.day06_b_post_put_delete;

import io.loopcamp.utils.MinionRestUtils;
import io.loopcamp.utils.MinionTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class MinionPostTest extends MinionTestBase {

    /**
     * Given except is json
     * And content type is json
     * And body is:
     * {
     * "gender": "Male",
     * "name": "TestPost4",
     * "phone": 1234567425
     * }
     * When I send POST request to /minions
     * ---------------------------------------------
     * Then status code is 201
     * And content type is "application/json;charset=UTF-8"
     * And "success" is "A Minion is Born!"
     * Data name, gender, phone matches my request details
     */

    @Test
    public void addNewMinionAsJsonTest() {
        String jsonBody = "{\n" +   // Always put the double quote first and then copy past the body
                "\"gender\": \"Male\",\n" +
                "\"name\": \"TestPost\",\n" +
                "\"phone\": 1234567425\n" +
                "}";

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(jsonBody)
                .when().post("/minions");

        // Verify status code
        response.prettyPrint();
        assertEquals(HttpStatus.SC_CREATED, response.statusCode()); // JUnit
        assertThat(response.statusCode(), is(201)); // Hamcrest

        // Verify Header
        assertThat(response.contentType(), is("application/json;charset=UTF-8"));

        // Converted response to JsonPath
        JsonPath jsonPath = response.jsonPath();

        // Verify body
        assertThat(jsonPath.getString("success"), equalTo("A Minion is Born!"));

        assertThat(jsonPath.getString("data.name"), equalTo("TestPost"));
        assertThat(jsonPath.getString("data.gender"), equalTo("Male"));
        assertThat(jsonPath.getString("data.phone"), equalTo("1234567425"));

        int id = jsonPath.getInt("data.id");
        System.out.println("Minion id: " + id);
        MinionRestUtils.deleteMinionById(id);
    }

    public void addNewMinionAsMapTest() {

    }

    public void addNewMinionAsPOJOTest() {

    }
}
