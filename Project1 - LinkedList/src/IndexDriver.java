
public class IndexDriver {

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
			System.out.println(x + " ");
		
		System.out.println("\n\n");
		
		list.removeLetter("B");
		list.remove("Stephen");
//		list.remove("Thomas");
//		list.remove("Bart");
//		list.removeLetter("S");
		list.removeLetter("M");
		list.removeLetter("T");
		
		System.out.println(list.find("Sarah") + "\n");
		System.out.println(list.find("Fred") + "\n");
//		for(String x: list)
//		System.out.println(x + " ");
		
		System.out.println(list);
	}

}
