package it.unicam.cs.asdl2223.mp1;

/**
 * Il crivello di Eratostene è un modo per determinare tutti i numeri primi da
 * {@code 1} a un certo intero {@code n} assegnato. Il crivello può essere
 * semplicemente rappresentato da un array di booleani in cui la posizione i è
 * true se e solo se il numero i è primo. Per costruirlo si segue un semplice
 * algoritmo che parte dalla posizione 2 e la mette a true. Dopodiché vengono
 * messe a false tutte le posizioni multiple di 2. Poi viene determinata la
 * prima posizione successiva a 2 che non è stato messa a false, in questo caso
 * 3. Si passa quindi a mettere a false tutte le posizioni multiple di 3. Si
 * ripete la procedura per la posizione non false successiva, che sarà 5. E così
 * via fino ad aver visitato tutte le caselle minori o uguali alla capacità del
 * crivello.
 * 
 * Un oggetto di questa classe permette di conoscere direttamente se un numero
 * tra 2 e la capacità del crivello è primo tramite il metodo isPrime(int).
 * 
 * Inoltre un oggetto di questa classe fornisce la funzionalità di elencare
 * tutti i numeri primi da 2 alla capacità del crivello tramite una serie di
 * chiamate al metodo nextPrime(). L'elenco può essere fatto ripartire in
 * qualsiasi momento chiamando il metodo restartPrimeIteration() e si interrompe
 * non appena il metodo hasNextPrime() restituisce false.
 * 
 * @author Luca Tesei (template) Vasil Ferecka vasil.ferecka@studenti.unicam.it (implementazione)
 *
 */
public class CrivelloDiEratostene {
    /*
     * Array di booleani che rappresenta il crivello. La posizione i dell'arraya
     * è true se e solo se il numero i è primo altrimenti la posizione i deve
     * essere false. Le posizioni 0 e 1 dell'array non sono significative e non
     * vengono usate. L'ultima posizione dell'array deve essere uguale alla
     * capacità del crivello.
     */
    private boolean[] crivello;

    /*
     * Capacità del crivello, immutabile
     */
    private final int capacity;
    
    //--VARIABILE INTERNA DEFINITA DA ME---
    
    /*
     * Variabile utilizzata come "testina puntatrice" durante lo scorrimento del crivello
     *  che contiene l'indice della cella corrente. Se, precedentemente,
     *  il crivello e' stato scorso e non ho piu' numeri primi; la variabile avra'
     *  un valore > 1 e necessito di una chiamata a restartPrimeIteration() . 
     *  Quando chiamo la procedura  restartPrimeIteration(), la variabile vienie
     *  riportata ad 1
     *  */
    private int posizioneCorrente;
    
    /**
     * Costruisce e inizializza il crivello di Eratostene fino alla capacità
     * data. La capacità deve essere almeno 2.
     * 
     * @param capacity
     *                     capacità del crivello, almeno 2
     * 
     * @throws IllegalArgumentException
     *                                      se il numero {@code capacity} è
     *                                      minore di {@code 2}
     */
    public CrivelloDiEratostene(int capacity) 
    {
    	if(capacity<2)
    	{
    		throw new IllegalArgumentException("| ERROR | La capacita del crivello di Eratostane deve essere almeno 2");
    	}
        this.capacity = capacity;
        this.crivello = new boolean[this.capacity + 1];
        this.posizioneCorrente=1;
        
        //Prima lo inizializzo
        this.inizializeCrivello();
        //Poi lo risolvo utilizzando il principio di Eratostene
        this.calcolaCrivello();
    }

    /**
     * Restituisce la capacità di questo crivello, cioè il numero massimo di
     * entrate.
     * 
     * @return la capacità di questo crivello
     */
    public int getCapacity() 
    {
        return this.capacity;
    }

    /**
     * Controlla se un numero è primo. Può rispondere solo se il numero passato
     * come parametro è minore o uguale alla capacità di questo crivello.
     * 
     * @param n
     *              il numero da controllare
     * @return true se il numero passato è primo, false altrimenti
     * @throws IllegalArgumentException
     *                                      se il numero passato {@code n}
     *                                      eccede la capacità di questo
     *                                      crivello o se è un numero minore di
     *                                      2.
     */
    public boolean isPrime(int n) 
    {
    	//Lancio l'exception se il valore passato e minore di 2 e maggiore di capacity
    	if(n<2 || n>this.capacity)
    	{
    		throw new IllegalArgumentException("| ERROR | Il numero deve essere maggiore di 2 e non superiore alla capacita' del crivello");
    	}
    	
    	//Ritorno il  valore della cella. Se primo il valore e' true. Fslse altrimenti
    	return this.crivello[n];
    }

