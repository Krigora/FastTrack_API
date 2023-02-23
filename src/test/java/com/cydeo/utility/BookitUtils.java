package com.cydeo.utility;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class BookitUtils {

    public static String getToken(String email,String password){

        String accessToken = given().accept(ContentType.JSON)
                .queryParam("email",email).
                queryParam("password",password).
                when().get("/sign").prettyPeek().
                then().statusCode(200)
                .extract().jsonPath().getString("accessToken");

        System.out.println("accessToken = " + accessToken);


        return "Bearer "+accessToken;
    }
}
