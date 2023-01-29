package it.unicam.cs.asdl2223.mp1;

/**
 * Un oggetto di quest classe rappresenta un fattore primo di un numero naturale
 * con una certa molteplicità.
 * 
 * @author Luca Tesei (template) Vasil Ferecka vasil.ferecka@studenti.unicam.it (implementazione)
 *
 */
public class Factor implements Comparable<Factor> {

    /*
     * Numero primo corrispondente a questo fattore
     */
    private final int primeValue;

    /*
     * Molteplicità del numero primo di questo fattore, deve essere maggiore o
     * uguale a 1.
     */
    private final int multiplicity;
    
    /**
     * Crea un fattore primo di un numero naturale, formato da un numero primo e
     * dalla sua molteplicità.
     * 
     * @param primeValue,
     *                          numero primo
     * @param multiplicity,
     *                          valore della molteplicità, deve essere almeno 1
     * @throws IllegalArgumentException
     *                                      se la molteplicità è minore di 1
     *                                      oppure se primeValue è minore o
     *                                      uguale di 0.
     */
    public Factor(int primeValue, int multiplicity) 
    {
    	if(multiplicity<1 || primeValue<=0)
    	{
    		throw new IllegalArgumentException("| ERROR | La molteplicità deve essere maggiore di 1 ed il valore primo almeno 1");
    	}
        this.primeValue = primeValue;
        this.multiplicity = multiplicity;
    }

    /**
     * @return the primeValue
     */
    public int getPrimeValue() 
    {
        return primeValue;
    }

    /**
     * @return the multiplicity
     */
    public int getMultiplicity() 
    {
        return multiplicity;
    }

    /*
     * Calcola l'hashcode dell'oggetto in accordo ai valori usati per definire
     * il metodo equals.
     */
    @Override
    public int hashCode() 
    {
    	//Designo un numero primo
        final int numeroPrimo=11;
        int hash=1;
        //sommo entrambi gli hash di un unica istanza di classe
        hash=numeroPrimo*hash+this.primeValue;
        hash=numeroPrimo*hash+this.multiplicity;
        return hash;
    }

    /*
     * Due oggetti Factor sono uguali se e solo se hanno lo stesso numero primo
     * e la stessa molteplicità
     */
    @Override
    public boolean equals(Object obj) 
    {
    	//Oggetto Nullo
        if(obj==null)
        {
        	return false;
        }
        
        //Controllo se l'oggetto passato e' un istanza della classe Factor
        if((obj instanceof Factor)==false)
        {
        	return false;
        }
        
        //Creo un oggetto Factor di tipologia final e gli assegno l'oggetto passato (con in casting)
        final Factor other=(Factor) obj;
        //Come da javadoc, sia la multiplicita' che il numero primo devono corrispondere
        if(this.multiplicity==other.getMultiplicity() && this.primeValue==other.getPrimeValue())
        {
        	return true;
        }
        return false;
    }

    /*
     * Un Factor è minore di un altro se contiene il numero primo minore. Se due
     * Factor hanno lo stesso numero primo allora il più piccolo dei due è
     * quello ce ha minore molteplicità.
     */
    @Override
    public int compareTo(Factor o) 
    {
    	//Oggetto bullo
        if (o==null)
        {
        	throw new NullPointerException("| ERROR | Oggetto passato null");
        }
        
        /*
         * Nell'if piu' esterno controllo entrambi i valori primi:
         * 	-1- Se uguali controllo multiplicita'
         * 	-2- Se l'oggetto passatto e' piu' grande, restituisco -1
         * 	-3- se piu' piccolo invece restituisco +1
         * */
        if(this.primeValue==o.getPrimeValue())
        {
            /*
             * Nell'if piu' interno controllo le molteplicita':
             * 	-1- Se uguali restituisco 0
             * 	-2- Se l'oggetto passatto e' piu' grande, restituisco -1
             * 	-3- se piu' piccolo invece restituisco +1
             * */
        	if(this.multiplicity<o.getMultiplicity())
        	{
        		return -1;
        	}
        	else if(this.multiplicity>o.getMultiplicity())
        	{
        		return 1;
        	}
        	else
        	{
        		return 0;
        	}
        }
        else if(this.primeValue<o.getPrimeValue())
        {
        	return -1;
        }
        else
        {
        	return 1;
        }
    }

    /*
     * Il fattore viene reso con la stringa primeValue^multiplicity
     */
    @Override
    public String toString() 
    { 
    	//Concateno le variabili con la stringa "^"
    	String str=this.primeValue+"^"+this.multiplicity;
    	return str;
    }
}