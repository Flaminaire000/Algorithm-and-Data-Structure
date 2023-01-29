/**
 * 
 */
package it.unicam.cs.asdl2223.mp3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


// ATTENZIONE: è vietato includere import a pacchetti che non siano della Java SE

/**
 * Classe che implementa un grafo non orientato tramite matrice di adiacenza.
 * Non sono accettate etichette dei nodi null e non sono accettate etichette
 * duplicate nei nodi (che in quel caso sono lo stesso nodo).
 * 
 * I nodi sono indicizzati da 0 a nodeCoount() - 1 seguendo l'ordine del loro
 * inserimento (0 è l'indice del primo nodo inserito, 1 del secondo e così via)
 * e quindi in ogni istante la matrice di adiacenza ha dimensione nodeCount() *
 * nodeCount(). La matrice, sempre quadrata, deve quindi aumentare di dimensione
 * ad ogni inserimento di un nodo. Per questo non è rappresentata tramite array
 * ma tramite ArrayList.
 * 
 * Gli oggetti GraphNode<L>, cioè i nodi, sono memorizzati in una mappa che
 * associa ad ogni nodo l'indice assegnato in fase di inserimento. Il dominio
 * della mappa rappresenta quindi l'insieme dei nodi.
 * 
 * Gli archi sono memorizzati nella matrice di adiacenza. A differenza della
 * rappresentazione standard con matrice di adiacenza, la posizione i,j della
 * matrice non contiene un flag di presenza, ma è null se i nodi i e j non sono
 * collegati da un arco e contiene un oggetto della classe GraphEdge<L> se lo
 * sono. Tale oggetto rappresenta l'arco. Un oggetto uguale (secondo equals) e
 * con lo stesso peso (se gli archi sono pesati) deve essere presente nella
 * posizione j, i della matrice.
 * 
 * Questa classe non supporta i metodi di cancellazione di nodi e archi, ma
 * supporta tutti i metodi che usano indici, utilizzando l'indice assegnato a
 * ogni nodo in fase di inserimento.
 * 
 * @author Luca Tesei (template) Vasil Ferecka vasil.ferecka@studenti.unicam.it (implementazione)
 *
 * 
 */
public class AdjacencyMatrixUndirectedGraph<L> extends Graph<L> {
    /*
     * Le seguenti variabili istanza sono protected al solo scopo di agevolare
     * il JUnit testing
     */

    /*
     * Insieme dei nodi e associazione di ogni nodo con il proprio indice nella
     * matrice di adiacenza
     */
    protected Map<GraphNode<L>, Integer> nodesIndex;

    /*
     * Matrice di adiacenza, gli elementi sono null o oggetti della classe
     * GraphEdge<L>. L'uso di ArrayList permette alla matrice di aumentare di
     * dimensione gradualmente ad ogni inserimento di un nuovo nodo e di
     * ridimensionarsi se un nodo viene cancellato.
     */
    protected ArrayList<ArrayList<GraphEdge<L>>> matrix;

    /**
     * Crea un grafo vuoto.
     */
    public AdjacencyMatrixUndirectedGraph() {
        this.matrix = new ArrayList<ArrayList<GraphEdge<L>>>();
        this.nodesIndex = new HashMap<GraphNode<L>, Integer>();
    }

    @Override
    public int nodeCount() 
    {
    	return this.nodesIndex.keySet().size();
    }

    @Override
    public int edgeCount() 
    {
    	return this.getEdges().size(); 
    }

    @Override
    public void clear() 
    {
    	//Resetto il grafo richiamando le funzioni predefinite "clear()"
    	this.matrix.clear();
        this.nodesIndex.clear();
    }

    @Override
    public boolean isDirected() 
    {
    	return false;
    }

