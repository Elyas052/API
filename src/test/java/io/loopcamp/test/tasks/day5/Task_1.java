package io.loopcamp.test.tasks.day5;

public class Task_1 {


    /**
     * Zipcode Homework
     * Documentation: https://www.zippopotam.us/ Links to an external site.
     *
     * BASEURL: api.zippopotam.us Links to an external site.
     *
     *
     * NOTE:
     * 	When writing pojo, for json keys that contain space, you will need to add @JasonProperty annotation:
     * 	Ex:
     * 		        @Data
     *        public class ZipInfo {
     *            @JasonProperty("post code")
     * 			private String postCode;
     *
     *
     * Scenarios:
     *
     * Given except application/json
     * And path zipcode is 22031
     * When I send a GET request to /us endpoint
     * Then status code must be 200
     * And content type must be application/json
     * And Server header is cloudflare
     * And Report-To header exists
     * And body should contain following information
     *     post code is 22031
     *     country  is United States
     *     country abbreviation is US
     *     place name is Fairfax
     *     state is Virginia
     *     latitude is 38.8604
     *
     * ===========================
     *
     * Given except application/json
     * And path zipcode is 50000
     * When I send a GET request to /us endpoint
     * Then status code must be 404
     * And content type must be application/json
     *
     * ============================
     *
     * Given except application/json
     * And path state is va
     * And path city is Fairfax
     * When I send a GET request to /us endpoint
     * Then status code must be 200
     * And content type must be application/json
     * And payload should contain following information
     *     country abbreviation is US
     *     country  United States
     *     place name  Fairfax
     *     each place must contain fairfax as a value
     *     each post code must start with 22
     */
}
