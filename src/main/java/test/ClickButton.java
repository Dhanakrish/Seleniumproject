package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class ClickButton {
    @Test
    public void clickButton() {

        //open Firefox browser
        WebDriver driver = new FirefoxDriver();

        //Maximize the window
        driver.manage().window().maximize();

        //Go to the site
        driver.get("https://cura.com/");


        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until((ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='See a Doctor']"))));

        driver.findElement(By.xpath("//a[text()='See a Doctor']")).click();
        //(Optional) wait to see the next page before closing
        try {
            Thread.sleep(3000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        //Close the browser
        driver.quit();



    }
}
