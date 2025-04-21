package web.steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import web.pages.LoginPage;

/**
 * Шаги для отображения в аллюр отчёте (LoginPage)
 */
public class LoginPageSteps {

    protected WebDriver driver;
    protected LoginPage loginPage;

    public LoginPageSteps(WebDriver driver) {
        this.driver = driver;
        this.loginPage = new LoginPage(driver);
    }


    @Step("Авторизация пользователя")
    public LoginPageSteps auth() {
        loginPage.authentication();
        return this;
    }

    @Step("переход в категорию {category}")
    public LoginPageSteps gotoCategory(String category) {
        loginPage.goToCategory(category);
        return this;
    }
}
