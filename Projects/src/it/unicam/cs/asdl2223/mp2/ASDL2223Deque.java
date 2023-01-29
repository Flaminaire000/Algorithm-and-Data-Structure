/**
 * 
 */
package it.unicam.cs.asdl2223.mp2;

import java.util.Collection;


import java.util.ConcurrentModificationException;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Implementation of the Java SE Double-ended Queue (Deque) interface
 * (<code>java.util.Deque</code>) based on a double linked list. This deque does
 * not have capacity restrictions, i.e., it is always possible to insert new
 * elements and the number of elements is unbound. Duplicated elements are
 * permitted while <code>null</code> elements are not permitted. Being
 * <code>Deque</code> a sub-interface of
 * <code>Queue<code>, this class can be used also as an implementaion of a <code>Queue</code>
 * and of a <code>Stack</code>.
 * 
 * The following operations are not supported:
 * <ul>
 * <li><code>public <T> T[] toArray(T[] a)</code></li>
 * <li><code>public boolean removeAll(Collection<?> c)</code></li>
 * <li><code>public boolean retainAll(Collection<?> c)</code></li>
 * <li><code>public boolean removeFirstOccurrence(Object o)</code></li>
 * <li><code>public boolean removeLastOccurrence(Object o)</code></li>
 * </ul>
 * 
 * @author Template: Luca Tesei, Implementation: Vasil Ferecka - vasil.ferecka@studenti.unicam.it
 *
 */
public class ASDL2223Deque<E> implements Deque<E> {

    /*
     * Current number of elements in this deque
     */
    private int size;

    /*
     * Pointer to the first element of the double-linked list used to implement
     * this deque
     */
    private Node<E> first;

    /*
     * Pointer to the last element of the double-linked list used to implement
     * this deque
     */
    private Node<E> last;

    //--- VARIABILE IMPLEMENTATA DA ME ---
    /*
     * Ogni volta che vado a modificare la lista originale,
     * (quindi ogni volta che aggiungo, rimuovo o pulisco la queue)
     * il contatore verra' incrementato. Quest'ultimo serivira'
     * per garantire all'iteratore la proprieta' fail-fast.
     * */
    private int numeroModifiche;

    /**
     * Constructs an empty deque.
     */
    public ASDL2223Deque() 
    {
    	//Dimensione a 0
        this.size=0;
        //Nessun first inserita
        this.first=null;
        //Nessun last inserito
        this.last=null;
        //Inizializzazione Numero modifiche
        this.numeroModifiche=0;
    }

    @Override
    public boolean isEmpty() 
    {
    	/*
    	 * Se la dimensione e' uguale a 0
    	 * implica che nessun elemento e' stato inserito
    	 * nella lista, e restituicse true.
    	 * False altrimenti.
    	 * */
    	if(this.size==0)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }

    @Override
    public Object[] toArray() 
    {
    	/*
    	 * Creo un array di dimensioni pari alla lista
    	 * e di tipo Object.
    	 * */
    	int counterCicli=this.size;
    	Node<E> nodoCorrente=this.first;
    	Object[] array=new Object[this.size];
    	//Counter che scorre le ppsizioni dell'array
        int i=0;
        
        /*
         * Ciclo for each dove ad ogni ciclo associo
         * un elemento della lista alla cella i dell'array.
         * */
        while(counterCicli!=0) 
        {
        	array[i]=nodoCorrente.item;
        	//Passo al nodo successivo
        	nodoCorrente=nodoCorrente.next;
        	//Incremento counter per passare alla posizione successiva
            i++;
            //Decremento i cicli da eseguire fino a 0 (tutti elementi inseriti) 
            counterCicli--;
        }
        
        //Ritorno array
        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException(
                "This class does not implement this service.");
    }

    @Override
    public boolean containsAll(Collection<?> c) 
    {
    	//Controllo se collections passata e' nulla
    	if(c==null)
    	{
    		throw new NullPointerException("| ERROR | Collection null");
    	}
    	
    	Iterator<?> iteratore = c.iterator();
    	
    	//Ciclo affinche' ho un prossimo elemento
    	while (iteratore.hasNext())
    	{
    		/*
    		 * Chiamo il metodol ridefinito "contains(Object o).
    		 * Se quel determinato elemento e' contenuta nella lista.
    		 * */
    		if(this.contains(iteratore.next())==false)
        	{
        		return false;
        	}
    	}
    	return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) 
    {
    	//Controllo se collections passata e' nulla
    	if(c==null)
    	{
    		throw new NullPointerException("| ERROR | Collection null");
    	}
    	
    	Iterator<? extends E> iteratore = c.iterator();
    	
    	//Ciclo affinche' ho un prossimo elemento
    	while (iteratore.hasNext())
    	{
    		//Aggiungo elemento
    		this.add(iteratore.next());
    	}
    	//Incremento il numero delle modifiche
		this.numeroModifiche++;
    	return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException(
                "This class does not implement this service.");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException(
                "This class does not implement this service.");
    }

