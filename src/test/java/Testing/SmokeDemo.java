package Testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SmokeDemo {
	WebDriver driver;

	@BeforeClass
	public void setup() {
		driver = new EdgeDriver();
		driver.get("http://137.184.76.209/orangehrm-4.9");
	}

	@Test
	public void verifyUrl() {
		Assert.assertTrue(driver.getCurrentUrl().contains("index.php"));
	}

	@Test
	public void verifyTitle() {
		Assert.assertTrue(driver.getTitle().contains("OrangeHRM"));
	}

	@Test
	public void verifySocialMediaCount() {
		Assert.assertEquals(driver.findElements(By.xpath("//div[@id='social-icons']//img")).size(), 4);
	}

	@Test
	public void verifyForgotPassword() {
		Assert.assertTrue(driver.findElement(By.linkText("Forgot your password?")).isDisplayed());
	}

	@AfterClass
	public void teardown() {
		driver.quit();
	}
}
