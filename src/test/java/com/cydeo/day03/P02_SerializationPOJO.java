package com.cydeo.day03;

import com.cydeo.pojo.Spartan;
import com.cydeo.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class P02_SerializationPOJO extends SpartanTestBase {

    @Test
    public void postSpartan() {

        Spartan spartan=new Spartan();
        spartan.setName("POST POJO");
        spartan.setGender("Male");
        spartan.setPhone(1231231231l);

        System.out.println(spartan);  // it will show id field as well

        given().log().body().accept(ContentType.JSON)  // print body that we are sending to API
                .contentType(ContentType.JSON)
                .body(spartan).
        when().post("/spartans").
        then().statusCode(201);


    }
}
