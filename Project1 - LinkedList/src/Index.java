// Project 1 - Names (Linked List)
// CS 3345.001
// Programmer: Stephen-Michael Brooks


import java.util.Iterator;
import java.util.NoSuchElementException;


public class Index implements Iterable<String>{
	private Node head, tail;							// tail implemented in the case of wanting to add to end
	private int size;
	private static int[] letterArray = new int[26];		// this array tracks how many words are added that begin with each letter
														// index 0 = A .... index 25 = Z
	public Index() {
		head = tail = null;
		size = 0;
	}
	
	public void add(String e) {		
		
		int firstLetter = e.charAt(0)-65;
		if (firstLetter > 25)
			firstLetter -= 32;	// letter was lower case, so adjust to share value with upper
		
		if (head == null) {		// this list is empty
			head = new Node(e.substring(0, 1).toUpperCase(), tail); 
			
			tail = new Node(e, null);
			head.next = tail;
			letterArray[firstLetter] += 2;
			size =+ 2;
		}
		else {		// the list is not empty
			int adjustToIndex = 0;		// adjustToIndex will serve as sentinel for traversing to the desired position in the list
			if (letterArray[firstLetter] == 0) {	// the list does not currently have any words that begin with the first letter of the new word
													// so both the letter and the word must be added
				for (int i = firstLetter; i >= 0; i--)	// accumulating the number of words that are alphabetically less than the new word
					adjustToIndex += letterArray[i];
				
				Node nameTemp = head;		// nameTemp will be used to traverse the list
				Node newNameNode = new Node(e, null);
				
				for (int i = adjustToIndex-1; i > 0; i--)	// traversing the list until nameTemp is the node that must point to the new node
					nameTemp = nameTemp.next;

				Node preserveNext = nameTemp.next;
				nameTemp.next = newNameNode;
				newNameNode.next = preserveNext;
				
				Node newLetterNode = new Node(e.substring(0, 1).toUpperCase(), newNameNode);
				nameTemp.next = newLetterNode;
				
				if (newNameNode.next == null)	// the new node needs to be tail
					tail = newNameNode;
				
				if (adjustToIndex == 0) {	// the new letter node must be the new head, some exchanging of pointers must happen
					Node temp = head;
					head = temp.next;
					Node temp2 = head.next.next;
					temp.next = temp2;
					Node temp3 = head.next;
					temp3.next = temp;
				}
				
			letterArray[firstLetter] += 2;	
			size += 2;
			}
			
			else {		// a letter node for the new word already exists
				adjustToIndex = 0;
				for (int i = firstLetter-1; i > 0; i--)
					adjustToIndex += letterArray[i];
				
				Node temp = head;
				for (int i = 0; i < adjustToIndex; i++)
					temp = temp.next;
				
				Node newNameNode1 = new Node(e, temp.next);
				temp.next = newNameNode1;
				
				while (newNameNode1.data.compareTo(newNameNode1.next.data) > -1) {
					temp.next = newNameNode1.next;
					newNameNode1.next = temp.next.next;
				}
				temp.next = newNameNode1;
				letterArray[firstLetter]++;
				size++;
			}
			
		}
	}	// end add method
	
	public void removeLetter(String e) {
		int firstLetter = e.charAt(0)-65;
		if (firstLetter > 25)
			firstLetter -= 32;
		
		if (letterArray[firstLetter] == 0)	// there are no words in the list that begin with the first letter of the requested word
			return;
		
		int adjustToIndex = 0;			
		for (int i = firstLetter-1; i > 0; i--)
			adjustToIndex += letterArray[i];
		
		Node front = head;		// front will be used to traverse the list and will stop just before the letter requested to be removed
		for (int i = adjustToIndex-1; i > 0; i--) {
			front = front.next;
		}
		
		if (front == head) {		// the letter requested to be removed is the head so just reset the head to the appropriate position
			Node newHead = front;
			for (int i = letterArray[firstLetter]; i > 0; i--)
				newHead = newHead.next;
			head = newHead;
			
			size -= letterArray[firstLetter];
			letterArray[firstLetter] = 0;
			return;		// the removeLetter method has finished, return
		}
		
		Node rear = front;		// rear will iterate further in the list until the final name of the removed letter
		int adjustToIndexRear = letterArray[firstLetter];
		for (int i = adjustToIndexRear; i > 0; i--)
			rear = rear.next;
		
		if (rear.next == null) {	// if rear points to null, the tail must be reassigned
			tail = front;
			tail.next = null;
		}
		else
			front.next = rear.next;	// point front to rear's next
		
		letterArray[firstLetter] = 0;
		size -= adjustToIndexRear;
	} // end removeLetter method
	
