package api.assertions;


import api.assertions.conditions.ErrorCondition;
import api.assertions.conditions.MessageCondition;
import api.assertions.conditions.StatusCodeCondition;

/**
 * Класс Conditions предоставляет статические методы-фабрики для создания
 * различных условий проверки, реализующих интерфейс {@link Condition}.
 *
 * <p>Примеры:
 * <ul>
 *     <li>{@code Conditions.hasMessage("Ожидаемое сообщение")} возвращает экземпляр {@link MessageCondition}</li>
 *     <li>{@code Conditions.hasStatusCode(200)} возвращает экземпляр {@link StatusCodeCondition}</li>
 * </ul>
 * </p>
 */
public class Conditions {
    /**
     * Создает условие проверки, которое проверяет, что в JSON-ответе
     * значение поля {@code info.message} равно ожидаемому.
     *
     * @param expectedMessage ожидаемое сообщение
     */
    public static MessageCondition haveMessage(String expectedMessage) {
        return new MessageCondition(expectedMessage);
    }

    /**
     * Создает условие проверки, которое проверяет, что статус-код ответа
     * равен ожидаемому.
     *
     * @param expectedStatusCode ожидаемый статус-код
     */
    public static StatusCodeCondition haveStatusCode(Integer expectedStatusCode) {
        return new StatusCodeCondition(expectedStatusCode);
    }

    public static ErrorCondition haveError(String expectedError) {
        return new ErrorCondition(expectedError);
    }
}
