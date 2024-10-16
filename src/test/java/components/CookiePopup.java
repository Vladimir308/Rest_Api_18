package components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CookiePopup {
    SelenideElement bannerRoot = $(".fc-consent-root");

    public void consentCookiePopup() {
        if (bannerRoot.isDisplayed()) {
            bannerRoot.$(byText("Consent")).click();
        }
    }
}
