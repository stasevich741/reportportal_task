package web.steps;

import org.openqa.selenium.WebDriver;
import web.pages.LaunchesPage;

/**
 * Шаги для отображения в аллюр отчёте (LaunchesPage)
 */
public class LaunchesPageSteps {

    protected WebDriver driver;
    protected LaunchesPage launchesPage;

    public LaunchesPageSteps(WebDriver driver) {
        this.driver = driver;
        this.launchesPage = new LaunchesPage(driver);
    }
}
