package lamdas_and_functional;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;

import org.junit.jupiter.api.Test;

class GenericUtilitiesTests {

	@Test
	void testMakeUnique() {
		ArrayList<Integer> values = new ArrayList<> ();
		values.add(1);
		values.add(1);
		values.add(2);
		values.add(3);
		values.add(3);
		ArrayList<Integer> uniqueValues = GenericUtilities.makeUnique(values);
		assertEquals(3, uniqueValues.size());
		assertEquals(1, uniqueValues.get(0).intValue());
		assertEquals(2, uniqueValues.get(1).intValue());
		assertEquals(3, uniqueValues.get(2).intValue());
	}
	
	@Test
	void testMakeUniqueStrings() {
		ArrayList<String> values = new ArrayList<> ();
		values.add(new String("hello"));
		values.add("hello");
		values.add("Greetings");
		values.add("Greetings");
		values.add("world");
		ArrayList<String> uniqueValues = GenericUtilities.makeUnique(values);
		assertEquals(3, uniqueValues.size());
		assertEquals(0, uniqueValues.get(0).compareTo("hello"));
		assertEquals(0, uniqueValues.get(1).compareTo("Greetings"));
		assertEquals(0, uniqueValues.get(2).compareTo("world"));
	}
	
	/**
	 * Demonstrate the user of a generic function that uses the BinaryOperator functional interface.
	 */
	@Test
	void testMaxWithInteger() {
		Comparator<Integer> comparator = (a, b) -> (a.compareTo(b));
		BinaryOperator<Integer> opMax = BinaryOperator.maxBy(comparator);
		Integer result = GenericUtilities.max(5,  7,  opMax);
		assertEquals(7, result.intValue());
	}

	
	/**
	 * Demonstrate the user of a generic function that uses the BinaryOperator functional interface.
	 */
	@Test
	void testMaxWithDouble() {
		Comparator<Double> comparator = (a, b) -> (a.compareTo(b));
		BinaryOperator<Double> opMax = BinaryOperator.maxBy(comparator);
		Double result = GenericUtilities.max(5.6,  5.7,  opMax);
		assertEquals(5.7, result.doubleValue());
	}

	
	/**
	 * Demonstrate the user of a generic function that uses the Predicate functional interface.
	 */
	@Test
	void testPredicate() {
		// Count even numbers in an ArrayList
		List<Integer> integerList = Arrays.asList(1, 3, 6, 7, 8, 9, 22, 44, 65, 400);
		assertEquals(5, GenericUtilities.countIf(integerList, (value) -> value % 2 == 0));
		
		// Count all three letter words
		List<String> strings = Arrays.asList("the", "cow", "jumped", "over", "the", "moon");
		assertEquals(3, GenericUtilities.countIf(strings, (element) -> element.length() == 3));
	}
}
