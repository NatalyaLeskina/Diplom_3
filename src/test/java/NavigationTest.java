import io.qameta.allure.Step;
import org.junit.Assert;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.PersonalAccountPage;
import pages.RegistrationPage;
import utils.ApiUtil;

import java.util.Random;

public class NavigationTest extends BaseTest {

    Random ran = new Random();

    @Test
    public void checkTransitionToPersonalAccountPage() {
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
        Assert.assertEquals(personalAccountPage.getPage(), driver.getCurrentUrl());

        ApiUtil.deleteUser(email, password);
    }

    @Test
    public void checkTransitionToMainPageByConstructorItem() {
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
        mainPage.transitionToPersonalAccountPage();
        constructorNavItemClick();
        Assert.assertEquals(mainPage.getPage(), driver.getCurrentUrl());

        ApiUtil.deleteUser(email, password);
    }

    @Test
    public void checkTransitionToMainPageByLogo() {
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
        mainPage.transitionToPersonalAccountPage();
        logoClick();
        Assert.assertEquals(mainPage.getPage(), driver.getCurrentUrl());

        ApiUtil.deleteUser(email, password);
    }

    @Step("Нажатие на пункт 'Конструктор'")
    private void constructorNavItemClick() {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        driver.findElement(personalAccountPage.constructorNavItem).click();
    }

    @Step("Нажатие на логотип")
    private void logoClick() {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        driver.findElement(personalAccountPage.logo).click();
    }
}
