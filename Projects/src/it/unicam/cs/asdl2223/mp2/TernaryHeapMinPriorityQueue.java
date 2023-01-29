package it.unicam.cs.asdl2223.mp2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

/**
 * Class that provides an implementation of a "dynamic" min-priority queue based
 * on a ternary heap. "Dynamic" means that the priority of an element already
 * present in the queue may be decreased, so possibly this element may become
 * the new minumum element. The elements that can be inserted may be of any
 * class implementing the interface <code>PriorityQueueElement</code>. This
 * min-priority queue does not have capacity restrictions, i.e., it is always
 * possible to insert new elements and the number of elements is unbound.
 * Duplicated elements are permitted while <code>null</code> elements are not
 * permitted.
 * 
 * @author Template: Luca Tesei, Implementation: Vasil Ferecka - vasil.ferecka@studenti.unicam.it
 *
 */
public class TernaryHeapMinPriorityQueue {

    /*
     * ArrayList for representing the ternary heap. Use all positions, including
     * position 0 (the JUnit tests will assume so). You have to adapt
     * child/parent indexing formulas consequently.
     */
    private ArrayList<PriorityQueueElement> heap;

    /**
     * Create an empty queue.
     */
    public TernaryHeapMinPriorityQueue() {
        this.heap = new ArrayList<PriorityQueueElement>();
    }

    /**
     * Return the current size of this queue.
     * 
     * @return the number of elements currently in this queue.
     */
    public int size() {
        return this.heap.size();
    }

    /**
     * Add an element to this min-priority queue. The current priority
     * associated with the element will be used to place it in the correct
     * position in the ternary heap. The handle of the element will also be set
     * accordingly.
     * 
     * @param element
     *                    the new element to add
     * @throws NullPointerException
     *                                  if the element passed is null
     */
    public void insert(PriorityQueueElement element) 
    {
    	//Controllo se l'elemento e' null
    	if(element==null)
    	{
    		throw new NullPointerException("| ERROR | Elemento null");
    	}
    	
    	//Asegno da i la grandezza attuale dello geap
    	int i=this.heap.size();
    	/*
    	 * Aggiungo l'elemto allo heap cosi' la sua
    	 * grandezza e' a +1 rispetto ad i
    	 * */
        this.heap.add(element);
        this.moveUp(element, i);
    }

    /**
     * Returns the current minimum element of this min-priority queue without
     * extracting it. This operation does not affect the ternary heap.
     * 
     * @return the current minimum element of this min-priority queue
     * 
     * @throws NoSuchElementException
     *                                    if this min-priority queue is empty
     */
    public PriorityQueueElement minimum() 
    {
    	//Controllo se vi e' almeno un elemento
    	if(this.heap.isEmpty()==true)
    	{
    		throw new NoSuchElementException("| ERROR | Nessun elemento presente nell'albero ternario");
    	}
    	//Siccome l'inserimento e' ordinato, l'elemento in posizione 0 e' il piu' piccolo
    	return this.heap.get(0);
    }

    /**
     * Extract the current minimum element from this min-priority queue. The
     * ternary heap will be updated accordingly.
     * 
     * @return the current minimum element
     * @throws NoSuchElementException
     *                                    if this min-priority queue is empty
     */
    public PriorityQueueElement extractMinimum() 
    {
    	//Controllo se vi e' almeno un elemento
    	if(this.heap.isEmpty()==true)
    	{
    		throw new NoSuchElementException("| ERROR | Nessun elemento presente nell'albero ternario");
    	}
    	
    	//Assegno a min l'effettivo elemento con priorita' minore
    	PriorityQueueElement min = this.minimum();
    	//Controllo se ho solo un elemento
   	 	if (this.heap.size()==1)
   	 	{
   	 		//Se ne ho effettivamente solo uno chiamo clear().
   	 		this.heap.clear();
   	 	}
   	 	else 
   	 	{
   	 		/*
   	 		 * A questo punto del codice vuol dire che ho piu' elementi,
   	 		 * Quindi scambio il mio elemento piu' piccolo con l'ultimo
   	 		 * elemeto dell'albero. per poi eliminare il piu' piccolo che 
   	 		 * ero si trova in ultima posizione. Infine chiamo il metodo
   	 		 * heapify() che riordina lo heap secondo la regola del min Heap
   	 		 * */
   	 		Collections.swap(this.heap, 0, this.heap.size()-1);
   	 		this.heap.remove(this.heap.size()-1);
   	 		//Siccome ho rimosso un elemento precedentemente scambiato con un altro, riordino gli handle
        	this.assignHandle();
   	 		this.heapify(0);
   	 	}
   	 	//Ritorno il minimo oramai cancellato dallo heap
   	 	return min;
    }

