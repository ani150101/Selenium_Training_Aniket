package com.selenium.training;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ManageCategoryDay3 {
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("https://ineuron-courses.vercel.app/login");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.findElement(By.xpath("//input[contains(@name, 'email') or contains(@id, 'email')]"))
				.sendKeys("ineuron@ineuron.ai");
		driver.findElement(By.xpath("//input[contains(@name, 'password') or contains(@id, 'password')]"))
				.sendKeys("ineuron");
		driver.findElement(By.xpath("//button[contains(text(), 'Sign in')]")).click();

		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//span[contains(text(), 'Manage')]"));
		action.moveToElement(element).perform();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		WebElement manageCateg = wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(), 'Manage Categories')]")));
		manageCateg.click();

		String parent = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
		List<String> WindowList = new ArrayList<>(allWindows);
		driver.switchTo().window(WindowList.get(1));

		String myCategory = "Gaming";

		driver.findElement(By.xpath("//button[contains(text(), 'New Category')]")).click();
		Alert alrt = wait.until(ExpectedConditions.alertIsPresent());
		alrt.sendKeys(myCategory);
		alrt.accept();

		List<WebElement> lWebElements = driver.findElements(By.xpath("//td[contains(text(), '" + myCategory + "')]"));
		if (lWebElements.size() > 0)
			System.out.println(myCategory + " created successfully!!");
		Thread.sleep(1500);
        WebElement categ = driver.findElement(By.xpath("//td[contains(text(), '" +
        		myCategory + "')]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scrollTo(0, document.body.scrollHeight);");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//td[contains(text(), '" + myCategory + "')]//following::td[1]/button")).click();
		driver.findElement(By.xpath("//div[contains(@class, 'modal')]//button[contains(text(), 'Delete')]")).click();
		new WebDriverWait(driver, Duration.ofSeconds(3)).until(
				ExpectedConditions.numberOfElementsToBe(By.xpath("//td[contains(text(), '" + myCategory + "')]"), 0));
		List<WebElement> lWebElementsDel = driver
				.findElements(By.xpath("//td[contains(text(), '" + myCategory + "')]"));

		if (lWebElementsDel.size() == 0)
			System.out.println(myCategory + " deleted successfully!!");
		driver.close();
		driver.switchTo().window(parent);
		driver.findElement(By.xpath("//button[contains(text(), 'Sign out')]")).click();
	}
}
