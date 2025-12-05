import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class OrangeHRM {
    WebDriver driver;
    WebDriverWait wait;
    String username = "Thomas Benny mi";

    // ---------------------  Setup  ---------------------
    @BeforeMethod
    public void setup() {
        driver = new EdgeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    // ---------------------  Helper Method  ---------------------
    WebElement waitAndFind(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void clickWhenReady(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void typeText(By locator, String text) {
        WebElement element = waitAndFind(locator);
        element.click();
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
        element.sendKeys(text);
    }

    // ---------------------  Login Test  ---------------------
    @Test(priority = 1)
    public void loginTC() {
        waitAndFind(By.cssSelector("input[placeholder='Username']")).sendKeys("Admin");
        waitAndFind(By.cssSelector("input[placeholder='Password']")).sendKeys("admin123");
        clickWhenReady(By.cssSelector("button[type='submit']"));
    }

    // ---------------------  Add User Test  ---------------------
    @Test(priority = 2)
    public void addUserTc() throws InterruptedException {
        loginTC();
        // Navigate to Admin
        clickWhenReady(By.xpath("//span[text()='Admin']/ancestor::a"));

        // Add button
        clickWhenReady(By.cssSelector(".orangehrm-header-container button"));

        // User Role dropdown
        clickWhenReady(By.xpath("(//div[@class='oxd-select-text-input'])[1]"));
        clickWhenReady(By.xpath("//div[@role='option']//span[text()='Admin']"));

        // Status dropdown
        clickWhenReady(By.xpath("(//div[@class='oxd-select-text-input'])[2]"));
        clickWhenReady(By.xpath("//div[@role='option']//span[text()='Enabled']"));

        // Employee name autocomplete
        WebElement employeeInput = waitAndFind(By.cssSelector("input[placeholder='Type for hints...']"));
        employeeInput.sendKeys("Thomas Kutty Benny");
        clickWhenReady(By.xpath("//div[@role='listbox']//span[contains(text(),'Thomas Kutty Benny')]"));

        // Username
        waitAndFind(By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]"))
                .sendKeys(username);

        // Password + Confirm
        waitAndFind(By.xpath("(//input[@type='password'])[1]")).sendKeys("123456Mm@#$");
        waitAndFind(By.xpath("(//input[@type='password'])[2]")).sendKeys("123456Mm@#$");

        // Save
        clickWhenReady(By.cssSelector("button[type='submit']"));
        Thread.sleep(Duration.ofSeconds(5));
    }

    @Test(priority = 3)
    public void editeUserTc() {
        loginTC();
        // Navigate to My Info
        clickWhenReady(By.xpath("//span[text()='My Info']/ancestor::a"));
        // change name details
        typeText(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[1]/div/div/div/div[2]/div[1]/div[2]/input"), "muhammed");
        typeText(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[1]/div/div/div/div[2]/div[2]/div[2]/input"), "moghazy");
        typeText(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[1]/div/div/div/div[2]/div[3]/div[2]/input"), "ghoname");
        // change IDs numbers
        typeText(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[2]/div[1]/div[1]/div/div[2]/input[1]"), "65465");
        typeText(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[2]/div[1]/div[2]/div/div[2]/input"), "5678");
        // change license number and expiry date
        typeText(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[2]/div[2]/div[1]/div/div[2]/input"), "5842");
        typeText(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[2]/div[2]/div[2]/div/div[2]/div/div/input"), "2025-04-12");
        //chage nationality
        clickWhenReady(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[1]/div[1]/div/div[2]/div/div/div[1]"));
        clickWhenReady(By.xpath("//div[@role='option']//span[text()='Afghan']"));
        // change marital status
        clickWhenReady(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[1]/div[2]/div/div[2]/div/div/div[1]"));
        clickWhenReady(By.xpath("//div[@role='option']//span[text()='Single']"));
        // change date of birth
        typeText(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[2]/div[1]/div/div[2]/div/div/input"), "2025-28-12");
        // save changes
        clickWhenReady(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[4]/button"));
    }


    // ---------------------  Search User Test  ---------------------
    @Test(priority = 4)
    public void searchUserTc() {
        loginTC();
        // Navigate to Admin
        clickWhenReady(By.xpath("//span[text()='Admin']/ancestor::a"));

        // Search Username
        // 2. Enter Username
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/input")
        )).sendKeys(username);
        // 3. Select User Role
        WebElement roleInput = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/div/div/div[1]"));
        roleInput.click();
        //wait for options to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='option']")));

        // choose 'Admin' option
        driver.findElement(By.xpath("//div[@role='listbox']//span[text()='Admin']")).click();

        // Employee name autocomplete
        WebElement employeeInput = waitAndFind(By.cssSelector("input[placeholder='Type for hints...']"));
        employeeInput.sendKeys("Thomas Kutty Benny");
        clickWhenReady(By.xpath("//div[@role='option']//span[contains(text(),'Thomas Kutty Benny')]"));

        // Status dropdown
        clickWhenReady(By.xpath("(//div[@class='oxd-select-text-input'])[2]"));
        clickWhenReady(By.xpath("//span[text()='Enabled']"));

        // Search Button
        clickWhenReady(By.cssSelector("button[type='submit']"));
        Assert.assertTrue(waitAndFind(By.xpath("//div[@class='oxd-table-body']//div[contains(text(),'"+username+"')]")).isDisplayed(), "User not found in search results");
    }

    @Test(priority = 5)
    public void deleteUserTc() {
        loginTC();
        // Navigate to Admin
        clickWhenReady(By.xpath("//span[text()='Admin']/ancestor::a"));
        // Search Username
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/input")
        )).sendKeys(username);

        // 3. Select User Role
        WebElement roleInput = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/div/div/div[1]"));
        roleInput.click();
        //wait for options to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='option']")));

        // choose 'Admin' option
        driver.findElement(By.xpath("//div[@role='listbox']//span[text()='Admin']")).click();

        // Employee name autocomplete
        WebElement employeeInput = waitAndFind(By.cssSelector("input[placeholder='Type for hints...']"));
        employeeInput.sendKeys("Thomas Kutty Benny");
        clickWhenReady(By.xpath("//div[@role='option']//span[contains(text(),'Thomas Kutty Benny')]"));

        // Status dropdown
        clickWhenReady(By.xpath("(//div[@class='oxd-select-text-input'])[2]"));
        clickWhenReady(By.xpath("//span[text()='Enabled']"));

        // Search Button
        clickWhenReady(By.cssSelector("button[type='submit']"));

        // Select User Checkbox
        clickWhenReady(By.cssSelector(".oxd-table-card .oxd-table-row .oxd-table-cell:first-child .oxd-checkbox-input"));

        // Delete Button
        clickWhenReady(By.cssSelector("button.oxd-button--label-danger"));

        // Confirm Delete
        driver.findElement(By.xpath("//button[normalize-space()='Yes, Delete']")).click();
    }

    @Test(priority = 6)
    public void addEmployeeJobTc() {
        loginTC();
        clickWhenReady(By.xpath("//span[text()='Recruitment']/ancestor::a"));
        clickWhenReady(By.cssSelector(".orangehrm-header-container button"));
        typeText(By.cssSelector("input[placeholder='First Name']"), "muhammed");
        typeText(By.cssSelector("input[placeholder='Last Name']"), "moghazy");
        //chosses vacancy
        clickWhenReady(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div/div/div[2]/div/div"));
        clickWhenReady(By.xpath("//div[@role='option']//span[text()='Senior QA Lead']"));
        //enter email
        typeText(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[1]/div/div[2]/input"), "muhammed@gmail.com");
        // enter contact number
        typeText(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[2]/div/div[2]/input"), "0123456789");
        //date of application
        typeText(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[5]/div/div[2]/div/div[2]/div/div/input"), "2025-05-12");
        //upload resume
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[4]/div/div/div/div/div[2]/input"))
                .sendKeys("C:\\Users\\KimoStore\\Desktop\\depi_pro\\src\\main\\resources\\Muhammed Moghazy_Computer and Data Science_CV.pdf");
        //keywords
        typeText(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[5]/div/div[1]/div/div[2]/input"), "QA, Automation, Selenium");
        //save
        clickWhenReady(By.cssSelector("button[type='submit']"));
        Assert.assertTrue(waitAndFind(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div[1]/form/div[2]/div[1]/p")).isDisplayed(), "Employee not added successfully");
    }

    @Test(priority = 7)
    public void timeAttendanceViewTc() throws InterruptedException {
        loginTC();
        // Navigate to Time
        clickWhenReady(By.xpath("//span[text()='Time']/ancestor::a"));
        // view attendance
        waitAndFind(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/form/div[1]/div/div/div/div[2]/div/div/input"))
                .sendKeys("Thomas Kutty Benny");
        clickWhenReady(By.xpath("//div[@role='option']//span[contains(text(),'Thomas Kutty Benny')]"));
        // Click on Punch In button
        clickWhenReady(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/form/div[2]/button"));

        waitAndFind(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/form/div[1]/div[1]/h6")).isDisplayed();

        Thread.sleep(Duration.ofSeconds(5));
    }
    @Test(priority = 8)
    public void logoutTc() {
        loginTC();
        // افتح منيو اليوزر
        waitAndFind(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[3]/ul/li/span")).click();

        // اضغط Logout
        waitAndFind(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[3]/ul/li/ul/li[4]/a")).click();

    }

    // ---------------------  Tear Down  ---------------------
    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}