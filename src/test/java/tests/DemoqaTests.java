package tests;

import api.AuthApi;
import api.BooksApi;
import components.CookiePopup;
import helpers.WithLogin;
import models.LoginResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class DemoqaTests extends TestBase {
    @Test
    @WithLogin
    @DisplayName("Удаление книги из коллекции пользователя")
    void DeleteProductFromCollectionTest () {
        CookiePopup cookie = new CookiePopup();
        LoginResponse authResponse = step("Авторизуем пользователя", () ->
                AuthApi.authResponse());

        step("Очищаем список коллекции пользователя", () ->
                BooksApi.deleteBooks(authResponse.getToken(), authResponse.getUserId()));

        step("Добавляем книгу в коллекцию", () ->
                BooksApi.addBooks(authResponse.getToken(), authResponse.getUserId())
        );

        step("Принимаем соглашение о куках", () ->
                cookie.consentCookiePopup()
        );

        step("Открываем страницу Profile", () ->
                open("/profile")
        );

        step("Нажимаем на кнопку удаления книги из коллекции", () ->
                $("#delete-record-undefined").click()
        );

        step("Подтверждаем удаление книги", () ->
                $("#closeSmallModal-ok").click()
        );

        step("Закрываем информационное окно", () ->
                switchTo().alert().accept()
        );

        step("Проверяем удаление книги из коллекции", () ->
                $(".rt-noData").shouldHave(text("No rows found"))
        );

    }

}
