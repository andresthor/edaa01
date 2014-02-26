package misc;

import java.util.LinkedList;

public class MiscTesting {

	public static void main(String[] args) {
		LinkedList<String> list = new LinkedList<String>();
		
		list.add("First");
		list.add("Second");
		list.add("Third");
		list.add("First");
		list.add("Fourth");
		
		for (String str : list) {
			System.out.println(str);
		}
		
	}

}
