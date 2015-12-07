package ca.jbrains.math.test;

import org.junit.Assert;
import org.junit.Test;

public class AddFractionsTest {
    @Test
    public void zeroPlusZero() throws Exception {
        Fraction sum = new Fraction(0).plus(new Fraction(0));

        Assert.assertEquals(0, sum.intValue());
    }

    @Test
    public void notZeroPlusZero() throws Exception {
        Fraction sum = new Fraction(7).plus(new Fraction(0));

        Assert.assertEquals(7, sum.intValue());
    }

    @Test
    public void zeroPlusNotZero() throws Exception {
        Fraction sum = new Fraction(0).plus(new Fraction(-4));

        Assert.assertEquals(-4, sum.intValue());
    }

    @Test
    public void integersNotBothZero() throws Exception {
        Fraction sum = new Fraction(10).plus(new Fraction(8));

        Assert.assertEquals(18, sum.intValue());
    }

    public static class Fraction {
        private final int intValue;

        public Fraction(int intValue) {
            this.intValue = intValue;
        }

        public Fraction plus(Fraction that) {
            return new Fraction(this.intValue + that.intValue);
        }

        public int intValue() {
            return intValue;
        }
    }
}
