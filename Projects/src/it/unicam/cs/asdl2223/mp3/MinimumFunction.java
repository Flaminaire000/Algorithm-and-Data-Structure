package it.unicam.cs.asdl2223.mp3;

import java.util.List;

/**
 * 
 * Class that implements an objective function that choses the minimum integer
 * value among the given candidates.
 * 
 * @author Daniele Marchei and Luca Tesei
 *
 */
public class MinimumFunction implements ObjectiveFunction {

	@Override
	public Integer getBest(List<Integer> candidates) {
		int min = Integer.MAX_VALUE;
		for(Integer candidate : candidates) {
			if (min > candidate) {
				min = candidate;
			}
		}
		
		return min;
		
	}
	
	@Override
    public int getBestIndex (List<Integer> candidates) {
        int min = Integer.MAX_VALUE;
        int k = -1;
        for(int i = 0; i < candidates.size(); i++) {
            if (min > candidates.get(i)) {
                min = candidates.get(i);
                k = i;
            }
        }
        
        return k;
        
    }

}
