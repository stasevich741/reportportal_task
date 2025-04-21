package api.assertions.conditions;

import api.assertions.Condition;
import io.restassured.response.ValidatableResponse;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;

/**
 * Класс {@code ErrorCondition} реализует интерфейс {@link Condition} и предназначен для проверки,
 * что значение поля {@code error} в JSON-ответе соответствует ожидаемому значению.
 */
@RequiredArgsConstructor
public class ErrorCondition implements Condition {

    private final String expectedError;

    @Override
    public void check(ValidatableResponse response) {
        String error = response.extract().jsonPath().getString("error");
        Assertions.assertEquals(expectedError, error);
    }
}