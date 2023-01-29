/**
 * 
 */
package it.unicam.cs.asdl2223.mp1;

/**
 * Un oggetto di questa classe rappresenta una lampada che ha un appoggio
 * circolare. Implementa l'interfaccia ShelfItem, ma come lunghezza e larghezza
 * ha il diametro della base. Ridefinisce il metodo di default per calcolare la
 * superficie occupata restituiendo l'area del cerchio che corrisponde alla
 * base. Una lampada è identificata dal nome e dal nome del brand.
 * 
 * @author Luca Tesei (template) Vasil Ferecka vasil.ferecka@studenti.unicam.it (implementazione)
 *
 */
public class RoundLamp implements ShelfItem {

    private final double diameter;

    private final double weight;

    private final String name;

    private final String brandName;
    
    /**
     * @param diameter
     *                      diametro della base in cm
     * @param weight
     *                      peso in grammi
     * @param name
     *                      nome del modello della lampada
     * @param brandName
     *                      nome del brand della lampada
     */
    public RoundLamp(double diameter, double weight, String name,String brandName) 
    {
        this.diameter = diameter;
        this.weight = weight;
        this.name = name;
        this.brandName = brandName;
    }

    /*
     * Restituisce l'area del cerchio corrispondente alla base
     */
    @Override
    public double getOccupiedSurface() 
    {
    	//Uno dei metodi per calcolare l'area della circonferenza e' (Pigreco*diametro^2)/4
        return ((Math.PI*Math.pow(diameter, 2))/4);
    }

    /*
     * Restituisce il diametro della base
     */
    @Override
    public double getLength() 
    {
        return this.diameter;
    }

    /*
     * Restituisce il diametro della base
     */
    @Override
    public double getWidth() 
    {
        return this.diameter;
    }

    @Override
    public double getWeight() 
    {
        return this.weight;
    }

    /**
     * @return the diameter
     */
    public double getDiameter() 
    {
        return diameter;
    }

    /**
     * @return the name
     */
    public String getName() 
    {
        return name;
    }

    /**
     * @return the brandName
     */
    public String getBrandName() 
    {
        return brandName;
    }

    /*
     * Due lampade sono considerate uguali se hanno lo stesso nome e lo stesso
     * nome del brand.
     */
    @Override
    public boolean equals(Object obj) 
    {
    	//Oggetto Nullo
        if(obj==null)
        {
        	return false;
        }
        
        //Controllo se l'oggetto passato non e' un istanza della classe RoundLamp
        if((obj instanceof RoundLamp)==false)
        {
        	return false;
        }
        
       //Creo un oggetto RoundLamp di tipologia final e gli assegno l'oggetto passato (con il casting)
        final RoundLamp other=(RoundLamp) obj;
        /*
         * Come da javadoc, sia il nome della lampada che il nome del brand devono corrispondere.
         * In questo caso, per confrontare i campi, adopero la funzione equals() della classe String
         * */
        if(this.name.equals(other.getName()) && this.brandName.equals(other.getBrandName()))
        {
        	return true;
        }
        return false;
    }

    /*
     * L'hashcode viene calcolato usando gli stessi campi usati per definire
     * l'uguaglianza
     */
    @Override
    public int hashCode() 
    {
    	//Designo un numero primo
        final int numeroPrimo=11;
        int hash=1;
        //sommo entrambi gli hash di un unica istanza di classe
        hash=numeroPrimo*hash+this.name.hashCode();
        hash=numeroPrimo*hash+this.brandName.hashCode();
        return hash;
    }
}