    @Override
    public void clear() 
    {
    	//Riporto tutto a null e/o 0
        this.first=null;
        this.last=null;
        this.size=0;
        //Incremento modifiche
        this.numeroModifiche++;
    }

    @Override
    public void addFirst(E e) 
    {
    	//Controllo se collections passata e' nulla
    	if(e==null)
    	{
    		throw new NullPointerException("| ERROR | Elemento null");
    	}
    	
    	//Controllo se non ho gia' un primo elemento
    	if(this.size==0)
    	{
    		/*
    		 * Istanzio un nodo con i due puntatori,
    		 * prev e next a null ed avente come item
    		 * l'elemento passato nel metodo*/
    		Node<E> nuovoNodo = new Node<E>(null, e, null);
    		//Esattamente come nel metodo "add()"
    		this.first=nuovoNodo;
    		this.last=nuovoNodo;
    	}
    	else
    	{
    		/*
    		 * Istanzio un nodo con i due puntatori,
    		 * prev a null, next al primo elemento corrente
    		 * e come iteml'elemento passato nel metodo
    		 * */
    		Node<E> nuovoNodo = new Node<E>(null, e, this.first);
    		/*
    		 * A questo punto non ho gia' un elemento first.
    		 * Quindi il first attuale puntera (nel campo prev)
    		 * al nuovo elemento, che poi prendera' il nome di first.
    		 * */
    		this.first.prev=nuovoNodo;
    		this.first=nuovoNodo;
    	}
    	//Incremento la grandezza ad operazioe eseguita
    	this.size++;
    	//Incremento il numero delle modifiche
    	this.numeroModifiche++;
    }

    @Override
    public void addLast(E e) 
    {
    	//Controllo se collections passata e' nulla
    	if(e==null)
    	{
    		throw new NullPointerException("| ERROR | Elemento null");
    	}
    	
    	//Controllo se non ho gia' un ultimo elemento
    	if(this.size==0)
    	{
    		/*
    		 * Siccome non ho un primo elemento,
    		 * richiamo un metodo gia' implementato per inserirlo.
    		 * */
    		this.addFirst(e);
    	}
    	else
    	{
    		/*
    		 * Istanzio un nodo con i due puntatori,
    		 * prev a null, next al primo elemento corrente
    		 * e come iteml'elemento passato nel metodo
    		 * */
    		Node<E> nuovoNodo = new Node<E>(this.last, e, null);
    		/*
    		 * A questo punto ho gia' un elemento first.
    		 * Quindi il last attuale puntera (nel campo next)
    		 * a null, adesso puntera' al nuovo nedo che sara'
    		 * lui  a puntare a null.
    		 * */
    		this.last.next=nuovoNodo;
    		this.last=nuovoNodo;
    		//Incremento la grandezza ad operazioe eseguita
        	this.size++;
        	//Incremento il numero delle modifiche
        	this.numeroModifiche++;
    	}
    }

    @Override
    public boolean offerFirst(E e) 
    {
    	/*
    	 * Inserisce l'elemento designato direttamente
    	 * evitando ulteriori controlli.
    	 * In questo caso aggiunge l'elemento in cima alla lista.
    	 * Quindi richiamo addFirst().
    	 * */
        this.addFirst(e);
        return true;
    }

    @Override
    public boolean offerLast(E e) 
    {
    	/*
    	 * Inserisce l'elemento designato direttamente
    	 * evitando ulteriori controlli.
    	 * In questo caso aggiunge l'elemento in coda alla lista.
    	 * Quindi richiamo addLast().
    	 * */
        this.addLast(e);
        return true;
    }

