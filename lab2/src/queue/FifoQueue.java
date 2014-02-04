package queue;
import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> 
implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		size = 0;
	}

	/**	
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator() {
		return new QueueIterator();
	}
	
	/**	
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size() {		
		return size;
	}

	/**	
	 * Inserts the specified element into this queue, if possible
	 * post:	The specified element is added to the rear of this queue
	 * @param	x the element to insert
	 * @return	true if it was possible to add the element 
	 * 			to this queue, else false
	 */
	public boolean offer(E x) {
		QueueNode<E> q = new QueueNode(x);
		
		if (size == 0) {
			last = q;
			last.next = q;
		} else {
			q.next = last.next;
			last.next = q;
			last = q;			
		}
		size++;
		return true;
	}

	/**	
	 * Retrieves and removes the head of this queue, 
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty 
	 */
	public E poll() {
		if (size == 0)
			return null;
		
		E e = last.next.element;
		last.next = last.next.next;
		//if (size == 1) { last = null; }
		size--;
		return e;
	}

	/**	
	 * Retrieves, but does not remove, the head of this queue, 
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null 
	 * 			if this queue is empty
	 */
	public E peek() {
		if (size == 0)
			return null;
		
		return last.next.element;
	}
	
	/**
	 * Appends the specified queue to this queue
	 * post: all the elements from the specified queue are appended
	 * 		 to this queue. The specified queue (q) is empty
	 * @param q the queue to append
	 */
	public void append(FifoQueue<E> q) {		
		if (q.last == null) {
			return;
		} else if (size != 0) {
			QueueNode<E> first = last.next;
			last.next = q.last.next;
			q.last.next = first;
		}
		size += q.size;
		last = q.last;
		q.size = 0;
		q.last = null;
	}

	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}
	
	private class QueueIterator implements Iterator<E> {
		private QueueNode<E> pos;
		
		/* Constructor */
		private QueueIterator () {
			if (size == 0) {
				pos = null;
			} else {
				pos = last.next;
			}
			
		}
		
		public boolean hasNext() {
			return pos != null;
		}

		public E next() {
			if (hasNext()){
				E e = pos.element;
				pos = pos.next;

				if (pos == last.next)
					pos = null;
				
				return e;
			}
			throw new NoSuchElementException();
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

}