    /**
     * Decrease the priority associated to an element of this min-priority
     * queue. The position of the element in the ternary heap must be changed
     * accordingly. The changed element may become the minimum element. The
     * handle of the element will also be changed accordingly.
     * 
     * @param element
     *                        the element whose priority will be decreased, it
     *                        must currently be inside this min-priority queue
     * @param newPriority
     *                        the new priority to assign to the element
     * 
     * @throws NoSuchElementException
     *                                      if the element is not currently
     *                                      present in this min-priority queue
     * @throws IllegalArgumentException
     *                                      if the specified newPriority is not
     *                                      strictly less than the current
     *                                      priority of the element
     */
    public void decreasePriority(PriorityQueueElement element,double newPriority) 
    {
    	//Controllo se l'elemento e' contenuto nello heap
    	if(this.heap.contains(element)==false)
    	{
    		throw new NoSuchElementException("| ERROR | Nessun elemento presente nell'albero ternario");
    	}
    	
    	//Controllo se la priorita' da cambiare e' maggiore rispetto a quella gia' inserita
    	if(newPriority>=element.getPriority())
    	{
    		throw new IllegalArgumentException("| ERROR | la nuova priorita' deve essere minore della precedente");
    	}
    	
    	//Mi trovo l'indice dell'elemento a cui cambiare la priorita'
    	int index=this.heap.indexOf(element);
    	//Cambio la priorita' con quella nuova
    	this.heap.get(index).setPriority(newPriority);
    	this.moveUp(element, index);
    }

    /**
     * Erase all the elements from this min-priority queue. After this operation
     * this min-priority queue is empty.
     */
    public void clear() {
        this.heap.clear();
    }

    //--- METODI IMPLEMENTATI DA ME ---
    
    // Funzione che restituisce l'indice dell'elemento figlio a sinistra del nodo i
    private int leftIndex(int i) 
    {
    	//Addattata per lo heap ternario
        return 3*i+1;
    }

    // Funzione che restituisce l'indice dell'elemento figlio centrale del nodo i
    private int midIndex(int i) 
    {
    	//Addattata per lo heap ternario
        return 3*i+2;
    }
    
    // Funzione che restituisce l'indice dell'elemento figlio a destra del nodo i
    private int rightIndex(int i) 
    {
    	//Addattata per lo heap ternario
        return 3*i+3;
    }

    // Funzione che restituisce l'indice dell'elemento padre rispetto al nodo in indice i
    private int parentIndex(int i) 
    {
    	//Addattata per lo heap ternario
    	return (i-1)/3;
    }

