package com.lits.rubinskyy.hw1;

import com.lits.calculator.Calculator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class OperationRevertTest {
    @Test(dataProvider = "data",
            description = "Check revert operation",
            groups = {"smoke", "regression"})
    public void testAddTwoNumbers(BigDecimal a, BigDecimal b, BigDecimal c, BigDecimal result) {
        Calculator calculator = new Calculator();
        // WHEN
        calculator.setValue(a);
        calculator.multiply(b);
        calculator.subtract(c);
        calculator.revert();

        // THEN
        int compareResult = result.compareTo(calculator.getCurrentAmount());
        Assert.assertEquals(compareResult, 0);
    }

    @DataProvider(parallel = true)
    public Object[][] data() {
        return new Object[][]{
                {BigDecimal.valueOf(3), BigDecimal.valueOf(7), BigDecimal.valueOf(2), BigDecimal.valueOf(21)}
        };
    }

}
