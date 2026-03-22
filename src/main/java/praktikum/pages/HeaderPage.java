package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderPage {
    private final WebDriver driver;
    private final By profileButton = By.xpath(".//p[text()='Личный Кабинет']");

    public HeaderPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открыть личный кабинет")
    public void openProfile() {
        driver.findElement(profileButton).click();
    }
}