    //------------------------------------ READ ME --------------------------------------
    /*
     * Da questo punto in poi devo gestire l'aggiuna di archi/nodi e la loro rimozione
     * in base a se passo nodi, label, etc... Il punto e' che la vera aggiunta effettiva
     * (cosi' come la loro rimozione) e' sempre la stessa.
     * Pertanto implemento completamente i primi metodi per ogni aggiunta e/o rimozione 
     * avente come parametro formale i vari Node ed Edge; poi per i restanti metodi 
     * (che hanno come paramtri formali label e gli indici) li riconduco a questi
     * richiamandolo dentro ogni metodo dopo i opportuni controlli.
     * Facendo cosi' elimino i vari copy & paste inutili e snellisco il codice.
     * L'unica minuzia non ottimizzabile e' che ogni volta che richiamo il primo
     * addEdge, esegue un controllo sull'edge passato inutilmente. 
     * Ma ai fini del codice e' ininfluente. 
     * */
    //------------------------------------------------------------------------------------
    /*
     * Gli indici dei nodi vanno assegnati nell'ordine di inserimento a partire
     * da zero
     */
    @Override
    public boolean addNode(GraphNode<L> node) 
    {
    	//Controllo se il nodo e' null
    	if(node==null)
    	{
    		throw new NullPointerException("| ERROR | Nodo null");
    	}
    	
    	/*
    	 * Controllo se il nodo e' gia' presente evitando 
    	 * cosi' le ripetizioni
    	 * */
    	if(this.nodesIndex.containsKey(node)==true)
    	{
    		return false;
    	}
    	
    	//Creo un nuovo elemento da inserire con il nodo passatomi
    	ArrayList<GraphEdge<L>> element=new ArrayList<GraphEdge<L>>();
    	//Aggiungo effettivamente l'elemento alla matrice
    	this.matrix.add(element);
		/*
		 * Rendo la matrice sempre quadrata ad ogni iterazione 
		 * aggiungendo "null" come elemento riempitivo.
		 * */
		for(int i=0; i<=this.nodesIndex.keySet().size(); i++)
		{
			this.matrix.get(this.nodesIndex.keySet().size()).add(null); 
		}
		
		for(int i=0; i<this.nodesIndex.keySet().size(); i++)
		{
			matrix.get(i).add(null);
		}
		
    	//Inserisco il nodo alla mia mappa
    	this.nodesIndex.put(node, this.nodesIndex.size());
    	//A questo punto del codice l'operazione di inserimento e' andata a buon fine
    	return true;
    }

    /*
     * Gli indici dei nodi vanno assegnati nell'ordine di inserimento a partire
     * da zero.
     */
    @Override
    public boolean addNode(L label) 
    {
    	//Controllo se l'etichetta e' nulla
    	if(label==null)
    	{
    		throw new NullPointerException("| ERROR | Label null");
    	}
    	/*
    	 * Controllo se l'etichetta e' gia' presente evitando 
    	 * cosi' le ripetizioni.
    	 * */
    	if(this.getNode(label)!=null)
    	{
    		return false;
    	}
    	//Creo un nuovo nodo con l'etichetta passatami
    	GraphNode<L> mioNodo=new GraphNode<L>(label);
    	/*
    	 * Utilizzando la stessa logica di inserimento e creando un nodo
    	 * con la nuova label richiamo l'addNode() precedente.
    	 * */
    	return this.addNode(mioNodo);
    }


    /*
     * Gli indici dei nodi il cui valore sia maggiore dell'indice del nodo da
     * cancellare devono essere decrementati di uno dopo la cancellazione del
     * nodo
     */
    @Override
    public void removeNode(GraphNode<L> node) 
    {
    	//Controllo se il nodo e' null
    	if(node==null)
    	{
    		throw new NullPointerException("| ERROR | Nodo null");
    	}
    	
    	/*
    	 * Controllo se il nodo e' gia' presente evitando 
    	 * cosi' le ripetizioni
    	 * */
    	if(this.nodesIndex.containsKey(node)==false)
    	{
    		throw new IllegalArgumentException("| ERROR | Il nodo non e' presente");
    	}
    	
    	/*
    	 * Siccome e' molto probabile che elimini un elemento nel mezzo,
    	 * devo gestire gli indici dei nodi successivi.
    	 * Pertanto essi "shifteranno" tutti di una posizione in avanti, 
    	 * e l'indice verra' decrementato
    	 * */
    	for(GraphNode<L> element : this.nodesIndex.keySet()) 
    	{
    		if(this.nodesIndex.get(element)>this.nodesIndex.get(node))
    		{
    			this.nodesIndex.replace(element, this.nodesIndex.get(element)-1);
    		}
    	}
    	//Variabile di comodo che mi salva in memoria l'indice del nodo da rimuovere
    	int nodeIndex=this.nodesIndex.get(node);
    	//Rimozione lista con lo stesso indice
    	this.matrix.remove(nodeIndex);
    	for(int i=0; i<this.nodeCount()-1; i++)
    	{
    		//Rimozione effettiva dalla matrice del nodo designato
    		this.matrix.get(i).remove(nodeIndex);
    	}
    	//Rimozione dalla mappa
    	this.nodesIndex.remove(node);
    }

