package tests;

import org.testng.annotations.Test;

public class GroupDemo {

    @Test(priority = 1, groups = "Smoke")
    public void loginTest() {
        System.out.println("Login test executed");
    }

    @Test(priority = 2, groups = "Regression")
    public void dashboardTest() {
        System.out.println("Dashboard test executed");
    }

    @Test(priority = 3, dependsOnMethods = "loginTest")
    public void logoutTest() {
        System.out.println("Logout test executed");
    }
}
