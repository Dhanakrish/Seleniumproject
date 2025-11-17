package test;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class OrderCompletion {
    private static final Logger logger= LogManager.getLogger(OrderCompletion.class);
    WebDriver driver;



    @BeforeMethod
    public void setUp() {
        logger.info("Starting browser");
        driver = new FirefoxDriver();
        logger.info("open the webiste");
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

    }

    @Test
    public void checkOut(){
        logger.info("Logging into SauceDemo");
       driver.findElement(By.id("user-name")).sendKeys("standard_user");
       driver.findElement(By.id("password")).sendKeys("secret_sauce");
       driver.findElement(By.id("login-button")).click();

       //Add the items to cart
        driver.findElement(By.cssSelector("button[data-test='add-to-cart-sauce-labs-backpack']")).click();
        driver.findElement(By.cssSelector("button[data-test='add-to-cart-sauce-labs-bike-light']")).click();
        String cartCount=driver.findElement(By.className("shopping_cart_badge")).getText();
        Assert.assertEquals(cartCount,"2");

        driver.findElement(By.cssSelector("a[data-test='shopping-cart-link']")).click();

        driver.findElement(By.cssSelector("button[data-test='checkout']")).click();
        driver.findElement(By.cssSelector("input[data-test='firstName']")).sendKeys("John");
        driver.findElement(By.cssSelector("input[data-test='lastName']")).sendKeys("Doe");
        driver.findElement(By.cssSelector("input[data-test='postalCode']")).sendKeys("12345");
        driver.findElement(By.cssSelector("input[data-test='continue']")).click();

        String overviewTitle=driver.findElement(By.className("title")).getText();
        Assert.assertEquals(overviewTitle,"Checkout: Overview");


        driver.findElement(By.id("finish")).click();
        String finshTitle=driver.findElement(By.className("title")).getText();
        Assert.assertEquals(finshTitle,"Checkout: Complete!");

        String completedtitle=driver.findElement(By.className("complete-header")).getText();
        Assert.assertEquals(completedtitle,"Thank you for your order!");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
