package steps;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class IssueSteps {

    private static final String BASE_URL = "https://github.com";
    private static final String LOGIN = "photoeb";
    private static final String PASSWORD = "";

    @Step("Открываем главную страницу")
    public void openMainPage() {
        open(BASE_URL);
    }

    @Step("Ищем пользователя, выводим результаты поиска по пользователям и кликаем по найденному пользователю")
    public void searchForUsername() {
        $(byName("q")).setValue("photoeb").pressEnter();
        $(byText("Users")).click();
        $(".user-list .text-gray").click();
    }

    @Step("Переходим на страницу репозитория 'Lesson4Allure'")
    public void openRepository() {$(byText("Lesson4Allure")).click(); }

    @Step("Открываем страницу с задачами")
    public void openIssuesPage() { $(byText("Issues")).click(); }

    @Step("Нажимаем кнопку 'Создать новую задачу'")
    public void clickNewIssue() { $(byText("New issue")).click(); }

    @Step("Выполняем авторизацию")
    public void performAuthorization() {
        $(".px-4" ).$(byText("Sign in")).click();
        $("#login_field").setValue(LOGIN);
        $("#password").setValue(PASSWORD).pressEnter();
    }

    @Step("Создаём новую задачу с заданным названием")
    public void createNewIssue() {
        $("#issue_title").setValue("Hello!");
        $(byText("Submit new issue")).click();
    }

    @Step("Проверяем результат создания задачи по заданному названию")
    public void shouldHaveIssueWithTitle() {
        $(".js-issue-title").shouldHave(text("Hello!"));
    }
}
