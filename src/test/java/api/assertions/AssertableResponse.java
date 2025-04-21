package api.assertions;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Класс AssertableResponse предоставляет методы для доступа к данным HTTP-ответа,
 * а также для применения кастомных проверок (условий) к ответу.
 */
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AssertableResponse {

    ValidatableResponse response;

    /**
     * Применяет указанное условие проверки к ответу.
     *
     * @param condition условие, реализующее интерфейс {@link Condition}
     */
    public AssertableResponse should(Condition condition) {
        condition.check(response);
        return this;
    }

    /**
     * Возвращает объект Response, содержащий исходный HTTP-ответ.
     */
    public JsonPath asJsonPath() {
        return response.extract().jsonPath();
    }

    public ValidatableResponse getResponse() {
        return response;
    }

    public int getInt(String jsonPath) {
        return response.extract().jsonPath().getInt(jsonPath);
    }
}

