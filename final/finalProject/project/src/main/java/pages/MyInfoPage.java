package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyInfoPage extends BasePage {
    private final By firstNameInput = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[1]/div/div/div/div[2]/div[1]/div[2]/input");
    private final By middleNameInput = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[1]/div/div/div/div[2]/div[2]/div[2]/input");
    private final By lastNameInput = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[1]/div/div/div/div[2]/div[3]/div[2]/input");
    private final By employeeIdInput = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[2]/div[1]/div[1]/div/div[2]/input[1]");
    private final By otherIdInput = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[2]/div[1]/div[2]/div/div[2]/input");
    private final By licenseNumberInput = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[2]/div[2]/div[1]/div/div[2]/input");
    private final By licenseExpiryInput = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[2]/div[2]/div[2]/div/div[2]/div/div/input");
    private final By nationalityDropdown = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[1]/div[1]/div/div[2]/div/div/div[1]");
    private final By maritalStatusDropdown = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[1]/div[2]/div/div[2]/div/div/div[1]");
    private final By dateOfBirthInput = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[2]/div[1]/div/div[2]/div/div/input");
    private final By saveButton = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[4]/button");

    public MyInfoPage(WebDriver driver) {
        super(driver);
    }

    public void updatePersonalDetails(String firstName, String middleName, String lastName) {
        typeText(firstNameInput, firstName);
        typeText(middleNameInput, middleName);
        typeText(lastNameInput, lastName);
    }

    public void updateIdNumbers(String employeeId, String otherId) {
        typeText(employeeIdInput, employeeId);
        typeText(otherIdInput, otherId);
    }

    public void updateLicenseDetails(String licenseNumber, String expiryDate) {
        typeText(licenseNumberInput, licenseNumber);
        typeText(licenseExpiryInput, expiryDate);
    }

    public void updateNationalityAndMaritalStatus(String nationality, String maritalStatus) {
        selectDropdownOption(nationalityDropdown, nationality);
        selectDropdownOption(maritalStatusDropdown, maritalStatus);
    }

    public void updateDateOfBirth(String dateOfBirth) {
        typeText(dateOfBirthInput, dateOfBirth);
    }

    public void saveChanges() {
        clickWhenReady(saveButton);
    }

    public void updateAllPersonalInfo(String firstName, String middleName, String lastName,
                                      String employeeId, String otherId, String licenseNumber,
                                      String expiryDate, String nationality, String maritalStatus,
                                      String dateOfBirth) {
        updatePersonalDetails(firstName, middleName, lastName);
        updateIdNumbers(employeeId, otherId);
        updateLicenseDetails(licenseNumber, expiryDate);
        updateNationalityAndMaritalStatus(nationality, maritalStatus);
        updateDateOfBirth(dateOfBirth);
        saveChanges();
    }
}
