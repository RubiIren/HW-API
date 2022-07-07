package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ReqresinTests {

    @Test
    void check404StatusTest() {
        given()
                .contentType(JSON)
                .when()
                .get("https://reqres.in/api/users/23")
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
                .get("https://reqres.in/api/users?page=2")
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
                .post("https://reqres.in/api/users")
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
                .post("https://reqres.in/api/login")
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
                .delete("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }

}

