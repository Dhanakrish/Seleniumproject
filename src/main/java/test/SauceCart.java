package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SauceCart {
    private static final Logger logger = LogManager.getLogger(SauceCart.class);
    WebDriver driver = new FirefoxDriver();
    @BeforeMethod
    public void setup() {


        logger.info("Browser launched successfully");

        driver.get("https://www.saucedemo.com/");
        logger.info("Navigated to SauceDemo website");

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @Test
            public void loginTest() {
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

        if (cartCount.equals("2")) {
            logger.info("The cart count is correct: " + cartCount);
        } else {
            logger.error("The cart count is incorrect! Found: " + cartCount);
        }
    }
    @AfterMethod
    public void tearDown() {


        logger.info("Closing browser");
        driver.quit();
    }
}
