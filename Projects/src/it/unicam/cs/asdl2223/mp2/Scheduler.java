package it.unicam.cs.asdl2223.mp2;

/**
 * Una semplice classe che fa lo scheduling di jobs utilizzando una coda di
 * min-priorità realizzata dalla classe TernaryHeapMinPriorityQueue. La priorità
 * dei job è la loro scadenza. La scadenza di un job può essere anticipata
 * dinamicamente, che corrisponde a dire che la sua priorità nella coda può
 * essere decrementata.
 * 
 * @author Luca Tesei
 *
 */
public class Scheduler {

    private TernaryHeapMinPriorityQueue queue;

    /**
     * Costruisce un nuovo scheduler
     */
    public Scheduler() {
        this.queue = new TernaryHeapMinPriorityQueue();
    }

    /**
     * Inserisce un nuovo job nello scheduler. Il job deve avere una deadline
     * già fissata.
     * 
     * @param j
     *              il nuovo job da inserire
     */
    public void schedule(Job j) {
        // la deadline verrà presa come priority
        this.queue.insert(j);
    }

    /**
     * Estrae il prossimo job da eseguire, cioè quello con scadenza più
     * ravvicinata.
     * 
     * @return il prossimo job da eseguire
     */
    public Job getNextJobToExecute() {
        return (Job) this.queue.extractMinimum();
    }

    /**
     * Anticipa un certo job di un certo tempo specificato.
     * 
     * @param j
     *                   il job da anticipare
     * @param amount
     *                   la quantità di tempo da anticipare
     * @throws IllegalArgumentException
     *                                      se amount è <= 0
     */
    public void anticipateJob(Job j, double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException(
                    "Anticipo di un job di un tempo non positivo");
        this.queue.decreasePriority(j, j.getPriority() - amount);
    }

}
