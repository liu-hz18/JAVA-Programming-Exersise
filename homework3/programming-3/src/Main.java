import java.util.Scanner;

public class Main
{
	public static void main(final String[] args)
	{
		try (final Scanner cin = new Scanner(System.in)) {
			while (cin.hasNextLine()){
				try {
					NetSecure.check(cin.nextLine());
				} catch (final NetException e) {
					System.out.println(e);
				}
			}	
		}
	}
}