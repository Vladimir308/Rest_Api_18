package guru.qa.api;

import guru.qa.models.login.LoginRequestModel;
import guru.qa.models.login.LoginResponseModel;

import static guru.qa.specs.Specs.*;
import static io.restassured.RestAssured.given;

public class UserApi {
    public LoginResponseModel login(LoginRequestModel loginRequest) {
        LoginResponseModel loginResponseModel = given(requestSpec)
                .body(loginRequest)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(responseSpec200)
                .extract().as(LoginResponseModel.class);
        return loginResponseModel;
    }
}