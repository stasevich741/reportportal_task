package api;

import api.services.DashboardService;
import helpers.constants.BaseConstants;
import helpers.listener.CustomTpl;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Map;

import static api.assertions.Conditions.haveStatusCode;

@Slf4j
@Tag("api")
@Epic("Report Portal API")
@Feature("Dashboard Controller")
public class NewDashboardCreateApiTest extends BaseApiTest {

    private static DashboardService dashboardService;
    private static String token;
    private static final String project = "default_personal";

    private String createdDashboardName;

    @BeforeAll
    static void setUpAll() {
        RestAssured.baseURI = BaseConstants.BASE_URL;
        token = getAccessToken();

        RestAssured.filters(
                new RequestLoggingFilter(),
                new ResponseLoggingFilter(),
                CustomTpl.customLogFilter().withTemplates()
        );

        dashboardService = new DashboardService();
    }

    @Test
    @Story("Создание нового Dashboard (позитивный тест)")
    @Description("""
            Выполнить POST запрос на создание дашборда.
            Статус ответа — 201.
            Убедиться, что дашборд с таким именем есть в списке дашбордов.
            """)
    @DisplayName("Позитивный тест — создание Dashboard")
    void createDashboardAndVerifyInList() {
        createdDashboardName = "Dashboard_" + System.currentTimeMillis();

        JsonPath response = dashboardService.createDashboard(project, token, createdDashboardName)
                .should(haveStatusCode(201))
                .asJsonPath();

        int id = response.getInt("id");
        Assertions.assertTrue(id > 0, "ID созданного дашборда: " + id);

        log.info("Получаем список всех дашбордов и проверяем, что нужный есть");
        List<Map<String, Object>> dashboards = dashboardService.getAllDashboards(project, token)
                .should(haveStatusCode(200))
                .asJsonPath()
                .getList("content");

        boolean found = dashboards.stream()
                .anyMatch(d -> createdDashboardName.equals(d.get("name")));

        Assertions.assertTrue(found, "Dashboard должен быть в списке: " + createdDashboardName);

    }

    @Test
    @Story("Создание Dashboard без обязательных параметров (негативный тест)")
    @Description("Попытка создать Dashboard без обязательного поля name. Ожидаем статус 400")
    @DisplayName("Негативный тест: создание Dashboard без имени")
    public void createDashboardWithMissingFields() {
        dashboardService.createDashboard(project, token, null)
                .should(haveStatusCode(400));
    }

    @AfterAll
    static void clearToken() {
        token = null;
    }
}
