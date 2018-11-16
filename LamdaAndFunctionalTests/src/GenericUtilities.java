import java.util.ArrayList;

public class GenericUtilities {
	/*
	 * Build a new list from the original where the new list contains no duplicate entries.
	 */
	public static <E extends Comparable<E>> ArrayList<E> makeUnique(ArrayList<E> input) {
		ArrayList<E> uniqueValues = new ArrayList<>();
		if(!input.isEmpty()) {
			uniqueValues.add(input.get(0));
			for(int i = 1; i < input.size(); ++i) {
				if(!uniqueValues.contains(input.get(i))) {
					uniqueValues.add(input.get(i));
				}
			}
		}
		return uniqueValues;
	}
}
