package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TimePage extends BasePage {
    private final By employeeNameInput = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/form/div[1]/div/div/div/div[2]/div/div/input");
    private final By viewAttendanceButton = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/form/div[2]/button");
    private final By attendanceHeader = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/form/div[1]/div[1]/h6");

    public TimePage(WebDriver driver) {
        super(driver);
    }

    public void viewAttendance(String employeeName) {
        waitAndFind(employeeNameInput).sendKeys(employeeName);
        typeInAutocomplete(employeeNameInput, employeeName, employeeName);
        clickWhenReady(viewAttendanceButton);
    }

    public boolean isAttendanceDisplayed() {
        return waitAndFind(attendanceHeader).isDisplayed();
    }
}
