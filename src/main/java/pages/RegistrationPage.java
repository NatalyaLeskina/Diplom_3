package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {

    private WebDriver driver;

    private By nameField = By.xpath("(//form[@class='Auth_form__3qKeq mb-20']//input)[1]");
    private By emailField = By.xpath("(//form[@class='Auth_form__3qKeq mb-20']//input)[2]");
    private By passwordField = By.xpath("(//form[@class='Auth_form__3qKeq mb-20']//input[@type='password'])");
    private By registrationButton = By.xpath("//form[@class='Auth_form__3qKeq mb-20']//button");
    public By wrongPasswordErrorElement = By.xpath("//p[@class='input__error text_type_main-default']");
    public String wrongPasswordErrorText = "Некорректный пароль";
    public By loginButton = By.xpath("//a[contains(text(), 'Войти')]");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openRegistrationPage() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
    }


    @Step("Заполнение и отправка формы регистрации")
    public void userRegistration(String name, String email, String password) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(registrationButton).click();
    }
}
