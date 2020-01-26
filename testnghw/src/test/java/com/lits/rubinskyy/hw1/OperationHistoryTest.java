package com.lits.rubinskyy.hw1;

import com.lits.calculator.Calculator;
import com.lits.calculator.DivideOperation;
import com.lits.calculator.MultiplyOperation;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class OperationHistoryTest {
    @Test(dataProvider = "data",
            description = "Check revert operation",
            groups = {"smoke", "regression"})
    public void testAddTwoNumbers(BigDecimal a, BigDecimal b, BigDecimal c) {
        Calculator calculator = new Calculator();
        // WHEN
        calculator.setValue(a);
        calculator.multiply(b);
        calculator.divide(c);

        System.out.println(calculator.getOperationsHistory().toString());

        // THEN
        Assert.assertTrue(calculator.getOperationsHistory().get(0) instanceof MultiplyOperation);
        Assert.assertTrue(calculator.getOperationsHistory().get(1) instanceof DivideOperation);
    }

    @DataProvider(parallel = true)
    public Object[][] data() {
        return new Object[][]{
                {BigDecimal.valueOf(6), BigDecimal.valueOf(7),BigDecimal.valueOf(2)}
        };
    }
}