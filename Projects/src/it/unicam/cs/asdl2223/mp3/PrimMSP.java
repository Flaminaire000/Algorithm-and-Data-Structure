package it.unicam.cs.asdl2223.mp3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


//ATTENZIONE: è vietato includere import a pacchetti che non siano della Java SE

/**
 * Classe singoletto che implementa l'algoritmo di Prim per trovare un Minimum
 * Spanning Tree di un grafo non orientato, pesato e con pesi non negativi.
 * 
 * L'algoritmo richiede l'uso di una coda di min priorità tra i nodi che può
 * essere realizzata con una semplice ArrayList (non c'è bisogno di ottimizzare
 * le operazioni di inserimento, di estrazione del minimo, o di decremento della
 * priorità).
 * 
 * Si possono usare i colori dei nodi per registrare la scoperta e la visita
 * effettuata dei nodi.
 * 
 * @author Luca Tesei (template) Vasil Ferecka vasil.ferecka@studenti.unicam.it (implementazione)
 * 
 * @param <L>
 *                tipo delle etichette dei nodi del grafo
 *
 */
public class PrimMSP<L> {

	//--- VARIABILI ISTANZIATE DA ME ---
	private List<GraphNode<L>> priorityQueue;

    /*
     * In particolare: si deve usare una coda con priorità che può semplicemente
     * essere realizzata con una List<GraphNode<L>> e si deve mantenere un
     * insieme dei nodi già visitati
     */

    /**
     * Crea un nuovo algoritmo e inizializza la coda di priorità con una coda
     * vuota.
     */
    public PrimMSP() 
    {
    	this.priorityQueue=new ArrayList<GraphNode<L>>();
    }

    /**
     * Utilizza l'algoritmo goloso di Prim per trovare un albero di copertura
     * minimo in un grafo non orientato e pesato, con pesi degli archi non
     * negativi. Dopo l'esecuzione del metodo nei nodi del grafo il campo
     * previous deve contenere un puntatore a un nodo in accordo all'albero di
     * copertura minimo calcolato, la cui radice è il nodo sorgente passato.
     * 
     * @param g
     *              un grafo non orientato, pesato, con pesi non negativi
     * @param s
     *              il nodo del grafo g sorgente, cioè da cui parte il calcolo
     *              dell'albero di copertura minimo. Tale nodo sarà la radice
     *              dell'albero di copertura trovato
     * 
     * @throw NullPointerException se il grafo g o il nodo sorgente s sono nulli
     * @throw IllegalArgumentException se il nodo sorgente s non esiste in g
     * @throw IllegalArgumentException se il grafo g è orientato, non pesato o
     *        con pesi negativi
     */
    public void computeMSP(Graph<L> g, GraphNode<L> s) 
    {
    	//Chiamo metodo che mi verifica tutti i controlli da fare come espresso nel javadoc
    	this.parametresCorrectnessChecker(g, s);
    	
    	//Chiamata metodo che inizializza il grafo
    	this.inizializePrim(g, s);
    	
    	//Oggetto che conterra' il nodo minore
    	GraphNode<L> nodoMinore;

    	//Itero affinche' ho elementi sulla coda prioritaria
    	while(this.priorityQueue.isEmpty()==false)
    	{
    		/*
    		 * Assegno alla variabile di comodo l'elemento minore.
    		 * Essendo una coda prioritaria, esso corrisponde
    		 * al primo elemento.
    		 * */
    	    nodoMinore=this.priorityQueue.get(0);
    	    Iterator<GraphNode<L>> iteratore=this.priorityQueue.iterator();
    	    //Itero affinche' ho un elemento successivo
    	    while(iteratore.hasNext()==true)
    	    {
    	    	//Assegno al nodo attuale quello successivo
    	        GraphNode<L> nodoAttuale=iteratore.next();
    	        //Controllo se il nodo attuale ha il peso minore rispetto a quello tale
    	        if(nodoAttuale.getFloatingPointDistance()<nodoMinore.getFloatingPointDistance())
    	        {
    	            nodoMinore=nodoAttuale;
    	        }
    	    }
    	    //Rimozione nodo minore
    	    this.priorityQueue.remove(nodoMinore);
    	    Iterator<GraphNode<L>> iteratoreAdiacenza=g.getAdjacentNodesOf(nodoMinore).iterator();
    	    //Itero affinche' ho nodi adiacenti
    	    while(iteratoreAdiacenza.hasNext()==true)
    	    {
    	    	//Assegno l'adiacente successivo
    	        GraphNode<L> nodoAdiacente=iteratoreAdiacenza.next();
    	        /*
    	         * Controllo se la coda prioritaria contiene il nodo adiacente
    	         * e se l'arco tra il nodo minore e questo adiacente ha costo minore 
    	         * rispetto a quello precedente.
    	         * */
    	        if(this.priorityQueue.contains(nodoAdiacente)==true && g.getEdge(nodoMinore, nodoAdiacente).getWeight()<nodoAdiacente.getFloatingPointDistance()) 
    	        {
    	        	//Imposto colore a grigio
    	            nodoAdiacente.setColor(GraphNode.COLOR_GREY);
    	            //Imposto nuovo peso minimo
    	            nodoAdiacente.setFloatingPointDistance(g.getEdge(nodoMinore, nodoAdiacente).getWeight());
    	            //Imposto predecessore nuovo
    	            nodoAdiacente.setPrevious(nodoMinore);
    	            this.priorityQueue.set(this.priorityQueue.indexOf(nodoAdiacente), nodoAdiacente);
    	        }
    	    }
    	    //A questo punto del codice il nodo ha visitato tutti i suoi adiacenti
    	    nodoMinore.setColor(GraphNode.COLOR_BLACK);
    	}
    }
    
