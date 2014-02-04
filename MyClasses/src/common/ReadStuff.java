package common;

import java.util.Scanner;

public class ReadStuff {
	
	/**
	 * Asks the user to input integers, followed by ENTER. Will also
	 * accept # as a valid input.
	 * @return string that contains an integer or #
	 */
	public static String getIntString() {
		Scanner scanner = new Scanner(System.in);
		String input;
		while (true) {
			input = scanner.next();
			try {
				Integer.parseInt(input);
				break;
			} catch (Exception e) {
				if (input.length() == 1 && input.charAt(0) == '#') {
					break;
				} else {
					System.out.println("That was not a valid integer: ");
				}
			}
		}
		return input;
	}
}
