package helpers.listener;

import io.qameta.allure.restassured.AllureRestAssured;

/**
 * Фильтры AllureRestAssured для Allure Report
 */
public class CustomTpl {
    private static final AllureRestAssured FILTER = new AllureRestAssured();

    private CustomTpl() {
    }

    public static CustomTpl customLogFilter() {
        return InitLogFilter.LOG_FILTER;
    }

    public AllureRestAssured withTemplates() {
        FILTER.setRequestTemplate("request.ftl");
        FILTER.setResponseTemplate("request.ftl");
        return FILTER;
    }

    private static class InitLogFilter {
        private static final CustomTpl LOG_FILTER = new CustomTpl();
    }
}
