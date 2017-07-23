
public class LinearProbingHashTableDriver {

	public static void main(String[] args) {
		LinearProbingHashTable<Double, Double> hashTableDD = new LinearProbingHashTable<Double, Double>();
		
		hashTableDD.insert(13.2, 88145.141);
		hashTableDD.insert(3.15, 3.15);
		hashTableDD.insert(88.44, 66.66);
		hashTableDD.insert(123.0, 123.1);
		hashTableDD.insert(779.21, 52.24);
		
		System.out.println("Table 1 <Double, Double> before manipulation...");
		System.out.println(hashTableDD);
		System.out.println(hashTableDD.getLocation(3.15));
		System.out.println(hashTableDD.getLocation(19.0));
		System.out.println(hashTableDD.find(779.21));
		System.out.println(hashTableDD.find(8.0));
		System.out.println(hashTableDD.delete(3.15));
		System.out.println(hashTableDD.delete(3.15));
		System.out.println("\nAfter manipulation...");
		System.out.println(hashTableDD);
		
		LinearProbingHashTable<String, Integer> hashTableSI = new LinearProbingHashTable<String, Integer>();
		
		hashTableSI.insert("Steve", 27);
		hashTableSI.insert("Manny", 19);
		hashTableSI.insert("Mark", 21);
		hashTableSI.insert("Jessica", 20);
		hashTableSI.insert("Johnny", 31);
		
		System.out.println("Table 2 <String, Integer> before manipulation...");
		System.out.println(hashTableSI);
		System.out.println(hashTableSI.getLocation("Johnny"));
		System.out.println(hashTableSI.getLocation("Jimmy"));
		System.out.println(hashTableSI.find("Steve"));
		System.out.println(hashTableSI.find("Jess"));
		System.out.println(hashTableSI.delete("Johnny"));
		System.out.println(hashTableSI.delete("Barbara"));
		System.out.println("\nAfter manipulation...");
		System.out.println(hashTableSI);
	}
	
	

}
