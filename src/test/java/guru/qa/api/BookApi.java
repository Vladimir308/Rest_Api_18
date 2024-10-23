package guru.qa.api;

import guru.qa.models.book.AddBookRequestModel;
import guru.qa.models.login.LoginResponseModel;

import static guru.qa.specs.Specs.*;
import static io.restassured.RestAssured.given;

public class BookApi {
    public void addBook(LoginResponseModel loginResponse, AddBookRequestModel addBookRequest) {
        given(requestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .body(addBookRequest)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(responseSpec201);
    }

    public void deleteAllBook(LoginResponseModel loginResponse) {
        given(requestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .queryParam("UserId", loginResponse.getUserId())
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(responseSpec204);
    }
}