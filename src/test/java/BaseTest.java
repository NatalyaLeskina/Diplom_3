import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;

    @Before
    public void init() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.close();
        }
    }

    @Step("Проверка редиректа на страницу логина после успешной регистрации пользователя")
    public void checkSuccessRegister() {
        LoginPage loginPage = new LoginPage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(loginPage.loginButton));
        Assert.assertEquals(loginPage.getPage(), driver.getCurrentUrl());
    }
}
