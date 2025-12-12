package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {
    private final By adminMenuLink = By.xpath("//span[text()='Admin']/ancestor::a");
    private final By myInfoMenuLink = By.xpath("//span[text()='My Info']/ancestor::a");
    private final By recruitmentMenuLink = By.xpath("//span[text()='Recruitment']/ancestor::a");
    private final By timeMenuLink = By.xpath("//span[text()='Time']/ancestor::a");
    private final By userMenuDropdown = By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[3]/ul/li/span");
    private final By logoutLink = By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[3]/ul/li/ul/li[4]/a");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public AdminPage navigateToAdmin() {
        clickWhenReady(adminMenuLink);
        return new AdminPage(driver);
    }

    public MyInfoPage navigateToMyInfo() {
        clickWhenReady(myInfoMenuLink);
        return new MyInfoPage(driver);
    }

    public RecruitmentPage navigateToRecruitment() {
        clickWhenReady(recruitmentMenuLink);
        return new RecruitmentPage(driver);
    }

    public TimePage navigateToTime() {
        clickWhenReady(timeMenuLink);
        return new TimePage(driver);
    }

    public void logout() {
        waitAndFind(userMenuDropdown).click();
        waitAndFind(logoutLink).click();
    }
}
