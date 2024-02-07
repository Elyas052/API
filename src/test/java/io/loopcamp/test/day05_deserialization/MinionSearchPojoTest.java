package io.loopcamp.test.day05_deserialization;

import io.loopcamp.utils.MinionTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinionSearchPojoTest extends MinionTestBase {

    @DisplayName("GET /minions -- search POJO")
    @Test
    public void readMinionJsonUsingPathTest() {

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("nameContains", "e")
                .and().queryParam("gender", "Female")
                //.and().queryParam("nameContains", "e", "gender", "Female")
                .when().get("/minions/search");

        response.prettyPrint();
        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());


    }
}