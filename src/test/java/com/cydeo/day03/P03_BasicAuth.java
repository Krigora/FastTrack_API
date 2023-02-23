package com.cydeo.day03;

import com.cydeo.utility.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class P03_BasicAuth extends SpartanAuthTestBase {

    /**
     *  Role Based Access Control--> RBAC
     *
     *  ADMIN     GET  POST PUT PATCH DELETE
     *  EDITOR    GET  POST PUT PATCH 403
     *  USER      GET  403  403  403  403
     *  GUEST     401  401  401  401  401
     *
     *
     *
     *
     */

    @Test
    public void negativeTest() {

        given().accept(ContentType.JSON)
                .pathParam("id",5).
        when().get("/spartans/{id}").prettyPeek().
        then().statusCode(401) // UnauthorizedUser
                .body("error",is("Unauthorized"));

    }

    @Test
    public  void getSpartanAsUser() {

        given().log().all().accept(ContentType.JSON)
                .pathParam("id",5)
                .auth().basic("user","user").
                when().get("/spartans/{id}").prettyPeek().
                then().statusCode(200);


    }

    @Test
    public void deleteSpartan() {

        given().accept(ContentType.JSON)
                .pathParam("id",5)
                .auth().basic("editor","editor").
        when().delete("/spartans/{id}").prettyPeek().
        then().statusCode(403)
                .body("error",is("Forbidden"));

    }

    /**
     *
     *   Create one DDT Test to do RBAC
     *
     *   @ParameterizedTest
     *   @CSVFileSource
     *  public void GETSpartans(String username,String password,int statusCode){
     *
     *      given().log().all().accept(ContentType.JSON)
     *                 .pathParam("id",5)
     *                 .auth().basic(username,password).
     *                 when().get("/spartans/{id}").prettyPeek().
     *                 then().statusCode(statusCode);
     *
     *  }
     *
     *  Create one csv file to put under resources as GETSpartans.csv
     *      admin,admin,200
     *      editor,editor,200
     *      user,user,200
     *      guest,guest,401
     *
     *
     */
}
