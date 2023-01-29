package it.unicam.cs.asdl2223.mp1;

import java.util.Arrays;

/**
 * Un fattorizzatore è un agente che fattorizza un qualsiasi numero naturale nei
 * sui fattori primi.
 * 
 * @author Luca Tesei (template) Vasil Ferecka vasil.ferecka@studenti.unicam.it (implementazione)
 *
 */
public class Factoriser {
	
    /**
     * Fattorizza un numero restituendo la sequenza crescente dei suoi fattori
     * primi. La molteplicità di ogni fattore primo esprime quante volte il
     * fattore stesso divide il numero fattorizzato. Per convenzione non viene
     * mai restituito il fattore 1. Il minimo numero fattorizzabile è 1. In
     * questo caso viene restituito un array vuoto.
     * 
     * @param n
     *              un numero intero da fattorizzare
     * @return un array contenente i fattori primi di n
     * @throws IllegalArgumentException
     *                                      se si chiede di fattorizzare un
     *                                      numero minore di 1.
     */
    public Factor[] getFactors(int n) 
    {
    	//Controllo se il numero passato e' minore di 1
    	if(n<1)
    	{
    		throw new IllegalArgumentException("| ERROR | Non posso fattorizzare un numero inferiore a 1");
    	}
    	
    	//Ritorno un array vuoto
       	if(n==1)
    	{
       		return new Factor[0];
    	}
       	
       	/*
       	 * arrivati a questo punto del metodo, n e' maggiore di 1, 
       	 * quindi n e' fattorizzabile
       	 * */
    	return calcolaFattorizzazione(n);
    }
    
    //---METODO INTERNO PRIVATO DEFINITO DA ME---
    
    /*
     * Ho scelto di fare un metodo interno per il calocolo della fattorizzaione
     * in modo tale da rendere piu' chiara la lettura del codice ed
     * il scovare gli errori logici di codice.
     * */
    private Factor[] calcolaFattorizzazione(int n)
    {
    	/*
    	 * Tutte le variabili e gli oggetti che seguono, ho deciso di allocarli
    	 * all'interno del metodo e non sullo spazio di dichiarazione della classe
    	 * in modo tale da associre lo spazio in memoria alle variabili solo se
    	 * il mio n e' fattorizzabile.
    	 * */
    	
    	//Tiene conto della molteplicita' del numero primo che divide n
    	int counterMolteplicity=0;
    	//Tiene conto del numero di oggetti Factor da inserire nell'array
    	int counterElements=0;
    	/*
    	 * Dichiaro ed alloco un array di tipo Factor con grandezza radq(n)
    	 * che rappresenta il numero massimo di fattori 
    	 * i quali sommati restituiscono n stesso. Ovviamente come grandezza
    	 * non e' ottimizzata, ma verra' sistemata una volta che all'array
    	 * ho aggiunto tutti i fattori che ho calcolato
    	 * */
    	Factor[] arrayFactor=new Factor[(int) Math.sqrt(n)];
    	/*
    	 * Un modo per avere tutti i numeri primi e' quello di
    	 * avere un crivello di Eratostene che me li fornisce
    	 * */
    	CrivelloDiEratostene crivello=new CrivelloDiEratostene(n);
    	/*
    	 * Variabile interna alla quale assegnero il numero primo successivo
    	 * fornito dal crivello precedentemente dichiarato ed allocato
    	 * */
    	int numeroPrimoAttuale=0;
    	
    	//Se durante la fattorizzazione n diventa uguale ad 1 termino
		while(n>1)
		{
			//Assegnazionone del numero primo successivo a numeroPrimoAttuale
			numeroPrimoAttuale=crivello.nextPrime();
			//Verifico se n e' multiplo di numeroPrimoAttuale
			if(n%numeroPrimoAttuale==0 && n>1)
			{
				/*
				 * Ripeto l'operazione di fattorizzazione con un numero primo
				 *  affinche' n e' multiplo del numero primo
				 * */
				while(n%numeroPrimoAttuale==0 && n>1)
				{
					n/=numeroPrimoAttuale;
					/*
					 * Ogni ciclo dove n (nonostante la divisione precedente)
					 * risulta essere multiplo di numeroPrimoAttuale (il fattore primo)
					 * allora la sua molteplicita' viene incrementata.
					 * In quanto richiedo un ulteriore divisione 
					 * per quello stesso numeroPrimoAttuale
					 * */
					counterMolteplicity++;
				}
				/*
				 * Giunti a questo punto abbiamo ottenuto la scomposizione massima
				 * di n per quel determinato numeroPrimoAttuale per counterMolteplicity volte. 
				 * Quindi posso creare un oggetto Factor avente come parametri c e counterMolteplicity, 
				 * poi lo aggiungo all'array in posizione counterMolteplicity
				 * */
				arrayFactor[counterElements]=new Factor(numeroPrimoAttuale,counterMolteplicity);
				/*
				 * Siccome da ora in poi avro' un altro numero primo
				 * resetto la molteplicita'
				 *  */
				counterMolteplicity=0;
				/*
				 * Dato che ho inserito un oggetto Factor nell'array,
				 * il counter dei suoi elementi si incrementa
				 * */
				counterElements++;
			}
		}
		
		/*
    	 * Utilizzo il metodo copyOf() che prende come parametro l'array attuale, 
    	 * lo copia e gli da una nuova lunghezza passata come parametro. 
    	 * In questo caso la nuova lunghezza coincide esattamente con il
    	 * numero di elemti inseriti nell'array
    	 * */
    	arrayFactor=Arrays.copyOf(arrayFactor, counterElements);
    	//Ritorno l'array con la giusta grandezza
    	return arrayFactor;
    }
}