/**
 * 
 */
package it.unicam.cs.asdl2223.mp3;

import java.util.Set;

/**
 * Classe astratta per un generico grafo i cui nodi sono etichettati con
 * elementi della classe {@code L}. Le classi {@code GraphNode<L>} e
 * {@code GraphEdge<L>} definiscono le operazioni generiche sui nodi e sugli
 * archi.
 * 
 * Il grafo può essere orientato o non orientato, la sottoclasse che estende
 * questa classe astratta specifica questo aspetto. Tale informazione è
 * disponibile tramite il metodo {@code isDirected()}.
 * 
 * Le etichette dei nodi sono obbligatorie ed uniche, cioè un nodo non può avere
 * etichetta nulla e due nodi con la stessa etichetta sono lo stesso nodo.
 * 
 * @author Luca Tesei
 * 
 * @param <L>
 *                tipo delle etichette dei nodi del grafo
 *
 */
public abstract class Graph<L> {

    /**
     * Restituisce il numero di nodi in questo grafo.
     * 
     * @return il numero di nodi in questo grafo.
     */
    public abstract int nodeCount();

    /**
     * Restituisce il numero di archi in questo grafo.
     * 
     * @return il numero di archi in questo grafo.
     */
    public abstract int edgeCount();

    /**
     * Restituisce la dimensione di questo grafo definita come il numero di nodi
     * più il numero di archi.
     * 
     * @return la dimensione di questo grafo definita come il numero dei nodi
     *         più il numero degli archi.
     */
    public int size() {
        return this.nodeCount() + this.edgeCount();
    }

    /**
     * Determina se questo grafo è vuoto, cioè senza nodi e senza archi.
     * 
     * @return se questo grafo è vuoto, false altrimenti.
     */
    public boolean isEmpty() {
        // se non ci sono nodi non ci possono essere neanche archi
        return this.nodeCount() == 0;
    }

    /**
     * Cancella tutti i nodi e gli archi di questo grafo portandolo ad essere un
     * grafo vuoto.
     */
    public abstract void clear();

    /**
     * Determina se questo grafo è orientato oppure no.
     * 
     * @return true se questo grafo è orientato, false altrimenti.
     */
    public abstract boolean isDirected();

    /**
     * Aggiunge un nodo a questo grafo.
     * 
     * @param node
     *                 il nuovo nodo da aggiungere
     * @return true se il nodo è stato aggiunto, false altrimenti cioè se il
     *         nodo è già presente
     * @throws NullPointerException
     *                                  se il nodo passato è null
     */
    public abstract boolean addNode(GraphNode<L> node);

    /**
     * Aggiunge un nodo a questo grafo etichettandolo con una etichetta data.
     * 
     * @param label
     *                  l'etichetta del nuovo nodo da aggiungere
     * @return true se il nodo è stato aggiunto, false altrimenti cioè se il
     *         nodo è già presente
     * @throws NullPointerException
     *                                  se l'etichetta passata è null
     */
    public abstract boolean addNode(L label);

    /**
     * Rimuove un nodo da questo grafo. Tutti gli archi collegati al nodo
     * vengono anch'essi eliminati.
     * 
     * Questa operazione è opzionale.
     * 
     * @param node
     *                 il nodo da rimuovere
     * @throws IllegalArgumentException
     *                                           se il nodo passato non esiste
     *                                           in questo grafo
     * @throws NullPointerException
     *                                           se il nodo passato è null
     * 
     * @throws UnsupportedOperationException
     *                                           se l'implementazione del grafo
     *                                           non supporta questa operazione
     */
    public abstract void removeNode(GraphNode<L> node);

    /**
     * Rimuove il nodo etichettato con una etichetta data da questo grafo. Tutti
     * gli archi collegati al nodo vengono anch'essi eliminati.
     * 
     * Questa operazione è opzionale.
     * 
     * @param label
     *                  l'etichetta del nodo da rimuovere
     * @throws IllegalArgumentException
     *                                           se l'etichetta data non esiste
     *                                           in nessun nodo di questo grafo
     * @throws NullPointerException
     *                                           se l'etichetta passata è null
     * 
     * @throws UnsupportedOperationException
     *                                           se l'implementazione del grafo
     *                                           non supporta questa operazione
     */
    public abstract void removeNode(L label);

    /**
     * Rimuove il nodo con un certo indice da questo grafo. Tutti gli archi
     * collegati al nodo vengono anch'essi eliminati.
     * 
     * Questa operazione è opzionale.
     * 
     * @param i
     *              l'indice del nodo da rimuovere
     * @throws IndexOutOfBoundsException
     *                                           se l'indice passato non
     *                                           corrisponde a nessun nodo o è
     *                                           fuori dai limiti
     *                                           dell'intervallo
     *                                           <code>[0, this.nodeCount() - 1]</code>
     * 
     * @throws UnsupportedOperationException
     *                                           se l'implementazione del grafo
     *                                           non supporta questa operazione
     */
    public abstract void removeNode(int i);

