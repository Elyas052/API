package io.loopcamp.utils;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public class MinionSecureTestBase {
    @BeforeAll  // JUnite - @Before
    public static void setUp () {
        // Since we are doing static import from RestAssured, we can use baseURI, which helps us for GET request concatenation in our test
        baseURI = ConfigurationReader.getProperty("minion.secure.api.url");
    }
}
