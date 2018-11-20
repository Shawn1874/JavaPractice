import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.*;

class ArrayListTests {

	@Test
	void testSort() {
		// Evidently Arraylist doesn't support primitive types.  It only supports
		// primitive helpers because it can only be created with something that 
		// inherits the object class.
		ArrayList<Integer> list = new ArrayList<>();
		list.add(5);
		list.add(3);
		list.add(7);
		list.sort(null);
		
		// 3 different ways of doing the comparison.
		assertEquals(new Integer(3), list.get(0));
		assertEquals(5, list.get(1).intValue());
		assertEquals(7, (int) list.get(2));
	}

	@Test
	void testSubList() {
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < 10; ++i) {
			list.add(i); // int is automatically boxed to an Integer
		}
		assertEquals(10, list.size());
		
		List<Integer> subList = list.subList(2, 5);
		assertEquals(3, subList.size());
		
		// Java doesn't unbox the Integer in this case due to the template function that requires 
		// int so I have to do it explicitly.
		assertEquals(2, subList.get(0).intValue());
	}
	
	/**
	 * Demonstrate another method to build an array list.  
	 * ArrayList<Integer> = { 1, 2, 3, 4 }; is not possible in java as it is in C++
	 * Arrays.asList is a static method that takes an arbitrary number of arguments
	 */
	@Test
	void testArraysAsListMethod() {
		List<Integer> li = Arrays.asList(1, 2, 3);
		assertEquals(3, li.size());
		
		List<String>  ls = Arrays.asList("one", "two", "three");
		assertEquals(3, ls.size());
	}
}
