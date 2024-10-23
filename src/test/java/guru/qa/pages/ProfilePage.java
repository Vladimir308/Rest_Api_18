package guru.qa.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ProfilePage {

    private final SelenideElement verifyUserName = $("#userName-value");
    private final SelenideElement verifyBookInTable = $(".ReactTable");
    private final SelenideElement clickDeleteIcon = $("#delete-record-undefined");
    private final SelenideElement modalWindow = $(".modal-content");
    private final SelenideElement modalWindowButtonOk = modalWindow.$("#closeSmallModal-ok");


    @Step("Открываем Профиль")
    public ProfilePage openProfilePage() {
        open("/profile");
        removeBanner();
        return this;
    }

    @Step("Проверям имя пользователя")
    public ProfilePage verifyUserName(String userName) {
        verifyUserName.shouldHave(text(userName));
        return this;
    }

    @Step("Проверям название книги")
    public ProfilePage verifyBookInTable(String bookName) {
        verifyBookInTable.shouldHave(text(bookName));
        return this;
    }

    @Step("Удаляем книгу из профиля")
    public ProfilePage deleteBook() {
        clickDeleteIcon.click();
        modalWindow
                .shouldBe(visible);
        modalWindowButtonOk
                .shouldBe(visible)
                .click();
        return this;
    }

    @Step("Проверям отсутсвие книги на экране профиля")
    public ProfilePage verifyBookNotInTable(String isbn) {
        verifyBookInTable.
                $(".rt-noData").shouldBe(visible);
        $(".rt-noData").shouldHave(text("No rows found"));
        return this;
    }

    private void removeBanner() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("document.getElementById('Ad.Plus-970x250-1').remove()");
        executeJavaScript("$('footer').remove()");
    }
}