package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private final By usernameInput = By.cssSelector("input[placeholder='Username']");
    private final By passwordInput = By.cssSelector("input[placeholder='Password']");
    private final By loginButton = By.cssSelector("button[type='submit']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        waitAndFind(usernameInput).sendKeys(username);
        waitAndFind(passwordInput).sendKeys(password);
        clickWhenReady(loginButton);
    }

    public void loginAsAdmin() {
        login("Admin", "admin123");
    }
}
