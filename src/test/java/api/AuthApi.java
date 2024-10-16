package api;


import models.LoginRequest;
import models.LoginResponse;

import static data.UserData.PASSWORD;
import static data.UserData.USER_NAME;
import static io.restassured.RestAssured.given;
import static specs.Specification.*;

public class AuthApi {
    public static LoginResponse authResponse () {
        LoginRequest userData = new LoginRequest();
        userData.setUserName(USER_NAME);
        userData.setPassword(PASSWORD);

        return
                given(request)
                        .body(userData)
                        .when()
                        .post("/Account/v1/Login")
                        .then()
                        .log().all()
                        .spec(successLoginResponse200)
                        .extract().as(LoginResponse.class);
    }
}
