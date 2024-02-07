package io.loopcamp.utils;

import io.restassured.http.ContentType;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class MinionRestUtils {

    private static String baseUrl = ConfigurationReader.getProperty("minion.api.url");

    public static void deleteMinionById(int id) {

        System.out.println("DELETING minion with id {" + id + "}");
        given().accept(ContentType.JSON)
                .and().pathParam("id", id)
                .when().delete(baseUrl + "/minions/{id}");
        //.then().log().all();
    }
}