    /*
     * Gli indici dei nodi il cui valore sia maggiore dell'indice del nodo da
     * cancellare devono essere decrementati di uno dopo la cancellazione del
     * nodo
     */
    @Override
    public void removeNode(L label) 
    {
    	//Controllo se l'etichetta e' nulla
    	if(label==null)
    	{
    		throw new NullPointerException("| ERROR | Label null");
    	}
    	
    	GraphNode<L> mioNodo=this.getNode(label);
    	
    	//Controllo se il nodo con quella label esiste
    	if(mioNodo==null)
    	{
    		throw new IllegalArgumentException("| ERROR | Nodo inesistente");
    	}
    	this.removeNode(mioNodo);
    }

    /*
     * Gli indici dei nodi il cui valore sia maggiore dell'indice del nodo da
     * cancellare devono essere decrementati di uno dopo la cancellazione del
     * nodo
     */
    @Override
    public void removeNode(int i) 
    {
    	if(i<0 || i>=this.nodeCount())
    	{
    		throw new IndexOutOfBoundsException("| ERROR | i non rientra nell'intervallo di indice");
    	}
    	
    	this.removeNode(this.getNode(i));
    }

    @Override
    public GraphNode<L> getNode(GraphNode<L> node) 
    {
    	//Controllo se il nodo e' null
    	if(node==null)
    	{
    		throw new NullPointerException("| ERROR | Nodo null");
    	}
    	
    	//Ricerco il nodo da ritornare iterando con un for each
        for(GraphNode<L> element : this.nodesIndex.keySet()) 
        {
    		if(element.equals(node)==true)
    		{
    			//Ritorno il nodo corrente
    			return element;
    		}
    	}
        //A questo punto il nodo da cercare non esiste
        return null;
    }

    @Override
    public GraphNode<L> getNode(L label) 
    {
    	//Controllo se il nodo e' null
    	if(label==null)
    	{
    		throw new NullPointerException("| ERROR | Label null");
    	}
    	
    	/*
    	 * Ricerco il nodo confrontando la label,
    	 * non costruisco un nodo con la label per poi pasasrlo
    	 * al getNode() precedente perche' controllo direttamente
    	 * l'uguaglianza tra label.
    	 * */
        for(GraphNode<L> element : this.nodesIndex.keySet()) 
        {
    		if(element.getLabel().equals(label)==true)
    		{
    			//Ritorno il nodo corrente
    			return element;
    		}
    	}
        //A questo punto la label da cercare non esiste
        return null;
    }

    @Override
    public GraphNode<L> getNode(int i) 
    {
    	//Controllo se l'indice passato rientra nell'intervallo
    	if(i<0 || i>=this.nodeCount())
    	{
    		throw new IndexOutOfBoundsException("| ERROR | i non rientra nell'intervallo di indice");
    	}
    	//Ricerco il nodo confrontando la label
    	for(GraphNode<L> nodeElement : this.nodesIndex.keySet()) 
    	{
    	    if(this.nodesIndex.get(nodeElement)==i)
    	    {
    	    	//Ritorno il nodo corrente
    	    	return nodeElement;
    	    }
    	}
    	//A questo punto l'indice da cercare non esiste
    	return null;
    }

    @Override
    public int getNodeIndexOf(GraphNode<L> node) 
    {
    	//Controllo nodo
    	if(node==null)
    	{
    		throw new NullPointerException("| ERROR | Nodo null");
    	}
    	
    	//Controllo se il nodo esiste
    	if(this.nodesIndex.containsKey(node)==false)
    	{
    		throw new IllegalArgumentException("| ERROR | Nodo inesistente");
    	}
    	
    	//A questo punto il nodo esiste, quindi restituisco l'indice
        return this.nodesIndex.get(node);
    }

