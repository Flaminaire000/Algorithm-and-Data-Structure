package it.unicam.cs.asdl2223.mp3;

import java.util.List;

/**
 * Interface to represent an objective function for the choice of the best
 * candidate among a list of integer values.
 * 
 * @author Daniele Marchei and Luca Tesei
 *
 */
public interface ObjectiveFunction {

    /**
     * Returns the best (optimal) choice among a list of candidate values,
     * according to the logic of this objective function.
     * 
     * @param candidates
     *                       a list of candidate values
     * @return the optimal value among the list of candidates, null if the list
     *         of candidates is empty
     * @throws NullPointerException
     *                                  if the list is null
     * 
     */
    Integer getBest(List<Integer> candidates);

    /**
     * Returns the index of a best (optimal) choice in a list of candidate
     * values, according to the logic of this objective function.
     * 
     * @param candidates
     *                       a list of candidates
     * @return the index of an optimal value among the list of candidates, null
     *         if the list of candidates is empty
     * @throws NullPointerException
     *                                  if the list is null
     * 
     */
    int getBestIndex(List<Integer> candidates);

}
