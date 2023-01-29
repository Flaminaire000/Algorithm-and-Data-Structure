package it.unicam.cs.asdl2223.mp2;

/**
 * Semplice classe i cui oggetti rappresentano dei Job da eseguire su un
 * processore entro una certa scadenza, rappresentata da un double. Un double
 * più piccolo rappresenta una scadenza più ravvicinata.
 * 
 * Un Job può essere inserito in una coda con priorità.
 * 
 * @author Luca Tesei
 *
 */
public class Job implements PriorityQueueElement {

    private String name;

    private double deadline;

    private int handle;
    
    // other fields may be present, this is just a demonstration

    /**
     * Crea un nuovo job con la sua deadline.
     * 
     * @param name nome del job
     * @param deadline scadenza
     */
    public Job(String name, double deadline) {
        this.name = name;
        this.deadline = deadline;
        // valore iniziale della handle non significativo
        this.handle = -1;
    }

    /**
     * @return the deadline
     */
    public double getDeadline() {
        return deadline;
    }

    /**
     * @param deadline
     *                     the deadline to set
     */
    public void setDeadline(double deadline) {
        this.deadline = deadline;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    @Override
    public double getPriority() {
        return deadline;
    }

    @Override
    public void setPriority(double newPriority) {
        this.deadline = newPriority;

    }

    @Override
    public int getHandle() {
        return this.handle;
    }

    @Override
    public void setHandle(int newHandle) {
        this.handle = newHandle;
    }

}