    @Override
    public int getNodeIndexOf(L label) 
    {
    	//Controllo label 
    	if(label==null)
    	{
    		throw new NullPointerException("| ERROR | Label null");
    	}
    	
    	//Controllo se il nodo con quella label esiste
    	if(this.getNode(label)==null)
    	{
    		throw new IllegalArgumentException("| ERROR | Nodo con label designata inesistente");
    	}
    	
    	/*
    	 * A differenza del precedente richiamo il getNode()
    	 * avente come parametro formale label.
    	 * Poi estrapolo l'indice.
    	 * */
        return this.getNodeIndexOf(this.getNode(label));
    }

    @Override
    public Set<GraphNode<L>> getNodes() 
    {
    	return this.nodesIndex.keySet();
    }

    @Override
    public boolean addEdge(GraphEdge<L> edge) 
    {
    	//Controllo arco
    	if(edge==null)
    	{
    		throw new NullPointerException("| ERROR | Arco null");
    	}
    	
    	//Controllo se nodi sono direzionati e/o esistenti
    	if(this.nodesIndex.containsKey(edge.getNode1())==false || this.nodesIndex.containsKey(edge.getNode2())==false || edge.isDirected()==true)
    	{
    		throw new IllegalArgumentException("| ERROR | Nodi inesitenti e/o direzionati");
    	}
    	
    	//Mi salvo la posizione dei due nodi
        int primaPosizioneNodo=this.nodesIndex.get(edge.getNode1());
        int secondaPosizioneNodo=this.nodesIndex.get(edge.getNode2());
        
        //Controllo se l'arco esiste
        if(this.getEdge(edge)!=null)
        {
        	return false;
        }
        	
        /*
         * A questo punto del codice aggiungo l'arco nelle posizioni
         * [primo,secondo] ed [secondo,primo]
         * */ 
        this.matrix.get(primaPosizioneNodo).set(secondaPosizioneNodo, edge);
        this.matrix.get(secondaPosizioneNodo).set(primaPosizioneNodo, edge);
        //Operazione andata a buon fine
        return true;
    }

    @Override
    public boolean addEdge(GraphNode<L> node1, GraphNode<L> node2) 
    {
    	//Controllo nodi 
    	if(node1==null || node2==null)
    	{
    		throw new NullPointerException("| ERROR | Nodi null");
    	}
    	
    	/*
    	 * Mi creo ed alloco un nuovo arco con i nodi passati
    	 * per poter richiamare l'addEdge() precedente,
    	 * evitando così di fare cpy & paste.
    	 * */
    	GraphEdge<L> nuovoArco=new GraphEdge<L>(node1,node2,false);
    	return this.addEdge(nuovoArco);
    }

    /*
     * Cio' che ho premesso all'inizio vale pure per gli "add pesati".
     * Posso richiamare tranquillamente addEdge() su di un addWeightedEdge()
     * siccome esistono due costruttori diversi per gli archi e,
     * l'effettiva aggiunta rimane invariata.
     * */
    @Override
    public boolean addWeightedEdge(GraphNode<L> node1, GraphNode<L> node2, double weight) 
    {
    	//Controllo nodi
    	if(node1==null || node2==null)
    	{
    		throw new NullPointerException("| ERROR | Nodi null");
    	}
    	
    	//Controllo peso
    	if(weight<0)
    	{
    		throw new IllegalArgumentException("| ERROR | Peso negativo");
    	}
    	
    	//Creazione oggetto necessaria per la chiamata del metodo
    	GraphEdge<L> nuovoArco=new GraphEdge<L>(node1,node2,false,weight);
    	return this.addEdge(nuovoArco);
    }

    @Override
    public boolean addEdge(L label1, L label2) 
    {
    	//Controllo label
    	if(label1==null || label2==null)
    	{
    		throw new NullPointerException("| ERROR | Label null");
    	}
    	
    	/*
    	 * Non necessito di richiamare l'addEdge() con l'arco,
    	 * ma richiamo l'addEdge() avente come paramtri formali 2 nodi
    	 * */
        return this.addEdge(this.getNode(label1), this.getNode(label2));
    }

