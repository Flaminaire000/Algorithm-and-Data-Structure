package it.unicam.cs.asdl2223.mp1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FactorTest {

    @Test
    final void testFactor() {
        assertThrows(IllegalArgumentException.class, () -> new Factor(2, 0));
    }

    @Test
    final void testFactor1() {
        assertThrows(IllegalArgumentException.class, () -> new Factor(2, -1));
    }

    @Test
    final void testFactor2() {
        assertThrows(IllegalArgumentException.class, () -> new Factor(0, 1));
    }

    @Test
    final void testFactor3() {
        assertThrows(IllegalArgumentException.class, () -> new Factor(-1, 1));
    }

    @Test
    final void testHashCode() {
        Factor f = new Factor(2, 3);
        Factor f1 = new Factor(2, 3);
        assertTrue(f.hashCode() == f1.hashCode());
        assertTrue(f.hashCode() == f1.hashCode());
    }

    @Test
    final void testEqualsObject() {
        Factor f = new Factor(2, 3);
        Factor f1 = new Factor(2, 3);
        Factor f2 = new Factor(3, 2);
        assertTrue(f.equals(f1));
        assertTrue(f1.equals(f));
        assertFalse(f.equals(f2));
        assertFalse(f2.equals(f1));
        assertFalse(f.equals(null));
        Factor f3 = new Factor(11, 31);
        Factor f4 = new Factor(2, 31);
        assertFalse(f3.equals(f4));
        assertFalse(f4.equals(f3));
        Factor f5 = new Factor(11, 31);
        Factor f6 = new Factor(11, 3);
        assertFalse(f5.equals(f6));
        assertFalse(f6.equals(f5));
    }

    @Test
    final void testCompareTo() {
        Factor f = new Factor(2, 3);
        Factor f1 = new Factor(2, 3);
        Factor f2 = new Factor(2, 5);
        Factor f3 = new Factor(2, 1);
        assertTrue(f.compareTo(f1) == 0);
        assertTrue(f1.compareTo(f) == 0);
        assertTrue(f.compareTo(f2) < 0);
        assertTrue(f2.compareTo(f) > 0);
        assertTrue(f.compareTo(f3) > 0);
        assertTrue(f3.compareTo(f) < 0);
    }

    @Test
    final void testCompareTo2() {
        Factor f = new Factor(2, 3);
        Factor f1 = new Factor(2, 3);
        Factor f2 = new Factor(2, 5);
        Factor f3 = new Factor(2, 1);
        Factor f4 = new Factor(3, 3);
        assertTrue(f4.compareTo(f) > 0);
        assertTrue(f4.compareTo(f1) > 0);
        assertTrue(f4.compareTo(f2) > 0);
        assertTrue(f4.compareTo(f3) > 0);
        assertTrue(f.compareTo(f4) < 0);
        assertTrue(f1.compareTo(f4) < 0);
        assertTrue(f2.compareTo(f4) < 0);
        assertTrue(f3.compareTo(f4) < 0);
    }

    @Test
    final void testToString() {
        Factor f = new Factor(2, 3);
        assertTrue(f.toString().equals("2^3"));
        Factor f1 = new Factor(11, 45);
        assertTrue(f1.toString().equals("11^45"));
    }

}
