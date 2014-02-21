package map;

import java.util.Scanner;

public class SimpleHashMap<K, V> implements Map<K, V> {
	private Entry<K, V>[] table;
	private double LOAD_FACTOR = 0.75;
	private int size = 0;
	private int capacity;

	public SimpleHashMap() {
		this(16);
	}
	
	public SimpleHashMap(int capacity) {
		this.capacity = capacity;
		table = (Entry<K, V>[]) new Entry[capacity];
	}
	
	private int index(K key) {
		//System.out.println("key: " + key + " key.hashCode: " + key.hashCode() + " % : " + key.hashCode() % 16);
		return Math.abs(key.hashCode() % capacity);
	}
	
	private Entry<K, V> find(int index, K key) {
		Entry<K, V> e = table[index];
		
		while (e != null) {
			if (e.getKey().equals(key))
				return e;
			e = e.next;
		}
		return null;
	}
	
	public V get(Object object) {
		K key = (K) object;
		Entry<K, V> e = find(index(key), key);
		if (e != null)
			return e.getValue();
		
		return null;
			
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public V put(K key, V value) {
		Entry<K, V> e = find(index(key), key);
		
		if (e != null)
			return e.setValue(value);
		
		e = new Entry<K, V>(key, value);
		e.next = table[index(key)];
		table[index(key)] = e;
		size++;
		
		if ((double) size / capacity > LOAD_FACTOR)
			rehash();
		
		return null;
	}
	
	private void rehash() {
		Entry<K, V>[] oldTable = table;
		table = (Entry<K, V>[]) new Entry[capacity *= 2];
		size = 0;
		
		for (int i = 0; i < oldTable.length; i++) {
			Entry<K, V> e = oldTable[i];
			while (e != null) {
				put(e.getKey(), e.getValue());
				e = e.next;
			}
		}
	}

	public V remove(Object object) {
		K key = (K) object;
		Entry<K, V> e = find(index(key), key);
		
		if (e == null) {
			return null;
		} else if (e == table[index(key)]) {
			table[index(key)] = table[index(key)].next;
		} else {
			Entry<K, V> itr = table[index(key)];
			while (itr.next != e) {
				itr = itr.next;
			}
			itr.next = itr.next.next;
		}
		size--;
		return e.getValue();
		
	}

	public int size() {
		return size;
	}
	
	public String show() {
		String str = "";
		Entry<K, V> e;
		for (int i = 0; i < table.length; i++) {
			e = table[i];
			str += String.format("%-10s", i);
			while (e != null) {
				str += String.format("%-10s", e);
				e = e.next;
			}
			str += "\n";
		}
		return str;
	}
	
	public static class Entry<K, V> implements Map.Entry<K, V>{
		private K key;
		private V value;
		private Entry<K, V> next;
		
		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public V setValue(V value) {
			V oldValue = this.value;
			this.value = value;
			return oldValue;			
		}
		
		public String toString() {
			return key + "=" + value;
		}

	}
	
	public static void main(String[] args) {
		SimpleHashMap<Integer, Integer> intMap = new SimpleHashMap<Integer, Integer>();
		SimpleHashMap<String, String> stringMap = new SimpleHashMap<String, String>();
		
		String str1, str2;
		str1 = str2 = "";
		Scanner scr = new Scanner(System.in);
		while (true) {
			System.out.print("Key = ");
			str1 = scr.nextLine();
			System.out.print("Value = ");
			str2 = scr.nextLine();
			if ((str1.equals("quit") || str2.equals("quit"))){
				break;
			}
			stringMap.put(str1, str2);
		}
		
		System.out.print(stringMap.show());
	}

}
