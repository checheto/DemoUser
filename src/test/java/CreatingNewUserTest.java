import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class CreatingNewUserTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.get("http://shop.pragmatic.bg/admin");
        driver.manage().window().maximize();
    }

    @Test
    public void creatingUserTest() {
        driver.findElement(By.id("input-username")).sendKeys("admin");
        WebElement passwordInputField = driver.findElement(By.id("input-password"));
        passwordInputField.sendKeys("parola123!");
        passwordInputField.submit();
        WebElement customersDropdown = driver.findElement(By.cssSelector("#menu-customer>a"));
        customersDropdown.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement customersLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#collapse5 > li:nth-child(1) > a")));
        customersLabel.click();
        WebElement plusButton = driver.findElement(By.xpath("//*[@class='fa fa-plus']/ .."));
        plusButton.click();
        driver.findElement(By.id("input-firstname")).sendKeys("Milen");
        driver.findElement(By.id("input-lastname")).sendKeys("Bozhinov");
        String prefix = RandomStringUtils.randomAlphabetic(7);
        String sufix = RandomStringUtils.randomAlphabetic(5);
        String domain = RandomStringUtils.randomAlphabetic(3);
        String emailAddress = prefix + "@" + sufix + "." + domain;
        driver.findElement(By.id("input-email")).sendKeys(emailAddress);

        //TODO: YOUR HOMEWORK STARTS HERE! BELOW!

        WebElement phoneNumberElement = driver.findElement(By.id("input-telephone"));
        String phoneNumber = RandomStringUtils.randomNumeric(8);
        phoneNumberElement.sendKeys(phoneNumber);

        WebElement passwordElement = driver.findElement(By.id("input-password"));
        passwordElement.sendKeys("parola123!");
        WebElement confirmPasswordElement = driver.findElement(By.id("input-confirm"));
        confirmPasswordElement.sendKeys("parola123!");

        WebElement newsletterDropdown = driver.findElement(By.id("input-newsletter"));
        Select newsletterSelect = new Select(newsletterDropdown);
        newsletterSelect.selectByValue("1");

        WebElement statusDropdown = driver.findElement(By.id("input-status"));
        Select statusSelect = new Select(statusDropdown);
        statusSelect.selectByValue("1");

        WebElement safeDropdonw = driver.findElement(By.id("input-safe"));
        Select selectSafe = new Select(safeDropdonw);
        selectSafe.selectByValue("1");

        WebElement submitRegistrationElement = driver.findElement(By.cssSelector(".pull-right>button"));
        submitRegistrationElement.click();

        WebElement filterByEmail = driver.findElement(By.id("input-email"));
        filterByEmail.sendKeys(emailAddress);
        driver.findElement(By.id("button-filter")).click();


        WebElement emailField = driver.findElement(By.xpath("//*[@id=\"form-customer\"]/table/tbody/tr[1]/td[3]"));
        String emailText = emailField.getText();
        Assert.assertEquals(emailText, emailAddress);
    }

    @AfterMethod
    public void quitDriver() {
        driver.quit();
    }

}
