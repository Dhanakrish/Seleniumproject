package test;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AssertionDemo {

    @Test
    public void verifyLoginTitle() {
        System.out.println("Verify Login Title");
        String expected="OrangeHRM";
        String actual="OrangeHRM";
        Assert.assertEquals(actual, expected);
        System.out.println("Testcase has passed");
    }
}
