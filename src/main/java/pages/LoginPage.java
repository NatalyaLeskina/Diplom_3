package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;

    private String page = "https://stellarburgers.nomoreparties.site/login";
    private By emailField = By.xpath("(//form[@class='Auth_form__3qKeq mb-20']//input)[1]");
    private By passwordField = By.xpath("(//form[@class='Auth_form__3qKeq mb-20']//input)[2]");

    public String getPage() {
        return page;
    }

    public By loginButton = By.xpath("//button[contains(text(), 'Войти')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openLoginPage() {
        driver.get(page);
    }

    @Step("Авторизация пользователя")
    public void userLogin(String email, String password) {
        MainPage mainPage = new MainPage(driver);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(mainPage.placeOrderButton));
    }
}