    /**
     * Restituisce il nodo di questo grafo che risulta uguale al nodo passato.
     * 
     * @param node
     *                 il nodo da restituire
     * 
     * @return il nodo di questo grafo che risulta uguale al nodo passato, null
     *         se tale nodo non esiste in questo grafo
     * 
     * @throws NullPointerException
     *                                  se il nodo passato è nullo
     * 
     */
    public abstract GraphNode<L> getNode(GraphNode<L> node);

    /**
     * Restituisce il nodo di questo grafo avente l'etichetta passata.
     * 
     * @param label
     *                  l'etichetta del nodo da restituire
     * 
     * @return il nodo di questo grafo che ha l'etichetta uguale a
     *         {@code label}, null se tale nodo non esiste in questo grafo
     * 
     * @throws NullPointerException
     *                                  se l'etichetta è nulla
     * 
     */
    public abstract GraphNode<L> getNode(L label);

    /**
     * Restituisce il nodo attualmente associato a un certo indice
     * nell'intervallo <code>[0, this.nodeCount() - 1]</code>. Questa
     * funzionalità è tipicamente disponibile se il grafo è rappresentato con
     * una matrice di adiacenza.
     * 
     * Questa operazione è opzionale.
     * 
     * @param i
     *              l'indice da ricercare
     * @return il nodo correntemente associato all'indice i
     * 
     * @throws IndexOutOfBoundsException
     *                                           se l'indice passato non
     *                                           corrisponde a nessun nodo o è
     *                                           fuori dai limiti
     *                                           dell'intervallo
     *                                           <code>[0, this.nodeCount() - 1]</code>
     * @throws UnsupportedOperationException
     *                                           se questa operazione non è
     *                                           supportata dall'implementazione
     *                                           di questo grafo
     */
    public abstract GraphNode<L> getNode(int i);

    /**
     * Restituisce l'indice unico attualmente associato in questo grafo a un
     * nodo dato. L'indice sarà nell'intervallo
     * <code>[0, this.nodeCount() - 1]</code>. Questa funzionalità è tipicamente
     * disponibile se il grafo è rappresentato con una matrice di adiacenza.
     * Tale indice può anche essere usato per identificare i nodi in strutture
     * dati esterne come array o liste che contengono informazioni aggiuntive
     * calcolate, ad esempio, da un algoritmo sul grafo.
     * 
     * Questa operazione è opzionale.
     * 
     * @param node
     *                 il nodo di cui restituire l'indice
     * 
     * @return un indice unico nell'intervallo
     *         <code>[0, this.nodeCount() - 1]</code> attualmente associato al
     *         nodo {@code node}. L'indice può cambiare se il grafo viene
     *         modificato togliendo dei nodi
     * 
     * @throws NullPointerException
     *                                           se il nodo passato è null
     * @throws IllegalArgumentException
     *                                           se il nodo passato non esiste
     *                                           in questo grafo
     * 
     * @throws UnsupportedOperationException
     *                                           se questa operazione non è
     *                                           supportata dall'implementazione
     *                                           di questo grafo
     */
    public abstract int getNodeIndexOf(GraphNode<L> node);

    /**
     * Restituisce l'indice unico attualmente associato in questo grafo al nodo
     * etichettato con l'etichetta data. L'indice sarà nell'intervallo
     * <code>[0, this.nodeCount() - 1]</code>. Questa funzionalità è tipicamente
     * disponibile se il grafo è rappresentato con una matrice di adiacenza.
     * Tale indice può anche essere usato per identificare i nodi in strutture
     * dati esterne come array o liste che contengono informazioni aggiuntive
     * calcolate, ad esempio, da un algoritmo sul grafo.
     * 
     * Questa operazione è opzionale.
     * 
     * @param label
     *                  l'etichetta del nodo di cui restituire l'indice
     * 
     * @return un indice unico nell'intervallo
     *         <code>[0, this.nodeCount() - 1]</code> attualmente associato al
     *         nodo con etichetta {@code label}. L'indice può cambiare se il
     *         grafo viene modificato togliendo dei nodi
     * 
     * @throws NullPointerException
     *                                           se l'etichetta passata è null
     * @throws IllegalArgumentException
     *                                           se un nodo con l'etichetta
     *                                           {@code label} non esiste in
     *                                           questo grafo
     * 
     * @throws UnsupportedOperationException
     *                                           se questa operazione non è
     *                                           supportata dall'implementazione
     *                                           di questo grafo
     */
    public abstract int getNodeIndexOf(L label);

    /**
     * Restituisce l'insieme dei nodi di questo grafo.
     * 
     * @return l'insieme dei nodi di questo grafo, possibilmente vuoto.
     */
    public abstract Set<GraphNode<L>> getNodes();

