import java.util.ArrayList;
import java.util.Collection;
import java.util.function.*;

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
	
	/**
	 * Returns the greater of x or y using the supplied binary operator.
	 * @param x		the first value
	 * @param y		the second value
	 * @param operator	An operator that supports comparing x and y and returning the greater value
	 * @return
	 */
	public static <T> T max(T x, T y, BinaryOperator<T> operator) {
        return operator.apply(x, y);
    }
	
	/**
	 * Count the number of elements in the collection that satisfy the predicate.
	 * @param values any type of object that implements the Collection interface.
	 * @param operator a predicate that takes one value at a time from values and returns true or false
	 * @return number of items in the collection that caused the predicate to return true
	 */
	public static <T> int countIf(Collection<T> values, Predicate<T> predicate) {
		int numberOf = 0;
		
		for(T value : values) {
			if(predicate.test(value)) {
				++numberOf;
			}
		}
		
		return numberOf;
	}
}