    /**
     * Indica se l'elenco corrente dei numeri primi di questo crivello ha ancora
     * un numero disponibile da elencare o se l'elenco è giunto al termine
     * perché sono già stati elencati tutti i numeri primi minori uguali alla
     * capacità. Se il metodo restituisce true, può essere fatta una ulteriore
     * chiamata al metodo nextPrime() per ottenere il numero successivo
     * nell'elenco. Se il metodo restituisce false non si potrà più chiamare il
     * metodo nextPrime() fino a quando l'elenco non viene fatto ripartire
     * tramite il metodo restartPrimeIteration().
     * 
     * @return true se c'è ancora un numero primo nell'elenco dei numeri primi
     *         di questo livello, false se sono già stati elencati tutti i
     *         numeri primi di questo crivello.
     */
    public boolean hasNextPrime() 
    {
   		/*
   		 * Siccome la variabile "posizioneCorrente" contiene l'indice di scorrimento corrente,
   		 * quando si passa al ciclo for, necessito di incrementarlo di uno dato che, in questo modo
   		 * soddisfo due condizioni:
   		 * 	-1- parto da due cosi' da soddisfare le richeste designate dal javadoc
   		 * 	-2- se non l'ho avessi incrementato, sarebbe rimasto sempre sullo stesso primo restituendo 
   		 * 		sempre lo stesso idice;
   		 * */
        for(int i=this.posizioneCorrente+1; i<this.crivello.length; i++)
        {
        	/*
        	 * Senza fare ulteriori controlli richiamo il metodo gia' 
        	 * implementato per la verifica di un numero primo
        	 * */
        	if(this.isPrime(i)==true)
        	{
        		//Vi e' effettivamente un primo successivo
        		return true;
        	}
        }
        /*
         * Il crivello non ha piu' numeri primi da offrire, necessito di una chiamata a restartPrimeIteration().
         * Tutti i primi sono stati precedentemente scansionati
         */
        return false;
    }

    /**
     * Restituisce il prossimo numero primo in questo crivello nell'elenco
     * corrente. L'elenco parte sempre dal numero 2 e si interrompe non appena
     * il metodo hasNextPrime() diventa false. Il metodo lancia l'eccezione
     * IllegalStateException se si prova a chiedere il prossimo numero primo
     * quando l'elenco corrente è terminato. L'elenco può essere fatto ripartire
     * in qualsiasi momento chiamando il metodo restartPrimeIteration().
     * 
     * @return il prossimo numero primo nell'elenco corrente
     * @throws IllegalStateException
     *                                   se l'elenco è terminato e non è stato
     *                                   ancora fatto ripartire.
     */
    public int nextPrime() 
    {
    	/*
    	 * La condizione unica mi permette di controllare implicitamente due eventi:
    	 * 	-1-Con hasNextPrime() se ho altri prime da offrire
    	 * 	-2-Se ho fatto ripartire l'elenco terminato
    	 *  */
   		if(this.hasNextPrime()==false)
		{
    		throw new IllegalStateException("| ERROR | Il crivello e terminato, fallo ripartire");
		}
  
   		//Il principio e' lo stesso di hasNextPrime()
    	for(int i=this.posizioneCorrente+1; i<this.crivello.length; i++)
    	{
    		if(this.isPrime(i)==true)
            {
            	//Cambio posizione corrente mettendoci il numero primo successivo a quello di partenza
            	this.posizioneCorrente=i;
            	return i;	
            }
    	}
    	return -1;
    }

    /**
     * Fa ripartire da 2 l'elenco corrente dei numeri primi fino alla capacità
     * di questo crivello. Questo metodo può essere chiamato in qualsiasi
     * momento, anche se l'elenco corrente non è ancora terminato. L'effetto è
     * comunque di ricominciare da 2.
     */
    public void restartPrimeIteration() 
    {
    	//Resetta la posizione corrente ad 1 in modo tale da permettere a nextPrime() di essere eseguito
        this.posizioneCorrente=1;    
    }

    //---METODI INTERNI PRIVATI DEFINITI DA ME---
    
    //Procedura interna che mi permette di calcolare i numeri primi con il metodo di Eratostene
    private void inizializeCrivello()
    {
        for(int i=2; i<this.crivello.length; i++)
        {
        	this.crivello[i]=true;
        }
    }
    
    //Procedura interna che mi permette di calcolare i numeri primi con il metodo di Eratostene
    private void calcolaCrivello()
    {
        /*
         * Ovviamente il mio ciclo for partira' da due siccome 
         * le posizioni 0 ed 1 del crivello di Eratostene non sono utili
         */
        for(int i=2; i<=Math.sqrt(this.capacity); i++)
        {
        	if(this.crivello[i]==true)
        	{
        		//Ciclo che pone a false tutti i multipli
                for(int j=i*2; j<this.crivello.length; j+=i)
                {
                	this.crivello[j]=false;
                }
        	}
        }
    }
}