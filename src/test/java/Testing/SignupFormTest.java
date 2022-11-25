package Testing;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import junit.framework.Assert;

public class SignupFormTest {
	WebDriver driver;
	String name = new Faker().name().firstName();
	String email = name.toLowerCase() + "@gmail.com";
	String password = "Abcd@1234";

	@BeforeClass
	public void setup() {
		driver = new EdgeDriver();
		driver.get("http://ineuron-courses.vercel.app/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		driver.findElement(By.linkText("New user? Signup")).click();
	}

	@AfterClass
	public void teardown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void fillForm() {

		driver.findElement(By.xpath("//input[contains(@placeholder, 'Name')]")).sendKeys(name);
		driver.findElement(By.xpath("//input[contains(@placeholder, 'Email')]")).sendKeys(email);
		driver.findElement(By.xpath("//input[contains(@placeholder, 'Password')]")).sendKeys(password);
		Reporter.log(name + ", " + email + ":" + password);
		driver.findElement(By.xpath("//label[contains(text(),'Quantum Physics')]")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement signupBtn = driver.findElement(By.xpath("//button[contains(text(), 'Sign up')]"));

		js.executeScript("arguments[0].scrollIntoView();", signupBtn);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		driver.findElement(By.xpath("//input[@type='radio' and contains(@value, 'Male')]")).click();
		WebElement stateDropdown = driver
				.findElement(By.xpath("//select[contains(@id, 'state') or contains(@name, 'state')]"));
		Select stateSelect = new Select(stateDropdown);
		stateSelect.selectByVisibleText("Uttar Pradesh");

		js.executeScript("arguments[0].scrollIntoView();", signupBtn);
		signupBtn.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		Assert.assertTrue(wait.until(ExpectedConditions.urlContains("login")));
	}

	@Test(priority = 3, dependsOnMethods = "fillForm")
	public void login() {
		driver.findElement(By.xpath("//input[contains(@placeholder, 'Email')]")).sendKeys(email);
		driver.findElement(By.xpath("//input[contains(@placeholder, 'Password')]")).sendKeys(password);
		driver.findElement(By.xpath("//button[contains(text(), 'Sign in')]")).click();

		Assert.assertEquals(driver.findElements(By.xpath("//h4[contains(@class, 'welcome')]")).size(), 1);
	}
}
