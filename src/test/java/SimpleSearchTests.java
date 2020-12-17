import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SimpleSearchTests {

    final WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class);

    @BeforeAll
    void setup (){
        Configuration.browser = CustomizeWebDriverProvider.class.getName();
        Configuration.startMaximized = true;
        if (System.getProperty("env").equals("remote")){
            Configuration.remote = config.webDriverRemoteURL();
        }
    }

    @Test
    void selenideSearchTest() {
        // Открыть раузер с поисковиком
        open(config.baseUrl());

        // Ввести Selenide в поиск
        $(byName("q")).setValue("Selenide").pressEnter();

        // Проверить, что Selenide появился в результатах поиска
        $("html").shouldHave(Condition.text("selenide.org"));
    }
}
