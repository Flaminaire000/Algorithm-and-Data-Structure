package it.unicam.cs.asdl2223.mp3;

import java.util.List;

/**
 * 
 * Class that implements an objective function that choses the maximum integer
 * value among the given candidates.
 * 
 * @author Daniele Marchei and Luca Tesei
 *
 */
public class MaximumFunction implements ObjectiveFunction {

    @Override
    public Integer getBest(List<Integer> candidates) {
        int max = Integer.MIN_VALUE;
        for (Integer candidate : candidates) {
            if (max < candidate) {
                max = candidate;
            }
        }
        return max;
    }

    @Override
    public int getBestIndex(List<Integer> candidates) {
        int max = Integer.MIN_VALUE;
        int k = -1;
        for (int i = 0; i < candidates.size(); i++) {
            if (max < candidates.get(i)) {
                max = candidates.get(i);
                k = i;
            }
        }

        return k;

    }

}
