package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ReqresinTests {
    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "https://reqres.in/";
    }

    @Test
    void check404StatusTest() {
        given()
                .contentType(JSON)
                .when()
                .get("api/users/23")
                .then()
                .log().status()
                .log().body()
                .statusCode(404);
    }

    @Test
    void checkPageTest() {
        given()
                .contentType(JSON)
                .when()
                .get("api/users?page=2")
                .then()
                .log().status()
                .log().body()
                .body("page", is(2));
    }

    @Test
    void createTest() {
        String body = "{ \"name\": \"morpheus\"," +
                " \"job\": \"leader\" }";

        given()
                .log().uri()
                .log().body()
                .body(body)
                .contentType(JSON)
                .when()
                .post("api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"));
    }

    @Test
    void loginTest() {
        String body = "{ \"email\": \"eve.holt@reqres.in\"," +
                " \"password\": \"cityslicka\" }";

        given()
                .log().uri()
                .log().body()
                .body(body)
                .contentType(JSON)
                .when()
                .post("api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void deleteTest() {
        given()
                .contentType(JSON)
                .when()
                .delete("api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }

}

