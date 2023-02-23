package com.cydeo.utility;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public abstract class SpartanTestBase {

    @BeforeAll
    public static void init(){
        baseURI="http://3.91.96.199:8000";
        basePath="/api";
    }

    @AfterAll
    public static void destroy(){

        reset();
    }


}
