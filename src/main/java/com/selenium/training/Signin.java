package com.selenium.training;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.edge.EdgeDriver;

public class Signin {
    public static void main(String[] args) {
        EdgeDriver driver = new EdgeDriver();
        driver.get("http://ineuron-courses.vercel.app/login");
        driver.findElement(By.name("email1")).sendKeys("SapientTraining@gmail.com");
        driver.findElement(By.name("password1")).sendKeys("Abcd@1234");

        driver.findElement(By.className("submit-btn")).click();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//div[@class='navbar-menu-links']/button[text()='Sign out']")).click();

        String title = driver.getTitle();
        System.out.println("Title is " + title);
        String url = driver.getCurrentUrl();
        System.out.println("URL is " + url);
        // driver.quit();
    }
}
