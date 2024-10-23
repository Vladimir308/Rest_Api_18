package guru.qa.config;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Configuration.remote;

public class RunConfig {

    private ConfigRunner config;

    public RunConfig() {
        this.config = ConfigFactory.create(ConfigRunner.class, System.getProperties());
        createWebDriver();
    }

    private void createWebDriver() {
        Configuration.baseUrl = config.getBaseUrl();
        RestAssured.baseURI = config.getBaseUri();
        switch (config.getBrowserName().toLowerCase()) {
            case "chrome": {
                Configuration.browser = "chrome";
                break;
            }
            case "firefox": {
                Configuration.browser = "firefox";
                break;
            }
            default:
                throw new RuntimeException("No such driver: + " + config.getBrowserName());
        }

        Configuration.browserVersion = config.getBrowserVersion();
        String selenoidURL = config.getSelenoidUrl();
        if (selenoidURL != null) {
            remote = selenoidURL;
            Configuration.pageLoadStrategy = "eager";

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true));

            Configuration.browserCapabilities = capabilities;
        }
    }
}