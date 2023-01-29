package it.unicam.cs.asdl2223.mp1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FactoriserTest {
    private static Factoriser f = new Factoriser();

    @Test
    final void testGetFactors1() {
        assertThrows(IllegalArgumentException.class, () -> f.getFactors(0));
    }

    @Test
    final void testGetFactors2() {
        Factor[] r = f.getFactors(1);
        assertTrue(r.length == 0);
    }

    @Test
    final void testGetFactors3() {
        Factor[] r = f.getFactors(2);
        assertTrue(r.length == 1);
        assertTrue(r[0].equals(new Factor(2, 1)));
    }

    @Test
    final void testGetFactors4() {
        Factor[] r = f.getFactors(8);
        assertTrue(r.length == 1);
        assertTrue(r[0].equals(new Factor(2, 3)));
    }

    @Test
    final void testGetFactors5() {
        Factor[] r = f.getFactors(24);
        assertTrue(r.length == 2);
        assertTrue(r[0].equals(new Factor(2, 3)));
        assertTrue(r[1].equals(new Factor(3, 1)));
    }

    @Test
    final void testGetFactors6() {
        Factor[] r = f.getFactors(24 * 3);
        assertTrue(r.length == 2);
        assertTrue(r[0].equals(new Factor(2, 3)));
        assertTrue(r[1].equals(new Factor(3, 2)));
    }

    @Test
    final void testGetFactors7() {
        Factor[] r = f.getFactors(24 * 3 * 7);
        assertTrue(r.length == 3);
        assertTrue(r[0].equals(new Factor(2, 3)));
        assertTrue(r[1].equals(new Factor(3, 2)));
        assertTrue(r[2].equals(new Factor(7, 1)));
    }

    @Test
    final void testGetFactors8() {
        Factor[] r = f.getFactors(24 * 3 * 7 * 7 * 7);
        assertTrue(r.length == 3);
        assertTrue(r[0].equals(new Factor(2, 3)));
        assertTrue(r[1].equals(new Factor(3, 2)));
        assertTrue(r[2].equals(new Factor(7, 3)));
    }

    @Test
    final void testGetFactors9() {
        Factor[] r = f.getFactors(24 * 3 * 7 * 7 * 7 * 23);
        assertTrue(r.length == 4);
        assertTrue(r[0].equals(new Factor(2, 3)));
        assertTrue(r[1].equals(new Factor(3, 2)));
        assertTrue(r[2].equals(new Factor(7, 3)));
        assertTrue(r[3].equals(new Factor(23, 1)));
    }

    @Test
    final void testGetFactors10() {
        Factor[] r = f.getFactors(24 * 3 * 7 * 7 * 7 * 23 * 23 * 23);
        assertTrue(r.length == 4);
        assertTrue(r[0].equals(new Factor(2, 3)));
        assertTrue(r[1].equals(new Factor(3, 2)));
        assertTrue(r[2].equals(new Factor(7, 3)));
        assertTrue(r[3].equals(new Factor(23, 3)));
    }

    @Test
    final void testGetFactors11() {
        Factor[] r = f.getFactors(1386);
        assertTrue(r.length == 4);
        assertTrue(r[0].equals(new Factor(2, 1)));
        assertTrue(r[1].equals(new Factor(3, 2)));
        assertTrue(r[2].equals(new Factor(7, 1)));
        assertTrue(r[3].equals(new Factor(11, 1)));
    }

    @Test
    final void testGetFactors12() {
        Factor[] r = f.getFactors(42890);
        assertTrue(r.length == 3);
        assertTrue(r[0].equals(new Factor(2, 1)));
        assertTrue(r[1].equals(new Factor(5, 1)));
        assertTrue(r[2].equals(new Factor(4289, 1)));
    }
}