    /**
     * Aggiunge un arco a questo grafo.
     * 
     * @param edge
     *                 l'arco da inserire
     * @return true se l'arco è stato inserito, false se un arco esattamente
     *         uguale già esiste
     * @throws NullPointerException
     *                                      se l'arco passato è nullo
     * @throws IllegalArgumentException
     *                                      se almeno uno dei due nodi
     *                                      specificati nell'arco non esiste
     * @throws IllegalArgumentException
     *                                      se l'arco è orientato e questo grafo
     *                                      non è orientato o viceversa
     */
    public abstract boolean addEdge(GraphEdge<L> edge);

    /**
     * Aggiunge un arco a questo grafo tra due nodi dati. Se il grafo è
     * orientato l'arco sarà orientato, altrimenti sarà un arco non orientato.
     * L'arco viene inserito non pesato.
     * 
     * @param node1
     *                  nodo sorgente del nuovo arco in caso di grafo orientato,
     *                  altrimenti un nodo del nuovo arco
     * @param node2
     *                  nodo destinazione del nuovo arco in caso di grafo
     *                  orientato, altrimenti un nodo del nuovo arco
     * @return true se l'arco è stato inserito, false se un arco esattamente
     *         uguale già esiste
     * @throws NullPointerException
     *                                      se almeno uno dei due nodi passati è
     *                                      nullo
     * @throws IllegalArgumentException
     *                                      se almeno uno dei due nodi passati
     *                                      non esiste nel grafo
     * 
     */
    public abstract boolean addEdge(GraphNode<L> node1, GraphNode<L> node2);

    /**
     * Aggiunge un arco pesato a questo grafo tra due nodi dati. Se il grafo è
     * orientato l'arco sarà orientato, altrimenti sarà un arco non orientato.
     * 
     * @param node1
     *                   nodo sorgente del nuovo arco in caso di grafo
     *                   orientato, altrimenti un nodo del nuovo arco
     * @param node2
     *                   nodo destinazione del nuovo arco in caso di grafo
     *                   orientato, altrimenti un nodo del nuovo arco
     * @param weight
     *                   il peso da associare all'arco
     * @return true se l'arco è stato inserito, false se un arco esattamente
     *         uguale già esiste
     * @throws NullPointerException
     *                                      se almeno uno dei due nodi passati è
     *                                      nullo
     * @throws IllegalArgumentException
     *                                      se almeno uno dei due nodi passati
     *                                      non esiste nel grafo
     */
    public abstract boolean addWeightedEdge(GraphNode<L> node1,
            GraphNode<L> node2, double weight);

    /**
     * Aggiunge un arco a questo grafo tra due nodi etichettati con le etichette
     * date. Se il grafo è orientato l'arco sarà orientato, altrimenti sarà un
     * arco non orientato. L'arco viene inserito non pesato.
     * 
     * @param label1
     *                   l'etichetta del nodo sorgente del nuovo arco in caso di
     *                   grafo orientato, altrimenti l'etichetta di un nodo del
     *                   nuovo arco
     * @param label2
     *                   l'etichetta del nodo destinazione del nuovo arco in
     *                   caso di grafo orientato, altrimenti l'etichetta di un
     *                   nodo del nuovo arco
     * @return true se l'arco è stato inserito, false se un arco esattamente
     *         uguale già esiste
     * @throws NullPointerException
     *                                      se una delle etichette passate è
     *                                      nulla
     * @throws IllegalArgumentException
     *                                      se almeno una delle due etichette
     *                                      non esiste in nessun nodo del grafo
     */
    public abstract boolean addEdge(L label1, L label2);

    /**
     * Aggiunge un arco pesato a questo grafo tra due nodi etichettati con le
     * etichette date. Se il grafo è orientato l'arco sarà orientato, altrimenti
     * sarà un arco non orientato.
     * 
     * @param label1
     *                   l'etichetta del nodo sorgente del nuovo arco in caso di
     *                   grafo orientato, altrimenti l'etichetta di un nodo del
     *                   nuovo arco
     * @param label2
     *                   l'etichetta del nodo destinazione del nuovo arco in
     *                   caso di grafo orientato, altrimenti l'etichetta di un
     *                   nodo del nuovo arco
     * @param weight
     *                   il peso da associare all'arco
     * @return true se l'arco è stato inserito, false se un arco esattamente
     *         uguale già esiste
     * @throws NullPointerException
     *                                      se una delle etichette passate è
     *                                      nulla
     * @throws IllegalArgumentException
     *                                      se almeno una delle due etichette
     *                                      non esiste in nessun nodo del grafo
     */
    public abstract boolean addWeightedEdge(L label1, L label2, double weight);

