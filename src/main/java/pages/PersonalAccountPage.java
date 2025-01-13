package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalAccountPage {

    private WebDriver driver;

    private String page = "https://stellarburgers.nomoreparties.site/account/profile";

    private By profileItem = By.xpath("//a[contains(text(), 'Профиль')]");
    public By logo = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']");
    public By logoutButton = By.xpath("//button[contains(text(), 'Выход')]");
    public By changeOfPersonalDataText = By.xpath("//p[contains(text(), 'В этом разделе вы можете изменить свои персональные данные')]");
    public By constructorNavItem = By.xpath("//p[contains(text(), 'Конструктор')]");

    public String getPage() {
        return page;
    }

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPersonalAccountPage() {
        driver.get(page);
    }
}
