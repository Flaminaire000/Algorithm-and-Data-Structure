package it.unicam.cs.asdl2223.mp1;

/**
 * Interfaccia che accomuna tutti gli oggetti che possono essere posati su una
 * mensola.
 * 
 * @author Luca Tesei
 *
 */
public interface ShelfItem {
    /**
     * @return la lunghezza dell'oggetto
     */
    public double getLength();

    /**
     * @return la larghezza dell'oggetto
     */
    public double getWidth();

    /**
     * @return il peso dell'oggetto
     */
    public double getWeight();

    /**
     * Restituisce l'area occupata dall'oggetto sulla superficie della mensola.
     * L'implementazione di default calcola l'area moltiplicando la lunghezza
     * per la larghezza, assumendo che l'oggetto appoggi una superficie
     * rettangolare. Nel caso di forma diversa l'implementazione di default deve
     * essere ridefinita.
     * 
     * @return la superficie occupata dall'oggetto
     */
    default double getOccupiedSurface() {
        return getLength() * getWidth();
    }
}
