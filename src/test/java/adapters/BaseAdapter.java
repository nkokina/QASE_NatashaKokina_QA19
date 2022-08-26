package adapters;

import static io.restassured.RestAssured.given;

public abstract class BaseAdapter {
    protected final static String BASE_URL = "https://api.qase.io/v1/";
    private final static String ACCESS_TOKEN = "b41ed6ab16b7118c733f2132f72f52edbb79b2ba";

    public String get(String endpoint, int statusCode) {
        return given()
                .header("Token", ACCESS_TOKEN)
                .header("Accept", "application/json")
                .when()
                .log().all()
                .get(BASE_URL + endpoint)
                .then()
                .log().all()
                .statusCode(statusCode).extract().body().asString();
    }

    public String post(String endpoint, int statusCode, String requestBody) {
        return given()
                .header("Token", ACCESS_TOKEN)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .log().all()
                .post(BASE_URL + endpoint)
                .then()
                .log().all()
                .statusCode(statusCode).
                extract().body().asString();
    }

    public String delete(String endpoint, int statusCode) {
        return given()
                .header("Accept", "application/json")
                .header("Token", ACCESS_TOKEN)
                .delete(BASE_URL + endpoint)
                .then()
                .log().all()
                .statusCode(statusCode)
                .extract().body().asString();
    }

    public String patch(String endpoint, int statusCode, String requestBody) {
        return given()
                .header("Token", ACCESS_TOKEN)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .log().all()
                .patch(BASE_URL + endpoint)
                .then()
                .log().all()
                .statusCode(statusCode)
                .extract().body().asString();
    }
}