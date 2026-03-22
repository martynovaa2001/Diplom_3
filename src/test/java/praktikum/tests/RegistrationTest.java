package praktikum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import praktikum.pages.LoginPage;
import praktikum.pages.RegisterPage;
import praktikum.settings.BaseTest;

import static org.junit.Assert.assertTrue;

public class RegistrationTest extends BaseTest {
    private RegisterPage registerPage;
    private LoginPage loginPage;

    @Before
    public void setUp() {
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
    }

    @Test
    @DisplayName("Тестирование успешной регистрации")
    @Description("Проверяем, что при вводе валидных данных регистрация проходит успешно")
    public void testSuccessfulRegistration() {
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(8, 12);
        String name = faker.name().fullName();

        registerPage.open();
        registerPage.register(name, email, password);

//         Проверяем успешность регистрации
        assertTrue(loginPage.isButtonOpenVisible());
    }

    @Test
    @DisplayName("Тестирование регистрации с коротким паролем")
    @Description("Проверяем, что регистрация с коротким паролем невозможна")
    public void testShortPasswordRegistration() {
        String email = faker.internet().emailAddress();
        String password = "12345";
        String name = faker.name().fullName();

        registerPage.open();
        registerPage.register(name, email, password);
        assertTrue(registerPage.isPasswordErrorVisible());
    }
}