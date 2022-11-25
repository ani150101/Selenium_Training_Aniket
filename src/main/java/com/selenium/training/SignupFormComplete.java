package com.selenium.training;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

public class SignupFormComplete {
    public static void main(String[] args) throws InterruptedException {
        EdgeDriver driver = new EdgeDriver();

        String name = "Anikn";
        String email = "anikn@gmail.com";
        String password = "Abcd@1234";

        driver.get("http://ineuron-courses.vercel.app/login");
        driver.manage().window().maximize();

        Thread.sleep(1000);
        driver.findElement(By.linkText("New user? Signup")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[contains(@placeholder, 'Name')]")).sendKeys(name);
        driver.findElement(By.xpath("//input[contains(@placeholder, 'Email')]")).sendKeys(email);
        driver.findElement(By.xpath("//input[contains(@placeholder, 'Password')]")).sendKeys(password);

        driver.findElement(By.xpath("//label[contains(text(),'selenium')]")).click();
        // driver.findElement(By.xpath("//input[@id='" + web.getAttribute("for") + "']")).click();
        driver.findElement(By.xpath("//input[@type='radio' and contains(@value, 'Male')]")).click();
        WebElement stateDropdown = driver
                .findElement(By.xpath("//select[contains(@id, 'state') or contains(@name, 'state')]"));
        Select stateSelect = new Select(stateDropdown);
        stateSelect.selectByVisibleText("Madhya Pradesh");
        Thread.sleep(1000);
        
        WebElement signupBtn = driver.findElement(By.xpath("//button[contains(text(), 'Sign up')]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", signupBtn);
//        signupBtn.click();
        Thread.sleep(2000);
        if (!driver.getCurrentUrl().contains("login")) {
            System.out.println("Something bad happened!!!");
            return;
        } else {
            driver.findElement(By.xpath("//input[contains(@placeholder, 'Email')]")).sendKeys("SapientTraining@gmail.com");
            driver.findElement(By.xpath("//input[contains(@placeholder, 'Password')]")).sendKeys("Abcd@1234");
            driver.findElement(By.xpath("//button[contains(text(), 'Sign in')]")).click();
        }
    }
}
