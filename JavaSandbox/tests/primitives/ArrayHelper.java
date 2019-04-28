package primitives;

public class ArrayHelper {
	
	public static int sum(int... values) {
		int sum = 0;
		for(int i : values) {
			sum += i;
		}
		return sum;
	}

}
