import com.automation.remarks.junit5.Video;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.WebDriverRunner;
import helpers.RunnerExtension;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.support.Color;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

@Tag("all")
@Tag("selenide")
@DisplayName("Пример сюита Selenide")
@ExtendWith(RunnerExtension.class)
class SelenideTest {

    @Video
    @Test
    @DisplayName("Проверить тексты ссылок в шапке страницы")
    @Description("Адаптивная верстка сайта может привести к исчезновению ссылок на маленьком экране")
    void headerLinks() {
        open("");
        ElementsCollection links = $$(".Header-menu .Menu-item");
        links.shouldHave(size(6));
        links.shouldHave(texts("НОВОСТИ", "ИСТОРИИ", "РАЗБОР", "ИГРЫ", "ШАПИТО", "ПОДКАСТЫ"));
    }

    @Video
    @Test
    @DisplayName("Переход на вкладку 'ИСТОРИИ'")
    void clickOnArticles() {
        open("");
        $(".Header-center a[data-to='articles']").click();
        Assertions.assertTrue(WebDriverRunner.url().contains("articles"), "Адресная строка содержит 'articles'");
    }

    @Video
    @Test
    @DisplayName("Поиск статей про бездомного разработчика")
    void search() {
        open("");
        $(".HeaderButton--search").click();
        $(".SearchLayer-input").setValue("Бездомный разработчик");
        $$(".SearchResults-item").shouldBe(sizeNotEqual(0));
    }

    @Video
    @Test
    @DisplayName("Поиск статей про автотесты")
    @Description("Специально роняем тест")
    void searchFail() {
        open("");
        $(".HeaderButton--search").click();
        $(".SearchLayer-input").setValue("Автоматические тесты");
        $$(".SearchResults-item").shouldBe(sizeGreaterThan(10));
    }

    @Video
    @Test
    @DisplayName("При открытии сайта активна вкладка новостей (коричневая ссылка)")
    void linkNewsIsActive() {
        open("");
        $(".Header-center a[data-to='news']").shouldHave(cssValue("color",  Color.fromString("#b88b58").asRgba()));
    }

    @Video
    @Test
    @DisplayName("При открытии страницы вкладка 'ИСТОРИИ' не активна (белая ссылка)")
    void linkIsActive() {
        open("");
        $(".Header-center a[data-to='articles']").shouldHave(cssValue("color",  Color.fromString("#fff;").asRgba()));
    }

}
