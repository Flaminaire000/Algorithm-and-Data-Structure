package it.unicam.cs.asdl2223.mp3;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Class that solves an instance of the the El Mamun's Caravan problem using
 * dynamic programming.
 *
 * Template: Daniele Marchei and Luca Tesei, Implementation: Vasil Ferecka vasil.ferecka@studenti.unicam.it
 *
 */
public class ElMamunCaravanSolver {

    // the expression to analyse
    private final Expression expression;

    // table to collect the optimal solution for each sub-problem,
    // protected just for Junit Testing purposes
    protected Integer[][] table;

    // table to record the chosen optimal solution among the optimal solution of
    // the sub-problems, protected just for JUnit Testing purposes
    protected Integer[][] tracebackTable;

    // flag indicating that the problem has been solved at least once
    private boolean solved;

    /**
     * Create a solver for a specific expression.
     *
     * @param expression
     *                       The expression to work on
     * @throws NullPointerException
     *                                  if the expression is null
     */
    public ElMamunCaravanSolver(Expression expression) 
    {
    	//Controllo espressione
        if (expression==null)
        {
        	throw new NullPointerException("| ERROR | Espressione null");
        }
        this.expression=expression;
        this.solved=false;
        //Alloco in memoria le due matrici
        this.table=new Integer[expression.size()][expression.size()];
        this.tracebackTable=new Integer[expression.size()][expression.size()];
        //Richiamo metodi che inizializzano le matrici a 0
        this.inizializeMatrix(table);
        this.inizializeMatrix(tracebackTable);
    }

    /**
     * Returns the expression that this solver analyse.
     *
     * @return the expression of this solver
     */
    public Expression getExpression() {
        return this.expression;
    }
    
    /**
     * Solve the problem on the expression of this solver by using a given
     * objective function.
     *
     * @param function
     *                     The objective function to be used when deciding which
     *                     candidate to choose
     * @throws NullPointerException
     *                                  if the objective function is null
     */
    public void solve(ObjectiveFunction function) 
    {
    	//Controllo function
        if (function==null) 
        {
        throw new NullPointerException("| ERROR | Function null");
        }
       
        /*
         * Variabili di comodo che servono per tenere conto dei valori che andranno salvati
         * rispettivamente nel Table e e nel Traceback. 
         * */
        List<Integer> bufferTable=new ArrayList<Integer>();
	    List<Integer> bufferTraceBack=new ArrayList<Integer>();
	   
	    //Richiamo il metodo che immette nella diagonale principale gli operandi della espressione
	    this.diagonalize();
	    
	    /*
	     * I primi due cicli for sono utili per scannerizzare la matrice.
	     * Utilizzo una "window" di scorrimento di grandezza i+j per essere
	     * sicuro di centrare i valori.
	     * */
	    for (int i=2; i<this.expression.size(); i+=2)
	    {
		    for (int j=0; j<this.expression.size()-i; j += 2)
		    {
		    	//Mi salvo il margine
			    int margine=j+i;
			    for (int k=margine-j-2; k>=0; k-=2)
			    {
			    	//Controllo se effettivamente l'elemento non vi e' presente
				    if (this.table[j+k+2][margine].equals(0)==false)
				    {
				        //Richiamo la funzione best
						int result=best(this.table[j][j+k], j+k+1, this.table[j+k+2][margine]);
						//Controllo se il valore e' stato gia' salvato nel buffer di appoggio
						if (bufferTable.contains(result)==false) 
						{
							//Aggiunta effettiva ai due buffer
							bufferTable.add(result);
							bufferTraceBack.add(k);
						}
						else 
						{
							//Mi salvo l'indice del risultato ottenuto
							int elementIndex=bufferTable.indexOf(result);
							if (bufferTraceBack.get(elementIndex)>k) 
							{
								//Aggiorno il valore rimuovendone il nuovo nell'indice specificato
							    bufferTraceBack.remove(elementIndex);
							    bufferTraceBack.add(elementIndex, k);
							}
					    }
				    }
			    }
			   
			    //Controllo se il buffer e' carico
			    if (j<margine && bufferTable.isEmpty()==false) 
			    {
				    //Carico l'elemento table
				    this.table[j][margine]=function.getBest(bufferTable);
				    //Carico la traceBackTable
				    this.tracebackTable[j][margine]=bufferTraceBack.get(function.getBestIndex(bufferTable));
				    //Refresh dei buffer
				    bufferTable.clear();
				    bufferTraceBack.clear();
			    }
		    }
	    }
	    //A questo del codice ho risolto il mamun
	    this.solved=true;
    }

