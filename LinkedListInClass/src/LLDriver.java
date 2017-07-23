
public class LLDriver {

	public static void main(String[] args) {
		LL list = new LL();
		list.addfront(1);
		list.addfront(2);
		list.addfront(3);
		list.addfront(4);
		list.addfront(5);
		list.addfront(6);
		list.addfront(7);
		list.addEnd(99);
		
		System.out.println(list.toString());
		list.reverse();
		System.out.println(list.toString());
		
		list.delete(4);
		list.delete(1);
		list.delete(7);
	}


}