    //--- METODI DEFINITI DA ME ---
    /*
     * In questo meodo raccolgo tutti i casi da verificare
     * e, eventualmente,  laciare l'eccezione.
     * Il tutto e' stato fatto come da istruzioni datemi
     * dal javadoc di riferimeto.
     * Il raccoglimento di tutti i controlli e' stato fatto
     * unicamente con lo scopo di semplificare la lettura del codice.
     * */
    private void parametresCorrectnessChecker(Graph<L> g, GraphNode<L> s)
    {
    	//Controllo se il grafo o il nodo sorgente sono null
    	if(g==null || s==null)
    	{
    		throw new NullPointerException("| ERROR | Grafo e/o nodo sorgente null");
    	}
    	
    	//Controllo se il nodo esiste
    	if(g.getNode(s)==null)
    	{
    		throw new IllegalArgumentException("| ERROR | Nodo inesistente");
    	}
    	
    	//Contollo se il grafo e' direzionato
    	if(g.isDirected()==true)
    	{
    		throw new IllegalArgumentException("| ERROR | Grafo orientato");
    	}
    	
    	//Ciclo che mi permette di iterare ogni elemento del grafo
        for(GraphEdge<L> element : g.getEdges())
        {
        	//Controllo se il peso e' negativo
        	if(element.getWeight()<0)
         	{
         		throw new IllegalArgumentException("| ERROR | Peso negativo");
         	}
         	
        	//Controllo se effettivaente vi e' un peso
         	if(element.hasWeight()==false)
         	{
         		throw new IllegalArgumentException("| ERROR | Arco non pesato");
         	}
         }
    }
    
    /*
	 * Lo scopo di questo metodo con iteratore e' quello di impostare il nodo di partenza a 0.
	 * Poi, per ciascun nodo:
	 * -Imposto il colore a bianco.
	 * -Il predecessore a null (ovvero nessun predecessore).
	 * -Poi lo aggiungo alla coda prioritaria.
	 * */
    private void inizializePrim(Graph<L> g, GraphNode<L> s)
    {
    	//Creo iteratore
    	Iterator<GraphNode<L>> iteratore=g.getNodes().iterator();
    	//Itero affinche' ho un successivo
    	while(iteratore.hasNext())
    	{
    	    GraphNode<L> mioNodo=iteratore.next();
    	    //Imposto colore
    	    mioNodo.setColor(0);
    	    //Imposto predecessore a null
    	    mioNodo.setPrevious(null);
    	    //Aggiunta effettiva alla coda
    	    this.priorityQueue.add(mioNodo);
    	    if(mioNodo.equals(s)==false)
    	    {
    	        mioNodo.setFloatingPointDistance(Double.POSITIVE_INFINITY);
    	    }
    	    else
    	    {
    	        mioNodo.setFloatingPointDistance(0);
    	    }
    	}
    }
}