    /**
     * Aggiunge un arco a questo grafo tra due nodi di indice dato. Se il grafo
     * è orientato l'arco sarà orientato, altrimenti sarà un arco non orientato.
     * L'arco viene inserito non pesato.
     * 
     * Questa operazione è opzionale.
     * 
     * @param i
     *              l'indice del nodo sorgente del nuovo arco in caso di grafo
     *              orientato, altrimento l'indice di uno dei nodi del nuovo
     *              arco
     * @param j
     *              l'indice del nodo destinazione del nuovo arco in caso di
     *              grafo orientato, altrimento l'indice di uno dei nodi del
     *              nuovo arco
     * @return true se l'arco è stato inserito, false se un arco esattamente
     *         uguale già esiste
     * @throws IndexOutOfBoundsException
     *                                           se almeno uno degli indici
     *                                           passati non corrisponde a
     *                                           nessun nodo o è fuori dai
     *                                           limiti dell'intervallo
     *                                           <code>[0, this.nodeCount() - 1]</code>
     * @throws UnsupportedOperationException
     *                                           se questa operazione non è
     *                                           supportata dall'implementazione
     *                                           di questo grafo
     */
    public abstract boolean addEdge(int i, int j);

    /**
     * Aggiunge un arco pesato a questo grafo tra due nodi di indice dato. Se il
     * grafo è orientato l'arco sarà orientato, altrimenti sarà un arco non
     * orientato.
     * 
     * Questa operazione è opzionale.
     * 
     * @param i
     *                   l'indice del nodo sorgente del nuovo arco in caso di
     *                   grafo orientato, altrimento l'indice di uno dei nodi
     *                   del nuovo arco
     * @param j
     *                   l'indice del nodo destinazione del nuovo arco in caso
     *                   di grafo orientato, altrimento l'indice di uno dei nodi
     *                   del nuovo arco
     * @param weight
     *                   il peso da associare all'arco
     * @return true se l'arco è stato inserito, false se un arco esattamente
     *         uguale già esiste
     * @throws IndexOutOfBoundsException
     *                                           se almeno uno degli indici
     *                                           passati non corrisponde a
     *                                           nessun nodo o è fuori dai
     *                                           limiti dell'intervallo
     *                                           <code>[0, this.nodeCount() - 1]</code>
     * @throws UnsupportedOperationException
     *                                           se questa operazione non è
     *                                           supportata dall'implementazione
     *                                           di questo grafo
     */
    public abstract boolean addWeightedEdge(int i, int j, double weight);

    /**
     * Rimuove un arco da questo grafo.
     * 
     * Questa operazione è opzionale.
     * 
     * @param edge
     *                 l'arco da rimuovere
     * 
     * @throws IllegalArgumentException
     *                                           se l'arco non esiste in questo
     *                                           grafo
     * @throws IllegalArgumentException
     *                                           se almeno uno dei due nodi
     *                                           specificati nell'arco non
     *                                           esiste
     * @throws NullPointerException
     *                                           se l'arco passato è nullo
     * @throws UnsupportedOperationException
     *                                           se l'implementazione del grafo
     *                                           non supporta questa operazione
     */
    public abstract void removeEdge(GraphEdge<L> edge);

    /**
     * Rimuove l'arco che connette due nodi dati da questo grafo. Se il grafo è
     * non orientato l'ordine dei due nodi passati come parametri non conta.
     * 
     * Questa operazione è opzionale.
     * 
     * @param node1
     *                  il nodo sorgente dell'arco da rimuovere nel caso di
     *                  grafo orientato, altrimenti uno dei due nodi dell'arco
     *                  da rimuovere
     * @param node2
     *                  il nodo destinazione dell'arco da rimuovere nel caso di
     *                  grafo orientato, altrimenti uno dei due nodi dell'arco
     *                  da rimuovere
     * @throws IllegalArgumentException
     *                                           se l'arco non esiste nel grafo
     * @throws IllegalArgumentException
     *                                           se almeno uno dei due nodi
     *                                           passati non esiste
     * @throws NullPointerException
     *                                           se almeno uno dei due nodi
     *                                           passati è nullo
     * @throws UnsupportedOperationException
     *                                           se l'implementazione del grafo
     *                                           non supporta questa operazione
     */
    public abstract void removeEdge(GraphNode<L> node1, GraphNode<L> node2);

    /**
     * Rimuove l'arco che connette i due nodi con etichette date da questo
     * grafo. Se il grafo è non orientato l'ordine delle due etichette passate
     * come parametri non conta.
     * 
     * Questa operazione è opzionale.
     * 
     * @param label1
     *                   l'etichetta del nodo sorgente dell'arco da rimuovere
     *                   nel caso di grafo orientato, altrimenti l'etichetta di
     *                   uno dei due nodi dell'arco da rimuovere
     * @param label2
     *                   l'etichetta del nodo destinazione dell'arco da
     *                   rimuovere nel caso di grafo orientato, altrimenti
     *                   l'etichetta di uno dei due nodi dell'arco da rimuovere
     * @throws IllegalArgumentException
     *                                           se l'arco non esiste nel grafo
     * @throws IllegalArgumentException
     *                                           se almeno una delle due
     *                                           etichette passate non esiste
     *                                           nel grafo
     * @throws NullPointerException
     *                                           se almeno una deelle due
     *                                           etichette passate è nulla
     * @throws UnsupportedOperationException
     *                                           se l'implementazione del grafo
     *                                           non supporta questa operazione
     */
    public abstract void removeEdge(L label1, L label2);

