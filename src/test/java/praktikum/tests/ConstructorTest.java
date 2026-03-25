package praktikum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import praktikum.pages.MainPage;
import praktikum.settings.BaseTest;

import static org.junit.Assert.assertTrue;

public class ConstructorTest extends BaseTest {
    private MainPage mainPage;

    @Before
    public void setUp() {
        mainPage = new MainPage(driver);
        mainPage.open();
    }

    @Test
    @DisplayName("Проверка перехода в раздел булок")
    @Description("Проверяем успешность перехода в раздел булок")
    public void testBunsSection() {
        mainPage.openSaucesSection();
        mainPage.openBunsSection();
        assertTrue(mainPage.isBunsSectionActive());
    }

    @Test
    @DisplayName("Проверка перехода в раздел соусов")
    @Description("Проверяем успешность перехода в раздел соусов")
    public void testSaucesSection() {
        mainPage.openSaucesSection();
        assertTrue(mainPage.isSaucesSectionActive());
    }

    @Test
    @DisplayName("Проверка перехода в раздел начинок")
    @Description("Проверяем успешность перехода в раздел начинок")
    public void testFillingsSection() {
        mainPage.openFillingsSection();
        assertTrue(mainPage.isFillingsSectionActive());
    }
}

