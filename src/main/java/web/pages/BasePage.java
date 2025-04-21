package web.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static helpers.constants.BaseConstants.TIME_TO_WAIT;

/**
 * Абстрактный базовый класс для всех Page Object классов.
 */
@Slf4j
abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIME_TO_WAIT));
        PageFactory.initElements(this.driver, this);
    }

    /**
     * Получает URL текущей открытой страницы в браузере.
     *
     * @return текущий URL.
     */
    public String getWindowUrl() {
        return this.driver.getCurrentUrl();
    }

    /**
     * Переход в категорию (меню слева) по имени
     */
    public BasePage goToCategory(String categoryName) {
        By category = By.xpath("//a[@href='" + categoryName + "']");
        wait.until(ExpectedConditions.elementToBeClickable(category));
        driver.findElement(category).click();
        return this;
    }
}
