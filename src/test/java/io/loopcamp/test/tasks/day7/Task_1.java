package io.loopcamp.test.tasks.day7;

public class Task_1 {
}


// 1) POST a region then do validations. Please use Map or POJO class, or JsonPath
/**
 * given accept is json
 * and content type is json
 * When I send post request to "/regions/"
 * With json:
 * {
 *     "region_id":100,
 *     "region_name":"Test Region"
 * }
 * Then status code is 201
 * content type is json
 * region_id is 100
 * region_name is Test Region
 */

/**
 * given accept is json
 * When I send post request to "/regions/100"
 * Then status code is 200
 * content type is json
 * region_id is 100
 * region_name is Test Region
 */