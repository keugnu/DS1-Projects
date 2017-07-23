// Project 3 - Linear Probing Hash Table
// CS 3345.001
// Programmer: Stephen-Michael Brooks

public class LinearProbingHashTable<K extends Comparable,V> {
	
	private Entry<K,V> [] table;
	private int currentSize;
	
	public LinearProbingHashTable() {
		this(4);
	}
	
	public LinearProbingHashTable(int size) {
		allocateArray(size);
		makeEmpty();
	}

	private static class Entry<K extends Comparable,V> {
		public V data;
		public K key;
		boolean isActive;
			
		public Entry(K key, V value) {
			this.data = value;
			this.key = key;
			isActive = true;
		}
		
	}

	private void allocateArray(int arraySize) {
		table = new Entry[arraySize];
		
	}
	
	private void makeEmpty() {
		currentSize = 0;
		for (int i = 0; i < table.length; i++) {
			table[i] = null;
		}
	}
	

	public boolean insert(K key, V value) {
	/*  inserts entry, rehashes if half full,
        can re-use deleted entries, throws
        exception if key is null, returns
        true if inserted, false if duplicate.
	*/
		int hashValue = getHashValue(key);
		if (table[hashValue] != null) {
			if (hashValue + 2 > table.length)
				hashValue = -1;
			int probe = 1;
			while(table[hashValue+probe] != null)
				probe++;
			
			table[hashValue+probe] = new Entry<>(key, value);
			currentSize++;
			
			if (table.length / 2  < currentSize)
				rehash();
			
			return true;
		}
		else {
			table[hashValue] = new Entry<>(key, value);
			currentSize++;

			if (table.length / 2  < currentSize)
				rehash();
			
			return true;
		}

	}
		
	public V find(K key) {
	// returns value for key, or null if not found
		V data = null;
		
		int location = getHashValue(key);
//		if (table[location] != null && table[location].key == key && table[location].isActive)
//			return table[location].data;
//		else
			if (location + 2 > table.length)
				location = -1;
			int probe = 1;
			int i = 0;
			while(table[location+probe] == null || table[location+probe].key.compareTo(key) != 0) {
				probe++;
				i++;
				if (i == table.length)
					return data;
				if (location+probe == table.length)
					location = probe = 0;
			}
		
		if (table[location+probe].key.compareTo(key) == 0 && table[location+probe].isActive)
			return table[location+probe].data;
		else
			return data;
	}
	
	public boolean delete(K key) {
	/*	marks the entry deleted but leaves it there,
       	returns true if deleted, false if not found
    */
		int location = getLocation(key);
		if (location == -1)
			return false;
		else
			table[location].isActive = false;
		return true;
	}
	
	public void rehash() {
	/*	doubles the table size, hashes everything to
        the new table, omitting items marked deleted
    */
		Entry<K,V> [] oldTable = table;
		allocateArray(2*oldTable.length);
		currentSize = 0;
		
		for (int i = 0; i < oldTable.length; i++) {
			if(oldTable[i] != null)
				insert(oldTable[i].key, oldTable[i].data);
		}
		
	}
	
	public int getHashValue(K key) {
	/* 	returns the hash value for the given key,
        or -1 if not found.
        (this is the value before probing occurs)
	*/
		int hashValue = key.hashCode();
		
		hashValue %= table.length;
		if (hashValue < 0)
			hashValue += table.length;
		
		return hashValue;
	}
	
	public int getLocation(K key) {
	/*	returns the location for the given key,
        or -1 if not found.
        (this is the value after probing occurs)
    */
		int location = getHashValue(key);
		int i = 0;
		int probe = 1;
		
		if (table[location] != null && table[location].key.compareTo(key) == 0 && table[location].isActive)
			return location;
		else {
			if (location + 2 > table.length)
				location = -1;
			
			while(i < table.length - 1 && (table[location+probe] == null || table[location+probe].key.compareTo(key) != 0)) {
				probe++;
				i++;
				if (location + probe >= table.length)
					location = 0;
				
				
			}
		}
		
		if (i == table.length - 1)
			return -1;
		else if (table[location+probe].isActive)
			return location+probe;
		else return -1;
		
	}
	
	public String toString() {
	/*	returns a formatted string of the hash table:
        0  null
        1  xxxxx
        2  yyyyy
        ...
    */
		String output = "";
		for(int i = 0; i < table.length; i++) {
			if (table[i] != null && table[i].isActive)
				output = output + i + "  " + table[i].data + "\n";
			else
				output = output + i + "  null\n";
		}
		return output;
	}
}
