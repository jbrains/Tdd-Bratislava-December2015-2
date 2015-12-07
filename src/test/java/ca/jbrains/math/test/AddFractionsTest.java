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

    @Test
    public void sameDenominator() throws Exception {
        Fraction sum = new Fraction(1, 5).plus(new Fraction(2, 5));

        Assert.assertEquals(3, sum.getNumerator());
        Assert.assertEquals(5, sum.getDenominator());
    }

    public static class Fraction {
        private int numerator;
        private int denominator;

        public Fraction(int intValue) {
            this(intValue, 1);
        }

        public Fraction(int numerator, int denominator) {
            this.numerator = numerator;
            this.denominator = denominator;
        }

        public Fraction plus(Fraction that) {
            return new Fraction(
                    this.numerator + that.numerator,
                    this.denominator);

        }

        public int intValue() {
            return numerator;
        }

        public int getNumerator() {
            return numerator;
        }

        public int getDenominator() {
            return denominator;
        }
    }
}
