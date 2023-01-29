package it.unicam.cs.asdl2223.mp2;

/**
 * An object of this class is an actor that uses an ASDL2223Deque<Character> as
 * a Stack in order to check that a sequence containing the following
 * characters: '(', ')', '[', ']', '{', '}' in any order is a string of balanced
 * parentheses or not. The input is given as a String in which white spaces,
 * tabs and newlines are ignored.
 * 
 * Some examples:
 * 
 * - " (( [( {\t (\t) [ ] } ) \n ] ) ) " is a string o balanced parentheses - " " is a
 * string of balanced parentheses - "(([)])" is NOT a string of balanced
 * parentheses - "( { } " is NOT a string of balanced parentheses - "}(([]))" is
 * NOT a string of balanced parentheses - "( ( \n [(P)] \t ))" is NOT a string
 * of balanced parentheses
 * 
 * @author Template: Luca Tesei, Implementation: Vasil Ferecka - vasil.ferecka@studenti.unicam.it
 *
 */
public class BalancedParenthesesChecker {

    // The stack is to be used to check the balanced parentheses
    private ASDL2223Deque<Character> stack;

    /**
     * Create a new checker.
     */
    public BalancedParenthesesChecker() 
    {
        this.stack = new ASDL2223Deque<Character>();
    }

    /**
     * Check if a given string contains a balanced parentheses sequence of
     * characters '(', ')', '[', ']', '{', '}' by ignoring white spaces ' ',
     * tabs '\t' and newlines '\n'.
     * 
     * @param s
     *              the string to check
     * @return true if s contains a balanced parentheses sequence, false
     *         otherwise
     * @throws IllegalArgumentException
     *                                      if s contains at least a character
     *                                      different form:'(', ')', '[', ']',
     *                                      '{', '}', white space ' ', tab '\t'
     *                                      and newline '\n'
     */
    public boolean check(String s) 
    {	
    	//Ciclo per ogni elemento della stringa
    	for(int i=0; i<s.length(); i++)
    	{
    		//Carattere attuale
    		switch(s.charAt(i))
    		{
    			//--- QUANDO INCONTRO UNA PARENTESI APERTA ---
    			/*
    			 * Lo aggingo (utilizzando la funzione push() di Deque)
    			 * senza ulteriori controlli allo 
    			 * stack allocato precedentemente. Vale per
    			 * qualsiasi tipo di parentesi aperta.
    			 * */
    		
	    		//--- QUANDO INCONTRO UNA PARENTESI CHIUSA ---
				/*
				 * Prendo (senza rimuovere effettivamente) la prima parentesi
				 * corrispondente aperta con il metodo peekFirst() di Deque.
				 * Se essa e' dello stesso tipo della parentesi chiusa del case
				 * in cui sono entrato (Ovvero, se entro in un case con ']' 
				 * mi devo aspettare una parentesi aperta nello stack corrispondente a '['), 
				 * la rimuovo dallo stack adoperando pop() di Deque. 
				 * Se invece e' di un tipo differente lo lascio
				 * caricato in memoria.
				 * */
    		
	    		//--- QUANDO INCONTRO ALTRI CARATTERI VALIDI CHE NON SONO PARENTESI ---
				/*
				 * Come dalle task assegnate accetto /n, /t e il " ".
				 * Quando li inontro la stringa e' ancora valida e non faccio nessun 
				 * tipo di operazione sullo stack.
				 * */
    		
	    		//--- DEFAULT CASE ---
				/*
				 * Se arrivo al default vuol dire che il valore attuale non e' una
				 * parentesi e non e' nessun altro tipo di carattere accettabile.
				 * quindi la stringa e' invalida e lancio l'eccezione.
				 * */
    			case '(':
    				this.stack.push(s.charAt(i));
    				break;
    				
    			case ')':
    				if(this.stack.peekFirst()=='(')
    				{
    					this.stack.pop();
    				}
    				break;
    			
    			case '[':
    				this.stack.push(s.charAt(i));
    				break;
    				
    			case ']':
    				if(this.stack.peekFirst()=='[')
    				{
    					this.stack.pop();
    				}
    				break;
    			
    			case '{':
    				this.stack.push(s.charAt(i)); 
    				break;
    				
    			case '}':
    				if(this.stack.peekFirst()=='{')
    				{
    					this.stack.pop();
    				}
    				break;
    			case ' ':
    			case '\n':
    			case '\t':
    				break;
        		default:
        			throw new IllegalArgumentException("| ERROR | Nella Stringa vi sono caratteri illegali");
    		}
    	}
    	
    	/*
    	 * A questo punto ho aggiunto tutte le pareteresi aperte della stringa
    	 * ed eliminato tutte le parentesi valide. Pertanto se nello stack vi e' 
    	 * rimasta aanche una parentesi vuol dire che la stringa non contiene
    	 * parentesi bilanciate. Se invece lo stack e' vuoto, la stringa e' bilanciata.
    	 * */
    	return this.stack.isEmpty();   
    }
}