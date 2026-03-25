package praktikum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import praktikum.settings.BaseTest;
import praktikum.pages.MainPage;
import praktikum.pages.LoginPage;
import praktikum.pages.RegisterPage;
import praktikum.pages.ForgotPasswordPage;
import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {

    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private ForgotPasswordPage forgotPasswordPage;

    @Before
    public void setupLoginTests() {

        accessToken = userClient.createUser(testUser);

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
    }

    @Test
    @DisplayName("Проверка входа через кнопку войти в аккаунт главной страницы")
    @Description("Проверяем что кнопка офорления заказа появляется после входа в аккаунт главной страницы через кнопку Войти")
    public void testLoginFromMainPage() {
        mainPage.open();
        mainPage.clickLoginButtonOnMainPage();
        loginPage.clickLogin();
        assertTrue("Кнопка оформления заказа должна появиться после входа через кнопку войти с главной страницы",
                loginPage.isButtonOpenVisible());
    }

    @Test
    @DisplayName("Проверка входа через кнопку личный кабинет в шапке сайта")
    @Description("Проверяем что кнопка офорления заказа появляется после входа через кнопку личный кабинет в шапке сайта")
    public void testPersonalAccountButtonFromHeader() {
        mainPage.open();
        mainPage.getHeader().openProfile();
        loginPage.clickLogin();

        assertTrue("Кнопка оформления заказа должна появиться после входа через кнопку личный кабинет",
                loginPage.isButtonOpenVisible());
    }

    @Test
    @DisplayName("Проверка входа через кнопку в форме регистрации")
    @Description("Проверяем что кнопка офорления заказа появляется после входа через кнопку в форме регистрации")
    public void testLoginFromRegisterForm() {
        registerPage.open();
        registerPage.clickLoginLinkOnRegisterPage();
        loginPage.clickLogin();
        assertTrue("Кнопка оформления заказа должна появиться после входа через кнопку в форме регистрации",
                loginPage.isButtonOpenVisible());
    }

    @Test
    @DisplayName("Проверка входа через кнопку в форме восстановления пароля")
    @Description("Проверяем что кнопка офорления заказа появляется после входа через кнопку восстановления пароля")
    public void testLoginForgotPasswordForm() {
        forgotPasswordPage.open();
        forgotPasswordPage.submitRequest();
        loginPage.clickLogin();
        assertTrue("Кнопка оформления заказа должна появиться после входа через кнопку в форме восстановления пароля",
                loginPage.isButtonOpenVisible());
    }
}

