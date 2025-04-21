package web;

import helpers.BrowserType;
import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Objects;

import static helpers.constants.BaseConstants.DASHBOARD_URL;
import static helpers.constants.BaseConstants.WIDGET_OVERALL_STATISTICS;

@Tag("Web")
@DisplayName("Создание нового Widget")
@TmsLink("testrail")
@Link("docs")
public class CreateNewWidgetTest extends BaseTest {

    @ParameterizedTest(name = "[{index}] Browser: {0}")
    @EnumSource(BrowserType.class)
    @DisplayName("Проверка создания и добавления нового Widget")
    @Description("""
            Войти в систему Report Portal (логин: default; пароль: 1q2w3e).
            Перейти на существующий Dashboard.
            Добавить новый Widget типа "Task Progress".
            Проверить, что Widget успешно добавлен и отображается на Dashboard.
            """)
    @TmsLink("link")
    @Owner("кто-то")
    public void createNewWidget(BrowserType browserType) {
        setUp(browserType);
        loginPageSteps
                .auth()
                .gotoCategory(DASHBOARD_URL);

        dashboardPageSteps.addNewWidgetToFirstExistDashboard()
                .selectWidgetByName(WIDGET_OVERALL_STATISTICS)
                .selectFirstFilterInWidget()
                .LastStepAddingWidget();
    }
}