    /*
     * Metodo ricorsivo che ricostruisce lo heap se e solo se
     * i sottoalberi inferiori rispetto all'indice sono "heapizzati"
     */
    private void heapify(int i) 
    {
    	//indice attuale
    	int min=i;
    	// Essendo un heap ternario avro' tre nodi figli
        int mioLeft=this.leftIndex(i);
        int mioMid=this.midIndex(i);
        int mioRight=this.rightIndex(i);
        
        /*
         * Controllo se l'indice del mio elemento a sinistra e minore della grandezza dello heap,
         * poi controllo se la priorita del mio elemto a sinistra e' minore dell'elemento padre.
         * Se lo e', il nuovo minimo diventa l'indice a sinistra.
         * */
        if(mioLeft<this.heap.size() && this.heap.get(mioLeft).getPriority()<this.heap.get(i).getPriority())
        {
        	//Assegno ad i l'indice dell'elemento a sinistra
        	min=mioLeft;
        }
        
        
        /*
         * Controllo sempre se l'indice del mio elemento centrale e' minore della grandezza dello heap.
         * Ma da adesso controllero se la priorita' del mio elemento centrale e' minore rispetto a quella
         * dell'elemento corrispondente all'indice min (quindi o il nodo padre, oppure il nodo a sinistra)
         * perche' se e' minore dell'elemento a sinistra il percorso da percorrere e' quello centrale
         * */
        if(mioMid<this.heap.size() && this.heap.get(mioMid).getPriority()<this.heap.get(min).getPriority())
        {
        	min=mioMid;
        }
        
        
        /*
         * Stesso discorso di prima ma adesso min puo' corrispondere a:
         * 1- elemento padre
         * 2- elemento a sinistra
         * 3- elemento centrale
         * */
        if(mioRight<this.heap.size() && this.heap.get(mioRight).getPriority()<this.heap.get(min).getPriority())
        {
        	min=mioRight;
        }
        
        //Controllo se l'indice non e' rimasto quello dipartenza
        if(min!=i)
        { 
        	/*
        	 * Scambio l'elemento ad i con l'elemento con indice
        	 * min con il metodo swap() di Collections.
        	 * */
        	Collections.swap(this.heap, i, min);
        	//Riassegno gli handle siccome disordinati dopo lo swap
        	this.assignHandle();
        	/*
        	 * RIchiamo ricorsivamente heapify() per controllare se il nuovo min
        	 * e' tale anche rispetto agli elementi ancora piu' in basso
        	 * */
        	this.heapify(min);
        }
    }
    
    /*
     * Metodo che mi permette di scambiare di posto un elemento figlio con
     * l'elemento padre se e solo se la sua priorita' e minore.
     * Inoltre aggiusto gli handle.
     * */
    private void moveUp(PriorityQueueElement element, int i)
    { 
    	 //Controllo se ho almeno 1 elemento
    	 while(i>0)
         {
         	/*
         	 * Se la priorita' dell'elemento appena inserito e' minore
         	 * rispetto a quella del suo nodo padre, scambio i due elementi.
         	 * */
         	if(element.getPriority()<this.heap.get(this.parentIndex(i)).getPriority())
         	{
         		//Per scambiarli richiamo il metodo swap() definito in Collections
         		Collections.swap(this.heap, this.parentIndex(i), i);
         		
         	}
         	/*
         	 * Per controllare se la priorida del nuovo elemento e' minore
         	 * del nodo padre del padre appena controllato, assegno ad i
         	 * l'indice del padre successivo.
         	 * */
         	i=this.parentIndex(i);
         }
         /*
          * A questo punto vuol dire che tutti i possibili swap sono stati
          * eseguiti, ma gli handle sono comunque disordinati. Pertanto 
          * richiamo una funzione da me definita che li riassegna nel corretto ordine.
          * */
         this.assignHandle();
    }
    
    /*
     * Metodo che riassegna in modo corretto gli handle.
     * Cicla per la grandezza attuale dello heap e, ad ogni
     * ciclo corrisponde l'indice di un elemento. Ovviamente
     * lo heap deve essere ordiato in precedenza.
     * */
    //--- NOTA PERSONALE ---
    /*
     * E' vero che questo non e' il metodo piu' efficiente 
     * temporalmente per assegnare gli handle, ma fecendo cosi ho:
     * 1- Risparmiato spazio in memoria
     * 2- Ho la sicurezza che tutti gli elementi hanno un handle corretto
     * 	  Invece, scambiando solo quello degli elementi interessati, 
     * 	  ho la siccurezza solo di quest'ultimi e non degli altri.
     * 3- E' piu' facile da implementare.
     * */
    private void assignHandle()
    {
    	for(int i=0; i<this.heap.size(); i++)
    	{
    		this.heap.get(i).setHandle(i);
    	}
    }
    
    /*
     * This method is only for JUnit testing purposes.
     */
    protected ArrayList<PriorityQueueElement> getTernaryHeap() {
        return this.heap;
    }
}