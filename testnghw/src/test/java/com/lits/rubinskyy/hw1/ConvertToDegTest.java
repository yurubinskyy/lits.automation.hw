package com.lits.rubinskyy.hw1;

import com.lits.calculator.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class ConvertToDegTest {

    @Test(dataProvider = "data",
            description = "Check to DEG conversion",
            groups = {"regression"})
    public void testAddTwoNumbers(BigDecimal a, BigDecimal result) {
        Calculator calculator = new Calculator();
        // WHEN
        calculator.setValue(a);
        calculator.convertToDEG();

        // THEN
        int compareResult = result.compareTo(calculator.getCurrentAmount());
        Assert.assertEquals(compareResult, 0);
    }

    @DataProvider(parallel = true)
    public Object[][] data() {
        return new Object[][]{
                {BigDecimal.valueOf(1), BigDecimal.valueOf(57)},
                {BigDecimal.valueOf(0), BigDecimal.valueOf(0)}
        };
    }
}
