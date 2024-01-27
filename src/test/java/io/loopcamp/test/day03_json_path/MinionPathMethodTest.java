package io.loopcamp.test.day03_json_path;

import io.loopcamp.utils.ConfigurationReader;
import io.loopcamp.utils.MinionTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class MinionPathMethodTest extends MinionTestBase {

    /**
     * Given accept is json
     * And path param id is 13
     * When I send get request to /minions/{id}
     * ----------
     * Then status code is 200
     * And content type is json
     * And id value is 13
     * And name is "Jaimie"
     * And gender is "Female"
     * And phone is "7842554879"
     */

    @DisplayName("GET /minions/{id}")
    @Test
    public void readMinionJsonUsingPathTest() {

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 13)
                .when().get("/minions/{id}");
        response.prettyPrint();
        /*
            {
                "id": 13,
                "gender": "Female",
                "name": "Jaimie",
                "phone": "7842554879"
            }
         */
        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());
        /*
         * And id value is 13
         * And name is "Jaimie"
         * And gender is "Female"
         * And phone is "7842554879"
         */
        //assertTrue(response.body().asString().contains("13"));
        System.out.println("id: " + response.path("id"));
        System.out.println("name: " + response.path("name"));
        System.out.println("gender: " + response.path("gender"));
        System.out.println("phone: " + response.path("phone"));


        Integer expId = 13;
        String name = response.path("name");

        /*
         * And id value is 13
         * And name is "Jaimie"
         * And gender is "Female"
         * And phone is "7842554879"
         */
        assertEquals(new Integer(13), response.path("id")); // Here both has to be same data type which is Integer.
        assertEquals(expId, response.path("id")); // Here both has to be same data type which is Integer.


        assertEquals("Jaimie", response.path("name")); // String
        assertEquals("Female", response.path("gender"));
        assertEquals("7842554879", response.path("phone"));


    }


}