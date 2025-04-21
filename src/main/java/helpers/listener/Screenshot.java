package helpers.listener;

import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Field;

/**
 * Делает скриншот при падении теста и добавляет его в аллюр отчёт
 */
public class Screenshot implements AfterTestExecutionCallback {

    /**
     * Проверяем, было ли исключение во время выполнения теста
     */
    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        if (context.getExecutionException().isPresent()) {
            Object instance = context.getRequiredTestInstance();
            WebDriver driver = extractDriver(instance);
            if (driver != null) {
                attachScreenshot(driver);
            }
        }
    }

    private WebDriver extractDriver(Object instance) {
        Class<?> cls = instance.getClass();
        while (cls != Object.class) {
            for (Field field : cls.getDeclaredFields()) {
                if (WebDriver.class.isAssignableFrom(field.getType())) {
                    field.setAccessible(true);
                    try {
                        return (WebDriver) field.get(instance);
                    } catch (IllegalAccessException ignored) {
                    }
                }
            }
            cls = cls.getSuperclass();
        }
        return null;
    }

    /**
     * Делает скриншот текущего состояния браузера
     */
    @Attachment(value = "Скриншот при падении", type = "image/png")
    private byte[] attachScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES);
    }
}