package api.services;

import api.assertions.AssertableResponse;
import io.restassured.http.ContentType;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * Сервис для работы с Dashboard API ReportPortal.
 */
public class DashboardService {

    private final String basePath = "/api/v1/{projectName}/dashboard";

    /**
     * Создает новый дашборд.
     *
     * @param projectName имя проекта
     * @param token       токен авторизации
     * @param name        имя дашборда
     */
    public AssertableResponse createDashboard(String projectName, String token, String name) {
        Map<String, String> body = new HashMap<>();
        body.put("name", name);

        return new AssertableResponse(
                given()
                        .header("Authorization", "Bearer " + token)
                        .pathParam("projectName", projectName)
                        .contentType(ContentType.JSON)
                        .body(body)
                        .when()
                        .post(basePath)
                        .then()
        );
    }

    /**
     * Получает список всех дашбордов в проекте.
     *
     * @param projectName имя проекта
     * @param token       токен авторизации
     */
    public AssertableResponse getAllDashboards(String projectName, String token) {
        return new AssertableResponse(
                given()
                        .header("Authorization", "Bearer " + token)
                        .pathParam("projectName", projectName)
                        .queryParam("page.page", 1)
                        .queryParam("page.size", 100) // Вытянуть максимум
                        .when()
                        .get(basePath)
                        .then()
        );
    }
}
