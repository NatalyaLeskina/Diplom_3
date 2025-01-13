import io.qameta.allure.Step;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.MainPage;
import pages.PersonalAccountPage;
import pages.RegistrationPage;
import utils.ApiUtil;

import java.time.Duration;
import java.util.Random;

public class PersonalAccountTest extends BaseTest {

    Random ran = new Random();

    @Test
    public void checkLogout() {
        int randomInt = ran.nextInt(9999);
        String name = "name" + randomInt;
        String email = "myemail" + randomInt + "@test.ru";
        String password = "password" + randomInt;

        RegistrationPage registrationPage = new RegistrationPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        MainPage mainPage = new MainPage(driver);
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);

        registrationPage.openRegistrationPage();
        registrationPage.userRegistration(name, email, password);
        checkSuccessRegister();
        loginPage.userLogin(email, password);

        mainPage.transitionToPersonalAccountPage();
        logoutButtonClick();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(loginPage.loginButton));
        Assert.assertEquals(loginPage.getPage(), driver.getCurrentUrl());

        ApiUtil.deleteUser(email, password);
    }

    @Step("Нажатие на кнопку выхода из аккаунта")
    private void logoutButtonClick() {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        driver.findElement(personalAccountPage.logoutButton).click();
    }
}