    @Override
    public boolean addWeightedEdge(L label1, L label2, double weight) 
    {
    	//Controllo label
    	if(label1==null || label2==null)
    	{
    		throw new NullPointerException("| ERROR | Label null");
    	}
    	
    	/*
    	 * Non necessito di richiamare l'addEdge() con l'arco,
    	 * ma richiamo l'addEdge() avente come paramtri formali 2 nodi
    	 * */
        return this.addWeightedEdge(this.getNode(label1), this.getNode(label2), weight);
    }

    @Override
    public boolean addEdge(int i, int j) 
    {
    	//Controllo indice se e' nell'intervallo
    	if(i<0 || j<0 || i>=this.nodeCount() || j>=this.nodeCount())
    	{
    		throw new IndexOutOfBoundsException("| ERROR | Parametri fuori intervallo");
    	}
    	
    	/*
    	 * Non necessito di richiamare l'addEdge() con l'arco,
    	 * ma richiamo l'addEdge() avente come paramtri formali 2 nodi
    	 * */
    	return this.addEdge(this.getNode(i),this.getNode(j));
    }

    @Override
    public boolean addWeightedEdge(int i, int j, double weight) 
    {
    	//Controllo indice se e' nell'intervallo
    	if(i<0 || j<0 || i>=this.nodeCount() || j>=this.nodeCount())
    	{
    		throw new IndexOutOfBoundsException("| ERROR | Parametri fuori intervallo");
    	}
    	
    	/*
    	 * Non necessito di richiamare l'addEdge() con l'arco,
    	 * ma richiamo l'addEdge() avente come paramtri formali 2 nodi
    	 * */
    	return this.addWeightedEdge(this.getNode(i),this.getNode(j),weight);
    }

    @Override
    public void removeEdge(GraphEdge<L> edge)
    {
    	//Controllo arco
    	 if(edge==null)
    	 {
    		 throw new NullPointerException("| ERROR | Arco null");
    	 }
    	
    	 //Controllo esistenza arco
         if(this.getEdge(edge)==null)
         {
        	 throw new IllegalArgumentException("| ERROR | Arco inesistente");
         }
        	
         //Controllo validita' dei nodi
         if(this.nodesIndex.containsKey(edge.getNode1())==false || this.nodesIndex.containsKey(edge.getNode2())==false)
         {
        	 throw new IllegalArgumentException("| ERROR | Nodi inesistenti");
         }
        	 
         this.matrix.get(this.getNodeIndexOf(edge.getNode1())).set(this.getNodeIndexOf(edge.getNode2()), null);
         this.matrix.get(this.getNodeIndexOf(edge.getNode2())).set(this.getNodeIndexOf(edge.getNode1()), null);
    }

    @Override
    public void removeEdge(GraphNode<L> node1, GraphNode<L> node2) 
    {
    	//Controllo nodi
    	if(node1==null || node2==null)
    	{
    		throw new NullPointerException("| ERROR | Nodi null");
    	}
    	
    	GraphEdge<L> arcoDesignato=new GraphEdge<L>(node1,node2,false);
    	this.removeEdge(arcoDesignato);
    }

    @Override
    public void removeEdge(L label1, L label2) 
    {
    	//Controllo label
    	if(label1==null || label2==null)
    	{
    		throw new NullPointerException();
    	}
    	
        this.removeEdge(this.getNode(label1), this.getNode(label2));
    }

    @Override
    public void removeEdge(int i, int j)
    {
    	//Controllo se gli indici rientrano nell'intervallo
    	if(i<0 || j<0 || i>=this.nodeCount() || j >=this.nodeCount())
    	{
    		throw new IndexOutOfBoundsException();
    	}
    	
    	this.removeEdge(this.getNode(i), this.getNode(j));
    }

