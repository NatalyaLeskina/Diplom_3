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

public class MainPageTest extends BaseTest {

    Random ran = new Random();
    MainPage mainPage = new MainPage(driver);
    PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);

    @Test
    public void checkSaucesClick() {
        int randomInt = ran.nextInt(9999);
        String name = "name" + randomInt;
        String email = "myemail" + randomInt + "@test.ru";
        String password = "password" + randomInt;

        RegistrationPage registrationPage = new RegistrationPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        registrationPage.openRegistrationPage();
        registrationPage.userRegistration(name, email, password);
        checkSuccessRegister();
        loginPage.userLogin(email, password);

        saucesButtonClick();
        Assert.assertEquals(mainPage.getPage(), driver.getCurrentUrl());
        checkOpenSauceSection();

        ApiUtil.deleteUser(email, password);
    }

    @Test
    public void checkFillingClick() {
        int randomInt = ran.nextInt(9999);
        String name = "name" + randomInt;
        String email = "myemail" + randomInt + "@test.ru";
        String password = "password" + randomInt;

        RegistrationPage registrationPage = new RegistrationPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        registrationPage.openRegistrationPage();
        registrationPage.userRegistration(name, email, password);
        checkSuccessRegister();
        loginPage.userLogin(email, password);

        fillingButtonClick();
        checkOpenFillingSection();

        ApiUtil.deleteUser(email, password);
    }

    @Test
    public void checkBunsClick() {
        int randomInt = ran.nextInt(9999);
        String name = "name" + randomInt;
        String email = "myemail" + randomInt + "@test.ru";
        String password = "password" + randomInt;

        RegistrationPage registrationPage = new RegistrationPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        registrationPage.openRegistrationPage();
        registrationPage.userRegistration(name, email, password);
        checkSuccessRegister();
        loginPage.userLogin(email, password);

        fillingButtonClick();
        checkOpenFillingSection();
        bunsButtonClick();
        checkOpenBunsSection();

        ApiUtil.deleteUser(email, password);
    }

    @Step("Проверка, что открыт раздел 'Соусы'")
    private void checkOpenSauceSection() {
        Assert.assertEquals("Соусы", driver.findElement(mainPage.activeSection).getText());
    }

    @Step("Проверка, что открыт раздел 'Начинки'")
    private void checkOpenFillingSection() {
        Assert.assertEquals("Начинки", driver.findElement(mainPage.activeSection).getText());
    }

    @Step("Проверка, что открыт раздел 'Булки'")
    private void checkOpenBunsSection() {
        Assert.assertEquals("Булки", driver.findElement(mainPage.activeSection).getText());
    }

    @Step("Нажатие на кнопку 'Соусы'")
    public void saucesButtonClick() {
        driver.findElement(mainPage.saucesButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(mainPage.activeSaucesSection));
    }

    @Step("Нажатие на кнопку 'Начинки'")
    public void fillingButtonClick() {
        driver.findElement(mainPage.fillingButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(mainPage.activeFillingSection));
    }

    @Step("Нажатие на кнопку 'Булки'")
    public void bunsButtonClick() {
        driver.findElement(mainPage.bunsButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(mainPage.activeBunsSection));
    }
}
