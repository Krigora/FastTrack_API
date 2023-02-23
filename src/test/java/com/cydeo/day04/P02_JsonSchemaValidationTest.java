package com.cydeo.day04;

import com.cydeo.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class P02_JsonSchemaValidationTest extends SpartanTestBase {

    @Test
    public void validation1() {

        given().accept(ContentType.JSON)
                .pathParam("id",2).
        when().get("/spartans/{id}").prettyPeek().
        then().statusCode(200)
                .contentType(ContentType.JSON)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SingleSpartanSchema.json"));

        //  matchesJsonSchemaInClasspath --> will check resources folder to see file

    }


    @Test
    public void validation2() {

        given().accept(ContentType.JSON)
                .pathParam("id",2).
                when().get("/spartans/{id}").prettyPeek().
                then().statusCode(200)
                .contentType(ContentType.JSON)
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/SingleSpartanSchema.json")));


    }
}
