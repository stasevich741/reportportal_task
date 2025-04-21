package api.assertions;

import io.restassured.response.ValidatableResponse;

/**
 * Интерфейс Condition определяет контракт для проверки условий
 * на объекте {@link ValidatableResponse}.
 * Каждый класс, реализующий этот интерфейс, должен реализовать метод
 * {@code check(ValidatableResponse response)} для выполнения своей проверки.
 */
public interface Condition {
    /**
     * Выполняет проверку условия на основе предоставленного объекта ответа.
     *
     * @param response объект ответа, который необходимо проверить
     */
    void check(ValidatableResponse response);
}
