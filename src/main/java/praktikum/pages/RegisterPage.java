package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private final WebDriver driver;

    public static final String REGISTER_PAGE_URL = "https://stellarburgers.education-services.ru/register";

    private final By nameField = By.xpath(".//label[text()='Имя']/following-sibling::input");
    private final By emailField = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By errorPasswordMessage = By.xpath(".//p[text()='Некорректный пароль']");
    private final By loginLinkOnRegisterPage = By.xpath(".//a[text()='Войти']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Заполнение имени: {name}")
    public void enterName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    @Step("Заполнение email: {email}")
    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Заполнение пароля: {password}")
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Нажать кнопку войти")
    public void clickRegister() {
        driver.findElement(registerButton).click();
    }

    @Step("Открытие страницы регистрации")
    public void open() {
        driver.get(REGISTER_PAGE_URL);
    }

    @Step("Перейти на страницу входа")
    public void clickLoginLinkOnRegisterPage() {
        driver.findElement(loginLinkOnRegisterPage).click();
    }

    @Step("Регистрация пользователя: {name} / {email} / {password}")
    public void register(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickRegister();

    }
    @Step("Проверка отображения ошибки 'Некорректный пароль'")
    public boolean isPasswordErrorVisible() {
        return driver.findElement(errorPasswordMessage).isDisplayed();
    }
}

