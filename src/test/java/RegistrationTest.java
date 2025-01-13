import io.qameta.allure.Step;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;
import utils.ApiUtil;

import java.time.Duration;
import java.util.Random;

public class RegistrationTest extends BaseTest {

    Random ran = new Random();

    @Test
    public void checkRegistration() {
        int randomInt = ran.nextInt(9999);
        String name = "name" + randomInt;
        String email = "myemail" + randomInt + "@test.ru";
        String password = "password" + randomInt;

        RegistrationPage registrationPage = new RegistrationPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        MainPage mainPage = new MainPage(driver);

        registrationPage.openRegistrationPage();
        registrationPage.userRegistration(name, email, password);
        checkSuccessRegister();

        loginPage.userLogin(email, password);
        Assert.assertEquals(mainPage.getPage(), driver.getCurrentUrl());

        ApiUtil.deleteUser(email, password);
    }

    @Test
    public void checkRegisterWithShortPassword() {
        int randomInt = ran.nextInt(9999);
        String name = "name" + randomInt;
        String email = "myemail" + randomInt + "@test.ru";
        String lessSixSymbolsPassword = "12345";

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.openRegistrationPage();

        registrationPage.userRegistration(name, email, lessSixSymbolsPassword);
        checkWrongPasswordError();
    }

    @Step("Проверка появления ошибки о некорректном пароле")
    public void checkWrongPasswordError() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(registrationPage.wrongPasswordErrorElement));
        Assert.assertEquals(registrationPage.wrongPasswordErrorText, driver.findElement(registrationPage.wrongPasswordErrorElement).getText());
    }
}
