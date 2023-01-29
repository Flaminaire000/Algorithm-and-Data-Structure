package it.unicam.cs.asdl2223.mp1;

import java.util.Arrays;

/**
 * Un oggetto di questa classe rappresenta una mensola su cui possono essere
 * appoggiati degli oggetti. Tali oggetti possono essere di diverso tipo, ma
 * tutti implementano l'interface ShelfItem. Un oggetto non può essere
 * appoggiato sulla mensola se ha lunghezza o larghezza che eccedono quelle
 * della mensola stessa. La mensola può contenere un numero non precisato di
 * oggetti, ma ad un certo punto non si possono appoggiare oggetti la cui
 * superficie occupata o il cui peso fanno eccedere la massima superficie
 * occupabile o il massimo peso sostenibile definiti nel costruttore della
 * mensola.
 * 
 * @author Luca Tesei (template) Vasil Ferecka vasil.ferecka@studenti.unicam.it (implementazione)
 *
 */
public class Shelf {
    /*
     * Dimensione iniziale dell'array items. Quando non è più sufficiente
     * l'array deve essere raddoppiato, anche più volte se necessario.
     */
    private final int INITIAL_SIZE = 5;

    /*
     * massima lunghezza di un oggetto che può essere appoggiato sulla mensola
     * in cm
     */
    private final double maxLength;

    /*
     * massima larghezza di un oggetto che può essere appoggiato sulla mensola
     * in cm
     */
    private final double maxWidth;

    /*
     * massima superficie occupabile della mensola in cm^2
     */
    private final double maxOccupableSurface;

    /*
     * massimo peso sostenibile dalla mensola in grammi
     */
    private final double maxTotalWeight;

    /*
     * array contenente tutti gli oggetti attualmente poggiati sulla mensola. In
     * caso di necessità viene raddoppiato nel momento che si poggia un nuovo
     * oggetto che fa superare la capacità dell'array.
     */
    private ShelfItem[] items;

    /*
     * variabile che indica il numero corrente di caselle nell'array che sono
     * occupate
     */
    private int numberOfItems;

    /**
     * Costruisce una mensola con le sue caratteristiche. All'inizio nessun
     * oggetto è posato sulla mensola.
     * 
     * @param maxLength
     *                                lunghezza massima di un oggetto
     *                                appoggiabile in cm
     * @param maxWidth
     *                                larghezza massima di un oggetto
     *                                appoggiabile in cm
     * @param maxOccupableSurface
     *                                massima superficie occupabile di questa
     *                                mensola in cm^2
     * @param maxTotalWeight
     *                                massimo peso sostenibile da questa mensola
     *                                in grammi
     */
    public Shelf(double maxLength, double maxWidth, double maxOccupableSurface,double maxTotalWeight) 
    {
        this.maxLength = maxLength;
        this.maxWidth = maxWidth;
        this.maxOccupableSurface = maxOccupableSurface;
        this.maxTotalWeight = maxTotalWeight;
        this.items = new ShelfItem[INITIAL_SIZE];
        this.numberOfItems = 0;
        //Non ho avuto bisogno di modificare il costruttore nonostante il to do
    }

