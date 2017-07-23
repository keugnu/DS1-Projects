
public class main {
	
	public static int RecTest(int n) {
		if (n<=1)
			return 3;
		else
			return (RecTest(n-1) + 3);
	}

	public static void main(String[] args) {
		System.out.println( main.RecTest(10));

	}

}
