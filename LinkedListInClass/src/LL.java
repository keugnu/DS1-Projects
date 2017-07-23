
public class LL {
	private Node head, tail;
	private int size;
	
		
	
	public void addfront(int x) {
		if (head == null) {		// empty list
			head = new Node(x, null);
			tail = head;
		}
		else {
			head = new Node(x, head);
		}
		size++;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("[ ");
		Node p = head;
		while (p != null) {
			sb.append(p.data + " ");
			p = p.next;
		}
		sb.append("]");
		return new String(sb);
	}
	
	public void addEnd(int x) {
		if (head == null) {
			tail = new Node(x, null);
			head = tail;
		}
		else {
			tail.next = new Node(x, null);
			tail = tail.next;
		}
	}
	
	public void reverse() {
		Node p1, p2, p3;
		
		if (size < 2)
			return;
		
		p1 = null;
		p2 = head;
		p3 = p2.next;
		
		while (p3 != null) {
			p2.next = p1;
			p1 = p2;
			p2 = p3;
			p3 = p3.next;
		}
		
		p2.next = p1;
		tail = head;
		head = p2;
	}
	
	public boolean delete(int x) {
		Node p = head;
		Node prev = null;
		while (p != null && p.data != x) {
			prev = p.next;
			p = p.next;
			
		}
		
		if (p != null) {
			prev.next = p.next;
		}
		return p != null;
	}
	
	public static class Node {
		int data;
		Node next;
		
		Node(int d, Node n) {
			data = d;
			next = n;
		}
	}
}
