import java.util.Scanner;

interface EasyInteger {
	EasyInteger add(EasyInteger operand);
	String toString();
}

class Main {
	public static void main(final String... arg) {
		final BigInteger a, b;
		try (final Scanner cin = new Scanner(System.in)) {
			a = new BigInteger(cin.next());
			b = new BigInteger(cin.next());
		}
		System.out.println(a.add(b));
	}
}
