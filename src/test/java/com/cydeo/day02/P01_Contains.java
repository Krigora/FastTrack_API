package com.cydeo.day02;

import com.cydeo.utility.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class P01_Contains extends HrTestBase {

    /*
       Given
                accept type is application/json
        When
                user sends get request to /regions/2
        Then
                response status code must be 200
                content type equals to application/json
                response body contains Americas

     */


    @Test
    public void getSingleRegion() {

        Response response = given().accept(ContentType.JSON)
                .and()  // Syntactic sugar just to increase readability of code
                .pathParam("abc", 2).
                when().get("/regions/{abc}").prettyPeek();

        // response status code must be 200
        assertEquals(200,response.statusCode());

        // content type equals to application/json
        assertEquals("application/json",response.contentType());

        // response body contains Americas
        assertTrue(response.asString().contains("Americas"));

    }
}
