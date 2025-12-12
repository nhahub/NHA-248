package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecruitmentPage extends BasePage {
    private final By addButton = By.cssSelector(".orangehrm-header-container button");
    private final By firstNameInput = By.cssSelector("input[placeholder='First Name']");
    private final By lastNameInput = By.cssSelector("input[placeholder='Last Name']");
    private final By vacancyDropdown = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div/div/div[2]/div/div");
    private final By emailInput = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[1]/div/div[2]/input");
    private final By contactNumberInput = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[2]/div/div[2]/input");
    private final By applicationDateInput = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[5]/div/div[2]/div/div[2]/div/div/input");
    private final By resumeUploadInput = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[4]/div/div/div/div/div[2]/input");
    private final By keywordsInput = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[5]/div/div[1]/div/div[2]/input");
    private final By saveButton = By.cssSelector("button[type='submit']");
    private final By successMessage = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div[1]/form/div[2]/div[1]/p");

    public RecruitmentPage(WebDriver driver) {
        super(driver);
    }

    public void addCandidate(String firstName, String lastName, String vacancy, String email,
                             String contactNumber, String applicationDate, String resumePath, String keywords) {
        clickWhenReady(addButton);
        typeText(firstNameInput, firstName);
        typeText(lastNameInput, lastName);
        selectDropdownOption(vacancyDropdown, vacancy);
        typeText(emailInput, email);
        typeText(contactNumberInput, contactNumber);
        typeText(applicationDateInput, applicationDate);
        driver.findElement(resumeUploadInput).sendKeys(resumePath);
        typeText(keywordsInput, keywords);
        clickWhenReady(saveButton);
    }

    public boolean isCandidateAddedSuccessfully() {
        return waitAndFind(successMessage).isDisplayed();
    }
}
