package web;

import helpers.BrowserType;
import helpers.listener.Screenshot;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import web.steps.DashboardPageSteps;
import web.steps.LaunchesPageSteps;
import web.steps.LoginPageSteps;

import java.time.Duration;

import static helpers.constants.BaseConstants.TIME_TO_WAIT;

@Slf4j
@FieldDefaults(level = AccessLevel.PROTECTED)
@ExtendWith(Screenshot.class)
abstract class BaseTest {
    WebDriver driver;
    LoginPageSteps loginPageSteps;
    DashboardPageSteps dashboardPageSteps;
    LaunchesPageSteps launchesPageSteps;

    void setUp(BrowserType browserType) {
        driver = createDriverByType(browserType);
        driver.manage().window().setSize(new Dimension(1820, 1080));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TIME_TO_WAIT));
        refreshPages();
    }
    private WebDriver createDriverByType(BrowserType type) {
        return switch (type) {
            case FIREFOX -> createFireFoxDriver();
            case EDGE -> createEdgeDriver();
        };
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Создает FirefoxDriver с заданными опциями.
     */
    private WebDriver createFireFoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
//        options.addArguments("-headless");
        return new FirefoxDriver(options);
    }

    /**
     * Создает EdgeDriver с заданными опциями.
     */
    private WebDriver createEdgeDriver() {
        EdgeOptions options = new EdgeOptions();
//        options.addArguments("-headless");
        return new EdgeDriver(options);
    }

    /**
     * Пересоздает экземпляры классов
     */
    private void refreshPages() {
        loginPageSteps = new LoginPageSteps(driver);
        dashboardPageSteps = new DashboardPageSteps(driver);
        launchesPageSteps = new LaunchesPageSteps(driver);
    }
}
