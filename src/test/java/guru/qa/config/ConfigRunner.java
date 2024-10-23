package guru.qa.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${env}.properties"
})

public interface ConfigRunner extends Config {
    @Key("baseURl")
    @DefaultValue("https://demoqa.com")
    String getBaseUrl();

    @Key("baseURI")
    @DefaultValue("https://demoqa.com")
    String getBaseUri();

    @Key("browserName")
    @DefaultValue("CHROME")
    String getBrowserName();

    @Key("browserVersion")
    @DefaultValue("126.0")
    String getBrowserVersion();

    @Key("selenoidURL")
    String getSelenoidUrl();
}