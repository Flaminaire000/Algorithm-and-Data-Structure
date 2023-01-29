package it.unicam.cs.asdl2223.mp1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ShelfTest {
    /*
     * Costante piccola per il confronto di due numeri double
     */
    private static final double EPSILON = 1.0E-15;

    private Book b1 = new Book(
            "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest,"
                    + " Clifford Stein",
            "Introduzione agli algoritmi e strutture dati", 2010,
            "McGraw-Hill Education", "978-8838665158", 26.6, 19.08, 1720);

    private Book b1Equals = new Book("", "", 0, "", "978-8838665158", 0, 0, 0);

    private Book b2 = new Book("Maurizio Gabbrielli, Simone Martini",
            "Linguaggi di programmazione. Principi e paradigmi", 2011,
            "McGraw-Hill Education", "978-8838665738", 24.13, 17.15, 939);

    private Book b2Equals = new Book("", "", 0, "", "978-8838665738", 0, 0, 0);

    private Book b3 = new Book("Cay S. Horstmann",
            "Concetti di informatica e fondamenti di Java. 7a edizione", 2020,
            "Maggioli Editore", "978-8891639431", 26.6, 19.08, 1280);

    private Book b3Equals = new Book("", "", 0, "", "978-8891639431", 0, 0, 0);

    private RoundLamp l1 = new RoundLamp(10, 1230, "Ticino", "Lindby");

    private RoundLamp l1Equals = new RoundLamp(0, 0, "Ticino", "Lindby");

    private RoundLamp l2 = new RoundLamp(13, 1550, "Tibby", "Lindby");

    private RoundLamp l2Equals = new RoundLamp(0, 0, "Tibby", "Lindby");

    private RoundLamp l3 = new RoundLamp(11, 2550, "Ronja", "Lindby");

    private RoundLamp l3Equals = new RoundLamp(0, 0, "Tibby", "Lindby");

    @Test
    final void testAddItem1() {
        Shelf shelf = new Shelf(15, 13, 1000, 3000);
        // Eccede lunghezza e larghezza
        assertThrows(IllegalArgumentException.class, () -> shelf.addItem(b1));
    }

    @Test
    final void testAddItem2() {
        Shelf shelf = new Shelf(15, 20, 1000, 3000);
        // Eccede lunghezza
        assertThrows(IllegalArgumentException.class, () -> shelf.addItem(b1));
    }

    @Test
    final void testAddItem3() {
        Shelf shelf = new Shelf(27, 13, 1000, 3000);
        // Eccede larghezza
        assertThrows(IllegalArgumentException.class, () -> shelf.addItem(b1));
    }

    @Test
    final void testAddItem4() {
        Shelf shelf = new Shelf(27, 20, 1000, 1700);
        // Eccede il peso
        assertThrows(IllegalArgumentException.class, () -> shelf.addItem(b1));
    }

    @Test
    final void testAddItem5() {
        Shelf shelf = new Shelf(27, 20, 190, 1700);
        // Eccede la superficie
        assertThrows(IllegalArgumentException.class, () -> shelf.addItem(b1));
    }

    @Test
    final void testAddItem6() {
        Shelf shelf = new Shelf(27, 20, 508 + 414, 9700);
        shelf.addItem(b1);
        shelf.addItem(b2);
        // Eccede la superficie
        assertThrows(IllegalArgumentException.class, () -> shelf.addItem(l1));
    }

    @Test
    final void testAddItem7() {
        Shelf shelf = new Shelf(27, 20, 508, 2800);
        shelf.addItem(l1);
        shelf.addItem(l2);
        // Eccede il peso
        assertThrows(IllegalArgumentException.class, () -> shelf.addItem(l3));
    }

    @Test
    final void testAddItem8() {
        Shelf shelf = new Shelf(27, 20, 2508, 10000);
        assertTrue(shelf.getNumberOfItems() == 0);
        shelf.addItem(l1);
        assertTrue(shelf.getNumberOfItems() == 1);
        shelf.addItem(l2);
        assertTrue(shelf.getNumberOfItems() == 2);
        shelf.addItem(l3);
        assertTrue(shelf.getNumberOfItems() == 3);
        shelf.addItem(b1);
        assertTrue(shelf.getNumberOfItems() == 4);
        shelf.addItem(b2);
        assertTrue(shelf.getNumberOfItems() == 5);
        // raggiunta massima posizione
        assertTrue(shelf.getItems().length == 5);
        // raddoppia
        shelf.addItem(b3);
        assertTrue(shelf.getNumberOfItems() == 6);
        assertTrue(shelf.getItems().length == 10);
    }

    @Test
    final void testAddItem9() {
        Shelf shelf = new Shelf(27, 20, 2508, 10000);
        shelf.addItem(l1);
        shelf.addItem(l2);
        shelf.addItem(l3);
        shelf.addItem(b1);
        shelf.addItem(b2);
        // raggiunta massima posizione
        assertTrue(shelf.getItems().length == 5);
        // raddoppia
        shelf.addItem(b3);
        assertTrue(shelf.getNumberOfItems() == 6);
        assertTrue(shelf.getItems().length == 10);
        assertTrue(shelf.getItems()[0].equals(l1));
        assertTrue(shelf.getItems()[1].equals(l2));
        assertTrue(shelf.getItems()[2].equals(l3));
        assertTrue(shelf.getItems()[3].equals(b1));
        assertTrue(shelf.getItems()[4].equals(b2));
        assertTrue(shelf.getItems()[5].equals(b3));
    }

    @Test
    final void testSearch1() {
        Shelf shelf = new Shelf(27, 20, 2508, 10000);
        shelf.addItem(l1);
        shelf.addItem(l2);
        shelf.addItem(l3);
        shelf.addItem(b1);
        shelf.addItem(b2);
        assertTrue(shelf.search(l1Equals) != null);
        ShelfItem searchResult = shelf.search(l1Equals);
        // Controlla se è stato tirato fuori l'oggetto nell'array
        RoundLamp l1Cast = (RoundLamp) searchResult;
        assertTrue(l1Cast.getDiameter() == 10);
    }

    @Test
    final void testSearch2() {
        Shelf shelf = new Shelf(27, 20, 2508, 10000);
        shelf.addItem(l1);
        shelf.addItem(l2);
        shelf.addItem(l3);
        shelf.addItem(b1);
        shelf.addItem(b2);
        assertTrue(shelf.search(b3Equals) == null);
    }

    @Test
    final void testSearch3() {
        Shelf shelf = new Shelf(27, 20, 2508, 10000);
        assertTrue(shelf.search(l1) == null);
        shelf.addItem(l1);
        assertTrue(shelf.search(l1Equals) != null);
        assertTrue(shelf.search(l1) != null);
        assertTrue(shelf.search(l2Equals) == null);
        shelf.addItem(l2);
        assertTrue(shelf.search(l2Equals) != null);
        assertTrue(shelf.search(b1) == null);
        shelf.addItem(b1);
        assertTrue(shelf.search(b1Equals) != null);
        assertTrue(shelf.search(b1) != null);
        assertTrue(shelf.search(b2Equals) == null);
        shelf.addItem(b2);
        assertTrue(shelf.search(b2Equals) != null);
    }

    @Test
    final void testSearch4() {
        Shelf shelf = new Shelf(27, 20, 2508, 10000);
        assertTrue(shelf.search(l1) == null);
        shelf.addItem(l1);
        assertTrue(shelf.search(l1Equals) != null);
        assertTrue(shelf.search(l1) != null);
        assertTrue(shelf.search(l2Equals) == null);
        shelf.addItem(l2);
        assertTrue(shelf.search(l2Equals) != null);
        assertTrue(shelf.search(b1) == null);
        shelf.addItem(b1);
        assertTrue(shelf.search(b1Equals) != null);
        assertTrue(shelf.search(b1) != null);
        assertTrue(shelf.search(b2Equals) == null);
        shelf.addItem(b2);
        assertTrue(shelf.search(b2Equals) != null);
        shelf.addItem(b3);
        shelf.addItem(l3);
        assertTrue(shelf.search(b3Equals) != null);
        assertTrue(shelf.search(l3Equals) != null);
    }

    @Test
    final void testSearch5() {
        Shelf shelf = new Shelf(27, 20, 2508, 10000);
        assertThrows(NullPointerException.class, () -> shelf.search(null));
    }

    @Test
    final void testGetNumberOfItems() {
        Shelf shelf = new Shelf(27, 20, 508, 2800);
        assertTrue(shelf.getNumberOfItems() == 0);
        shelf.addItem(l1);
        assertTrue(shelf.getNumberOfItems() == 1);
        shelf.addItem(l2);
        assertTrue(shelf.getNumberOfItems() == 2);
    }

    @Test
    final void testGetItems() {
        Shelf shelf = new Shelf(27, 20, 508, 2800);
        assertTrue(shelf.getNumberOfItems() == 0);
        shelf.addItem(l1);
        assertTrue(shelf.getNumberOfItems() == 1);
        shelf.addItem(l2);
        assertTrue(shelf.getNumberOfItems() == 2);
        assertTrue(shelf.getItems()[0].equals(l1Equals));
        assertTrue(shelf.getItems()[1].equals(l2Equals));
        assertTrue(shelf.getItems()[2] == null);
    }

    @Test
    final void testGetCurrentTotalWeight() {
        Shelf shelf = new Shelf(27, 20, 508, 2800);
        assertTrue(shelf.getCurrentTotalWeight() == 0);
        shelf.addItem(l1);
        assertTrue(shelf.getCurrentTotalWeight() == 1230);
        shelf.addItem(l2);
        assertTrue(Math
                .abs(shelf.getCurrentTotalWeight() - (1230 + 1550)) < EPSILON);
    }

    @Test
    final void testGetCurrentTotalOccupiedSurface() {
        Shelf shelf = new Shelf(27, 20, 508, 2800);
        assertTrue(shelf.getCurrentTotalOccupiedSurface() == 0);
        shelf.addItem(l1);
        assertTrue(Math.abs(shelf.getCurrentTotalOccupiedSurface()
                - (5 * 5 * Math.PI)) < EPSILON);
        shelf.addItem(l2);
        assertTrue(Math
                .abs(shelf.getCurrentTotalOccupiedSurface() - ((5 * 5 * Math.PI)
                        + ((13.0 / 2) * (13.0 / 2) * Math.PI))) < EPSILON);
    }

}