	public void remove(String e) {
		int firstLetter = e.charAt(0)-65;
		if (firstLetter > 25)
			firstLetter -= 32;
		
		if (letterArray[firstLetter] == 0)	// the requested word does not exist
			return;
		
		if (letterArray[firstLetter] == 2) {	// the requested name is alone with a letter node, so remove both
			Node removal = head;
			int adjustToIndex = 0;	
			for (int i = firstLetter-1; i > 0; i--)
				adjustToIndex += letterArray[i];
			
			if (adjustToIndex == 0)	// the requested letter node is the head, so just reassign head
				head = head.next.next;
			else {	// the requested word is not alone
				for (int i = adjustToIndex-1; i > 0; i--)
					removal = removal.next;
				
				if (removal.next.next == null) // the requested name is the tail
					tail = removal;
				else
					removal.next = removal.next.next.next;
			}
			
			letterArray[firstLetter] = 0;
			size -= 2;
			return;
		}	// end if word is alone
		
		Node removal = head;
		int adjustToIndex = 0;
		for (int i = letterArray[firstLetter-1]; i >= 0; i--)
			adjustToIndex += letterArray[i];
		for (int i = adjustToIndex; i > 0; i--)
			removal = removal.next;
		
		boolean isRemoved = false;
		while (!isRemoved) {
			if (e.compareTo(removal.next.data) == 0) {	// checks if the requested word has been found
				removal.next = removal.next.next;
				isRemoved = true;
				letterArray[firstLetter]--;
				size--;
			}
			removal = removal.next;
		}
		return;
	}	// end remove method
	
	public boolean find(String e) {
		
		if (head == null) throw new IndexOutOfBoundsException();
		
		int adjustToIndex = 0;
		int firstLetter = e.charAt(0)-65;
		if (firstLetter > 25)
			firstLetter -= 32;
		
		if (letterArray[firstLetter] == 0)
			return false;
		else {
			for (int i = firstLetter-1; i > 0; i--)
				adjustToIndex += letterArray[i];
			
			Node temp = head;
			for (int i = adjustToIndex; i > 0; i--)
				temp = temp.next;
			
			for (int i = adjustToIndex; i >= 0; i--) {
				if (e.compareTo(temp.next.data) == 0)
					return true;
				else
					temp = temp.next;
			}
			return false;
		}
	}	// end find method
	
	
	public int getSize() {
		return size;
	}	// end getSize method
	
	@Override
	public String toString() {
		
		Node current = head; 
		String str = "";
		while (current != null) {
			if (current.data.length() > 1)
				str += "  ";
			str += current.data;
			str += "\n";
			
			current = current.next;
		}
		return str;
	}	// end toString method
	
	@Override
	public Iterator<String> iterator() {
		return new MyLinkedListIterator();
	}	// end iterator method
	
	
	/* ******************
	 * Node inner class *
	 * ******************/
	private class Node {
		protected String data;		// stores list data
		protected Node next;		// points to next node
		
		public Node(String d, Node n) {
			data = d;
			next = n;
		}
	}	// end Node class
	
	/* **********************************
	 * MyLinkedListIterator inner class *
	 * **********************************/
	private class MyLinkedListIterator implements Iterator<String> {
		private Node nextNode;
		
		public MyLinkedListIterator() {
			nextNode = head;
		}
		
		public boolean hasNext() {
			return nextNode != null;
		}

		public String next() {
			// make sure next exists
			if(!hasNext()) throw new NoSuchElementException();
			String data = nextNode.data;
			nextNode = nextNode.next;
			return data;
		}
		
	}	// end MyLinkedListIterator class
	
	public static void main(String[] args) {
		Index list = new Index();
		
		list.add("Jessica");
		list.add("Stephen");
		list.add("Sarah");
		list.add("betty");
		list.add("Bart");
		list.add("marcos");
		list.add("Thomas");
		list.add("Fred");
		
		for(String x : list)
			System.out.print(x + " ");
		
		System.out.println("\n");
		
		//list.removeLetter("B");		// remove letter that is head
		list.remove("betty");
		list.remove("Bart");			// remove final name of letter
		list.remove("Stephen");			// remove single name
		list.removeLetter("M");			// remove letter in middle of list
	//	list.removeLetter("T");			// remove letter at end of list
		list.remove("Thomas");			// remove the very last name in the list
		System.out.println("Does Thomas exist in the list? " + list.find("Thomas"));
		System.out.println("Does Sarah exist in the list? " + list.find("Sarah"));
		System.out.println("Does Fred exist in the list? " + list.find("Fred"));
		System.out.println("Does Bill exist in the list? " + list.find("Bill") + "\n");
		
		System.out.println(list);
	}

}
