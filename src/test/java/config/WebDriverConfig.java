package config;

import org.aeonbits.owner.Config;

import java.net.URL;

@Config.Sources("classpath:${env}.properties")
public interface WebDriverConfig extends Config {

    @Key("base.url")
    @DefaultValue("https://demoqa.com/")
    String getBaseUrl();

    @Key("browser.name")
    @DefaultValue("CHROME")
    String getBrowser();

    @Key("browser.version")
    @DefaultValue("126.0")
    String getBrowserVersion();

    @Key("remote.url")
    URL getRemoteUrl();

    @Key("remote.WebDriver")
    String getRemoteWebDriver();

}