package helpers;

import api.AuthApi;
import components.CookiePopup;
import models.LoginResponse;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class SessionExtension implements BeforeEachCallback {
    @Override
    public void beforeEach(ExtensionContext context) {
        open("/favicon.ico");

        LoginResponse authResponse = AuthApi.authResponse();
        getWebDriver().manage().addCookie(new Cookie("userID", authResponse.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("expires", authResponse.getExpires()));
        getWebDriver().manage().addCookie(new Cookie("token", authResponse.getToken()));

    }


}
