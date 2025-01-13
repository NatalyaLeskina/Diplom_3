import org.junit.Assert;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.PasswordRecoveryPage;
import pages.RegistrationPage;
import utils.ApiUtil;

import java.util.Random;

public class LoginTest extends BaseTest {

    Random ran = new Random();

    @Test
    public void checkLoginFromMainPage() {
        int randomInt = ran.nextInt(9999);
        String name = "name" + randomInt;
        String email = "myemail" + randomInt + "@test.ru";
        String password = "password" + randomInt;

        RegistrationPage registrationPage = new RegistrationPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        MainPage mainPage = new MainPage(driver);

        registrationPage.openRegistrationPage();
        registrationPage.userRegistration(name, email, password);

        mainPage.openMainPage();
        driver.findElement(mainPage.loginToAccountButton).click();
        loginPage.userLogin(email, password);

        Assert.assertEquals(mainPage.getPage(), driver.getCurrentUrl());

        ApiUtil.deleteUser(email, password);
    }

    @Test
    public void checkLoginFromPersonalAccountButton() {
        int randomInt = ran.nextInt(9999);
        String name = "name" + randomInt;
        String email = "myemail" + randomInt + "@test.ru";
        String password = "password" + randomInt;

        RegistrationPage registrationPage = new RegistrationPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        MainPage mainPage = new MainPage(driver);

        registrationPage.openRegistrationPage();
        registrationPage.userRegistration(name, email, password);

        mainPage.openMainPage();
        driver.findElement(mainPage.personalAccountButton).click();
        loginPage.userLogin(email, password);

        Assert.assertEquals(mainPage.getPage(), driver.getCurrentUrl());

        ApiUtil.deleteUser(email, password);
    }

    @Test
    public void checkLoginFromRegisterPage() {
        int randomInt = ran.nextInt(9999);
        String name = "name" + randomInt;
        String email = "myemail" + randomInt + "@test.ru";
        String password = "password" + randomInt;

        RegistrationPage registrationPage = new RegistrationPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        MainPage mainPage = new MainPage(driver);

        registrationPage.openRegistrationPage();
        registrationPage.userRegistration(name, email, password);

        registrationPage.openRegistrationPage();
        driver.findElement(registrationPage.loginButton).click();
        loginPage.userLogin(email, password);

        Assert.assertEquals(mainPage.getPage(), driver.getCurrentUrl());

        ApiUtil.deleteUser(email, password);
    }

    @Test
    public void checkLoginFromPasswordRecoveryPage() {
        int randomInt = ran.nextInt(9999);
        String name = "name" + randomInt;
        String email = "myemail" + randomInt + "@test.ru";
        String password = "password" + randomInt;

        RegistrationPage registrationPage = new RegistrationPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        MainPage mainPage = new MainPage(driver);
        PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage(driver);

        registrationPage.openRegistrationPage();
        registrationPage.userRegistration(name, email, password);

        passwordRecoveryPage.openPasswordRecoveryPage();
        driver.findElement(passwordRecoveryPage.loginButton).click();
        loginPage.userLogin(email, password);

        Assert.assertEquals(mainPage.getPage(), driver.getCurrentUrl());

        ApiUtil.deleteUser(email, password);
    }
}
