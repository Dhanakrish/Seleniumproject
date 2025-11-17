package test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.SauceCart;

import java.time.Duration;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class RemoveCart {
    private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger(SauceCart.class);
    WebDriver driver=new FirefoxDriver();
    @BeforeMethod
    public void setDriver(){
        logger.info("Browser launched successfully");

        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void RemoveItemFromCartTest() {
        // Login flow
        logger.info("Entering username");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        logger.info("Entering password");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        logger.info("Clicking Login button");
        driver.findElement(By.id("login-button")).click();

        // Add items into cart flow
        logger.info("Adding Backpack to cart");
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        logger.info("Adding Bike Light to cart");
        driver.findElement(By.name("add-to-cart-sauce-labs-bike-light")).click();

        // Verify cart badge count
        logger.info("Checking cart count");
        String cartCount = driver.findElement(By.className("shopping_cart_badge")).getText();
        Assert.assertEquals(cartCount, "2","Initial cart count mismatch");

        //go to cart
        logger.info("Removing cart from cart");
        driver.findElement(By.className("shopping_cart_link")).click();
        // Remove one item
        logger.info("Removing cart from cart");
        driver.findElement(By.name("remove-sauce-labs-backpack")).click();

        //verify cart count to be 1
        logger.info("Chcking cart count");
        String UpdatedCount=driver.findElement(By.className("shopping_cart_badge")).getText();
        Assert.assertEquals(UpdatedCount,"1","Initial cart count mismatch");
        logger.info("Item removed successfully. updated count="+UpdatedCount);

    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }


}
