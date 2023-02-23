package com.cydeo.day02;

import com.cydeo.pojo.Search;
import com.cydeo.pojo.Spartan;
import com.cydeo.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class P07_JsonToPOJO extends SpartanTestBase {
    @Test
    public void getSingleSpartan() {
        Response response =
                given().accept(ContentType.JSON)
                        .pathParam("id", 10).
                        when().get("/spartans/{id}").
                        then()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .extract().response();

        /*
        {
            "id": 10,
            "name": "Lorenza",
            "gender": "Female",
            "phone": 3312820936
        }
         */

        // First Approach --> Response
        System.out.println("--------- First Approach --> Response.as() --------- ");

            Spartan spartan = response.as(Spartan.class);
            System.out.println("spartan = " + spartan);
            System.out.println(spartan.getId());
            System.out.println(spartan.getName());



        // Second Approach --> JsonPath
        System.out.println("--------- Second Approach --> JsonPath --------- ");

            JsonPath jsonPath = response.jsonPath();
            Spartan sparta = jsonPath.getObject("", Spartan.class);
            System.out.println("sparta = " + sparta);
            System.out.println(sparta.getId());
            System.out.println(sparta.getName());


    }

    @Test
    public void searchSpartans() {

        Response response = given().accept(ContentType.JSON)
                .queryParam("nameContains", "f")
                .queryParam("gender", "Female").
                when().get("/spartans/search").
                then()
                .statusCode(200)
                .contentType(ContentType.JSON.toString())
                .extract().response();

        // First Approach --> Response
        System.out.println("--------- GET ME FIRST SPARTAN  --> Response.as() --------- ");

           //  response.as() --> We are not gonna use response.as() method to get partial of resposne as POJO class
           //  It does not have path parameter to do it


        // Second Approach --> JsonPath
        System.out.println("--------- GET ME FIRST SPARTAN--> JsonPath --------- ");
        JsonPath jsonPath = response.jsonPath();
        Spartan spartan = jsonPath.getObject("content[0]", Spartan.class);
             System.out.println(spartan);
             System.out.println(spartan.getName());

    }

    @Test
    public void searchSpartanPOJO() {
        Response response = given().accept(ContentType.JSON)
                .queryParam("nameContains", "f")
                .queryParam("gender", "Female").
                when().get("/spartans/search").
                then()
                .statusCode(200)
                .contentType(ContentType.JSON.toString())
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        Search search = jsonPath.getObject("", Search.class);

        // print out total Element
        System.out.println(search.getTotalElement());

        // print how many spartan we have
        /*
            List<Spartan> content = search.getContent();
            System.out.println(search.getContent().size());

            // print first spartan info
            System.out.println("content.get(0) = " + content.get(0));
            // print first spartan name
            Spartan spartan = content.get(0);
            System.out.println(spartan.getName());
         */

        // print how many spartan we have
        List<Spartan> allSpartans = search.getAllSpartans();
        for (Spartan eachSpartan : allSpartans) {
            System.out.println(eachSpartan);
        }

        // print first spartan info
        System.out.println("allSpartans.get(0) = " + allSpartans.get(0));


    }
}
