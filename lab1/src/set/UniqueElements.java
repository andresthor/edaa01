package set;

public abstract class UniqueElements {
	private static MaxSet<Integer> s;
	
	/**
	 * Takes an integer vector, removes duplicates and sorts the
	 * remaining integers in ascending order.
	 * @param ints the unsorted integer vector with possible
	 * duplicates
	 * @return an integer vector with no duplicates, sorted in
	 * ascending order
	 */
	public static int[] uniqueElements(int[] ints) {
		MaxSet<Integer> s2 = new MaxSet<Integer>();
		for (int i : ints) {
			s2.add(i);
		}
		
		int size = s2.size;
		
		int[] vec = new int[size];
		
		for(int i = s2.size-1; i >=0; i--) {
			vec[i] = s2.getMax();
			s2.remove(s2.getMax());
			size = s2.size;
		}
		
		return vec;
	}

}
