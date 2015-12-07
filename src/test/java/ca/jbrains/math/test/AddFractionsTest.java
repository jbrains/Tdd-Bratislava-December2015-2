package ca.jbrains.math.test;

import org.junit.Assert;
import org.junit.Test;

public class AddFractionsTest {
    @Test
    public void zeroPlusZero() throws Exception {
        Fraction sum = new Fraction(0).plus(new Fraction(0));

        Assert.assertEquals(0, sum.getNumerator());
    }

    @Test
    public void notZeroPlusZero() throws Exception {
        Fraction sum = new Fraction(7).plus(new Fraction(0));

        Assert.assertEquals(7, sum.getNumerator());
    }

    @Test
    public void zeroPlusNotZero() throws Exception {
        Fraction sum = new Fraction(0).plus(new Fraction(-4));

        Assert.assertEquals(-4, sum.getNumerator());
    }

    @Test
    public void integersNotBothZero() throws Exception {
        Fraction sum = new Fraction(10).plus(new Fraction(8));

        Assert.assertEquals(18, sum.getNumerator());
    }

    @Test
    public void sameDenominator() throws Exception {
        Fraction sum = new Fraction(1, 5).plus(new Fraction(2, 5));

        Assert.assertEquals(3, sum.getNumerator());
        Assert.assertEquals(5, sum.getDenominator());
    }

    @Test
    public void differentDenominatorsWithoutReducing() throws Exception {
        Fraction sum = new Fraction(3, 4).plus(new Fraction(2, 5));

        Assert.assertEquals(23, sum.getNumerator());
        Assert.assertEquals(20, sum.getDenominator());
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
            if (this.denominator == that.denominator)
                return new Fraction(
                        this.numerator + that.numerator,
                        this.denominator);
            else
                return new Fraction(
                        this.numerator * that.denominator
                                + that.numerator * this.denominator,
                        this.denominator * that.denominator);
        }

        public int getNumerator() {
            return numerator;
        }

        public int getDenominator() {
            return denominator;
        }

        @Override
        public boolean equals(Object other) {
            if (other instanceof Fraction) {
                Fraction that = (Fraction) other;
                return this.numerator * that.denominator
                        == this.denominator * that.numerator;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return -762;
        }

        @Override
        public String toString() {
            return String.format("%d/%d", numerator, denominator);
        }
    }
}
