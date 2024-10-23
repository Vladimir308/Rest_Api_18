package guru.qa.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import guru.qa.api.BookApi;
import guru.qa.api.UserApi;
import guru.qa.config.RunConfig;
import guru.qa.helpers.Attach;
import guru.qa.models.login.LoginRequestModel;
import guru.qa.models.login.LoginResponseModel;
import guru.qa.pages.ProfilePage;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static guru.qa.tests.TestData.userName;
import static guru.qa.tests.TestData.userPassword;

public class TestBase {
    RunConfig runConfig = new RunConfig();
    ProfilePage profilePage = new ProfilePage();
    UserApi userApi = new UserApi();
    BookApi bookApi = new BookApi();
    LoginRequestModel loginRequest = new LoginRequestModel(userName, userPassword);
    LoginResponseModel loginResponse = userApi.login(loginRequest);

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void afterEach() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }
}