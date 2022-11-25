package Testing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.basic.BasicArrowButton;

import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class DataProviderLoginWithStatus {
	WebDriver driver;
	WebDriverWait wait;
	XSSFWorkbook wb;
	File file;
	FileOutputStream fos;
	List<String> result;

	@BeforeClass
	public void setupResults() {
		result = new ArrayList<>();
	}

	@BeforeMethod
	public void setup() throws FileNotFoundException {
		driver = new EdgeDriver(new EdgeOptions().setHeadless(true));
		wait = new WebDriverWait(driver, Duration.ofSeconds(7));
		driver.get("http://ineuron-courses.vercel.app/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		file = new File("./testdata/SapientTestData.xlsx");
		fos = new FileOutputStream(new File("./testdata/SapientTestDataStatus.xlsx"));
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

	@AfterClass
	public void tearDownClass() {

		System.out.println("Results " + result);

		try {
			wb = new XSSFWorkbook(new FileInputStream(new File("./testdata/SapientTestData.xlsx")));

			for (int i = 0; i < wb.getSheet("LoginData").getPhysicalNumberOfRows(); i++) {
				CellStyle style = wb.createCellStyle();
				Font font = wb.createFont();
				if (result.get(i).equals("PASS")) {
					font.setColor(HSSFColorPredefined.GREEN.getIndex());
					style.setFont(font);
				} else {
					font.setColor(HSSFColorPredefined.RED.getIndex());
					style.setFont(font);
				}
				XSSFCell cell = wb.getSheet("LoginData").getRow(i).createCell(2);
				cell.setCellStyle(style);
				cell.setCellValue(result.get(i));

			}

			wb.write(new FileOutputStream(new File("./testdata/SapientTestDataStatus.xlsx")));
			// wb.close();
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

		if (wlcmMsg) {
			result.add("PASS");
		} else {
			result.add("FAIL");
		}

		driver.findElement(By.xpath("//button[contains(text(), 'Sign out')]")).click();

		boolean logoutChk = wait.until(ExpectedConditions.urlContains("login"));

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
