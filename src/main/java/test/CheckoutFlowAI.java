package test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

public class CheckoutFlowAI {

    private static final Logger logger = LogManager.getLogger(CheckoutFlowAI.class);
    WebDriver driver;

    @BeforeMethod
    public void setup() {

        System.setProperty("webdriver.gecko.driver", "C:\\drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        logger.info("Browser launched successfully");

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.saucedemo.com/");
        logger.info("Navigated to SauceDemo website");
    }

    @Test
    public void completeCheckoutTest() {

        // Login
        logger.info("Logging into SauceDemo");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // Add 2 items to cart
        logger.info("Adding items to cart");
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();

        // Verify cart count = 2
        String cartCount = driver.findElement(By.className("shopping_cart_badge")).getText();
        Assert.assertEquals(cartCount, "2", "Cart count mismatch");

        // Go to Cart
        logger.info("Opening cart page");
        driver.findElement(By.className("shopping_cart_link")).click();

        // Click Checkout
        logger.info("Proceeding to checkout");
        driver.findElement(By.id("checkout")).click();

        // Fill checkout info
        logger.info("Entering checkout information");
        driver.findElement(By.id("first-name")).sendKeys("Dhana");
        driver.findElement(By.id("last-name")).sendKeys("Shekar");
        driver.findElement(By.id("postal-code")).sendKeys("560001");
        driver.findElement(By.id("continue")).click();

        // Verify Overview Page Loaded
        logger.info("Verifying overview page");
        String title = driver.findElement(By.className("title")).getText();
        Assert.assertEquals(title, "Checkout: Overview", "Incorrect overview page title");

        // Finish checkout
        logger.info("Finishing order");
        driver.findElement(By.id("finish")).click();

        // Verify order completion
        logger.info("Verifying order completion message");
        String successMsg = driver.findElement(By.className("complete-header")).getText();
        Assert.assertEquals(successMsg, "Thank you for your order!", "Order success message mismatch");

        logger.info("✔ Order completed successfully!");
    }

    @AfterMethod
    public void tearDown() {
        logger.info("Closing browser");
        driver.quit();
    }
}