    @Override
    public E removeFirst() 
    {  
       	//Controllo se vi e' almeno un elemento
    	if(this.size==0)
    	{
    		throw new NoSuchElementException("| ERROR | Nessun elemento in coda"); 
    	}
    	
    	//Alloco un elemento che memorizza il primo item
    	E element=this.first.item;
    	/*
    	 * In questo caso vi e' solo un elemento in coda,
    	 * pertanto sia first che last puntano lo stesso nodo.
    	 * per rimuovelo chiamo clear() e non incremento
    	 * numeroModifiche in quanto lo fa gia' clear().
    	 * */
    	if(this.size==1)
    	{
    		this.clear();
    	}
    	else
    	{
    		/*
    		 * In questo brachet elimino il primo elemento
    		 * facendo puntare a first il successivo del
    		 * first precedente
    		 * */
    		this.first=this.first.next;
    		//Decremento la lunghezza
    		this.size--;
    		//Incremento il numero dell emodifiche
    		this.numeroModifiche++;
    	}
    	//Restituisco l'item
    	return element;
    }

    @Override
    public E removeLast() 
    {
    	//Controllo se vi e' almeno un elemento
    	if(this.size==0)
    	{
    		throw new NoSuchElementException("| ERROR | Nessun elemento in coda"); 
    	}
    	else
    	{
    		//Alloco un elemento che memorizza l'ultimo item
    		E element=this.last.item;
    		//Stesso discorso di removeFirst()
        	if(this.size==1)
        	{
        		this.clear();
        	}
        	else
        	{
        		/*
        		 * In questo brachet elimino l'ultimo elemento
        		 * facendo puntare a last il precedente del
        		 * last precedente
        		 * */
        		this.last=this.last.prev;
        		/*
        		 * Ovviamente devo "pulire" il campo next del nuovo last 
        		 * che precedentemente puntava al vecchio last
        		 * */
        		this.last.next=null;
        		//Decremento size
        		this.size--;
        		//Incremento il numero delle modifiche
        		this.numeroModifiche++;
        	}
        	//Ritorno l'elemento dell'ultimo
        	return element;
    	}
    }

    @Override
    public E pollFirst() 
    {
    	/*
    	 * Elimina il primo elemento della coda.
    	 * Ritorna null se la coda e' vuota.
    	 * */
        if(this.size==0)
        {
        	return null;
        }
        else
        {
        	return this.removeFirst();
        }
    }

    @Override
    public E pollLast() 
    {
    	/*
    	 * Elimina l'ultimo elemento della coda.
    	 * Ritorna null se la coda e' vuota.
    	 * */
        if(this.size==0)
        {
        	return null;
        }
        else
        {
        	return this.removeLast();
        }
    }

    @Override
    public E getFirst() 
    {
    	//Controllo se ho elementi in coda
    	if(this.size==0)
    	{
    		throw new NoSuchElementException("| ERROR | Nessun elemento in coda"); 
    	}
    	
    	//Ritorna il primo elemento
    	return this.first.item;
    }

    @Override
    public E getLast() 
    {
    	//Controllo se vi e' almeno un elemento
    	if(this.size==0)
    	{
    		throw new NoSuchElementException("| ERROR | Nessun elemento in coda"); 
    	}
    	
    	//Ritorna l'ultimo elemento
        return this.last.item;
    }

    @Override
    public E peekFirst() 
    {
    	/*
    	 * Ritorna il primo element della coda esattamente come
    	 * getFirst() ma evita l'exception facendo ritornare null
    	 * quando non ho elementi.
    	 * */
    	if(this.size==0)
    	{
    		return null;
    	}
    	else
    	{
    		return this.first.item;
    	}
    }

    @Override
    public E peekLast() 
    {
    	/*
    	 * Ritorna l'ultimo element della coda esattamente come
    	 * getLast() ma evita l'exception facendo ritornare null
    	 * quando non ho elementi.
    	 * */
    	if(this.size==0)
    	{
    		return null;
    	}
    	else
    	{
    		return this.last.item;
    	}
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        throw new UnsupportedOperationException(
                "This class does not implement this service.");
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        throw new UnsupportedOperationException(
                "This class does not implement this service.");
    }

    @Override
    public boolean add(E e) 
    {
    	/*
    	 * Richiamo addLast() perche' la coda prevede di base 
    	 * l'inserimento degli elementi nuovi dal fondo.
    	 * */
    	this.addLast(e);
    	return true;
    }

    @Override
    public boolean offer(E e) 
    {
    	//Esattamente come add().
        this.addLast(e);
        return true;
    }

    @Override
    public E remove() 
    {
    	/*
    	 * Richiamo removeFirst() perche' la coda prevede di base 
    	 * la rimozione degli elementi in coda dall'inizio.
    	 * */
        return this.removeFirst();
    }

    @Override
    public E poll() 
    {
    	//Esattamente come pollFirst().
    	return this.pollFirst();
    }

    @Override
    public E element() 
    {
    	//Ritorna il Primo elemento
        return this.getFirst();
    }

