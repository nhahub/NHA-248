import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.IOException;
import java.util.List;

public class orangehrm {
    WebDriver driver;

    @Test
    //valid login test
    public void validLoginTest() {
         driver = new EdgeDriver();
         url("https://ammar-osondemand.orangehrm.com/auth/login");
         maximize();
         driver.findElement(By.xpath("username")).sendKeys("Admin");
         driver.findElement(By.xpath("password")).sendKeys("@2n@@r7kPGHB");
         click(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button"));
//         quit();
    }
    @Test
    public void invalidMassage() {
         driver = new EdgeDriver();
         url("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
         maximize();
            type("username", "Admin");
            type("password", "admin123");
            click(By.xpath("//button[@type='submit']"));
            getText(By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']"));
        quit();
    }
    @Test
    public void addEmployee() throws InterruptedException, IOException {
        logIn();
        driver.findElement(By.xpath("//span[text()='PIM']")).click();
        driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
        driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys("muhammed");
        driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys("moghazy");
        driver.findElement(By.xpath("//button[@class='oxd-icon-button oxd-icon-button--solid-main employee-image-action']")).click();
        Thread.sleep(5000);//pause of 5 seconds
        Runtime.getRuntime().exec("C:\\Users\\moghazy\\OneDrive\\Desktop\\image.jpg");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
        String confirmationMessage = driver.findElement(By.xpath("//h6[normalize-space()='Personal Details']")).getText();
        if (confirmationMessage.contains("Personal Details")) {
            System.out.println("Employee added successfully!");
        } else {
            System.out.println("Failed to add employee!");
        }
    }
    public void searchEmployeeNyName() throws InterruptedException
    {
        logIn();
        //find PIM Menu and click on PIM Menu
        driver.findElement(By.xpath("//span[text()='PIM']")).click();
        //Select Employee List
        driver.findElement(By.xpath("//a[normalize-space()='Employee List']")).click();
        driver.findElements(By.tagName("input")).get(1).sendKeys("muhamed");
        //Click the search button.
        driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();
        List<WebElement> element=	driver.findElements(By.xpath("//span[@class='oxd-text oxd-text--span']"));
        String expectedMessage = "Record Found";
        String messageActual = element.get(0).getText();
        System.out.println(messageActual);
        Assert.assertTrue(messageActual.contains(expectedMessage));

    }
    public void searchEmployeeById() throws InterruptedException
    {
        logIn();
        String empId = "0372";
        String messagAectual ="";
        //find PIM Menu and click on PIM Menu
        driver.findElement(By.xpath("//span[text()='PIM']")).click();
        //Select Employee List
        driver.findElement(By.xpath("//a[normalize-space()='Employee List']")).click();
        //enter empoyee id
        driver.findElements(By.tagName("input")).get(2).sendKeys(empId);
        //Click the search button.
        driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();
        List<WebElement> rows = driver.findElements(By.xpath("(//div[@role='row'])"));
        if(rows.size()>1)
        {
            messagAectual = driver.findElement(By.xpath("((//div[@role='row'])[2]/div[@role='cell'])[2]")).getText();

        }
        Assert.assertEquals(empId, messagAectual);
        quit();

    }
    @Test
    public void fileUpload() throws IOException, InterruptedException
    {

        logIn();

        //find PIM Menu and click on PIM Menu
        driver.findElement(By.xpath("//span[text()='PIM']")).click();

        //click on configuration button
        driver.findElement(By.xpath("//span[@class='oxd-topbar-body-nav-tab-item']")).click();


        //click on Data import
        driver.findElement(By.partialLinkText("Data ")).click();

        //click on browse button
        driver.findElement(By.xpath("//div[@class='oxd-file-button']")).click();

        Thread.sleep(5000);//pause of 5 seconds

        Runtime.getRuntime().exec("C://Users//ASUS//Desktop//CS_SeleniumExercises//SeleniumPractice//FileUploadOrangeHRM.exe");

        Thread.sleep(5000);

        //click on upload button
        driver.findElement(By.xpath("//button[@type='submit']")).submit();

        logOut();
    }

    @Test
    public void deleteEmployee() throws InterruptedException
    {
        logIn();

        //find PIM Menu and click on PIM Menu
        driver.findElement(By.xpath("//span[text()='PIM']")).click();

        //Select Employee List
        driver.findElement(By.xpath("//a[text()='Employee List']")).click();

        //enter employee name
        driver.findElements(By.tagName("input")).get(1).sendKeys("Odis");
        //driver.findElement(By.tagName("input")).get(1).sendKeys("Nesta");
        //Click the search button.
        driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();
        Thread.sleep(3000);
        //click on delete icon of the record
        driver.findElement(By.xpath("//i[@class='oxd-icon bi-trash']")).click();
        //click on yes, delete messaage button
        driver.findElement(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin']")).click();
        //check for message "No Record Found"
        String msg = driver.findElement(By.xpath("(//span[@class='oxd-text oxd-text--span'])[1]")).getText();
        Assert.assertEquals(msg, "No Records Found");
        Thread.sleep(5000);
        logOut();
    }
    @Test
    public void ListEmployees() throws InterruptedException
    {
        logIn();
        //find PIM Menu and click on PIM Menu
        driver.findElement(By.xpath("//span[text()='PIM']")).click();

        //Select Employee List
        driver.findElement(By.xpath("//a[normalize-space()='Employee List']")).click();
        Thread.sleep(3000);

        //find total links
        List<WebElement> totalLinksElements = driver.findElements(By.xpath("//ul[@class='oxd-pagination__ul']/li"));

        int totalLinks = totalLinksElements.size();

        for (int i=0; i<totalLinks; i++ )//0,1,2,3,
        {

            try
            {
                String currentLinkText = totalLinksElements.get(i).getText();


                int page = Integer.parseInt(currentLinkText);
                System.out.println("Page: " + page);

                totalLinksElements.get(i).click();

                Thread.sleep(2000);

                List <WebElement> emp_list = driver.findElements(By.xpath("//div[@class='oxd-table-card']/div /div[4]"));

                for(int j=0; j<emp_list.size();j++)
                {
                    //print last name of each row
                    String lastName = emp_list.get(j).getText();
                    System.out.println(lastName);
                }
            }
            catch(Exception e)
            {
                System.out.println("Not a number.");
            }


        }
        Thread.sleep(5000);
        logOut();
    }
    @Test
    public void applyLeave() throws InterruptedException
    {
        //find username and enter username "Admin"
        driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");

        //find password and enter password admin123
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");

        //login button click
        driver.findElement(By.xpath("//button[@type='submit']")).submit();


        //click on leave menu
        driver.findElement(By.linkText("Leave")).click();

        //click on Apply menu
        driver.findElement(By.linkText("Apply")).click();

        //click on leave type drop down
        driver.findElement(By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow']")).click();

        //select CAN-FMLA option from leave type dropdown
        driver.findElement(By.xpath("//*[contains(text(),'CAN')]")).click();

        //enter from date
        driver.findElement(By.xpath("//div[@class='oxd-date-input']/input")).sendKeys("2024-08-04");


        //enter comment
        driver.findElement(By.tagName("textarea")).sendKeys("This is my personal leave");
        Thread.sleep(3000);


        //click on Apply button
        driver.findElement(By.xpath("//button[@type='submit']")).click();


        Thread.sleep(5000);
        logOut();

    }


    //-----------------------function----------------------------
    public void getText(By locator) {
        driver.findElement(locator).getText();
    }
    public void url(String url) {
        driver.get(url);
    }
    public void maximize() {
        driver.manage().window().maximize();
    }
    public void quit() {
        driver.quit();
    }
    public void type(String name, String value) {
        driver.findElement(By.name(name)).sendKeys(value);
    }
    public void click(By locator) {
        driver.findElement(locator ).click();
    }
    public void logIn()
    {
        driver = new EdgeDriver();
        url("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        maximize();
        //find username and enter username "Admin"
        driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");

        //find password and enter password admin123
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");

        //login button click
        driver.findElement(By.xpath("//button[@type='submit']")).submit();

    }

    public void logOut()
    {
        driver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).click();
        //driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();

        List <WebElement> elementlist = driver.findElements(By.xpath("//a[@class='oxd-userdropdown-link']"));


        elementlist.get(3).click();//click on logout

    }


}
