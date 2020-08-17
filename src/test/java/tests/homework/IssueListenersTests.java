package tests.homework;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Feature;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;


@Feature("Работа с задачами")
class IssueListenersTests {

    private static final String BASE_URL = "https://github.com";
    private static final String LOGIN = "photoeb";
    private static final String PASSWORD = "";


    @BeforeAll
    static void initlogger() {
        SelenideLogger.addListener("allure", new AllureSelenide()
                .savePageSource(true)
                .screenshots(true));
    }

    @Test
    @DisplayName("Пользователь должен иметь возможность создать новую задачу")
    void createIssueTest() {
    open(BASE_URL);

    $(byName("q")).setValue("photoeb").pressEnter();
    $(byText("Users")).click();
    $(".user-list .text-gray").click();
    $(byText("Lesson4Allure")).click();
    $(byText("Issues")).click();
    $(byText("New issue")).click();
    $(".px-4" ).$(byText("Sign in")).click();
    $(byId("login_field")).setValue(LOGIN);
    $(byId("password")).setValue(PASSWORD).pressEnter();
    $(byId("issue_title")).setValue("Hello!");
    $(byText("Submit new issue")).click();

    $(".js-issue-title").shouldHave(text("Hello!"));
    //.js-issue-assignees
    //.js-issue-labels
    }
}
