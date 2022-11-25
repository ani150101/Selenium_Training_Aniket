package Day5;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

public class UploadAutoit {

	public static void main(String[] args) throws IOException, InterruptedException {
		WebDriver driver = new EdgeDriver();
		Actions act = new Actions(driver);
		
		driver.get("https://the-internet.herokuapp.com/upload");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		Thread.sleep(2000);
		WebElement uploadbtn = driver.findElement(By.xpath("//input[contains(@id, 'upload')]"));
		act.moveToElement(uploadbtn).click().perform();
		Runtime.getRuntime().exec("C:\\Users\\anisingh12\\Documents\\uploadTest.exe");
		
		driver.findElement(By.xpath("//input[@id='file-submit']")).click();

	}

}
