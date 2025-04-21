package web.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static helpers.constants.BaseConstants.*;

/**
 * Страница входа (Login)
 */
@Slf4j
public class LoginPage extends BasePage {

    @FindBy(xpath = "//button[@type='submit']")
    WebElement submitButton;
    @FindBy(xpath = "//input[@name='login']")
    WebElement loginField;
    @FindBy(xpath = "//input[@name='password']")
    WebElement passwordField;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Открывает страницу входа
     */
    protected void openLoginPage() {
        log.info("Открытие страницы входа по URL: {}", LOGIN_URL);
        driver.get(LOGIN_URL);
    }

    public LoginPage authentication() {
       log.info("Вводим логин и пароль");
        openLoginPage();
        wait.until(ExpectedConditions.elementToBeClickable(loginField));

        loginField.clear();
        loginField.sendKeys(LOGIN);

        passwordField.clear();
        passwordField.sendKeys(PASSWORD);
        submitButton.click();

        return this;
    }
}
