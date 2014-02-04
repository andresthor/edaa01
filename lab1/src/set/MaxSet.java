package set;

import java.util.NoSuchElementException;
import java.util.Iterator;


public class MaxSet<E extends Comparable<E>> extends ArraySet<E> {
	private E maxElement;
	
	/**
	 * Constructs a new empty set.
	 */
	public MaxSet() {
		super();
	}
	
	/** Returns the currently largest element in this set. 
	pre: the set is not empty 
	post: the set is unchanged 
	@return the currently largest element in this set 
	@throws NoSuchElementException if this set is empty 
	*/ 
	public E getMax() {
		if (super.isEmpty()) {
			throw new NoSuchElementException();
		} else {
			return maxElement;
		}
	}
	
	/** 
	 * Adds the specified element to this set, if it is not already present.
	 * post: x is added to the set if it is not already present
	 * @param  x the element to be added
	 * @return true if the specified element was added
	 */
	public boolean add(E x) {
		if (super.isEmpty()) {
			maxElement = x;
			return super.add(x);
		} else if (maxElement.compareTo(x) >= 0) {
			return super.add(x);
		} else {
			maxElement = x;
			return super.add(x);
		}
	}
	
	/** 
	 * Removes the specified element from this set if it is present. 
	 * post: 	x is removed if it was present
	 * @param 	x the element to remove - if present
	 * @return true if the set contained the specified element
	 */
	public boolean remove(Object x) {
		if (super.isEmpty()) {
			return false;
		} else if (maxElement.compareTo((E) x) == 0) {
			super.remove(x);
			findMax();
			return true;			
		} else {
			return super.remove(x);
		}

	}
	/**
	 * Compares all the elements in this set, determines the largest
	 * and makes maxElement point to that element.
	 * If the set is empty it sets E maxElement = null
	 * pre: maxElements possibly points to wrong value
	 * post: maxElement points to largest value in this set
	 */
	private void findMax() {
		if (super.isEmpty()) {
			maxElement = null;
		} else {
			Iterator<E> itr = super.iterator();
			E nextValue = itr.next();
			maxElement = nextValue;
			while(itr.hasNext()) {
				nextValue = itr.next();
				if (maxElement.compareTo(nextValue) < 1) {
					maxElement = nextValue;
				}
			}
		}
	}
	/**
	 * Adds all of the elements in the specified set, for which it is 
	 * possible, to this set. 
	 * post: all elements, for which it is possible, in the 
	 * specified set are added to this set. 
	 * @return true if this set changed as a result of the call 
	 */
	public boolean addAll(SimpleSet<? extends E> c) {
		int tmp = super.size();
		for (E e : c) {
			add(e);
		}
		return tmp != super.size();
	}	
}