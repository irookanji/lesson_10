import com.codeborne.selenide.WebDriverProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import static org.openqa.selenium.remote.BrowserType.CHROME;
import static org.openqa.selenium.remote.BrowserType.FIREFOX;

public class CustomizeWebDriverProvider implements WebDriverProvider {
    final WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class);

    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {
        desiredCapabilities.setVersion(config.browserVersion());

        switch (config.browserName()) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(getChromeOptions().merge(desiredCapabilities));
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver(getFirefoxOptions().merge(desiredCapabilities));
            default:
                throw new RuntimeException("Unknown browser: " + config.browserName());
        }
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions =
                new ChromeOptions()
                        .addArguments("--no-sandbox")
                        .addArguments("--disable-notifications")
                        .addArguments("--disable-infobars");
        return chromeOptions;
    }

    private FirefoxOptions getFirefoxOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions().setAcceptInsecureCerts(true);
        return firefoxOptions;
    }
}
