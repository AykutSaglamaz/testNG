package com.techproed.tests;

import com.techproed.utilities.Driver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Day11_C1_FirstDriverTest {

    @Test
    public void amazonTitleTest(){
        // driver ----->>>>>> Driver.getDriver()
        Driver.getDriver().get("https://www.amazon.com");

        String actualTitle = Driver.getDriver().getTitle();

        Assert.assertTrue(actualTitle.contains("Amazon"));
    }
}
