package io.loopcamp.test.day04_b_xml_path_deserialization;

import groovy.xml.XmlParser;
import io.loopcamp.utils.MinionTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class MinionXmlPathTest extends MinionTestBase {

    /**
     * Given an accepted type is application/xml
     * When I send GET request to /minions
     * ----------------------------------------
     * Then status code is 200
     * And content type is XML
     * And minion at index 0 is matching: (NOTE: Your index 0 might be different)
     * id --> 1
     * name --> Meade
     * gender --> Male
     * phone --> 9994128233
     */

    @DisplayName("GET /minions -- with XML")
    @Test
    public void readMinionJsonUsingPathTest() {

        // Send a GET request to /minions
        Response response = given().accept(ContentType.XML)
                .when().get("/minions");

        // Validate status code and content type
        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals(ContentType.XML.toString(), response.contentType());

        // Convert XML response body to XmlPath object
        XmlPath xmlPath = response.xmlPath();

        System.out.println("id: " + xmlPath.getInt("List.item[0].id"));
        System.out.println("name: " + xmlPath.getString("List.item[0].name"));
        System.out.println("gender: " + xmlPath.getString("List.item[0].gender"));
        System.out.println("phone: " + xmlPath.getString("List.item[0].phone"));
        //System.out.println("phone: " + xmlPath.getLong("List.item[0].phone"));

        assertEquals(1, xmlPath.getInt("list.item[0].id"));
        assertEquals("Meade", xmlPath.getString("list.item[0].name"));
        assertEquals("Male", xmlPath.getString("list.item[0].gender"));
        assertEquals(9994128233L, xmlPath.getLong("list.item[0].phone"));

        // Can you get me all the names?
        List<String> allNames = xmlPath.getList("list.item.name");
        for (int i = 0; i < allNames.size(); i++) {
            System.out.println("All Names = " + allNames.get(i));
        }
        System.out.println("Count: " + allNames.size());
    }
}