    /**
     * Rimuove l'arco che connette i due nodi con indici dati da questo grafo.
     * Se il grafo è non orientato l'ordine dei due indici passati come
     * parametri non conta.
     * 
     * Questa operazione è opzionale.
     * 
     * @param i
     *              l'indice del nodo sorgente dell'arco da rimuovere nel caso
     *              di grafo orientato, altrimenti l'etichetta di uno dei due
     *              nodi dell'arco da rimuovere
     * @param j
     *              l'indice del nodo destinazione dell'arco da rimuovere nel
     *              caso di grafo orientato, altrimenti l'etichetta di uno dei
     *              due nodi dell'arco da rimuovere
     * 
     * @throws IllegalArgumentException
     *                                           se l'arco non esiste nel grafo
     * @throws IndexOutOfBoundsException
     *                                           se almeno uno degli indici
     *                                           passati non corrisponde a
     *                                           nessun nodo o è fuori dai
     *                                           limiti dell'intervallo
     *                                           <code>[0, this.nodeCount() - 1]</code>
     * @throws UnsupportedOperationException
     *                                           se questa operazione non è
     *                                           supportata dall'implementazione
     *                                           di questo grafo
     */
    public abstract void removeEdge(int i, int j);

    /**
     * Restituisce, se esiste, un arco di questo grafo che è uguale all'arco
     * dato. Se il grafo è non orientato l'arco restituto potrebbe avere i nodi
     * in ordine diverso da quelli dell'arco dato.
     * 
     * @param edge
     *                 l'arco di cui ricercare l'arco uguale in questo grafo
     * @return un arco di questo grafo uguale all'arco dato oppure null se
     *         questo grafo non contiene nessun arco uguale a quello dato. Se il
     *         grafo non è orientato allora l'arco restituito può contenere i
     *         nodi dell'arco dato in qualsiasi ordine
     * @throws NullPointerException
     *                                      l'arco passato è null
     * @throws IllegalArgumentException
     *                                      se almeno uno dei due nodi dell'arco
     *                                      passato non esiste nel grafo
     */
    public abstract GraphEdge<L> getEdge(GraphEdge<L> edge);

    /**
     * Restituisce, se esiste, l'arco che connette due nodi dati in questo
     * grafo. In caso di grafo non orientato l'ordine dei due nodi è
     * ininfluente, in caso di grafo orientato viene ricercato l'arco che
     * connette il primo nodo specificato al secondo.
     * 
     * @param node1
     *                  il primo nodo
     * @param node2
     *                  il secondo nodo
     * @return un arco che connette node1 e node2 nel grafo oppure null se tale
     *         arco non esiste nel grafo. Se il grafo è orientato l'arco
     *         ricercato e restituito è quello che ha sorgente in node1 e
     *         destinazione in node2. Se il grafo non è orientato allora l'arco
     *         restituito può contenere node1 e node2 in qualsiasi ordine
     * @throws NullPointerException
     *                                      se almeno uno dei due nodi passati è
     *                                      null
     * @throws IllegalArgumentException
     *                                      se almeno uno dei due nodi passati
     *                                      non esiste nel grafo
     */
    public abstract GraphEdge<L> getEdge(GraphNode<L> node1,
            GraphNode<L> node2);

    /**
     * Restituisce, se esiste, l'arco che connette i due nodi che hanno le
     * etichette date in questo grafo. In caso di grafo non orientato l'ordine
     * delle due etichette è ininfluente, in caso di grafo orientato viene
     * ricercato l'arco che connette il primo nodo con etichetta specificata al
     * secondo.
     * 
     * @param label1
     *                   l'etichetta del primo nodo
     * @param label2
     *                   l'etichetta del secondo nodo
     * @return un arco che connette il nodo etichettato con label1 e il nodo
     *         etichettato con label2 nel grafo oppure null se tale arco non
     *         esiste nel grafo. Se il grafo è orientato l'arco ricercato e
     *         restituito è quello che ha sorgente nel nodo etichettato con
     *         label1 e destinazione nel nodo etichettato con label2. Se il
     *         grafo non è orientato allora l'arco restituito può contenere il
     *         nodo etichettato con label1 e il nodo etichettato con label2 in
     *         qualsiasi ordine
     * @throws NullPointerException
     *                                      se almeno una delle due etichette
     *                                      passate è null
     * @throws IllegalArgumentException
     *                                      se almeno una delle due etichette
     *                                      passate non è presente in nessun
     *                                      nodo di questo grafo
     */
    public abstract GraphEdge<L> getEdge(L label1, L label2);

