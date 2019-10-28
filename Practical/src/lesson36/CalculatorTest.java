package lesson36;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(value = Parameterized.class)
public class CalculatorTest {

    private static Calculator calc;

    private int[] intArray;
    private int[] intResArray;
    private int[] intArray2;
    private boolean resBool2;

    @BeforeClass
    public static void init() {
        System.out.println("init calc");
        calc = new Calculator();
    }

    public CalculatorTest(int[] intArray, int[] intResArray, int[] intResArray2, boolean resBool2) {
        this.intArray = intArray;
        this.intResArray = intResArray;
        this.intArray2 = intResArray2;
        this.resBool2 = resBool2;
    }

    @Parameterized.Parameters
    public static Collection abracadabra() {
        return Arrays.asList(new Object[][]{
                        {new int[]{1, 2, 3, 4, 5, 6}, new int[]{5, 6}, new int[]{1, 2, 3}, true},
                        {new int[]{1, 2, 4, 5, 4, 6}, new int[]{6}, new int[]{2, 3, 4}, true},
                        {new int[]{1, 4, 3, 5, 6}, new int[]{3, 5, 6}, new int[]{2, 3, 5}, false},
                        {new int[]{1, 3, 5, 4}, new int[]{}, new int[]{1, 2, 3, 4, 5}, true},
                        {new int[]{1, 3, 5}, new int[]{}, new int[]{1, 2, 3, 4}, true},
                        {null, new int[]{}, null, false},
                }
        );
    }


    @Test
    public void metod1() {
        try {
            Assert.assertTrue("Arrays are not equal", java.util.Arrays.equals(calc.metod1(this.intArray), this.intResArray));
        } catch (RuntimeException thrown) {
            Assert.assertNotEquals("", thrown.getMessage());
        }
    }

    @Test
    public void metod2() {
        Assert.assertEquals(calc.metod2(this.intArray2), this.resBool2);
    }
}