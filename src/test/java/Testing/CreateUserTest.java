package Testing;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import junit.framework.Assert;

public class CreateUserTest {
	WebDriver driver = new EdgeDriver();
	String username = new Faker().name().firstName() + "007";
	String password = "Aniket@123";
	WebDriverWait wait;

	@BeforeClass
	public void setup() {
		driver.get("http://137.184.76.209/orangehrm-4.9");
		System.out.println(username);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@Test
	public void createUser() throws InterruptedException {
		driver.findElement(By.xpath("//input[contains(@name, 'Username')]")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[contains(@name, 'Password')]")).sendKeys("ASDFwhuhpp3lhdz3k47iw%");
		driver.findElement(By.xpath("//input[@type='submit' and contains(@id, 'Login')]")).click();

		driver.findElement(By.xpath("//a//*[text()='Admin']")).click();
		driver.findElement(By.xpath("//input[@name='btnAdd']")).click();

		driver.findElement(By.xpath("//input[@name='systemUser[employeeName][empName]']")).sendKeys("Mukesh Otwani");
		driver.findElement(By.xpath("//input[@name='systemUser[userName]']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='systemUser[password]']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@name='systemUser[confirmPassword]']")).sendKeys(password);

		Thread.sleep(1500);
		driver.findElement(By.xpath("//input[@name='btnSave']")).click();
		wait.until(ExpectedConditions.urlContains("viewSystemUsers"));
		driver.findElement(By.xpath("//a[@id='welcome']")).click();

		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		Assert.assertTrue(wait.until(ExpectedConditions.urlContains("login")));
	}

	@Test(priority = 6)
	public void SignInWithNewUser() throws InterruptedException {
		driver.findElement(By.xpath("//input[contains(@name, 'Username')]")).sendKeys(username);
		driver.findElement(By.xpath("//input[contains(@name, 'Password')]")).sendKeys(password);
		driver.findElement(By.xpath("//input[@type='submit' and contains(@id, 'Login')]")).click();

		Assert.assertTrue(wait.until(ExpectedConditions.urlContains("dashboard")));
	}

	@AfterClass
	public void teardown() {
		driver.quit();
	}
}
