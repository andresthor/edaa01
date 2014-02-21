package misc;

public class MiscTesting {

	public static void main(String[] args) {
		String str = "";
		String[] array = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};
		
		for (int i = 0; i < array.length; i++) {
			str = str + String.format("%-10s", i) + array[i] + "\n";
		}
		
		System.out.print(str);
		
	}

}
