package it.unicam.cs.asdl2223.mp1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CrivelloDiEratosteneTest {

    // ------------------------------- TEST getCapacity
    // -------------------------------

    @Test()
    void testInvalidCapacity1() {
        assertThrows(IllegalArgumentException.class, () -> {
            int capacity = -1;
            new CrivelloDiEratostene(capacity);
        });
    }

    @Test()
    void testInvalidCapacity2() {
        assertThrows(IllegalArgumentException.class, () -> {
            int capacity = 0;
            new CrivelloDiEratostene(capacity);
        });
    }

    @Test()
    void testInvalidCapacity3() {
        assertThrows(IllegalArgumentException.class, () -> {
            int capacity = 1;
            new CrivelloDiEratostene(capacity);
        });
    }

    // ------------------------------- TEST isPrime
    // -------------------------------

    @Test
    void testTrueIsPrime1() {
        int[] primes = new int[] { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37,
                41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101 };
        int capacity = 101;
        CrivelloDiEratostene c = new CrivelloDiEratostene(capacity);
        for (int prime : primes) {
            assertTrue(c.isPrime(prime));
        }

    }

    @Test
    void testTrueIsPrime2() {
        int[] primes = new int[] { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37,
                41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101 };
        int capacity = 102;
        CrivelloDiEratostene c = new CrivelloDiEratostene(capacity);
        for (int prime : primes) {
            assertTrue(c.isPrime(prime));
        }

    }

    @Test
    void testFalseIsPrime() {
        int capacity = 100;
        CrivelloDiEratostene c = new CrivelloDiEratostene(capacity);
        assertFalse(c.isPrime(6));
        assertFalse(c.isPrime(32));
        assertFalse(c.isPrime(50));
        assertFalse(c.isPrime(88));
        assertFalse(c.isPrime(100));
    }

    @Test
    void testInvalidIsPrime1() {
        assertThrows(IllegalArgumentException.class, () -> {
            int capacity = 100;
            CrivelloDiEratostene c = new CrivelloDiEratostene(capacity);
            c.isPrime(1);
        });
    }

    @Test
    void testInvalidIsPrime2() {
        assertThrows(IllegalArgumentException.class, () -> {
            int capacity = 100;
            CrivelloDiEratostene c = new CrivelloDiEratostene(capacity);
            c.isPrime(0);
        });
    }

    @Test
    void testInvalidIsPrime3() {
        assertThrows(IllegalArgumentException.class, () -> {
            int capacity = 100;
            CrivelloDiEratostene c = new CrivelloDiEratostene(capacity);
            c.isPrime(101);
        });
    }

    @Test
    void testInvalidIsPrime4() {
        assertThrows(IllegalArgumentException.class, () -> {
            int capacity = 200;
            CrivelloDiEratostene c = new CrivelloDiEratostene(capacity);
            c.isPrime(205);
        });
    }

    // ------------------------------- TEST restartPrimeIteration
    // -------------------------------

    @Test
    void testRestartPrimeIteration1() {
        int capacity = 200;
        CrivelloDiEratostene c = new CrivelloDiEratostene(capacity);
        c.nextPrime(); // 2
        c.nextPrime(); // 3
        c.nextPrime(); // 5
        c.nextPrime(); // 7
        c.nextPrime(); // 11
        c.nextPrime(); // 13

        c.restartPrimeIteration();

        assertEquals(c.nextPrime(), 2);
        assertEquals(c.nextPrime(), 3);
        assertEquals(c.nextPrime(), 5);
        assertEquals(c.nextPrime(), 7);
        assertEquals(c.nextPrime(), 11);
        assertEquals(c.nextPrime(), 13);

        c.restartPrimeIteration();

        assertEquals(c.nextPrime(), 2);
        assertEquals(c.nextPrime(), 3);
        assertEquals(c.nextPrime(), 5);

        c.restartPrimeIteration();

        assertEquals(c.nextPrime(), 2);
        assertEquals(c.nextPrime(), 3);
        assertEquals(c.nextPrime(), 5);
        assertEquals(c.nextPrime(), 7);
        assertEquals(c.nextPrime(), 11);
        assertEquals(c.nextPrime(), 13);
    }

    @Test
    void testRestartPrimeIteration2() {
        int capacity = 13;
        CrivelloDiEratostene c = new CrivelloDiEratostene(capacity);

        c.restartPrimeIteration();
        c.restartPrimeIteration();
        c.restartPrimeIteration();
        c.restartPrimeIteration();
        c.restartPrimeIteration();

        assertEquals(c.nextPrime(), 2);
        assertEquals(c.nextPrime(), 3);
        assertEquals(c.nextPrime(), 5);
        assertEquals(c.nextPrime(), 7);
        assertEquals(c.nextPrime(), 11);
        assertEquals(c.nextPrime(), 13);

    }

    // ------------------------------- TEST hasNextPrime
    // -------------------------------

    @Test
    void testHasNextPrime1() {
        int[] primes = new int[] { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37,
                41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101 };
        int capacity = 102;
        CrivelloDiEratostene c = new CrivelloDiEratostene(capacity);
        for (int i = 0; i < primes.length; i++) {
            assertTrue(c.hasNextPrime());
            c.nextPrime();
        }
        assertFalse(c.hasNextPrime());
    }

    @Test
    void testHasNextPrime2() {
        int capacity = 2;
        CrivelloDiEratostene c = new CrivelloDiEratostene(capacity);
        c.nextPrime(); // 2
        assertFalse(c.hasNextPrime());

    }

    // ------------------------------- TEST nextPrime
    // -------------------------------

    @Test
    void testValidNextPrime1() {
        int[] primes = new int[] { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37,
                41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101 };
        int capacity = 101;
        CrivelloDiEratostene c = new CrivelloDiEratostene(capacity);
        for (int i = 0; i < primes.length; i++) {
            assertEquals(c.nextPrime(), primes[i]);
        }
    }

    @Test
    void testValidNextPrime2() {
        int capacity = 2;
        CrivelloDiEratostene c = new CrivelloDiEratostene(capacity);
        assertEquals(c.nextPrime(), 2);

    }

    @Test
    void testInvalidNextPrime1() {
        int capacity = 2;
        CrivelloDiEratostene c = new CrivelloDiEratostene(capacity);
        c.nextPrime();
        assertThrows(IllegalStateException.class, () -> {
            c.nextPrime();
        });

    }

    @Test
    void testInvalidNextPrime2() {
        int capacity = 5;
        CrivelloDiEratostene c = new CrivelloDiEratostene(capacity);
        c.nextPrime();
        c.nextPrime();
        c.nextPrime();
        assertThrows(IllegalStateException.class, () -> {
            c.nextPrime();
        });

    }

    @Test
    void testInvalidNextPrime3() {
        int capacity = 20;
        CrivelloDiEratostene c = new CrivelloDiEratostene(capacity);
        c.nextPrime(); // 2
        c.nextPrime(); // 3
        c.nextPrime(); // 5
        c.nextPrime(); // 7
        c.nextPrime(); // 11
        c.nextPrime(); // 13
        c.nextPrime(); // 17
        c.nextPrime(); // 19
        assertThrows(IllegalStateException.class, () -> {
            c.nextPrime();
        });

    }
}