    /**
     * Restituisce, se esiste, l'arco che connette i due nodi con indici dati in
     * questo grafo. In caso di grafo non orientato l'ordine dei due nodi è
     * ininfluente, in caso di grafo orientato viene ricercato l'arco che
     * connette il primo nodo specificato al secondo.
     * 
     * Questa operazione è opzionale.
     * 
     * @param i
     *              l'indice del primo nodo
     * @param j
     *              l'indice del secondo nodo
     * @return un arco che connette il nodo all'indice i e il nodo all'indice j
     *         nel grafo oppure null se tale arco non esiste nel grafo. Se il
     *         grafo è orientato l'arco ricercato e restituito è quello che ha
     *         sorgente nel nodo di indice i e destinazione nel nodo di indice
     *         j. Se il grafo non è orientato allora l'arco restituito può
     *         contenere il nodo di indice i e il nodo di indice j in qualsiasi
     *         ordine
     * @throws IndexOutOfBoundsException
     *                                           se almeno uno degli indici
     *                                           passati non corrisponde a
     *                                           nessun nodo o è fuori dai
     *                                           limiti dell'intervallo
     *                                           <code>[0, this.nodeCount() - 1]</code>
     * @throws UnsupportedOperationException
     *                                           se questa operazione non è
     *                                           supportata dall'implementazione
     *                                           di questo grafo
     */
    public abstract GraphEdge<L> getEdge(int i, int j);

    /**
     * Restituisce l'insieme di tutti i nodi adiacenti a un certo nodo. Se il
     * grafo è orientato, i nodi restituiti sono solo quelli collegati da un un
     * arco uscente dal nodo passato.
     * 
     * @param node
     *                 il nodo di cui cercare i nodi adiacenti
     * @return l'insieme di tutti i nodi adiacenti al nodo specificato
     * 
     * @throws IllegalArgumentException
     *                                      se il nodo passato non esiste
     * @throws NullPointerException
     *                                      se il nodo passato è nullo
     */
    public abstract Set<GraphNode<L>> getAdjacentNodesOf(GraphNode<L> node);

    /**
     * Restituisce l'insieme di tutti i nodi adiacenti al nodo con etichetta
     * data. Se il grafo è orientato, i nodi restituiti sono solo quelli
     * collegati da un un arco uscente dal nodo in questione.
     * 
     * @param label
     *                  l'etichetta del nodo di cui cercare i nodi adiacenti
     * @return l'insieme di tutti i nodi adiacenti al nodo con etichetta data
     * 
     * @throws IllegalArgumentException
     *                                      se l'etichetta passata non esiste in
     *                                      nessun nodo di questo grafo
     * @throws NullPointerException
     *                                      se l'etichetta passata è null
     */
    public abstract Set<GraphNode<L>> getAdjacentNodesOf(L label);

    /**
     * Restituisce l'insieme di tutti i nodi adiacenti al nodo con l'indice
     * dato. Se il grafo è orientato, i nodi restituiti sono solo quelli
     * collegati da un un arco uscente dal nodo in questione.
     * 
     * Questa operazione è opzionale.
     * 
     * @param i
     *              l'indice del nodo di cui cercare i nodi adiacenti
     * @return l'insieme di tutti i nodi adiacenti al nodo con indice dato
     * 
     * @throws IndexOutOfBoundsException
     *                                           se l'indice passato non
     *                                           corrisponde a nessun nodo o è
     *                                           fuori dai limiti
     *                                           dell'intervallo
     *                                           <code>[0, this.nodeCount() - 1]</code>
     * @throws UnsupportedOperationException
     *                                           se questa operazione non è
     *                                           supportata dall'implementazione
     *                                           di questo grafo
     */
    public abstract Set<GraphNode<L>> getAdjacentNodesOf(int i);

    /**
     * Restituisce l'insieme di tutti i nodi collegati tramite un arco entrante
     * in un certo nodo in un grafo orientato.
     * 
     * @param node
     *                 il nodo di cui cercare i nodi predecessori
     * @return l'insieme di tutti i nodi collegati tramite un arco entrante al
     *         nodo specificato
     * @throws UnsupportedOperationException
     *                                           se il grafo su cui il metodo è
     *                                           chiamato non è orientato
     * @throws IllegalArgumentException
     *                                           se il nodo passato non esiste
     * @throws NullPointerException
     *                                           se il nodo passato è nullo
     */
    public abstract Set<GraphNode<L>> getPredecessorNodesOf(GraphNode<L> node);

    /**
     * Restituisce l'insieme di tutti i nodi collegati tramite un arco entrante
     * nel nodo con etichetta data in un grafo orientato.
     * 
     * @param label
     *                  l'etichetta del nodo di cui cercare i nodi predecessori
     * @return l'insieme di tutti i nodi collegati tramite un arco entrante al
     *         nodo con etichetta data
     * @throws UnsupportedOperationException
     *                                           se il grafo su cui il metodo è
     *                                           chiamato non è orientato
     * @throws IllegalArgumentException
     *                                           se l'etichetta passata non
     *                                           esiste in nessun nodo del grafo
     * @throws NullPointerException
     *                                           se l'etichetta passata è nulla
     */
    public abstract Set<GraphNode<L>> getPredecessorNodesOf(L label);