    @Override
    public GraphEdge<L> getEdge(GraphEdge<L> edge) 
    {
    	//Controllo arco
    	if(edge==null)
    	{
    		throw new NullPointerException("| ERROR | Arco null");
    	}
    	//Controllo esistenza nodi 
    	if(this.nodesIndex.containsKey(edge.getNode1())==false || this.nodesIndex.containsKey(edge.getNode2())==false)
    	{
    		throw new IllegalArgumentException("| ERROR | Nodi inesistenti");
    	}
    		
    	/*
    	 * Adopero un doppio ciclo for che scannerizza riga per riga
    	 * la matrice e controllo se l'arco effettivamente esiste.
    	 * Se si lo restituisco
    	 * */
    	 for(int i=0; i<this.nodeCount(); i++)
         {
         	for(int j=0; j<this.nodeCount(); j++) 
         	{
         		if(this.matrix.get(i).get(j)!=null) 
         		{
         			if(this.matrix.get(i).get(j).equals(edge))
         			{
         				return this.matrix.get(i).get(j);
         			}
         		}
         	}
         }
    	 //A questo punto del codice l'arco non esiste
        return null;
    }

    /*
     * Da questo punto in poi non ho piu' bisogno di adoperare una matrice
     * siccome basta vedere se esiste un arco con i vari nodi e label dati.
     * Quindi restituisco senza ciclo.
     * */
    @Override
    public GraphEdge<L> getEdge(GraphNode<L> node1, GraphNode<L> node2) 
    {
    	//Controllo nodi
    	if(node1==null || node2==null)
    	{
    		throw new NullPointerException("| ERROR | Nodi null");
    	}
    	//Controllo esistenza nodi
    	if(this.nodesIndex.containsKey(node1)==false || this.nodesIndex.containsKey(node2)==false)
    	{
    		throw new IllegalArgumentException("| ERROR | Nodi inesistenti");
    	}
    		
    	//Restituisco arco
        return this.matrix.get(this.getNodeIndexOf(node1)).get(this.getNodeIndexOf(node2));
    }

    @Override
    public GraphEdge<L> getEdge(L label1, L label2) 
    {
    	//Controllo label
    	if(label1==null || label2==null)
    	{
    		throw new NullPointerException("| ERROR | Label null");
    	}
    	//Richiamo getEdge() con parametri formali i nodi
        return this.getEdge(this.getNode(label1), this.getNode(label2));
    }

    @Override
    public GraphEdge<L> getEdge(int i, int j) 
    {
    	//Controllo se gli indici rientrano nell'intervallo
    	if(i<0 || j<0 || i>=this.nodeCount() || j>=this.nodeCount())
    	{
    		throw new IndexOutOfBoundsException("| ERROR | indice fuori intervallo");
    	}
    	
    	//Restituisco arco
        return this.matrix.get(i).get(j);
    }

    @Override
    public Set<GraphNode<L>> getAdjacentNodesOf(GraphNode<L> node) 
    {
    	//Controllo nodo
    	if(node==null)
    	{
    		throw new NullPointerException("| ERROR | Nodo null");
    	}
    	
    	//Controllo se esistente 
        if(this.nodesIndex.containsKey(node)==false)
        {
        	throw new IllegalArgumentException("| ERROR | Nodo inesistente");
        }
        
        //Creazione ed allocazione nodo
        Set<GraphNode<L>> mioNodo=new HashSet<GraphNode<L>>();
        
        //Mi salvo l'indice del nodo
        int nodeIndex=this.getNodeIndexOf(node);
        /*
         * Scorro la matrice e, per ogni elemento in esso, lo aggiungo su mioNodo
         * poi faccio un controllo per designare il nodo da inserire effettivamente
         * */
        for(int i=0; i<this.nodeCount(); i++) 
        {
        	if(this.matrix.get(nodeIndex).get(i)!=null) 
        	{
        		if(this.matrix.get(nodeIndex).get(i).getNode1().equals(node))
        		{
        			mioNodo.add(this.matrix.get(nodeIndex).get(i).getNode2());
        		}
        		else
        		{
        			mioNodo.add(this.matrix.get(nodeIndex).get(i).getNode1());
        		}
        	}
        }
        //restituisco la lista
        return mioNodo;
    }

    @Override
    public Set<GraphNode<L>> getAdjacentNodesOf(L label) 
    {
    	//Controllo label
        if(label==null)
        {
        	throw new NullPointerException("| ERROR | Label null");
        }
        
        //Controllo se il nodo con la label passata esiste
        if(this.getNode(label)==null)
        {
        	throw new IllegalArgumentException("| ERROR | Nodo con label passata inesistente");
        }
        
        //Richiamo il metodo precedente come detto nel read me
        return this.getAdjacentNodesOf(this.getNode(label));
    }

