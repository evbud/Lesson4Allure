package tests.homework;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.IssueSteps;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Feature("Работа с задачами")
public class IssueLambdasAndStepsTests {

    private static final String BASE_URL = "https://github.com";
    private static final String LOGIN = "evbud";
    private static final String PASSWORD = "";

    private final IssueSteps steps = new IssueSteps();

    @AfterEach
    public void logout() {
        $(".js-feature-preview-indicator-container").click();
        $(".dropdown-signout").click();
        //$(byText("Sign out")).click();
    }

    @Test
    @DisplayName("Пользователь должен иметь возможность создать новую задачу(лямбды)")
    void createIssueWithLambdasTest() {
        step("Открываем главную страницу", () ->
            open(BASE_URL));
        
        step("Ищем пользователя", () -> {
            $(byName("q")).setValue("evbud").pressEnter();
        });
        step("Выводим результаты поиска по пользователям и кликаем по найденному пользователю", () -> {
            $(byText("Users")).click();
            $(".user-list .text-gray").click();
        });
        step("Переходим на страницу репозитория 'Lesson4Allure'", () -> {
            $(byText("Lesson4Allure")).click();
        });
        step("Открываем страницу с задачами ", () -> {
            $(byText("Issues")).click();
        });
        step("Нажимаем кнопку 'Создать новую задачу'", () -> {
            $(byText("New issue")).click();
        });

        step("Выполняем авторизацию", () -> {
            $(".px-4" ).$(byText("Sign in")).click();
            $("#login_field").setValue(LOGIN);
            $("#password").setValue(PASSWORD).pressEnter();
        });

        step("Создаём новую задачу с заданным названием", () -> {
            $("#issue_title").setValue("Hello!");
            $(byText("Submit new issue")).click();
        });

        step("Проверяем результат создания задачи по заданному названию", () -> {
            $(".js-issue-title").shouldHave(text("Hello!"));
        });
        //.js-issue-assignees
        //.js-issue-labels
    }

    @Test
    @DisplayName("Пользователь должен иметь возможность создать новую задачу(шаги)")
    void createIssueWithSteps() {
        steps.openMainPage();
        
        steps.searchForUsername();
        steps.openRepository();
        steps.openIssuesPage();
        steps.clickNewIssue();
        steps.performAuthorization();
        steps.createNewIssue();
        
        steps.shouldHaveIssueWithTitle();
    }
}
