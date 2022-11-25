package Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ReadingExcel {

	@Test
	public void testData() {

		try {
			XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(new File("./testdata/SapientTestData.xlsx")));
//			XSSFSheet sheet = wb.getSheet("LoginData");
//			XSSFRow r1 = sheet.getRow(0);
//			XSSFCell c1 = r1.getCell(0);
//			String email = wb.getSheet("LoginData").getRow(0).getCell(0).getStringCellValue();

			int rows = wb.getSheet("LoginData").getPhysicalNumberOfRows();
			int columns = wb.getSheet("LoginData").getRow(0).getPhysicalNumberOfCells();

			Object[][] arr = new Object[rows][columns];

			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					arr[i][j] = wb.getSheet("LoginData").getRow(i).getCell(j);
				}
			}
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					System.out.print(arr[i][j] + " ");
				}
				System.out.println();
			}

		} catch (FileNotFoundException e) {
			System.out.println("Please check your file path: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Somehting went wrong IO: " + e.getMessage());
		}
	}
}
