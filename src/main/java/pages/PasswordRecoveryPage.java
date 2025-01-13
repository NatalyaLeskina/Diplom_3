package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasswordRecoveryPage {

    private WebDriver driver;

    public PasswordRecoveryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPasswordRecoveryPage() {
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
    }

    public By loginButton = By.xpath("//a[contains(text(), 'Войти')]");
}
