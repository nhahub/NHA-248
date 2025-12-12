package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class AdminPage extends BasePage {
    private final By addButton = By.cssSelector(".orangehrm-header-container button");
    private final By userRoleDropdown = By.xpath("(//div[@class='oxd-select-text-input'])[1]");
    private final By statusDropdown = By.xpath("(//div[@class='oxd-select-text-input'])[2]");
    private final By employeeNameInput = By.cssSelector("input[placeholder='Type for hints...']");
    private final By usernameInput = By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
    private final By passwordInput = By.xpath("(//input[@type='password'])[1]");
    private final By confirmPasswordInput = By.xpath("(//input[@type='password'])[2]");
    private final By saveButton = By.cssSelector("button[type='submit']");
    private final By searchUsernameInput = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/input");
    private final By searchUserRoleDropdown = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/div/div/div[1]");
    private final By searchStatusDropdown = By.xpath("(//div[@class='oxd-select-text-input'])[2]");
    private final By userCheckbox = By.cssSelector(".oxd-table-card .oxd-table-row .oxd-table-cell:first-child .oxd-checkbox-input");
    private final By deleteButton = By.cssSelector("button.oxd-button--label-danger");
    private final By confirmDeleteButton = By.xpath("//button[normalize-space()='Yes, Delete']");

    public AdminPage(WebDriver driver) {
        super(driver);
    }

    public void addUser(String userRole, String status, String employeeName, String username, String password) throws InterruptedException {
        clickWhenReady(addButton);
        selectDropdownOption(userRoleDropdown, userRole);
        selectDropdownOption(statusDropdown, status);
        typeInAutocomplete(employeeNameInput, employeeName, employeeName);
        waitAndFind(usernameInput).sendKeys(username);
        waitAndFind(passwordInput).sendKeys(password);
        waitAndFind(confirmPasswordInput).sendKeys(password);
        clickWhenReady(saveButton);
        Thread.sleep(Duration.ofSeconds(5).toMillis());
    }

    public void searchUser(String username, String userRole, String employeeName, String status) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchUsernameInput)).sendKeys(username);

        WebElement roleInput = driver.findElement(searchUserRoleDropdown);
        roleInput.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='option']")));
        driver.findElement(By.xpath("//div[@role='listbox']//span[text()='" + userRole + "']")).click();

        typeInAutocomplete(employeeNameInput, employeeName, employeeName);
        selectDropdownOption(searchStatusDropdown, status);
        clickWhenReady(saveButton);
    }

    public boolean isUserDisplayedInResults(String username) {
        return waitAndFind(By.xpath("//div[@class='oxd-table-body']//div[contains(text(),'" + username + "')]"))
                .isDisplayed();
    }

    public void deleteUser(String username, String userRole, String employeeName, String status) {
        searchUser(username, userRole, employeeName, status);
        clickWhenReady(userCheckbox);
        clickWhenReady(deleteButton);
        driver.findElement(confirmDeleteButton).click();
    }
}
