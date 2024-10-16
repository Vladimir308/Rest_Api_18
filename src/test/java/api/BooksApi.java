package api;

import models.*;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static specs.Specification.*;

public class BooksApi {

    public static void deleteBooks(String token, String userId) {

        given(request)
                .header("Authorization", "Bearer " + token)
                .queryParam("UserId", userId)
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .log().all()
                .spec(successDeleteBooksResponse204);
    }

    public static AddListOfBooksResponse addBooks(String token, String userId) {
        ArrayList books = new ArrayList<>();
        books.add(new ListOfIsbns("9781449325862"));
        AddListOfBooksRequest dataBook = new AddListOfBooksRequest();
        dataBook.setCollectionOfIsbns(books);
        dataBook.setUserId(userId);

        return given(request)
                .header("Authorization", "Bearer " + token)
                .body(dataBook)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .log().all()
                .spec(successAddBooksResponse201)
                .extract().as(AddListOfBooksResponse.class);
    }

}