    /**
     * Restituisce l'insieme di tutti i nodi collegati tramite un arco entrante
     * nel nodo con indice dato in un grafo orientato.
     * 
     * Questa operazione è opzionale.
     * 
     * @param i
     *              l'indice del nodo di cui cercare i nodi predecessori
     * @return l'insieme di tutti i nodi collegati tramite un arco entrante al
     *         nodo con indice dato
     * @throws UnsupportedOperationException
     *                                           se il grafo su cui il metodo è
     *                                           chiamato non è orientato
     * @throws IndexOutOfBoundsException
     *                                           se l'indice passato non
     *                                           corrisponde a nessun nodo o è
     *                                           fuori dai limiti
     *                                           dell'intervallo
     *                                           <code>[0, this.nodeCount() - 1]</code>
     * @throws UnsupportedOperationException
     *                                           se questa operazione non è
     *                                           supportata dall'implementazione
     *                                           di questo grafo
     */
    public abstract Set<GraphNode<L>> getPredecessorNodesOf(int i);

    /**
     * Restituisce l'insieme di tutti gli archi connessi a un certo nodo in
     * questo grafo. Nel caso di grafo orientato vengono restituiti solo gli
     * archi uscenti.
     * 
     * @param node
     *                 il nodo di cui sono richiesti gli archi connessi
     * 
     * @return un insieme contenente tutti gli archi connessi al nodo dato in
     *         questo grafo (solo gli archi uscenti in caso di grafo orientato)
     * 
     * @throws IllegalArgumentException
     *                                      se il nodo passato non esiste
     * @throws NullPointerException
     *                                      se il nodo passato è nullo
     */
    public abstract Set<GraphEdge<L>> getEdgesOf(GraphNode<L> node);

    /**
     * Restituisce l'insieme di tutti gli archi connessi al nodo con etichetta
     * data in questo grafo. Nel caso di grafo orientato vengono restituiti solo
     * gli archi uscenti.
     * 
     * @param label
     *                  l'etichetta del nodo di cui sono richiesti gli archi
     *                  connessi
     * 
     * @return un insieme contenente tutti gli archi connessi al nodo con
     *         etichetta data in questo grafo (solo gli archi uscenti in caso di
     *         grafo orientato)
     * 
     * @throws IllegalArgumentException
     *                                      se il nodo con l'etichetta passata
     *                                      non esiste
     * @throws NullPointerException
     *                                      se l'etichetta passata è nulla
     */
    public abstract Set<GraphEdge<L>> getEdgesOf(L label);

    /**
     * Restituisce l'insieme di tutti gli archi connessi al nodo con indice dato
     * in questo grafo. Nel caso di grafo orientato vengono restituiti solo gli
     * archi uscenti.
     * 
     * Questa operazione è opzionale.
     * 
     * @param label
     *                  l'indice del nodo di cui sono richiesti gli archi
     *                  connessi
     * 
     * @return un insieme contenente tutti gli archi connessi al nodo con indice
     *         dato in questo grafo (solo gli archi uscenti in caso di grafo
     *         orientato)
     * 
     * @throws IndexOutOfBoundsException
     *                                           se l'indice passato non
     *                                           corrisponde a nessun nodo o è
     *                                           fuori dai limiti
     *                                           dell'intervallo
     *                                           <code>[0, this.nodeCount() - 1]</code>
     * @throws UnsupportedOperationException
     *                                           se questa operazione non è
     *                                           supportata dall'implementazione
     *                                           di questo grafo
     */
    public abstract Set<GraphEdge<L>> getEdgesOf(int i);

    /**
     * Restituisce l'insieme di tutti gli archi entranti in un certo nodo in un
     * grafo orientato.
     * 
     * @param node
     *                 il nodo di cui determinare tutti gli archi entranti
     * 
     * @return un insieme contenente tutti gli archi entranti nel nodo passato
     *         in questo grafo orientato.
     * 
     * @throws UnsupportedOperationException
     *                                           se il grafo su cui il metodo è
     *                                           chiamato non è orientato
     * @throws IllegalArgumentException
     *                                           se il nodo passato non esiste
     * @throws NullPointerException
     *                                           se il nodo passato è nullo
     */
    public abstract Set<GraphEdge<L>> getIngoingEdgesOf(GraphNode<L> node);

    /**
     * Restituisce l'insieme di tutti gli archi entranti nel nodo con etichetta
     * data in un grafo orientato.
     * 
     * Questa operazione è opzionale.
     * 
     * @param label
     *                  l'etichetta del nodo di cui determinare tutti gli archi
     *                  entranti
     * 
     * @return un insieme contenente tutti gli archi entranti nel nodo con
     *         etichetta data in questo grafo orientato.
     * 
     * @throws UnsupportedOperationException
     *                                           se il grafo su cui il metodo è
     *                                           chiamato non è orientato
     * @throws IllegalArgumentException
     *                                           se l'etichetta passata non
     *                                           esiste in nessun nodo del grafo
     * @throws NullPointerException
     *                                           se l'etichetta passata è nulla
     */
    public abstract Set<GraphEdge<L>> getIngoingEdgesOf(L label);

