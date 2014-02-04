package set;

import java.util.ArrayList;
import common.PrintStuff;
import common.ReadStuff;

public class UniqueTest2 {

	public static void main(String[] args) {
		int[] trimmed, ordered;
		ArrayList<Integer> digits = new ArrayList<Integer>();
		String input = "";
		PrintStuff printer = new PrintStuff();
		ReadStuff reader = new ReadStuff();
		
		
		System.out.println("Type in a positive integer, or '#' to end: ");
		while (!input.equals("#")) {
			input = reader.getIntString();
			if (!input.equals("#")) {
				digits.add(Integer.parseInt(input));
			} else break; 
		}
		
		trimmed = new int[digits.size()];
		
		int k = 0;
		for (int i : digits) {
			trimmed[k] = i;			
			k++;
		}
		
		ordered = UniqueElements.uniqueElements(trimmed);
		
		printer.printArray("Orginal", trimmed);
		printer.printArray("Ordered", ordered);
	}

}
