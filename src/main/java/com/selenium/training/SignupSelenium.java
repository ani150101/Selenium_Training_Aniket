package com.selenium.training;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

public class SignupSelenium {
    public static void main(String[] args) throws InterruptedException {
        EdgeDriver driver = new EdgeDriver();

        driver.get("http://ineuron-courses.vercel.app/login");

        driver.findElement(By.linkText("New user? Signup")).click();
        Thread.sleep(5000);
        WebElement stateDropdown = driver.findElement(By.xpath("//select[contains(@id, 'state') or contains(@name, 'state')]"));
        Select stateSelect = new Select(stateDropdown);
        List<WebElement> statesList = stateSelect.getOptions();

        for (WebElement state : statesList) {
            if (state.getText().equalsIgnoreCase("Rajasthan"))
                break;
            else System.out.println(state.getText());
        }
    }
}