    @Override
    public E peek() 
    {
    	//Ritorna il Primo elemento
        return this.peekFirst();
    }

    @Override
    public void push(E e) 
    {
    	//Aggiunge l' elemento
    	this.addFirst(e);
    }

    @Override
    public E pop() 
    {
    	//Rimuove il primo elemento
        return this.removeFirst();
    }

    @Override
    public boolean remove(Object o) 
    {
    	//Controllo se l'oggetto e' null
    	if(o==null)
    	{
    		throw new NullPointerException("| ERROR | Oggetto null");
    	}
    	
    	/*
    	 * Controllo se l'elemento da cancellare e' in coda.
    	 * Se non lo e' ritorno false.
    	 * */
    	if(this.contains(o)==false)
    	{
    		return false;
    	}
    	
    	/*
    	 * Se l'elemento da cancelllare e' il primo,
    	 * lo rimuovo direttamente.
    	 * */
    	if(this.first.item.equals(o)) 
    	{
    		this.removeFirst();
    		return true;
    	}
    	
    	/*
    	 * Se l'elemento da cancelllare e' l'ultimo,
    	 * lo rimuovo direttamente.
    	 * */
    	if(this.last.item.equals(o)) 
    	{
    		this.removeLast();
    		this.numeroModifiche++;
    		return true;
    	}
    	
    	/*
    	 * Mi salvo la posizione del primo elemento originario
    	 * su un nuovo nodo.
    	 *  */
    	Node<E> head=this.first;
    	//Indice per il numero di cicli da eseguire
    	int index=0;
    	//Itero affinche' head e' null
    	while(head!=null)
    	{
    		//Controllo se l'elemento corrente e' uguale a quello passato
    		if(head.item.equals(o)==true)
    		{
    			//Il contatore parte dall'indice
    			int counter=index;
    			/*
    			 * Di natura la coda segue la politica FIFO,
    			 * pertanto "dovrebbe eliminare" solo gli elementi in cima.
    			 * in questo caso, una volta trovato l'elemento con il suo
    			 * indice, lo scambio di posto verso la fine della coda 
    			 * per un numero di volte pari a size-index.
    			 * */
    		   	while(counter<=this.size)
    	    	{
    		   		//Il primo Elemento ha come elemento quello successivo.
    		   		this.first.item=this.first.next.item;
    		   		//Ora faccio puntare a first il nodo prossimo
    		   		this.first=this.first.next;
    		   		//Incremento contatore
    		   		counter++;
    	    	}
    		   	//Riassegno a first il suo oggetto primo in origine
    		   	this.first=head;
    		   	//Rimuovo dal basso
    		   	this.removeLast();
    		   	//Incremento numero modifiche
    		   	this.numeroModifiche++;
    		   	return true;
    		}
    		else
    		{
    			/*
    			 * Inquesto brakchet il mio nodo non e' uguale ad o.
    			 * Pertanto passo al nodo successivo.
    			 * */
    			head=head.next;
    			//Indice del nodo successivo
    			index++;
    		}
    	}
    	return false;
    }

    @Override
    public boolean contains(Object o) 
    {
    	//Controllo se oggetto passato e' nullo
    	if(o==null)
    	{
    		throw new NullPointerException("| ERROR | Oggetto null");
    	}
    	
    	//Creo un oggetto di tipo nodo partendo dal primo
        Node<E> elementoCorrente = this.first;
        
        //Ciclo affinche' non trovo un oggetto nullo
        while (elementoCorrente != null)
        {
        	/*
        	 * Controllo se l'oggetto di riferimento
        	 * e' uguale all'elemento corrente.
        	 * Se si ritorno true.
        	 * */
        	if (o.equals(elementoCorrente.item)==true) 
            {
                return true;
            } 
        	else
        	{
        		/*
        		 * Siccome non e' stata verificata la condizione,
        		 * passo all'elemento della lista successivo.
        		 * */
        		elementoCorrente = elementoCorrente.next;
        	}
                
        }
        //Ritorno false
        return false;
    }

    @Override
    public int size() 
    {
        return this.size;
    }

    /*
     * Class for representing the nodes of the double-linked list used to
     * implement this deque. The class and its members/methods are protected
     * instead of private only for JUnit testing purposes.
     */
    protected static class Node<E> {
        protected E item;

        protected Node<E> next;

        protected Node<E> prev;

