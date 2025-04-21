package web.pages;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Objects;

import static helpers.constants.BaseConstants.DASHBOARD_URL;

/**
 * Страница дашбордов (Dashboards).
 */
@Slf4j
public class DashboardPage extends BasePage {

    @FindBy(css = ".addDashboardButton__add-dashboard-btn--acseh")
    WebElement addNewDashboardButton;
    @FindBy(xpath = "//input[@placeholder='Search by name']")
    WebElement searchField;
    @FindBy(xpath = "//div[@class='gridRow__grid-row-wrapper--xj8DG'][1]")
    WebElement firstDashboardElement;
    @FindBy(xpath = "//div[contains(@class, 'gridRow__grid-row--X9wIq')]")
    List<WebElement> rowsDashboards;
    @FindBy(xpath = "//span[text()='Add new widget']/preceding::i[1]")
    WebElement addNewWidgetButton;
    @FindBy(xpath = "//span[text()='Next step']/ancestor::button")
    WebElement nextStepButton;
    @FindBy(xpath = "//div[@class='filtersItem__filter-item--DHlV9'][1]")
    WebElement firstFilterFromConfigureWidgetList;
    @FindBy(xpath = "//div[contains(@class, 'modalField__modal-field')]//span[text()='Widget name']/following::input[contains(@class, 'input__input')]")
    WebElement widgetNameInput;
    @FindBy(xpath = "//button[contains(@class, 'bigButton__color-booger') and text()='Add']")
    WebElement addWidgetButtonLastStep;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Добавляет виджет в первый дашборд из списка.
     */
    public DashboardPage addNewWidgetToFirstExistDashboard() {
       log.info("Переходим в раздел дашбордов");
        Assertions.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains(DASHBOARD_URL));
       log.info("Добавляем виджет в первый дашборд.");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[contains(@class, 'gridRow__grid-row--X9wIq')]")
        ));
        WebElement firstRow = rowsDashboards.get(0);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
                firstRow
        );
        String itemText = firstRow.findElement(
                By.xpath(".//a[contains(@class, 'dashboardTable__name')]")
        ).getText();

        WebElement targetLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(String.format(
                        "//a[contains(@class, 'dashboardTable__name') and normalize-space()='%s']",
                        itemText
                ))
        ));
        targetLink.click();
        log.info("Переход на дашборд '{}'.", itemText);
        addNewWidget();
        return this;
    }

    /**
     * Открывает первый доступный дашборд и переходит к добавлению нового виджета.
     */
    public DashboardPage addNewWidget() {
        wait.until(ExpectedConditions.elementToBeClickable(addNewWidgetButton)).click();
        return this;
    }

    /**
     * Выбирает виджет по имени на первом шаге и нажимает "Следующий шаг".
     *
     * @param widgetName Имя виджета для выбора.
     */
    public DashboardPage selectWidgetByNameFirstStep(String widgetName) {
        log.info("Шаг 1: Выбор виджета '{}'.", widgetName);
        By widgetLocator = By.xpath(String.format(
                "//div[@class='widgetTypeItem__widget-type-item-name--WYizn' and text()='%s']",
                widgetName
        ));
        wait.until(ExpectedConditions.elementToBeClickable(widgetLocator)).click();
        nextStepButton.click();
        return this;
    }

    /**
     * Выбирает первый фильтр на втором шаге и нажимает "Следующий шаг".
     */
    public DashboardPage selectFirstFilterInWidgetSecondStep() {
        log.info("Шаг 2: Выбор первого фильтра.");
        wait.until(ExpectedConditions.elementToBeClickable(firstFilterFromConfigureWidgetList)).click();
        nextStepButton.click();
        return this;
    }

    /**
     * Выполняет последний шаг добавления виджета и проверяет создание нового виджета.
     */
    public DashboardPage LastStepAddingWidget() {
        log.info("Шаг 3: Завершение добавления виджета.");
        wait.until(ExpectedConditions.visibilityOf(widgetNameInput));

        String expectedWidgetName = widgetNameInput.getAttribute("value");

        wait.until(ExpectedConditions.elementToBeClickable(addWidgetButtonLastStep)).click();

        By createdWidgetLocator = By.xpath(
                String.format("//div[contains(@class, 'widget-name-block') and normalize-space(text())='%s']", expectedWidgetName)
        );

        WebElement createdWidget = wait.until(ExpectedConditions.visibilityOfElementLocated(createdWidgetLocator));

        String actualWidgetName = createdWidget.getText();
        log.info("Проверяем, что виджет создан");
        Assertions.assertEquals(expectedWidgetName, actualWidgetName,
                "Название виджета не совпадает после добавления");
        return this;
    }
}
