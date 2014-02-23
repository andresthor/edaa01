package map;

import java.util.Scanner;

public class SimpleHashMap<K, V> implements Map<K, V> {
	private Entry<K, V>[] table;
	private double LOAD_FACTOR = 0.75;
	private int size = 0;
	private int capacity;

	public SimpleHashMap() {
		this(16);										// Anropar den andra konstruktorn med
	}													// argumentet 16

	public SimpleHashMap(int capacity) {
		this.capacity = capacity;	
		table = (Entry<K, V>[]) new Entry[capacity];	// Skapar en array med Entry<K, V> objekt
	}
	
	private int index(K key) {
		return Math.abs(key.hashCode() % capacity); 	// Omvandlar key först till en hashcode och
	}													// normerar den sedan. 
	
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
		K key = (K) object;								// Object -> K
		Entry<K, V> e = find(index(key), key);			
		if (e != null)									// Finns key i Entry[] ?
			return e.getValue();
		
		return null;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public V put(K key, V value) {
		Entry<K, V> e = find(index(key), key);			// Söker om den redan finns
		
		if (e != null)									// Om nyckeln fanns så ändrar vi motsvarande
			return e.setValue(value);					// värde till value och returnerar det gamla
		
		e = new Entry<K, V>(key, value);				// Annars skapar vi Entry med key och value
		e.next = table[index(key)];						// och lägger den *främst* i listan med mot-
		table[index(key)] = e;							// svarande index
		size++;
		
		if ((double) size / capacity > LOAD_FACTOR)		// om vi överstiger Load faktorn, rehash-ar
			rehash();									// vi vårt map
		
		return null;
	}
	
	private void rehash() {
		Entry<K, V>[] oldTable = table;					// Skapar ny array + döper om den gamla
		table = (Entry<K, V>[]) new Entry[capacity *= 2];
		size = 0;
		
		for (int i = 0; i < oldTable.length; i++) {		// För alla värdena i gamla : lägg i nya
			Entry<K, V> e = oldTable[i];
			while (e != null) {
				put(e.getKey(), e.getValue());
				e = e.next;
			}
		}
	}

	public V remove(Object object) {
		K key = (K) object;								// Object -> K
		Entry<K, V> e = find(index(key), key);			// Söker om den finns
		
		if (e == null) {
			return null;								// returnerar null om den inte finns
		} else if (e == table[index(key)]) {			// om den motsv första elementet i listan
			table[index(key)] = table[index(key)].next; // kan vi ta bort den direkt
		} else {
			Entry<K, V> itr = table[index(key)];		// Annars itererar vi genom listan och
			while (itr.next != e) {						// tar bort den när den är funnen
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
		for (int i = 0; i < table.length; i++) {		// för alla index i listan :
			e = table[i];								// skapar vi Entry objekt
			str += String.format("%-10s", i);			// Skriver ut index + 10 mellanslag
			while (e != null) {							// itererar över alla Entry i listan
				str += String.format("%-10s", e);		// och skriver ut de
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
		//SimpleHashMap<Integer, Integer> intMap = new SimpleHashMap<Integer, Integer>();
		SimpleHashMap<String, String> stringMap = new SimpleHashMap<String, String>();
		
		// Ett enkelt program som loopar och ber användaren först om Key och sedan Value
		// Lägger all input i SimpleHashMap för strängar
		// Slutar loopa och skriver ut resultatet om användaren anger 'quit' som antingen key
		// eller value
		
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
