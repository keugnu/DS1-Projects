
public class Pair<E> {
	private E var1, var2;
	
	Pair(E var1, E var2) {
		this.var1 = var1;
		this.var2 = var2;
	}
	
	public E getfirst() {
		return var1;
	}
	
	public E getsecond() {
		return var2;
	}
	
	public void swap() {
		E temp = var1;
		var1 = var2;
		var2 = temp;
	}


	public static void main(String args[]) {
		
		String str1 = "Hello";
		String str2 = "World";
		
		Pair<String> stringSwap = new Pair<>(str1, str2);
		
		System.out.println("First: " + stringSwap.getfirst() + '\n' + "Second: " + stringSwap.getsecond() + "\n\n");
		
		stringSwap.swap();
		System.out.println("Calling swap method...");
		System.out.println("First: " + stringSwap.getfirst() + '\n' + "Second: " + stringSwap.getsecond());
	}
}