        protected Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    /*
     * Class for implementing an iterator for this deque. The iterator is
     * fail-fast: it detects if during the iteration a modification to the
     * original deque was done and, if so, it launches a
     * <code>ConcurrentModificationException</code> as soon as a call to the
     * method <code>next()</code> is done.
     */
    private class Itr implements Iterator<E> {
    	//--- VARIABILI IMPLEMENTATE DA ME ---
    	//Numero di modifiche effettivamente effettuate
    	private int modificheAttese;
    	//Ultimo nodo
    	private Node<E> ultimo;
    	
    	//Costruttore
		Itr() 
        {
			//Assegno a modificheAttese il numero di modifiche effettuate
            this.modificheAttese=ASDL2223Deque.this.numeroModifiche;
            //Inizializzo a null
            this.ultimo=null;
        }
		
		//Ritorna true se vi e' effettivamente un oggetto sucessivo
        public boolean hasNext() 
        {
            if(this.ultimo==null)
            {
            	//Ovvero non vi e' neanche un oggetto
            	return ASDL2223Deque.this.first!=null;
            }
            else
            {
            	return ultimo.next!=null;
            }
        }

        public E next() 
        {
        	/*
        	 * Se le modifiche attese non sono uguali a quelle effettuate,
        	 * lancio un exception
        	 * */
        	if(this.modificheAttese!=ASDL2223Deque.this.numeroModifiche)
            {
        		throw new ConcurrentModificationException("| ERROR | Le modifiche attese non sono statte rispettate");
            }
        	
        	//Non vi sono elementi successivi
        	if(this.hasNext()==false)
            {
        		throw new NoSuchElementException("| ERROR | Non vi sono piu' elementi");
            }
        	
        	//Controllo se ultimo e' null
        	if(this.ultimo==null)
        	{
        		/*
        		 * Se lo e' faccio ripartire ultimo dal
        		 * primo valore della coda .
        		 * */
        		this.ultimo=ASDL2223Deque.this.first;
        	}
        	else
        	{
        		//Se non lo e' passo al successivo
        		this.ultimo=this.ultimo.next;
        	}
        	//Ritorno item successivo
        	return this.ultimo.item;
        }
    }

    @Override
    public Iterator<E> descendingIterator() {
        return new DescItr();
    }

    /*
     * Class for implementing a descendign iterator for this deque. The iterator
     * is fail-fast: it detects if during the iteration a modification to the
     * original deque was done and, if so, it launches a
     * <code>ConcurrentModificationException</code> as soon as a call to the
     * method <code>next()</code> is done.
     */
    private class DescItr implements Iterator<E> {
    	//--- VARIABILI IMPLEMENTATE DA ME ---
    	//Numero di modifiche effettivamente effettuate
    	private int modificheAttese;
    	//Ultimo nodo
    	private Node<E> ultimo;
    	
    	//Costruttore
        DescItr() 
        {

			//Assegno a modificheAttese il numero di modifiche effettuate
            this.modificheAttese=ASDL2223Deque.this.numeroModifiche;
            //Inizializzo a null
            this.ultimo=null;
        }

        public boolean hasNext() 
        {
        	/*
        	 * A differenza del primo hasNext(), qui' parto 
        	 * dalla fine e scorro gli elementi precedenti.
        	 * */
        	if(this.ultimo==null)
            {
        		//Ovvero non vi e' neanche un oggetto
        		return ASDL2223Deque.this.last != null;
            }
            else
            {
            	return this.ultimo.prev != null;
            }
        }

        public E next() 
        {
        	//Controllo se il numero delle modifiche attese e' stato rispettato
        	if(this.modificheAttese!=ASDL2223Deque.this.numeroModifiche)
            {
        		throw new ConcurrentModificationException("| ERROR | Le modifiche attese non sono statte rispettate");
            }
        	
        	//Controllo se vi sono successivi
        	if(this.hasNext()==false)
            {
        		throw new NoSuchElementException("| ERROR | Non vi sono piu' elementi");
            }
        	
        	//Controllo se ultimo e' null
        	if(this.ultimo==null)
        	{
        		/*
        		 * Se lo e' faccio ripartire ultimo dal
        		 * ultimo valore della coda.
        		 * */
        		this.ultimo=ASDL2223Deque.this.last;
        	}
        	else
        	{
        		//Restituisco il precedente
        		this.ultimo=this.ultimo.prev;
        	}
        	
        	return this.ultimo.item;
        }

    }

    /*
     * This method is only for JUnit testing purposes.
     */
    protected Node<E> getFirstNode() {
        return this.first;
    }

    /*
     * This method is only for JUnit testing purposes.
     */
    protected Node<E> getLastNode() {
        return this.last;
    }
}