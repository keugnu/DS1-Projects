import java.util.Random;
public class MySearchTreeDriver {

	public static void main(String[] args) {
		MySearchTree<Double> tree = new MySearchTree<>();
		
		for (int i = 60; i >= 0; i--) {
			Random ran = new Random();
			double x = ran.nextDouble();
			tree.add(x);
		}
		
		System.out.println("Double Tree: ");
		int size = tree.getSize();
		System.out.println("Size: " + size);
		
		int leaves = tree.leafCount();
		System.out.println("Leaves: " + leaves);

		int parents = tree.parentCount();
		System.out.println("Parents: " + parents);

		int height = tree.height();
		System.out.println("Height: " + height);
		
		System.out.println("Is the tree perfect? " + tree.isPerfect());
		
		System.out.print("[");
		tree.inOrderPrint();
		System.out.println("]");
		
		System.out.print("[");
		tree.preOrderPrint();
		System.out.println("]");
		
		tree.ancestors(6.0);
		
		System.out.println("\n\nString Tree: ");
		MySearchTree<String> eert = new MySearchTree<>();
		
		eert.add("Stephen");
		eert.add("Gertrude");
		eert.add("Amanda");
		eert.add("Bart");
		eert.add("Oliver");
		eert.add("Brittany");
		eert.add("Max");
		eert.add("Zander");
		eert.add("Jacob");
		eert.add("Penelope");
		
		int size2 = eert.getSize();
		System.out.println("Size: " + size2);
		
		int leaves2 = eert.leafCount();
		System.out.println("Leaves: " + leaves2);

		int parents2 = eert.parentCount();
		System.out.println("Parents: " + parents2);

		int height2 = eert.height();
		System.out.println("Height: " + height2);
		
		System.out.println("Is the tree perfect? " + eert.isPerfect());
		
		System.out.print("[");
		eert.inOrderPrint();
		System.out.println("]");
		
		System.out.print("[");
		eert.preOrderPrint();
		System.out.println("]");
		
		eert.ancestors("Oliver");
		
		
		System.out.println("\n\nInteger Tree: ");
		MySearchTree<Integer> intTree = new MySearchTree<>();
		
		intTree.add(5);
		intTree.add(2);
		intTree.add(1);
		intTree.add(4);
		intTree.add(7);
		intTree.add(6);
		intTree.add(10);

		
		
		int size3 = intTree.getSize();
		System.out.println("Size: " + size3);
		
		int leaves3 = intTree.leafCount();
		System.out.println("Leaves: " + leaves3);

		int parents3 = intTree.parentCount();
		System.out.println("Parents: " + parents3);

		int height3 = intTree.height();
		System.out.println("Height: " + height3);
		
		System.out.println("Is the tree perfect? " + intTree.isPerfect());
		
		System.out.print("[");
		intTree.inOrderPrint();
		System.out.println("]");
		
		System.out.print("[");
		intTree.preOrderPrint();
		System.out.println("]");
		
		intTree.ancestors(6);
	}

}
