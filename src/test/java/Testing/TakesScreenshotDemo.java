package Testing;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

public class TakesScreenshotDemo {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.get("http://google.com");

		TakesScreenshot ts = (TakesScreenshot) driver;

		File src = ts.getScreenshotAs(OutputType.FILE);

		File dest = new File("./Aniket.png");

		try {
			FileHandler.copy(src, dest);
		} catch (IOException e) {
			System.out.println("Failed " + e.getMessage());
		}

	}
}