    /**
     * Aggiunge un nuovo oggetto sulla mensola. Qualora non ci sia più spazio
     * nell'array che contiene gli oggetti correnti, tale array viene
     * raddoppiato per fare spazio al nuovo oggetto.
     * 
     * @param i
     *              l'oggetto da appoggiare
     * @return true se l'oggetto è stato inserito, false se è già presente
     * @throws IllegalArgumentException
     *                                      se il peso dell'oggetto farebbe
     *                                      superare il massimo peso consentito
     *                                      oopure se la superficie dell'oggetto
     *                                      farebbe superare la massima
     *                                      superficie occupabile consentita,
     *                                      oppure se la lunghezza o larghezza
     *                                      dell'oggetto superano quelle massime
     *                                      consentite
     * @throws NullPointerException
     *                                      se l'oggetto passato è null
     */
    public boolean addItem(ShelfItem i) 
    {
    	//Controllo se oggetto passato e' nullo
        if(i==null)
        {
        	throw new NullPointerException("| ERROR | Oggetto null");
        }
        
        /*
         * Controllo se il peso dell'oggetto sommato al peso attuale 
         * supera il peso massimo della mensola
         * */
        if(i.getWeight()+this.getCurrentTotalWeight()>this.maxTotalWeight)
        {
        	throw new IllegalArgumentException("| ERROR | L'oggetto e' troppo pesante per la mensola");
        }
        
        /*
         * Controllo se la superficie dell'oggetto sommato alla superficie attuale 
         * supera la superficie massima occupabile sulla mensola
         * */
        if(i.getOccupiedSurface()+this.getCurrentTotalOccupiedSurface()>this.maxOccupableSurface)
        {
        	throw new IllegalArgumentException("| ERROR | La superficie residua non basta per la superficie dell'oggetto");
        }
        
        /*
         * Controllo se la lunghezza dell' oggetto supera 
         * la lunghezza massima definita dalla mensola
         * */
        if(i.getLength()>this.maxLength)
        {
        	throw new IllegalArgumentException("| ERROR | Lunghezza dell'oggetto maggiore rispetto a quella massima");
        }
        
        /*
         * Controllo se la larghezza dell' oggetto supera 
         * la larghezza massima definita dalla mensola
         * */
        if(i.getWidth()>this.maxWidth)
        {
        	throw new IllegalArgumentException("| ERROR | Larghezza dell'oggetto maggiore rispetto a quella massima");
        }
        
        /*
         * Controllo se l'oggetto che voglio inserire nella mensola e' stato gia' inserito.
         * Cio' l'ho fatto richiamando il metodo gia' definito per la ricerca "search()".
         * Controllo se diverso da null perche' il metodo restituisce un oggetto
         * se coincide con quello passato
         * */
        if(this.search(i)!=null)
        {
        	return false;
        }
        
        /*
         * Controllo se il  numero di oggetti inseriti e' minore della lunghezza dell'array;
         * ergo ho ancora slot disponibili per inserire oggetti
         * */
        if(this.numberOfItems<this.items.length)
        {
        	//Inserisco l'oggetto nella mensola
        	this.items[this.numberOfItems]=i;
        	//incremento il numero degli oggetti
            this.numberOfItems++;
        }
        else
        {
        	/*
        	 * Arrivato a questo bracket della condizione, significa che il numero degli
        	 * oggetti inseriti e' pari o superiore alla lunghezza dell'array. Per tanto
        	 * andro' a raddoppiare le dimensioni di quest'ultimo per poi inserire l'oggetto.
        	 * Utilizzo il metodo copyOf() che prende come parametro l'array attuale, 
        	 * lo copia e gli da una nuova lunghezza passata come parametro. 
        	 * Nel nostro caso la raddoppia.
        	 * */
        	this.items=Arrays.copyOf(this.items, this.items.length*2);
        	//Quindi aggiungo l'elemento in questione siccome ora ho spazio
        	this.items[this.numberOfItems]=i;
            numberOfItems++;	
        }
        return true;
    }

    /**
     * Cerca se è presente un oggetto sulla mensola. La ricerca utilizza il
     * metodo equals della classe dell'oggetto.
     * 
     * @param i
     *              un oggetto per cercare sulla mensola un oggetto uguale a i
     * @return null se sulla mensola non c'è nessun oggetto uguale a i,
     *         altrimenti l'oggetto x che si trova sulla mensola tale che
     *         i.equals(x) == true
     * @throws NullPointerException
     *                                  se l'oggetto passato è null
     */
    public ShelfItem search(ShelfItem i) 
    {
    	//Controllo se oggetto passato e' nullo
    	if(i==null)
    	{
    		throw new NullPointerException("| ERROR | Oggetto null");
    	}
    	
    	/*
    	 * Ciclo che mi permette di scorrere l'array non per la sua lunghezza ma 
    	 * per il numero di oggetti inserito restituendomi l'oggetto designato
    	 * */
        for(int j=0; j<this.numberOfItems; j++)
        {
        	//Per vedere se i due oggetti coincidono utilizzo la funzione "equals()"
        	if(this.items[j].equals(i)==true)
        	{
        		//Oggetto uguale a indice j
        		return this.items[j];
        	}
        }
        //A questo punto non ho un oggetto uguale
        return null;
    }

    /**
     * @return il numero attuale di oggetti appoggiati sulla mensola
     */
    public int getNumberOfItems() 
    {
        return this.numberOfItems;
    }

    /*
     * protected, per solo scopo di JUnit testing
     */
    protected ShelfItem[] getItems() 
    {
        return this.items;
    }

    /**
     * @return the currentTotalWeight
     */
    public double getCurrentTotalWeight() 
    {
    	/*
    	 * Metodo che somma ad una variabile inizializzata a 0
    	 * i valori del campo Weight di tutti gli oggetti immessi nella mensola
    	 * */
    	double pesoTotaleCorrente=0;
        for(int i=0; i<this.numberOfItems; i++)
        {
        	pesoTotaleCorrente+=items[i].getWeight();
        }
        //Ritorno la somma finale
        return pesoTotaleCorrente;
    }

    /**
     * @return the currentTotalOccupiedSurface
     */
    public double getCurrentTotalOccupiedSurface() 
    {
    	//Stesso principio di "getCurrentTotalWeight()"
    	double superficieTotaleCorrente=0;
    	for(int i=0; i<this.numberOfItems; i++)
    	{
    		superficieTotaleCorrente+=items[i].getOccupiedSurface();
    	}
    	//Ritorno la somma finale
    	return superficieTotaleCorrente;
    }

    /**
     * @return the maxLength
     */
    public double getMaxLength() 
    {
        return maxLength;
    }

    /**
     * @return the maxWidth
     */
    public double getMaxWidth() 
    {
        return maxWidth;
    }

    /**
     * @return the maxOccupableSurface
     */
    public double getMaxOccupableSurface() 
    {
        return maxOccupableSurface;
    }

    /**
     * @return the maxTotalWeight
     */
    public double getMaxTotalWeight() 
    {
        return maxTotalWeight;
    }
}