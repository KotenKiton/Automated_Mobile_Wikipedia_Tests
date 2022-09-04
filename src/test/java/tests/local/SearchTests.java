package tests.local;

import com.codeborne.selenide.CollectionCondition;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Tag("android")
public class SearchTests extends TestBase {
    @Test
    @DisplayName("Проверка кнопки поиск")
    void searchTest() {
        switchTo().alert().accept();//Only for local tests.//For Browserstack need commit it

        back();
        step("Type search", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/search_container")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });
        step("Verify content found", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(CollectionCondition.sizeGreaterThan(0)));

    }

    @Test
    @DisplayName("Задание №11-Онбординг")
    void onboardingCheckTest() {
        switchTo().alert().accept();//Only for local tests.//For Browserstack need commit it

        $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
                .shouldHave(text("The Free Encyclopedia …in over 300 languages"));

        $(AppiumBy.xpath("//android.widget.LinearLayout[2]")).click();
        $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
                .shouldHave(text("New ways to explore"));

        $(AppiumBy.id("org.wikipedia.alpha:id/secondaryTextView"))
                .shouldHave(text("Dive down the Wikipedia rabbit hole " +
                        "with a constantly updating Explore feed. " +
                        "Customize the feed to your interests – whether it’s" +
                        " learning about historical events On this day, " +
                        "or rolling the dice with Random."));

        $(AppiumBy.xpath("//android.widget.LinearLayout[3]")).click();
        $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
                .shouldHave(text("Reading lists with sync"));

        $(AppiumBy.xpath("//android.widget.LinearLayout[4]")).click();
        $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
                .shouldHave(text("Send anonymous data"));
    }

    @Test
    @DisplayName("Проверка запуска экрана Saved")
    void savedScreenTest() {
        switchTo().alert().accept();//Only for local tests.//For Browserstack need commit it
        back();
        step("Open menu Saved", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/nav_tab_reading_lists")).click());
        step("Verify Menu contains Donate item", () ->
                $(AppiumBy.xpath("/hierarchy/android.widget" +
                        ".FrameLayout/android.widget.LinearLayout/" +
                        "android.widget.FrameLayout/android.widget" +
                        ".FrameLayout/android.widget" +
                        ".FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.TextView"))
                        .shouldHave(text("Saved")));
    }

    @Test
    @DisplayName("Проверка экрана Log in/Join Wiki")
    void savedLogInTest() {
        switchTo().alert().accept();//Only for local tests.//For Browserstack need commit it
        back();
        step("Open menu", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/nav_tab_reading_lists")).click());
        $(AppiumBy.id("org.wikipedia.alpha:id/positiveButton")).click();
        step("Verify Menu contains Login item", () ->
                $(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android" +
                        ".widget.LinearLayout/android.widget.FrameLayout/" +
                        "android.view.ViewGroup/android.widget.FrameLayout[1]" +
                        "/android.view.ViewGroup/android.widget.TextView"))
                        .shouldHave(text("Create an account")));
    }
}