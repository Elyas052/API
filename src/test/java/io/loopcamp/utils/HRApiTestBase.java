package io.loopcamp.utils;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public class HRApiTestBase {

    @BeforeAll  // JUnite - @Before
    public static void setUp() {
        // Set up the base URI for the Minion API using the URL from the configuration properties
        baseURI = ConfigurationReader.getProperty("hr.api.url");

        // Since we are doing a static import from RestAssured, we can use baseURI
        // This helps us concatenate the base URI for GET requests in our test scenarios
    }
}
