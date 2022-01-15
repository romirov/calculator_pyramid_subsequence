package com.tsystems.javaschool.tasks.pyramid;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PyramidBuilder {

    /**
     * Builds a pyramid with sorted values (with minumum value at the top line and maximum at the bottom,
     * from left to right). All vacant positions in the array are zeros.
     *
     * @param inputNumbers to be used in the pyramid
     * @return 2d array with pyramid inside
     * @throws {@link CannotBuildPyramidException} if the pyramid cannot be build with given input
     */
    public static int[][] buildPyramid(List<Integer> inputNumbers) throws CannotBuildPyramidException {
        // TODO : Implement your solution here
    	if(inputNumbers.contains(null) 
    			|| Collections.frequency(inputNumbers, Integer.valueOf(0)) == inputNumbers.size()) {
    		throw new CannotBuildPyramidException();
    	}
    	
    	Collections.sort(inputNumbers);
    	System.out.println(inputNumbers.toString());
    	int maxNumberOfItemInRow = getMaxNumberOfItemInRow(inputNumbers);
    	
    	if(maxNumberOfItemInRow < 0) 
    		throw new CannotBuildPyramidException();
    	int xSize = maxNumberOfItemInRow;
    	int ySize = maxNumberOfItemInRow + (maxNumberOfItemInRow - 1);
    	int[][] result = new int[xSize][ySize];
    	
    	for(int[] array : result) {
    		Arrays.fill(array, 0);
    	}
    	
    	int shift = maxNumberOfItemInRow - 1;
    	int i = 0;
    	for(int[] array : result) {
			if(shift < 0) break;
			else {
				for(int k = shift; k <= array.length - shift; k += 2) {
					array[k] = inputNumbers.get(i);
					i++;
				}shift--;
			}
    	}
    	return result;
    }

    private static int getMaxNumberOfItemInRow(List<Integer> inputNumbers) {
    	int count = 1;
    	int sizeList = inputNumbers.size();
    	while(sizeList != 0) {
    		sizeList -= count;
    		if(sizeList == 0) return count;
    		else if(sizeList < 0) return -1;
    		count++;
    	}
    	return count;
    }
}
