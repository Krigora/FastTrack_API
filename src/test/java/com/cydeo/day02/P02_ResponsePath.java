package com.cydeo.day02;

import com.cydeo.utility.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class P02_ResponsePath extends HrTestBase {

    /*
       Given
                accept type is application/json
        When
                user sends get request to /regions/2
        Then
                response status code must be 200
                region_name is Americas
                region_id is 2
                print out all the links

     */

    @Test
    public void getSingleRegion() {

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 2).
                when().get("/regions/{id}");

        //response status code must be 200
        assertEquals(200,response.statusCode());

        //region_name is Americas
        assertEquals("Americas",response.path("region_name"));
        //region_id is 2
        assertEquals(2, (Integer) response.path("region_id"));

        //print out all the links
        List<Map<String,String>> links = response.path("links");
        System.out.println("------ PRINT EACH LINK INFO ------");
        for (Map<String, String> eachLink : links) {
            System.out.println(eachLink.get("rel"));
            System.out.println(eachLink.get("href"));
            System.out.println("-----------------------");
        }

        // Get me all href info
        List<String> allHref = response.path("links.href");
        System.out.println("allHref = " + allHref);

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/regions.csv",numLinesToSkip = 1)
    public void parameterizedTest(int id,String regionName) {

        System.out.println(id+"---> "+regionName);

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", id).
                when().get("/regions/{id}");

        //response status code must be 200
        assertEquals(200,response.statusCode());

        //region_name
        assertEquals(regionName,response.path("region_name"));
        //region_id
        assertEquals(id, (Integer) response.path("region_id"));

    }
}
