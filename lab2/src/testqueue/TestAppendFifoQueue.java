package testqueue;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import queue.FifoQueue;

public class TestAppendFifoQueue {
	private FifoQueue<Integer> myIntQueue1;
	private FifoQueue<Integer> myIntQueue2;
	private Iterator<Integer> intItr1;
	private Iterator<Integer> intItr2;

	@Before
	public void setUp() throws Exception {
		myIntQueue1 = new FifoQueue<Integer>();
		myIntQueue2 = new FifoQueue<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		myIntQueue1 = null;
		myIntQueue2 = null;
	}

	@Test
	public void testAddTwoEmptyQueues() {
		myIntQueue1.append(myIntQueue2);
		assertEquals("Size should be 0 after adding 0: ", 0, myIntQueue1.size());
		assertEquals("Size should be 0 after adding to another queue: ", 0, myIntQueue2.size());
	}
	
	@Test
	public void testAddEmptyToQueue() {
		for (int i = 0; i < 10; i++) {
			myIntQueue1.add(i);
		}
		myIntQueue1.append(myIntQueue2);
		
		assertEquals("First element in myIntQueue1 should still be 0: ", 0, (int) myIntQueue1.peek());
		assertEquals("Size should be 10 after adding 0: ", 10, myIntQueue1.size());
		assertEquals("Size should be 0 after adding to another queue: ", 0, myIntQueue2.size());
		
		int k = 0;
		for (int i : myIntQueue1) {
			myIntQueue1.poll();
			k++;
			assertEquals("Size wrong after polling x times: ", 10 - k, myIntQueue1.size());
		}
		assertEquals("Iterator should have iterated over 10 items: ", 10, k);
	}
	
	@Test
	public void testAddQueueToEmpty() {
		for (int i = 0; i < 10; i++) {
			myIntQueue2.add(i);
		}
		myIntQueue1.append(myIntQueue2);
		
		assertEquals("First element in myIntQueue1 should be 0: ", 0, (int) myIntQueue1.peek());
		assertEquals("Size should be 10 after adding 10: ", 10, myIntQueue1.size());
		assertEquals("Size should be 0 after adding to another queue: ", 0, myIntQueue2.size());
		
		int k = 0;
		for (int i : myIntQueue1) {
			myIntQueue1.poll();
			assertEquals("Size wrong after polling x times: ",  9 - k, myIntQueue1.size());
			k++;
		}
		assertEquals("Iterator should have iterated over 10 items: ", 10, k);
	}
	
	@Test
	public void testAddTwoQueues() {
		for (int i = 0; i < 10; i++) {
			myIntQueue1.add(i);
			myIntQueue2.add(i+10);
		}
		
		myIntQueue1.append(myIntQueue2);
		
		assertEquals("Total size should be 20 elements: ", 20, myIntQueue1.size());
		assertEquals("Size should be 0 after adding to another queue: ", 0, myIntQueue2.size());
		
		int k = 0;
		for (int i : myIntQueue1) {
			assertEquals("Wrong item when polling: ", k, (int) myIntQueue1.poll());
			assertEquals("Size wrong after polling x times: ", 19 - k, myIntQueue1.size());
			k++;
		}
		
		assertEquals("Iterator should have iterated over 20 items: ", 20, k);
	}
	
	@Test
	public void testAddQueuesWithSingleElement() {
		
		myIntQueue1.add(1);
		myIntQueue2.add(2);
		myIntQueue1.append(myIntQueue2);
		
		assertEquals("First element in myIntQueue1 should be 0: ", 1, (int) myIntQueue1.peek());
		assertEquals("Size should be 2 after adding 1: ", 2, myIntQueue1.size());
		assertEquals("Size should be 0 after adding to another queue: ", 0, myIntQueue2.size());
		
	}

}
