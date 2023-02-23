package com.cydeo.day04;

import com.cydeo.utility.SpartanAuthTestBase;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class P03_SpartanSpecTest extends SpartanAuthTestBase {

    @Test
    public void test1() {

        given().spec(reqSpec("editor","editor")).
                when().get("/spartans").
                then().spec(resSpec(200));

    }

    @Test
    public void test2() {


        given().spec(reqSpec("user","user"))
                .pathParam("id", 2).
                when().get("/spartans/{id}").
                then().spec(resSpec(200));

    }
    /**
     *  Create GET_RBAC.csv
     *   username,password,id,statuscode
     *    admin,admin,3,200
     *    editor,editor,3,200
     *    user,user,3,200
     *    guest,guest,3,401
     *
     *  Create a parameterized test to check RBAC for GET method
     *
     *
     */



    @Test
    public void test3() {


        given().spec(reqSpec("user","user"))
                .pathParam("id", 2).
                when().delete("/spartans/{id}").
                then().spec(resSpec(403));


    }



}
