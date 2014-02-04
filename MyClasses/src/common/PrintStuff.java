package common;

public class PrintStuff {

	/**
	 * Takes an int array and prints its contents to console
	 * in the form {1, 2, ..., n}
	 * @param ints the integer array to be printed
	 */
	public static void printArray(int[] ints) {
		System.out.print(" {");
		for (int i = 0; i < ints.length; i++) {
			System.out.print(ints[i]);
			if (i != ints.length -1 ) {
				System.out.print(", ");
			}
		}
		System.out.println("}");
	}
	/**
	 * Takes an int array and prints its contents to console
	 * in the form name: {1, 2, ..., n}
	 * @param name what the array is called
	 * @param ints the integer array to be printed
	 */
	
	public static void printArray(String name, int[] ints) {
		System.out.print(name + ": {");
		for (int i = 0; i < ints.length; i++) {
			System.out.print(ints[i]);
			if (i != ints.length -1 ) {
				System.out.print(", ");
			}
		}
		System.out.println("}");
	}
}
