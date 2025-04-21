package api.assertions.conditions;

import api.assertions.Condition;
import io.restassured.response.ValidatableResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Assertions;

/**
 * Класс StatusCodeCondition реализует интерфейс {@link Condition} и предназначен для проверки,
 * что статус-код ответа соответствует ожидаемому значению.
 */
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StatusCodeCondition implements Condition {

    int expectedStatusCode;

    /**
     * Выполняет проверку: извлекает статус-код из ответа и сравнивает с ожидаемым.
     *
     * @param response объект ответа для проверки
     */
    @Override
    public void check(ValidatableResponse response) {

        int actualStatusCode = response.extract().statusCode();
        Assertions.assertEquals(expectedStatusCode, actualStatusCode);
    }
}
