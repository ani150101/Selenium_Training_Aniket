package com.selenium.training;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HandleFrames {

    public static void main(String[] args) throws InterruptedException {
        // withExplicitWait();
        withoutExplicitWait();
    }

    private static void withoutExplicitWait() throws InterruptedException {
        WebDriver driver = new EdgeDriver();
        driver.get("http://seleniumpractise.blogspot.com/2019/01/alert-demo.html");
        driver.findElement(By.xpath("//button[text()='Try it']")).click();

        for (int i = 0; i < 20; i++) {
            try {
                Alert alrt = driver.switchTo().alert();
                String str = alrt.getText();
                if (str.contains("Selenium")) {
                    System.out.println("PASS!");
                    alrt.accept();
                    break;
                }
                else {
                    System.out.println("FAIL!!");
                }
            } catch (NoAlertPresentException e) {
                System.out.println("Waiting for WebElement");
                Thread.sleep(1000);
            }
        }
    }

    private static void withExplicitWait() {
        WebDriver driver = new EdgeDriver();
        driver.get("http://seleniumpractise.blogspot.com/2019/01/alert-demo.html");
        driver.findElement(By.xpath("//button[text()='Try it']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        Alert alrt = wait.until(ExpectedConditions.alertIsPresent());
        System.out.println(alrt.getText());

        if (alrt.getText().contains("Selenium"))
            System.out.println("PASS!");
        else
            System.out.println("FAIL!!");
        alrt.accept();
        
    }
}
