package com.cydeo.day02;

import com.cydeo.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class P05_SpartanTest extends SpartanTestBase {

    /**
     * - Send a request to GET /spartans/search
     * - Query Parameters values are
     *     - gender —> Female
     *     - nameContains —> f
     * - Log Everything
     * - Verify followings
     *       - Status Code is 200
     *       - ContentType is application/json
     *       - Total Element 4
     *       - jsonArray size hasSize 4
     *       - Names hasItem "Alfy"
     *       - Every gender is Female
     */

    @Test
    public void searchSpartans() {

        given().log().all()
                .queryParam("gender","Female")
                .queryParam("nameContains","f").
        when().get("/spartans/search").prettyPeek().
        then()
                .log().ifValidationFails() // if any validation fails in then() by using HamCrest it will log detail
                .statusCode(200)
                .contentType(ContentType.JSON.toString())
                .body("totalElement",is(4))
                .body("content",hasSize(4))
                .body("content.name",hasItem("Alfy"))
                .body("content.gender",everyItem(is("Female")));




        }
    }

