package fox.shawn;
import java.util.Scanner;

public class GetName {
	public static String getName() {
		System.out.println("Enter your name: ");
		String name;
		Scanner in = new Scanner(System.in);
		name = in.nextLine();
		in.close();
		return name;
	}
}
