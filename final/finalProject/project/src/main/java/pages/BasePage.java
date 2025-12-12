package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected WebElement waitAndFind(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void clickWhenReady(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void typeText(By locator, String text) {
        WebElement element = waitAndFind(locator);
        element.click();
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
        element.sendKeys(text);
    }

    protected void selectDropdownOption(By dropdownLocator, String optionText) {
        clickWhenReady(dropdownLocator);
        clickWhenReady(By.xpath("//div[@role='option']//span[text()='" + optionText + "']"));
    }

    protected void typeInAutocomplete(By inputLocator, String text, String optionText) {
        WebElement input = waitAndFind(inputLocator);
        input.sendKeys(text);
        clickWhenReady(By.xpath("//div[@role='option']//span[contains(text(),'" + optionText + "')]"));
    }
}
