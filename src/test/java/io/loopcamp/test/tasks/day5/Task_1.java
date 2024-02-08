package io.loopcamp.test.tasks.day5;

import io.loopcamp.pojo.ZipCode;
import io.loopcamp.pojo.ZipCodeInfo;
import io.loopcamp.utils.ZipCodeTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class Task_1 extends ZipCodeTestBase {

    @DisplayName("Get Body Response")
    @Test
    public void given_except_application_json() {

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("postal-code", "22192")
                .get("us/{postal-code}");

        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());
        assertEquals("cloudflare", response.getHeader("Server"));
        assertFalse(("Report-To").isEmpty());

        ZipCode zip = response.as(ZipCode.class);

        assertEquals("22192", zip.getPostCode());
        assertEquals("United States", zip.getCountry());
        assertEquals("US", zip.getCountryAbbreviation());

        assertEquals("Woodbridge", zip.getPlaces().get(0).getPlaceName());
        assertEquals("Virginia", zip.getPlaces().get(0).getState());
        assertEquals("38.676", zip.getPlaces().get(0).getLatitude());
    }

    @DisplayName("Wrong Zip Code")
    @Test
    public void wrongZipCode() {

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("postal-code", "50000")
                .get("us/{postal-code}");

        assertEquals(HttpStatus.SC_NOT_FOUND, response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());
    }

    @DisplayName("Compare Body Information")
    @Test
    public void getBodyInformation() {

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("state", "VA")
                .and().pathParam("city", "Woodbridge")
                .when().get("/us/{state}/{city}");

        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());

        ZipCode zip = response.as(ZipCode.class);

        assertEquals("US", zip.getCountryAbbreviation());
        assertEquals("United States", zip.getCountry());
        assertEquals("Woodbridge", zip.getPlaceName());

        for (ZipCodeInfo each : zip.getPlaces()) {
            assertTrue(each.getPlaceName().contains("Woodbridge"));
            assertTrue(each.getPostCode().startsWith("22"));
        }
    }
}
