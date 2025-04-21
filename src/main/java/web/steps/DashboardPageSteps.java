package web.steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import web.pages.DashboardPage;

/**
 * Шаги для отображения в аллюр отчёте (DashboardPage)
 */
public class DashboardPageSteps {

    protected WebDriver driver;
    protected DashboardPage dashboardPage;

    public DashboardPageSteps(WebDriver driver) {
        this.driver = driver;
        this.dashboardPage = new DashboardPage(driver);
    }

    @Step("добавление виджета в существующий дашборд")
    public DashboardPageSteps addNewWidgetToFirstExistDashboard() {
        dashboardPage.addNewWidgetToFirstExistDashboard();
        return this;
    }

    @Step("выбрать виджет {widgetName}")
    public DashboardPageSteps selectWidgetByName(String widgetName) {
        dashboardPage.selectWidgetByNameFirstStep(widgetName);
        return this;
    }


    @Step("выбор первого доступного фильтра в меню настроек виджета")
    public DashboardPageSteps selectFirstFilterInWidget() {
        dashboardPage.selectFirstFilterInWidgetSecondStep();
        return this;
    }

    @Step("завершение добавления виджета")
    public DashboardPageSteps LastStepAddingWidget() {
        dashboardPage.LastStepAddingWidget();
        return this;
    }
}
