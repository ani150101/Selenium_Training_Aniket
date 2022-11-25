package com.selenium.training;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class CalendarWithoutLoop {
    private static WebDriver driver = new EdgeDriver();

    public static void main(String[] args) throws InterruptedException {
        // withoutLoop();
        // withLoop();
        DropdownWithoutLoop();
        // DropdownWithLoop();
    }
    
    private static void DropdownWithoutLoop() {
        driver.get("http://seleniumpractise.blogspot.com/2016/08/bootstrap-dropdown-example-for-selenium.html");
        driver.findElement(By.xpath("//div[contains(@class, 'dropdown')]/button[contains(text(), 'Tutorials')]")).click();
        driver.findElement(By.xpath("//div[contains(@class, 'dropdown')]/ul[contains(@class,'dropdown')]//a[contains(text(), 'JavaScript')]")).click();
    }

    private static void DropdownWithLoop() throws InterruptedException {
        driver.get("http://seleniumpractise.blogspot.com/2016/08/bootstrap-dropdown-example-for-selenium.html");
        driver.findElement(By.xpath("//div[contains(@class, 'dropdown')]/button[contains(text(), 'Tutorials')]")).click();
        List<WebElement> allElements = driver
                .findElements(By.xpath("//div[contains(@class, 'dropdown')]/ul[contains(@class,'dropdown')]//a"));
        for (WebElement ele : allElements) {
            if (ele.getText().equalsIgnoreCase("javascript")) {
                ele.click();
                break;
            }
        }
    }

    private static void withLoop() {
        driver.get("http://seleniumpractise.blogspot.com/2016/08/how-to-handle-calendar-in-selenium.html");
        driver.findElement(By.xpath("//input[contains(@id, 'datepicker')]")).click();
        List<WebElement> allDateHrefs = driver.findElements(By.xpath("//table[contains(@class, 'calendar')]//a"));
        for (WebElement dateHref : allDateHrefs) {
            if (dateHref.getText().equals("26")) {
                dateHref.click();
                break;
            }
        }
    }

    private static void withoutLoop() {
        driver.get("http://seleniumpractise.blogspot.com/2016/08/how-to-handle-calendar-in-selenium.html");
        driver.findElement(By.id("datepicker")).click();
        driver.findElement(By.xpath("//td[@data-handler='selectDay']/a[text()='25']")).click();
    }
}