    /**
     * Restituisce l'insieme di tutti gli archi entranti nel nodo con indice
     * dato in un grafo orientato.
     * 
     * Questa operazione è opzionale.
     * 
     * @param i
     *              l'indice del nodo di cui determinare tutti gli archi
     *              entranti
     * 
     * @return un insieme contenente tutti gli archi entranti nel nodo con
     *         indice dato in questo grafo orientato.
     * 
     * @throws UnsupportedOperationException
     *                                           se il grafo su cui il metodo è
     *                                           chiamato non è orientato
     * @throws IndexOutOfBoundsException
     *                                           se l'indice passato non
     *                                           corrisponde a nessun nodo o è
     *                                           fuori dai limiti
     *                                           dell'intervallo
     *                                           <code>[0, this.nodeCount() - 1]</code>
     * @throws UnsupportedOperationException
     *                                           se questa operazione non è
     *                                           supportata dall'implementazione
     *                                           di questo grafo
     */
    public abstract Set<GraphEdge<L>> getIngoingEdgesOf(int i);

    /**
     * Restituisce l'insieme di tutti gli archi in questo grafo.
     * 
     * @return un insieme, possibilmente vuoto, contenente tutti gli archi di
     *         questo grafo
     */
    public abstract Set<GraphEdge<L>> getEdges();

    /**
     * Restituisce il grado di un nodo, cioè il numero di archi connessi al
     * nodo. Nel caso di grafo orientato è la somma del numero di archi in
     * entrata e del numero di archi in uscita.
     * 
     * @param node
     *                 il nodo di cui calcolare il grado
     * @return il grado del nodo passato
     * 
     * @throws IllegalArgumentException
     *                                      se il nodo passato non esiste
     * @throws NullPointerException
     *                                      se il nodo passato è nullo
     */
    public int getDegreeOf(GraphNode<L> node) {
        if (node == null)
            throw new NullPointerException(
                    "Tentativo di calcolare il grado di un nodo null");
        if (this.getNode(node) == null)
            throw new IllegalArgumentException(
                    "Tentativo di calcolare il grado di un nodo non "
                            + "presente in questo grafo");
        if (!this.isDirected())
            return this.getEdgesOf(node).size();
        else
            return this.getEdgesOf(node).size()
                    + this.getIngoingEdgesOf(node).size();
    }

    /**
     * Restituisce il grado del nodo con l'etichetta data, cioè il numero di
     * archi connessi al nodo. Nel caso di grafo orientato è la somma del numero
     * di archi in entrata e del numero di archi in uscita.
     * 
     * @param label
     *                  l'etichetta del nodo di cui calcolare il grado
     * @return il grado del nodo con l'etichetta data
     * 
     * @throws IllegalArgumentException
     *                                      se l'etichetta passata non esiste in
     *                                      nessun nodo del grafo
     * @throws NullPointerException
     *                                      se l'etichetta passata è nulla
     */
    public int getDegreeOf(L label) {
        if (label == null)
            throw new NullPointerException(
                    "Tentativo di calcolare il grado di un nodo null");
        if (this.getNode(label) == null)
            throw new IllegalArgumentException(
                    "Tentativo di calcolare il grado di un nodo non "
                            + "presente in questo grafo");
        if (!this.isDirected())
            return this.getEdgesOf(label).size();
        else
            return this.getEdgesOf(label).size()
                    + this.getIngoingEdgesOf(label).size();
    }

    /**
     * Restituisce il grado del nodo con l'indice dato, cioè il numero di archi
     * connessi al nodo. Nel caso di grafo orientato è la somma del numero di
     * archi in entrata e del numero di archi in uscita.
     * 
     * Questa operazione è opzionale.
     * 
     * @param i
     *              l'indice del nodo di cui calcolare il grado
     * @return il grado del nodo con l'indice dato
     * 
     * @throws IndexOutOfBoundsException
     *                                           se l'indice passato non
     *                                           corrisponde a nessun nodo o è
     *                                           fuori dai limiti
     *                                           dell'intervallo
     *                                           <code>[0, this.nodeCount() - 1]</code>
     * @throws UnsupportedOperationException
     *                                           se questa operazione non è
     *                                           supportata dall'implementazione
     *                                           di questo grafo
     */
    public int getDegreeOf(int i) {
        GraphNode<L> n = null;
        try {
            n = this.getNode(i);
        } catch (IndexOutOfBoundsException e) {
            // cambio il messaggio dell'eccezione per renderlo adatto a questo
            // metodo
            throw new IndexOutOfBoundsException(
                    "Tentativo di calcolare il grado di un nodo con indice "
                            + "non valido");
        }
        // L'eccezione UnsupportedOperationException non viene catturata perché
        // il messaggio di quella che viene lanciata è corretto rispetto a
        // questo metodo
        if (!this.isDirected())
            return this.getEdgesOf(n).size();
        else
            return this.getEdgesOf(n).size() + this.getIngoingEdgesOf(n).size();
    }

}
