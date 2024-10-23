package guru.qa.tests;

import guru.qa.extensions.WithLogin;
import guru.qa.models.book.AddBookRequestModel;
import guru.qa.models.book.IsbnModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static guru.qa.tests.TestData.*;

public class BookStoreTests extends TestBase {

    @Test
    @WithLogin
    @DisplayName("Удаление книги")
    public void deleteBookInProfile() {
        bookApi.deleteAllBook(loginResponse);
        List<IsbnModel> isbnList = new ArrayList<>();
        IsbnModel isbn = new IsbnModel(TestData.testIsbn);
        isbnList.add(isbn);
        bookApi.addBook(loginResponse, new AddBookRequestModel(loginResponse.getUserId(), isbnList));
        profilePage.openProfilePage()
                .verifyUserName(userName)
                .verifyBookInTable(bookName)
                .deleteBook()
                .verifyBookNotInTable(testIsbn);
    }
}