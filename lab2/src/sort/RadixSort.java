package sort;
import queue.FifoQueue;


public class RadixSort {
	public static void radixSort(int[] a, int maxNbrOfDigits) {
		FifoQueue<Integer> numbers = new FifoQueue<Integer>();
		FifoQueue<Integer>[] queues = (FifoQueue<Integer>[]) new FifoQueue[10];
		for (int i: a) {
			numbers.add(i);
		}

		for (int i = 0; i < 10; i++) {
			queues[i] = new FifoQueue<Integer>();
		}
		for (int i = 0; i < maxNbrOfDigits; i++) {
			while (numbers.size() != 0) {
				int nbr = numbers.poll();
				int j = nbr / pow(10, i) % 10;
				queues[j].offer(nbr);
			}

			for (int l = 0; l < 10; l++) {
				numbers.append(queues[l]);
			}
		}
		
		for (int i = 0; i < a.length; i++) {
			a[i] = numbers.poll();
		}
	}
	
	public static int pow(int base, int exp) {
		int tmp = base;
		base = 1;
		for (int i = 0; i < exp; i++) {
			base *= tmp;
		}
		return base;
	}
}
