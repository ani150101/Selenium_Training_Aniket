package Testing;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class LoginTest {
	WebDriver driver;

	@BeforeClass
	public void setup() {
		driver = new EdgeDriver();
		driver.get("http://137.184.76.209/orangehrm-4.9");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
	}

	@Test(priority = 1)
	public void noUsernameNoPassword() {
		driver.findElement(By.xpath("//input[contains(@name, 'Username')]")).clear();
		driver.findElement(By.xpath("//input[contains(@name, 'Password')]")).clear();
		driver.findElement(By.xpath("//input[@type='submit' and contains(@id, 'Login')]")).click();
		Assert.assertEquals(
				driver.findElement(By.xpath("//input[@type='submit' and contains(@id, 'Login')]//following::span[1]"))
						.getText(),
				"Username cannot be empty");
	}

	@Test(priority = 1)
	public void invalidUsernameNoPassword() {
		driver.findElement(By.xpath("//input[contains(@name, 'Username')]")).sendKeys("admin");
		driver.findElement(By.xpath("//input[contains(@name, 'Password')]")).clear();
		driver.findElement(By.xpath("//input[@type='submit' and contains(@id, 'Login')]")).click();
		Assert.assertEquals(
				driver.findElement(By.xpath("//input[@type='submit' and contains(@id, 'Login')]//following::span[1]"))
						.getText(),
				"Password cannot be empty");

	}

	@Test(priority = 1)
	public void noUsernamePassword() {
		driver.findElement(By.xpath("//input[contains(@name, 'Username')]")).clear();
		driver.findElement(By.xpath("//input[contains(@name, 'Password')]")).sendKeys("aisjdias");
		driver.findElement(By.xpath("//input[@type='submit' and contains(@id, 'Login')]")).click();
		Assert.assertEquals(
				driver.findElement(By.xpath("//input[@type='submit' and contains(@id, 'Login')]//following::span[1]"))
						.getText(),
				"Username cannot be empty");
	}

	@Test(priority = 1)
	public void validUsernameInvalidPassword() {
		driver.findElement(By.xpath("//input[contains(@name, 'Username')]")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[contains(@name, 'Password')]")).sendKeys("asduhudef");
		driver.findElement(By.xpath("//input[@type='submit' and contains(@id, 'Login')]")).click();
		Assert.assertEquals(
				driver.findElement(By.xpath("//input[@type='submit' and contains(@id, 'Login')]//following::span[1]"))
						.getText(),
				"Invalid credentials");
	}

	@Test(priority = 2)
	public void validUsernameValidPassword() {
		driver.findElement(By.xpath("//input[contains(@name, 'Username')]")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[contains(@name, 'Password')]")).sendKeys("ASDFwhuhpp3lhdz3k47iw%");
		driver.findElement(By.xpath("//input[@type='submit' and contains(@id, 'Login')]")).click();

		Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
		if (driver.getCurrentUrl().contains("dashboard")) {
			driver.findElement(By.xpath("//a[contains(text(), 'Welcome')]")).click();
			driver.findElement(By.xpath("//div[@id='welcome-menu']//a[contains(text(), 'Logout')]")).click();
		}
	}
	
	@AfterClass
	public void teardown() {
		driver.quit();
	}
}
