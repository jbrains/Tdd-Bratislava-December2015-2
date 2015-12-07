package ca.jbrains.math.test;

import org.junit.Assert;
import org.junit.Ignore;
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

    public static class Fraction {
        private final int intValue;

        public Fraction(int intValue) {
            this.intValue = intValue;
        }

        public Fraction plus(Fraction that) {
            return this;
        }

        public int intValue() {
            return intValue;
        }
    }
}