    @Override
    public Set<GraphNode<L>> getAdjacentNodesOf(int i) 
    {
    	//Controllo se l'indice rientra nell'intervallo
    	if(i<0 || i>=this.nodeCount())
    	{
    		throw new IndexOutOfBoundsException("| ERROR | Indice fuori intervallo");
    	}
    	
        //Richiamo il metodo precedente come detto nel read me  	
    	return this.getAdjacentNodesOf(this.getNode(i));
    }

    @Override
    public Set<GraphNode<L>> getPredecessorNodesOf(GraphNode<L> node) {
        throw new UnsupportedOperationException(
                "Operazione non supportata in un grafo non orientato");
    }

    @Override
    public Set<GraphNode<L>> getPredecessorNodesOf(L label) {
        throw new UnsupportedOperationException(
                "Operazione non supportata in un grafo non orientato");
    }

    @Override
    public Set<GraphNode<L>> getPredecessorNodesOf(int i) {
        throw new UnsupportedOperationException(
                "Operazione non supportata in un grafo non orientato");
    }

    @Override
    public Set<GraphEdge<L>> getEdgesOf(GraphNode<L> node) 
    {
    	//Controllo nodo
    	if(node==null)
    	{
    		throw new NullPointerException("| ERROR | Nodo null");
    	}
    	
    	//Controllo se nodo esiste
    	if(this.nodesIndex.containsKey(node)==false)
    	{
    		throw new IllegalArgumentException("| ERROR | Nodo inesistente");
    	}
    	
    	//Creazione ed allocamento dell'arco
    	Set<GraphEdge<L>> mioArco=new HashSet<GraphEdge<L>>();
    	//Itero per aggiungere a mioArco tutti gli archi esistenti
    	for (int i=0; i<this.nodeCount(); i++) 
    	{
			if(this.matrix.get(this.getNodeIndexOf(node)).get(i)!= null)
			{
				mioArco.add(this.matrix.get(this.getNodeIndexOf(node)).get(i));
			}
		}
    	//Ritorno mioArco
    	return mioArco;
    }

    @Override
    public Set<GraphEdge<L>> getEdgesOf(L label) 
    {
    	//Controllo label
    	if(label==null)
    	{
    		throw new NullPointerException("| ERROR | Label null");
    	}
    	
    	//Controllo se il nodo con la label passa esiste
        if(this.nodesIndex.containsKey(this.getNode(label))==false)
        {
        	throw new IllegalArgumentException();
        }
        
        //Richiamo il metodo precedente come detto nel read me
        return this.getEdgesOf(this.getNode(label));
    }

    @Override
    public Set<GraphEdge<L>> getEdgesOf(int i) 
    {
        //Richiamo il metodo precedente come detto nel read me
    	return this.getEdgesOf(this.getNode(i));
    }

    @Override
    public Set<GraphEdge<L>> getIngoingEdgesOf(GraphNode<L> node) {
        throw new UnsupportedOperationException(
                "Operazione non supportata in un grafo non orientato");
    }

    @Override
    public Set<GraphEdge<L>> getIngoingEdgesOf(L label) {
        throw new UnsupportedOperationException(
                "Operazione non supportata in un grafo non orientato");
    }

    @Override
    public Set<GraphEdge<L>> getIngoingEdgesOf(int i) {
        throw new UnsupportedOperationException(
                "Operazione non supportata in un grafo non orientato");
    }

    @Override
    public Set<GraphEdge<L>> getEdges() 
    {
    	Set<GraphEdge<L>> mioArco = new HashSet<GraphEdge<L>>();   
    	//Scorro la matrice riga per riga, se l'arco esiste la ggiungo a mioArco
    	for (int i = 0; i <this.nodeCount(); i++)
    	{
    		for (GraphEdge<L> element : matrix.get(i)) 
    		{
    			if (element!=null) 
    			{
    				mioArco.add(element);
    	        }
    	    }
    	}
    	return mioArco;
    }
}