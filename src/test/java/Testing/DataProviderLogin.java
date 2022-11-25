package Testing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import javax.swing.plaf.basic.BasicArrowButton;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class DataProviderLogin {
	WebDriver driver;
	WebDriverWait wait;
	XSSFWorkbook wb;
	File file;
	FileOutputStream fos;
	List<String> result;

	@BeforeMethod
	public void setup() throws FileNotFoundException {
		driver = new EdgeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(7));
		driver.get("http://ineuron-courses.vercel.app/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		file = new File("./testdata/SapientTestData.xlsx");
		fos = new FileOutputStream(new File("./testdata/SapientTestData1.xlsx"));
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
	@AfterClass
	public void tearDownClass() {
		try {
			wb.write(new FileOutputStream(new File("./testdata/SapientTestData1.xlsx")));
		//	wb.close();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test(dataProvider = "logindataexcel")
	public void login(String email, String password) throws FileNotFoundException, IOException {
		driver.findElement(By.xpath("//input[contains(@placeholder, 'Email')]")).sendKeys(email);
		driver.findElement(By.xpath("//input[contains(@placeholder, 'Password')]")).sendKeys(password);
		driver.findElement(By.xpath("//button[contains(text(), 'Sign in')]")).click();
		boolean wlcmMsg = driver.findElements(By.xpath("//h4[contains(@class, 'welcome')]")).size() > 0;

		driver.findElement(By.xpath("//button[contains(text(), 'Sign out')]")).click();

		boolean logoutChk = wait.until(ExpectedConditions.urlContains("login"));
		wb = new XSSFWorkbook(new FileInputStream(file));
		XSSFSheet sheet = wb.getSheet("LoginData");
		int rows = sheet.getPhysicalNumberOfRows();
		int columns = sheet.getRow(0).getPhysicalNumberOfCells();
		int targetRow = -999;
		for (int i = 0; i < rows; i++) {
			if (sheet.getRow(i).getCell(0).getStringCellValue().equals(email)) {
				targetRow = i;
				break;
			}
		}
		try {
			if (wlcmMsg && logoutChk) {
				result.add("PASS");
				sheet.getRow(targetRow).createCell(2).setCellValue("PASS");
				System.out.println("PASS");
			} else {
				result.add("FAIL");
				sheet.getRow(targetRow).createCell(2).setCellValue("FAIL");
				System.out.println("FAIL");
			}
		} catch (Exception e) {
		}
		
		Assert.assertTrue(wlcmMsg && logoutChk);

	}

	@DataProvider(name = "logindataexcel")
	public Object[][] iprovideDataExcel() throws FileNotFoundException, IOException {
		Object arr[][] = null;
		wb = new XSSFWorkbook(new FileInputStream(new File("./testdata/SapientTestData.xlsx")));
		int rows = wb.getSheet("LoginData").getPhysicalNumberOfRows();
		int columns = wb.getSheet("LoginData").getRow(0).getPhysicalNumberOfCells();
		arr = new Object[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				arr[i][j] = wb.getSheet("LoginData").getRow(i).getCell(j).getStringCellValue();
			}
		}

		return arr;
	}

	@DataProvider(name = "logindata")
	public Object[][] iProvideDataHardcoded() {
		String[][] arr = new String[4][2];

		arr[0][0] = "sample9@gmail.com";
		arr[0][1] = "Mukesh1234";
		arr[1][0] = "samplemukesh@gmail.com";
		arr[1][1] = "Mukesh1234";
		arr[2][0] = "samplemukesh1@gmail.com";
		arr[2][1] = "Mukesh1234";
		arr[3][0] = "samplemukesh2@gmail.com";
		arr[3][1] = "Mukesh1234";
		return arr;
	}
}