    /**
     * Returns the current optimal value for the expression of this solver. The
     * value corresponds to the one obtained after the last solving (which used
     * a particular objective function).
     *
     * @return the current optimal value
     * @throws IllegalStateException
     *                                   if the problem has never been solved
     */
    public int getOptimalSolution()
    {
    	//Controllo se il problema e' stato risolto
    	if(this.isSolved()==false)
    	{
    		throw new IllegalStateException("| ERROR | Problema irrisolto");
    	}
    	//Restituisco valore ottimale
    	return table[0][expression.size()-1];
    }

 
   
    /**
     * Returns an optimal parenthesization corresponding to an optimal solution
     * of the expression of this solver. The parenthesization corresponds to the
     * optimal value obtained after the last solving (which used a particular
     * objective function).
     *
     * If the expression is just a digit then the parenthesization is the
     * expression itself. If the expression is not just a digit then the
     * parethesization is of the form "(<parenthesization>)". Examples: "1",
     * "(1+2)", "(1*(2+(3*4)))"
     *
     * @return the current optimal parenthesization for the expression of this
     *         solver
     * @throws IllegalStateException
     *                                   if the problem has never been solved
     */
    public String getOptimalParenthesization() 
    {
    	//Controllo se il problema e' stato risolto
        if (isSolved()==false) 
        {
        	throw new IllegalStateException("| ERROR | Problema irrisolto");
        }
        
        return parentesizza(0, expression.size()-1);
    }

    /**
     * Determines if the problem has been solved at least once.
     *
     * @return true if the problem has been solved at least once, false
     *         otherwise.
     */
    public boolean isSolved() {
        return this.solved;
    }

    @Override
    public String toString() {
        return "ElMamunCaravanSolver for " + expression;
    }
    
    //--- METODI DEFINITI DA ME ---
    //Inizializzo le matrici portandole tutte a 0
    private void inizializeMatrix(Integer m[][])
    {
        for(int i=0; i<m.length; i++)
        {
        	for(int j=0; j<m.length; j++)
        	{
        		m[i][j]=0;
        	}
        }
    }
    
    /*
     * Metodo che mi permette di inserire dall'espressione 
     * i vari operandi nella diagonale principale.
     * */
    private void diagonalize()
    {
	    for(int i=0; i<this.expression.size(); i+=2)
	    {
		    this.table[i][i]=(Integer)this.expression.get(i).getValue();
		    this.tracebackTable[i][i]=0;
	    }
    }
    
    /*
     * Il metodo compute mi permette di eseguire effettivamente le operazioni
     * di moltiplicazione e addizione tra operandi ed operatori passati.
     * In base al segno dell'operatore scelgo se fare '+' o '*'
     * */
    private int best(int primoOperando, int operatore, int secondoOperando) 
    {
    	//Siccome l'espressione e' di tipo Integer devo conertire in char
    	char segno=expression.get(operatore).getValue().toString().toCharArray()[0];
    	//Controllo se devo fare l'addizione
    	if(segno=='+')
    	{
    		return primoOperando+secondoOperando;
    	}
    	else
    	{
    		//Se entro in questo brachet vuol dire che devo eseguire una moltiplicazione
    		return primoOperando*secondoOperando;
    	}
    }
    
    /*
     * Metodo utile alla tracebackTable che mi permette di aggiungere nel modo corretto
     * tutte le parentesi.
     * */
    private String parentesizza(int inizio, int margine) 
    {
	    //Controllo se i==j da documentazione google fornitaci
	    if (inizio==margine) 
	    {
		    return expression.get(inizio).getValue().toString();
	    }
	    
	    return "("+parentesizza(inizio, inizio+tracebackTable[inizio][margine])
	    		+expression.get(inizio + tracebackTable[inizio][margine]+1).getValue()
	    		+parentesizza(inizio + tracebackTable[inizio][margine]+2, margine)+")";
    }
}