package Day5;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class TestingBrowserStack {
	WebDriver driver;
	URL url = null;
	WebDriverWait wait;

	DesiredCapabilities capabilities;

	@Parameters({ "os", "osVersion", "browserName", "browserVersion" })
	@BeforeMethod
	public void setup (String os, String osVersion, String browserName, String browserVersion) {
		capabilities = new DesiredCapabilities();

		try {
			url = new URL("https://aniketsingh_3BXyqS:Wyynmtpjxf32ioMK2nGH@hub-cloud.browserstack.com/wd/hub");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		capabilities.setCapability("os", os);
		capabilities.setCapability("osVersion", osVersion);
		capabilities.setCapability("browserName", browserName);
		capabilities.setCapability("browserVersion", browserVersion);
		driver = new RemoteWebDriver(url, capabilities);
		driver.get("http://ineuron-courses.vercel.app/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

	@Test
	public void testLoginBrowsers() {
		driver.findElement(By.xpath("//input[contains(@placeholder, 'Email')]")).sendKeys("sample9@gmail.com");
		driver.findElement(By.xpath("//input[contains(@placeholder, 'Password')]")).sendKeys("Mukesh1234");
		driver.findElement(By.xpath("//button[contains(text(), 'Sign in')]")).click();
		Assert.assertTrue(driver.findElements(By.xpath("//h4[contains(@class, 'welcome')]")).size() > 0);
	}
}
