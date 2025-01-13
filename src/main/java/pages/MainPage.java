package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private WebDriver driver;

    private String page = "https://stellarburgers.nomoreparties.site/";

    public String getPage() {
        return page;
    }

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openMainPage() {
        driver.get(page);
    }

    public By placeOrderButton = By.xpath("//button[contains(text(), 'Оформить заказ')]");
    public By loginToAccountButton = By.xpath("//button[contains(text(), 'Войти в аккаунт')]");
    public By personalAccountButton = By.xpath("//p[contains(text(), 'Личный Кабинет')]");
    public By saucesButton = By.xpath("//span[contains(text(), 'Соусы')]");
    public By fillingButton = By.xpath("//span[contains(text(), 'Начинки')]");
    public By bunsButton = By.xpath("//span[contains(text(), 'Булки')]");
    public By activeSection = By.xpath("//div[contains(@class, 'tab_tab_type_current__2BEPc')]");
    public By activeSaucesSection = By.xpath("//div[contains(@class, 'tab_tab_type_current__2BEPc')]//span[contains(text(), 'Соусы')]");
    public By activeFillingSection = By.xpath("//div[contains(@class, 'tab_tab_type_current__2BEPc')]//span[contains(text(), 'Начинки')]");
    public By activeBunsSection = By.xpath("//div[contains(@class, 'tab_tab_type_current__2BEPc')]//span[contains(text(), 'Булки')]");

    public void transitionToPersonalAccountPage() {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        driver.findElement(personalAccountButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(personalAccountPage.changeOfPersonalDataText));
    }
}
