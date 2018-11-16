import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

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
}
