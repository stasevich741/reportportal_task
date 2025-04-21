package api.assertions.conditions;

import api.assertions.Condition;
import api.model.Info;
import io.restassured.response.ValidatableResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Assertions;

/**
 * Класс MessageCondition реализует интерфейс {@link Condition} и
 * предназначен для проверки, что значение поля {@code info.message}
 * в JSON-ответе соответствует ожидаемому.
 */
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageCondition implements Condition {

    /**
     * Ожидаемое сообщение, с которым сравнивается значение поля {@code info.message}.
     */
    String expectedMessage;

    /**
     * Выполняет проверку: извлекает объект {@link Info} из JSON-ответа по пути "info"
     * и сравнивает его поле {@code message} с {@code expectedMessage}.
     *
     * @param response объект ответа для проверки
     */
    @Override
    public void check(ValidatableResponse response) {
        Info info = response.extract().jsonPath().getObject("info", Info.class);
        Assertions.assertEquals(expectedMessage, info.getMessage());
// Альтернативно:
//        response.body("info.message", equalTo(expectedMessage));
    }
}
