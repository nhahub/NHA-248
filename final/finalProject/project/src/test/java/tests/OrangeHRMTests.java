package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class OrangeHRMTests extends BaseTest {
    private final String USERNAME = "Thomas Benny mu";
    private final String EMPLOYEE_NAME = "Thomas Kutty Benny";
    private final String PASSWORD = "123456Mm@#$";

    @Test
    public void loginTest() {
        loginPage.loginAsAdmin();
    }

    @Test
    public void addUserTest() throws InterruptedException {
        loginAsAdmin();
        AdminPage adminPage = dashboardPage.navigateToAdmin();
        adminPage.addUser("Admin", "Enabled", EMPLOYEE_NAME, USERNAME, PASSWORD);
    }

    @Test
    public void editUserTest() {
        loginAsAdmin();
        MyInfoPage myInfoPage = dashboardPage.navigateToMyInfo();
        myInfoPage.updateAllPersonalInfo(
                "muhammed", "moghazy", "ghoname",
                "65465", "5678", "5842", "2025-04-12",
                "Afghan", "Single", "2025-28-12"
        );
    }

    @Test
    public void searchUserTest() {
        loginAsAdmin();
        AdminPage adminPage = dashboardPage.navigateToAdmin();
        adminPage.searchUser(USERNAME, "Admin", EMPLOYEE_NAME, "Enabled");
        Assert.assertTrue(adminPage.isUserDisplayedInResults(USERNAME), "User not found in search results");
    }

    @Test
    public void deleteUserTest() {
        loginAsAdmin();
        AdminPage adminPage = dashboardPage.navigateToAdmin();
        adminPage.deleteUser(USERNAME, "Admin", EMPLOYEE_NAME, "Enabled");
    }

    @Test
    public void addEmployeeJobTest() {
        loginAsAdmin();
        RecruitmentPage recruitmentPage = dashboardPage.navigateToRecruitment();
        recruitmentPage.addCandidate(
                "muhammed", "moghazy", "Senior QA Lead",
                "muhammed@gmail.com", "0123456789", "2025-05-12",
                "C:\\Users\\Desktop\\DEPI\\src\\main\\resources\\Muhammed Moghazy_Computer and Data Science_CV.pdf",
                "QA, Automation, Selenium"
        );
        Assert.assertTrue(recruitmentPage.isCandidateAddedSuccessfully(), "Employee not added successfully");
    }

    @Test
    public void timeAttendanceViewTest() throws InterruptedException {
        loginAsAdmin();
        TimePage timePage = dashboardPage.navigateToTime();
        timePage.viewAttendance(EMPLOYEE_NAME);
        Assert.assertTrue(timePage.isAttendanceDisplayed());
        Thread.sleep(5000);
    }

    @Test
    public void logoutTest() {
        loginAsAdmin();
        dashboardPage.logout();
    }
}
