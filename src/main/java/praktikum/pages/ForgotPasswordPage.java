package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {
    private final WebDriver driver;
    private final By emailField = By.xpath("//input[@name='email']");
    private final By submitButton = By.xpath(".//a[text()='Войти']");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    private static final String FORGOT_PASSWORD_PAGE_URL = "https://stellarburgers.education-services.ru/forgot-password";

    @Step("Отправить запрос на восстановление")
    public void submitRequest() {
        driver.findElement(submitButton).click();
    }

    @Step("Открытие страницы восстановления")
    public void open() {
        driver.get(FORGOT_PASSWORD_PAGE_URL);
    }
}


