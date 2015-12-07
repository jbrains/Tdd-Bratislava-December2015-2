package ca.jbrains.math.test;

import ca.jbrains.math.test.AddFractionsTest.Fraction;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.stream.Collectors;

public class FractionEqualsTest {
    @Test
    public void someEqualValues() throws Exception {
        Assert.assertEquals(new Fraction(2), new Fraction(2));
        Assert.assertEquals(new Fraction(-8), new Fraction(-8));
        Assert.assertEquals(new Fraction(1, 2), new Fraction(1, 2));
        Assert.assertEquals(new Fraction(1, 2), new Fraction(3, 6));
        Assert.assertEquals(new Fraction(2), new Fraction(2, 1));
    }

    @Test
    public void someUnequalValues() throws Exception {
        Assert.assertNotEquals(new Fraction(4), new Fraction(7));
        Assert.assertNotEquals(new Fraction(4, 5), new Fraction(7, 5));
        Assert.assertNotEquals(new Fraction(4, 5), new Fraction(4, 7));
    }

    @RunWith(Theories.class)
    public static class ReflexiveProperty {
        @DataPoints
        public static final Fraction[] allDifferentValues = new Fraction[]{
                new Fraction(1, 2),
                new Fraction(3, 4),
                new Fraction(-7, 8),
                new Fraction(9, -10),
                new Fraction(5),
                new Fraction(0)
        };

        @Theory
        public void compareToItself(Fraction fraction) throws Exception {
            Assert.assertEquals(fraction, fraction);
        }

        @Theory
        public void compareToOthersWithDifferentValues(Fraction one, Fraction theOther) throws Exception {
            Assume.assumeFalse(one == theOther);
            Assert.assertNotEquals(one, theOther);
        }
    }

    @RunWith(Theories.class)
    public static class SymmetricProperty {
        @DataPoints
        public static final Fraction[] notAllDifferentValues =
                Arrays.asList(ReflexiveProperty.allDifferentValues).stream()
                        .flatMap((f) -> Arrays.asList(1, 4, 8, -3, -7, 1932, -392132).stream()
                                .map((n) -> new Fraction(n * f.getNumerator(), n * f.getDenominator())))
                        .collect(Collectors.<Fraction>toList()).toArray(new Fraction[0]);

        @Theory
        public void differentValues(Fraction one, Fraction differentValue) throws Exception {
            Assume.assumeFalse(one.equals(differentValue));
            Assert.assertNotEquals(one, differentValue);
            Assert.assertNotEquals(differentValue, one);
        }

        @Theory
        public void equalValues(Fraction one, Fraction equalToOne) throws Exception {
            Assume.assumeTrue(one.equals(equalToOne));
            Assert.assertTrue(equalToOne.equals(one));
        }
    }

    @RunWith(Theories.class)
    public static class TransitiveProperty {
        @DataPoints
        public static final Fraction[] notAllDifferentValues = SymmetricProperty.notAllDifferentValues;

        @Theory
        public void holds(Fraction a, Fraction b, Fraction c) throws Exception {
            Assume.assumeTrue(a.equals(b) && b.equals(c));
            Assert.assertEquals(c, a);
        }

        @Theory
        public void doesNotHoldDueToA(Fraction a, Fraction b, Fraction c) throws Exception {
            Assume.assumeFalse(a.equals(b));
            Assume.assumeTrue(b.equals(c));
            Assert.assertNotEquals(c, a);
        }

        @Theory
        public void doesNotHoldDueToC(Fraction a, Fraction b, Fraction c) throws Exception {
            Assume.assumeTrue(a.equals(b));
            Assume.assumeFalse(b.equals(c));
            Assert.assertNotEquals(c, a);
        }
    }

    @RunWith(Theories.class)
    public static class DenominatorOfOne {
        @DataPoints
        public static final int[] numerators = new int[]{1, 2, 4, 7, 8, -19, -329};

        @Theory
        public void nEqualsNOver1(int n) throws Exception {
            Assert.assertEquals(new Fraction(n, 1), new Fraction(n));
        }
    }

    @RunWith(Theories.class)
    public static class TheManyFacesOfZero {
        @DataPoints
        public static final int[] denominators = new int[]{1, 2, 4, 7, 8, -19, -329};

        @Theory
        public void zeroEqualsZeroOverN(int n) throws Exception {
            Assert.assertEquals(new Fraction(0), new Fraction(0, n));
        }
    }
}

