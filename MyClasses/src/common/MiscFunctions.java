package common;

public class MiscFunctions {
	
	/**
	 * Finds the magnitude of a an integer. 
	 * @param nbr the integer to be examined
	 * @return the magnitude of nbr. For example: returns 1 for
	 *  integers in the range 0-9 and 3 for integers in the range
	 * 100-999
	 */
	public static int magnitudeOf(int nbr) {
		int magnitude = 1;
		while (true) {
			if (nbr / pow(10, magnitude) % 10 > 0) {
				magnitude++;
			} else { break;}
		}
		return magnitude;
	}
	
	/**
	 * Performs exponentiation with integers.
	 * @param base the base that will be raised to the power of exp
	 * @param exp the exponential which the base is raised to
	 * @return the exponential base^exp
	 */
	public static int pow(int base, int exp) {
		int tmp = base;
		base = 1;
		for (int i = 0; i < exp; i++) {
			base *= tmp;
		}
		return base;
	}

}
