// Project 2 - Binary Search Tree
// CS 3345.001
// Programmer: Stephen-Michael Brooks


public class MySearchTree<E extends Comparable<? super E>> {
	private Node<E> root;
	private int size;
	
	// constructor
	public MySearchTree() {
		root = null;
		size = 0;
	}
	
	public int getSize() {
		return size;
	}
	
	public boolean find(E e) {
		// make a call to private find
		return contains(e, root);
	}
	
	public void add(E e) {
		// make a call to private add
		size++;
		root = insert(e, root);
	}
	
	public int leafCount() {
		if (root == null)	// list is empty
			return 0;
		
		// make call to private leaves
		return leaves(root);
	}
	
	public int parentCount() {
		if (root == null)	// list is empty
			return 0;
		
		// make call to private parents
		return parents(root);
	}
	
	public int height() {
		if (root == null)	// list is empty
			return 0;
		
		return depth(root);
	}
	
	public boolean isPerfect() {
		if (root == null)
			return false;
		
		// make call to perfect
		return perfect(root);
	}
	
	public void inOrderPrint() {
		if (root == null)
			return;
		
		// make call to inorder
		inorder(root);
	}
	
	public void preOrderPrint() {
		if (root == null)
			return;
		
		// make call to preorder
		preorder(root);
	}
	
	public void ancestors(E e) {
		if (root == null)
			return;
		
		if (this.find(e) == false)
			System.out.println(e + " is not in the list.");
		else {
			System.out.print("Path to " + e + ": ");
			// make call to path
			path(e, root);
			System.out.print(e);
		}
	}
	
	private Node<E> insert(E e, Node<E> n) {
		if (n == null)
			return new Node<E>(e, null, null);
		
		int result = e.compareTo(n.data);

		if (result < 0)	// insert needs to move left
			n.leftchild = insert(e, n.leftchild);
		else if (result > 0)	// insert needs to move right
			n.rightchild = insert(e, n.rightchild);

		else { size--; }		// duplicate data
		return n;
	}

	private boolean contains(E e, Node<E> n) {
		if (n == null) // the list is empty
			return false;

		int result = e.compareTo(n.data);

		if (result == 0)
			return true;
		if (result < 0)
			return contains(e, n.leftchild);
		else if (result > 0)
			return contains(e, n.rightchild);
		
		else
			return true;
	}
	
	private int leaves(Node<E> n) {
		if (n.leftchild != null && n.rightchild != null)
			return leaves(n.leftchild) + leaves(n.rightchild);
		if (n.leftchild == null && n.rightchild != null)
			return leaves(n.rightchild);
		if (n.leftchild != null && n.rightchild == null)
			return leaves(n.leftchild);
		if (n.leftchild == null && n.rightchild == null)
			return 1;
		return 0;
	}
	
	private int parents(Node<E> n) {
		if (n.leftchild != null && n.rightchild != null)
			return 1 + parents(n.rightchild) + parents(n.leftchild);
		if (n.leftchild == null && n.rightchild != null)
			return 1 + parents(n.rightchild);
		if (n.leftchild != null && n.rightchild == null)
			return 1 + parents(n.leftchild);
		else {}
		
		return 0;
	}
	
	private int depth(Node<E> n) {
		int rightHeight = 0, leftHeight = 0;
		if (n.leftchild != null)
			leftHeight = depth(n.leftchild) + 1;
		if (n.rightchild != null)
			rightHeight = depth(n.rightchild) + 1;
		
		if (rightHeight > leftHeight)
			return rightHeight;
		else
			return leftHeight;	
	}
	
	private boolean perfect(Node<E> n) {
		boolean perfectLeft = false;
		boolean perfectRight = false;
		if (n.leftchild != null && n.rightchild != null) {
			perfectLeft = perfect(n.leftchild);
			perfectRight = perfect(n.rightchild);
		}
		if (perfectLeft && perfectRight)
			return true;
		else
			return true;
		
	}
		
	
	private void inorder(Node<E> n) {
		if(n != null) {
			inorder(n.leftchild);
			System.out.println(n.data);
			inorder(n.rightchild);
		}
	}
	
	private void preorder(Node<E> n) {
		if (n != null) {
			System.out.println(n.data);
			preorder(n.leftchild);
			preorder(n.rightchild);
		}
	}
	
	private void path(E e, Node<E> n) {
		if (n == null) // the list is empty
			return;

		int result = e.compareTo(n.data);

		if (result == 0)
			return;
		
		if (result > 0) {
			System.out.print(n.data + " -> ");
			path(e, n.rightchild);
		}
		else if (result < 0) {
			System.out.print(n.data + " -> ");
			path(e, n.leftchild);
		}
	}
	
	// Node inner class
	private static class Node<E> {
		E data;
		Node<E> leftchild, rightchild;
		
		// Node constructor
		Node(E e) {
			this(e, null, null);
		}
		
		Node(E e, Node<E> left, Node<E> right) {
			data = e;
			leftchild = left;
			rightchild = right;
		}
	}
}