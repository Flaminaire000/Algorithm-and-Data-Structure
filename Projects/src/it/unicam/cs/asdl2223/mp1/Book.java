/**
 * 
 */
package it.unicam.cs.asdl2223.mp1;

/**
 * Un oggetto di questa classe rappresenta un libro. Oltre le informazioni
 * tipiche ci sono la lunghezza, la larghezza e il peso. Queste servono per
 * implementare i metodi dell'interfaccia ShelfItem. Un libro è identificato dal
 * suo codice ISBN.
 * 
 * @author Luca Tesei (template) Vasil Ferecka vasil.ferecka@studenti.unicam.it (implementazione)
 *
 */
public class Book implements ShelfItem {
    private final String author;

    private final String title;

    private final int year;

    private final String publisher;

    private final String iSBN;

    private final double length;

    private final double width;

    private final double weight;

    /**
     * @param author
     *                      stringa contenente l'autore
     * @param title
     *                      stringa contente il titolo
     * @param year
     *                      anno di pubblicazione
     * @param publisher
     *                      stringa con il nome dell'editore
     * @param iSBN
     *                      codice univoco del libro
     * @param length
     *                      lunghezza in cm
     * @param width
     *                      larghezza in cm
     * @param weight
     *                      peso in grammi
     */
    public Book(String author, String title, int year, String publisher,String iSBN, double length, double width, double weight) 
    {
        this.author = author;
        this.title = title;
        this.year = year;
        this.publisher = publisher;
        this.iSBN = iSBN;
        this.length = length;
        this.width = width;
        this.weight = weight;
    }

    @Override
    public double getLength() 
    {
        return this.length;
    }

    @Override
    public double getWidth() 
    {
        return this.width;
    }

    @Override
    public double getWeight() 
    {
        return this.weight;
    }

    /**
     * @return the author
     */
    public String getAuthor() 
    {
        return author;
    }

    /**
     * @return the title
     */
    public String getTitle() 
    {
        return title;
    }

    /**
     * @return the year
     */
    public int getYear() 
    {
        return year;
    }

    /**
     * @return the publisher
     */
    public String getPublisher() 
    {
        return publisher;
    }

    /**
     * @return the iSBN
     */
    public String getiSBN() 
    {
        return iSBN;
    }

    /*
     * Due libri sono uguali se e solo se hanno lo stesso codice ISBN.
     */
    @Override
    public boolean equals(Object obj) 
    {
    	//Oggetto null
        if(obj==null)
        {
        	return false;
        }
        
        //Controllo se l'oggetto passato e' un istanza della classe Book
        if((obj instanceof Book)==false)
        {
        	return false;
        }
        
       //Creo un oggetto Book di tipologia final e gli assegno l'oggetto passato (con il casting)
        final Book other=(Book) obj;
        //Chiamo l'equals() per comparare due elementi String
        if(this.iSBN.equals(other.getiSBN()))
        {
        	return true;
        }
        //Se arrivo a questo punto l'ISBN tra i due libri sicuramente non e' uguale
        return false;
    }

    /*
     * Hashcode definito in base all'ISBN, per compatibilità con equals.
     */
    @Override
    public int hashCode() 
    {
    	//Designo un numero primo
        final int numeroPrimo=11;
        int hash=1;
        //Moltiplico il numero primo per l'hash per poi sommarlo all'hascode() di ISBN
        hash=numeroPrimo*hash+this.iSBN.hashCode();
        //Ritorno l'hash completo
        return hash;
    }
}