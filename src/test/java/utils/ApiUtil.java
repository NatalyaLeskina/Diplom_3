package utils;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.User;

import static io.restassured.RestAssured.given;

public class ApiUtil {

    private static String getAccessTokenFromAuth(String email, String password) {
        User user = new User(email, password);
        String accessToken = given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post("/api/auth/login")
                .then()
                .statusCode(200)
                .extract()
                .path("accessToken");
        return accessToken;
    }

    @Step("Удаление пользователя")
    public static void deleteUser(String email, String password) {
        String accessToken = getAccessTokenFromAuth(email, password);

        Response response = given()
                .header("Authorization", accessToken)
                .when()
                .delete("/api/auth/user");
    }
}
