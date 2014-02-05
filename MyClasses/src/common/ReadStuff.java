package common;

import java.util.Scanner;

public class ReadStuff {
	
	/**
	 * Asks the user to input integers, followed by ENTER. Will also
	 * accept # and rnd as valid inputs. rnd will generate a random
	 * integer between 1 and 1000
	 * @return string that contains an integer (input or random), or #
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
				} else if (input.equals("rnd")) {
					input = "" + ((int) (Math.random() * 1000));
					System.out.println("You chose a random int and got: " + input);
					break;
				} else {
					System.out.println("That was not a valid integer: ");
				}
			}
		}
		return input;
	}
}
