package utils;


import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.Map;

public class Validations {
    private Validations() { super();}


    @Step("Assert the response of the api")
    public static void validationTrue(boolean condition, String message){
        Assert.assertTrue(condition, message);
    }
    public static void validationFalse(boolean condition, String message){
        Assert.assertFalse(condition, message);
    }
    public static void validationEqual(boolean condition, String message){
        Assert.assertEquals(condition, message);
    }

    @Step("Assert that the response equal the actual entered data")
    public static void EqualValidation(Map actual, Map Expected, String message){
        Assert.assertEquals(actual,Expected,message);
    }

}
