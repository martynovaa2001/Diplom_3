package praktikum.settings;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.qameta.allure.restassured.AllureRestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import praktikum.clients.UserClient;
import praktikum.api.dto.UserRequest;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected Faker faker;
    protected UserClient userClient;
    protected UserRequest testUser;
    protected String accessToken;
    private static final String BASE_URL = "https://stellarburgers.education-services.ru";

    @BeforeClass
    public static void setupRestAssured() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.filters(
                new RequestLoggingFilter(),
                new ResponseLoggingFilter(),
                new AllureRestAssured()
        );
    }

    @Before
    @DisplayName("Настройка тестового окружения")
    public void setupTestEnvironment() {
        String browserType = System.getProperty("browser", "chrome");

        if ("yandex".equals(browserType)) {
            System.setProperty("webdriver.chrome.driver", "downloads/yandexdriver.exe");
        } else {
            WebDriverManager.chromedriver().setup();
        }

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        faker = new Faker();
        userClient = new UserClient();

        // Создаем уникального пользователя для каждого теста
        testUser = new UserRequest(
                faker.internet().emailAddress(),
                faker.internet().password(8, 16),
                faker.name().fullName()
        );
    }

    @Before
    public void createTestUser() {
        // Создаем пользователя через API
        accessToken = userClient.createUser(testUser);
    }

    @After
    @DisplayName("Очистка тестовых данных")
    public void cleanup() {
        if (accessToken != null) {
            try {
                userClient.deleteUser(accessToken);
            } catch (Exception e) {
                System.err.println("Ошибка при удалении пользователя: " + e.getMessage());
            }
        }

        if (driver != null) {
            driver.quit();
        }
    }

    // Метод для получения нового токена
    protected String getNewToken() {
        return userClient.loginUser(testUser);
    }

    // Метод для проверки авторизации
    protected boolean isUserAuthorized() {
        return accessToken != null && !accessToken.isEmpty();
    }
}

