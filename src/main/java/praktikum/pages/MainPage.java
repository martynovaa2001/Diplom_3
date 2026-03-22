package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Step;
import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private final HeaderPage header;

    private static final String BASE_URL = "https://stellarburgers.education-services.ru/";

    // Локаторы элементов
    private final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By orderButton = By.xpath("//button[text()='Оформить заказ']");

    private final By bunsSection = By.xpath("//span[text()='Булки']/..");
    private final By saucesSection = By.xpath("//span[text()='Соусы']/..");
    private final By fillingsSection = By.xpath("//span[text()='Начинки']/..");

    private final By activeSection = By.xpath("//div[contains(@class, 'tab_tab_type_current')]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.header = new HeaderPage(driver);
    }

    @Step("Переход на главную страницу")
    public void open() {
        driver.get(BASE_URL);
        waitForPageLoad();
    }

    private void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(loginButton));
    }

    public HeaderPage getHeader() {
        return header;
    }

    @Step("Проверить доступность кнопки заказа")
    public boolean isOrderButtonAvailable() {
        return driver.findElement(orderButton).isDisplayed();
    }

    // Булки
    @Step("Открыть раздел булок")
    public void openBunsSection() {
        clickSection(bunsSection);
    }

    @Step("Проверить активность раздела булок")
    public boolean isBunsSectionActive() {
        return isSectionActive(bunsSection);
    }

    // Соусы
    @Step("Открыть раздел соусов")
    public void openSaucesSection() {
        clickSection(saucesSection);
    }

    @Step("Проверить активность раздела соусов")
    public boolean isSaucesSectionActive() {
        return isSectionActive(saucesSection);
    }

    // Начинки
    @Step("Открыть раздел начинок")
    public void openFillingsSection() {
        clickSection(fillingsSection);
    }

    @Step("Проверить активность раздела начинок")
    public boolean isFillingsSectionActive() {
        return isSectionActive(fillingsSection);
    }

    @Step("Клик по кнопке 'Войти в аккаунт'")
    public void clickLoginButtonOnMainPage() {
        driver.findElement(loginButton).click();
    }

    private void clickSection(By section) {
        driver.findElement(section).click();
        waitForSectionChange();
    }

    private boolean isSectionActive(By section) {
        String classes = driver.findElement(section).getAttribute("class");
        return classes != null && classes.contains("tab_tab_type_current");
    }

    private void waitForSectionChange() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.presenceOfElementLocated(activeSection));
    }
}

