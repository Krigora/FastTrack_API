package com.cydeo.day02;

import com.cydeo.utility.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class P03_JsonPath extends HrTestBase {

    /*
     Given
             accept type is application/json
     When
             user sends get request to /locations
     Then
             response status code must be 200
             content type equals to application/json
             get the second city with JsonPath
             get the last city with JsonPath
             get all country ids
             get all city where their country id is UK

    */

    @Test
    public void getLocations() {

        Response response = given().accept(ContentType.JSON).
                when().get("/locations");

        //response status code must be 200
        assertEquals(200,response.statusCode());

        //content type equals to application/json
        assertEquals(ContentType.JSON.toString(),response.contentType());

        JsonPath jsonPath = response.jsonPath();

        //get the second city with JsonPath
        System.out.println("jsonPath.getString(\"items[1].city\") = " + jsonPath.getString("items[1].city"));

        //get the last city with JsonPath
        System.out.println("jsonPath.getString(\"items[-1].city\") = " + jsonPath.getString("items[-1].city"));

        //get all country ids
        List<String> allCountryIDs = jsonPath.getList("items.country_id");
        System.out.println("allCountryIDs = " + allCountryIDs);

        //get all city where their country id is UK
        List<String> allCityUK = jsonPath.getList("items.findAll {it.country_id=='UK'}.city");
        System.out.println("allCityUK = " + allCityUK);
    }

   /*
    Given
             accept type is application/json
     When
             user sends get request to /employees
     Then
             response status code must be 200
             get me all employees first_name who is making salary more than 15000

    */


}
