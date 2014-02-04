package tmp;

import java.util.ArrayList;

import queue.FifoQueue;
import common.MiscFunctions;
import common.PrintStuff;
import common.ReadStuff;
import sort.RadixSort;

public class TestRadixSort {

	public static void main(String[] args) {
		
//		Exempel från laborationshäftet
//		int[] array = {721, 10, 51, 122, 674, 96, 109, 44, 236, 178, 1, 567, 674};
//		PrintStuff.printArray("Original", array);
//		RadixSort.radixSort(array, 3);
//		PrintStuff.printArray("Sorted", array);

		String input = "";
		int largest = 0;
		ArrayList<Integer> intInput = new ArrayList<Integer>();
		
		// Input from user - create an integer array
		System.out.println("Create an array of integers by entering them "
				+ "one at a time, followed by ENTER. Input # to end: ");
		while (!input.equals("#")) {
			input = ReadStuff.getIntString();
			if(input.equals("#")) {break; }
			int nbr = Integer.parseInt(input);
			intInput.add(nbr);
			if (nbr > largest) { largest = nbr; }
		}
		
		int[] intArray = new int[intInput.size()];
		int k = 0;
		for (int i : intInput) {
			intArray[k] = i;
			k++;
		}
		
		// Magnitude of the largest int entered
		int magnitude = MiscFunctions.magnitudeOf(largest);
		
		// Print out the original array as well as the sorted array
		PrintStuff.printArray("Unsorted", intArray);
		RadixSort.radixSort(intArray, magnitude);
		PrintStuff.printArray("Sorted", intArray);
		
	}
}
