package com.lits.rubinskyy.hw1;

import com.lits.calculator.Calculator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class ConvertToRadTest {
    @Test(dataProvider = "data",
            description = "Check to RAD conversion",
            groups = {"regression"})
    public void testAddTwoNumbers(BigDecimal a, BigDecimal result) {
        Calculator calculator = new Calculator();
        // WHEN
        calculator.setValue(a);
        calculator.convertToRAD();

        // THEN
        int compareResult = result.compareTo(calculator.getCurrentAmount());
        Assert.assertEquals(compareResult, 0);
    }

    @DataProvider(parallel = true)
    public Object[][] data() {
        return new Object[][]{
                {BigDecimal.valueOf(55), BigDecimal.valueOf(0.959931088596865)},
                {BigDecimal.valueOf(5), BigDecimal.valueOf(0.087266462599715)}
        };
    }
}
