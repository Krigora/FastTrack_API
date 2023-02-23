package com.cydeo.day04;


import com.cydeo.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class P04_MockAPI extends SpartanTestBase {

    // https://357b1cc9-ab4b-426e-9d0f-4732b45069cb.mock.pstmn.io

    /*
    @BeforeAll
    public static void init(){
        baseURI="https://357b1cc9-ab4b-426e-9d0f-4732b45069cb.mock.pstmn.io";
    }
     */

    @Test
    public void test1() {

        given().log().uri().accept(ContentType.JSON).
        when().get("/spartans").prettyPeek().
        then().statusCode(200)
                .contentType(ContentType.JSON)
                .header("Transfer-Encoding",notNullValue())
                .body("id",everyItem(notNullValue()))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("AllSpartansSchema.json"));
    }
}
